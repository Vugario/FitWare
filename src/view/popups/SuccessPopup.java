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
		
		this.setColor(Color.GREEN);
		this.setTitle("GESLAAGD");
	}
	
}
