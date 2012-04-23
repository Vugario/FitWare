package helper.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author allentje
 */
public class Manager {
	
	private Connection dbConnection;

	public ResultSet resultSet(){
		return resultSet();
	};
	
	public Manager()
	{
		try {
			dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fitshape", "postgres", "");
		} catch (SQLException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void query(String query)
	{	
		
		try {
			Statement stmt = dbConnection.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	
	
}
