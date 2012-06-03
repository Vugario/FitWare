package model;

import helper.db.Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.medewerker.UserAdd;

/**
 * This class is used to read branches
 * @author allentje
 */
public class Branch extends Model {

	/**
	 * String city is the place where the branch is placed.
	 */
	String city;
	/**
	 * String street is the street where the branch is placed
	 */
	String street;
	/**
	 * String postcode is the postcode for a branch
	 */
	String postcode;
	/**
	 * String phonenumber is phonenumber of a branch
	 */
	String phonenumber;

	/**
	 * Default constructor for branch
	 */
	public Branch() {
	}

	/**
	 * This method is the constructor for branch
	 * @param result is the result of a query
	 */
	public Branch(ResultSet result) {
		super();

		this.result = result;
		this.setPropertiesFromResult();
	}

	/**
	 * This method reads a branch by the identifier
	 * @param id is the identifier of a branch
	 */
	public Branch(int id) {
		super();
		// Do the query
		try {
			this.open();
			this.query("SELECT * FROM \"branch\" WHERE id = ? LIMIT 1").setInt(1, id);
			this.result();
			this.result.first();

			this.setPropertiesFromResult();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * This methods reads every branch there is and puts it in a ArrayList
	 * @return returns the ArrayList Branch
	 */
	public static ArrayList<Branch> readAll() {

		ArrayList<Branch> branches = new ArrayList<Branch>();

		try {
			// Execute the query
			Model model = new Model();
			model.open();
			model.query("SELECT * FROM \"branch\"");
			model.result();

			// Loop over all results
			while (model.result.next()) {
				branches.add(new Branch(model.result));
			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return branches;
	}

	/**
	 * Sets all the properties from the query result
	 */
	protected final void setPropertiesFromResult() {
		try {

			// Check if there is a result
			if (this.result.getRow() == 0) {
				// There is no result, so return without doing anything
				return;
			}

			// Fill in all properties
			this.id = this.result.getInt("id");
			this.city = this.result.getString("city");
			this.street = this.result.getString("street");
			this.postcode = this.result.getString("postcode");
			this.phonenumber = this.result.getString("phonenumber");

		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * 
	 * @return the street of a branch
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * 
	 * @param Street is the street of a branch
	 */
	public void setStreet(String Street) {
		this.street = Street;
	}

	/**
	 * 
	 * @return the city of a branch
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city the city of a branch
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return the phonenumber of a branch
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * 
	 * @param phonenumber the phonenumber of a branch
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * 
	 * @return the postcode of a branch
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * 
	 * @param postcode the postcode of a branch
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
}
