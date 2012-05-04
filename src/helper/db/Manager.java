package helper.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import main.ExceptionHandler;
import main.Settings;

/**
 * This class is used as base class for Model, and thus as base class for all
 * classes in the Model package
 */
public class Manager {

	private Connection dbConnection;

	/**
	 * This method is used to chain after executing query method
	 * @see This is maybe to much to return and should think about using what we need in loose methods
	 * @return 
	 */
	public ResultSet resultSet() {
		return resultSet();
	}

	/**
	 * Construct opens connection due to there always should be a connection
	 */
	public Manager() {
		try {
			// Initiate driver
			// The driver needs to be downloaded and included in the librarys
			Class.forName("org.postgresql.Driver");

			String url = Settings.get("db-url");
			String name = Settings.get("db-name");
			String password = Settings.get("db-password");

			dbConnection = DriverManager.getConnection(url, name, password);

		} catch (ClassNotFoundException ex) {
			Logger.getLogger("LET OP mocht je dit zien en je krijgt hierna een null dan moet je de driver installeren: " + Manager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Execute a query without returning a result
	 * @param query Like UPDATE, INSERT, CREATE, etc.
	 */
	public void q(String query) {
		try {
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}

	/**
	 * Execute a query and return the result
	 * @param query Probably a SELECT
	 * @return The result of the query
	 */
	public ResultSet query(String query) {
		try {
			Statement statement = dbConnection.createStatement();
			return statement.executeQuery(query);
		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
		return null;
	}
}
