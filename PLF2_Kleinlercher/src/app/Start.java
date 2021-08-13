package app;

import java.io.IOException;
import java.util.Date;

import bll.Mitarbeiter;
import bll.Projekt;
import bll.SerializationHelper;
import bll.Unternehmen;
import bll.Weiterbildung;

public class Start {

	public static void main(String[] args) {
		Unternehmen u = new Unternehmen();
		Projekt p1 = new Projekt(1, "Bezeichnung1", 2, 100.50, false);
		Mitarbeiter m1 = new Mitarbeiter(true, "Hanz", "Franz", new Date());
		Weiterbildung w1 = new Weiterbildung("5", "bez","hier", 30);
		u.addWeiterbildung(w1);
		
		m1.addWeiterbildung(w1);
		p1.addMitarbeiter(m1);
		u.addProject(p1);
		u.addProject(new Projekt(2, "Bezeichnung2", 5, 50.50, false));
		u.addProject(new Projekt(3, "Bezeichnung3", 4, 666.50, true));
		
		try {
			SerializationHelper.Serializable(m1, m1.getNachname() +".bin");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
