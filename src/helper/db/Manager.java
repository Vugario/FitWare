package helper.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;



public class Manager {
	
	private Connection dbConnection;
	private ResultSet result;
	private PreparedStatement dbQuery;
	
	
	public Map<String, String> modelDefenition = new HashMap<String, String>();
	
	/**
	 * This method is used to chain after executing query method
	 * @see This is maybe to much to return and should think about using what we need in loose methods
	 * @return 
	 */
	public ResultSet result(){
		try {
			result = dbQuery.executeQuery();
				
			try {
				
				while(result.next()){
					//System.out.println("lol");
					setData(result);
				}
				
			} catch (SQLException ex) {
				Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		}
		

		//System.out.println(this.data.toString());
		return result;
	};
	
	/**
	 * construct opens connection due to there always should be a connection
	 */
	public  Manager()
	{
		
		try {
			
			//initiate driver, The driver nees to be downloaded and included in the librarys
			Class.forName("org.postgresql.Driver");
			
			dbConnection = DriverManager.getConnection("jdbc:postgresql://allen.pb/fitshape", "postgres", "root");
			
		} catch (ClassNotFoundException ex) {
			Logger.getLogger("LET OP mocht je dit zien en je krijgt hierna een null dan moet je de driver installeren: "+Manager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * execute querys without a result
	 * @param query UPDATE, INSERT, CREATE
	 */
	public void execute(String query)
	{	
		
		try {
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(query);
			//System.out.println(result);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		
	}
	
	public PreparedStatement query(String query)
	{	
		
		try {
			Statement stmt = dbConnection.createStatement();
			dbQuery = dbConnection.prepareStatement(query);
			//System.out.println(result);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return dbQuery;
	}
	
	 /**
     * remember to close the connection when finished
     */
    public void close() {
        try {
            dbConnection.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }
	
	
	public void setData(ResultSet result)
	{
		for(String key : this.modelDefenition.keySet()){
			try {
				Data data = (Data)result.getObject(key);
			} catch (SQLException ex) {
				Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
			}
			System.out.println(data);
			try {
				System.out.println(result.getObject(key));
			} catch (SQLException ex) {
				Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
			}
			
			/*if("numeric".equals(this.modelDefenition.get(key))){
				try {

					this.data.add(result.getInt(key));
					
				} catch (SQLException ex) {
					Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			if("string".equals(this.modelDefenition.get(key))){
				try {
					this.data.add(result.getString(key));
					
				} catch (SQLException ex) {
					Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
				}
			}*/
		}
	}
	
}
