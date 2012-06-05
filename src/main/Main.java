
package main;

import helper.RuntimeExceptionCatcher;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;

/**
 * This is the main application, from here the application will be run
 * 
 */
public class Main {
	
	public static void main(String args[]) {

		// Install the new EventQueue. This new queue will catch all runtime
		// exceptions, so we can show them in a user-friendly way.
		EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		queue.push(new RuntimeExceptionCatcher());

		// Start the program from Swings EventQueue (the one we just installed)
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// Start up our application
				Application.getInstance().startup();
			}
		});

	}
}
