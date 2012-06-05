
package helper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * This class is made to work with dates more easily.
 * 
 * @author allentje
 */
public class Datetime{
	/**
	 * String dateString is the String with a date in it
	 */
	private String dateString;
	/**
	 * SimpleDateFormat dateFormat is a simple date
	 */
	private SimpleDateFormat dateFormat;
	/**
	 * long unixTimestamp is the long that contains the Unix timestamp
	 */
	private long unixTimestamp;
	/**
	 * Timestamp timestamp is a timestamp
	 */
	private Timestamp timestamp;
	/**
	 * Date parsedDate is a date that has been parsed to a Unix timestamp
	 */
	private Date parsedDate;

	/**
	 * This method is the Datetime constructor
	 */
	public Datetime(){
		
		this.parsedDate = new Date();
		this.unixTimestamp = parsedDate.getTime();
	}
	
	/**
	 * This method is the constructor for Datetime with a datetim in it
	 * @param dateString is the String that contains a date
	 */
	public Datetime(String dateString){
	
		this.timestamp = Timestamp.valueOf(dateString);
		
		this.unixTimestamp = timestamp.getTime();
		
	}
	
	/**
	 * This method is the constructor for Datetime with a time in it.
	 * @param time the time
	 */
	public Datetime(long time){
		this.unixTimestamp = time;
	}
	
	/**
	 * This method is the constructor for Datetime with a timestamp in it
	 * @param time the timestamp
	 */
	public Datetime(Timestamp time){
		this.unixTimestamp = time.getTime();
	}
	
	/**
	 * This method formats a date and puts it in a String
	 * @param format is the string (a date) to be formatted
	 * @return the String diff that contains a formatted date
	 */
	public String format(String format){
		
		dateFormat = new SimpleDateFormat(format);

		//Timestamp ts2 = Timestamp.valueOf(this.dateString);
		parsedDate = new Date(unixTimestamp);

		TimeZone tZ = TimeZone.getTimeZone("Europe/Amsterdam");

		dateFormat.setTimeZone(tZ);
		String diff = dateFormat.format(parsedDate);

		
		return diff;
	}
	
	/**
	 * This method returns the timestamp that is made
	 * @return timestamp
	 */
	public Timestamp timestamp(){
		
		return this.timestamp;
		
	}
	
	
	
}

