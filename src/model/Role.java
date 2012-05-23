package model;

import helper.db.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allentje
 */
public class Role extends Model{
	
	/**
	 * class attributes / table columns
	 */
	int id;
	String title;
	
	/**
	 * empty constructor for calling class
	 */
	public Role() {	
		
	}
	
	/**
	 * constructor to set results, this is not one in user due to the amount of results
	 * @param id
	 * @param title 
	 */
	public Role(int id, String title) {
		setId(id);
		setTitle(title);
	}
	
	public ArrayList<Role> readByUserId(int userId) {
				
		ArrayList<Role> roles = new ArrayList<Role>();
			
		try {
			
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
