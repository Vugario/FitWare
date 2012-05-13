/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import helper.Annotation.Column;
import helper.Annotation.Table;
import java.lang.reflect.Method;

/**
 *
 * @author allentje
 */
public class AnnotationReader{
	
	public static void persist( Object o )
	{
		System.out.println(o.toString());
		try{
			
			//Class tableClass = Class.forName( "helper.Annotation.Table" );
			Table table = (Table) o.getClass().getAnnotation(Table.class);
			
			Method[] methods = o.getClass().getDeclaredMethods();
			
			for( int i=0; i<methods.length; i++ )
			{
				String methodName = methods[ i ].getName();
				
				if( methodName.startsWith( "get" ) || methodName.startsWith( "is" ) )
				{
					
					if( !methodName.equals( "getClass" ) )
					{
						Column column = ( Column )methods[ i ].getAnnotation( Column.class );
						
						String columnName = null;
						if( column != null )
						{
						  columnName = column.value();

						}
						else
						{
						  // Derive the column name from the method name...
						  if( methodName.startsWith( "get" ) )
						  {
							columnName = Character.toLowerCase( methodName.charAt( 3 ) ) +
							methodName.substring( 4 );
						  }
						  else
						  {
							columnName = Character.toLowerCase( methodName.charAt( 2 ) ) +
								   methodName.substring( 3 );
						  }
						}
						String type = methods[i].getReturnType().getSimpleName();

						// Read the value
						Object res = methods[ i ].invoke( o, new Object[] {} );

						
						System.out.println( "\t" + columnName + " = " + res );
					}
				}
			}
		}catch(Exception e){
			e.getMessage();
		}

	}
	
	public void getColumns(){
		
	};
	
	public void getTable(){
		
	};
}
