/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allentje
 */
public class Product extends helper.db.Manager{
	
	private String name;
	private String description;
	private String type;
	private double price;
	
	public Product(){
		
	}
	
	public void read(){
		try {
			this.query("SELECT * FROM product WHERE id = ?");
			this.result();
			this.result.first();
		} catch (SQLException ex) {
			Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	public void readAll(){
		String query = "SELECT * FROM product LIMIT 0, 20";
	}

	public void readPerCategory(){
		String query = "SELECT * FROM product WHERE cs";
	}
	
	public void create(){
		String query = "INSERT INTO \"product\" () VALUES (?, ?, ?, ?)";
	}
	
	public void update(){
		String query = "UPDATE product SET ? = ?";
	}
	
	public void delete(){
		String query = "DELETE FROM product WHERE id = ?";
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
