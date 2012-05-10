/*
 * Main.java
 *
 * Created on May 6, 2012, 3:08:25 PM
 */
package main;

import view.barmedewerker.UserOverview;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import view.*;
import view.barmedewerker.UserAdd;
import view.barmedewerker.UserModify;

/**
 *
 * @author Daan
 */
public class Application extends javax.swing.JFrame {

	/**
	 * A HashMap with all pages added to this JFrame
	 */
	protected Map<String, JPanel> pages = new HashMap<String, JPanel>();

	/** Creates new form Main */
	public Application() {
		initComponents();

		this.content.setLayout(new java.awt.BorderLayout());

		// Create the pages for our applications
		pages.put("login",			new Login());
		pages.put("dashboard",		new Dashboard());
		pages.put("profile",		new Profile());
		pages.put("invoices",		new Invoices());
		pages.put("enrollments",	new Enrollments());
		pages.put("bmi",			new Bmi());
		pages.put("useroverview",	new UserOverview());
		pages.put("useradd",		new UserAdd());
		pages.put("usermodify",		new UserModify());
		
		// And display the Login page              
		this.changeContentPanel("useroverview");
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

		this.content.removeAll();
		this.content.add(panel);
		this.content.revalidate();
		this.content.repaint();


	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header1 = new view.FitshapeTopPanel();
        content = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);

        org.jdesktop.layout.GroupLayout contentLayout = new org.jdesktop.layout.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 849, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 595, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout menuLayout = new org.jdesktop.layout.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 145, Short.MAX_VALUE)
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 536, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(menu, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(content, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(header1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(header1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(content, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(menu, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
    private javax.swing.JPanel content;
    private view.FitshapeTopPanel header1;
    private javax.swing.JPanel menu;
    // End of variables declaration//GEN-END:variables
}
