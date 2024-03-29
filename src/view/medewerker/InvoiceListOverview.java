/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InvoiceListOverview.java
 *
 * Created on May 22, 2012, 12:44:21 AM
 */
package view.medewerker;

import helper.InvoiceCreator;
import helper.SearchTable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import main.Application;
import main.Session;
import model.Invoice;
import model.User;
import org.postgresql.translation.messages_bg;
import view.popups.InvoicePopup;
import view.popups.SuccessPopup;

/**
 * This class is used to show all the invoices
 * @author Daan
 */
public class InvoiceListOverview extends javax.swing.JPanel {

	protected SearchTable searchTable;
	protected DefaultTableModel model;
	
	/** Creates new form InvoiceListOverview */
	public InvoiceListOverview() {
		initComponents();
		
		// Make the table searchable
		this.searchTable = new SearchTable(jTable1, jTextFieldSearch, jButtonReset);
		this.model = (DefaultTableModel) jTable1.getModel();

		// Update the table with all invoices of the currently logged in user
		updateTable();
	}
	
	private void updateTable() {
		
		// First, empty it.
		model.setRowCount(0);
		
		// Secondly, fill it with all invoices
		ArrayList<Invoice> invoices = Invoice.readAll();
		
		// In reverse order (so newest is on top)
		Collections.reverse(invoices);
		
		for(Invoice invoice : invoices) {
			model.addRow(invoice.getTableRowObjects(true));
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

        jButtonCreateInvoices = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonReset = new javax.swing.JButton();

        jButtonCreateInvoices.setText("Maak facturen");
        jButtonCreateInvoices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateInvoicesActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel19.setText("Mijn facturen");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"0004", "0001", "jveldhuj001", "April 2012", "€ 45,00", "30-01-2012", "Niet betaald"},
                {"0003", "0001", "jveldhuj001", "Maart 2012", "€ 42,50", "31-01-2012", "Betaald"},
                {"0002", "0001", "jveldhuj001", "Februari 2012", "€ 33,00", "29-01-2012", "Betaald"},
                {"0001", "0001", "jveldhuj001", "Januari 2012", "€ 33,00", "31-01-2012", "Betaald"}
            },
            new String [] {
                "Factuurnummer", "Klantnummer", "Klantnaam", "Maand", "Prijs", "Datum", "Betaling"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jTextFieldSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 169, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButtonReset)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 364, Short.MAX_VALUE)
                        .add(jButtonCreateInvoices))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                    .add(jLabel19))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel19)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButtonCreateInvoices)
                    .add(jTextFieldSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButtonReset))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jButtonCreateInvoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateInvoicesActionPerformed
		
		// Create all invoices
		int count = InvoiceCreator.createAllInvoices();
		
		// Reload the table
		updateTable();
		
		// Show a popup
		String message;
		switch(count) {
			case 0:
				message = "Er hoeven geen facturen te worden aangemaakt";
				break;
			case 1:
				message = "Er is 1 factuur aangemaakt";
				break;
			default:
				message = String.format("Er zijn %d facturen aangemaakt.", count);
				break;
		}
		Application.getInstance().showPopup(new SuccessPopup(message));
	}//GEN-LAST:event_jButtonCreateInvoicesActionPerformed

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
		if (evt.getClickCount() >= 2) {
			// Double clicked!
			showInvoice();
		}
	}//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCreateInvoices;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
