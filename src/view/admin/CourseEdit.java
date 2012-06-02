/*
 * CourseEdit.java
 *
 * Created on May 18, 2012, 8:53:21 PM
 */
package view.admin;

import helper.Datetime;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import main.Application;
import model.Subscription;
import view.popups.SuccessPopup;

/**
 * This is the view for editing a course
 * @author mennowolvers
 */
public class CourseEdit extends javax.swing.JPanel {

	String days;
	String type;
	Subscription subscription;

	/** Creates new form CourseAdd */
	public CourseEdit( int id ) {
		initComponents();
		selectionCheck();
		
		this.subscription = new Subscription( id );

		jComboBoxAgeGroup.removeAllItems();
		jComboBoxAgeGroup.addItem("< 16");
		jComboBoxAgeGroup.addItem("16 - 18");
		jComboBoxAgeGroup.addItem("18 - 65");
		jComboBoxAgeGroup.addItem("65 +");

		ButtonGroup buttongroupType = new ButtonGroup();
		buttongroupType.add(jRadioButtonCourseType1);
		buttongroupType.add(jRadioButtonCourseType2);

		ButtonGroup buttongroupGender = new ButtonGroup();
		buttongroupGender.add(jRadioButtonMale);
		buttongroupGender.add(jRadioButtonFemale);
		buttongroupGender.add(jRadioButtonMixed);
		
		this.populate();

	}
	
	public void populate()
	{
		jTextFieldCourseName.setText( this.subscription.getTitle() );
		
		if( this.subscription.getType() == 'd' )
			jRadioButtonCourseType2.setSelected(true);
		else
			jRadioButtonCourseType1.setSelected(true);
		
		jTextFieldStartTime.setText( String.valueOf( this.subscription.getStartTime() ) );
		jTextFieldEndTime.setText( String.valueOf( this.subscription.getEndTime() ) );
		jTextFieldStartDate.setText( String.valueOf( this.subscription.getStartDate() ) );
		jTextFieldEndDate.setText( String.valueOf( this.subscription.getEndDate() ) );
		
		if( this.subscription.getGender() == 'm' )
			jRadioButtonMale.setSelected(true);
		else if( this.subscription.getGender() == 'f' )
			jRadioButtonMale.setSelected(true);
		else
			jRadioButtonMixed.setSelected(true);
		
		jTextFieldPrice.setText( String.valueOf( this.subscription.getPrice() ) );
		
		if( this.subscription.getMaximumAge() <= 16 )
			jComboBoxAgeGroup.setSelectedIndex(0);
		else if( this.subscription.getMaximumAge() <= 18 )
			jComboBoxAgeGroup.setSelectedIndex(1);
		else if( this.subscription.getMaximumAge() <= 65 )
			jComboBoxAgeGroup.setSelectedIndex(2);
		else if( this.subscription.getMaximumAge() <= 150 )
			jComboBoxAgeGroup.setSelectedIndex(3);
		
		if( subscription.getDays() != null && subscription.getDays().length > 0 ) {
			for( String day : subscription.getDays() ) {
				if( day.equals( "Maandag" ) )
					jCheckBoxMonday.setSelected(true);
				else if( day.equals( "Dinsdag" ) )
					jCheckBoxTuesday.setSelected(true);
				else if( day.equals( "Woensdag" ) )
					jCheckBoxWednesday.setSelected(true);
				else if( day.equals( "Donderdag" ) )
					jCheckBoxThursday.setSelected(true);
				else if( day.equals( "Vrijdag" ) )
					jCheckBoxFriday.setSelected(true);
				else if( day.equals( "Zaterdag" ) )
					jCheckBoxSaturday.setSelected(true);
				else if( day.equals( "Zondag" ) )
					jCheckBoxSunday.setSelected(true);
			}
		}
		
		jTextAreaDescription.setText( this.subscription.getDescription() );
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

	public void setSubscriptionData() {
		
			
			String name = jTextFieldCourseName.getText();
			subscription.setTitle(name);
			
			String startTime = jTextFieldStartTime.getText();
			String endTime = jTextFieldEndTime.getText();
			String startDate = jTextFieldStartDate.getText();
			String endDate = jTextFieldEndDate.getText();
			
		
			if(jRadioButtonCourseType1.isSelected()){
				type = jRadioButtonCourseType1.getText();
			} else {
				type = jRadioButtonCourseType2.getText() +" : " + days;
			}

			String description = jTextAreaDescription.getText();
			subscription.setDescription(description);
			
			if( jRadioButtonCourseType2.isSelected() )
				subscription.setType( 'd' );
			else
				subscription.setType( 'l' );
						

			if (jRadioButtonMale.isSelected()) {
				subscription.setGender('m');
			} else if (jRadioButtonFemale.isSelected()) {
				subscription.setGender('f');
			} else {
				//Don't set the gender.
			}
			
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
			
			subscription.setDays( this.getDays() );
			subscription.setStartTime( startTime );
			subscription.setEndTime( endTime );
			
			double price = Double.parseDouble(jTextFieldPrice.getText());
			subscription.setPrice(price);
					
	}

	/** This method is called from within the constructor to
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
        jCheckBoxMonday = new javax.swing.JCheckBox();
        jCheckBoxTuesday = new javax.swing.JCheckBox();
        jCheckBoxThursday = new javax.swing.JCheckBox();
        jCheckBoxWednesday = new javax.swing.JCheckBox();
        jCheckBoxFriday = new javax.swing.JCheckBox();
        jCheckBoxSaturday = new javax.swing.JCheckBox();
        jCheckBoxSunday = new javax.swing.JCheckBox();

        jLabelCourseName.setText("Naam *");

        jLabelCourseType.setText("Type");

        jLabelCourseStartDate.setText("Start datum");

        jLabelCourseEndDate.setText("Eind datum");

        jLabelCourseStart.setText("Begin cursus");

        jLabelCourseEnd.setText("Eind cursus");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 16));
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
        jRadioButtonCourseType2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCourseType2ActionPerformed(evt);
            }
        });

        jRadioButtonMale.setText("Man");

        jRadioButtonFemale.setText("Vrouw");

        jRadioButtonMixed.setText("Gecombineerd");

        jComboBoxAgeGroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescription);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Cursus wijzigen");

        jButtonSave.setText("Opslaan");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jLabel2.setText("Prijs");

        jCheckBoxMonday.setText("Ma");
        jCheckBoxMonday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMondayActionPerformed(evt);
            }
        });

        jCheckBoxTuesday.setText("Di");

        jCheckBoxThursday.setText("Do");

        jCheckBoxWednesday.setText("Wo");

        jCheckBoxFriday.setText("Vr");

        jCheckBoxSaturday.setText("Za");

        jCheckBoxSunday.setText("Zo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabelCourseName))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabelCourseType))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jCheckBoxMonday)
                        .addComponent(jCheckBoxTuesday)
                        .addGap(7, 7, 7)
                        .addComponent(jCheckBoxWednesday)
                        .addComponent(jCheckBoxThursday)
                        .addGap(7, 7, 7)
                        .addComponent(jCheckBoxFriday)
                        .addGap(7, 7, 7)
                        .addComponent(jCheckBoxSaturday)
                        .addGap(7, 7, 7)
                        .addComponent(jCheckBoxSunday))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(650, 650, 650)
                                .addComponent(jButtonSave))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCourseStartDate)
                                    .addComponent(jLabelCourseEndDate)
                                    .addComponent(jLabelCourseStart)
                                    .addComponent(jLabelCourseEnd)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabelDescription)
                                    .addComponent(jLabelAgeGroup)
                                    .addComponent(jLabelFocusGroup))
                                .addGap(114, 114, 114)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButtonMale)
                                        .addGap(2, 2, 2)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(jRadioButtonMixed))
                                            .addComponent(jRadioButtonFemale)))
                                    .addComponent(jComboBoxAgeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButtonCourseType2)
                                    .addComponent(jTextFieldStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButtonCourseType1)
                                    .addComponent(jTextFieldCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCourseName)
                    .addComponent(jTextFieldCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCourseType)
                    .addComponent(jRadioButtonCourseType1))
                .addGap(3, 3, 3)
                .addComponent(jRadioButtonCourseType2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxMonday)
                    .addComponent(jCheckBoxTuesday)
                    .addComponent(jCheckBoxWednesday)
                    .addComponent(jCheckBoxThursday)
                    .addComponent(jCheckBoxFriday)
                    .addComponent(jCheckBoxSaturday)
                    .addComponent(jCheckBoxSunday))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabelCourseStartDate))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabelCourseEndDate))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCourseStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCourseEnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(8, 8, 8)
                        .addComponent(jLabelFocusGroup)
                        .addGap(2, 2, 2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonMale)
                            .addComponent(jRadioButtonMixed)
                            .addComponent(jRadioButtonFemale))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabelAgeGroup))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxAgeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSave)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jRadioButtonCourseType2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonCourseType2StateChanged
		//When the RadioButton's state is chaged go to selectionCheck
		selectionCheck();

	}//GEN-LAST:event_jRadioButtonCourseType2StateChanged

	private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
		// TODO add your handling code here:
		setSubscriptionData();
		subscription.edit();
		
		Application.getInstance().showPanel(new view.medewerker.CoursesOverview());
		
		
	}//GEN-LAST:event_jButtonSaveActionPerformed

private void jRadioButtonCourseType2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCourseType2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jRadioButtonCourseType2ActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
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
