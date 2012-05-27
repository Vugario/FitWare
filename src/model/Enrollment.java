package model;

import helper.db.Model;
import java.sql.Timestamp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mennowolvers
 */
public class Enrollment extends Model {

	private int id;
	private int user_id;
	private int subscription_id;
	private Timestamp timestamp;
	private User user;
	private Subscription subscription;
	private boolean enrolled;

	public Enrollment() {
	}

	public Enrollment(ResultSet result) {
		super();

		this.result = result;
		this.setPropertiesFromResult();
	}

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
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return enrollments;
	}

	public Enrollment readEnrollmentById(int id) {
		try {
			this.open();
			this.query("SELECT * FROM \"enrollment\" e LEFT JOIN \"user\" u ON e.user_id = u.id LEFT JOIN \"subscription\" s ON e.subscription_id = s.id WHERE e.id = ? LIMIT 1").setInt(1, id);
			this.result();
			this.result.first();

			this.setPropertiesFromResult();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;
	}

	public Enrollment readEnrollmentBySubscriptionIdAndUserId(int id, int user_id) {
		try {
			System.out.println("subscription_id = " + id + " AND user_id = " + user_id );
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
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;
	}

	public void subscribe() {

		try {
			this.open();
			PreparedStatement query = this.query("INSERT INTO \"enrollment\" (user_id, subscription_id, datetime, branch_id) VALUES (?, ?, NOW(), ?)");
			query.setInt(1, 2);
			query.setInt(2, 3);
			query.setInt(3, 2);
			this.result();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

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

			this.setSubscription(new Subscription(this.getSubscription_id()));

		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}

		return true;
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
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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

	public boolean isEnrolled() {
		return this.enrolled;
	}
}
