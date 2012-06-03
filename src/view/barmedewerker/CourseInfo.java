/**
 * CourseInfo.java
 *
 * Created on May 10, 2012, 10:21:26 PM
 */
package view.barmedewerker;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import main.Application;
import helper.ExceptionHandler;
import model.Enrollment;
import model.Subscription;
import model.User;
import view.popups.ErrorPopup;
import view.popups.SuccessPopup;

/**
 * This is the view to inquiry detailed information about a course.
 *
 * @author mennowolvers
 */
public class CourseInfo extends javax.swing.JPanel {
	
	/**
	 * subscription is the variable of the Subscription class
	 */
	private Subscription subscription;
	/**
	 * model is the variable used to fill and search the jTable's 
	 * information
	 */
	private DefaultTableModel model;
	
	/**
	 * row is the variable used to get the selected row
	 */
	private ListSelectionModel row;
	/**
	 * userId is a integer that is used to give a user identifier
	 */
	private int userId;

	/** Creates new form CourseInfo */
	public CourseInfo() {
		initComponents();
	}
	
	/**
	 * This method gets the course info of a particular course (subscription)
	 * @param subscriptionId is the identifier of a course (subscription)
	 */
	public CourseInfo(int subscriptionId) {
		initComponents();

		this.subscription = new Subscription(subscriptionId);
		this.model = (DefaultTableModel) jTableUsers.getModel();

		this.render();
	}

	/**
	 * This method is used to 
	 * @todo 
	 */
	public void render() {
		jLabelName.setText(this.subscription.getTitle());
		jLabelGender.setText("Man");
		jLabelAge.setText(String.valueOf(this.subscription.getMinimumAge()));
		jLabelDays.setText(CourseInfo.implode( this.subscription.getDays(), ", " ) );
		jLabelDuration.setText(String.valueOf(this.subscription.getStartTime()) + " - " + String.valueOf(this.subscription.getEndTime()));
		jLabelDescription.setText(this.subscription.getDescription());
		
		// Empty results
		jLabelUser.setText( "" );
		jBtnSubmit.setVisible( false );
		
		// Age range
		if( this.subscription.getMaximumAge() <= 16 )
			jLabelAge.setText( "< 16" );
		else if( this.subscription.getMaximumAge() <= 18 )
			jLabelAge.setText( "16 - 18" );
		else if( this.subscription.getMaximumAge() <= 65 )
			jLabelAge.setText( "18 - 65" );
		else if( this.subscription.getMaximumAge() <= 150 )
			jLabelAge.setText( "65+" );

		// Enrollments
		ArrayList<User> users = Enrollment.readBySubscriptionId(this.subscription.getId());

		for (User user : users) {
			if (user.getFullName() != "" && user.getFirstname() != null) {
				this.model.insertRow(0, new Object[]{user.getId(), user.getFullName()});
			}
		}

		// Make a selection listener
		this.row = jTableUsers.getSelectionModel();
		this.row.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

	}
	
	/**
	 * This method searches the user you type in the textfield. The user is 
	 * searched to add him to the course.
	 */
	public void searchUser() {
		// Empty results
		jLabelUser.setText( "" );
		jBtnSubmit.setVisible( false );
		
		// Search a user and return a result
		User user = new User();

		try {
			int id = Integer.parseInt(jTextId.getText());
			user.readUser(id);
			if (user.getId() > 0) {
				this.userId = user.getId();
				// Set the label
				jLabelUser.setText(user.getFullName());
				jBtnSubmit.setVisible( true );
			}
		} catch (Exception ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}



	}
	/**
	 * This method makes visible which days the course is held.
	 * @param ary array of days
	 * @param delim delimiter is used to add a , and a space to separate the days
	 * @return 
	 */
	public static String implode(String[] ary, String delim) {
	    String out = "";
	    if( ary != null && ary.length > 0 ) {
		    for(int i=0; i<ary.length; i++) {
			if(i!=0) { out += delim; }
			out += ary[i];
		    }
	    }
	    return out;
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
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabelDuration = new javax.swing.JLabel();
                jLabelDays = new javax.swing.JLabel();
                jLabelGender = new javax.swing.JLabel();
                jLabelName = new javax.swing.JLabel();
                jSeparator1 = new javax.swing.JSeparator();
                jLabel6 = new javax.swing.JLabel();
                jLabelAge = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                jTextId = new javax.swing.JTextField();
                jBtnSearch = new javax.swing.JButton();
                jSeparator2 = new javax.swing.JSeparator();
                jBtnSubmit = new javax.swing.JButton();
                jLabel8 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTableUsers = new javax.swing.JTable();
                jBtnEdit = new javax.swing.JButton();
                jBtnEdit1 = new javax.swing.JButton();
                jScrollPane2 = new javax.swing.JScrollPane();
                jLabelDescription = new javax.swing.JTextArea();
                jScrollPane3 = new javax.swing.JScrollPane();
                jLabelUser = new javax.swing.JTextArea();
                jLabel9 = new javax.swing.JLabel();

                jLabel1.setText("Naam");

                jLabel2.setText("Geslacht");

                jLabel3.setText("Duratie");

                jLabel4.setText("Dagen");

                jLabel5.setText("Omschrijving");

                jLabelDuration.setText("Duratie");

                jLabelDays.setText("Dagen");

                jLabelGender.setText("Geslacht");

                jLabelName.setText("Naam");

                jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

                jLabel6.setText("Leeftijdscategorie");

                jLabelAge.setText("Leeftijdscategorie");

                jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 13));
                jLabel7.setText("Deelnemer");

                jTextId.setText("Klantnummer");
                jTextId.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jTextIdMouseClicked(evt);
                        }
                });
                jTextId.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jTextIdActionPerformed(evt);
                        }
                });
                jTextId.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                jTextIdKeyPressed(evt);
                        }
                });

                jBtnSearch.setText("Zoeken");
                jBtnSearch.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jBtnSearchActionPerformed(evt);
                        }
                });

                jBtnSubmit.setText("Aanmelden");
                jBtnSubmit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jBtnSubmitActionPerformed(evt);
                        }
                });

                jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 13));
                jLabel8.setText("Deelnemers");

                jTableUsers.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Klantnr", "Deelnemer"
                        }
                ) {
                        boolean[] canEdit = new boolean [] {
                                false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jTableUsers.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jTableUsersMouseClicked(evt);
                        }
                });
                jScrollPane1.setViewportView(jTableUsers);

                jBtnEdit.setText("Wijzigen");
                jBtnEdit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jBtnEditActionPerformed(evt);
                        }
                });

                jBtnEdit1.setText("Verwijderen");
                jBtnEdit1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jBtnEdit1ActionPerformed(evt);
                        }
                });

                jScrollPane2.setBorder(null);

                jLabelDescription.setBackground(new java.awt.Color(238, 238, 238));
                jLabelDescription.setColumns(20);
                jLabelDescription.setRows(4);
                jLabelDescription.setWrapStyleWord(true);
                jLabelDescription.setBorder(null);
                jLabelDescription.setCaretColor(new java.awt.Color(255, 255, 255));
                jLabelDescription.setEnabled(false);
                jScrollPane2.setViewportView(jLabelDescription);

                jScrollPane3.setBorder(null);

                jLabelUser.setBackground(new java.awt.Color(238, 238, 238));
                jLabelUser.setColumns(17);
                jLabelUser.setRows(2);
                jLabelUser.setTabSize(4);
                jLabelUser.setWrapStyleWord(true);
                jLabelUser.setBorder(null);
                jLabelUser.setCaretColor(new java.awt.Color(255, 255, 255));
                jLabelUser.setEnabled(false);
                jScrollPane3.setViewportView(jLabelUser);

                jLabel9.setFont(new java.awt.Font("Arial", 1, 24));
                jLabel9.setText("Cursus Informatie");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel1)
                                                                                        .addComponent(jLabel2)
                                                                                        .addComponent(jLabel4)
                                                                                        .addComponent(jLabel3)
                                                                                        .addComponent(jLabel6))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabelName)
                                                                                        .addComponent(jLabelGender)
                                                                                        .addComponent(jLabelDays)
                                                                                        .addComponent(jLabelDuration)
                                                                                        .addComponent(jLabelAge))
                                                                                .addGap(61, 61, 61))
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                        .addComponent(jTextId, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addComponent(jBtnSearch)))))
                                                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                                                .addComponent(jBtnSubmit))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel7)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel8)
                                                                .addGap(76, 76, 76)
                                                                .addComponent(jBtnEdit)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jBtnEdit1)
                                                                .addGap(17, 17, 17))
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)))
                                        .addComponent(jLabel9))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(36, 36, 36)
                                                                                .addComponent(jLabel8))
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jBtnEdit)
                                                                                .addComponent(jBtnEdit1)))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabelName))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabelGender))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabelDays))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabelDuration))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabelAge))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jBtnSearch))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jBtnSubmit)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
                );
        }// </editor-fold>//GEN-END:initComponents

private void jBtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSearchActionPerformed
	this.searchUser();
}//GEN-LAST:event_jBtnSearchActionPerformed

private void jBtnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSubmitActionPerformed
	Enrollment enrollment = new Enrollment();
	
	enrollment.readEnrollmentBySubscriptionIdAndUserId( this.subscription.getId(), this.userId );
	
	if( enrollment.isEnrolled() == false ) {
		enrollment.subscribe(this.subscription.getId(), this.userId);


		User user = new User();
		user.readUser(this.userId);

		this.model.insertRow(0, new Object[]{user.getId(), user.getFullName()});

		Application.getInstance().showPopup(new SuccessPopup("Deelnemer " + user.getFullName() + " is ingeschreven."));
	}else{
		Application.getInstance().showPopup(new ErrorPopup("De deelnemer is al ingeschreven."));
	}
}//GEN-LAST:event_jBtnSubmitActionPerformed

private void jTableUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsersMouseClicked
	if (evt.getClickCount() >= 2) {
		// Double clicked!

		int response = JOptionPane.showConfirmDialog(null, "Weet je zeker dat je deze deelnemer wilt uitschrijven?", "Deelnemer uitschrijven", JOptionPane.YES_NO_OPTION);
		
		if( response == 0 ) {
			// Get the currently selected subscription
			int rowNumber = jTableUsers.getSelectedRow();
			int user_id = (Integer) model.getValueAt(rowNumber, 0);

			Enrollment enrollment = new Enrollment();
			enrollment.unsubscribe(this.subscription.getId(), user_id);
			
			this.model.removeRow(rowNumber);

			Application.getInstance().showPopup(new SuccessPopup("De deelnemer is succesvol uitgeschreven."));
		}
	}
}//GEN-LAST:event_jTableUsersMouseClicked

private void jBtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditActionPerformed
	Application.getInstance().showPanel( new view.admin.CourseEdit( this.subscription.getId() ) );
}//GEN-LAST:event_jBtnEditActionPerformed

private void jBtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEdit1ActionPerformed
	int response = JOptionPane.showConfirmDialog(null, "Weet je zeker dat je de cursus wilt verwijderen?", "Cursus verwijderen", JOptionPane.YES_NO_OPTION);
		
	if( response == 0 ) {
		// Get the currently selected subscription
		this.subscription.delete();
		
		Application.getInstance().showPanel( new view.medewerker.CoursesOverview() );
	}
}//GEN-LAST:event_jBtnEdit1ActionPerformed

private void jTextIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextIdActionPerformed
	jTextId.setText("");
}//GEN-LAST:event_jTextIdActionPerformed

private void jTextIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextIdKeyPressed
	jTextId.setText("");
}//GEN-LAST:event_jTextIdKeyPressed

private void jTextIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextIdMouseClicked
	jTextId.setText("");
}//GEN-LAST:event_jTextIdMouseClicked

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton jBtnEdit;
        private javax.swing.JButton jBtnEdit1;
        private javax.swing.JButton jBtnSearch;
        private javax.swing.JButton jBtnSubmit;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JLabel jLabelAge;
        private javax.swing.JLabel jLabelDays;
        private javax.swing.JTextArea jLabelDescription;
        private javax.swing.JLabel jLabelDuration;
        private javax.swing.JLabel jLabelGender;
        private javax.swing.JLabel jLabelName;
        private javax.swing.JTextArea jLabelUser;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JSeparator jSeparator1;
        private javax.swing.JSeparator jSeparator2;
        private javax.swing.JTable jTableUsers;
        private javax.swing.JTextField jTextId;
        // End of variables declaration//GEN-END:variables
}
