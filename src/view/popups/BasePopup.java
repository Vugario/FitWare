/*
 * ErrorPopup.java
 *
 * Created on May 13, 2012, 2:37:50 PM
 */
package view.popups;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import main.Application;

/**
 *
 * @author Daan
 */
abstract public class BasePopup extends javax.swing.JPanel {

	/**
	 * The alpha transparant background for popups
	 */
	protected JPanel popupBackground;

	/**
	 * Creates new form ErrorPopup
	 * 
	 * @param message The message to show
	 */
	public BasePopup(String message) {
		super();
		initComponents();
		
		// Fix newlines
		message = "<html>" + message + "</html>";
		message.replaceAll("\n", "<br>");
		
		// Set the message
		jLabelMessage.setText(message);
		
		// Give the close button focus
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				jButtonClosePopup.requestFocusInWindow();
			}
		});
		
	}
	
	/**
	 * Set the color of this popup.
	 * 
	 * @param color The color for the title and the border
	 */
	protected void setColor(Color color) {
		
		// Set the border color...
		Border newBorder = new LineBorder(color, 3, false);
		this.jPanelBorders.setBorder(newBorder);
		
		// ...and the title color
		jLabelTitle.setForeground(color);
	}
	
	/**
	 * Set the title of this popup.
	 * 
	 * @param title The title for this popup.
	 */
	protected void setTitle(String title) {
		jLabelTitle.setText(title);
	}
	
	/**
	 * Close this popup
	 */
	public void close() {
		Application.getInstance().closePopup();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBorders = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelMessage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonClosePopup = new javax.swing.JButton();

        setOpaque(false);

        jPanelBorders.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabelTitle.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("<TITEL>");

        jLabelMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMessage.setText("<bericht komt hier>");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonClosePopup.setText("OK");
        jButtonClosePopup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClosePopupActionPerformed(evt);
            }
        });
        jButtonClosePopup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButtonClosePopupKeyReleased(evt);
            }
        });
        jPanel1.add(jButtonClosePopup, new java.awt.GridBagConstraints());

        org.jdesktop.layout.GroupLayout jPanelBordersLayout = new org.jdesktop.layout.GroupLayout(jPanelBorders);
        jPanelBorders.setLayout(jPanelBordersLayout);
        jPanelBordersLayout.setHorizontalGroup(
            jPanelBordersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBordersLayout.createSequentialGroup()
                .add(jPanelBordersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelBordersLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
                    .add(jPanelBordersLayout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(jPanelBordersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelMessage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                            .add(jLabelTitle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelBordersLayout.setVerticalGroup(
            jPanelBordersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBordersLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelTitle)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabelMessage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelBorders, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelBorders, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void jButtonClosePopupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClosePopupActionPerformed
		this.close();
	}//GEN-LAST:event_jButtonClosePopupActionPerformed

	private void jButtonClosePopupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonClosePopupKeyReleased
		// If this is an enter, check the login
		// The KeyCode for an enter is 10
		if (evt.getKeyCode() == 10) {
			this.close();
		}
	}//GEN-LAST:event_jButtonClosePopupKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClosePopup;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBorders;
    // End of variables declaration//GEN-END:variables
}
