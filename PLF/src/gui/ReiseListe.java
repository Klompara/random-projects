package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bll.Reisetag;

public class ReiseListe extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private DefaultListModel<Reisetag> dml;
	private JList<Reisetag> reiseList;
	private MainFrame frame;
	
	public ReiseListe(MainFrame frame) {
		initializeControls();
		this.frame = frame;
	}
	
	private void initializeControls() {
		Dimension d = new Dimension(400, 100);
		dml = new DefaultListModel<Reisetag>();
		reiseList = new JList<Reisetag>();
		reiseList.setModel(dml);
		scrollPane = new JScrollPane(reiseList);

		scrollPane.setMinimumSize(d);
		scrollPane.setPreferredSize(d);
		reiseList.setFont(this.getFont());
		reiseList.setBackground(Color.white);
		reiseList.addMouseListener(this);
		reiseList.setModel(dml);
		
		this.add(scrollPane);
		this.setVisible(true);
	}

	public Reisetag getSelected() {
		return reiseList.getSelectedValue();
	}
	
	public void setReisen(ArrayList<Reisetag> reisetage) {
		dml.clear();
		for(Reisetag r : reisetage) {
			dml.addElement(r);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1 && getSelected() != null)
			frame.setTextBox();
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
