/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author allentje
 */
public class Model extends Manager{
	
	public String table;
	
	public Model()
	{
		this.table = this.getClass().getSimpleName().toLowerCase();
		System.out.println(table);
	}
	
	/**
	 * Read result by id
	 * @todo make query dynamic
	 * @param id
	 * @return 
	 */
	public int read(int id){
		
		try {
			query("SELECT * FROM ? WHERE id = ?").setString(1, this.table);
			ResultSet result = result();
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			System.out.println(result.getStatement());
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 1;
	}

}
