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
	String emailadress;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
		
	public String getEmailadress() {
		return emailadress;
	}

	public void setEmailadress(String emailadress) {
		this.emailadress = emailadress;
	}

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
