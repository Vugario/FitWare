/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.db;

import helper.ExceptionHandler;
import java.sql.SQLException;


/**
 *
 * @author allentje
 */
public class Model extends Manager{
	
	protected int id;
	protected String table;
	
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
