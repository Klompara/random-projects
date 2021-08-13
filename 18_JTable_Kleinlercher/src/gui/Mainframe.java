package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import bll.DateLabelFormatter;
import bll.Fuﬂballer;

public class Mainframe extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private JScrollPane scrollPane;
	private ArrayList<Fuﬂballer> fuﬂballer;
	private JPopupMenu popupDelete;
	private JButton buttonDelete;
	private JComboBox<String> comboBox;
	private JCheckBox box;

	private JDatePickerImpl datePicker;
	private JDatePanelImpl datePanel;
	private UtilDateModel modelFrom;
	private Properties p;

	public Mainframe(ArrayList<Fuﬂballer> fuﬂballer) {
		this.fuﬂballer = fuﬂballer;
		initializeControls();
		this.setTitle("Fuﬂballer...");
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initializeControls() {
		this.setLayout(new BorderLayout());

		String[] titles = { "Vorname", "Nachname", "Verein", "Gesperrt", "Eintrittsdatum" };
		Object[][] objects = Fuﬂballer.fuﬂballerToArray(fuﬂballer);
		table = new JTable(new DefaultTableModel(objects, titles)) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
				return true;
			}

		};

		comboBox = new JComboBox<>(Fuﬂballer.getAllVereine());
		box = new JCheckBox();

		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		modelFrom = new UtilDateModel();
		modelFrom.setValue(java.util.Calendar.getInstance().getTime());
		modelFrom.setSelected(true);
		datePanel = new JDatePanelImpl(modelFrom, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(box));
		table.getColumnModel().getColumn(3).setCellRenderer(new CheckBoxRenderer());
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));

		scrollPane = new JScrollPane(table);

		popupDelete = new JPopupMenu();
		buttonDelete = new JButton("Lˆschen");
		buttonDelete.addActionListener(this);
		popupDelete.add(buttonDelete);

		MouseListener popupDeleteListener = new PopupListener(popupDelete);
		table.addMouseListener(popupDeleteListener);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(new JLabel("no details displayed..."), BorderLayout.SOUTH); // Nix
																				// angabe
	}

	class PopupListener extends MouseAdapter {
		JPopupMenu popupDelete;

		PopupListener(JPopupMenu popupDeleteMenu) {
			popupDelete = popupDeleteMenu;
		}

		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				popupDelete.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonDelete) {
			popupDelete.setVisible(false);
			removeSelectedRows();
		}
	}

	public void removeSelectedRows() {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		int[] rows = table.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			model.removeRow(rows[i] - i);
		}
	}
}
