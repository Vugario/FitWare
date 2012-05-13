/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.db.Manager;
import helper.db.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mennowolvers
 */
public class Enrollment extends Model {

	String qresultFirstname;
	String qresultLastname;
	String qresultSubname;
	String qresultUsername;
	String qresultStreet;
	//String qresultHouseNumber;
	String qresultCity;
	String qresultPostcode;
	String qresultPhonenumber;

	public Enrollment() {
            this.query("SELECT * FROM \"user\"");
            ResultSet result1 = this.result();
            
            System.out.println( result1.toString() );
	}
        
        

	
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
