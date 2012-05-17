/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author allentje
 */
public class Purchase extends helper.db.Model {
	int id;
	private double	price;
	private int product_id;
	private String paymentoption;
	private short quantity;
    private Timestamp datetime;
	private int user_id;
	private User user = new User();
		
	public Purchase readLastPurchase(){
		try{
			this.open();
			this.query(
	"SELECT "
			+ "pur.datetime,"
			+ "p.name,"
			+ "pur.quantity*p.price as \"price\""
	+ "FROM "
					+ "\"user\" u "
	+ "JOIN "
					+ "purchase pur "
	+ "ON "
					+ "u.id = pur.user_id "
	+ "JOIN "
					+ "product p "
	+ "ON "
					+ "p.id = pur.product_id "
	+ "WHERE u.id = ? "
	+ "LIMIT "
					+ "10"
	).setInt(1, id);
			this.result();
			//this.result.first();

			this.setPropertiesFromResult();

		} catch (Exception ex) {	
					
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;	
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
			this.product_id = this.result.getInt("product_id");
			this.datetime = this.result.getTimestamp("datetime");
			this.paymentoption = this.result.getString("payment_option");
			this.price = this.result.getDouble("price");
			this.quantity = this.result.getShort("quantity");
			this.user_id = this.result.getInt("user_id");
			
			
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
}
