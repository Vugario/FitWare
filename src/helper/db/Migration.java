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
		String path = System.getProperty(null);
		//System.out.println(path);
		path+="/src/model/";
		
		migrate(new File(path));
	}

	/**
	 * 
	 * @param dir this is the path to the models
	 */	
	private static void migrate(File dir)
	{
			
		if(dir.isFile()){
			
			String filename = dir.toString();
			
			int extPos = filename.lastIndexOf(".");
			
			if(extPos >= 0){
				if(".sql".equals(filename.substring(extPos))){
					
					try{
						BufferedReader file = new BufferedReader(new FileReader(dir));
						
						String queryStr;
						StringBuilder str = new StringBuilder();
						
						while((queryStr = file.readLine()) != null){
							str.append((queryStr+"\n "));
						}
						
						
						Manager db = new Manager();
						
						db.query(queryStr);
						
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
