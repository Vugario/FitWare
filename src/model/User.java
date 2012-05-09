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
	String resultFirstname;

    public User() {
        //this.query("SELECT * FROM \"user\"");
       // this.result();

    }

    public String userDataQuery() {
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
				resultFirstname = result.getString("firstname");
				System.out.println(resultFirstname);
			}
		
		} catch (SQLException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		}
		return resultFirstname;
		jTextField2.setText(resultFirstname);

    }
}
