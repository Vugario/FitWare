package view.popups;

import java.awt.Color;

/**
 * A blue colored popup to show a notification
 * 
 * @author Daan
 */
public class NotificationPopup extends BasePopup {
	
	public NotificationPopup(String message) {
		super(message);
		
		this.setColor(Color.BLUE);
		this.setTitle("OPMERKING");
	}
	
}
