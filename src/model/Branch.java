/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author allentje
 */
public class Branch extends Model {

	int id;
	String city;
	String street;
	String postcode;
	String phonenumber;

	public Branch() {
	}

	public Branch(ResultSet result) {
		super();

		this.result = result;
		this.setPropertiesFromResult();
	}

	/*public int readBranchById() {
		try {
			
			//Set city variable
			UserAdd useradd = new UserAdd();
			city = useradd.getjComboBox1().getSelectedItem().toString();
			
			
			// Execute the query
			this.open();
			
			PreparedStatement query = this.query("SELECT id FROM \"branch\" WHERE city = '?' LIMIT 1");
			
			query.setString(1, city);
			
			this.execute();


		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return id;
	}*/

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

	public String getStreet() {
		return street;
	}

	public void setStreet(String Street) {
		this.street = Street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
