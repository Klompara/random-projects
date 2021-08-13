package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bll.BuchListe;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnLoad = null;
	private JButton btnAdd = null;
	private JButton btnEdit = null;
	private JButton btnDelete = null;
	private JButton btnCopy = null;
	
	private BuchListe bookList = null;
	
	private JLabel labelIsbn;
	private JLabel labelTitle;
	private JLabel labelFirstname;
	private JLabel labelLastname;
	private JLabel labelPublisher;
	private JLabel labelPrice;
	private JLabel labelAmount;
	
	private JTextField textIsbn;
	private JTextField textTitle;
	private JTextField textFirstname;
	private JTextField textLastname;
	private JTextField textPublisher;
	private JTextField textPrice;
	private JTextField textAmount;
	
	public MainFrame() {
		this.setTitle("Bibliothek");
		this.setVisible(true);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializeControls();
	}

	private void initializeControls() {
		this.setLayout(new BorderLayout());
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(this);
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(this);
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		btnCopy = new JButton("Copy");
		btnCopy.addActionListener(this);
		
		JPanel panelTop = new JPanel();
		panelTop.add(btnLoad);
		panelTop.add(btnAdd);
		panelTop.add(btnEdit);
		panelTop.add(btnDelete);
		panelTop.add(btnCopy);
		this.add(panelTop, BorderLayout.NORTH);
		
		bookList = new BuchListe();
		JPanel panelLeft = new JPanel();
		panelLeft.add(bookList);
		this.add(bookList, BorderLayout.WEST);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(7, 1));
		labelAmount = new JLabel("Amount");
		labelAmount.setPreferredSize(new Dimension(150, 20));
		labelFirstname = new JLabel("Firstname");
		labelIsbn = new JLabel("ISBN");
		labelLastname = new JLabel("Lastname");
		labelPrice = new JLabel("Price");
		labelPublisher = new JLabel("Publisher");
		labelTitle = new JLabel("Title");
		panelCenter.add(labelIsbn);
		panelCenter.add(labelTitle);
		panelCenter.add(labelFirstname);
		panelCenter.add(labelLastname);
		panelCenter.add(labelPublisher);
		panelCenter.add(labelPrice);
		panelCenter.add(labelAmount);
		this.add(panelCenter, BorderLayout.CENTER);
		
		JPanel panelEast = new JPanel();
		panelEast.setLayout(new GridLayout(7, 1));
		textAmount = new JTextField();
		textAmount.setPreferredSize(new Dimension(125, 20));
		textFirstname = new JTextField();
		textIsbn = new JTextField();
		textLastname = new JTextField();
		textPrice = new JTextField();
		textPublisher = new JTextField();
		textTitle = new JTextField();
		panelEast.add(textIsbn);
		panelEast.add(textTitle);
		panelEast.add(textFirstname);
		panelEast.add(textLastname);
		panelEast.add(textPublisher);
		panelEast.add(textPrice);
		panelEast.add(textAmount);
		this.add(panelEast, BorderLayout.EAST);
		
		this.pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
