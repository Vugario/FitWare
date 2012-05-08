/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import helper.db.Model;


/**
 *
 * @author allentje
 */
public class User extends Model{
	
	public User()
	{
		this.modelDefenition.put("version", "numeric");
		this.modelDefenition.put("model", "string");
		/*this.modelDefenition.put("firstname", "string");
		this.modelDefenition.put("lastname", "string");
		this.modelDefenition.put("age", "numeric");*/
		
		/*System.out.println(model.keySet());
		
		for(String key : model.keySet()){
			System.out.println(key+" = "+model.get(key));
		}*/
		
	}
	
	
}


