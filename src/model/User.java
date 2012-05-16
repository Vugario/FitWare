/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.db.Model;
import helper.Datetime;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author allentje
 */
public class User extends Model {

	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String subname;
	private String birthdate;
	private boolean gender;
	private String email;
	private boolean active;
	private String password;
	private String bankaccount;
	private String street;
	private String housenumber;
	private String city;
	private String postcode;
	private String phonenumber;
	protected String mobilenumber;
	
	public final static boolean MALE = true;
	public final static boolean FEMALE = false;
	
	protected String category;

	public User() {
	}

	public User readUser(int id) {
		try {
			this.open();
			this.query("SELECT * FROM \"user\" WHERE id = ? LIMIT 1").setInt(1, id);
			this.result();
			this.result.first();

			this.setPropertiesFromResult();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;
	}

	public User readByCredentials(String username, String password) {
		try {
			this.open();
			PreparedStatement query = this.query("SELECT * FROM \"user\" WHERE username = ? AND password = MD5(?) LIMIT 1");
			
			query.setString(1, username.toLowerCase());
			query.setString(2, password);
			this.result();
			
			//System.exit(0);
			
			this.result.first();
//System.out.println(this.result.getString("id"));
//			System.out.println(this.result.getStatement());
//			System.exit(0);
			this.setPropertiesFromResult();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;
	}

	/*public void setDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
		
		try {
			bdate = dateFormat.parse(birthdate);
		} catch (ParseException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}*/

	public void save() {
		try {
			this.open();

			boolean passwordChanged = !"".equals(password);

			//gender = profile.jRadioButtonGenderMale.isSelected(); //how do i get this state?

			PreparedStatement query = this.query("UPDATE \"user\" SET username = ?,"
					+ "firstname = ?,"
					+ "subname = ?,"
					+ "lastname = ?,"
					+ "birthdate = ?,"
					+ "street = ?,"
					+ "housenumber = ?,"
					+ "phonenumber = ?,"
					+ "mobilenumber = ?,"
					+ "email = ?,"
					+ "gender = ?,"
					+ (passwordChanged ? "password = ?" : "")
					+ "WHERE id = ?");
			query.setString(1, username.toLowerCase());
			query.setString(2, firstname);
			query.setString(3, subname);
			query.setString(4, lastname);
			
			//Datetime datetime = new Datetime(birthdate);
			
			//query.setTimestamp(14, birthdate);
			query.setString(6, street);
			query.setString(7, housenumber);
			query.setString(8, phonenumber);
			query.setString(9, mobilenumber);
			query.setString(10, email);
			query.setBoolean(11, gender);
			if (passwordChanged) {
				query.setString(12, password);
				query.setInt(13, id);
			} else {
				query.setInt(12, id);
			}

			this.result();

			this.setPropertiesFromResult();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

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
			this.username = this.result.getString("username");
			this.firstname = this.result.getString("firstname");
			this.lastname = this.result.getString("lastname");
			this.subname = this.result.getString("subname");
			this.birthdate = this.result.getString("birthdate");
			this.gender = this.result.getBoolean("gender");
			this.email = this.result.getString("email");
			this.active = this.result.getBoolean("active");
			this.password = this.result.getString("password");
			this.bankaccount = this.result.getString("bankaccount");
			this.street = this.result.getString("street");
			this.housenumber = this.result.getString("housenumber");
			this.city = this.result.getString("city");
			this.postcode = this.result.getString("postcode");
			this.phonenumber = this.result.getString("phonenumber");
			this.mobilenumber = this.result.getString("mobilenumber");

		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Get the full name of this user
	 * 
	 * @return The full name
	 */
	public String getFullName() {

		String fullName = "";

		// Add the firstName
		fullName += this.getFirstname();

		// Add the subName when needed
		if (this.getSubname() != null) {
			fullName += " " + this.getSubname();
		}

		// Add the lastName
		fullName += " " + this.getLastname();

		return fullName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
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

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
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
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
