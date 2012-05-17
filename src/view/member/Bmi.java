/*
 * Bmi.java
 *
 * Created on May 6, 2012, 4:56:17 PM
 */
package view.member;

import java.text.ParseException;
import javax.swing.ButtonGroup;
import main.Session;
import model.User;



/**
 *
 * @author vm
 */
public class Bmi extends javax.swing.JPanel {

    private float bmi;

    /**
     * Creates new form Bmi
     */
    public Bmi() {
        initComponents();
    }
   
    @SuppressWarnings("deprecation")
    public void loadUserData() {
    User user = Session.get().getLoggedInUser();
    
    		ButtonGroup group = new ButtonGroup();
		group.add(jRadioButton3);
		group.add(jRadioButton4);
                
    if (user.getGender() == User.MALE) {
			jRadioButton3.setSelected(true);
		}else {
			jRadioButton4.setSelected(true);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JLabel();
        txtResult = new javax.swing.JLabel();
        jButtonCalcBMI = new javax.swing.JButton();
        jAge = new javax.swing.JTextField();
        txtLength = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtWeight = new javax.swing.JTextField();

        jLabel21.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel21.setText("BMI Berekenen");

        jLabel22.setText("Geslacht");

        jLabel23.setText("Leeftijd");

        jLabel24.setText("Lengte");

        jLabel25.setText("Gewicht");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Man");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Vrouw");

        jLabel26.setText("cm");

        jLabel27.setText("kg");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel28.setText("De Body Mass Index (BMI) is een index voor het gewicht in verhouding tot lichaamslengte.");

        jLabel29.setText("De BMI-berekening geeft een waarde terug, aan deze waarde kan worden afgelezen of");

        jLabel30.setText("er sprake is van overgewicht, ondergewicht of een gezond gewicht.");

        jLabel31.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel31.setText("Resultaat");

        txtDescription.setText(" ");

        txtResult.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtResult.setText("Voer uw gegevens in");

        jButtonCalcBMI.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButtonCalcBMI.setText("Bereken!");
        jButtonCalcBMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcBMIActionPerformed(evt);
            }
        });

        jAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAgeActionPerformed(evt);
            }
        });

        jLabel1.setText("jaar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(jAge, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLength, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel26))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel27)))))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtResult, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jButtonCalcBMI)))
                .addContainerGap(60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(jAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(31, 31, 31)
                                .addComponent(jLabel24))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(txtLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel31)
                            .addGap(18, 18, 18)
                            .addComponent(txtResult)
                            .addGap(63, 63, 63)
                            .addComponent(txtDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(jButtonCalcBMI)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCalcBMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcBMIActionPerformed
        float weight = new Float(txtWeight.getText());        
        float length = new Float(txtLength.getText()) / 100;
        
        
        
        this.bmi = weight / (length * length);
        
        txtResult.setText(String.format("Uw BMI is %.2f", this.bmi));
        
        if (this.bmi < 18.5) {
            txtDescription.setText("U heeft ondergewicht");
        }
        if (this.bmi < 25 && this.bmi > 18.5) {
            txtDescription.setText("U heeft het ideale gewicht");
        }
        if (this.bmi > 25) {
            txtDescription.setText("U heeft overgewicht");
        }
        if (this.bmi > 30) {
            txtDescription.setText("<html>U lijdt aan obesitas. <br> Neem contact op met uw huisarts!</html>");
        }
    }//GEN-LAST:event_jButtonCalcBMIActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAgeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField jAge;
    private javax.swing.JButton jButtonCalcBMI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel txtDescription;
    private javax.swing.JTextField txtLength;
    private javax.swing.JLabel txtResult;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables

	
}

