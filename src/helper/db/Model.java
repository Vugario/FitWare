/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.db;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author allentje
 */
public class Model extends Manager{
	
	public int id;
	public String table;
	
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
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
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
