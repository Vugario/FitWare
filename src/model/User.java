/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.db.Model;

import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.action.GetBooleanAction;

/**
 *
 * @author allentje
 */
public class User extends Model {

	String firstname;
	String lastname;
	String subname;
	String birthdate;
	String username;
	String password;
	String street;
	String housenumber;
	String city;
	String postcode;
	String phonenumber;
	String mobilenumber;
	String email;
	boolean gender;


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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
			}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String street) {
		this.housenumber = housenumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailadress() {
		return email;
	}

	public void setEmailadress(String emailadress) {
		this.email = emailadress;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
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
			this.birthdate = this.result.getString("birthdate");
			this.street = this.result.getString("street");
			this.city = this.result.getString("city");
			this.postcode = this.result.getString("postcode");
			this.email = this.result.getString("email");
			this.phonenumber = this.result.getString("phonenumber");
			this.mobilenumber = this.result.getString("mobilenumber");
			this.gender = this.result.getBoolean("gender");
			this.housenumber = this.result.getString("housenumber");
			this.username = this.result.getString("username");
			this.password = this.result.getString("password");
		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;
	}
}
