package model;

import helper.ExceptionHandler;
import helper.db.Model;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


/**
 *
 * @author allentje
 */
public class Attendance extends Model {
	/**
	 * int enrollmentId is the identifier for the enrollment
	 */
	int enrollmentId;
	/**
	 * Timestamp datetime is the date and the time
	 */
	Timestamp datetime;

	/**
	 * This method is the default constructor
	 */
	public Attendance(){
		
	}
	
	/*
	 * This method creates an attendance
	 */
	public boolean create() {
		//create the attendance via the query
		try {
			this.open();
			PreparedStatement query = this.query("INSERT INTO \"attendance\" (enrollment_id, datetime) VALUES (?, NOW())");
			query.setInt( 1, enrollmentId);
			this.execute();

		} catch (Exception ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
			return false;
		}
		
		return true;
	} 
	/**
	 * 
	 * @return the date and the time
	 */
	public Timestamp getDatetime() {
		return datetime;
	}
	/**
	 * 
	 * @param datetime is the date and the time
	 */
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	
	/**
	 * 
	 * @return the enrollment identifier
	 */
	public int getEnrollmentId() {
		return enrollmentId;
	}
	
	/**
	 * 
	 * @param enrollmentId is the enrollment identifier
	 */
	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

}
