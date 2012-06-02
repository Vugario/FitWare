package view.popups;

import java.awt.Color;

/**
 * A green colored popup to show a success message
 * 
 * @author Daan
 */
public class SuccessPopup extends BasePopup {
	
	public SuccessPopup(String message) {
		super(message);
		
		this.setColor(new Color(0, 150, 0));
		this.setTitle("GESLAAGD");
	}
	
}
