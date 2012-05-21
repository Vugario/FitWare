/*
 * Enrollments.java
 *
 * Created on May 6, 2012, 5:01:37 PM
 */
package view;

import helper.SearchTable;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import main.Application;
import main.Session;
import model.Enrollment;
import model.Subscription;
import view.popups.EnrollmentPopup;
import view.popups.ErrorPopup;

/**
 *
 * @author mennowolvers
 */
public class Enrollments extends javax.swing.JPanel {
    /**
     * Model for making the content of the table
     */
    private DefaultTableModel model;
    
    /**
     * An individual row to attach the onclick event
     */
    private ListSelectionModel row;
    private final SearchTable searchTable;
    
    private Enrollment enrollments = new Enrollment().readEnrollmentByUserId(Session.get().getLoggedInUser().getId());

    /** Creates new form Enrollments */
    public Enrollments() {
        initComponents();
        
        this.searchTable = new SearchTable(jEnrollments, jTextFieldSearch, jButtonReset);
        
        // Retrieve all subscriptions
        ArrayList<Subscription> items = Subscription.readAll();
        
        // Fill the model with data
        this.model = (DefaultTableModel) jEnrollments.getModel();
        for( int i = 0; i < items.size(); i++ )
        {
            Subscription item = items.get(i);
            model.insertRow(0,new Object[]{ item.getId(), item.getTitle(), "€ " + item.getPrice(), item.getMinimumAge(), item.getMaximumAge() });
        }
        
        // Make a selection listener
        this.row = jEnrollments.getSelectionModel();
        this.row.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.row.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               if (! e.getValueIsAdjusting())
                {
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    Object value = 0;
                    if (!lsm.isSelectionEmpty()) {
                        int minIndex = lsm.getMinSelectionIndex();
                        int maxIndex = lsm.getMaxSelectionIndex();
                        for (int i = minIndex; i <= maxIndex; i++) {
                            if (lsm.isSelectedIndex(i)) {
                                value = jEnrollments.getModel().getValueAt(i, 0);
                            }
                        }
                    }
                    
                    Subscription subscription = new Subscription( Integer.parseInt( value.toString() ) );
                    Application.getInstance().showPopup(new EnrollmentPopup( "Naam: " + subscription.getTitle() + "\nLeeftijdscategorie: " + subscription.getMinimumAge() + " tot " + subscription.getMaximumAge() + "\nOmschrijving: " + subscription.getDescription(), subscription ));
                }
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEnrollments = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();

        jLabel19.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jLabel19.setText("Mijn cursussen");

        jEnrollments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Naam", "Prijs", "Minimale leeftijd", "Maximale leeftijd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jEnrollments);

        jTextFieldSearch.setText("zoekterm");

        jButtonSearch.setText("Zoek");

        jButtonReset.setText("Reset");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonReset)))
                        .addGap(0, 106, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch)
                    .addComponent(jButtonReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonReset.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JTable jEnrollments;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

    private class jLayeredPaneWrapper {

        public jLayeredPaneWrapper() {
        }
    }
}
