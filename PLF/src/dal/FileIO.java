package dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import bll.Reisetag;

public class FileIO {

	public FileIO() {
		
	}
	
	public void setBeschreibungen(ArrayList<Reisetag> r) {
		File f = new File(".\\res\\Toskana.txt");
		BufferedReader inputStream = null;
		String l;
		String[] splittedLine = null;
		try {
			inputStream = new BufferedReader(new FileReader(f.getAbsolutePath()));
			int counter = 0;
			while ((l = inputStream.readLine()) != null) {
				splittedLine = l.split("\"");
				if(l.startsWith("Beschreibung Tag " + (counter+1))) {
					counter++;
				}
				if(l.startsWith("\"") || l.startsWith("+ \"")) {
					Reisetag tempR = r.get(counter-1);
					tempR.setBeschreibung(tempR.getBeschreibung() + splittedLine[1]);
				}
			}
		} catch (IOException e) {
			System.err.println("Fehler beim Laden der Datei!");
		} catch (NullPointerException e) {
			System.err.println("Keine Datei wurde ausgewaehlt!");
		} catch (NumberFormatException ex) {
			System.err.println("Fehler beim Parsen der Katalognummer!");
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
