/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.db;

import helper.Annotation.Table;
import helper.Annotation.Column;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;



/**
 *
 * @author allentje
 */
public class Model extends Manager{
	
	public String tables;
		
	public Model()
	{

		helper.AnnotationReader.persist(new User(true, 1));
		
		//System.out.println(table.name());
		
		/*Annotation[] field = User.class.getAnnotations();

		for(Annotation lol : field){
			System.out.println(lol.);
		}
		
		System.out.println(field);
		*/
		this.tables = this.getClass().getSimpleName().toLowerCase();
		//System.out.println(tables);
		
		open();
	}


	
	public static void persist( Object o )
  {
    try
    {
      // Get the table annotation
      Class tableClass = Class.forName( "helper.Annotation.table" );
      Table table = ( Table )o.getClass().getAnnotation( tableClass );

      if( table == null )
      {
        // This object doesn’t support the table annotation
        System.out.println( "I cannot persist this object, it does not support the @Table annotation" );
        return;
      }

      // Read the table name
      System.out.println( "Table name: " + table.name() );

      // Access the column class so that we can extract it from our get methods
      Class columnClass = Class.forName( "helper.Annotation.Column" );

      // Read the object’s columns, which are assigned to the get methods
      Method[] methods = o.getClass().getMethods();
      for( int i=0; i<methods.length; i++ )
      {
        String methodName = methods[ i ].getName();
        if( methodName.startsWith( "get" ) || methodName.startsWith( "is" ) )
        {
          // Skip over "getClass()"
          if( !methodName.equals( "getClass" ) )
          {
            Column column = ( Column )methods[ i ].getAnnotation( columnClass );
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
              }
              else
              {
                columnName = Character.toLowerCase( methodName.charAt( 2 ) ) +
                       methodName.substring( 3 );
              }
            }

            // Read the value
            Object res = methods[ i ].invoke( o, new Object[] {} );
            System.out.println( "\t" + columnName + " = " + res );
          }
        }
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }

	/**
	 * Read result by id
	 * @todo make query dynamic
	 * @param id
	 * @return 
	 */
	/*public ResultSet read(int id) throws Exception {
		
		PreparedStatement db = query("SELECT * FROM \""+table+"\" WHERE id = ?");
		db.setInt(1, id);
		db.setMaxRows(1);
		
		System.out.println(this.getClass().getMethods().toString());
		
		ResultSet result;
		
		result().getR
		
		while(result().next()){
			
		}
		
		//return result.first();
		return result;
		
	}*/

}
