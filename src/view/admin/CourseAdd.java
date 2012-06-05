/*
 * CourseAdd.java
 *
 * Created on May 18, 2012, 8:53:21 PM
 */
package view.admin;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import main.Application;
import model.Branch;
import model.Subscription;
import view.popups.ErrorPopup;
import view.popups.SuccessPopup;

/**
 * This is the view for adding a course.
 * When a course is created members can subscribe to the course.
 * @author vm
 */
public class CourseAdd extends javax.swing.JPanel {

	/**
	 * The type of course, either a single course or on multiple days.
	 */
	String type;
	/**
	 * The day(s) when the course will be given
	 */
	String days;
	/**
	 * The instantiate the object Subscription
	 */
	Subscription subscription = new Subscription();
	
	/**
	 * All available branches where the subscription could take place
	 */
	ArrayList<Branch> branches;

	/** Creates new form CourseAdd */
	public CourseAdd() {
		initComponents();
		selectionCheck();
		
		//Remove the items
		jComboBoxAgeGroup.removeAllItems();

		//Add items to the combobox
		jComboBoxAgeGroup.addItem("< 16");
		jComboBoxAgeGroup.addItem("16 - 18");
		jComboBoxAgeGroup.addItem("18 - 65");
		jComboBoxAgeGroup.addItem("65 +");
		
		this.branches = Branch.readAll();
		
		jComboBoxBranch.removeAllItems();
		for( Branch branch : this.branches )
			jComboBoxBranch.addItem( branch.getCity() );
		

		//Group the buttons so there can be only one button seleted in the group
		ButtonGroup buttongroupType = new ButtonGroup();
		buttongroupType.add(jRadioButtonCourseType1);
		buttongroupType.add(jRadioButtonCourseType2);

		ButtonGroup buttongroupGender = new ButtonGroup();
		buttongroupGender.add(jRadioButtonMale);
		buttongroupGender.add(jRadioButtonFemale);
		buttongroupGender.add(jRadioButtonMixed);

	}

	/**
	 * Sets all the variables from the view, this way the Subscription model
	 * can use this info to do a insert or update on table in the database.
	 * @author vm
	 */
	public void setSubscriptionData() {
		// The name to set into the Subscription model			
		String name = jTextFieldCourseName.getText();
		subscription.setTitle(name);

		// The start- and endtime to set into the Subscription model
		String startTime = jTextFieldStartTime.getText();
		String endTime = jTextFieldEndTime.getText();
		String startDate = jTextFieldStartDate.getText();
		String endDate = jTextFieldEndDate.getText();

		// Get the data for type.
		if (jRadioButtonCourseType1.isSelected()) {
			type = jRadioButtonCourseType1.getText();
		} else {
			type = jRadioButtonCourseType2.getText() + " : " + days;
		}
		// Description to set in the Subscription model
		String description = jTextAreaDescription.getText();
		subscription.setDescription(description);
		
		// Set the type of the course
		if (jRadioButtonCourseType2.isSelected()) {
			subscription.setType('d');
		} else {
			subscription.setType('l');
		}

		// The gender to set into the Subcription model
		if (jRadioButtonMale.isSelected()) {
			subscription.setGender('m');
		} else if (jRadioButtonFemale.isSelected()) {
			subscription.setGender('f');
		} else {
			//Don't set the gender.
		}

		// The age selection to set into the Subscription model
		if (jComboBoxAgeGroup.getSelectedIndex() == 0) {
			subscription.setMinimumAge(0);
			subscription.setMaximumAge(16);
		} else if (jComboBoxAgeGroup.getSelectedIndex() == 1) {
			subscription.setMinimumAge(16);
			subscription.setMaximumAge(18);
		} else if (jComboBoxAgeGroup.getSelectedIndex() == 2) {
			subscription.setMinimumAge(18);
			subscription.setMaximumAge(65);
		} else if (jComboBoxAgeGroup.getSelectedIndex() == 3) {
			subscription.setMinimumAge(65);
			subscription.setMaximumAge(150);
		}
		
		// Set the days
		subscription.setDays( this.getDays() );
		
		// Set the starting time
		subscription.setStartTime( startTime );
		
		// Set the end time
		subscription.setEndTime( endTime );
		
		// Set the starting date
		subscription.setStartDate( startDate );
		
		// Set the end date
		subscription.setEndDate( endDate );

		// Set the price
		double price = Double.parseDouble(jTextFieldPrice.getText());
		subscription.setPrice(price);
		
		this.subscription.setBranchId( new Branch( jComboBoxBranch.getSelectedItem().toString() ).getId() );
	}
	
	/*
	 * Check on selection of the JRadioButtonCourseType2
	 * @author vm
	 * 
	 */
	public void selectionCheck() {

		if (jRadioButtonCourseType2.isSelected()) {
			enableCheckBox();
		} else {
			disableCheckBox();
		}
	}

	/**
	 * Enable the days checkboxes
	 */
	public void enableCheckBox() {
		//Enable all the checkboxes
		jCheckBoxMonday.setEnabled(true);
		jCheckBoxTuesday.setEnabled(true);
		jCheckBoxWednesday.setEnabled(true);
		jCheckBoxThursday.setEnabled(true);
		jCheckBoxFriday.setEnabled(true);
		jCheckBoxSaturday.setEnabled(true);
		jCheckBoxSunday.setEnabled(true);

	}

	/**
	 * Disable the days checkboxes
	 */
	public void disableCheckBox() {
		//Disable all the checkboxes
		jCheckBoxMonday.setEnabled(false);
		jCheckBoxTuesday.setEnabled(false);
		jCheckBoxWednesday.setEnabled(false);
		jCheckBoxThursday.setEnabled(false);
		jCheckBoxFriday.setEnabled(false);
		jCheckBoxSaturday.setEnabled(false);
		jCheckBoxSunday.setEnabled(false);
	}

	/**
	 * Retrieve an ArrayList of days the subscription is given
	 * @return ArrayList Every day the subscription is given
	 */
	public ArrayList getDays() {
		ArrayList days = new ArrayList();
		
		if( jCheckBoxMonday.isSelected() )
			days.add( "Maandag" );
		if( jCheckBoxTuesday.isSelected() )
			days.add( "Dinsdag" );
		if( jCheckBoxWednesday.isSelected() )
			days.add( "Woensdag" );
		if( jCheckBoxThursday.isSelected() )
			days.add( "Donderdag" );
		if( jCheckBoxFriday.isSelected() )
			days.add( "Vrijdag" );
		if( jCheckBoxSaturday.isSelected() )
			days.add( "Zaterdag" );
		if( jCheckBoxSunday.isSelected() )
			days.add( "Zondag" );
		
		return days;
	}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabelCourseName = new javax.swing.JLabel();
                jLabelCourseType = new javax.swing.JLabel();
                jLabelCourseStartDate = new javax.swing.JLabel();
                jLabelCourseEndDate = new javax.swing.JLabel();
                jLabelCourseStart = new javax.swing.JLabel();
                jLabelCourseEnd = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                jLabelFocusGroup = new javax.swing.JLabel();
                jLabelAgeGroup = new javax.swing.JLabel();
                jLabelDescription = new javax.swing.JLabel();
                jRadioButtonCourseType1 = new javax.swing.JRadioButton();
                jRadioButtonCourseType2 = new javax.swing.JRadioButton();
                jCheckBoxMonday = new javax.swing.JCheckBox();
                jCheckBoxTuesday = new javax.swing.JCheckBox();
                jCheckBoxWednesday = new javax.swing.JCheckBox();
                jCheckBoxThursday = new javax.swing.JCheckBox();
                jCheckBoxFriday = new javax.swing.JCheckBox();
                jCheckBoxSaturday = new javax.swing.JCheckBox();
                jCheckBoxSunday = new javax.swing.JCheckBox();
                jTextFieldStartDate = new javax.swing.JTextField();
                jTextFieldEndDate = new javax.swing.JTextField();
                jTextFieldStartTime = new javax.swing.JTextField();
                jTextFieldEndTime = new javax.swing.JTextField();
                jRadioButtonMale = new javax.swing.JRadioButton();
                jRadioButtonFemale = new javax.swing.JRadioButton();
                jRadioButtonMixed = new javax.swing.JRadioButton();
                jComboBoxAgeGroup = new javax.swing.JComboBox();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTextAreaDescription = new javax.swing.JTextArea();
                jTextFieldCourseName = new javax.swing.JTextField();
                jLabel1 = new javax.swing.JLabel();
                jButtonSave = new javax.swing.JButton();
                jLabel2 = new javax.swing.JLabel();
                jTextFieldPrice = new javax.swing.JTextField();
                jLabel6 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jComboBoxBranch = new javax.swing.JComboBox();
                jLabel8 = new javax.swing.JLabel();

                jLabelCourseName.setText("Naam *");

                jLabelCourseType.setText("Type");

                jLabelCourseStartDate.setText("Start datum");

                jLabelCourseEndDate.setText("Eind datum");

                jLabelCourseStart.setText("Begin cursus");

                jLabelCourseEnd.setText("Eind cursus");

                jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
                jLabel7.setText("Omschrijving");

                jLabelFocusGroup.setText("Doelgroep");

                jLabelAgeGroup.setText("Leeftijds groep");

                jLabelDescription.setText("Omschrijving");

                jRadioButtonCourseType1.setText("Los");

                jRadioButtonCourseType2.setText("Dagelijks");
                jRadioButtonCourseType2.addChangeListener(new javax.swing.event.ChangeListener() {
                        public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                jRadioButtonCourseType2StateChanged(evt);
                        }
                });

                jCheckBoxMonday.setText("Ma");
                jCheckBoxMonday.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jCheckBoxMondayActionPerformed(evt);
                        }
                });

                jCheckBoxTuesday.setText("Di");

                jCheckBoxWednesday.setText("Wo");

                jCheckBoxThursday.setText("Do");

                jCheckBoxFriday.setText("Vr");

                jCheckBoxSaturday.setText("Za");

                jCheckBoxSunday.setText("Zo");

                jRadioButtonMale.setText("Man");

                jRadioButtonFemale.setText("Vrouw");

                jRadioButtonMixed.setText("Gecombineerd");

                jComboBoxAgeGroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                jTextAreaDescription.setColumns(20);
                jTextAreaDescription.setRows(5);
                jScrollPane1.setViewportView(jTextAreaDescription);

                jLabel1.setFont(new java.awt.Font("Arial", 1, 24));
                jLabel1.setText("Cursus Aanmaken");

                jButtonSave.setText("Opslaan");
                jButtonSave.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonSaveActionPerformed(evt);
                        }
                });

                jLabel2.setText("Prijs");

                jLabel6.setForeground(new java.awt.Color(51, 51, 51));
                jLabel6.setText("Voorbeeld: 20:00");

                jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                jLabel5.setText("Voorbeeld: 19:00");

                jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                jLabel4.setText("Voorbeeld: 2012-05-29");

                jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                jLabel3.setText("Voorbeeld: 2012-01-29");

                jComboBoxBranch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                jLabel8.setText("Locatie");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(251, 251, 251)
                                                .addComponent(jCheckBoxMonday)
                                                .addComponent(jCheckBoxTuesday)
                                                .addGap(10, 10, 10)
                                                .addComponent(jCheckBoxWednesday)
                                                .addComponent(jCheckBoxThursday)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBoxFriday)
                                                .addGap(7, 7, 7)
                                                .addComponent(jCheckBoxSaturday)
                                                .addGap(7, 7, 7)
                                                .addComponent(jCheckBoxSunday))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelCourseStartDate)
                                                        .addComponent(jLabelCourseEndDate)
                                                        .addComponent(jLabelCourseStart)
                                                        .addComponent(jLabelCourseEnd)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabelCourseType)
                                                        .addComponent(jLabelCourseName)
                                                        .addComponent(jLabel8))
                                                .addGap(129, 129, 129)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jComboBoxBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButtonCourseType1)
                                                        .addComponent(jRadioButtonCourseType2)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTextFieldStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextFieldEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextFieldEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel5)
                                                                        .addComponent(jLabel6)))))
                                        .addComponent(jLabel7)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelFocusGroup)
                                                        .addComponent(jLabelAgeGroup)
                                                        .addComponent(jLabelDescription))
                                                .addGap(115, 115, 115)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jComboBoxAgeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jButtonSave)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jRadioButtonMale)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jRadioButtonFemale)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jRadioButtonMixed)))))
                                .addContainerGap(28, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelCourseName))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButtonCourseType1)
                                        .addComponent(jLabelCourseType))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonCourseType2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBoxSaturday)
                                        .addComponent(jCheckBoxSunday)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jCheckBoxMonday)
                                                        .addComponent(jCheckBoxTuesday)
                                                        .addComponent(jCheckBoxWednesday)
                                                        .addComponent(jCheckBoxThursday)
                                                        .addComponent(jCheckBoxFriday))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelCourseStartDate)
                                                        .addComponent(jLabel3))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextFieldEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelCourseEndDate)
                                                        .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextFieldStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelCourseStart)
                                                        .addComponent(jLabel5))
                                                .addGap(5, 5, 5)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextFieldEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelCourseEnd)
                                                        .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel8))
                                        .addComponent(jComboBoxBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelFocusGroup)
                                        .addComponent(jRadioButtonFemale)
                                        .addComponent(jRadioButtonMixed)
                                        .addComponent(jRadioButtonMale))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelAgeGroup)
                                        .addComponent(jComboBoxAgeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelDescription)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSave)
                                .addGap(17, 17, 17))
                );
        }// </editor-fold>//GEN-END:initComponents

	private void jRadioButtonCourseType2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonCourseType2StateChanged
		//When the RadioButton's state is chaged go to selectionCheck
		selectionCheck();
	}//GEN-LAST:event_jRadioButtonCourseType2StateChanged

	public boolean isEmpty() {
		
		if( jTextFieldCourseName.getText().isEmpty() )
			return true;
		
		if( jRadioButtonCourseType1.isSelected() == false && jRadioButtonCourseType2.isSelected() == false )
			return true;
		
		if( jTextFieldStartDate.getText().isEmpty() )
			return true;
		
		if( jTextFieldEndDate.getText().isEmpty() )
			return true;
		
		if( jTextFieldStartTime.getText().isEmpty() )
			return true;
		
		if( jTextFieldEndTime.getText().isEmpty() )
			return true;
		
		if( jTextFieldPrice.getText().isEmpty() )
			return true;
		
		if( jRadioButtonMale.isSelected() == false && jRadioButtonFemale.isSelected() == false && jRadioButtonMixed.isSelected() == false )
			return true;
		
		if( jTextAreaDescription.getText().isEmpty() )
			return true;
		
		return false;
	}
	
	private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
		if( this.isEmpty() ) {
			Application.getInstance().showPopup(new ErrorPopup("Niet alle verplichte velden zijn ingevuld."));
		}else{
			// Save the subscription data into the database with the button
			setSubscriptionData();
			subscription.create();
			
			Application.getInstance().showPanel(new view.medewerker.CoursesOverview());
		}
	}//GEN-LAST:event_jButtonSaveActionPerformed

private void jCheckBoxMondayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMondayActionPerformed
	// TODO add your handling code here:
}//GEN-LAST:event_jCheckBoxMondayActionPerformed
        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton jButtonSave;
        private javax.swing.JCheckBox jCheckBoxFriday;
        private javax.swing.JCheckBox jCheckBoxMonday;
        private javax.swing.JCheckBox jCheckBoxSaturday;
        private javax.swing.JCheckBox jCheckBoxSunday;
        private javax.swing.JCheckBox jCheckBoxThursday;
        private javax.swing.JCheckBox jCheckBoxTuesday;
        private javax.swing.JCheckBox jCheckBoxWednesday;
        private javax.swing.JComboBox jComboBoxAgeGroup;
        private javax.swing.JComboBox jComboBoxBranch;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabelAgeGroup;
        private javax.swing.JLabel jLabelCourseEnd;
        private javax.swing.JLabel jLabelCourseEndDate;
        private javax.swing.JLabel jLabelCourseName;
        private javax.swing.JLabel jLabelCourseStart;
        private javax.swing.JLabel jLabelCourseStartDate;
        private javax.swing.JLabel jLabelCourseType;
        private javax.swing.JLabel jLabelDescription;
        private javax.swing.JLabel jLabelFocusGroup;
        private javax.swing.JRadioButton jRadioButtonCourseType1;
        private javax.swing.JRadioButton jRadioButtonCourseType2;
        private javax.swing.JRadioButton jRadioButtonFemale;
        private javax.swing.JRadioButton jRadioButtonMale;
        private javax.swing.JRadioButton jRadioButtonMixed;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextArea jTextAreaDescription;
        private javax.swing.JTextField jTextFieldCourseName;
        private javax.swing.JTextField jTextFieldEndDate;
        private javax.swing.JTextField jTextFieldEndTime;
        private javax.swing.JTextField jTextFieldPrice;
        private javax.swing.JTextField jTextFieldStartDate;
        private javax.swing.JTextField jTextFieldStartTime;
        // End of variables declaration//GEN-END:variables
}
