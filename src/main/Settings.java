package main;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all settings that are the same for all clients, but which
 * might change during the development of Fitware
 * 
 * @author Daan
 */
public class Settings {

	/**
	 * The HashMap that contains all settings
	 */
	protected static Map<String, String> settings = new HashMap<String, String>();

	/**
	 * Initialize this Settings class.
	 */
	public static void init() {
		// Check if this Settings class is already initialized
		if (!settings.isEmpty()) {
			return;
		}

		// Database settings
		settings.put("db-url", "jdbc:postgresql://db.fitwareonline.nl:5432/fitware");
		settings.put("db-user", "fitware");
		settings.put("db-password", "8T3m4577");
		
		// Allen's database settings
//		settings.put("db-url", "jdbc:postgresql://allen.pb/fitshape");
//		settings.put("db-user", "postgres");
//		settings.put("db-password", "root");
		
		// Debug mode: either "on" or "off"
		settings.put("debug-mode", "on");
	}

	/**
	 * Get a setting
	 * 
	 * @param name The name by which the setting is identified
	 * @return The value
	 */
	public static String get(String name) {
		Settings.init();
		
		return settings.get(name);
	}

	/**
	 * Set a setting temporary for this process.
	 * 
	 * @param name The name by which the setting is identified
	 * @param value The (new) value
	 */
	public static void set(String name, String value) {
		settings.put(name, value);
	}
}
