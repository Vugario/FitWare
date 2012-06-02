package helper;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import javax.swing.JOptionPane;

/**
 * This class overrides the default EventQueue that is used by the Swing
 * framework. Because all actions are performed through this EventQueue, this is
 * the place where we can catch all runtime exceptions without try-catching
 * every piece of code.
 * 
 * This new EventQueue is installed in Main.run(), before everything else is
 * started.
 * 
 * @see main.Main
 * @see EventQueue
 * @author Daan
 */
public class RuntimeExceptionCatcher extends EventQueue {

	/**
	 * The new dispatchEvent method. It calls the parent dispatchEvent method
	 * and catch all Exceptions that are thrown
	 * 
	 * @param newEvent The event to dispatch
	 */
	@Override
	protected void dispatchEvent(AWTEvent newEvent) {
		try {
			
			// The parent may do all the stuff, but if it fails, I'll catch it!
			super.dispatchEvent(newEvent);
			
		} catch (Exception exception) {
			
			// Report this exception to the ExceptionHandler
			ExceptionHandler.handle(exception, ExceptionHandler.TYPE_SYSTEM_ERROR);
			
		}
	}
}
