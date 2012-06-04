/*
 * UserAdd.java
 *
 * Created on May 10, 2012, 10:52:48 PM
 */
package view.medewerker;

import java.util.ArrayList;
import javax.swing.ButtonGroup;
import main.Application;

import model.Branch;
import model.Enrollment;
import model.Subscription;
import model.User;
import view.popups.ErrorPopup;


/**
 * This is the class for the view to add a user
 *
 * @author vm
 */
public class UserModify extends javax.swing.JPanel {
	Subscription subscription = new Subscription();
	private User user = new User();
	//private User sessionuser = Session.get().getLoggedInUser();
	boolean passwordChange;
	
	public boolean isPasswordChange() {
		return passwordChange;
	}

	public void setPasswordChange(boolean passwordChange) {
		this.passwordChange = passwordChange;
	}
	
    /** Creates new form UserModify */
    public UserModify(int id) {
		
        initComponents();
		user.readUser(id);
		loadUserData();
		jLabel17.setVisible(false);
		jLabel18.setVisible(false);
		jPasswordField1.setVisible(false);
		jPasswordField2.setVisible(false);
		
		addSubscriptionToComboBox();
    }
	
	
	  public void addSubscriptionToComboBox() {
        jComboBox2.removeAllItems();
        ArrayList<Subscription> items = Subscription.readAllMonthly();
        //Fill the ComboBox with all the monthly subscriptions
        for (int i = 0; i < items.size(); i++) {
            Subscription item = items.get(i);
            jComboBox2.addItem(item.getTitle());
        }
	  }

	/*
	  /**
	 * This method fills the ComboBox with all the possible branches
	 *
	public void addBranchToComboBox() {
		ArrayList<Branch> branches = Branch.readAll();

		//DefaultComboBoxModel comboModel = (DefaultComboBoxModel) jComboBox1.getModel();
		for (int i = 0; i < branches.size(); i++) {
			Branch branch = branches.get(i);
			jComboBox1.addItem(branch.getCity());
		}
	}*/
	
	/**
	 * This method loads all the userdata into the form.
	 */
	public final void loadUserData() {
		
		jTextFieldId.setText(Integer.toString(user.getId()));
		jTextFieldUsername.setText(user.getUsername());
		jTextFieldFirstname.setText(user.getFirstname());
		jTextFieldSubname.setText(user.getSubname());
		jTextFieldLastname.setText(user.getLastname());
		
		
		if(user.getBirthdate() != null){
			helper.Datetime datetime = new helper.Datetime(user.getBirthdate());


			jTextFieldBirthdate.setText(datetime.format("yyyy-MM-dd"));
		}
		jTextFieldStreet.setText(user.getStreet());
		jTextFieldCity.setText(user.getCity());
		jTextFieldStreetnumber.setText(user.getHousenumber());
		jTextFieldPostcode.setText(user.getPostcode());
		jTextFieldEmail.setText(user.getEmail());
		jTextFieldPhonenumber.setText(user.getPhonenumber());
		jTextFieldMobilenumber.setText(user.getMobilenumber());
		jTextFieldBankAccount.setText(Long.toString(user.getBankaccount()));
		jTextFieldBaccountname.setText(user.getBaccountname());

		//This makes sure there is only one Radiobutton selected
		ButtonGroup group = new ButtonGroup();
		group.add(jRadioButtonGenderMale);
		group.add(jRadioButtonGenderFemale);

		switch(user.getRoleId()){
			case 1:
				jRadioButtonMember.setSelected(true);
				break;
			case 2:
				jRadioButtonBarmedewerker.setSelected(true);
				break;
			case 3:
				jRadioButtonAdmin.setSelected(true);
				break;
		}

		
		if (user.getGender()) {
			jRadioButtonGenderMale.setSelected(true);
		} else {
			jRadioButtonGenderFemale.setSelected(true);
		}
		

	}

	/**
	 * This method sets all the data from the form to the User model.
	 */
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
		
		String bankaccount = jTextFieldBankAccount.getText();
		user.setBankaccount(Integer.parseInt(bankaccount));

		String email = jTextFieldEmail.getText();
		user.setEmail(email);

		if (jRadioButtonGenderMale.isSelected() == true) {
			user.setGender(true);
		} else {
			user.setGender(false);
		}
		
		String title = (String) jComboBox2.getSelectedItem();
        System.out.println(title);
        subscription.setTitle(title);

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
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        profileSaveButton = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButtonDisable = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        passwordChanged = new javax.swing.JCheckBox();
        jRadioButtonGenderFemale = new javax.swing.JRadioButton();
        jRadioButtonBarmedewerker = new javax.swing.JRadioButton();
        jTextFieldStreet = new javax.swing.JTextField();
        jRadioButtonAdmin = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jRadioButtonGenderMale = new javax.swing.JRadioButton();
        jTextFieldBirthdate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldSubname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldLastname = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldStreetnumber = new javax.swing.JTextField();
        jTextFieldPostcode = new javax.swing.JTextField();
        jTextFieldPhonenumber = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldMobilenumber = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldBaccountname = new javax.swing.JTextField();
        jTextFieldFirstname = new javax.swing.JTextField();
        jTextFieldUsername = new javax.swing.JTextField();
        jRadioButtonMember = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldBankAccount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCity = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel4.setText("Gebruiker Wijzigen");

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
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
        });

        jPasswordField2.setText("jPasswordField2");
        jPasswordField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusGained(evt);
            }
        });

        jButtonDisable.setText("Non-actief");

        jLabel21.setText("Wachtwoord aanpassen?");

        passwordChanged.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordChangedActionPerformed(evt);
            }
        });

        jRadioButtonGenderFemale.setText("Vrouw");

        jRadioButtonBarmedewerker.setText("Barmedewerker");

        jRadioButtonAdmin.setText("Administratief medewerker");

        jLabel9.setText("Geslacht");

        jLabel22.setText("Type gebruiker");

        jRadioButtonGenderMale.setText("Man");

        jLabel8.setText("Geboortedatum");

        jTextFieldSubname.setText("tussenvoegsel");

        jLabel3.setText("Abbonnement");

        jTextFieldLastname.setText("achternaam");

        jLabel15.setText("E-mailadres");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jLabel12.setText("Contactgegevens");

        jLabel13.setText("Telefoonnummer");

        jLabel14.setText("Mobielnummer");

        jLabel20.setText("Ten name van");

        jLabel7.setText("Volledige naam");

        jTextFieldFirstname.setText("voornaam");

        jRadioButtonMember.setText("Lid");

        jLabel6.setText("Gebruikersnaam");

        jLabel1.setText("Rekeningnummer");

        jLabel11.setText("Woonplaats / postcode");

        jLabel10.setText("Straat en huisnr");

        jLabel5.setText("Klantnummer");

        jTextFieldId.setEnabled(false);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jButtonDisable)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(profileSaveButton))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel4)
                            .add(jLabel18)
                            .add(jLabel17)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel10)
                                    .add(jLabel11)
                                    .add(jLabel13)
                                    .add(jLabel14)
                                    .add(jLabel15)
                                    .add(jLabel1)
                                    .add(jLabel9)
                                    .add(jLabel8)
                                    .add(jLabel7)
                                    .add(jLabel6)
                                    .add(jLabel3))
                                .add(71, 71, 71)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(passwordChanged)
                                    .add(jTextFieldBirthdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(layout.createSequentialGroup()
                                        .add(jRadioButtonGenderMale)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jRadioButtonGenderFemale))
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldId)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldUsername, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                                    .add(layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldBankAccount)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldEmail)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldMobilenumber)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldPhonenumber)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldCity)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldStreet, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 245, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createSequentialGroup()
                                                .add(jLabel20)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jTextFieldBaccountname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 177, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                            .add(jTextFieldPostcode, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jTextFieldStreetnumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPasswordField1)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPasswordField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jComboBox2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .add(jTextFieldFirstname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(4, 4, 4)
                                            .add(jTextFieldSubname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jTextFieldLastname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .add(jRadioButtonMember)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jRadioButtonBarmedewerker)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jRadioButtonAdmin)))))
                            .add(jLabel16)
                            .add(jLabel21)
                            .add(jLabel12)
                            .add(jLabel5))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jButtonDisable)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(profileSaveButton))
                    .add(layout.createSequentialGroup()
                        .add(jLabel12)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 631, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel5)
                    .add(jLabel22))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 30, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jTextFieldId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldUsername, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldFirstname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7)
                    .add(jTextFieldSubname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldLastname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldBirthdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jRadioButtonGenderFemale)
                    .add(jRadioButtonGenderMale)
                    .add(jLabel9))
                .add(14, 14, 14)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel22)
                    .add(jRadioButtonMember)
                    .add(jRadioButtonBarmedewerker)
                    .add(jRadioButtonAdmin))
                .add(36, 36, 36)
                .add(jLabel12)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldStreet, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldStreetnumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldCity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldPostcode, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel11))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldPhonenumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel13))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldMobilenumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldEmail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel15))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldBankAccount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldBaccountname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel20)
                    .add(jLabel1))
                .add(27, 27, 27)
                .add(jLabel16)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel21)
                    .add(passwordChanged))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel18)
                    .add(jPasswordField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(12, 12, 12)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel17)
                    .add(jPasswordField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(profileSaveButton)
                    .add(jButtonDisable)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void profileSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileSaveButtonActionPerformed

        //Check if a @ sign is in the emailadress field
       int emailcheck = jTextFieldEmail.getText().indexOf('@');
        
		//If emailcheck returns <0 then the @ sign is missing, Message Dialog will be shown
		if (emailcheck < 0) {

			Application.getInstance().showPopup(new ErrorPopup(
					"U vergeet een '@'-teken in uw e-mailadres te plaatsen.\n"
					+ "Probeer het nogmaals alstublieft."));
		}

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
		
		
		setUserData();
                
                if (user.checkUserExist()) {
            // User already exists
            Application.getInstance().showPopup(new ErrorPopup(
                    "Deze gebruikersnaam is al in gebruik.\n"
                    + "Kies een andere naam."));
            return;
        }
		
		if(!user.update()){
			Application.getInstance().showPopup(new ErrorPopup(
					"Gegevens zijn niet goed opgeslagen. \nProbeer het nogmaals alstublieft."));
		}
		Enrollment enrollment = new Enrollment();
       
        enrollment.subscribe(subscription.readByTitle().getId(), user.getId());
		
		Application.getInstance().showPanel(new view.medewerker.UserOverview());
		
    }//GEN-LAST:event_profileSaveButtonActionPerformed

	private void passwordChangedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordChangedActionPerformed
		// If the password changed checkbox is selected then show the password boxes
		
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

	private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
		// Empty the password field on focus gain
		jPasswordField1.setText(null);
	}//GEN-LAST:event_jPasswordField1FocusGained

	private void jPasswordField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusGained
		// Empty the password field on focus gain
		jPasswordField2.setText(null);
	}//GEN-LAST:event_jPasswordField2FocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDisable;
    private javax.swing.JComboBox jComboBox2;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JRadioButton jRadioButtonAdmin;
    private javax.swing.JRadioButton jRadioButtonBarmedewerker;
    private javax.swing.JRadioButton jRadioButtonGenderFemale;
    private javax.swing.JRadioButton jRadioButtonGenderMale;
    private javax.swing.JRadioButton jRadioButtonMember;
    private javax.swing.JTextField jTextFieldBaccountname;
    private javax.swing.JTextField jTextFieldBankAccount;
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
