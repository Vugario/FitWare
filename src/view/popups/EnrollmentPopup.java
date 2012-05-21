package view.popups;

import java.awt.Color;
import main.Session;
import model.Enrollment;
import model.Subscription;

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
                
                if( "model.Subscription".equals( data.getClass().getName() ) )
                {
                    Subscription subscription = (Subscription) data;
                    Enrollment enrollment = new Enrollment().readEnrollmentBySubscriptionIdAndUserId( subscription.getId(), Session.get().getLoggedInUser().getId() );
                    
                    System.out.println( enrollment );
                }
                
                jButtonConfirm.setText("Inschrijven");
	}
	
}
