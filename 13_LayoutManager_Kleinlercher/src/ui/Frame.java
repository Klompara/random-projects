package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bll.Student;

public class Frame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1890535670469353122L;

	private List<Student> students = new ArrayList<Student>();
	
	private JFrame frameFlow = new JFrame("Flow Layout");
	private JPanel panelFlow = new JPanel();
	private JFrame frameGrid = new JFrame("Grid layout");
	private JPanel panelGrid = new JPanel();
	private JFrame frameBorder = new JFrame("Border layout");
	private JPanel panelBorder = new JPanel();
	
	private JButton btnAddStudent = new JButton("Add student");
	private JButton btnShowStudentsFlow = new JButton("Show students");
	private JButton btnShowStudentsBorder = new JButton("Show students");
	private JLabel laFirstName = new JLabel("Student firstname: ");
	private JLabel laSurName = new JLabel("Student surname: ");
	private JLabel laKatNr = new JLabel("Katalog nummer: ");
	private JTextField fiFirstName = new JTextField();
	private JTextField fiSurName = new JTextField();
	private JTextField fiKatNr = new JTextField();
	
	public Frame() {
		btnAddStudent.addActionListener(this);
		btnShowStudentsFlow.addActionListener(this);
		btnShowStudentsBorder.addActionListener(this);
		panelGrid.setLayout(new GridLayout(0, 2));
		setUpFields();
		frameGrid.add(panelGrid);
		frameGrid.pack();
		frameGrid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameGrid.setLocationRelativeTo(null);
		frameGrid.setResizable(false);
		frameGrid.setVisible(true);
		
		panelFlow.setLayout(new FlowLayout(3));
		frameFlow.add(panelFlow);
		frameFlow.pack();
		frameFlow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFlow.setLocationRelativeTo(null);
		frameFlow.setResizable(false);
		frameFlow.setVisible(true);
		frameFlow.setSize(new Dimension(250, 250));
		
		panelBorder.setLayout(new BorderLayout());
		frameBorder.add(panelBorder);
		frameBorder.pack();
		frameBorder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameBorder.setLocationRelativeTo(null);
		frameBorder.setResizable(false);
		frameBorder.setVisible(true);
		frameBorder.setSize(new Dimension(250, 250));
	}

	private void setUpFields() {
		panelGrid.add(laFirstName);
		panelGrid.add(fiFirstName);
		panelGrid.add(laSurName);
		panelGrid.add(fiSurName);
		panelGrid.add(laKatNr);
		panelGrid.add(fiKatNr);
		panelGrid.add(btnAddStudent);
		panelFlow.add(btnShowStudentsFlow);
		panelBorder.add(btnShowStudentsBorder, BorderLayout.PAGE_START);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAddStudent)) {
			students.add(new Student(Integer.parseInt(fiKatNr.getText()), fiFirstName.getText(), fiSurName.getText()));
			fiKatNr.setText("");
			fiFirstName.setText("");
			fiSurName.setText("");
		}else if(e.getSource().equals(btnShowStudentsFlow)) {
			panelFlow.removeAll();
			panelFlow.add(btnShowStudentsFlow);
			for(Student s : students) {
				panelFlow.add(new JLabel(s.getNachname()));
				panelFlow.add(new JLabel(s.getVorname()));
				panelFlow.add(new JLabel(""+s.getKatNr()));
			}
			frameFlow.pack();
		}else if(e.getSource().equals(btnShowStudentsBorder)) {
			panelBorder.removeAll();
			panelBorder.add(btnShowStudentsBorder);
			for(Student s : students) {
				panelBorder.add(new JLabel(s.getNachname()));
				panelBorder.add(new JLabel(s.getVorname()));
				panelBorder.add(new JLabel(""+s.getKatNr()));
			}
			frameBorder.pack();
		}
	}
}
