/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import helper.Annotation.Table;
import helper.Annotation.Column;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author allentje
 */
public class AnnotationReader {
	
	public static void persist( Object o )
	{
		
		try{
			
			//Class tableClass = Class.forName( "helper.Annotation.Table" );
			Table table = (Table) o.getClass().getAnnotation(Table.class);
			
			Method[] methods = o.getClass().getDeclaredMethods();
			
			for( int i=0; i<methods.length; i++ )
			{
				String methodName = methods[ i ].getName();
				//System.out.println(methodName);
				if( methodName.startsWith( "get" ) || methodName.startsWith( "is" ) )
				{
					//System.out.println(methodName);
					if( !methodName.equals( "getClass" ) )
					{
						Column column = ( Column )methods[ i ].getAnnotation( Column.class );
						//System.out.println(column);
						String columnName = null;
						if( column != null )
						{
						  columnName = column.value();
						  //System.out.println( "\t" + column.value() );
						}
						else
						{
						  // Derive the column name from the method name...
						  if( methodName.startsWith( "get" ) )
						  {
							columnName = Character.toLowerCase( methodName.charAt( 3 ) ) +
								   methodName.substring( 4 );
							  //System.out.println(columnName);
						  }
						  else
						  {
							columnName = Character.toLowerCase( methodName.charAt( 2 ) ) +
								   methodName.substring( 3 );
						  }
						}
						String type = methods[i].getReturnType().getSimpleName();
						//System.out.println(methods[i].invoke(o.getClass(), new Object[] {}));
						// Read the value
						//Object res = methods[ i ].invoke( o.getClass(), new Object[] {} );
						//System.out.println( "\t" + columnName + " = " + res );
					}
				}
			}
		}catch(Exception e){
			e.getMessage();
		}

	}	
}
