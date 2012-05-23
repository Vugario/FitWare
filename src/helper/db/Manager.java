package helper.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.ExceptionHandler;
import main.Settings;
import java.sql.Statement;


public class Manager {

	private Connection dbConnection;
        
	public ResultSet result;
	private PreparedStatement dbQuery;
	private Statement stmnt;
	
	private int key;
	
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
	 * @return The prepared statement 
	 */
	public PreparedStatement query(String query)
	{	
		
		try {
			dbQuery = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//stmnt = dbQuery.//.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
		
		return dbQuery;
	}
	
	
	/**
	 * Execute a query returning the last 
	 */
	public void execute()
	{	
		try {
			dbQuery.execute();
			
			/*ResultSet generatedKeys = dbQuery.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				System.out.println("3");
				key = generatedKeys.getInt(1);
				
			}*/

		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}
	
	/**
	 * gets latest id from table for saving with relations
	 */
	public int getKey(){
		
		return key;

	}

	
	/**
	 * Execute a query returning a resultset
	 */
	public void result() {
		
		try {
			
			result = dbQuery.executeQuery();
		} catch (SQLException ex){
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		//	return null;
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
