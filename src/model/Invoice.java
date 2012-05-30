package model;

import helper.Datetime;
import helper.db.Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daanvm
 */
public class Invoice extends Model{
	int id;
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
			this.query("SELECT * FROM \"invoice\" WHERE id = ? LIMIT 1").setInt(1, id);
			this.result();
			this.result.first();

			this.setPropertiesFromResult();
			
		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;
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
	 * Get a list of objects that can be used to store this Invoice in a JTable
	 * @return The row for in a JTable
	 */
	public Object[] getTableRowObjects() {
		
		Datetime date = new Datetime(invoiceDate);
		
		return new Object[] {
			String.format("%04d", id),
			date.format("MMMM yyyy"),
			String.format("€ %.2f", amount),
			date.format("dd-MM-yyyy"),
			payed ? "Betaald" : "Niet bataald"
		};
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
