package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class can handle the exceptions that may be thrown.
 * 
 * This class makes use of the multiton design pattern. You can read more about
 * this at: http://en.wikipedia.org/wiki/Multiton_pattern#Java
 * 
 * @todo I'm not sure what this class will do with the exceptions. Probably
 *       something like displaying it to the user and logging it.
 * 
 * @author Daan
 */
public class ExceptionHandler {

	public static final int TYPE_SYSTEM_ERROR = 0;
	public static final int TYPE_USER_ERROR = 1;
	
	/**
	 * A static map containing all instances of this class
	 */
	protected static Map<Integer, ExceptionHandler> instances = new HashMap<Integer, ExceptionHandler>();
	
	/**
	 * A list with exceptions added to this type
	 */
	protected ArrayList<Exception> exceptions = new ArrayList<Exception>();

	/**
	 * Get an instance of ExceptionHandler of the requested type
	 * 
	 * @param key The key by which the instance is identified
	 * @return The instance of ExceptionHandler of the requested type
	 */
	public static ExceptionHandler getInstance(int key) {

		// We use synchronized to prevent raceconditions.
		// These raceconditions can occure in multithreaded programs.
		synchronized (instances) {

			// Try to get the ExceptionHandler of the requested type
			ExceptionHandler instance = instances.get(key);

			// Check if we could get an ExceptionHandler
			if (instance == null) {
				// We couldn't get an ExceptionHandler, so create one
				instance = new ExceptionHandler();

				// And put it in the map
				instances.put(key, instance);
			}

			// Return the found or created ExceptionHandler
			return instance;
		}
	}

	/**
	 * Add an exception to this handler.
	 * 
	 * @param exception The exception to add
	 */
	public void handle(Exception exception) {
		exceptions.add(exception);
		
		exception.printStackTrace();
	}
	
	/**
	 * Add an exception to the specified ExceptionHandler
	 * 
	 * @param exception The exception to add
	 * @param type The type of the ExceptionHandler to add the Exception to
	 */
	public static void handle(Exception exception, int type) {
		ExceptionHandler handler = ExceptionHandler.getInstance(type);
		handler.handle(exception);
	}
}
