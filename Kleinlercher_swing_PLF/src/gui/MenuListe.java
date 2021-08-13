package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import bll.Menu;

public class MenuListe extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<Menu> dml;
	private JList<Menu> menuList;
	private Mainframe frame;

	public MenuListe(Mainframe frame) {
		this.frame = frame;
		initializeControls();
	}

	private void initializeControls() {
		dml = new DefaultListModel<Menu>();
		menuList = new JList<Menu>();
		menuList.setModel(dml);

		menuList.setFont(this.getFont());
		menuList.setBackground(Color.white);
		menuList.addMouseListener(this);
		menuList.setModel(dml);
		
		this.setVisible(true);
	}

	public void setMenus(ArrayList<Menu> menus) {
		dml.clear();
		for(Menu m : menus) {
			dml.addElement(m);
		}
	}
	
	public Menu getSelectedMenu() {
		return menuList.getSelectedValue();
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1) // linksclick
			frame.enableButtonsSetLabels();
		else if(e.getButton() == 3 && getSelectedMenu() != null) // rechtsclick
			new MenuDialog(getSelectedMenu(), frame);
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) // linksclick (zwei mal click event weil manchmal nicht selected aber nicht als klick erkannt)
			frame.enableButtonsSetLabels();
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
