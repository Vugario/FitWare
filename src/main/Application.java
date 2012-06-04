/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import helper.ExceptionHandler;
import helper.PopupMouseListener;
import helper.db.Manager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import model.User;

/**
 *
 * @author allentje
 */
public final class Application {

	private Manager db = new Manager();
	private JFrame window;
	private static Application instance = new Application();
	private JPanel popupBackground;
	private ArrayList<JPanel> popups = new ArrayList<JPanel>();

	/**
	 * A private constructor, to prevent others to make a new instance
	 */
	private Application() {
	}

	public void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			ExceptionHandler.handle(e, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
		
		startup();

	}

	public void startup() {

		window = new JFrame("Fitware");
		window.setSize(1000, 600);
		window.setResizable(false);
		window.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent event) {
				close();
			}
		});

		window.getContentPane().setLayout(new BorderLayout());

		showPanel(new view.Login());

		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public void showPanel(JPanel panel) {

		JScrollPane scrollpane = new JScrollPane(panel);
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getHorizontalScrollBar().setEnabled(false);

		scrollpane.setBorder(null);


		view.Header header = new view.Header();

		window.getContentPane().removeAll();
		window.getContentPane().add(scrollpane, BorderLayout.CENTER);
		window.getContentPane().add(header, BorderLayout.NORTH);
		window.getContentPane().add(new view.Footer(), BorderLayout.SOUTH);

		User userSession = Session.get().getLoggedInUser();

		if (userSession == null) {
			header.showUserInfo(false);
			window.getContentPane().add(new view.Login());

		} else {
			header.showUserInfo(true);
			window.getContentPane().add(this.getMenuForLoggedInUser(), BorderLayout.WEST);
		}

		window.getContentPane().validate();
		window.getContentPane().repaint();
	}

	/**
	 * Show a popup
	 * 
	 * @param panel 
	 */
	public void showPopup(JPanel panel) {

		// Add the popup to the list
		popups.add(panel);

		// Only add the semi transparent background if there wasn't already a popup
		if (popups.size() == 1) {
			// Source: http://tips4java.wordpress.com/2009/05/31/backgrounds-with-transparency/
			popupBackground = new JPanel() {

				@Override
				protected void paintComponent(Graphics g) {
					g.setColor(getBackground());
					g.fillRect(0, 0, getWidth(), getHeight());
					super.paintComponent(g);
				}
			};
			popupBackground.setOpaque(false);
			popupBackground.setBackground(new Color(0, 0, 0, 90));
			popupBackground.setSize(window.getSize());
			window.getLayeredPane().add(popupBackground, new Integer(JLayeredPane.POPUP_LAYER - 1));
		}

		// Add the popup
		panel.setSize(panel.getPreferredSize());
		int posX = (window.getWidth() - panel.getWidth()) / 2;
		int posY = (window.getHeight() - panel.getHeight()) / 3;
		panel.setLocation(posX, posY);
		window.getLayeredPane().add(panel, JLayeredPane.POPUP_LAYER);

		// Show the GlassPane, so the background panel is 'disabled'
		Component glassPane = window.getGlassPane();
		glassPane.addMouseListener(new PopupMouseListener(window, panel));
		glassPane.setVisible(true);

	}

	/**
	 * Close a popup
	 */
	public void closePopup() {

		// Get the popup that is on top
		JPanel popup = popups.get(popups.size() - 1);

		// Hide the GlassPane
		window.getGlassPane().setVisible(false);

		// Remove the popup
		window.getLayeredPane().remove(popup);
		popups.remove(popup);

		// Remove the semi transparent background if no other popup is visible
		if (popups.isEmpty()) {
			window.getLayeredPane().remove(popupBackground);
		}

		// Repaint this application
		window.repaint();
	}

	private void close() {
		db.close();
		window.dispose();
	}

	public static Application getInstance() {
		return instance;
	}

	/**
	 * Get the menu for the currently logged in user
	 * 
	 * @return The Menu instance, or null if no user is logged in
	 */
	private JPanel getMenuForLoggedInUser() {
		User user = Session.get().getLoggedInUser();

		// Check if somebody is logged in
		if (user == null) {

			// Nobody is logged in at the moment
			return null;
		}

		// Get the role of the currently logged in user
		model.Role role = user.getRole();

		// Return the corresponding menu
		if (role.getTitle().equals("member")) {

			// Return the MemberMenu
			return new view.menu.MemberMenu();

		} else if (role.getTitle().equals("barmedewerker")) {

			// Return the BarmedewerkerMenu
			return new view.menu.BarmedewerkerMenu();

		} else if (role.getTitle().equals("admin")) {

			// Does not exist yet:
			return new view.menu.AdminMenu();

		} else {

			// Fallback to the MemberMenu
			return new view.menu.MemberMenu();

		}
	}
}
