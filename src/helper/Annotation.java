/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author allentje
 */
public @interface Annotation {
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD, ElementType.FIELD})
	public @interface Column {
	  String value();
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Table {

		public String name();
		
	}
	
}
