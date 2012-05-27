/*
 * Profile.java
 *
 * Created on May 7, 2012, 10:52:48 AM
 */
package view.member;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import main.Application;
import main.Session;
import model.User;
import view.popups.ErrorPopup;

/**
 *
 * @author Daan
 */
public class Profile extends javax.swing.JPanel {

	boolean passwordChange;
	
	public boolean isPasswordChange() {
		return passwordChange;
	}

	public void setPasswordChange(boolean passwordChange) {
		this.passwordChange = passwordChange;
	}
	
	
	
	private	User sessionUser = new User().readUser(Session.get().getLoggedInUser().getId());

	
	/** Creates new form Profile */
	public Profile() {
		initComponents();
		loadUserData();
		
		jLabel17.setVisible(false);
		jLabel18.setVisible(false);

		jPasswordField1.setVisible(false);
		jPasswordField2.setVisible(false);
	}

	public void loadUserData() {
		
		jTextFieldId.setText(Integer.toString(sessionUser.getId()));
		jTextFieldUsername.setText(sessionUser.getUsername());
		jTextFieldFirstname.setText(sessionUser.getFirstname());
		jTextFieldSubname.setText(sessionUser.getSubname());
		jTextFieldLastname.setText(sessionUser.getLastname());
		
		
		if(sessionUser.getBirthdate() != null){
			
			System.out.println(sessionUser.getBirthdate());
			
			helper.Datetime datetime = new helper.Datetime(sessionUser.getBirthdate());


			jTextFieldBirthdate.setText(datetime.format("yyyy-MM-dd"));
		}
		jTextFieldStreet.setText(sessionUser.getStreet());
		jTextFieldCity.setText(sessionUser.getCity());
		jTextFieldStreetnumber.setText(sessionUser.getHousenumber());
		jTextFieldPostcode.setText(sessionUser.getPostcode());
		jTextFieldEmail.setText(sessionUser.getEmail());
		jTextFieldPhonenumber.setText(sessionUser.getPhonenumber());
		jTextFieldMobilenumber.setText(sessionUser.getMobilenumber());

		ButtonGroup group = new ButtonGroup();
		group.add(jRadioButtonGenderMale);
		group.add(jRadioButtonGenderFemale);

		if (sessionUser.getGender()) {
			jRadioButtonGenderMale.setSelected(true);
		} else {
			jRadioButtonGenderFemale.setSelected(true);
		}

	}

	public void setUserData(User user) {
		
		String username = jTextFieldUsername.getText();
		user.setUsername(username);

		String firstname = jTextFieldFirstname.getText();
		user.setFirstname(firstname);

		String subname = jTextFieldSubname.getText();
		user.setSubname(subname);

		String lastname = jTextFieldLastname.getText();
		user.setLastname(lastname);

		String birthdate = jTextFieldBirthdate.getText();
		helper.Datetime datetime = new helper.Datetime(birthdate+" 00:00:00");	
		user.setBirthdate(datetime.timestamp());

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

		if (jRadioButtonGenderMale.isSelected() == true) {
			user.setGender(true);
		} else {
			user.setGender(false);
		}
		
		String id = jTextFieldId.getText();
		user.setId(Integer.parseInt(id));

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
        jTextFieldId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        passwordChanged = new javax.swing.JCheckBox();

        setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setText("Mijn gegevens");

        jLabel5.setText("Klantnummer");

        jLabel6.setText("Gebruikersnaam");

        jLabel7.setText("Volledige naam");

        jLabel8.setText("Geboortedatum");

        jLabel9.setText("Geslacht");

        jRadioButtonGenderMale.setText("Man");

        jRadioButtonGenderFemale.setText("Vrouw");

        jLabel10.setText("Straat en huisnr");

        jLabel11.setText("Woonplaats / postcode");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel12.setText("Contactgegevens");

        jLabel13.setText("Telefoonnummer");

        jLabel14.setText("Mobielnummer");

        jLabel15.setText("E-mailadres");

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel16.setText("Wachtwoord");

        jLabel17.setText("Wachtwoord herhalen");

        jLabel18.setText("Wachtwoord");

        profileSaveButton.setText("Opslaan");
        profileSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileSaveButtonActionPerformed(evt);
            }
        });

        jTextFieldId.setEnabled(false);

        jLabel1.setText("Wachtwoord aanpassen?");

        passwordChanged.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordChangedActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel12)
                    .add(jLabel16)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel11)
                            .add(jLabel10)
                            .add(jLabel13)
                            .add(jLabel14)
                            .add(jLabel15)
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
                            .add(jLabel18)
                            .add(jLabel17)
                            .add(jLabel1))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(passwordChanged)
                            .add(jTextFieldBirthdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(jTextFieldFirstname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jTextFieldSubname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(layout.createSequentialGroup()
                                            .add(jRadioButtonGenderMale)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jRadioButtonGenderFemale)))
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldId)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldUsername, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextFieldLastname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPasswordField2)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPasswordField1)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldEmail)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldMobilenumber)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(jTextFieldCity)
                                        .add(jTextFieldStreet, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jTextFieldPostcode, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(jTextFieldStreetnumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldPhonenumber)
                                .add(profileSaveButton)))))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jTextFieldId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(60, 60, 60)
                        .add(jLabel12))
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jRadioButtonGenderFemale)
                            .add(jRadioButtonGenderMale)
                            .add(jLabel9))))
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
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel16)
                        .add(14, 14, 14)
                        .add(jLabel1))
                    .add(passwordChanged))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel18)
                    .add(jPasswordField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel17)
                    .add(jPasswordField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(profileSaveButton)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        passwordChanged.getAccessibleContext().setAccessibleName("passwordChanged");
    }// </editor-fold>//GEN-END:initComponents

    private void profileSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileSaveButtonActionPerformed
		
		//initiate new user to set new values
		User user = new User();
		
		setUserData(user);

		//Check if a @ sign is in the emailadress field
		int emailcheck = jTextFieldEmail.getText().indexOf('@');
		
		boolean passwordChanged1 = passwordChanged.isSelected();
		
		if(passwordChanged.isSelected()){
			String password1 = new String(jPasswordField1.getPassword());
			String password2 = new String(jPasswordField2.getPassword());
			Boolean passwordequal = password1.equals(password2);
			user.setPassword(password2);
			
			if (!passwordequal) {
				Application.getInstance().showPopup(new ErrorPopup(
					"Uw wachtwoorden komen niet overeen. \nProbeer het nogmaals alstublieft."));
			}
		}
		
		

		//If emailcheck returns <0 then the @ sign is missing, Message Dialog will be shown
		if (emailcheck < 0) {

			Application.getInstance().showPopup(new ErrorPopup(
					"U vergeet een '@'-teken in uw e-mailadres te plaatsen.\n"
					+ "Probeer het nogmaals alstublieft."));
		}
		
		
		if(!user.update()){
			Application.getInstance().showPopup(new ErrorPopup(
					"gegevens zijn niet goed opgeslagen. \nProbeer het nogmaals alstublieft."));
		}
		
		Application.getInstance().showPanel(this);
		
    }//GEN-LAST:event_profileSaveButtonActionPerformed

	private void passwordChangedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordChangedActionPerformed
		
		if(passwordChanged.isSelected()){
			jLabel17.setVisible(true);
			jLabel18.setVisible(true);
			
			jPasswordField1.setVisible(true);
			jPasswordField2.setVisible(true);
			passwordChange = true;
		}else{
			jLabel17.setVisible(false);
			jLabel18.setVisible(false);
			
			jPasswordField1.setVisible(false);
			jPasswordField2.setVisible(false);
		}
	}//GEN-LAST:event_passwordChangedActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JRadioButton jRadioButtonGenderFemale;
    private javax.swing.JRadioButton jRadioButtonGenderMale;
    private javax.swing.JTextField jTextFieldBirthdate;
    private javax.swing.JTextField jTextFieldCity;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFirstname;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldLastname;
    private javax.swing.JTextField jTextFieldMobilenumber;
    private javax.swing.JTextField jTextFieldPhonenumber;
    private javax.swing.JTextField jTextFieldPostcode;
    private javax.swing.JTextField jTextFieldStreet;
    private javax.swing.JTextField jTextFieldStreetnumber;
    private javax.swing.JTextField jTextFieldSubname;
    private javax.swing.JTextField jTextFieldUsername;
    private javax.swing.JCheckBox passwordChanged;
    private javax.swing.JButton profileSaveButton;
    // End of variables declaration//GEN-END:variables
}


/*
 * 
		//If emailcheck returns <0 then the @ sign is missing, Message Dialog will be shown
		if (emailcheck < 0) {

			new ErrorPopup().showError("U vergeet een '@'-teken in uw e-mailadres te plaatsen.\n"
					+ "Probeer het nogmaals alstublieft.");
		}
		//passwordequal check doesn't work, the SOUT works, it gives false or true but it will not show a panel. (Jeroen)
		// That was because you checked with passwordequal = false. You needed the ==, or none at all.
		if (!passwordequal) {
			new ErrorPopup().showError("Uw wachtwoorden komen niet overeen. \nProbeer het nogmaals alstublieft.");
		}
 */
