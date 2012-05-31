package model;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import helper.db.*;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import main.Application;
import view.popups.NotificationPopup;

/**
 *
 * @author mennowolvers
 */
public class Subscription extends Model {

	private int branchId;
	private String title;
	private String description;
	private boolean gender;
	private int maximumAge;
	private int minimumAge;

	
	private boolean monthly;
	private double price;
	private Timestamp startDate;
	private Timestamp endDate;

	public Subscription() {
	}

	public Subscription(ResultSet result) {
		super();

		this.result = result;
		this.setPropertiesFromResult();
	}

	public Subscription(int id) {
		super();

		try {
			this.open();
			this.query("SELECT * FROM \"subscription\" WHERE id = ? LIMIT 1").setInt(1, id);
			this.result();
			this.result.first();

			this.setPropertiesFromResult();

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static ArrayList<Subscription> readAll() {

		ArrayList<Subscription> items = new ArrayList<Subscription>();

		try {
			// Execute the query
			Model model = new Model();
			model.open();
			model.query("SELECT * FROM subscription");
			model.result();

			// Loop over all results
			while (model.result.next()) {
				items.add(new Subscription(model.result));
			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return items;

	}
	
	public void create(){
		try {
			this.open();
			PreparedStatement query =  this.query("INSERT INTO subscription"
					+ "(title, description, \"minimumAge\", "
					+ "\"maximumAge\", price, monthly, \"branchId\", gender, "
					+ "startdate, enddate)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
					);
								
					query.setString(1, title);
					query.setString(2, description);					
					query.setInt(3, minimumAge);
					query.setInt(4, maximumAge);
					query.setDouble(5, price);
					query.setBoolean(6, monthly);
					query.setInt(7, 1);
					query.setBoolean(8, gender);
					query.setTimestamp(9, startDate);
					query.setTimestamp(10, endDate);
					
					Application.getInstance().showPopup(new NotificationPopup("De cursus is toegevoegd."));
		} catch (SQLException ex) {
			
			Logger.getLogger(Subscription.class.getName()).log(Level.SEVERE, null, ex);
			Application.getInstance().showPopup(new NotificationPopup("Er is iets mis gegaan. \n"
					+ "Probeer het nogmaals."));
		}finally {
			this.execute();
		}
		
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
			this.setBranchId(this.result.getInt("branchid"));
			this.setTitle(this.result.getString("title"));
			this.setDescription(this.result.getString("description"));
			this.setGender(this.result.getBoolean("gender"));
			this.setMaximumAge(this.result.getInt("maximumage"));
			this.setMinimumAge(this.result.getInt("minimumage"));
			this.setMonthly(this.result.getBoolean("monthly"));
			this.setPrice(this.result.getDouble("price"));


		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the gender
	 */
	public boolean isGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(boolean gender) {
		this.gender = gender;
	}

	/**
	 * @return the maxAge
	 */
	public int getMaximumAge() {
		return maximumAge;
	}

	/**
	 * @param maxAge the maxAge to set
	 */
	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}

	/**
	 * @return the minAge
	 */
	public int getMinimumAge() {
		return minimumAge;
	}

	/**
	 * @param minAge the minAge to set
	 */
	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}

	/**
	 * @return the monthly
	 */
	public boolean isMonthly() {
		return monthly;
	}

	/**
	 * @param monthly the monthly to set
	 */
	public void setMonthly(boolean monthly) {
		this.monthly = monthly;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the endDate 
	 */
	public Timestamp getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the startDate
	 */
	public Timestamp getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate startDate to set
	 */

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	
}
