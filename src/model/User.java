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
		this.query("SELECT * FROM \"user\"");
		this.result();
		
	}
	
	
}


