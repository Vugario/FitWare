
package helper;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This Class containes the Listeners for mouse in the Popup
 * @author Daan
 */
public class PopupMouseListener extends MouseAdapter {
	/**
	 * The Jframe window, the frame where the popup will be showed in
	 */
	private JFrame window;
	/**
	 * The jPanel popup the panel that contains the popup
	 */
	private JPanel popup;
	
	/**
	 * This method is the constructor for PopupMouseListener
	 * @param window The Jframe window, the frame where the popup will be showed in
	 * @param popup The jPanel popup the panel that contains the popup
	 */
	public PopupMouseListener(JFrame window, JPanel popup) {
		super();
		this.window = window;
		this.popup = popup;
	}
	
	/**
	 * This method is the a overidden version of mouseClicked
	 * @param e the event MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		redispatchMouseEvent(e);
	}
	
	/**
	 * This method is the a overidden version of mouseDragged
	 * @param e the event MouseEvent
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		redispatchMouseEvent(e);
	}
	
	/**
	 * This method is the a overidden version of mouseEntered
	 * @param e the event MouseEvent
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		redispatchMouseEvent(e);
	}
	
	/**
	 * This method is the a overidden version of mouseExited
	 * @param e the event MouseEvent
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		redispatchMouseEvent(e);
	}
	
	/**
	 * This method is the a overidden version of mouseMoved
	 * @param e the event MouseEvent
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		redispatchMouseEvent(e);
	}

	/**
	 * This method is the a overidden version of mousePressed
	 * @param e the event MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		redispatchMouseEvent(e);
	}
	/**
	 * This method is the a overidden version of mousReleased
	 * @param e the event MouseEvent
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		redispatchMouseEvent(e);
		this.mouseWheelMoved(null);
	}

		/**
	 * This method is the redispatcher of mouseEvent. It allows events in the 
	 * popup and handles the event.
	 * @param e the event MouseEvent
	 */
	private void redispatchMouseEvent(MouseEvent e) {
		// Allow events in the popup itself
		Point glassPanePoint = e.getPoint();
		Point popupPoint = SwingUtilities.convertPoint(
				window.getGlassPane(),
				glassPanePoint,
				popup);

		// Find out where we clicked excactly
		Component component =
				SwingUtilities.getDeepestComponentAt(
				popup,
				popupPoint.x,
				popupPoint.y);

		if (component != null) {
			//Forward events to the popup.
			Point componentPoint = SwingUtilities.convertPoint(
					window.getGlassPane(),
					glassPanePoint,
					component);
			component.dispatchEvent(new MouseEvent(component,
					e.getID(),
					e.getWhen(),
					e.getModifiers(),
					componentPoint.x,
					componentPoint.y,
					e.getClickCount(),
					e.isPopupTrigger()));
		}
	}
}
