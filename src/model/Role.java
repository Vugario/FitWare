package model;

import helper.db.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory.Result;

/**
 *
 * @author allentje
 */
public class Role extends Model{
	int id;
	String title;
	
	public Role() {	
	}
	
	public Role(ResultSet result) {
		super();
		
		this.result = result;
		this.setPropertiesFromResult();
	}
	
	public static ArrayList<Role> readByUserId(int userId) {
			
		ArrayList<Role> roles = new ArrayList<Role>();
			
		try {
			// Execute the query
			Model model = new Model();
			model.open();
			model.query(
					"SELECT role.* FROM \"role\""
					+ " INNER JOIN user_role ON role.id = user_role.\"roleID\""
					+ " WHERE user_role.\"userID\" = ?").setInt(1, userId);
			model.result();
			
			// Loop over all results
			while(model.result.next()) {
				roles.add(new Role(model.result));
			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return roles;

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
			this.title = this.result.getString("title");
			
			
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
