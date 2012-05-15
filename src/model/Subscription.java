/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import helper.db.*;

/**
 *
 * @author allentje
 */
public class Subscription extends Model {
	String category;
	String title;
	String description;
	boolean gender;
	int maxAge;
	int minAge;
	boolean monthly;
	double price;

}
