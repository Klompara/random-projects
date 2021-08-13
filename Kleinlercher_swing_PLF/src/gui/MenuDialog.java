package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bll.Menu;

public class MenuDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JButton btnApply;
	private JButton btnCancel;
	private JLabel labelVorsp;
	private JLabel labelNachsp;
	private JLabel labelHauptsp;
	private JTextField textVorsp;
	private JTextField textHauptsp;
	private JTextField textNachsp;
	
	private Menu menu;
	private Mainframe frame; // um text zu setzen
	
	public MenuDialog(Menu menu, Mainframe frame) {
		this.frame = frame;
		this.menu = menu;
		initializeControls();
	}
	
	private void initializeControls() {
		this.setLayout(new GridLayout(4, 2));
		btnApply = new JButton("Apply");
		btnApply.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		labelVorsp = new JLabel("Vorspeise");
		labelHauptsp = new JLabel("Hauptspeise");
		labelNachsp = new JLabel("Nachspeise");
		textVorsp = new JTextField(menu.getVorspeise());
		textHauptsp = new JTextField(menu.getHauptspeise());
		textNachsp = new JTextField(menu.getNachspeise());
		
		this.add(labelVorsp);
		this.add(textVorsp);
		this.add(labelHauptsp);
		this.add(textHauptsp);
		this.add(labelNachsp);
		this.add(textNachsp);
		this.add(btnCancel);
		this.add(btnApply);
		
		this.pack();
		this.setLocationRelativeTo(null); // zentriert
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnApply) {
			menu.setHauptspeise(textHauptsp.getText());
			menu.setVorspeise(textVorsp.getText());
			menu.setNachspeise(textNachsp.getText());
			frame.enableButtonsSetLabels();
			this.dispose(); // close
		}
		if(e.getSource() == btnCancel) {
			this.dispose(); // close
		}
	}

}
