/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.db;


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
	 * @param id
	 * @return 
	 */
	public int read(int id){
		return 1;
	}

	/**
	 * Read result by value
	 * @param column database column from a table
	 * @param value value in database column
	 * @return 
	 */
	public int read(String column, String value){
		//this.resultSet().
		return 1;
	}
	
	/**
	 * Read result by value
	 * @param column
	 * @param value
	 * @return 
	 */
	public int read(String column, int value){
		return 1;
	}

	/**
	 * Read result by datetime value, this functions also formats to correct format
	 * @param column database datetime column from a table
	 * @param datetime value in database column
	 * @return 
	 */
	/*public int read(String column, Date value){
		return 1;
	}*/
	
}
