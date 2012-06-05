
package helper.db;

import helper.ExceptionHandler;
import java.sql.SQLException;


/**
 * This class is used to make a model to reduce the work to make a query
 * @author allentje
 */
public class Model extends Manager{
	/**
	 * Int id is the identifier for something (can be an identifier for every table)
	 */
	protected int id;
	/**
	 * String table is the string with a tablename.
	 */
	protected String table;
	
	/**
	 * The default constructor
	 */
	public Model()
	{
		this.table = this.getClass().getSimpleName().toLowerCase();
	}
	
	/**
	 * Read result by id
	 * @todo make query dynamic
	 * @param id
	 * @return 
	 */
	public int read(int id){
		
		
		try {
			this.query("SELECT * FROM update WHERE ?").setBoolean(1, true);
		} catch (SQLException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
		
		this.result();
		
		return 1;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){

		return this.id;
	}
}
