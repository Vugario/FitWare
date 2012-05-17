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
	private long unixTimestamp;
	private Timestamp timestamp;
	private Date parsedDate;

	
	public Datetime(){
		
		this.parsedDate = new Date();
		this.unixTimestamp = parsedDate.getTime();
	}
	
	public Datetime(String dateString){
	
		this.timestamp = Timestamp.valueOf(dateString);

		this.unixTimestamp = timestamp.getTime();
		
	}
	
	public Datetime(long time){
		this.unixTimestamp = time;
	}
	
	public Datetime(Timestamp time){
		this.unixTimestamp = time.getTime();
	}
		
	public String format(String format){
		
		dateFormat = new SimpleDateFormat(format);

		//Timestamp ts2 = Timestamp.valueOf(this.dateString);
		parsedDate = new Date(unixTimestamp);

		TimeZone tZ = TimeZone.getTimeZone("Europe/Amsterdam");

		dateFormat.setTimeZone(tZ);
		String diff = dateFormat.format(parsedDate);
		//timestamp = parsedDate.getTime();
		System.out.println(diff);

		
		return diff;
	}
	
	public Timestamp timestamp(){
		
		return this.timestamp;
		
	}
	
	
	
}

