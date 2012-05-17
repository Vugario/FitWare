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
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.UIManager;
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
	
	public Application(){
		
	}
	
	public void initialize(){
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		startup();
				
	}
	
	public void startup(){
	
		
		
		window = new JFrame("Fitware");
		window.setSize(1000, 1000);
		window.setResizable(false);
		window.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent event){
				close();
			}
			
		});
		
		window.getContentPane().setLayout(new BorderLayout());
		
		showPanel(new view.Login());
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	public void showPanel(JPanel panel){
				
		JScrollPane scrollpane = new JScrollPane(panel);
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);

		view.Header header = new view.Header();
		header.showUserInfo(false);
		
		window.getContentPane().removeAll();
		window.getContentPane().add(scrollpane, BorderLayout.CENTER);
		window.getContentPane().add(header, BorderLayout.NORTH);
	
		User userSession = Session.get().getLoggedInUser();

		if(userSession == null){
			window.getContentPane().add(new view.Login());

		}else{
			header.showUserInfo(true);
			window.getContentPane().add(new view.menu.MemberMenu(), BorderLayout.WEST);
			window.getContentPane().add(new view.Header(), BorderLayout.SOUTH);
		}
		
		window.getContentPane().validate();
		window.getContentPane().repaint();
	}
	
	private void close(){
		//db.close();
		window.dispose();
	}
	
	public static Application getInstance(){
		return instance;
	}
	
}
