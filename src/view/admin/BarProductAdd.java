/*
 * BarProductAdd.java
 *
 * Created on May 18, 2012, 8:54:42 PM
 */
package view.admin;

import main.Application;
import model.Product;
import view.popups.ErrorPopup;
import view.popups.SuccessPopup;

/**
 * BarProductAdd:
 * This class is made to add a product to the database. You only
 * get here from the BarApp and then pressing 'Beheer Producten'
 * and then pressing the '+'-Button.
 * @author vm
 */
public class BarProductAdd extends javax.swing.JPanel {
	Product product = new Product();
	
	/** Creates new form BarProductAdd.
	 This is the constructer for the BarProductAdd view, it fills the
	 jComboBoxType and initiates components.*/
	public BarProductAdd() {
		initComponents();
		
		jComboBoxType.removeAllItems();
		jComboBoxType.addItem("Drinken");
		jComboBoxType.addItem("Eten");
		jComboBoxType.addItem("Overig");
		
	}
	
	/**
	 * This sets the product from the view to the query that takes
	 * it to the database (product.create()).
	 * @author vm
	 */
	public void setProductData(){
		String name = jTextFieldName.getText();
		product.setName(name);
		
		String type = jComboBoxType.getSelectedItem().toString();
		if (type.equals("Drinken")){
			type = "drink";
		}else if(type.equals("Eten")){
			type = "food";
		} else {
			type = "other";
		}
			
		product.setType(type);
		
		double price = Double.parseDouble(jTextFieldPrice.getText().replace("," , "."));
		product.setPrice(price);
		
		String description = jTextFieldDescription.getText();
		product.setDescription(description);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelProductName = new javax.swing.JLabel();
        jLabelProductType = new javax.swing.JLabel();
        jLabelProductPrice = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldPrice = new javax.swing.JTextField();
        jTextFieldDescription = new javax.swing.JTextField();
        jComboBoxType = new javax.swing.JComboBox();
        jButtonBack = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        jLabelProductName.setText("Naam");

        jLabelProductType.setText("Type");

        jLabelProductPrice.setText("Prijs");

        jLabelDescription.setText("Omschrijving");

        jLabel5.setText("min / cl / beleg / smaak");

        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonBack.setText("Terug");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jButtonSave.setText("Opslaan");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelProductName)
                    .addComponent(jLabelProductType)
                    .addComponent(jLabelProductPrice)
                    .addComponent(jLabelDescription))
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldDescription)
                    .addComponent(jTextFieldPrice)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jComboBoxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(521, Short.MAX_VALUE)
                .addComponent(jButtonBack)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(506, Short.MAX_VALUE)
                .addComponent(jButtonSave)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonBack)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProductName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProductType)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProductPrice)
                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDescription)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jButtonSave)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
		//Set the product data from the textfields
		setProductData();
				
		// TODO Save the new product in the database
		if(!product.create()){
			//If product.create fails show error message
			Application.getInstance().showPopup(new ErrorPopup("Het is niet gelukt om op te slaan, probeer het nogmaals alstublieft."));
		}else {
			Application.getInstance().showPopup(new SuccessPopup("Het product is opgeslagen, u keert nu terug naar het overzicht."));
		};
				
		//return to the overview page to prevent query spam
		Application.getInstance().showPanel(new view.admin.BarProductOverview());
			
	}//GEN-LAST:event_jButtonSaveActionPerformed

	private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
		// Return to overview view
		Application.getInstance().showPanel(new view.admin.BarProductOverview());
	}//GEN-LAST:event_jButtonBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelProductName;
    private javax.swing.JLabel jLabelProductPrice;
    private javax.swing.JLabel jLabelProductType;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPrice;
    // End of variables declaration//GEN-END:variables
}
