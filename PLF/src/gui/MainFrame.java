package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bll.Reisetag;
import dal.FileIO;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8026416994513756565L;
	
	private ReiseListe reiseListe;
	private JLabel labelDatum;
	private JLabel labelStadt;
	private JLabel labelPreis;
	private JLabel labelInfo;
	private JTextField textDatum;
	private JTextField textStadt;
	private JTextField textPreis;
	private JTextArea textBeschreibung;
	private JButton buttonAendernBeschreibung;
	private JButton buttonAendernDetails;
	private FileIO fileio = new FileIO();
	
	public MainFrame(ArrayList<Reisetag> r) {
		fileio.setBeschreibungen(r);
		reiseListe = new ReiseListe(this);
		reiseListe.setReisen(r);
		this.setSize(800, 600);
		this.setTitle("Reiseplan");
		this.setResizable(true);
		this.setVisible(true);
		initializeControls();
	}
	
	private void initializeControls() {
		this.setLayout(new BorderLayout());
		JPanel panelRight = new JPanel();
		panelRight.setLayout(new GridLayout(4, 2));
		JPanel panelLeft = new JPanel();
		panelLeft.setLayout(new GridLayout(2, 1));
		
		labelDatum = new JLabel("Datum");
		labelStadt = new JLabel("Stadt");
		labelPreis = new JLabel("Preis");
		labelInfo = new JLabel("Es ist noch nichts geschehen!");
		labelInfo.setForeground(Color.blue);
		textDatum = new JTextField();
		textStadt = new JTextField();
		textPreis = new JTextField();
		textBeschreibung = new JTextArea();
		buttonAendernBeschreibung = new JButton("Ändern");
		buttonAendernBeschreibung.addActionListener(this);
		buttonAendernDetails = new JButton("Ändern");
		buttonAendernDetails.addActionListener(this);
		panelLeft.add(textBeschreibung);
		panelLeft.add(buttonAendernBeschreibung);
		panelRight.add(labelDatum);
		panelRight.add(textDatum);
		panelRight.add(labelStadt);
		panelRight.add(textStadt);
		panelRight.add(labelPreis);
		panelRight.add(textPreis);
		panelRight.add(new JLabel(""));
		panelRight.add(buttonAendernDetails);
		
		this.add(panelRight, BorderLayout.EAST);
		this.add(panelLeft, BorderLayout.CENTER);
		this.add(reiseListe, BorderLayout.NORTH);
		this.add(labelInfo, BorderLayout.SOUTH);
		this.pack();
	}

	public void setTextBox() {
		Reisetag r = reiseListe.getSelected();
		textDatum.setText(r.getDatum());
		textStadt.setText(r.getStadt());
		textPreis.setText(String.valueOf(r.getPreis()));
		textBeschreibung.setText(r.getBeschreibung());
		this.pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonAendernBeschreibung)
			reiseListe.getSelected().setBeschreibung(textBeschreibung.getText());
		if(e.getSource() == buttonAendernDetails) {
			Reisetag r = reiseListe.getSelected();
			SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy");
			try {
				r.setDatum(dt.parse(textDatum.getText()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			r.setPreis(Double.parseDouble(textPreis.getText()));
			r.setStadt(textStadt.getText());
		}
	}
	
}
