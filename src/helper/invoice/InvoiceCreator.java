package helper.invoice;

import helper.db.Model;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Enrollment;
import model.Invoice;
import model.Purchase;
import model.Subscription;
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
	 * Create all invoices for all users.
	 * This method is Kim's best friend :)
	 * 
	 * @return The number of invoices created
	 */
	public static int createAllInvoices() {
		
		// Get all users
		ArrayList<User> users = User.readAll();
		
		// Loop over all users and create the invoices
		int invoiceCount = 0;
		
		for (int i = 0; i < users.size(); i++) {
			
			User user = users.get(i);
			
			// Create the invoices
			invoiceCount += createAllInvoicesForUser(user);
		}
		
		return invoiceCount;
	}
	
	/**
	 * Create all invoices for the given user
	 * 
	 * @param user The user to create all invoices for
	 * @return The number of invoices created
	 */
	public static int createAllInvoicesForUser(User user) {
		
		// Get the first month to create an invoice for
		GregorianCalendar firstMonth = getFirstMonthForUser(user);
		
		// Get the current month
		GregorianCalendar currentMonth = new GregorianCalendar();
		
		// Loop untill all past months since firstMonth have been processed
		int invoiceCount = 0;
		GregorianCalendar processingMonth = firstMonth;
		
		while (getEndOfMonth(processingMonth).before(getStartOfMonth(currentMonth))) {
			
			// Create the invoice
			InvoiceCreator invoiceCreator = new InvoiceCreator(user.getId(), processingMonth);
			Invoice invoice = invoiceCreator.run();
			
			// Increase the processingMonth and invoiceCount
			processingMonth.add(Calendar.MONTH, 1);
			invoiceCount++;
			
		}
		
		return invoiceCount;
	}
	
	/**
	 * Create the invoice
	 * 
	 * @return The newly created and already saved invoice
	 */
	public Invoice run() {
		
		GregorianCalendar currentMonth = new GregorianCalendar();
		
		GregorianCalendar invoiceDate = getEndOfMonth(this.month);
		
		// Check if the month we want to create an invoice is completed yet.
		// We cannot create an invoice for the current or upcomming months.
		if(invoiceDate.after(getStartOfMonth(currentMonth))) {
			return null;
		}
		
		// Add all enrollments and purchases to this invoice
		this.enrollmentsOnInvoice = this.getEnrollments();
		this.purchasesOnInvoice   = this.getPurchases();
		
		// Calculate the amount
		Double amount = 0.0;
		
		// Loop over each enrollment
		for (int i = 0; i < this.enrollmentsOnInvoice.size(); i++) {
			
			Enrollment enrollment = this.enrollmentsOnInvoice.get(i);
			Subscription subscription = enrollment.getSubscription();
			
			// Only add non-monthly subscriptions if the enrollment was this month
			Date enrollmentDate = new Date(enrollment.getTimestamp().getTime());
			GregorianCalendar enrollmentCalendar = new GregorianCalendar();
			enrollmentCalendar.setTime(enrollmentDate);
			
			if(subscription.isMonthly() && enrollmentCalendar.get(Calendar.MONTH) != this.month.get(Calendar.MONTH)) {
				// Skip this subscription
				continue;
			}
			
			// Add the amount
			amount += subscription.getPrice();
		}
		
		// Loop over each purchase
		for (int i = 0; i < this.purchasesOnInvoice.size(); i++) {
			
			Purchase purchase = this.purchasesOnInvoice.get(i);
			
			// Add the amount
			amount += purchase.getPrice();
		}
		
		// Create the invoice
		Invoice invoice = new Invoice();
		invoice.setUserID(user.getId());
		invoice.setPayed(false);
		invoice.setAmount(amount);
		invoice.setInvoiceDate(new Timestamp(invoiceDate.getTimeInMillis()));
		
		// Save it
		invoice.create();
		
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
		GregorianCalendar nextInvoiceCalendar = new GregorianCalendar();

		// Get all invoices
		ArrayList<Invoice> invoices = Invoice.readByUserId(user.getId());

		// Check if an invoice already exists
		if (invoices.size() > 0) {
			// Find the latest invoice for this user.
			Invoice lastInvoice = invoices.get(invoices.size() - 1);

			// Find the last invoice date
			Date lastInvoiceDate = new Date(lastInvoice.getInvoiceDate().getTime());
			nextInvoiceCalendar.setTime(lastInvoiceDate);
			
			// And add one month
			nextInvoiceCalendar.add(Calendar.MONTH, 1);
			
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

				if (enrollmentCalendar.before(nextInvoiceCalendar)) {
					// The enrollment time is before the time we found earlier
					nextInvoiceCalendar = enrollmentCalendar;
				}
			}

			// Loop over each purchase
			ArrayList<Purchase> purchases = Purchase.readByUserId(user.getId());
			for (int i = 0; i < purchases.size(); i++) {
				Purchase purchase = purchases.get(i);

				GregorianCalendar purchaseCalendar = new GregorianCalendar();
				Date purchaseDate = new Date(purchase.getDatetime().getTime());
				purchaseCalendar.setTime(purchaseDate);

				if (purchaseCalendar.before(nextInvoiceCalendar)) {
					// The purchase time is before the time we found earlier
					nextInvoiceCalendar = purchaseCalendar;
				}
			}
		}

		// Return it!
		return nextInvoiceCalendar;
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
					+ "  AND \"datetime\" BETWEEN ? AND ?");
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
	public static GregorianCalendar getStartOfMonth(GregorianCalendar givenMonth) {
		
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
	public static GregorianCalendar getEndOfMonth(GregorianCalendar givenMonth) {
		
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
