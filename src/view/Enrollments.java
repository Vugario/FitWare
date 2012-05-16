/*
 * Enrollments.java
 *
 * Created on May 6, 2012, 5:01:37 PM
 */
package view;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import main.Application2;
import main.Main;

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

    /** Creates new form Enrollments */
    public Enrollments() {
        initComponents();
        
        // Get application
        Application2 application = Main.getApplication();
        
        // Fill the model with example data
        this.model = (DefaultTableModel) jEnrollments.getModel();
        model.insertRow(0,new Object[]{ "1", "Zumba", "pakket", "€ 20", "21-02-2012", "21-05-2012" });
        model.insertRow(0,new Object[]{ "2", "Lente Jam", "pakket", "€ 25", "21-02-2012", "21-05-2012" });
        model.insertRow(0,new Object[]{ "3", "Zumba Summer Session", "pakket", "€ 25", "21-02-2012", "21-05-2012" });
        model.insertRow(0,new Object[]{ "4", "Kickboxing", "pakket", "€ 20", "21-02-2012", "21-05-2012" });
        
        // Make a selection listener
        this.row = jEnrollments.getSelectionModel();
        this.row.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.row.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               /* if (! e.getValueIsAdjusting())
                {
                    Application application = Main.getApplication();
                    application.showPopup("Gebruikersnaam en wachtwoord combinatie is niet bekend.");
                }*/
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

        jLabel19.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jLabel19.setText("Mijn cursussen");

        jEnrollments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Naam", "Type", "Prijs", "Start datum", "Eind datum"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jEnrollments);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addComponent(jLabel19))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable jEnrollments;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private class jLayeredPaneWrapper {

        public jLayeredPaneWrapper() {
        }
    }
}
