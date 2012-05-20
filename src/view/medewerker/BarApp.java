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
import javax.swing.JButton;
import main.Application;
import main.Session;
import model.Product;
import model.User;

/**
 *
 * @author vm
 */
public class BarApp extends javax.swing.JPanel {

	/**
	 * Creates new form BarApp
	 */
	public BarApp() {
		initComponents();
		addProductbuttons();
		User user = Session.get().getLoggedInUser();
		jButtonProductmgnt.setEnabled(false);

		// TODO If user role is !admin then jButtonProductmgnt is not visible
		if (user.getRoles().get(0).getTitle().equals("admin")) {
			//Set the button for 'Product beheer'
			jButtonProductmgnt.setEnabled(true);
		}
		System.out.println(user.getRoles().get(0).getTitle());
	}

	private void addProductbuttons() {
		ArrayList<Product> products = Product.readAll();


		for (int i = 0; i < products.size(); i++) {

			Product product = products.get(i);

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
					// TODO Voeg toe aan basket
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
        jList1 = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButtonProductmgnt = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(830, 599));
        setPreferredSize(new java.awt.Dimension(830, 599));

        jPanelFood.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jTabbedPaneRemainder.addTab("Eten", jPanelFood);

        jPanelDrink.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jTabbedPaneRemainder.addTab("Drinken", jPanelDrink);

        jPanelActivity.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jTabbedPaneRemainder.addTab("Overige", jPanelActivity);

        jPanel5.setPreferredSize(new java.awt.Dimension(250, 290));

        jLabelOrder.setText("De bestelling");

        jLabel1.setText("Prijs:");

        jLabelOrderPrice.setText("jLabelOrderPrice");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Verwijder:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(filler2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelOrderPrice))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabelOrder)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelOrder))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelOrderPrice)
                            .addComponent(jLabel1))))
                .addContainerGap())
        );

        jButtonProductmgnt.setText("Beheer producten");
        jButtonProductmgnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductmgntActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPaneRemainder, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(91, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonProductmgnt)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonProductmgnt)
                            .addComponent(jTabbedPaneRemainder, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(264, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jButtonProductmgntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductmgntActionPerformed
		//Load the Baroverview screen to go and manage products
		Application.getInstance().showPanel(new view.admin.BarProductOverview());

	}//GEN-LAST:event_jButtonProductmgntActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButtonProductmgnt;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelOrder;
    private javax.swing.JLabel jLabelOrderPrice;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelActivity;
    private javax.swing.JPanel jPanelDrink;
    private javax.swing.JPanel jPanelFood;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPaneRemainder;
    // End of variables declaration//GEN-END:variables
}
