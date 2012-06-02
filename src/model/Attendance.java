/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.db.Model;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import main.ExceptionHandler;

/**
 *
 * @author allentje
 */
public class Attendance extends Model {

	int enrollmentId;
	Timestamp datetime;

	public Attendance(){
		
	}
	
	public boolean create() {

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
	
	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

}
