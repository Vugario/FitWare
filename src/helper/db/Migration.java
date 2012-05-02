/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.db;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import helper.db.Manager;
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
		String path = System.getProperty("user.dir");
		//System.out.println(path);
		path += File.separator+"src"+File.separator+"model"+File.separator;
		//System.out.println(path);
		migrate(new File(path));
	}

	/**
	 * This method loops through model directory and reads .sql files ready to insert into database
	 * @see connection to db need to be tested
	 * @param dir this is the path to the models
	 */	
	private static void migrate(File dir)
	{
		//System.out.println(dir.getAbsolutePath());		
		if(dir.isFile()){
			
			String filename = dir.toString();
			
			int extPos = filename.lastIndexOf(".");
			
			if(extPos >= 0){
				
				if(".sql".equals(filename.substring(extPos))){
					//System.out.println(dir.getName());
					try{
						BufferedReader file = new BufferedReader(new FileReader(dir));
						
						String queryStr;
						StringBuilder str = new StringBuilder();
						
						while((queryStr = file.readLine()) != null){
							str.append((queryStr+"\n "));
						}
						
						
						Manager db = new Manager();
						
						db.query(str.toString());
						//System.out.println(str);
						file.close();

					}catch(Exception e){
						System.out.println(e.getMessage());
						
					}
					
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
