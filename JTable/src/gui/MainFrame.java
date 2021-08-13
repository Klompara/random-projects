package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import bll.Schueler;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Table table;

	public MainFrame(ArrayList<Schueler> s) {
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		initializeControls(s);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	private void initializeControls(ArrayList<Schueler> s) {
		this.setLayout(new BorderLayout());
		table = new Table(s);
		this.add(table, BorderLayout.PAGE_START);
	}

}
