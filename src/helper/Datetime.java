/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author allentje
 */
public class Datetime{
	
	private String dateString;
	private SimpleDateFormat dateFormat;
	private long timestamp;
	private Date parsedDate;

	
	public Datetime(){
		
		this.parsedDate = new Date();
		this.timestamp = parsedDate.getTime();
	}
	
	public Datetime(String dateString){
	
		Timestamp ts2 = Timestamp.valueOf(dateString);
		this.timestamp = ts2.getTime();
		
	}
	
	public Datetime(long time){
		this.timestamp = time;
	}
	
	public Datetime(Timestamp time){
		this.timestamp = time.getTime();
	}
	
	
	public String format(String format){
		dateFormat = new SimpleDateFormat(format);
		System.out.println(dateString);
		try {
			dateFormat.parse(this.dateString);
			
		} catch (ParseException ex) {
			Logger.getLogger(Datetime.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		TimeZone tZ = TimeZone.getTimeZone("Europe/Amsterdam");

		dateFormat.setTimeZone(tZ);
		String diff = dateFormat.format(parsedDate);

		
		return diff;
	}

	
	public String format2(String format){
		dateFormat = new SimpleDateFormat(format);

		//Timestamp ts2 = Timestamp.valueOf(this.dateString);
		parsedDate = new Date(timestamp);

		TimeZone tZ = TimeZone.getTimeZone("Europe/Amsterdam");

		dateFormat.setTimeZone(tZ);
		String diff = dateFormat.format(parsedDate);
		//timestamp = parsedDate.getTime();
		System.out.println(diff);

		
		return diff;
	}
	
	
	
}

