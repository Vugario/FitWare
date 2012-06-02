/*
 * BarProductAdd.java
 *
 * Created on May 18, 2012, 8:54:42 PM
 */
package view.admin;

import main.Application;
import model.Product;
import view.popups.SuccessPopup;

/**
 * BarProductModify:
 * This class is show info on one product that is chosen from the
 * BarProductOverview view. In this class you can modify the data
 * and save it.
 * @author vm
 */
public class BarProductModify extends javax.swing.JPanel {

	//Product product = product.readById(productId);
	Product product = new Product(); // I cant't get the above to work, help needed.

	/** 
	 * Constructor for the BarProductModify view.
	 * It fills the jComboBoxType and initiates components.
	 */
	public BarProductModify(int productId) {
		System.out.println(productId);

		initComponents();

		//read the product by an id
		product.readById(productId);
		loadProductData();

		//Get the product type and add it into the combobox, translated to Dutch
		if ("drink".equals(product.getType())) {
			jComboBoxType.removeAllItems();
			jComboBoxType.addItem("Drinken");
			jComboBoxType.addItem("Eten");
			jComboBoxType.addItem("Overige");
		} else if ("food".equals(product.getType())) {
			jComboBoxType.removeAllItems();
			jComboBoxType.addItem("Eten");
			jComboBoxType.addItem("Drinken");
			jComboBoxType.addItem("Overige");

		} else {
			jComboBoxType.removeAllItems();
			jComboBoxType.addItem("Overige");
			jComboBoxType.addItem("Drinke");
			jComboBoxType.addItem("Eten");
		}
	}

	/**
	 * Load the product data from the Product.readById() method
	 * to the BarProductModify view
	 */
	public void loadProductData() {
		//Get the id and fill the form
		jTextFieldId.setText(Integer.toString(product.getId()));
		//Get the name and fill the form
		jTextFieldName.setText(product.getName());

		/*if (product.getType().equals("drink")) {
		jComboBoxType.setSelectedIndex(1);
		
		} else if (product.getType().equals("food")) {
		jComboBoxType.setSelectedIndex(2);
		
		} else {
		
		jComboBoxType.setSelectedIndex(3);
		}*/

		//Get the price and fill the form
		jTextFieldPrice.setText(String.format("%.2f", product.getPrice()));
		// Get the description and fill the form
		jTextFieldDescription.setText(product.getDescription());

	}

	/**
	 * This sets the product from the view to the query that takes
	 * it to the database (product.create()).
	 * 
	 * @author vm
	 */
	public void setProductData() {
		// Set the name in the Product model
		String name = jTextFieldName.getText();
		product.setName(name);

		// Set the price in the Product model and replace the comma with a dot
		String price = jTextFieldPrice.getText().replace(",", ".");
		product.setPrice(Double.parseDouble(price));

		// Set the description in the Product model
		String description = jTextFieldDescription.getText();
		product.setDescription(description);

		// Get the type and translate it from Dutch to English          
		String type = jComboBoxType.getSelectedItem().toString();
		if (type.equals("Drinken")) {
			type = "drink";
		} else if (type.equals("Eten")) {
			type = "food";
		} else {
			type = "other";
		}
		// Set the type in the Product model
		product.setType(type);

		// Set the id
		int id = Integer.parseInt(jTextFieldId.getText());
		product.setId(id);

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
        jLabelExtraInfo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldPrice = new javax.swing.JTextField();
        jTextFieldDescription = new javax.swing.JTextField();
        jComboBoxType = new javax.swing.JComboBox();
        jButtonBack = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();

        jLabelProductName.setText("Naam");

        jLabelProductType.setText("Type");

        jLabelProductPrice.setText("Prijs");

        jLabelExtraInfo.setText("Omschrijving");

        jLabel5.setText("min / cl / beleg / smaak");

        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonBack.setText("Terug");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Verwijderen");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonSave.setText("Opslaan");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jLabel1.setText("Product id");

        jTextFieldId.setEnabled(false);
        jTextFieldId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(418, Short.MAX_VALUE)
                .addComponent(jButtonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBack)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(506, Short.MAX_VALUE)
                .addComponent(jButtonSave)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelProductName)
                    .addComponent(jLabelProductType)
                    .addComponent(jLabelProductPrice)
                    .addComponent(jLabelExtraInfo)
                    .addComponent(jLabel1))
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldId)
                    .addComponent(jTextFieldDescription)
                    .addComponent(jTextFieldPrice)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonBack)
                            .addComponent(jButtonDelete))
                        .addGap(53, 53, 53))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                    .addComponent(jLabelExtraInfo)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(jButtonSave)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
		// Load the Baroverview screen to go and manage products
		Application.getInstance().showPanel(new view.admin.BarProductOverview());
	}//GEN-LAST:event_jButtonBackActionPerformed

	private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
		// Execute the update query from Product.java
		setProductData();
		product.updateProduct();
		Application.getInstance().showPanel(new view.admin.BarProductOverview());

	}//GEN-LAST:event_jButtonSaveActionPerformed

	private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
		// Delete the information of the selected product
		Product product = new Product();
		product.setId(Integer.parseInt(jTextFieldId.getText()));
		product.deleteProduct();

	}//GEN-LAST:event_jButtonDeleteActionPerformed

	private void jTextFieldIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_jTextFieldIdActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelExtraInfo;
    private javax.swing.JLabel jLabelProductName;
    private javax.swing.JLabel jLabelProductPrice;
    private javax.swing.JLabel jLabelProductType;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPrice;
    // End of variables declaration//GEN-END:variables
}
