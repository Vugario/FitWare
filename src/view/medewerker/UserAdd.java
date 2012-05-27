/*
 * UserAdd.java
 *
 * Created on May 10, 2012, 10:52:48 PM
 */
package view.medewerker;

import java.util.ArrayList;
import javax.media.j3d.View;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import main.Application;
import main.Session;
import model.Branch;
import model.Subscription;
import model.User;
import view.popups.ErrorPopup;

/**
 * This is the class for the view to add a user
 *
 * @author vm
 */
public final class UserAdd extends javax.swing.JPanel {

	private User user = new User();
	private User userSession = Session.get().getLoggedInUser();

	/** Creates new form Profile */
	public UserAdd() {

		initComponents();

		// Empty the combo box
		jComboBox1.removeAllItems();

		//Set the radiobuttons in a group
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

		ButtonGroup groupUserType = new ButtonGroup();
		groupUserType.add(jRadioButtonAdmin);
		groupUserType.add(jRadioButtonBarmedewerker);
		groupUserType.add(jRadioButtonMember);

		//jComboBox1.setModel(new DefaultComboBoxModel());
		//uncomment this one when the sql works
		addBranchToComboBox();


		jRadioButtonAdmin.setVisible(false);
		jRadioButtonBarmedewerker.setVisible(false);
		jRadioButtonMember.setVisible(false);


		if (userSession.getRole().getTitle().equals("admin")) {
			jRadioButtonAdmin.setVisible(true);
			jRadioButtonBarmedewerker.setVisible(true);
			jRadioButtonMember.setVisible(true);

		}

	}

	public void addBranchToComboBox() {
		ArrayList<Branch> branches = Branch.readAll();

		//DefaultComboBoxModel comboModel = (DefaultComboBoxModel) jComboBox1.getModel();
		for (int i = 0; i < branches.size(); i++) {
			Branch branch = branches.get(i);
			jComboBox1.addItem(branch.getCity());
		}
	}

	public void setUserData() {

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

		// Default is role ID 1 (members)
		int role_id = 1;

		// Only admin users may change the user type
		if (userSession.getRole().getTitle().equals("admin")) {
			if (jRadioButtonAdmin.isSelected()) {
				//TODOSet the userType Admin
				role_id = 3;

			} else if (jRadioButtonBarmedewerker.isSelected()) {
				//TODOSet the userType member
				role_id = 2;
			} else if (jRadioButtonMember.isSelected()) {
				//TODOSet the userType member
				role_id = 1;
			}
		}
		user.setRoleId(role_id);


		Subscription subscription = new Subscription();

		/*if (jRadioButtonYouth.isSelected()) {
		subscription.setCategory("youth");
		} else if (jRadioButtonAdult.isSelected()) {
		subscription.setCategory("adult");
		} else {
		subscription.setCategory("senior");
		}
		
		if(jRadioButtonDayTime.isSelected){
		subscription.set
		}*/

	}

	public JComboBox getjComboBox1() {
		return jComboBox1;
	}

	public void setjComboBox1(JComboBox jComboBox1) {
		this.jComboBox1 = jComboBox1;
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
        jLabel1 = new javax.swing.JLabel();
        jTextFieldAccountNumber = new javax.swing.JTextField();
        jTextFieldTNV = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jRadioButtonMember = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jRadioButtonAdmin = new javax.swing.JRadioButton();
        jRadioButtonBarmedewerker = new javax.swing.JRadioButton();

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setText("Gebruiker Aanmaken");

        jLabel6.setText("Gebruikersnaam");

        jTextFieldFirstname.setText("voornaam");
        jTextFieldFirstname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldFirstnameFocusGained(evt);
            }
        });

        jLabel7.setText("Volledige naam");

        jTextFieldSubname.setText("tussenvoegsel");
        jTextFieldSubname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSubnameFocusGained(evt);
            }
        });

        jTextFieldLastname.setText("achternaam");
        jTextFieldLastname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldLastnameFocusGained(evt);
            }
        });

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

        jLabel1.setText("Rekeningnummer");

        jLabel20.setText("Ter name van");

        jRadioButtonMember.setText("Lid");

        jLabel21.setText("Type gebruiker");

        jRadioButtonAdmin.setText("Administratief medewerker");

        jRadioButtonBarmedewerker.setText("Barmedewerker");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
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
                            .add(jLabel4)
                            .add(jLabel2)
                            .add(jLabel3)
                            .add(jLabel19)
                            .add(jLabel21))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jRadioButtonMember)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jRadioButtonBarmedewerker)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jRadioButtonAdmin))
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
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldUsername, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 158, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(profileSaveButton)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel18)
                            .add(jLabel17))
                        .add(22, 22, 22)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPasswordField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                            .add(jPasswordField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)))
                    .add(jLabel16)
                    .add(layout.createSequentialGroup()
                        .add(jLabel12)
                        .add(454, 454, 454))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel11)
                            .add(jLabel10)
                            .add(jLabel13)
                            .add(jLabel14)
                            .add(jLabel15)
                            .add(jLabel1))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jTextFieldEmail, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                            .add(jTextFieldMobilenumber, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jTextFieldCity)
                                    .add(jTextFieldStreet, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 216, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jTextFieldPostcode, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextFieldStreetnumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jTextFieldPhonenumber, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(jTextFieldAccountNumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 60, Short.MAX_VALUE)
                                .add(jLabel20)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextFieldTNV, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 177, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel4)
                .add(52, 52, 52)
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
                    .add(jLabel19)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel21)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jRadioButtonMember)
                        .add(jRadioButtonBarmedewerker)
                        .add(jRadioButtonAdmin)))
                .add(18, 18, 18)
                .add(jLabel12)
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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldAccountNumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(jTextFieldTNV, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel20))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
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
		
		//Check if a @ sign is in the emailadress field
		int emailcheck = jTextFieldEmail.getText().indexOf('@');

		String password1 = new String(jPasswordField1.getPassword());
		String password2 = new String(jPasswordField2.getPassword());

		if (password1.equals(password2)) {
			user.setPassword(password1);
		}

		//If emailcheck returns <0 then the @ sign is missing, Message Dialog will be shown
		if (emailcheck < 0) {

			Application.getInstance().showPopup(new ErrorPopup(
					"U vergeet een '@'-teken in uw e-mailadres te plaatsen.\n"
					+ "Probeer het nogmaals alstublieft."));
		}

		this.setUserData();
		
		user.create();
		
		Application.getInstance().showPanel(new view.medewerker.UserOverview());

    }//GEN-LAST:event_profileSaveButtonActionPerformed

	private void jTextFieldFirstnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldFirstnameFocusGained
		// Set the textfield empty on focus
		jTextFieldFirstname.setText(null);

	}//GEN-LAST:event_jTextFieldFirstnameFocusGained

	private void jTextFieldSubnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSubnameFocusGained
		// Set the textfield empty on focus
		jTextFieldSubname.setText(null);
	}//GEN-LAST:event_jTextFieldSubnameFocusGained

	private void jTextFieldLastnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLastnameFocusGained
		// Set the textfield empty on focus
		jTextFieldLastname.setText(null);
	}//GEN-LAST:event_jTextFieldLastnameFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JRadioButton jRadioButtonAdmin;
    private javax.swing.JRadioButton jRadioButtonAdult;
    private javax.swing.JRadioButton jRadioButtonBarmedewerker;
    private javax.swing.JRadioButton jRadioButtonDayTime;
    private javax.swing.JRadioButton jRadioButtonFullTime;
    private javax.swing.JRadioButton jRadioButtonGenderFemale;
    private javax.swing.JRadioButton jRadioButtonGenderMale;
    private javax.swing.JRadioButton jRadioButtonMember;
    private javax.swing.JRadioButton jRadioButtonSenior;
    private javax.swing.JRadioButton jRadioButtonYouth;
    private javax.swing.JTextField jTextFieldAccountNumber;
    private javax.swing.JTextField jTextFieldBirthdate;
    private javax.swing.JTextField jTextFieldCity;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFirstname;
    private javax.swing.JTextField jTextFieldLastname;
    private javax.swing.JTextField jTextFieldMobilenumber;
    private javax.swing.JTextField jTextFieldPhonenumber;
    private javax.swing.JTextField jTextFieldPostcode;
    private javax.swing.JTextField jTextFieldStreet;
    private javax.swing.JTextField jTextFieldStreetnumber;
    private javax.swing.JTextField jTextFieldSubname;
    private javax.swing.JTextField jTextFieldTNV;
    private javax.swing.JTextField jTextFieldUsername;
    private javax.swing.JButton profileSaveButton;
    // End of variables declaration//GEN-END:variables
}
