package model;

import helper.Datetime;
import helper.ExceptionHandler;
import helper.db.Model;
import java.sql.Timestamp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Settings;

/**
 *	This class is used to make enrollments and set subscriptions to enrollments.
 * @author mennowolvers
 */
public class Enrollment extends Model {

	private int user_id;
	private int subscription_id;
	private int branch_id;
	private Timestamp datetime;
	private User user;
	private Subscription subscription;
	private boolean enrolled;
	private Branch branch;
	
	/**
	 * This method is the default constructor for Enrollment
	 */
	public Enrollment() {
	}
	
	/**
	 * This method is the constructor for Enrollment
	 * @param result is the result of a query
	 */
	public Enrollment(ResultSet result) {
		super();

		this.result = result;
		this.setPropertiesFromResult();
	}
	

	/**
	 * This method collects all the subscriptions for the member dashboard
	 * @param user_id
	 * @return the ArrayList Enrollment
	 */

	public static ArrayList<Enrollment> readByUserId(int id) {

		ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();

		try {
			// Execute the query
			Model model = new Model();
			model.open();
			model.query("SELECT * FROM \"enrollment\" WHERE user_id = ?").setInt(1, id);
			model.result();

			// Loop over all results
			while (model.result.next()) {
				enrollments.add(new Enrollment(model.result));
			}

		} catch (Exception ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}

		return enrollments;
	}
	
	/**
	 * This method reads the enrollment information that belongs to a certain
	 * subscription identifier.
	 * @param id
	 * @return the Arraylist of Users
	 */
	public static ArrayList<User> readBySubscriptionId(int id) {

		ArrayList<User> users = new ArrayList<User>();

		try {
			// Execute the query
			Model model = new Model();
			model.open();
			model.query("SELECT e.* FROM \"enrollment\" e RIGHT JOIN \"user\" u ON e.user_id = u.id WHERE e.subscription_id = ?").setInt(1, id);
			model.result();

			// Loop over all results
			while (model.result.next()) {
				User user = new User();
				user.readUser( model.result.getInt("user_id"));
				users.add( user );
			}

		} catch (Exception ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}

		return users;
	}

	/**
	 * This method reads information about a enrollment.
	 * @param id the identifier of the enrollment
	 * @return the result of the query
	 */
	public Enrollment readEnrollmentById(int id) {
		try {
			this.open();
			this.query("SELECT * FROM \"enrollment\" e LEFT JOIN \"user\" u ON e.user_id = u.id LEFT JOIN \"subscription\" s ON e.subscription_id = s.id WHERE e.id = ? LIMIT 1").setInt(1, id);
			this.result();
			this.result.first();

			this.setPropertiesFromResult();

		} catch (Exception ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}

		return this;
	}
	
	/**
	 * This method reads information about a enrollment by the subscription identifier
	 * and user identifier.
	 * @param id identifier of a subscription
	 * @param user_id identifier of a user
	 * @return returns the result of the query.
	 */
	public Enrollment readEnrollmentBySubscriptionIdAndUserId(int id, int user_id) {
		try {
			//Execute the query
			this.open();
			PreparedStatement query = this.query("SELECT * FROM \"enrollment\" WHERE subscription_id = ? AND user_id = ? LIMIT 1");
			query.setInt(1, id);
			query.setInt(2, user_id);
			this.result();
			this.result.first();

			if ( this.result.getRow() == 0 ) {
				this.enrolled = false;
			} else {
				this.enrolled = true;
			}

		} catch (Exception ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}

		return this;
	}
	
	/**
	 * This method is used to see what montly subscription is the latest
	 * a user has registered
	 * 
	 * @param user_id the id of the user
	 * @return the result of the query
	 */
	public Enrollment readMonthlyEnrollmentByUserId(int user_id) {
		try {
			//Execute the query
			this.open();
			PreparedStatement query = this.query("SELECT s.title "
					+ "FROM \"enrollment\" e, subscription s"
					+ "WHERE user_id = ? AND s.monthly = true AND e.subscription_id = s.id "
					+ "AND e.id = (SELECT MAX(e.id) FROM \"enrollment\" e WHERE user_id = ?)");
			query.setInt(1, user_id);
			query.setInt(2, user_id);
			
			this.result();
			this.result.first();

			if ( this.result.getRow() == 0 ) {
				this.enrolled = false;
			} else {
				this.enrolled = true;
			}

		} catch (Exception ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}

		return this;
	}
	
	/**
	 * This method is used to subscribe a user to an enrollment
	 * @param id identifier of a subscription
	 * @param user_id identifier of a user
	 */
	public void subscribe( int id, int user_id ) {

		try {
			//execute the query
			this.open();
			PreparedStatement query = this.query("INSERT INTO \"enrollment\" (user_id, subscription_id, datetime, branch_id) VALUES (?, ?, NOW(), ?)");
			query.setInt( 1, user_id );
			query.setInt( 2, id );
			query.setInt( 3, Settings.getInt("branch") );
			this.execute();

		} catch (Exception ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}

	/**
	 * This method unsubscribes a user from an enrollement
	 * @param id identifier of the subscription
	 * @param user_id identifier of user
	 */
	public void unsubscribe( int id, int user_id ) {

		try {
			//execute the query
			this.open();
			PreparedStatement query = this.query("DELETE FROM \"enrollment\" WHERE user_id = ? AND subscription_id = ?");
			query.setInt( 1, user_id );
			query.setInt( 2, id );
			this.execute();

		} catch (Exception ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}
	
	/**
	 * This method sets the  properties from a query result
	 * @return true if all goes well, false when this method fails
	 */
	protected boolean setPropertiesFromResult() {

		try {

			// Check if there is a result
			if (this.result.getRow() == 0) {

				// There is no result, so return without doing anything
				return false;
			}

			// Fill in all properties
			this.setId(this.result.getInt("id"));
			this.setUser_id(this.result.getInt("user_id"));
			this.setSubscription_id(this.result.getInt("subscription_id"));
			this.setDatetime(this.result.getTimestamp("datetime"));
			this.setBranch_id(this.result.getInt("branch_id"));		
			
			this.setBranch(new Branch(this.getBranch_id()));
			this.setSubscription(new Subscription(this.getSubscription_id()));

		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
			return false;
		}

		return true;
	}

	/**
	 * @author daan
	 * @return returns the table row objects
	 */
	public Object[] getTableRowObjects() {

		String fomatDatetime = null;
		
		if(this.datetime != null){
			Datetime enrollmentDatetime = new Datetime(this.datetime);
			fomatDatetime = enrollmentDatetime.format("dd-MM-yyyy HH:mm");
		}
		
		return new Object[] {
			fomatDatetime,
			this.subscription.getTitle()
			};
	}
	
	/**
	 * This method overrides to string for combobox
	 * @return the title of a subscription subscription
	 */
	@Override
	public String toString() {
		return subscription.getTitle();
    }
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**

	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the subscription_id
	 */
	public int getSubscription_id() {
		return subscription_id;
	}

	/**
	 * @param subscription_id the subscription_id to set
	 */
	public void setSubscription_id(int subscription_id) {
		this.subscription_id = subscription_id;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getDatetime() {
		return datetime;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the subscription
	 */
	public Subscription getSubscription() {
		return subscription;
	}

	/**
	 * @param subscription the subscription to set
	 */
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * @param Branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	/**
	 * Gets the branch identifier
	 * @return the branch identifier
	 */
	public int getBranch_id() {
		return branch_id;
	}
	/**
	 * 
	 * @param branch_id is the identfier of a branch
	 */
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	
	/**
	 * 
	 * @return true if someone is enrolled, false if someone isn't
	 */
	public boolean isEnrolled() {
		return this.enrolled;
	}
}
