package model;

import helper.Datetime;
import helper.InvoiceCreator;
import helper.db.Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daanvm
 */
public class Invoice extends Model{
	int userID;
	boolean payed;
	double amount;
	Timestamp invoiceDate;
	
	public Invoice() {	
	}
	
	public Invoice(ResultSet result) {
		super();
		
		this.result = result;
		this.setPropertiesFromResult();
	}
	
	public Invoice readInvoice(int invoiceId) {
		try {
			this.open();
			this.query("SELECT * FROM \"invoice\" WHERE id = ? LIMIT 1").setInt(1, invoiceId);
			this.result();
			this.result.first();

			this.setPropertiesFromResult();
			
		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;
	}
	
	public static ArrayList<Invoice> readAll() {
			
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
			
		try {
			// Execute the query
			Model model = new Model();
			model.open();
			model.query("SELECT * FROM invoice");
			model.result();
			
			// Loop over all results
			while(model.result.next()) {
				invoices.add(new Invoice(model.result));
			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return invoices;

	}
	
	public static ArrayList<Invoice> readByUserId(int userId) {
			
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
			
		try {
			// Execute the query
			Model model = new Model();
			model.open();
			model.query(
					"SELECT * FROM invoice"
					+ " WHERE \"userID\" = ? ORDER BY id").setInt(1, userId);
			model.result();
			
			// Loop over all results
			while(model.result.next()) {
				invoices.add(new Invoice(model.result));
			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return invoices;

	}
	
	public boolean create(){
		
		// Recalculate the price
		recalculateAmount();
		
		// Set to paid if amount = 0
		if(getAmount() == 0.0) {
			setPayed(true);
		}
		
		try {
			this.open();
			
			PreparedStatement query = this.query(
					"INSERT INTO "
					+ "invoice (\"userID\", payed, amount, \"invoiceDate\")"
					+ "VALUES "
					+ "(?, ?, ?, ?) RETURNING id"
				);

			query.setInt(1, userID);
			query.setBoolean(2, payed);
			query.setDouble(3, amount);
			query.setTimestamp(4, invoiceDate);

			this.execute();
			
		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Find all enrollments in the month we are creating the invoice for
	 * 
	 * @todo Return also the repeating enrollments
	 * @return All enrollments that need to be put on this invoice
	 */
	public ArrayList<Enrollment> getEnrollments() {
		
		ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
		
		try {
			// Execute the query
			Model model = new Model();
			model.open();
			PreparedStatement query = model.query(
					"SELECT * FROM enrollment "
					+ "WHERE user_id = ?"
					+ "  AND EXTRACT(YEAR FROM \"datetime\") <= ?"
					+ "  AND EXTRACT(MONTH FROM \"datetime\") <= ?");
			query.setInt(1, getUserID());
			query.setInt(2, getYear());
			query.setInt(3, getMonth());
			model.result();
			
			// Loop over all results
			while(model.result.next()) {
				enrollments.add(new Enrollment(model.result));
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(InvoiceCreator.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		// Loop over each enrollment
		// If an enrollment is non-monthly (thus for a fixed period), the whole amount is paid in advance
		// Don't include these enrollments every month, only the first month it must be paid
		Iterator<Enrollment> iterator = enrollments.iterator();
		while (iterator.hasNext()) {
			Enrollment enrollment = iterator.next();
			Subscription subscription = enrollment.getSubscription();
			
			// Only add non-monthly subscriptions if the enrollment was this month
			Date enrollmentDate = new Date(enrollment.getTimestamp().getTime());
			GregorianCalendar enrollmentCalendar = new GregorianCalendar();
			enrollmentCalendar.setTime(enrollmentDate);
			
			if(!subscription.getMonthly()
					&& enrollmentCalendar.get(Calendar.YEAR) != getInvoiceCalendar().get(Calendar.YEAR)
					&& enrollmentCalendar.get(Calendar.MONTH) != getInvoiceCalendar().get(Calendar.MONTH)) {
				// Remove this subscription
				// It is paid already
				iterator.remove();
			}
		}
		
		return enrollments;
	}
	
	/**
	 * Find all purchases in the month we are creating the invoice for
	 * 
	 * @return All purchases that need to be put on this invoice
	 */
	public ArrayList<Purchase> getPurchases() {
		
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		
		try {
			// Execute the query
			Model model = new Model();
			model.open();
			PreparedStatement query = model.query(
					"SELECT * FROM purchase "
					+ "WHERE user_id = ?"
					+ "  AND EXTRACT(YEAR FROM \"datetime\") = ?"
					+ "  AND EXTRACT(MONTH FROM \"datetime\") = ?"
					+ "  AND paymentoption = ?");
			query.setInt(1, getUserID());
			query.setInt(2, getYear());
			query.setInt(3, getMonth());
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
	 * Recalculate the price of this invoice
	 */
	public void recalculateAmount() {
		
		Double newAmount = 0.0;
		
		// Loop over each enrollment
		ArrayList<Enrollment> enrollments = getEnrollments();
		for (int i = 0; i < enrollments.size(); i++) {
			
			Enrollment enrollment = enrollments.get(i);
			Subscription subscription = enrollment.getSubscription();
			
			// Add the amount
			newAmount += subscription.getPrice();
		}
		
		// Loop over each purchase
		ArrayList<Purchase> purchases = getPurchases();
		for (int i = 0; i < purchases.size(); i++) {
			
			Purchase purchase = purchases.get(i);
			
			// Add the amount
			newAmount += purchase.getPrice();
		}
		
		// Update the amount
		this.setAmount(newAmount);
	}
	
	protected final void setPropertiesFromResult() {
		try {

			// Check if there is a result
			if (this.result.getRow() == 0) {
				// There is no result, so return without doing anything
				return;
			}

			// Fill in all properties
			this.id = this.result.getInt("id");
			this.userID = this.result.getInt("userid");
			this.payed = this.result.getBoolean("payed");
			this.amount = this.result.getDouble("amount");
			this.invoiceDate = this.result.getTimestamp("invoicedate");
			
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Get the user of this invoice
	 * 
	 * @return The user of this invoice
	 */
	public User getUser() {
		
		User user = new User();
		user.readUser(userID);
		return user;
	}
	
	/**
	 * Get the GregorianCalendar object for this invoiceDate
	 * 
	 * @return The invoiceDate as GregorianCalendar object
	 */
	public GregorianCalendar getInvoiceCalendar() {
		Date date = new Date(getInvoiceDate().getTime());
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		return calendar;
	}
	
	/**
	 * Get the year of this invoice
	 * 
	 * @return The year this invoice is created in
	 */
	public int getYear() {
		return getInvoiceCalendar().get(Calendar.YEAR);
	}
	
	/**
	 * Get the month of this invoice (Jan = 1, Dec = 12)
	 * 
	 * @return The month this invoice is created in
	 */
	public int getMonth() {
		
		// Add 1, because the calendar object uses jan = 0 and dec = 11.
		return getInvoiceCalendar().get(Calendar.MONTH) + 1;
	}
	
	/**
	 * Get a list of objects that can be used to store this Invoice in a JTable
	 * 
	 * @param includeUsers Set to true if the user info must be included
	 * @return The row for in a JTable
	 */
	public Object[] getTableRowObjects(boolean includeUsers) {
		
		Datetime date = new Datetime(invoiceDate);
		
		if(includeUsers) {
			
			// Get the user
			User user = getUser();
			
			// Return a table row with user id and username
			return new Object[] {
				String.format("%04d", id),
				String.format("%04d", user.getId()),
				user.getFullName(),
				date.format("MMMM yyyy"),
				String.format("€ %.2f", amount),
				date.format("dd-MM-yyyy"),
				payed ? "Betaald" : "Niet betaald"
			};
			
		} else {
			
			// Return a table row without user id and username
			return new Object[] {
				String.format("%04d", id),
				date.format("MMMM yyyy"),
				String.format("€ %.2f", amount),
				date.format("dd-MM-yyyy"),
				payed ? "Betaald" : "Niet betaald"
			};
			
		}
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Timestamp invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
