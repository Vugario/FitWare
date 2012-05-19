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

	protected void setPropertiesFromResult() {

		try {

			// Check if there is a result
			if (this.result.getRow() == 0) {

				// There is no result, so return without doing anything
				return;
			}

			// Fill in all properties
			this.id = this.result.getInt("id");
			this.user_id = this.result.getInt("user_id");
			this.subscription_id = this.result.getInt("subscription_id");

		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
