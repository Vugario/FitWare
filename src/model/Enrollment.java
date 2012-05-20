/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.db.Model;
import helper.Datetime;
import java.sql.Timestamp;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.text.DateFormat;

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

	public Enrollment() {
	}

	public Enrollment readEnrollmentByUserId(int id) {
		try {
			this.open();
			this.query("SELECT * FROM \"enrollment\" e LEFT JOIN \"user\" u ON e.user_id = u.id LEFT JOIN \"subscription\" s ON e.subscription_id = s.id WHERE user_id = ? LIMIT 1").setInt(1, id);
			this.result();
			this.result.first();

			this.setPropertiesFromResult();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this;
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

	protected void setPropertiesFromResult() {

		try {

			// Check if there is a result
			if (this.result.getRow() == 0) {

				// There is no result, so return without doing anything
				return;
			}

			// Fill in all properties
			this.setId(this.result.getInt("id"));
			this.setUser_id(this.result.getInt("user_id"));
			this.setSubscription_id(this.result.getInt("subscription_id"));
                        
                        this.setSubscription( new Subscription( this.getSubscription_id() ) );

		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
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
}
