/*
 * Enrollments.java
 *
 * Created on May 6, 2012, 5:01:37 PM
 */
package view.medewerker;

import view.member.*;
import helper.SearchTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import main.Application;
import main.Session;
import model.Enrollment;
import model.Subscription;
import view.popups.EnrollmentPopup;

/**
 * This class is the class that serves as a view to see all the courses
 * @author mennowolvers
 */
public class CoursesOverview extends javax.swing.JPanel {

	/**
	 * Model for making the content of the table
	 */
	private DefaultTableModel model;
	/**
	 * An individual row to attach the onclick event
	 */
	private ListSelectionModel row;
	private final SearchTable searchTable;
	private ArrayList<Enrollment> enrollments = Enrollment.readByUserId(Session.get().getLoggedInUser().getId());

	/** Creates new form Enrollments */
	public CoursesOverview() {
		initComponents();

		this.searchTable = new SearchTable(jEnrollments, jTextFieldSearch, jButtonReset);

		// Retrieve all subscriptions
		ArrayList<Subscription> items = Subscription.readAll();

		// Fill the model with data
		this.model = (DefaultTableModel) jEnrollments.getModel();
		for (int i = 0; i < items.size(); i++) {
			Subscription item = items.get(i);
			model.insertRow(0, new Object[]{item.getId(), item.getTitle(), "€ " + item.getPrice(), item.getMinimumAge(), item.getMaximumAge()});
		}
		
		// Make a selection listener
		this.row = jEnrollments.getSelectionModel();
		this.row.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}

	private void showEnrollment() {
		// Get the currently selected subscription
		int rowNumber = jEnrollments.convertRowIndexToModel(jEnrollments.getSelectedRow());
		int subscriptionId = (Integer) model.getValueAt(rowNumber, 0);
		Subscription subscription = new Subscription(subscriptionId);

		// Show popup
		Application.getInstance().showPopup(new EnrollmentPopup(
				"Naam: " + subscription.getTitle()
				+ "\nLeeftijdscategorie: " + subscription.getMinimumAge()
				+ " tot " + subscription.getMaximumAge()
				+ "\nOmschrijving: " + subscription.getDescription(),
				subscription));
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEnrollments = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonReset = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jLabel19.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel19.setText("Mijn cursussen");

        jEnrollments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Naam", "Prijs", "Minimale leeftijd", "Maximale leeftijd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jEnrollments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jEnrollmentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jEnrollments);

        jTextFieldSearch.setText("zoekterm");

        jButtonReset.setText("Reset");

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addComponent(jLabel19)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonReset)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonReset.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

	private void jEnrollmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEnrollmentsMouseClicked
		if (evt.getClickCount() >= 2) {
			// Double clicked!
			int rowNumber = jEnrollments.getSelectedRow();
			int subscriptionId = (Integer) model.getValueAt(rowNumber, 0);
			Application.getInstance().showPanel(new view.barmedewerker.CourseInfo( subscriptionId ));
		}
	}//GEN-LAST:event_jEnrollmentsMouseClicked

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		// Show add course
		Application.getInstance().showPanel(new view.admin.CourseAdd());
	}//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JTable jEnrollments;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

	private class jLayeredPaneWrapper {

		public jLayeredPaneWrapper() {
		}
	}
}
