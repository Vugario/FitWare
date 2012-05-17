/*
 * Main.java
 *
 * Created on May 6, 2012, 3:08:25 PM
 */
package main;

import view.member.Invoices;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import view.barmedewerker.UserOverview;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import view.*;
import view.barmedewerker.UserAdd;
import view.barmedewerker.UserModify;
import view.member.Dashboard;
import view.menu.AdminuserMenu;
import view.menu.BarmedewerkerMenu;
import view.menu.MemberMenu;

/**
 *
 * @author Daan
 */
public class Application2 extends javax.swing.JFrame {

	/**
	 * A HashMap with all pages added to this JFrame
	 */
	protected Map<String, JPanel> pages = new HashMap<String, JPanel>();
	/**
	 * A HashMap with all manus added to this JFrame
	 */
	protected Map<String, JPanel> menus = new HashMap<String, JPanel>();
	
	/** Creates new form Main */
	public Application2() {
		initComponents();

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

		// Hide all pages
		Iterator iterator = pages.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry<String, JPanel> entry = (Map.Entry<String, JPanel>)iterator.next();
			entry.getValue().setVisible(false);
		}
		
		// Create the menus for our application
		menus.put("member", new MemberMenu());
		menus.put("admin", new AdminuserMenu());
		menus.put("barmedewerker", new BarmedewerkerMenu());

		// Go to the login page.
		// 1. Hide the menu
		//this.showMenu(false);
		// 2. Hide the user info in the right top
		//this.getHeader().showUserInfo(false);
		// 3. And display the Login page              
		this.changeContentPanel("usermodify");
	}
	
	public JPanel getPage(String pageName){
		return pages.get(pageName);
	}

	public JPanel getMenu() {
		// Return the panel
		return this.menu;

	}

	/**
	 * Get the header of the application
	 * 
	 * @return Header The header currently showed in our application
	 */
	public final Header getHeader() {
		return this.header;
	}
	
	/**
	 * Get the wrapper of the application
	 * 
	 * @return wrapper The wrapper that contains all content of our application
	 */
	public JLayeredPane getWrapper() {
		return this.jLayeredPaneWrapper;
	}

	/**
	 * Change content panel
	 * 
	 * @param pageName The new panel to display
	 */
	public final void changeContentPanel(String pageName) {

		// Hide the current page
		this.content.getViewport().getView().setVisible(false);
		
		// Get the right page
		JPanel panel = pages.get(pageName);

		if (panel == null) {
			Exception ex = new Exception("Trying to add page that doesn't exist: " + pageName);
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
			return;
		}

		this.content.setViewportView(panel);
		panel.setVisible(true);
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
	 * Show or hide the menu, and resize the content
	 * 
	 * @param showMenu True shows the menu, false hides it
	 */
	public final void showMenu(boolean showMenu) {
		
		// Find out if the menu is currently shown
		Boolean menuCurrentlyShown = this.jLayeredPaneWrapper.getIndexOf(this.menu) >= 0;
		
		// Check if we uberhaupt have to do something
		if((showMenu && menuCurrentlyShown) || (!showMenu && !menuCurrentlyShown)) {
			// Nope, what is wanted is already done
			return;
		}
		
		// Do the right thing!
		if(showMenu) {
			
			// Make the content smaller
			int newWidth = this.content.getWidth() - this.menu.getWidth();
			int newHeight = this.content.getHeight();
			Dimension newSize = new Dimension(newWidth, newHeight);
			this.content.setSize(newSize);
			this.content.setPreferredSize(newSize);
			this.content.setMaximumSize(newSize);
			this.content.setMinimumSize(newSize);
			
			// Set the content on the correct location
			Point location = this.content.getLocation();
			location.translate(this.menu.getWidth(), 0);
			this.content.setLocation(location);
			
			// Add the menu
			this.jLayeredPaneWrapper.add(this.menu);
			
		} else {
			
			// Remove the menu
			this.jLayeredPaneWrapper.remove(this.menu);
			
			// Set the content on the correct location
			this.content.setLocation(this.menu.getLocation());
			
			// Make the content wider
			int newWidth = this.menu.getWidth() + this.content.getWidth();
			int newHeight = this.content.getHeight();
			Dimension newSize = new Dimension(newWidth, newHeight);
			this.content.setSize(newSize);
			this.content.setPreferredSize(newSize);
			this.content.setMaximumSize(newSize);
			this.content.setMinimumSize(newSize);
		}
		
		// Repaint this application
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
        setTitle("FitShape - FitWare");
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);

        header.setBackground(new java.awt.Color(164, 27, 62));

        jLayeredPaneWrapper.setMaximumSize(new java.awt.Dimension(1000, 650));
        jLayeredPaneWrapper.setPreferredSize(new java.awt.Dimension(1000, 650));

        menu.setLayout(new java.awt.BorderLayout());
        menu.add(memberMenu, java.awt.BorderLayout.CENTER);

        menu.setBounds(0, 0, 140, 570);
        jLayeredPaneWrapper.add(menu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        content.setBackground(new java.awt.Color(238, 238, 238));
        content.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        content.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        content.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        content.setAutoscrolls(true);

        dashboard.setForeground(new java.awt.Color(204, 0, 0));
        content.setViewportView(dashboard);

        content.setBounds(140, 0, 860, 650);
        jLayeredPaneWrapper.add(content, javax.swing.JLayeredPane.DEFAULT_LAYER);
        content.getViewport().setBackground(new Color(238,238,238));

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
			java.util.logging.Logger.getLogger(Application2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Application2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Application2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Application2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Application2().setVisible(true);
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
