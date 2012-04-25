package helper.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Manager {
	
	private Connection dbConnection;
	private ResultSet result;
	
	/**
	 * This method is used to chain after executing query method
	 * @see This is maybe to much to return and should think about using what we need in loose methods
	 * @return 
	 */
	public ResultSet resultSet(){
		return resultSet();
	};
	
	/**
	 * construct opens connection due to there always should be a connection
	 */
	public  Manager()
	{
		try {
			
			//initiate driver
			Class.forName("org.postgresql.Driver");
			
			dbConnection = DriverManager.getConnection("jdbc:postgresql://allen.pb/fitshape", "postgres", "root");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void query(String query)
	{	
		
		try {
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(query);
			//System.out.println(result);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		
	}
	
	
	public Object toObject() throws SQLException
	{
		return result.getObject(null);
	}
	
	
}
