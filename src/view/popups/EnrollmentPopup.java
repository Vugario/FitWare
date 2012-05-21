package view.popups;

import java.awt.Color;

/**
 * A simple popup to show subscription data and allow a user to sign in or off
 * 
 * @author mennowolvers
 */
public class EnrollmentPopup extends DetailPopup {
	
	public EnrollmentPopup(String message, Object data) {
		super(message);
		
		this.setColor(Color.GRAY);
		this.setTitle("Cursus");
                
                System.out.println( data.getClass().getName() );
                
                jButtonConfirm.setText("Inschrijven");
	}
	
}
