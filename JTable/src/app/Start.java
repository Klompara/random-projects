package app;

import java.util.ArrayList;

import bll.Schueler;
import gui.MainFrame;

public class Start {

	public static void main(String[] args) {
		
		ArrayList<Schueler> sl = new ArrayList<Schueler>();
		sl.add(new Schueler("Michael", "Kleinlercher", 7));
		sl.add(new Schueler("Philipp", "Hohenwarter", 6));
		sl.add(new Schueler("Rene", "Eder", 4));
		sl.add(new Schueler("Max", "Mustermann", 1));
		new MainFrame(sl);
	}

}
