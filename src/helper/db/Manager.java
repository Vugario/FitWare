package helper.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import helper.ExceptionHandler;
import main.Settings;
import java.sql.Statement;

/**
 * This is the class to handle the connection to the database
 * @author Allen
 */
public class Manager {

	/**
	 * dbConnection is the database connection
	 */
	private static Connection dbConnection;
	/**
	 * Result is the out come of a query
	 */
	public ResultSet result;
	/**
	 * dbQeury is the query that is sent to the database
	 */
	private PreparedStatement dbQuery;
	private Statement stmnt;
	/**
	 * The id of latest row before saving
	 */
	private int key;

	public Manager() {

		try {
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}

	public void open() {

		// Check if a connection already exists
		if (dbConnection != null) {
			return;
		}

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
	public PreparedStatement query(String query) {

		try {
			dbQuery = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//stmnt = dbQuery.//.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}

		return dbQuery;
	}

	public static Connection getConnection() {
		return dbConnection;
	}

	/**
	 * Execute a query returning the last 
	 */
	public void execute() {
		try {
			dbQuery.execute();

		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}

	/**
	 * gets latest id from table for saving with relations
	 * @return the latest id from table for saving with relations
	 */
	public int getKey() {

		return key;

	}

	/**
	 * Execute a query returning a resultset
	 */
	public void result() {

		try {

			result = dbQuery.executeQuery();
		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}

	/**
	 * remember to close the connection when finished
	 */
	public void close() {
		try {
			if (dbConnection != null) {
				dbConnection.close();
			}
		} catch (Exception ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}
}
