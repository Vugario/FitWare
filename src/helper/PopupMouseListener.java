package helper;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Daan
 */
public class PopupMouseListener extends MouseAdapter {

	private JFrame window;
	private JPanel popup;
	
	public PopupMouseListener(JFrame window, JPanel popup) {
		super();
		this.window = window;
		this.popup = popup;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		redispatchMouseEvent(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		redispatchMouseEvent(e);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		redispatchMouseEvent(e);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		redispatchMouseEvent(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		redispatchMouseEvent(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		redispatchMouseEvent(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		redispatchMouseEvent(e);
		this.mouseWheelMoved(null);
	}

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
