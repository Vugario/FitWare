/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.db;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import helper.db.Manager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.lang.StringBuffer;

/**
 *
 * @author allentje
 */
public class Migration {
	
	/**
	 * This runs the migrate script based on unix systems
	 */
	public static void run()
	{
		
		Manager db = new Manager();
		
		
		db.execute(
			"CREATE TABLE IF NOT EXISTS "
			+ "update("
			+ "		id serial NOT NULL, model character varying(60), version smallint, CONSTRAINT id PRIMARY KEY (id )"
			+ ")WITH("
			+ "		OIDS=FALSE"
			+ ")");

			String path = System.getProperty("user.dir");
			
			path += File.separator+"src"+File.separator+"model"+File.separator;
			
			migrate(new File(path));

	}

	/**
	 * This method loops through model directory and reads .sql files ready to insert into database
	 * @see connection to db need to be tested
	 * @param dir this is the path to the models
	 */	
	private static void migrate(File dir)
	{
		
		Manager db = new Manager();
		
		//System.out.println(dir.getAbsolutePath());		
		if(dir.isFile()){
			
			String filename = dir.toString();
			
			int extPos = filename.lastIndexOf(".");
			
			if(extPos >= 0){
				
				if(".sql".equals(filename.substring(extPos))){

					try {
						//check if we need to read file or skip to the next
						db.query("SELECT * FROM update WHERE model = ?")
								.setString(1, dir.getParentFile().getName());
					} catch (SQLException ex) {
						Logger.getLogger(Migration.class.getName()).log(Level.SEVERE, null, ex);
					}
					
					db.result();

					
					
					
					/*try{
						BufferedReader file = new BufferedReader(new FileReader(dir));
						
						String queryStr;
						StringBuilder str = new StringBuilder();
						
						while((queryStr = file.readLine()) != null){
							str.append((queryStr+"\n "));
						}
						
						
						db.execute(str.toString());
						//System.out.println(str);
						file.close();

					}catch(Exception e){
						System.out.println(e.getMessage());
						
					}*/
					
				};	
			}
			

		}else{

			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				//System.out.println(children[i]);
				migrate(new File(dir, children[i]));
			}
		}

	}
	
}
