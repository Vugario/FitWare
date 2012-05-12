/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FitshapeTopPanel.java
 *
 * Created on May 6, 2012, 8:44:06 PM
 */
package view;

/**
 *
 * @author vm
 */
public class FitshapeTopPanel extends javax.swing.JPanel {

    /** Creates new form FitshapeTopPanel */
    public FitshapeTopPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPaneHeader = new javax.swing.JLayeredPane();
        jLayeredPaneUser = new javax.swing.JLayeredPane();
        userIcon = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jLabelLogout = new javax.swing.JLabel();
        header = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1000, 50));
        setMinimumSize(new java.awt.Dimension(1000, 50));
        setPreferredSize(new java.awt.Dimension(1000, 50));

        jLayeredPaneHeader.setMaximumSize(new java.awt.Dimension(1000, 50));
        jLayeredPaneHeader.setMinimumSize(new java.awt.Dimension(1000, 50));
        jLayeredPaneHeader.setPreferredSize(new java.awt.Dimension(1000, 50));
        jLayeredPaneHeader.setSize(new java.awt.Dimension(1000, 50));

        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/user_32.png"))); // NOI18N
        userIcon.setBounds(360, 10, 32, 32);
        jLayeredPaneUser.add(userIcon, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabelUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelUsername.setText("<gebruikersnaam>");
        jLabelUsername.setBounds(21, 7, 330, 16);
        jLayeredPaneUser.add(jLabelUsername, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabelLogout.setText("<html><u>uitloggen</u></html>");
        jLabelLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelLogout.setBounds(290, 25, 60, 16);
        jLayeredPaneUser.add(jLabelLogout, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPaneUser.setBounds(600, 0, 400, 50);
        jLayeredPaneHeader.add(jLayeredPaneUser, javax.swing.JLayeredPane.DEFAULT_LAYER);

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/header.png"))); // NOI18N
        header.setMaximumSize(new java.awt.Dimension(1008, 50));
        header.setMinimumSize(new java.awt.Dimension(1008, 50));
        header.setBounds(0, 0, 1000, 50);
        jLayeredPaneHeader.add(header, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPaneHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPaneHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabelLogout;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JLayeredPane jLayeredPaneHeader;
    private javax.swing.JLayeredPane jLayeredPaneUser;
    private javax.swing.JLabel userIcon;
    // End of variables declaration//GEN-END:variables

}
