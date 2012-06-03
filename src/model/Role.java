
package model;

import helper.db.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to read roles
 * @author allentje
 */
public class Role extends Model{
	
	/**
	 * class attributes / table columns
	 */
	String title;
	
	/**
	 * This is the default constructor for calling class
	 */
	public Role() {	
		
	}
	
	/**
	 * This method is the constructor to set results, this is not one in user due to the amount of results
	 * @param id
	 * @param title 
	 */
	public Role(int id, String title) {
		setId(id);
		setTitle(title);
	}
	
	/**
	 * This method is used to read a role by a user identifier
	 * @param userId identifier of a user
	 * @return the arraylist roles
	 */
	public ArrayList<Role> readByUserId(int userId) {
				
		ArrayList<Role> roles = new ArrayList<Role>();
			
		try {
			//execute the query
			this.open();
			this.query(
					"SELECT role.* FROM \"role\""
					+ " INNER JOIN user_role ON role.id = user_role.\"roleID\""
					+ " WHERE user_role.\"userID\" = ? ORDER BY role.id").setInt(1, userId);
			this.result();

			// Loop over all results
			while(this.result.next()) {
				roles.add(new Role(this.result.getInt("id"), this.result.getString("title")));
			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return roles;

	}
	/**
	 * This method is used to read a role by the role identifier
	 * @param roleId role identiefier
	 * @return the result of the query (a role)
	 */
	public Role readRole(int roleId) {
		
		try {
			this.open();
			this.query(
					"SELECT * FROM \"role\""
					+ "WHERE id = ? LIMIT 1").setInt(1, roleId);
			this.result();
			
			this.result.first();
			this.setPropertiesFromResult();
		}
		catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return this;
	}
	/**
	 * This method is used to set the properties from the result of the query
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
			this.title = this.result.getString("title");

		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	/**
	 * 
	 * @return the title of a role (member, barmedewerker or admin)
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 
	 * @param title is the title of a role (member, barmedewerker or admin)
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
}
