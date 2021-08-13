package gui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bll.Student;

public class StudentDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8498751856732002062L;
	private JLabel lbNumber = null;
	private JTextField tfNumber = null;
	private JLabel lbFirstName = null;
	private JTextField tfFirstName = null;
	private JLabel lbLastName = null;
	private JTextField tfLastName = null;
	private JButton btnOk = null;
	private JButton btnCancel = null;
	private Student student = null;
	private StudentList studentList;

	public StudentDialog(Student student) {
		super();
		this.student = student;
		this.initializeControls();
		this.fillControls();
		this.pack();
		this.setVisible(true);
	}

	public StudentDialog(Frame owner, boolean modal, Student student, StudentList studentList) {
		super(owner, modal);
		this.studentList = studentList;
		this.student = student;
		this.initializeControls();
		this.fillControls();
		this.pack();
		this.setVisible(true);
	}

	public Student getStudent() {
		return this.student;
	}

	private void fillControls() {
		if (this.student != null) {
			this.tfNumber.setText(String.valueOf(this.student.getNumber()));
			this.tfFirstName.setText(this.student.getFirstName());
			this.tfLastName.setText(this.student.getLastName());
		}
	}

	private void initializeControls() {
		GridLayout grid = new GridLayout(4, 2);
		this.setLayout(grid);

		this.lbNumber = new JLabel("Nummer: ");
		this.tfNumber = new JTextField();

		this.lbFirstName = new JLabel("Vorname: ");
		this.tfFirstName = new JTextField();

		this.lbLastName = new JLabel("Nachname: ");
		this.tfLastName = new JTextField();

		this.btnOk = new JButton("Ok");
		this.btnOk.addActionListener(this);
		this.btnCancel = new JButton("Abbrechen");
		this.btnCancel.addActionListener(this);

		this.add(this.lbNumber);
		this.add(this.tfNumber);
		this.add(this.lbFirstName);
		this.add(this.tfFirstName);
		this.add(this.lbLastName);
		this.add(this.tfLastName);
		this.add(this.btnOk);
		this.add(this.btnCancel);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnOk)) {
			if (writeValuesToStudent()) {
				studentList.addNewStudent(student);
				this.setVisible(false);
				this.dispose();
			}
		} else if (event.getSource().equals(this.btnCancel)) {
			this.setVisible(false);
			this.dispose();
		}
	}

	private boolean writeValuesToStudent() {
		boolean isValid = false;
		try {
			if (this.tfNumber.getText().trim().length() > 0 && this.tfFirstName.getText().trim().length() > 0
					&& this.tfLastName.getText().trim().length() > 0) {

				if (this.student == null) {
					this.student = new Student();
				}
				this.student.setNumber(Integer.parseInt(this.tfNumber.getText()));
				this.student.setFirstName(this.tfFirstName.getText());
				this.student.setLastName(this.tfLastName.getText());
				isValid = true;
			}

		} catch (NumberFormatException ex) {
			System.out.println("NumberFormatException beim Schreiben der Katalognummer aufgetreten! " + ex.toString());
		}
		return isValid;
	}

}
