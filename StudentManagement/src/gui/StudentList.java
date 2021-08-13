package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import bll.Student;

public class StudentList extends JPanel implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JList<Student> listStudents = null;
	private Frame parent = null;
	private JScrollPane scrollPane = null;
	private DefaultListModel<Student> dml = null;
	private JPopupMenu popup;
	private JButton btnPopupCreate;
	private JButton btnPopupDelete;

	public StudentList(Frame parent) {
		this.parent = parent;
		this.initializeControls();
		this.initializePopup();
	}

	private void initializeControls() {
		Dimension d = new Dimension(580, 280);
		this.listStudents = new JList<Student>();
		this.scrollPane = new JScrollPane(this.listStudents);
		dml = new DefaultListModel<Student>();

		this.scrollPane.setMinimumSize(d);
		this.scrollPane.setPreferredSize(d);
		this.listStudents.setFont(this.getFont());
		this.listStudents.setBackground(Color.white);
		this.listStudents.addMouseListener(this);
		this.listStudents.setModel(dml);

		this.add(this.scrollPane);
		this.setVisible(true);
	}

	private void initializePopup() {
		popup = new JPopupMenu();
		btnPopupCreate = new JButton("new");
		btnPopupDelete = new JButton("delete");
		btnPopupCreate.addActionListener(this);
		btnPopupDelete.addActionListener(this);
		popup.add(btnPopupCreate);
		popup.add(btnPopupDelete);
		MouseListener popupListener = new PopupListener(popup);
		this.listStudents.addMouseListener(popupListener);
	}

	class PopupListener extends MouseAdapter {
		JPopupMenu popup;

		PopupListener(JPopupMenu popupMenu) {
			popup = popupMenu;
		}

		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	public ArrayList<Student> getStudents() {
		ArrayList<Student> list = new ArrayList<Student>();
		for (int i = 0; i < dml.getSize(); i++)
			list.add(dml.getElementAt(i));
		return list;
	}

	public void FillList(ArrayList<Student> students) {
		dml.removeAllElements();
		for (Student s : students)
			dml.addElement(s);
	}

	public void addNewStudent(Student s) {
		dml.addElement(s);
	}
	
	public void removeStudent(Student s) {
		
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnPopupCreate) {
			new StudentDialog(parent, false, null, this);
			popup.setVisible(false);
		}
		if(e.getSource() == btnPopupDelete) {
			if(listStudents.getSelectedIndex() >= 0)
				dml.removeElementAt(listStudents.getSelectedIndex());
			popup.setVisible(false);
		}
	}

}
