package gui;

import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bll.Schueler;

public class Table extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<Schueler> schuelerList;

	public Table(ArrayList<Schueler> schuelerListe) {
		super();
		this.schuelerList = schuelerListe;
		initializeControls();
	}

	private void initializeControls() {
		String[] titles = new String[] { "Vorname", "Nachname", "Katalognummer", "Geschlecht" };

		DefaultTableModel model = new DefaultTableModel(getTableContent(), titles);
		this.table = new JTable(model);
		String[] geschlecht = {"Männlich", "Weiblich"};
		JComboBox<String> combobox = new JComboBox<>(geschlecht);
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(combobox));
		this.add(table);
	}

	private String[][] getTableContent() {
		String[][] rgw = new String[schuelerList.size()][4];
		for (int i = 0; i < schuelerList.size(); i++) {
			Schueler s = schuelerList.get(i);
			rgw[i][0] = s.getVorname();
			rgw[i][1] = s.getNachname();
			rgw[i][2] = "" + s.getKatnr();
			rgw[i][3] = "Männlich";
		}
		return rgw;
	}
}
