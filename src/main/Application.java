/*
 * Main.java
 *
 * Created on May 6, 2012, 3:08:25 PM
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import view.barmedewerker.UserOverview;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import view.*;
import view.barmedewerker.UserAdd;
import view.barmedewerker.UserModify;
import view.menu.AdminuserMenu;
import view.menu.BarmedewerkerMenu;
import view.menu.MemberMenu;

/**
 *
 * @author Daan
 */
public class Application extends javax.swing.JFrame {

	/**
	 * A HashMap with all pages added to this JFrame
	 */
	protected Map<String, JPanel> pages = new HashMap<String, JPanel>();
	/**
	 * A HashMap with all manus added to this JFrame
	 */
	protected Map<String, JPanel> menus = new HashMap<String, JPanel>();

	/**
	 * The alpha transparant background for popups
	 */
	protected JPanel popupBackground;
	
	/**
	 * The popup itself
	 */
	protected Popup popup;
	
	/** Creates new form Main */
	public Application() {
		initComponents();
		initPopup();

		// Create the pages for our application
		pages.put("login", new Login());
		pages.put("dashboard", new Dashboard());
		pages.put("profile", new Profile());
		pages.put("invoices", new Invoices());
		pages.put("enrollments", new Enrollments());
		pages.put("bmi", new Bmi());
		pages.put("useroverview", new UserOverview());
		pages.put("useradd", new UserAdd());
		pages.put("usermodify", new UserModify());

		// Create the menus for our application
		menus.put("member", new MemberMenu());
		menus.put("admin", new AdminuserMenu());
		menus.put("barmedewerker", new BarmedewerkerMenu());

		// Hide the menu
		this.menu.removeAll();

		// Hide the user info in the right top
		this.header.showUserInfo(false);

		// And display the Login page              
		this.changeContentPanel("login");
	}

	public JPanel getMenu() {
		// Return the panel
		return this.menu;

	}

	/**
	 * Change content panel
	 * 
	 * @param pageName The new panel to display
	 */
	public final void changeContentPanel(String pageName) {

		//this.content.setLayout(new java.awt.BorderLayout());
		JPanel panel = pages.get(pageName);

		if (panel == null) {
			Exception ex = new Exception("Trying to add page that doesn't exist: " + pageName);
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
			return;
		}

		this.content.setViewportView(panel);
	}

	/**
	 * Change menu panel
	 * 
	 * @param menuName The new panel to display
	 */
	public final void changeMenuPanel(String menuName) {

		//this.content.setLayout(new java.awt.BorderLayout());
		JPanel panel = menus.get(menuName);

		if (panel == null) {
			Exception ex = new Exception("Trying to add menu that doesn't exist: " + menuName);
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
			return;
		}

		this.menu.removeAll();
		this.menu.add(panel, BorderLayout.WEST);
		this.menu.revalidate();
		this.menu.repaint();
	}

	/**
	 * Get the header of the application
	 * 
	 * @return Header The header currently showed in our application
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Initialize the popups
	 */
	protected final void initPopup() {
		// Create an alpha-transparent JPanel
		// Source: http://tips4java.wordpress.com/2009/05/31/backgrounds-with-transparency/
		this.popupBackground = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		this.popupBackground.setOpaque(false);
		this.popupBackground.setBackground(new Color(0, 0, 0, 90));
		
		// Set the size to the same size as the application
		Dimension dimension = this.jLayeredPaneWrapper.getSize();
		this.popupBackground.setSize(dimension);
		this.popupBackground.setPreferredSize(dimension);
		this.popupBackground.setMaximumSize(dimension);
		this.popupBackground.setMinimumSize(dimension);
		
		// Create the popup
		this.popup = new Popup();
		
		// Set the popups size
		Dimension popupDimension = new Dimension(400, 166);
		//this.popup.setSize(popupDimension);
		//this.popup.setPreferredSize(popupDimension);
		//this.popup.setMaximumSize(popupDimension);
		//this.popup.setMinimumSize(popupDimension);
		
		// Center the popup
		int posX = (this.popupBackground.getWidth() - this.popup.getWidth()) / 2;
		int posY = (this.popupBackground.getHeight() - this.popup.getHeight()) / 2;
		this.popup.setLocation(posX, posY);
	}
	
	/**
	 * Show a popup
	 * 
	 * @param message The message to show
	 */
	public final void showPopup(String message) {
		
		// Disable all current components
		// TODO
		
		// Set the message
		this.popup.setMessage(message);
		
		// Add the background and popup
		this.jLayeredPaneWrapper.add(this.popupBackground, new Integer(100));
		this.jLayeredPaneWrapper.add(this.popup, new Integer(101));
	}
	
	/**
	 * Hide a popup
	 */
	public void hidePopup() {
		
		// Remove the background and popup
		this.jLayeredPaneWrapper.remove(this.popupBackground);
		this.jLayeredPaneWrapper.remove(this.popup);
		
		// Repaint the application
		this.repaint();
		
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new view.Header();
        jLayeredPaneWrapper = new javax.swing.JLayeredPane();
        menu = new javax.swing.JPanel();
        memberMenu = new view.menu.MemberMenu();
        content = new javax.swing.JScrollPane();
        dashboard = new view.member.Dashboard();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);

        header.setBackground(new java.awt.Color(164, 27, 62));

        jLayeredPaneWrapper.setMaximumSize(new java.awt.Dimension(1000, 650));
        jLayeredPaneWrapper.setPreferredSize(new java.awt.Dimension(1000, 650));
        jLayeredPaneWrapper.setSize(new java.awt.Dimension(1000, 650));

        menu.setLayout(new java.awt.BorderLayout());
        menu.add(memberMenu, java.awt.BorderLayout.CENTER);

        menu.setBounds(0, 0, 140, 373);
        jLayeredPaneWrapper.add(menu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        content.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        content.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        content.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        content.setOpaque(false);

        dashboard.setForeground(new java.awt.Color(204, 0, 0));
        content.setViewportView(dashboard);

        content.setBounds(140, 0, 860, 650);
        jLayeredPaneWrapper.add(content, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLayeredPaneWrapper, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1000, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(header, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(header, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLayeredPaneWrapper, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 650, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Application().setVisible(true);
			}
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane content;
    private view.member.Dashboard dashboard;
    private view.Header header;
    private javax.swing.JLayeredPane jLayeredPaneWrapper;
    private view.menu.MemberMenu memberMenu;
    private javax.swing.JPanel menu;
    // End of variables declaration//GEN-END:variables
}
