package gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dal.CSVLoader;
import dal.DBLoader;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 2681336469250233501L;

	private JMenuBar menubar = null;
	private JMenu menu = null;
	private JMenuItem mItemAnlegen = null;
	private JMenuItem mItemLadenCSV = null;
	private JMenuItem mItemLadenDB = null;
	private JMenuItem mItemSpeichern = null;
	private StudentList studentList = null;
	private CSVLoader csvLoader;
	private DBLoader dbLoader;

	public MainFrame(String identifier) throws HeadlessException {
		super(identifier);
		this.setResizable(false);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.initializeControls();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeControls() {
		this.setLayout(new BorderLayout());
		menubar = new JMenuBar();
		menu = new JMenu("Start");
		studentList = new StudentList(this);
		csvLoader = new CSVLoader(studentList);
		dbLoader = new DBLoader();
		
		mItemAnlegen = new JMenuItem("Neuen Schüler anlegen");
		mItemLadenCSV = new JMenuItem("Schülerliste laden CSV");
		mItemLadenDB = new JMenuItem("Schülerliste laden DB");
		mItemSpeichern = new JMenuItem("Schülerliste speichern");

		mItemAnlegen.addActionListener(this);
		mItemLadenCSV.addActionListener(this);
		mItemLadenDB.addActionListener(this);
		mItemSpeichern.addActionListener(this);

		menu.add(mItemAnlegen);
		menu.add(mItemLadenCSV);
		menu.add(mItemLadenDB);
		menu.add(mItemSpeichern);

		menubar.add(menu);
		this.add(studentList);
		this.setJMenuBar(menubar);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mItemAnlegen) {
			new StudentDialog(this, false, null, studentList);
		} else if (e.getSource() == mItemLadenCSV) {
			csvLoader.loadFromFile();
		} else if (e.getSource() == mItemSpeichern) {
			csvLoader.writeFile();
		} else if(e.getSource() == mItemLadenDB) {
			dbLoader.loadFromDatabase();
		}
	}

}
