/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import helper.db.*;

/**
 *
 * @author allentje
 */
public class Subscription extends Model {

	String name;
	int id;
	int newAttr;
	double price;
	int minimumAge;
	int maximumAge;
	boolean monthly;
	boolean subscriptionType;

	public Subscription readSubscription() {
		try {
			this.open();

			PreparedStatement query = this.query("SELECT * FROM \"subscription\" WHERE id = ? ").setInt(1, id);
			this.result();

			this.setPropertiesFromResult();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return this;

	}

	public Boolean getSubscriptionType() {
		return subscriptionType;
	}

	public void getSubscriptionType(Boolean subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
}
