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

/**
 * The user model for collecting and setting user data
 * @author allentje
 */
public class User extends Model {

	private String username;
	private String firstname;
	private String lastname;
	private String subname;
	private Timestamp birthdate;
	private boolean gender;
	private String email;
	private boolean active;
	private String password;
	private int bankaccount;
	private String street;
	private String housenumber;
	private String city;
	private String postcode;
	private String phonenumber;
	private String mobilenumber;
	private String category;
	private Role role = new Role();
	private int roleId;

	public final static boolean MALE = true;
	public final static boolean FEMALE = false;

	public User() {
	}

	private User(ResultSet result) {
		super();
		
		this.result = result;
		this.setPropertiesFromResult();
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
			
			this.result.first();
			this.setPropertiesFromResult();
			
		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;
	}
	
	/**
	 * 
	 * @return returns an array list of Product
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
			while(model.result.next()) {
				users.add(new User(model.result));
			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return users;
	}

	public boolean create(){
		
		try {
			this.open();
			
			PreparedStatement query = this.query(
					"INSERT INTO "
					+ "\"user\"(active, username, firstname, subname, lastname, birthdate, street, housenumber, phonenumber, mobilenumber, email, gender, password, bankaccount, city, postcode, role_id)"
					+ "VALUES "
					+ "(true, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, MD5(?), ?, ?, ?, ?) RETURNING id"
				);

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

						
			this.execute();
			//this.result();
			//result.first();
			//System.out.println(result.getInt(1));
			
			//this.getId();
			System.out.println(this.getKey());
			
		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		
		return true;
	}
	
	public boolean update() {
		
		try {
			this.open();

			boolean passwordChanged = (this.getPassword() == null ) ? false : true;
			
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
					+ "bankaccount = ?"
					+ (passwordChanged ? ", password = MD5(?)" : "")
					+ "WHERE id = ?"
				);

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
			
			query.setInt(13, bankaccount);
			
			if (passwordChanged) {
				query.setString(14, password);
				query.setInt(15, id);
			}else{
				query.setInt(14, id);
			}

			this.execute();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}

		return true;
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
			
			// Set the role
			this.role = role.readRole(this.getRoleId());

		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * @author daan
	 * @author allen how does the dat get set?
	 * @return returns the table row objects
	 */
		public Object[] getTableRowObjects() {
			
			String roleTitle = null;
			
			switch(roleId){
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
			
			if(birthdate != null){
				Datetime datetime = new Datetime(birthdate);
				fomatBirthdate = datetime.format("dd-MM-yyyy");
			}
			
			return new Object[] {
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

	public Timestamp getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Timestamp birthdate) {

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
