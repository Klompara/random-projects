package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bll.Menu;

public class Mainframe extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnBestellen;
	private JButton btnStornieren;
	private JLabel labelMenuInfo;
	private JLabel labelBottom;
	private MenuListe menuList;

	public Mainframe(ArrayList<Menu> startMenus) {
		this.setTitle("Speiseplan");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		initializeControls();
		this.setLocationRelativeTo(null); // zentriert
		menuList.setMenus(startMenus);
	}

	private void initializeControls() {
		btnBestellen = new JButton("Bestellen");
		btnBestellen.setEnabled(false);
		btnBestellen.addActionListener(this);
		btnStornieren = new JButton("Stornieren");
		btnStornieren.setEnabled(false);
		btnStornieren.addActionListener(this);
		labelMenuInfo = new JLabel("");
		labelBottom = new JLabel("Es ist noch nichts geschehen!");
		labelBottom.setForeground(Color.blue);
		menuList = new MenuListe(this);

		this.setLayout(new BorderLayout());
		this.add(menuList, BorderLayout.WEST);
		JPanel panelEast = new JPanel();
		panelEast.setLayout(new GridLayout(3, 1));
		panelEast.add(labelMenuInfo);
		panelEast.add(btnBestellen);
		panelEast.add(btnStornieren);

		this.add(panelEast, BorderLayout.CENTER);
		this.add(labelBottom, BorderLayout.SOUTH);
		this.pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBestellen) {
			if (menuList.getSelectedMenu() == null) {
				btnBestellen.setEnabled(false);
				btnStornieren.setEnabled(false);
				labelMenuInfo.setText("");
			} else {
				menuList.getSelectedMenu().addBestellung();
				enableButtonsSetLabels();
			}
		}
		if (e.getSource() == btnStornieren) {
			if (menuList.getSelectedMenu() == null) {
				btnBestellen.setEnabled(false);
				btnStornieren.setEnabled(false);
				labelMenuInfo.setText("");
			} else {
				menuList.getSelectedMenu().addStornierung();
				enableButtonsSetLabels();
			}
		}
	}
	
	public void enableButtonsSetLabels() {
		btnBestellen.setEnabled(true);
		btnStornieren.setEnabled(true);
		labelMenuInfo.setText(menuList.getSelectedMenu().toString());
		labelBottom.setText(menuList.getSelectedMenu().getMenuText());
		this.pack();
	}
}
