package helper.invoice;

import helper.db.Model;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Enrollment;
import model.Invoice;
import model.Purchase;
import model.User;

/**
 *
 * @author Daan
 */
public class InvoiceCreator {

	/**
	 * The user for who we want to create an invoice
	 */
	protected User user = new User();
	
	/**
	 * We want to create an invoice for the month that contains this date
	 */
	protected GregorianCalendar month;

	/**
	 * All purchases that need to be put on this invoice
	 */
	protected ArrayList<Enrollment> enrollmentsOnInvoice = new ArrayList<Enrollment>();

	/**
	 * All purchases that need to be put on this invoice
	 */
	protected ArrayList<Purchase> purchasesOnInvoice = new ArrayList<Purchase>();
	
	/**
	 * Create an InvoiceCreator.
	 * This object can create Invoices for one member.
	 * 
	 * @param userId The user for who we want to create an invoice
	 * @param month We want to create an invoice for the month that contains this date
	 */
	public InvoiceCreator(int userId, GregorianCalendar month) {
		this.user.readUser(userId);
		this.month = month;
	}
	
	/**
	 * Create the invoice
	 * 
	 * @return The newly created and already saved invoice
	 */
	public Invoice run() {
		
		GregorianCalendar currentMonth = new GregorianCalendar();
		
		// Check if the month we want to create an invoice is completed yet.
		// We cannot create an invoice for the current or upcomming months.
		if(getEndOfMonth(this.month).after(getStartOfMonth(currentMonth))) {
			return null;
		}
		
		// Add all enrollments and purchases to this invoice
		this.enrollmentsOnInvoice = this.getEnrollments();
		this.purchasesOnInvoice   = this.getPurchases();
		
		// Calculate the amount
		Double amount = 0.0;
		// TODO
		
		// Create the invoice
		Invoice invoice = new Invoice();
		invoice.setUserID(user.getId());
		invoice.setPayed(false);
		invoice.setAmount(amount);
		
		// Save it
		// TODO
		
		// Return it
		return invoice;
	}

	/**
	 * Find out from which month the invoices need to be created
	 * If an invoice already exist for this member, the month after the last
	 * invoice is returned.
	 * If no invoice exists yet, than the month when the member subscribed is
	 * returned.
	 * 
	 * @param user The user to find this month for
	 * @return A calendar object
	 */
	protected static GregorianCalendar getFirstMonthForUser(User user) {

		// Initialize the return variable with the current time
		GregorianCalendar lastInvoiceCalendar = new GregorianCalendar();

		// Get all invoices
		ArrayList<Invoice> invoices = Invoice.readByUserId(user.getId());

		// Check if an invoice already exists
		if (invoices.size() > 0) {
			// Find the latest invoice for this user.
			Invoice lastInvoice = invoices.get(invoices.size() - 1);

			// Find the last invoice date
			Date lastInvoiceDate = new Date(lastInvoice.getInvoiceDate().getTime());
			lastInvoiceCalendar.setTime(lastInvoiceDate);
		} else {

			// No invoice exists yet.
			// Check the first enrollment date and purchase date.

			// Loop over each enrollment
			ArrayList<Enrollment> enrollments = Enrollment.readByUserId(user.getId());
			for (int i = 0; i < enrollments.size(); i++) {
				Enrollment enrollment = enrollments.get(i);

				GregorianCalendar enrollmentCalendar = new GregorianCalendar();
				Date enrollmentDate = new Date(enrollment.getTimestamp().getTime());
				enrollmentCalendar.setTime(enrollmentDate);

				if (enrollmentCalendar.before(lastInvoiceCalendar)) {
					// The enrollment time is before the time we found earlier
					lastInvoiceCalendar = enrollmentCalendar;
				}
			}

			// Loop over each purchase
			ArrayList<Purchase> purchases = Purchase.readByUserId(user.getId());
			for (int i = 0; i < purchases.size(); i++) {
				Purchase purchase = purchases.get(i);

				GregorianCalendar purchaseCalendar = new GregorianCalendar();
				Date purchaseDate = new Date(purchase.getDatetime().getTime());
				purchaseCalendar.setTime(purchaseDate);

				if (purchaseCalendar.before(lastInvoiceCalendar)) {
					// The purchase time is before the time we found earlier
					lastInvoiceCalendar = purchaseCalendar;
				}
			}
		}

		// Return it!
		return lastInvoiceCalendar;
	}
	
	/**
	 * Find all enrollments in the month we are creating the invoice for
	 * 
	 * @todo Return also the repeating enrollments
	 * @return All enrollments that need to be put on this invoice
	 */
	protected ArrayList<Enrollment> getEnrollments() {
		
		ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
		
		try {
			// Execute the query
			Model model = new Model();
			model.open();
			PreparedStatement query = model.query(
					"SELECT * FROM enrollment "
					+ "WHERE user_id = ?"
					+ "  AND timestamp BETWEEN ? AND ?");
			query.setInt(1, user.getId());
			query.setTimestamp(2, new Timestamp(getStartOfMonth(month).getTimeInMillis()));
			query.setTimestamp(3, new Timestamp(getEndOfMonth(month).getTimeInMillis()));
			model.result();
			
			// Loop over all results
			while(model.result.next()) {
				enrollments.add(new Enrollment(model.result));
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(InvoiceCreator.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return enrollments;
	}
	
	/**
	 * Find all purchases in the month we are creating the invoice for
	 * 
	 * @return All purchases that need to be put on this invoice
	 */
	protected ArrayList<Purchase> getPurchases() {
		
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		
		try {
			// Execute the query
			Model model = new Model();
			model.open();
			PreparedStatement query = model.query(
					"SELECT * FROM purchase "
					+ "WHERE user_id = ?"
					+ "  AND datetime BETWEEN ? AND ?"
					+ "  AND paymentoption = ?");
			query.setInt(1, user.getId());
			query.setTimestamp(2, new Timestamp(getStartOfMonth(month).getTimeInMillis()));
			query.setTimestamp(3, new Timestamp(getEndOfMonth(month).getTimeInMillis()));
			query.setString(4, "Op rekening");
			model.result();
			
			// Loop over all results
			while(model.result.next()) {
				purchases.add(new Purchase(model.result));
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(InvoiceCreator.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return purchases;
	}
	
	/**
	 * Return the start of the month
	 * 
	 * @param givenMonth The month to calculate the start for
	 * @return The start of the month
	 */
	public GregorianCalendar getStartOfMonth(GregorianCalendar givenMonth) {
		
		GregorianCalendar startMonth = (GregorianCalendar) givenMonth.clone();
		
		// Set to first time of current month
		startMonth.set(Calendar.DAY_OF_MONTH, startMonth.getActualMinimum(Calendar.DAY_OF_MONTH));
		startMonth.set(Calendar.HOUR_OF_DAY,  startMonth.getActualMinimum(Calendar.HOUR_OF_DAY));
		startMonth.set(Calendar.MINUTE,       startMonth.getActualMinimum(Calendar.MINUTE));
		startMonth.set(Calendar.SECOND,       startMonth.getActualMinimum(Calendar.SECOND));
		startMonth.set(Calendar.MILLISECOND,  startMonth.getActualMinimum(Calendar.MILLISECOND));
		
		return startMonth;
	}
	
	/**
	 * Return the end of the month
	 * 
	 * @param givenMonth The month to calculate the end for
	 * @return The end tof the month
	 */
	public GregorianCalendar getEndOfMonth(GregorianCalendar givenMonth) {
		
		GregorianCalendar endMonth = (GregorianCalendar) givenMonth.clone();
		
		// Set to last time of current month
		endMonth.set(Calendar.MILLISECOND,  endMonth.getActualMaximum(Calendar.MILLISECOND));
		endMonth.set(Calendar.SECOND,       endMonth.getActualMaximum(Calendar.SECOND));
		endMonth.set(Calendar.MINUTE,       endMonth.getActualMaximum(Calendar.MINUTE));
		endMonth.set(Calendar.HOUR_OF_DAY,  endMonth.getActualMaximum(Calendar.HOUR_OF_DAY));
		endMonth.set(Calendar.DAY_OF_MONTH, endMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return endMonth;
	}
}
