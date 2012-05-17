/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import helper.db.Manager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import main.Session;
import model.User;

/**
 *
 * @author allentje
 */
public final class Application {

	private Manager db;
	private JFrame window;
	private static Application instance = new Application();

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
	 * method to show popup
	 * @daan zou je dit kunnen nakijken en verbeteren?
	 * @param panel 
	 */
	public void showPopup(JPanel panel){
		
		int posX = (window.getContentPane().getWidth()-panel.getWidth()) / 2;
		int posY = (window.getContentPane().getHeight()-panel.getHeight()) / 3;
		
		PopupFactory factory = PopupFactory.getSharedInstance();
		Popup popup = factory.getPopup(window.getContentPane(), panel, posX, posY);
		popup.show();
	
	
	}
	
	private void close() {
		//db.close();
		window.dispose();
	}

	public static Application getInstance() {
		return instance;
	}
}
