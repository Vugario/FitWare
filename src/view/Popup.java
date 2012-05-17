/*
 * Popup.java
 *
 * Created on May 13, 2012, 2:37:50 PM
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import main.Application;
import main.Main;

/**
 *
 * @author Daan
 */
public class Popup extends javax.swing.JPanel {

	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_ERROR = 1;
	public static final int TYPE_NOTICE = 2;
	public static final int TYPE_SUCCESS = 3;
	
	protected static Color[] colors = new Color[4];

	/**
	 * The alpha transparant background for popups
	 */
	protected JPanel popupBackground;
	
	/** Creates new form Popup */
	public Popup() {
		initComponents();
		initPopup();
	}
	
	/**
	 * Show an popup
	 * 
	 * @param message The message to display
	 * @param title The title for this popup
	 */
	public void show(String message, String title) {
		this.setMessage(message);
		this.setType(Popup.TYPE_NORMAL);
		this.setTitle(title);
		this.display();
	}
	
	/**
	 * Show a panel in this popup
	 * 
	 * @param panel The panel to display
	 */
	public void showPanel(JPanel panel) {
		// TODO
	}
	
	/**
	 * Show an error popup
	 * 
	 * @param errorMessage The errorMessage to display
	 */
	public void showError(String errorMessage) {
		this.setMessage(errorMessage);
		this.setType(Popup.TYPE_ERROR);
		this.setTitle("foutmelding");
		this.display();
	}
	
	/**
	 * Show a notice popup
	 * 
	 * @param noticeMessage The noticeMessage to display
	 */
	public void showNotice(String noticeMessage) {
		this.setMessage(noticeMessage);
		this.setType(Popup.TYPE_NOTICE);
		this.setTitle("opmerking");
		this.display();
	}
	
	/**
	 * Change the message of the popup
	 * 
	 * @param message The new message to show
	 */
	protected void setMessage(String message) {
		
		// Set the text
		this.jLabelMessage.setText(message);
	}
	
	/**
	 * Set the type of this popup
	 * 
	 * @param type Either Popup.TYPE_NORMAL, Popup.TYPE_ERROR or Popup.TYPE_NOTICE
	 */
	protected void setType(int type) {
		
		// Set the border color...
		Border newBorder = new LineBorder(Popup.colors[type], 3, true);
		this.jPanelBorders.setBorder(newBorder);
		
		// ...and the title color
		this.jLabelFoutmelding.setForeground(Popup.colors[type]);
		
	}
	
	/**
	 * Set the title of this popup
	 * 
	 * @param title The title to show for this popup
	 */
	protected void setTitle(String title) {
		this.jLabelFoutmelding.setText(title.toUpperCase());
	}
	
	/**
	 * Show the popup
	 */
	protected void display() {
		
		// Update the size according to the message
		Dimension textSize = this.jLabelMessage.getPreferredSize();
		Dimension popupSize = new Dimension(textSize.width + 100, textSize.height + 170);
		
		this.setSize(popupSize);
		this.setPreferredSize(popupSize);
		this.setMaximumSize(popupSize);
		this.setMinimumSize(popupSize);
		
		// Position the popup
		// Horizontally centered, vertically at 1/3 of the height
		int posX = (this.popupBackground.getWidth() - this.getWidth()) / 2;
		int posY = (this.popupBackground.getHeight() - this.getHeight()) / 3;
		this.setLocation(posX, posY);
		
		// Get the wrapper of our application...
		Application application = Main.getApplication();
		JLayeredPane wrapper = application.getWrapper();
		
		// ...and add the background and popup to it
		wrapper.add(this.popupBackground, new Integer(100));
		wrapper.add(this, new Integer(101));
		
		// Finaly, focus the 'close' button
		this.jButtonClosePopup.requestFocusInWindow();
	}
	
	/**
	 * Hide this popup
	 */
	protected void close() {
		
		// Get the wrapper of our application...
		Application application = Main.getApplication();
		JLayeredPane wrapper = application.getWrapper();
		
		// Remove the background and popup
		wrapper.remove(this.popupBackground);
		wrapper.remove(this);
		
		// Repaint the application
		application.repaint();
	}
	
	/**
	 * Initialize the popup
	 */
	private void initPopup() {
		
		// Initialize the colors
		if(Popup.colors[0] == null) {
			// Black
			colors[Popup.TYPE_NORMAL] = new Color(0, 0, 0);
			// Red
			colors[Popup.TYPE_ERROR] = new Color(255, 0, 0);
			// Blue
			colors[Popup.TYPE_NOTICE] = new Color(0, 0, 255);
			// Green
			colors[Popup.TYPE_SUCCESS] = new Color(0, 255, 0);
		}
		
		// Create an alpha-transparent JPanel
		// Source: http://tips4java.wordpress.com/2009/05/31/backgrounds-with-transparency/
		this.popupBackground = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		this.popupBackground.setOpaque(false);
		this.popupBackground.setBackground(new Color(0, 0, 0, 90));
		
		// Set the size to the same size as the application
		Application application = Main.getApplication();
		Dimension dimension = application.getWrapper().getSize();
		this.popupBackground.setSize(dimension);
		this.popupBackground.setPreferredSize(dimension);
		this.popupBackground.setMaximumSize(dimension);
		this.popupBackground.setMinimumSize(dimension);
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
        jLabelFoutmelding = new javax.swing.JLabel();
        jLabelMessage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonClosePopup = new javax.swing.JButton();

        setOpaque(false);

        jPanelBorders.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 3, true));

        jLabelFoutmelding.setFont(new java.awt.Font("Lucida Grande", 1, 13));
        jLabelFoutmelding.setForeground(new java.awt.Color(255, 0, 0));
        jLabelFoutmelding.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFoutmelding.setText("FOUTMELDING");

        jLabelMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMessage.setText("<foutmelding bericht komt hier>");

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
                            .add(jLabelFoutmelding, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelBordersLayout.setVerticalGroup(
            jPanelBordersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBordersLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelFoutmelding)
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
		if(evt.getKeyCode() == 10) {
			this.close();
		}
	}//GEN-LAST:event_jButtonClosePopupKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClosePopup;
    private javax.swing.JLabel jLabelFoutmelding;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBorders;
    // End of variables declaration//GEN-END:variables
}
