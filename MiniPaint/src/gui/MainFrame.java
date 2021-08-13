package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private PaintPanel paintPanel = null;
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem itemColor;
	private JMenuItem itemSetSize;
	private JRadioButtonMenuItem radioStraight;
	private JRadioButtonMenuItem radioNotStraight;
	private JMenuItem itemSaveImage;
	private JMenuItem itemLoadImage;
	private JMenuItem itemClearImage;

	public MainFrame(String identifier) throws HeadlessException {
		super(identifier);
		this.setMinimumSize(new Dimension(800, 600));
		this.setPreferredSize(new Dimension(800, 600));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initializeControls();
	}

	private void initializeControls() {
		GridLayout grid = new GridLayout(1, 1);
		this.paintPanel = new PaintPanel();
		this.paintPanel.setStraight(false);
		this.setLayout(grid);

		menubar = new JMenuBar();
		menu = new JMenu("Options");
		itemColor = new JMenuItem("Set Color");
		itemSetSize = new JMenuItem("Set Size");
		radioStraight = new JRadioButtonMenuItem("Straight");
		radioNotStraight = new JRadioButtonMenuItem("not Straight");
		itemSaveImage = new JMenuItem("Safe Image");
		itemLoadImage = new JMenuItem("Load Image");
		itemClearImage = new JMenuItem("Clear Image");
		
		radioNotStraight.setSelected(true);
		radioStraight.addActionListener(this);
		radioNotStraight.addActionListener(this);
		itemSetSize.addActionListener(this);
		itemColor.addActionListener(this);
		itemSaveImage.addActionListener(this);
		itemLoadImage.addActionListener(this);
		itemClearImage.addActionListener(this);
		
		menu.add(itemSetSize);
		menu.add(itemColor);
		menu.addSeparator();
		menu.add(radioStraight);
		menu.add(radioNotStraight);
		menu.addSeparator();
		menu.add(itemSaveImage);
		menu.add(itemLoadImage);
		menu.addSeparator();
		menu.add(itemClearImage);
		menubar.add(menu);

		this.setJMenuBar(menubar);
		this.add(this.paintPanel);
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == radioStraight) {
			radioNotStraight.setSelected(!radioStraight.isSelected());
			paintPanel.setStraight(!radioNotStraight.isSelected());
		} else if (e.getSource() == radioNotStraight) {
			radioStraight.setSelected(!radioNotStraight.isSelected());
			paintPanel.setStraight(radioStraight.isSelected());
		} else if (e.getSource() == itemSetSize) {
			paintPanel.setSize(Integer.parseInt(JOptionPane.showInputDialog("Gib die Groese ein: ")));
		} else if (e.getSource() == itemColor) {
			paintPanel.setColor(JColorChooser.showDialog(null, "Farbe", Color.white));
		} else if (e.getSource() == itemSaveImage) {
			paintPanel.saveImage();
		} else if (e.getSource() == itemLoadImage) {
			paintPanel.loadImage();
		} else if (e.getSource() == itemClearImage) {
			paintPanel.clearImage();
		}
	}
}
