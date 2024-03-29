/*
 * Menu.java
 *
 * Created on May 6, 2012, 3:09:56 PM
 */
package view.menu;

import main.Application;

/**
 *	This is the class for the view of the Barmedewerkeruser menubar
 * @author Daan
 */
public class BarmedewerkerMenu extends javax.swing.JPanel {

    /**
     * Creates new form Menu
     */
    public BarmedewerkerMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonDashboard = new javax.swing.JButton();
        jButtonProfile = new javax.swing.JButton();
        jButtonInvoices = new javax.swing.JButton();
        jButtonEnrollments = new javax.swing.JButton();
        jButtonBar = new javax.swing.JButton();

        jButtonDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/home_32.png"))); // NOI18N
        jButtonDashboard.setText("Dashboard");
        jButtonDashboard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDashboard.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDashboardActionPerformed(evt);
            }
        });

        jButtonProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/users_32.png"))); // NOI18N
        jButtonProfile.setText("Gebruikers");
        jButtonProfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonProfile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProfileActionPerformed(evt);
            }
        });

        jButtonInvoices.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/page_text_32.png"))); // NOI18N
        jButtonInvoices.setText("Facturen");
        jButtonInvoices.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonInvoices.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonInvoices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInvoicesActionPerformed(evt);
            }
        });

        jButtonEnrollments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/book_32.png"))); // NOI18N
        jButtonEnrollments.setText("Cursussen");
        jButtonEnrollments.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEnrollments.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEnrollments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnrollmentsActionPerformed(evt);
            }
        });

        jButtonBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/basket_32.png"))); // NOI18N
        jButtonBar.setText("Bar");
        jButtonBar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jButtonInvoices, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .add(jButtonDashboard, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .add(jButtonProfile, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .add(jButtonEnrollments, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jButtonBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jButtonDashboard, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonProfile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonInvoices)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonEnrollments)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonBar)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        layout.linkSize(new java.awt.Component[] {jButtonBar, jButtonDashboard, jButtonEnrollments, jButtonInvoices, jButtonProfile}, org.jdesktop.layout.GroupLayout.VERTICAL);

    }// </editor-fold>//GEN-END:initComponents

	private void jButtonDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDashboardActionPerformed
		Application.getInstance().showPanel(new view.barmedewerker.Dashboard());
	}//GEN-LAST:event_jButtonDashboardActionPerformed

	private void jButtonProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProfileActionPerformed
		Application.getInstance().showPanel(new view.medewerker.UserOverview());
	}//GEN-LAST:event_jButtonProfileActionPerformed

	private void jButtonInvoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInvoicesActionPerformed
        Application.getInstance().showPanel(new view.medewerker.InvoiceListOverview());
	}//GEN-LAST:event_jButtonInvoicesActionPerformed

	private void jButtonEnrollmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnrollmentsActionPerformed
         Application.getInstance().showPanel(new view.medewerker.CoursesOverview());
	}//GEN-LAST:event_jButtonEnrollmentsActionPerformed

	private void jButtonBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBarActionPerformed
		Application.getInstance().showPanel(new view.medewerker.BarApp());	
	}//GEN-LAST:event_jButtonBarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBar;
    private javax.swing.JButton jButtonDashboard;
    private javax.swing.JButton jButtonEnrollments;
    private javax.swing.JButton jButtonInvoices;
    private javax.swing.JButton jButtonProfile;
    // End of variables declaration//GEN-END:variables
}
