package bll;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Book;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BuchListe extends JPanel implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;

	private JList<Buch> books = null;
	private DefaultListModel<Buch> dml = null;
	private JScrollPane scrollpane = null;

	public BuchListe() {
		initializeControls();
	}

	private void initializeControls() {
		Dimension d = new Dimension(580, 280);
		this.books = new JList<Buch>();
		this.scrollpane = new JScrollPane(this.books);
		dml = new DefaultListModel<Buch>();

		this.scrollpane.setMinimumSize(d);
		this.scrollpane.setPreferredSize(d);
		this.books.setFont(this.getFont());
		this.books.setBackground(Color.white);
		this.books.addMouseListener(this);
		this.books.setModel(dml);

		this.add(this.scrollpane);
		this.setVisible(true);
		for(int i = 0; i < 500; i++)
			addToList(new Buch("isb" + i,"tit", "pub", 99.99, "last", "first", 2));
	}

	public void addToList(Buch book) {
		dml.addElement(book);
	}
	
	public void actionPerformed(ActionEvent e) {

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

}
