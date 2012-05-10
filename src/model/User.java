/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.db.Manager;
import helper.db.Model;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allentje
 */
public class User extends Model {
	
	String username;
	String firstname;
	String lastname;
	String subname;
	String birthdate;
	boolean gender;
	String email;
	boolean active;
	String password;
	int bankaccount;
	String street;
	String city;
	String postcode;
	String phonenumber;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(int bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	
	public User() {}

	/*public String userDataQuery() {

		try {
			this.query("SELECT * FROM \"user\" WHERE id = ?").setInt(1, 1);


		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			this.result = dbQuery.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {

			while (result.next()) {
				qresultFirstname = result.getString("firstname").toString();
				qresultLastname = result.getString("lastname").toString();
				qresultSubname = result.getString("subname").toString();
				qresultUsername = result.getString("username").toString();userDataQuery
				qresultStreet = result.getString("street").toString();
				qresultCity = result.getString("city").toString();
				
				
				
				System.out.println(qresultFirstname +" \n"+ qresultLastname +" \n"+ qresultSubname +" \n"+ qresultUsername +" \n"+ qresultStreet +" \n"+ qresultCity);
			}

		} catch (SQLException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		}
		return qresultFirstname;
	}*/
}
