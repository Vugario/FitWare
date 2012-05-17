/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Allen;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.Application;

/**
 *
 * @author allentje
 */
public class Login2 extends JPanel implements MouseListener {

	/**
	 * Creates new form Login
	 */
	public Login2() {
		super();
		initComponents();
	}
	
	
	public void initComponents(){
		this.setBackground(Color.red);
		addTitle();
	}
	
	public void addTitle(){
		JLabel lblTitle = new JLabel();
		lblTitle.setText("Welkom");
		lblTitle.setBounds(20, 20, 300, 20);
		lblTitle.setFont(new Font("Verdaba", Font.PLAIN, 10));
		add(lblTitle);
	}
	
	
		@Override
	public void mouseClicked(MouseEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}
}
