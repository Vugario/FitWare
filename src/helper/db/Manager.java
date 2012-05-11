package helper.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.ExceptionHandler;
import main.Settings;


public class Manager {

	private Connection dbConnection;
        
	private Statement stmnt;
	private PreparedStatement dbQuery;
	
	public Manager() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}

	public void open() {
		try {
			
			String url = Settings.get("db-url");
			String name = Settings.get("db-user");
			String password = Settings.get("db-password");
			
			dbConnection = DriverManager.getConnection(url, name, password);
			
		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}
	
	/**
	 * prepare all querys
	 * @param query 
	 */
	public PreparedStatement query(String query)
	{	
		
		try {
			stmnt = dbConnection.createStatement();
			dbQuery = dbConnection.prepareStatement(query);

		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
		
		return dbQuery;
	}
	
	
	/**
	 * Execute a query without returning a result
	 */
	public void execute()
	{	
		try {
			dbQuery.executeUpdate();
		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}
	
	/**
	 * Execute a query returning a resultset
	 */
	public ResultSet result() {
		
		try {
			return dbQuery.executeQuery();
		} catch (SQLException ex){
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
			return null;
		}
	}
	
	 /**
     * remember to close the connection when finished
     */
    public void close() {
        try {
            dbConnection.close();
        } catch (Exception ex) {
            ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
        }
    }
	
}
