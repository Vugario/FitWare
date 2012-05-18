/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import helper.db.Manager;
import java.awt.BorderLayout;
import java.awt.Color;
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

	private Manager db;
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
			System.out.println(e.getMessage());
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

		view.Header header = new view.Header();
		header.showUserInfo(false);

		window.getContentPane().removeAll();
		window.getContentPane().add(scrollpane, BorderLayout.CENTER);
		window.getContentPane().add(header, BorderLayout.NORTH);

		User userSession = Session.get().getLoggedInUser();

		if (userSession == null) {
			window.getContentPane().add(new view.Login());

		} else {
			header.showUserInfo(true);
			window.getContentPane().add(new view.menu.MemberMenu(), BorderLayout.WEST);
			window.getContentPane().add(new view.Header(), BorderLayout.SOUTH);
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

		// Todo:
		// Show the GlassPane, so the background panel is 'disabled'
		// This does not work:
		//window.getGlassPane().setVisible(true);

	}

	/**
	 * Close a popup
	 */
	public void closePopup() {

		// Get the popup that is on top
		JPanel popup = popups.get(popups.size()-1);
		
		// Remove the popup
		window.getLayeredPane().remove(popup);
		popups.remove(popup);
		
		// Remove the semi transparent background if no other popup is visible
		if(popups.isEmpty()) {
			window.getLayeredPane().remove(popupBackground);
		}
		
		// Repaint this application
		window.repaint();
	}

	private void close() {
		//db.close();
		window.dispose();
	}

	public static Application getInstance() {
		return instance;
	}
}
