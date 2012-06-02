/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UserOverview.java
 *
 * Created on May 10, 2012, 9:29:22 PM
 */
package view.medewerker;

import helper.SearchTable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import main.Application;
import model.User;

/**
 * This is the class to view several users. From this view it's possible to go 
 * to the UserAdd view or the UserModify view
 * 
 * @author vm
 */
public class UserOverview extends javax.swing.JPanel {
	protected DefaultTableModel model;
	private final SearchTable searchTable;
	
	/** Creates new form UserOverview */
	public UserOverview() {
		initComponents();
		this.searchTable = new SearchTable(jTable1, jTextFieldSearch, jButtonReset);
		this.model = (DefaultTableModel) jTable1.getModel();
		User user  = new User();
		user.getTableRowObjects();
		updateTable();
	}
	
	/**
	 * This method fills the jTable in the BarProductOverview.
	 * @author daan
	 * 
	 */
	private void updateTable() {
		//DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
		//User user = Session.get().getLoggedInUser();

		// First, empty it.
		model.setRowCount(0);

		// Secondly, fill it with all users
		ArrayList<User> users = User.readAll();

		for (User user : users) {
			model.addRow(user.getTableRowObjects());
		}
	}
	
	/**
	 * Show the user in a different view (userModify view)
	 */
	private void showUser() {
		
		// Get the currently selected user
		int rowNumber = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
		String stringId = (String) model.getValueAt(rowNumber, 0);
		int userId = Integer.parseInt(stringId);
		
		//Set the product data in the view
		Application.getInstance().showPanel(new view.medewerker.UserModify(userId));

	}
	
	
 
 
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        newUser = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1000, 700));
        setPreferredSize(new java.awt.Dimension(510, 500));

        jLabel1.setText("Toon:");

        jTextFieldSearch.setText("Zoek op naam");
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });
        jTextFieldSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusGained(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Klant-ID", "Naam", "Geboortedatum", "Rol", "E-mailadres"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setMinWidth(75);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(75);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(75);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(12);

        newUser.setText("+");
        newUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserActionPerformed(evt);
            }
        });

        jButtonReset.setText("Reset");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newUser)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonReset)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addComponent(newUser)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
		// TODO 
	}//GEN-LAST:event_jTextFieldSearchActionPerformed

	private void newUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserActionPerformed
		// TODO add new user (go to the profile panel)
		Application.getInstance().showPanel(new UserAdd());
	}//GEN-LAST:event_newUserActionPerformed

	private void jTextFieldSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusGained
		// Clear the text when focussed
		jTextFieldSearch.setText(null);
	}//GEN-LAST:event_jTextFieldSearchFocusGained

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
		// Show the UserModify screen so there is detailed information 
		//about the selected user
		if (evt.getClickCount() >= 2) {
			// Double clicked!
			showUser();
		}
	}//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JButton newUser;
    // End of variables declaration//GEN-END:variables
}
