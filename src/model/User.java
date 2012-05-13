/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.Annotation.*;
import helper.db.Model;


/**
 *
 * @author allentje
 */
@Table(name = "user")
public class User extends Model{
	
	boolean active;
	int bankaccount;

	
	public User() {}
	
	public User(boolean active, int bankaccount) {
		
		this.active = active;
		this.bankaccount = bankaccount;
		
	}
	
	
	@Column(value="actieve")
	public boolean isActive() {
		
		return active;
		
	}

	@Column(value="bankaccouaant")
	public int getBankaccount() {
		return bankaccount;
	}

}

