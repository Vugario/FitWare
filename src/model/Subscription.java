package model;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import helper.db.*;
import java.sql.*;
import java.util.ArrayList;
import main.Application;
import view.popups.NotificationPopup;

/**
 *
 * @author mennowolvers
 */
public class Subscription extends Model {

	private int id;
	private int branchId;
	private String title;
	private String description;
	private char gender;
	private int maximumAge;
	private int minimumAge;
	private char type;

	
	private boolean monthly;
	private double price;
	private Date startDate;
	private Date endDate;
	private Time startTime;
	private Time endTime;

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
					+ "startdate, enddate, starttime, endtime, \"type\")"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
					);
								
					query.setString(1, this.getTitle() );
					query.setString(2, this.getDescription() );					
					query.setInt(3, this.getMinimumAge() );
					query.setInt(4, this.getMaximumAge() );
					query.setDouble(5, this.getPrice() );
					query.setBoolean(6, this.getMonthly() );
					query.setInt(7, 1);
					query.setString(8, String.valueOf( this.getGender() ) );
					query.setDate(9, this.getStartDate() );
					query.setDate(10, this.getEndDate() );
					query.setTime(11, this.getStartTime() );
					query.setTime(12, this.getEndTime() );
					query.setString(13, String.valueOf( this.getType() ) );
					
					Application.getInstance().showPopup(new NotificationPopup("De cursus is toegevoegd."));
		} catch (SQLException ex) {
			
			Logger.getLogger(Subscription.class.getName()).log(Level.SEVERE, null, ex);
			Application.getInstance().showPopup(new NotificationPopup("Er is iets mis gegaan. \n"
					+ "Probeer het nogmaals."));
		}finally {
			this.execute();
			this.close();
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
			this.setGender(this.getChar( this.result.getString("gender") ));
			this.setType(this.getChar( this.result.getString("type") ));
			this.setMaximumAge(this.result.getInt("maximumage"));
			this.setMinimumAge(this.result.getInt("minimumage"));
			this.setMonthly(this.result.getBoolean("monthly"));
			this.setPrice(this.result.getDouble("price"));
			this.setStartDate(this.result.getDate("startDate"));
			this.setEndDate(this.result.getDate("endDate"));
			this.setStartTime(this.result.getTime("startTime"));
			this.setEndTime(this.result.getTime("endTime"));


		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public char getChar( String string ) {
		if( string == null)
			return '0';
		
		if( string.isEmpty() )
			return '0';
			
		char[] array = string.toCharArray();
		char response = '0';
		
		if( array.length > 0 )
		{
			for( char c : array ) {
				response = c;
			}
		}
		
		return response;
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
	public char getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(char gender) {
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
	public boolean getMonthly() {
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
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate startDate to set
	 */

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(char type) {
		this.type = type;
	}

	/**
	 * @return the startTime
	 */
	public Time getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Time getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
}
