package helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Application;
import model.User;
import view.popups.ErrorPopup;

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
	protected final static Map<Integer, ExceptionHandler> instances = new HashMap<Integer, ExceptionHandler>();
	/**
	 * A list with exceptions added to this type
	 */
	protected ArrayList<Exception> exceptions = new ArrayList<Exception>();
	/**
	 * The type of this ExceptionHandler.
	 * It must be one of the ExceptionHandler.TYPE_* constants
	 */
	protected int type;

	/**
	 * A protected constructor, so nobody else can create one
	 * 
	 * @param type One of the ExceptionHandler.TYPE_* constants
	 */
	protected ExceptionHandler(int type) {
		this.type = type;
	}

	/**
	 * Get an instance of ExceptionHandler of the requested type
	 * 
	 * @param type The type by which the instance is identified
	 * @return The instance of ExceptionHandler of the requested type
	 */
	public static ExceptionHandler getInstance(int type) {

		// We use synchronized to prevent raceconditions.
		// These raceconditions can occure in multithreaded programs.
		synchronized (instances) {

			// Try to get the ExceptionHandler of the requested type
			ExceptionHandler instance = instances.get(type);

			// Check if we could get an ExceptionHandler
			if (instance == null) {
				// We couldn't get an ExceptionHandler, so create one
				instance = new ExceptionHandler(type);

				// And put it in the map
				instances.put(type, instance);
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

		// Add the exception
		exceptions.add(exception);

		// Make sure the exception has a message
		String message = exception.getMessage();
		if (message == null || message.length() == 0) {
			message = "Fatal: " + exception.getClass();
		}

		Application application = Application.getInstance();

		// Actions that are always performed
		Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, exception);

		// Actions per type
		switch (type) {
			default:
			case ExceptionHandler.TYPE_SYSTEM_ERROR:
				application.showPopup(new ErrorPopup(
						"Er is een interne fout opgetreden.\n"
						+ "Neem a.u.b. contact op met de Itopia klantenservice als\n"
						+ "dit probleem blijvend is."));
				break;

			case ExceptionHandler.TYPE_USER_ERROR:
				application.showPopup(new ErrorPopup(message));
				break;
		}
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
