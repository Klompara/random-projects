package app;

import java.nio.file.FileSystems;
import java.nio.file.Paths;

import bll.FileRead;
import bll.Mitarbeiter;

public class Start {

	public static void main(String[] args) {
		String separator = FileSystems.getDefault().getSeparator();
		
		FileRead fr = new FileRead();
		for(int i = 1; i < 7; i++) {
			fr.readFileAndSaveUmsatz(Paths.get("." + separator + "res" + separator + "Umsatz_" + ((i < 10) ? "0"+i : i) + "_17.csv"));
		}
		for(Mitarbeiter m : fr.getMitarbeiter()) {
			m.calculateUmsaetze();
		}
		for(Mitarbeiter m : fr.getMitarbeiter()) {
			System.out.println(m.toString());
		}
		
		fr.writeFile(Paths.get("." + separator + "res" + separator + "Umsatzauswertung.csv"));
	}

}
