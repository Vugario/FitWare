/*
 * Dashboard.java
 *
 * Created on May 6, 2012, 3:13:00 PM
 */
package view.barmedewerker;

import helper.Datetime;
import helper.ExceptionHandler;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import main.Session;
import model.Attendance;
import model.Enrollment;
import model.Purchase;
import model.User;

/**
 *
 * @author Daan
 */
@SuppressWarnings("serial")
public class Dashboard extends javax.swing.JPanel {

    private DefaultTableModel purchaseModel;
    private DefaultTableModel enrollmentModel;
    private User sessionUser = Session.get().getLoggedInUser();
    private User user = new User();

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();

        purchaseModel = (DefaultTableModel) recentPayments.getModel();

        //disbale user elementes if no user has been found yet
        jLabelCustomerName.setVisible(false);
        userFoundPanel.setVisible(false);
        jLabelCustomerBirthdate.setVisible(false);
        jLabelCustomerGender.setVisible(false);
        checkinSuccessText.setVisible(false);
        checkinSuccessPanel.setVisible(false);
        userCourses.setVisible(false);

        updateLatestPurchase();
        updateUserInfo();
    }

    private void updateUserInfo() {
        this.jLabelFullname.setText(sessionUser.getFullName());
    }

    private void updateLatestPurchase() {

		Purchase purchaseModel = new Purchase();
        getPurchaseModel().setRowCount(0);

        // Secondly, fill it with all users
        ArrayList<Purchase> purchases = purchaseModel.readLastPurchase(13);

        for (Purchase purchase : purchases) {
            getPurchaseModel().addRow(purchase.getTableRowObjects());
        }

    }

    public void searchUser() {

        try {
            int id = Integer.parseInt(userSearchField.getText());
            user.readByRole(id, "member");

            jLabelCustomerName.setVisible(true);
            userFoundPanel.setVisible(true);

            if (user.getId() > 0) {

                jLabelCustomerBirthdate.setVisible(true);
                jLabelCustomerGender.setVisible(true);
                userCourses.setVisible(true);

                int userId = user.getId();
                // Set the label
                jLabelCustomerName.setText(user.getFullName());

                Datetime datetime = new Datetime(user.getBirthdate());
                jLabelCustomerBirthdate.setText(datetime.format("dd-MM-yyyy"));

                if (user.getGender()) {
                    jLabelCustomerGender.setText("Man");
                } else {
                    jLabelCustomerGender.setText("Vrouw");
                }

                addUserCoursesToComboBox(userId);

            } else {
				jLabelCustomerBirthdate.setVisible(false);
                jLabelCustomerGender.setVisible(false);
                userCourses.setVisible(false);
				
                jLabelCustomerName.setText("Gebruiker niet gevonden");
            }
        } catch (Exception ex) {
            ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
        }



    }

    @SuppressWarnings("unchecked")
    public void addUserCoursesToComboBox(int userId) {
        userCourses.removeAllItems();

        ArrayList<Enrollment> enrollments = Enrollment.readByUserId(userId);

        for (Enrollment enrollment : enrollments) {

            //  Object lol = new Object();

            userCourses.addItem(enrollment);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        recentPayments = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelFullname = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        userSearchField = new javax.swing.JTextField();
        jLabelFullname1 = new javax.swing.JLabel();
        jButtonSearchUser = new javax.swing.JButton();
        userFoundPanel = new javax.swing.JPanel();
        jLabelCustomerName = new javax.swing.JLabel();
        jLabelCustomerBirthdate = new javax.swing.JLabel();
        jLabelCustomerGender = new javax.swing.JLabel();
        userCourses = new javax.swing.JComboBox();
        userCheckin = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        checkinSuccessPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelFullname2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        checkinSuccessText = new javax.swing.JTextPane();

        recentPayments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Datum", "Prijs", "Product", "Betaald"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(recentPayments);
        recentPayments.getColumnModel().getColumn(0).setResizable(false);
        recentPayments.getColumnModel().getColumn(1).setResizable(false);
        recentPayments.getColumnModel().getColumn(2).setResizable(false);
        recentPayments.getColumnModel().getColumn(3).setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setPreferredSize(new java.awt.Dimension(380, 94));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jLabel1.setText("Welkom terug, ");

        jLabelFullname.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabelFullname.setText("jLabelFullname");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelFullname)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelFullname))
                .add(32, 32, 32))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jLabel3.setText("Aangemeld voor cursussen");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(8, 8, 8)
                .add(jLabel3)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jLabel3)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jLabel2.setText("Recente activiteiten");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jLabel2)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(242, 241, 240));

        userSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordFieldKeyReleased(evt);
            }
        });

        jLabelFullname1.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabelFullname1.setText("Gebruiker inchecken");

        jButtonSearchUser.setText("Zoeken");
        jButtonSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchUserActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(userSearchField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jButtonSearchUser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabelFullname1))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(13, 13, 13)
                .add(jLabelFullname1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(userSearchField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButtonSearchUser))
                .add(23, 23, 23))
        );

        userFoundPanel.setBackground(new java.awt.Color(242, 241, 240));

        jLabelCustomerName.setFont(new java.awt.Font("Ubuntu", 1, 14));
        jLabelCustomerName.setText("gebruiker");

        jLabelCustomerBirthdate.setFont(new java.awt.Font("Ubuntu", 0, 14));
        jLabelCustomerBirthdate.setText("21-05-2012");

        jLabelCustomerGender.setFont(new java.awt.Font("Ubuntu", 0, 14));
        jLabelCustomerGender.setText("gender");

        userCourses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        userCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userCoursesActionPerformed(evt);
            }
        });
        userCourses.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                userCoursesCaretPositionChanged(evt);
            }
        });

        userCheckin.setText("Inchecken");
        userCheckin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUserCheckinActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout userFoundPanelLayout = new org.jdesktop.layout.GroupLayout(userFoundPanel);
        userFoundPanel.setLayout(userFoundPanelLayout);
        userFoundPanelLayout.setHorizontalGroup(
            userFoundPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .add(userFoundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelCustomerBirthdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelCustomerGender, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, userFoundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(userFoundPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(userFoundPanelLayout.createSequentialGroup()
                        .add(jLabelCustomerName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .add(37, 37, 37))
                    .add(userFoundPanelLayout.createSequentialGroup()
                        .add(userCourses, 0, 243, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(userCheckin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        userFoundPanelLayout.setVerticalGroup(
            userFoundPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(userFoundPanelLayout.createSequentialGroup()
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelCustomerName)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(userFoundPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelCustomerBirthdate)
                    .add(jLabelCustomerGender))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(userFoundPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(userCourses, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(userCheckin))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        checkinSuccessPanel.setBackground(new java.awt.Color(242, 241, 240));

        jLabelFullname2.setFont(new java.awt.Font("Ubuntu", 0, 18));
        jLabelFullname2.setText("Succesvol ingecheckt");

        checkinSuccessText.setBackground(new java.awt.Color(242, 241, 240));
        checkinSuccessText.setBorder(null);
        checkinSuccessText.setEditable(false);
        jScrollPane2.setViewportView(checkinSuccessText);

        org.jdesktop.layout.GroupLayout checkinSuccessPanelLayout = new org.jdesktop.layout.GroupLayout(checkinSuccessPanel);
        checkinSuccessPanel.setLayout(checkinSuccessPanelLayout);
        checkinSuccessPanelLayout.setHorizontalGroup(
            checkinSuccessPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .add(checkinSuccessPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(checkinSuccessPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .add(jLabelFullname2))
                .addContainerGap())
        );
        checkinSuccessPanelLayout.setVerticalGroup(
            checkinSuccessPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(checkinSuccessPanelLayout.createSequentialGroup()
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jLabelFullname2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(userFoundPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(checkinSuccessPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(userFoundPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(checkinSuccessPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jButtonUserCheckinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUserCheckinActionPerformed
		// TODO add your handling code here:
		
		userCheckin.setEnabled(false);
		
		Enrollment selectedEnrollment = (Enrollment)userCourses.getSelectedItem();
		Attendance attendance = new Attendance();
		attendance.setEnrollmentId(selectedEnrollment.getId());
		if(attendance.create()){
			
			Datetime currentDate = new Datetime();
			
			String success = user.getFullName()+" is succesvol ingecheckt in de vestiging "+selectedEnrollment.getBranch().getCity()+" op "+currentDate.format("dd MM yyyy")+" om "+currentDate.format("hh:mm");
			checkinSuccessPanel.setVisible(true);
			checkinSuccessText.setVisible(true);
			checkinSuccessText.setText(success);
		}
	}//GEN-LAST:event_jButtonUserCheckinActionPerformed

	private void jButtonSearchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchUserActionPerformed
            // TODO add your handling code here:
            searchUser();
	}//GEN-LAST:event_jButtonSearchUserActionPerformed

	private void userCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userCoursesActionPerformed
            // TODO add your handling code here:
	}//GEN-LAST:event_userCoursesActionPerformed

	private void userCoursesCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_userCoursesCaretPositionChanged
            // TODO add your handling code here:
	}//GEN-LAST:event_userCoursesCaretPositionChanged

    private void jPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldKeyReleased
        // If this is an enter, search user
        // The KeyCode for an enter is 10

        if (evt.getKeyCode() == 10) {
            searchUser();
        }
    }//GEN-LAST:event_jPasswordFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel checkinSuccessPanel;
    private javax.swing.JTextPane checkinSuccessText;
    private javax.swing.JButton jButtonSearchUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCustomerBirthdate;
    private javax.swing.JLabel jLabelCustomerGender;
    private javax.swing.JLabel jLabelCustomerName;
    private javax.swing.JLabel jLabelFullname;
    private javax.swing.JLabel jLabelFullname1;
    private javax.swing.JLabel jLabelFullname2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable recentPayments;
    private javax.swing.JButton userCheckin;
    private javax.swing.JComboBox userCourses;
    private javax.swing.JPanel userFoundPanel;
    private javax.swing.JTextField userSearchField;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the enrollmentModel
     */
    public DefaultTableModel getEnrollmentModel() {
        return enrollmentModel;
    }

    /**
     * @param enrollmentModel the enrollmentModel to set
     */
    public void setEnrollmentModel(DefaultTableModel enrollmentModel) {
        this.enrollmentModel = enrollmentModel;
    }

    /**
     * @return the purchaseModel
     */
    public DefaultTableModel getPurchaseModel() {
        return purchaseModel;
    }

    /**
     * @param purchaseModel the purchaseModel to set
     */
    public void setPurchaseModel(DefaultTableModel purchaseModel) {
        this.purchaseModel = purchaseModel;
    }
}
