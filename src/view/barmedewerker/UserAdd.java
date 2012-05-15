/*
 * UserAdd.java
 *
 * Created on May 10, 2012, 10:52:48 PM
 */
package view.barmedewerker;

import javax.swing.ButtonGroup;
import javax.swing.Popup;
import main.Session;
import model.User;


/**
 * This is the class for the view to add a user
 *
 * @author vm
 */
public class UserAdd extends javax.swing.JPanel {

	private User user = new User();
	
    /** Creates new form Profile */
    public UserAdd() {
        initComponents();
    }
	
	
	public void setUserData() {
		User user = Session.get().getLoggedInUser();

		String username = jTextFieldUsername.getText();
		user.setUsername(username);

		String firstname = jTextFieldFirstname.getText();
		user.setFirstname(firstname);

		String subname = jTextFieldSubname.getText();
		user.setSubname(subname);

		String lastname = jTextFieldLastname.getText();
		user.setUsername(username);

		String birthdate = jTextFieldBirthdate.getText();
		user.setUsername(username);

		String street = jTextFieldStreet.getText();
		user.setStreet(street);

		String city = jTextFieldCity.getText();
		user.setCity(city);

		String housenumber = jTextFieldStreetnumber.getText();
		user.setHousenumber(housenumber);

		String postcode = jTextFieldPostcode.getText();
		user.setPostcode(postcode);

		String phonenumber = jTextFieldPhonenumber.getText();
		user.setPhonenumber(phonenumber);

		String mobilenumber = jTextFieldMobilenumber.getText();
		user.setMobilenumber(mobilenumber);

		String email = jTextFieldEmail.getText();
		user.setEmail(email);

		ButtonGroup groupCategory = new ButtonGroup();
		groupCategory.add(jRadioButtonYouth);
		groupCategory.add(jRadioButtonAdult);
		groupCategory.add(jRadioButtonSenior);
		
		ButtonGroup groupGender = new ButtonGroup();
		groupGender.add(jRadioButtonGenderMale);
		groupGender.add(jRadioButtonGenderFemale);
		
		ButtonGroup groupTime = new ButtonGroup();
		groupTime.add(jRadioButtonDayTime);
		groupTime.add(jRadioButtonFullTime);
		
		
		if (jRadioButtonGenderMale.isSelected() == true) {
			user.setGender(true);
		} else {
			user.setGender(false);
		}
		 
		if (jRadioButtonYouth.isSelected() == true) {
			user.setCategory("youth");
		} else if (jRadioButtonAdult.isSelected() == true) {
			user.setCategory("adult");
		}else {
			user.setCategory("senior");
		}
				
		user.save();
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jTextFieldFirstname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldSubname = new javax.swing.JTextField();
        jTextFieldLastname = new javax.swing.JTextField();
        jTextFieldBirthdate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRadioButtonGenderMale = new javax.swing.JRadioButton();
        jRadioButtonGenderFemale = new javax.swing.JRadioButton();
        jTextFieldStreet = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldCity = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldStreetnumber = new javax.swing.JTextField();
        jTextFieldPostcode = new javax.swing.JTextField();
        jTextFieldPhonenumber = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldMobilenumber = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        profileSaveButton = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButtonYouth = new javax.swing.JRadioButton();
        jRadioButtonAdult = new javax.swing.JRadioButton();
        jRadioButtonSenior = new javax.swing.JRadioButton();
        jRadioButtonDayTime = new javax.swing.JRadioButton();
        jRadioButtonFullTime = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jLabel4.setText("Gebruiker Aanmaken");

        jLabel5.setText("Klantnummer");

        jLabel6.setText("Gebruikersnaam");

        jTextFieldFirstname.setText("voornaam");

        jLabel7.setText("Volledige naam");

        jTextFieldSubname.setText("tussenvoegsel");

        jTextFieldLastname.setText("achternaam");

        jLabel8.setText("Geboortedatum");

        jLabel9.setText("Geslacht");

        jRadioButtonGenderMale.setText("Man");

        jRadioButtonGenderFemale.setText("Vrouw");

        jLabel10.setText("Straat en huisnr");

        jLabel11.setText("Woonplaats / postcode");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jLabel12.setText("Contactgegevens");

        jLabel13.setText("Telefoonnummer");

        jLabel14.setText("Mobielnummer");

        jLabel15.setText("E-mailadres");

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jLabel16.setText("Wachtwoord");

        jLabel17.setText("Wachtwoord herhalen");

        jLabel18.setText("Wachtwoord");

        profileSaveButton.setText("Opslaan");
        profileSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileSaveButtonActionPerformed(evt);
            }
        });

        jPasswordField1.setText("jPasswordField1");

        jPasswordField2.setText("jPasswordField2");

        jLabel2.setText("Category");

        jLabel3.setText("Abbonnement");

        jRadioButtonYouth.setText("Jeugd (< 18)");

        jRadioButtonAdult.setText("Volwassenen");

        jRadioButtonSenior.setText("Senioren");

        jRadioButtonDayTime.setText("Alleen overdag (weekdagen)");

        jRadioButtonFullTime.setText("Volledig");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setText("Primaire locatie");

        jTextFieldID.setEnabled(false);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(profileSaveButton)
                    .add(jLabel16)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel11)
                            .add(jLabel10)
                            .add(jLabel13)
                            .add(jLabel14)
                            .add(jLabel15)
                            .add(jLabel18)
                            .add(jLabel17))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPasswordField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                            .add(jPasswordField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                            .add(jTextFieldEmail, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                            .add(jTextFieldMobilenumber, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jTextFieldCity)
                                    .add(jTextFieldStreet, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 216, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jTextFieldPostcode, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextFieldStreetnumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jTextFieldPhonenumber, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel6)
                                    .add(63, 63, 63))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jLabel7)
                                        .add(jLabel8))
                                    .add(68, 68, 68)))
                            .add(jLabel9)
                            .add(jLabel5)
                            .add(jLabel4)
                            .add(jLabel2)
                            .add(jLabel3)
                            .add(jLabel12)
                            .add(jLabel19))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jTextFieldBirthdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jTextFieldFirstname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(layout.createSequentialGroup()
                                                .add(jRadioButtonGenderMale)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jRadioButtonGenderFemale)))
                                        .add(10, 10, 10)
                                        .add(jTextFieldSubname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldID)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldUsername, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextFieldLastname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jRadioButtonYouth)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(jRadioButtonAdult))
                                    .add(jRadioButtonDayTime))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jRadioButtonFullTime)
                                    .add(jRadioButtonSenior)))
                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 158, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 19, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jTextFieldID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jTextFieldUsername, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jTextFieldFirstname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldSubname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldLastname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(jTextFieldBirthdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jRadioButtonGenderFemale)
                    .add(jRadioButtonGenderMale)
                    .add(jLabel9))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jRadioButtonYouth)
                    .add(jRadioButtonAdult)
                    .add(jRadioButtonSenior))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jRadioButtonDayTime)
                    .add(jRadioButtonFullTime))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel19)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 8, Short.MAX_VALUE)
                        .add(jLabel12))
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel10)
                    .add(jTextFieldStreet, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldStreetnumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(jTextFieldCity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldPostcode, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel13)
                    .add(jTextFieldPhonenumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel14)
                    .add(jTextFieldMobilenumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel15)
                    .add(jTextFieldEmail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jLabel16)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel18)
                    .add(jPasswordField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel17)
                    .add(jPasswordField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(profileSaveButton))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void profileSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileSaveButtonActionPerformed
        // TODO
       User user = Session.get().getLoggedInUser();
		
		
		
        //Check if a @ sign is in the emailadress field
        int emailcheck = jTextFieldEmail.getText().indexOf('@');
        
       String password1 = new String(jPasswordField1.getPassword());
		String password2 = new String(jPasswordField2.getPassword());
		Boolean passwordequal = password1.equals(password2);

		//If emailcheck returns <0 then the @ sign is missing, Message Dialog will be shown
		if (emailcheck < 0) {

			new Popup().showError("U vergeet een '@'-teken in uw e-mailadres te plaatsen.\n"
					+ "Probeer het nogmaals alstublieft.");
		}
		//passwordequal check doesn't work, the SOUT works, it gives false or true but it will not show a panel. (Jeroen)
		// That was because you checked with passwordequal = false. You needed the ==, or none at all.
		if (!passwordequal) {
			new Popup().showError("Uw wachtwoorden komen niet overeen. \nProbeer het nogmaals alstublieft.");
		}
        
    }//GEN-LAST:event_profileSaveButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JRadioButton jRadioButtonAdult;
    private javax.swing.JRadioButton jRadioButtonDayTime;
    private javax.swing.JRadioButton jRadioButtonFullTime;
    private javax.swing.JRadioButton jRadioButtonGenderFemale;
    private javax.swing.JRadioButton jRadioButtonGenderMale;
    private javax.swing.JRadioButton jRadioButtonSenior;
    private javax.swing.JRadioButton jRadioButtonYouth;
    private javax.swing.JTextField jTextFieldBirthdate;
    private javax.swing.JTextField jTextFieldCity;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFirstname;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldLastname;
    private javax.swing.JTextField jTextFieldMobilenumber;
    private javax.swing.JTextField jTextFieldPhonenumber;
    private javax.swing.JTextField jTextFieldPostcode;
    private javax.swing.JTextField jTextFieldStreet;
    private javax.swing.JTextField jTextFieldStreetnumber;
    private javax.swing.JTextField jTextFieldSubname;
    private javax.swing.JTextField jTextFieldUsername;
    private javax.swing.JButton profileSaveButton;
    // End of variables declaration//GEN-END:variables
	
	
	
}
