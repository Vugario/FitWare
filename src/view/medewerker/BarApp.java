/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BarApp.java
 *
 * Created on May 18, 2012, 9:08:53 PM
 */
package view.medewerker;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import main.Application;
import main.Session;
import model.Product;
import model.Purchase;
import model.User;
import view.popups.ErrorPopup;
import view.popups.SuccessPopup;

/**
 *
 * @author vm
 */
public class BarApp extends javax.swing.JPanel {

    double totalPrice = 0.00;

    /**
     * Creates new form BarApp
     */
    public BarApp() {
        initComponents();
        addProductbuttons();
        User user = Session.get().getLoggedInUser();
        jButtonProductmgnt.setVisible(false);

        // TODO If user role is !admin then jButtonProductmgnt is not visible
        if (user.getRoles().get(0).getTitle().equals("admin")) {
            //Set the button for 'Product beheer'
            jButtonProductmgnt.setVisible(true);
        }

        jListBasket.setModel(new DefaultListModel());
        ButtonGroup group = new ButtonGroup();
        group.add(jRadioButtonPayCredit);
        group.add(jRadioButtonPayCash);


    }

    private void addProductbuttons() {
        ArrayList<Product> products = Product.readAll();


        for (int i = 0; i < products.size(); i++) {

            final Product product = products.get(i);

            JButton productButton = new JButton();
            productButton.setFont(new Font("Tahoma", 0, 10));
            productButton.setText(
                    "<html><p align=\"center\">"
                    + "<strong>" + product.getName() + "</strong><br>"
                    + product.getDescription() + " / "
                    + product.getDecoratedPrice()
                    + "</p></html>");
            productButton.setMaximumSize(new Dimension(120, 60));
            productButton.setMinimumSize(new Dimension(120, 60));
            productButton.setPreferredSize(new Dimension(120, 60));
            productButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    addProductToBasket(product);
                }
            });

            if (product.getType().equals("food")) {
                jPanelFood.add(productButton);
            } else if (product.getType().equals("drink")) {
                jPanelDrink.add(productButton);
            } else {
                jPanelActivity.add(productButton);
            }
        }


    }

    public void addProductToBasket(Product product) {
        DefaultListModel listModel = (DefaultListModel) jListBasket.getModel();
        listModel.addElement(product);

        recalculatePrice();
    }

    public void removeSelectedProductFromBasket() {
        try {

            DefaultListModel listModel = (DefaultListModel) jListBasket.getModel();
            int selectedItem = jListBasket.getSelectedIndex();
            listModel.remove(selectedItem);

            // Recalculate the price
            recalculatePrice();

        } catch (ArrayIndexOutOfBoundsException aioobe) {
            Application.getInstance().showPopup(new ErrorPopup("Selecteer eerst een product om te verwijderen."));
        }
    }

    public void resetBasket() {
        DefaultListModel listModel = (DefaultListModel) jListBasket.getModel();
        listModel.removeAllElements();
        recalculatePrice();
    }

    //  public void resetCustomernumber(){
    //      if ()
    //      jTextFieldSearch.setText(null);
    // }
    public void recalculatePrice() {
        DefaultListModel listModel = (DefaultListModel) jListBasket.getModel();

        // Recalculate the price
        totalPrice = 0;
        for (int i = 0; i < listModel.getSize(); i++) {
            Product product = (Product) listModel.getElementAt(i);

            totalPrice = totalPrice + product.getPrice();
        }

        // Update the price in the label
        jLabelOrderPrice.setText(String.format("€ %.2f", totalPrice));

    }

    public User searchUser() {
        // Search a user and return a result
        // TODO: search by name, etc.
        User user = new User();
        int id = Integer.parseInt(jTextFieldSearch.getText());
        user.readUser(id);

        // Choose which user when multiple users are found
        //user = showSearchPopup();

        // Set the label
        jLabelCustomerName.setText(user.getFullName());

        // Return the user
        return user;
    }

    public void showSearchPopup() {
        //TODO
        //Shows a screen when multiple users are found
    }

    public void savePurchase() {
        // Loop over all products
        DefaultListModel listModel = (DefaultListModel) jListBasket.getModel();

        for (int i = 0; i < listModel.getSize(); i++) {
            // Add a purchase for this product
            Product product = (Product) listModel.getElementAt(i);

            // Create a purchase
            Purchase purchase = new Purchase();

            // Set the user
            int user_id = searchUser().getId();
            purchase.setUser_id(user_id);

            // Set the product
            int product_id = product.getId();
            purchase.setProduct_id(product_id);

            // Set the price
            double price = product.getPrice();
            purchase.setPrice(price);




            // Set the payment option
            if (jRadioButtonPayCash.isSelected()) {

                purchase.setPaymentoption("Cash");

            } else {
                purchase.setPaymentoption("Op rekening");
            }


            // Set the quantity
            // For now: add each product 1 time.
            short quantity = 1;
            purchase.setQuantity(quantity);

            // Save the purchase
            purchase.savePurchase();
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

        jTabbedPaneRemainder = new javax.swing.JTabbedPane();
        jPanelFood = new javax.swing.JPanel();
        jPanelDrink = new javax.swing.JPanel();
        jPanelActivity = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabelOrder = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 32767));
        jLabel1 = new javax.swing.JLabel();
        jLabelOrderPrice = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListBasket = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonDelete = new javax.swing.JButton();
        jButtonSearchCustomer = new javax.swing.JButton();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonProductmgnt = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonPayCash = new javax.swing.JRadioButton();
        jRadioButtonPayCredit = new javax.swing.JRadioButton();
        jLabelPayment = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelCustomerName = new javax.swing.JLabel();
        jButtonOrder = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(830, 599));
        setPreferredSize(new java.awt.Dimension(830, 599));

        jPanelFood.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jTabbedPaneRemainder.addTab("Eten", jPanelFood);

        jPanelDrink.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jTabbedPaneRemainder.addTab("Drinken", jPanelDrink);

        jPanelActivity.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jTabbedPaneRemainder.addTab("Overige", jPanelActivity);

        jPanel5.setPreferredSize(new java.awt.Dimension(250, 290));

        jLabelOrder.setFont(new java.awt.Font("Arial", 1, 18));
        jLabelOrder.setText("De bestelling");

        jLabel1.setText("Prijs:");

        jLabelOrderPrice.setText("€ 00,00");

        jListBasket.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListBasket);

        jButtonDelete.setText("Verwijder");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonSearchCustomer.setText("Zoek klant");
        jButtonSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchCustomerActionPerformed(evt);
            }
        });

        jTextFieldSearch.setText("Klantnummer");
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });
        jTextFieldSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(filler2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(116, 116, 116))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(174, 174, 174))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelOrder)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabelOrderPrice)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonDelete, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jTextFieldSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSearchCustomer)))
                        .addGap(192, 192, 192)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabelOrder)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelOrderPrice)
                            .addComponent(jLabel1))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSearchCustomer)
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );

        jButtonProductmgnt.setText("Beheer producten");
        jButtonProductmgnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductmgntActionPerformed(evt);
            }
        });

        jRadioButtonPayCash.setText("Direct betalen");

        jRadioButtonPayCredit.setText("Op rekening zetten");
        jRadioButtonPayCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPayCreditActionPerformed(evt);
            }
        });

        jLabelPayment.setText("Afrekenen");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPayment)
                            .addComponent(jRadioButtonPayCash)
                            .addComponent(jRadioButtonPayCredit))
                        .addContainerGap(220, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabelPayment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonPayCash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonPayCredit)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jLabelCustomerName.setText("Geen klant geselecteerd");

        jButtonOrder.setText("Bestellen");
        jButtonOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPaneRemainder, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jButtonProductmgnt))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabelCustomerName))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jButtonOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonProductmgnt)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCustomerName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonOrder))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPaneRemainder, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jButtonProductmgntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductmgntActionPerformed
            //Load the Baroverview screen to go and manage products
            Application.getInstance().showPanel(new view.admin.BarProductOverview());

	}//GEN-LAST:event_jButtonProductmgntActionPerformed

	private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
            removeSelectedProductFromBasket();
	}//GEN-LAST:event_jButtonDeleteActionPerformed

	private void jRadioButtonPayCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPayCreditActionPerformed
            // TODO add your handling code here:
	}//GEN-LAST:event_jRadioButtonPayCreditActionPerformed

	private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
            // TODO add your handling code here:
	}//GEN-LAST:event_jTextFieldSearchActionPerformed

	private void jTextFieldSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusGained
            // Clear the SearchField on click
            jTextFieldSearch.setText(null);
	}//GEN-LAST:event_jTextFieldSearchFocusGained

	private void jButtonSearchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchCustomerActionPerformed
            // TODO
            // Search user by user id
            searchUser();
	}//GEN-LAST:event_jButtonSearchCustomerActionPerformed

	private void jButtonOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrderActionPerformed
            // TODO add your handling code here:
            try {
                savePurchase();
            } catch (NumberFormatException nfe) {
                jTextFieldSearch.setText(null);
            }

            resetBasket();
            Application.getInstance().showPopup(new SuccessPopup("De bestelling is geplaatst."));
            jTextFieldSearch.setText("Klantnummer");
	}//GEN-LAST:event_jButtonOrderActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonOrder;
    private javax.swing.JButton jButtonProductmgnt;
    private javax.swing.JButton jButtonSearchCustomer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCustomerName;
    private javax.swing.JLabel jLabelOrder;
    private javax.swing.JLabel jLabelOrderPrice;
    private javax.swing.JLabel jLabelPayment;
    private javax.swing.JList jListBasket;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelActivity;
    private javax.swing.JPanel jPanelDrink;
    private javax.swing.JPanel jPanelFood;
    private javax.swing.JRadioButton jRadioButtonPayCash;
    private javax.swing.JRadioButton jRadioButtonPayCredit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPaneRemainder;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
