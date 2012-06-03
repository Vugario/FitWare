/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.db.Model;
import helper.Datetime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allentje
 */
public class Purchase extends Model {

	private double price;
	private int product_id;
	private String paymentoption;
	private short quantity;
	private Timestamp datetime;
	private int user_id;
	private User user = new User();
	private Product product = new Product();
	
	public Purchase() {
	}
	
	public Purchase(ResultSet result) {
		super();
		
		this.result = result;
		this.setPropertiesFromResult();
	}

	public static ArrayList<Purchase> readByUserId(int id) {
		
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
			
		try {
			// Execute the query
			Model model = new Model();
			model.open();
			model.query("SELECT * FROM \"purchase\" WHERE user_id = ? ORDER BY datetime DESC LIMIT 10").setInt(1, id);
			model.result();
			
			// Loop over all results
			while(model.result.next()) {
				purchases.add(new Purchase(model.result));
			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return purchases;
	}

	/**
	 * read latest purchases, give limit for different overviews
	 * @param limit
	 * @return Purchase objects in array list
	 */
	public ArrayList<Purchase> readLastPurchase(int limit) {
	
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		
		try {
			this.open();
			this.query(
				"SELECT "
					+ "*"
				+ "FROM "
					+ "purchase pur "
				+ "LIMIT "
					+ "?"
				).setInt(1, limit);
			this.result();
		
			
			// Loop over all results
			while(this.result.next()) {
				purchases.add(new Purchase(this.result));
			}


		} catch (Exception ex) {

			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return purchases;
	}

	public boolean savePurchase() {
		//TODO create SQL for to save purchase
		// Save the purchase into the database
		try {
			
			boolean availableUser = (user_id > 0 ) ? true : false;
			
			this.open();
			PreparedStatement query = this.query(
					"INSERT INTO purchase "
						+ "(user_id, product_id, price, paymentoption, quantity, datetime)"
					+ "VALUES ("
						+ " ?,"
						+ " ?,"
						+ " ?,"
						+ " ?,"
						+ " ?,"
						+ " NOW()"
					+ ")");

			if(availableUser){
				query.setInt(1, user_id);
			}else{
				
				query.setNull(1, java.sql.Types.INTEGER);
			}
			
			query.setInt(2, product_id);
			query.setDouble(3, price);
			query.setString(4, paymentoption);
			query.setInt(5, quantity);
			
			
			this.execute();

		} catch (Exception ex) {

			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return false;

	}
	
	/**
	 * Get the purchased Product object
	 * 
	 * @return The purchased Product
	 */
	public Product getProduct() {
		
		Product product = new Product();
		product.readById(product_id);
		return product;
		
	}

	protected void setPropertiesFromResult() {
		try {

			// Check if there is a result
			if (this.result.getRow() == 0) {
				// There is no result, so return without doing anything
				return;
			}

			// Fill in all properties
			this.id = this.result.getInt("id");
			this.datetime = this.result.getTimestamp("datetime");
			this.paymentoption = this.result.getString("paymentoption");
			this.price = this.result.getDouble("price");
			this.quantity = this.result.getShort("quantity");
			this.user_id = this.result.getInt("user_id");

			// Set the product
			this.product_id = this.result.getInt("product_id");
			this.product = product.readById(this.getProduct_id());

		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * @author daan
	 * @return returns the table row objects
	 */
	public Object[] getTableRowObjects() {

		String fomatDatetime = null;

		if(this.datetime != null){
			Datetime purchaseDatetime = new Datetime(this.datetime);
			fomatDatetime = purchaseDatetime.format("dd-MM-yyyy HH:mm");
		}

		return new Object[] {
			fomatDatetime,
			"€"+price,
			product.getName(),
			paymentoption
			};
	}

	

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getPaymentoption() {
		return paymentoption;
	}

	public void setPaymentoption(String paymentoption) {
		this.paymentoption = paymentoption;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public short getQuantity() {
		return quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
}
