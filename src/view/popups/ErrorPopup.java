package view.popups;

import java.awt.Color;

/**
 * A red colored popup to show an error
 * 
 * @author Daan
 */
public class ErrorPopup extends BasePopup {
	
	public ErrorPopup(String message) {
		super(message);
		
		this.setColor(Color.RED);
		this.setTitle("FOUTMELDING");
	}
	
}
