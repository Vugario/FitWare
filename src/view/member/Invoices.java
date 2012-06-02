/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Invoices.java
 *
 * Created on May 6, 2012, 5:01:37 PM
 */
package view.member;

import helper.SearchTable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import main.Application;
import main.Session;
import model.Invoice;
import model.User;
import view.popups.InvoicePopup;

/**
 *
 * @author vm
 */
public class Invoices extends javax.swing.JPanel {

	protected SearchTable searchTable;
	protected DefaultTableModel model;

	/** Creates new form Invoices */
	public Invoices() {
		initComponents();
		
		// Make the table searchable
		this.searchTable = new SearchTable(jTable1, jTextFieldSearch, jButtonReset);
		this.model = (DefaultTableModel) jTable1.getModel();

		// Update the table with all invoices of the currently logged in user
		updateTable();
	}
	
	private void updateTable() {
		User user = Session.get().getLoggedInUser();
		
		// First, empty it.
		model.setRowCount(0);
		
		// Secondly, fill it with all invoices
		ArrayList<Invoice> invoices = Invoice.readByUserId(user.getId());
		
		// In reverse order (so newest is on top)
		Collections.reverse(invoices);
		
		for(Invoice invoice : invoices) {
			model.addRow(invoice.getTableRowObjects(false));
		}
	}
	
	private void showInvoice() {
		// Get the currently selected subscription
		int rowNumber = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
		String invoiceId = (String) model.getValueAt(rowNumber, 0);
		Invoice invoice = new Invoice();
		invoice.readInvoice(Integer.parseInt(invoiceId));
		
		// Show popup
		Application.getInstance().showPopup(new InvoicePopup(invoice));
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonReset = new javax.swing.JButton();

        jLabel19.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel19.setText("Mijn facturen");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"0004", "April 2012", "€ 45,00", "30-01-2012", "Niet betaald"},
                {"0003", "Maart 2012", "€ 42,50", "31-01-2012", "Betaald"},
                {"0002", "Februari 2012", "€ 33,00", "29-01-2012", "Betaald"},
                {"0001", "Januari 2012", "€ 33,00", "31-01-2012", "Betaald"}
            },
            new String [] {
                "Factuurnummer", "Maand", "Prijs", "Datum", "Betaling"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextFieldSearch.setText("zoekterm");

        jButtonReset.setText("Reset");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                    .addComponent(jLabel19)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonReset)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
		if (evt.getClickCount() >= 2) {
			// Double clicked!
			showInvoice();
		}
	}//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReset;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
