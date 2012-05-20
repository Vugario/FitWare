/*
 * Enrollments.java
 *
 * Created on May 6, 2012, 5:01:37 PM
 */
package view;

import helper.SearchTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import main.Session;
import model.Enrollment;

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
               if (! e.getValueIsAdjusting())
                {
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    int value;
                    if (!lsm.isSelectionEmpty()) {
                        int minIndex = lsm.getMinSelectionIndex();
                        int maxIndex = lsm.getMaxSelectionIndex();
                        for (int i = minIndex; i <= maxIndex; i++) {
                            if (lsm.isSelectedIndex(i)) {
                                value = i;
                                System.out.println(i);
                            }
                        }
                    }
                    
                    System.out.println(value);
                    //Enrollment enrollments = new Enrollment().readEnrollmentById();
                    //System.out.println( enrollments.getId() );
                    //Application application = Main.getApplication();
                    //application.showPopup("Gebruikersnaam en wachtwoord combinatie is niet bekend.");
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

        jLabel19.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
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
                        .addGap(0, 0, Short.MAX_VALUE)))
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
