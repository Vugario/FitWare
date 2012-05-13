/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.db.Model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allentje
 */
public class User extends Model {

	String firstname;
	String lastname;
	String subname;
	String username;
	String street;
	String city;
	String postcode;
	String phonenumber;

	public User() {
		//this.query("SELECT * FROM \"user\"");
		// this.result();
	}

	public User readUser(int id) {
		try {
			this.open();
			this.query("SELECT * FROM \"user\" WHERE id = 1 LIMIT 1");
			this.result();
			this.result.first();

			this.firstname = this.result.getString("firstname");
			this.lastname = this.result.getString("lastname");
			this.subname = this.result.getString("subname");
			this.username = this.result.getString("username");
			this.street = this.result.getString("street");
			this.city = this.result.getString("city");
			this.postcode = this.result.getString("postcode");
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return this;
	}
	
}
