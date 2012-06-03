package model;

import helper.Datetime;
import helper.db.Model;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Application;
import view.popups.SuccessPopup;

/**
 * This class is used for collecting and setting user data
 * @author allentje
 */
public class User extends Model {

	/**
	 * The username for the user
	 */
	private String username;
	/**
	 * The firstname of the user
	 */
	private String firstname;
	/**
	 * The lastname of the user
	 */
	private String lastname;
	/**
	 * The subname of the user
	 */
	private String subname;
	/**
	 * The birthdate of a user
	 */
	private Timestamp birthdate;
	/**
	 * The gender of a user (true = male, false is female)
	 */
	private boolean gender;
	/**
	 * The emailadress of the user
	 */
	private String email;
	/**
	 * The user is inactive if he is deleted.
	 * True = active, false is inactive
	 */
	private boolean active;
	/**
	 * Password for the user to log on with
	 */
	private String password;
	/**
	 * The bankaccoutn number for th user.
	 */
	private int bankaccount;
	/**
	 * The name of the owner of the bankaccount
	 */
	private String baccountname;
	/**
	 * The stret where the user lives
	 */
	private String street;
	/**
	 * The housenumber where the user lives
	 */
	private String housenumber;
	/**
	 * The city where the user lives
	 */
	private String city;
	/**
	 * The postcode where the user lives
	 */
	private String postcode;
	/**
	 * The phonenumber of the user
	 */
	private String phonenumber;
	/**
	 * The mobile phonenumber of the user
	 */
	private String mobilenumber;
	/**
	 * The category of the user 
	 */
	private String category;
	/**
	 * Get model for relation
	 */
	private Role role = new Role();
	/**
	 * Role identifier
	 */
	private int roleId;
	/*
	 * The male gender
	 */
	public final static boolean MALE = true;
	/**
	 * The female gender
	 */
	public final static boolean FEMALE = false;

	/**
	 * Default constructor
	 */
	public User() {
	}

	/**
	 * The constructor for User
	 * @param result is the result of the query
	 */
	private User(ResultSet result) {
		super();

		this.result = result;
		this.setPropertiesFromResult();
	}

	/**
	 * This method reads a user by the user id
	 * @param id identifier of a user
	 * @return the result of the readUser qeury
	 */
	public User readUser(int id) {
		try {
			//Execute query
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

	/**
	 * This method is used to read a user by it's credentials (for login)
	 */
	public User readByCredentials(String username, String password) {
		try {
			// Execute query
			this.open();
			PreparedStatement query = this.query("SELECT * FROM \"user\" WHERE username = ? AND password = MD5(?) LIMIT 1");


			query.setString(1, username.toLowerCase());
			query.setString(2, password);
			this.result();

			this.result.first();
			this.setPropertiesFromResult();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;
	}

	/**
	 * This method reads all the users and puts the result of the query into the
	 * ArrayList users
	 * @return returns an array list of users
	 */
	public static ArrayList<User> readAll() {

		ArrayList<User> users = new ArrayList<User>();
		Model model = new Model();

		try {
			// Execute the query

			model.open();
			model.query(
					"SELECT * FROM \"user\" ORDER BY id DESC");
			model.result();

			// Loop over all results
			while (model.result.next()) {
				users.add(new User(model.result));
			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return users;
	}

	/**
	 * This method checks if a user exists
	 * @return if the amount is > 0.
	 */
	public boolean checkUserExist() {

		int userCount = 0;

		try {
			// Execute query
			this.open();
			PreparedStatement query = this.query(
					"SELECT COUNT(*) AS userCount "
					+ "FROM \"user\" "
					+ "WHERE username = ?");
			query.setString(1, username);

			this.result();
			this.result.first();
			userCount = this.result.getInt("userCount");

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return (userCount > 0);
	}

	/**
	 * This method creates a user
	 * @return true if creation of user is successfull
	 */
	public boolean create() {

		try {
			// Execute query
			this.open();

			PreparedStatement query = this.query(
					"INSERT INTO "
					+ "\"user\"(active, username, firstname, subname, lastname, birthdate, street, housenumber, phonenumber, mobilenumber, email, gender, password, bankaccount, city, postcode, role_id, baccountname)"
					+ "VALUES "
					+ "(true, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, MD5(?), ?, ?, ?, ?,?) RETURNING id");

			query.setString(1, username.toLowerCase());
			query.setString(2, firstname);
			query.setString(3, subname);
			query.setString(4, lastname);
			query.setTimestamp(5, birthdate);
			query.setString(6, street);
			query.setString(7, housenumber);
			query.setString(8, phonenumber);
			query.setString(9, mobilenumber);
			query.setString(10, email);
			query.setBoolean(11, gender);
			query.setString(12, password);
			query.setInt(13, bankaccount);
			query.setString(14, city);
			query.setString(15, postcode);
			query.setInt(16, roleId);
			query.setString(17, baccountname);

			this.execute();

			System.out.println(this.getKey());

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		Application.getInstance().showPopup(new SuccessPopup(
				"De gebruiker is toegevoegd."));

		return true;
	}

	/**
	 * This method updates a user
	 * @return true if succesfull, else false
	 */
	public boolean update() {

		try {
			// Execute the query
			this.open();

			boolean passwordChanged = (this.getPassword() == null) ? false : true;

			PreparedStatement query = this.query("UPDATE \"user\" SET "
					+ "username = ?,"
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
					+ "role_id = ?,"
					+ "baccountname = ?,"
					+ "bankaccount = ?"
					+ (passwordChanged ? ", password = MD5(?)" : "")
					+ "WHERE id = ?");

			query.setString(1, username.toLowerCase());
			query.setString(2, firstname);
			query.setString(3, subname);
			query.setString(4, lastname);
			query.setTimestamp(5, birthdate);
			query.setString(6, street);
			query.setString(7, housenumber);
			query.setString(8, phonenumber);
			query.setString(9, mobilenumber);
			query.setString(10, email);
			query.setBoolean(11, gender);
			query.setInt(12, roleId);
			query.setString(13, baccountname);
			query.setInt(14, bankaccount);

			if (passwordChanged) {
				query.setString(15, password);
				query.setInt(16, id);
			} else {
				query.setInt(15, id);
			}


			this.execute();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}

		return true;
	}

	/**
	 * This method sets the properties from a query result
	 */
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
			this.birthdate = this.result.getTimestamp("birthdate");
			this.gender = this.result.getBoolean("gender");
			this.email = this.result.getString("email");
			this.active = this.result.getBoolean("active");
			this.password = this.result.getString("password");
			this.bankaccount = this.result.getInt("bankaccount");
			this.street = this.result.getString("street");
			this.housenumber = this.result.getString("housenumber");
			this.city = this.result.getString("city");
			this.postcode = this.result.getString("postcode");
			this.phonenumber = this.result.getString("phonenumber");
			this.mobilenumber = this.result.getString("mobilenumber");
			this.roleId = this.result.getInt("role_id");
			this.baccountname = this.result.getString("baccountname");

			// Set the role
			this.role = role.readRole(this.getRoleId());

		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * This method gets the Row objects of for a table
	 * @author daan
	 * @return returns the table row objects
	 */
	public Object[] getTableRowObjects() {

		String roleTitle = null;

		switch (roleId) {
			case 1:
				roleTitle = "Lid";
				break;
			case 2:
				roleTitle = "Barmedewerker";
				break;
			case 3:
				roleTitle = "Admin";
		}

		String fomatBirthdate = null;

		if (birthdate != null) {
			Datetime datetime = new Datetime(birthdate);
			fomatBirthdate = datetime.format("dd-MM-yyyy");
		}

		return new Object[]{
					String.format("%04d", id),
					getFullName(),
					fomatBirthdate,
					roleTitle,
					email
				};
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

	/**
	 * This method reads the total of all members
	 * @return 
	 */
	public static int totalMembers() {
		Model model = new Model();

		int count = 0;
		try {
			// Execute the query
			model.open();
			model.query(
					"SELECT"
					+ " COUNT(u.id) "
					+ "FROM "
					+ "\"user\" u "
					+ "JOIN "
					+ "role r "
					+ "ON "
					+ "r.id = u.role_id "
					+ "WHERE "
					+ "r.title = 'member' "
					+ "AND "
					+ "u.active = true "
					+ "GROUP BY "
					+ "u.role_id");

			model.result();
			model.result.first();

			count = model.result.getInt(1);
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return count;
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * 
	 * @param active 
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * 
	 * @return 
	 */
	public int getBankaccount() {
		return bankaccount;
	}

	/**
	 * 
	 * @param bankaccount 
	 */
	public void setBankaccount(int bankaccount) {
		this.bankaccount = bankaccount;
	}

	/**
	 * 
	 * @return 
	 */
	public String getBaccountname() {
		return baccountname;
	}

	/**
	 * 
	 * @param baccountname 
	 */
	public void setBaccountname(String baccountname) {
		this.baccountname = baccountname;
	}

	/**
	 * 
	 * @return 
	 */
	public Timestamp getBirthdate() {
		return birthdate;
	}

	/**
	 * 
	 * @param birthdate 
	 */
	public void setBirthdate(Timestamp birthdate) {

		this.birthdate = birthdate;
	}

	/**
	 * 
	 * @return the city of a user
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city is the city of a user
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return the email of a user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email is the email of a user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return the firstname of a user
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * 
	 * @param firstname is the firstname of a user
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * 
	 * @return the gender of a user
	 */
	public boolean getGender() {
		return gender;
	}

	/**
	 * 
	 * @param gender is the gender of a user
	 */
	public void setGender(boolean gender) {
		this.gender = gender;
	}

	/**
	 * 
	 * @return the housenumber of a user
	 */
	public String getHousenumber() {
		return housenumber;
	}

	/**
	 * 
	 * @param housenumber is the housenumber of a user
	 */
	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	/**
	 * 
	 * @return the lastname of a user
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * 
	 * @param lastname is the lastname of a user
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * 
	 * @return the mobilenumber of a user
	 */
	public String getMobilenumber() {
		return mobilenumber;
	}

	/**
	 * 
	 * @param mobilenumber is the mobilenumber of a user
	 */
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	/**
	 * 
	 * @return the password of a user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password is the password of a user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return the phonenumber of a user
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * 
	 * @param phonenumber is the phonenumber of a user
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * 
	 * @return the postcode of a user
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * 
	 * @param postcode is the postcode of a user
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * 
	 * @return the street of a user
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * 
	 * @param street is the street of a user
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * 
	 * @return the subname of a user
	 */
	public String getSubname() {
		return subname;
	}

	/**
	 * 
	 * @param subname is the subname of a user
	 */
	public void setSubname(String subname) {
		this.subname = subname;
	}

	/**
	 * 
	 * @return the username for a user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username is the username for a user
	 */
	public void setUsername(String username) {
		this.username = username.toLowerCase();
	}

	/**
	 * 
	 * @return the category of a user
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category is the category of a user
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 
	 * @return the role of a dashboard
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * 
	 * @param role is the role for a user
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * 
	 * @return the role identifier for a user
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * 
	 * @param roleId is the role identifier for a user
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
