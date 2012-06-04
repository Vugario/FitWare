/*
 * UserAdd.java
 *
 * Created on May 10, 2012, 10:52:48 PM
 */
package view.medewerker;

import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import main.Application;
import main.Session;
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
public final class UserAdd extends javax.swing.JPanel {

    private User user = new User();
    private User userSession = Session.get().getLoggedInUser();
    Subscription subscription = new Subscription();

    /**
     * Creates new form Profile
     */
    public UserAdd() {

        initComponents();

        // Empty the combo box
       // jComboBox1.removeAllItems();

        ButtonGroup groupGender = new ButtonGroup();
        groupGender.add(jRadioButtonGenderMale);
        groupGender.add(jRadioButtonGenderFemale);

        ButtonGroup groupUserType = new ButtonGroup();
        groupUserType.add(jRadioButtonAdmin);
        groupUserType.add(jRadioButtonBarmedewerker);
        groupUserType.add(jRadioButtonMember);

        //jComboBox1.setModel(new DefaultComboBoxModel());
        //uncomment this one when the sql works
		//addBranchToComboBox();

        addSubscriptionToComboBox();
        //set the admin view buttons to false
        jRadioButtonAdmin.setVisible(false);
        jRadioButtonBarmedewerker.setVisible(false);
        jRadioButtonMember.setVisible(false);


        if (userSession.getRole().getTitle().equals("admin")) {
            jRadioButtonAdmin.setVisible(true);
            jRadioButtonBarmedewerker.setVisible(true);
            jRadioButtonMember.setVisible(true);

        }

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

   /* public void addBranchToComboBox() {
        ArrayList<Branch> branches = Branch.readAll();

        //DefaultComboBoxModel comboModel = (DefaultComboBoxModel) jComboBox1.getModel();
        for (int i = 0; i < branches.size(); i++) {
            Branch branch = branches.get(i);
            jComboBox1.addItem(branch.getCity());
        }
    }*/

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
        helper.Datetime datetime = new helper.Datetime(birthdate + " 00:00:00");
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

        int bankaccount = Integer.parseInt(jTextFieldBankAccount.getText());
        user.setBankaccount(bankaccount);

        String baccountname = jTextFieldBaccountname.getText();
        user.setBaccountname(baccountname);


        String title = (String) jComboBox2.getSelectedItem();
        System.out.println(title);
        subscription.setTitle(title);
    }

   /* public JComboBox getjComboBox1() {
        return jComboBox1;
    }

    public void setjComboBox1(JComboBox jComboBox1) {
        this.jComboBox1 = jComboBox1;
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBankAccount = new javax.swing.JTextField();
        jTextFieldBaccountname = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jRadioButtonMember = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jRadioButtonAdmin = new javax.swing.JRadioButton();
        jRadioButtonBarmedewerker = new javax.swing.JRadioButton();
        jComboBox2 = new javax.swing.JComboBox();

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24));
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

        jLabel3.setText("Abbonnement");

        jLabel1.setText("Rekeningnummer");

        jLabel20.setText("Ten name van");

        jRadioButtonMember.setText("Lid");

        jLabel21.setText("Type gebruiker");

        jRadioButtonAdmin.setText("Administratief medewerker");

        jRadioButtonBarmedewerker.setText("Barmedewerker");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(jLabel9)
                            .add(jLabel8)
                            .add(jLabel7)
                            .add(jLabel6))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(55, 55, 55)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jComboBox2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jTextFieldBirthdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jTextFieldFirstname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                                .add(jRadioButtonGenderMale)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jRadioButtonGenderFemale)))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTextFieldSubname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTextFieldLastname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .add(layout.createSequentialGroup()
                                .add(54, 54, 54)
                                .add(jTextFieldUsername, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(layout.createSequentialGroup()
                        .add(169, 169, 169)
                        .add(jRadioButtonMember)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jRadioButtonBarmedewerker)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jRadioButtonAdmin))
                    .add(jLabel21)
                    .add(jLabel12)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel11)
                            .add(jLabel10)
                            .add(jLabel13)
                            .add(jLabel14)
                            .add(jLabel15)
                            .add(jLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jTextFieldBankAccount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel20)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextFieldBaccountname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 177, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldEmail)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldMobilenumber)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldPhonenumber)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(jTextFieldCity)
                                        .add(jTextFieldStreet, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 216, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jTextFieldPostcode, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(jTextFieldStreetnumber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                    .add(profileSaveButton)
                    .add(jLabel16)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel18)
                            .add(jLabel17))
                        .add(16, 16, 16)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(layout.createSequentialGroup()
                                .add(2, 2, 2)
                                .add(jPasswordField2))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPasswordField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 325, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
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
                    .add(jLabel9)
                    .add(jRadioButtonGenderFemale)
                    .add(jRadioButtonGenderMale))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jRadioButtonMember)
                    .add(jRadioButtonBarmedewerker)
                    .add(jRadioButtonAdmin)
                    .add(jLabel21))
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
                    .add(jTextFieldBankAccount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(jTextFieldBaccountname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel20))
                .add(18, 18, 18)
                .add(jLabel16)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel18)
                    .add(jPasswordField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(16, 16, 16)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel17)
                    .add(jPasswordField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(profileSaveButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void profileSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileSaveButtonActionPerformed


        //Check if a @ sign is in the emailadress field 
        int emailcheck = jTextFieldEmail.getText().indexOf('@');

        String password1 = new String(jPasswordField1.getPassword());
        String password2 = new String(jPasswordField2.getPassword());

        if (password1.equals(password2)) {
            user.setPassword(password1);
        } else {
            Application.getInstance().showPopup(new ErrorPopup("De"
                    + " wachtwoorden komen niet overeen."));
            return;
        }

        //If emailcheck returns <0 then the @ sign is missing, 
        //Message Dialog will be shown 
        if (emailcheck < 0) {
            Application.getInstance().showPopup(new ErrorPopup("U vergeet een '@'-teken in uw e-mailadres te plaatsen.\n"
                    + "Probeer het nogmaals alstublieft."));
            return;
        }

        this.setUserData();

        if (user.checkUserExist()) {
            // User already exists
            Application.getInstance().showPopup(new ErrorPopup("Deze username is al in gebruik.\n"
                    + "Kies een andere naam."));
            return;
        }

        user.create();


        user.readLatestUser();
        Enrollment enrollment = new Enrollment();
        // int subscriptionId;
        // subscriptionId = subscription.readByTitle().getId();
        enrollment.subscribe(subscription.readByTitle().getId(), user.getId());

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

	private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
            // Empty the password field on focus gain
            jPasswordField1.setText(null);
	}//GEN-LAST:event_jPasswordField1FocusGained

	private void jPasswordField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusGained
            // Empty the password field on focus gain
            jPasswordField2.setText(null);
	}//GEN-LAST:event_jPasswordField2FocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
