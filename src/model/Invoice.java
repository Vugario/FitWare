package model;

import helper.db.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daanvm
 */
public class Invoice extends Model{
	int id;
	int user_id;
	
	public Invoice() {	
	}
	
	public Invoice(ResultSet result) {
		super();
		
		this.result = result;
		this.setPropertiesFromResult();
	}
	
	public static ArrayList<Invoice> readByUserId(int userId) {
			
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
			
		try {
			// Execute the query
			Model model = new Model();
			model.open();
			model.query(
					"SELECT * FROM invoice"
					+ " WHERE user_id = ? ORDER BY id").setInt(1, userId);
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
	
	protected final void setPropertiesFromResult() {
		try {

			// Check if there is a result
			if (this.result.getRow() == 0) {
				// There is no result, so return without doing anything
				return;
			}

			// Fill in all properties
			this.id = this.result.getInt("id");
			this.user_id = this.result.getInt("user_id");
			
			
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
