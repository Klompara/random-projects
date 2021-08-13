package bll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FileRead {

	private Set<Mitarbeiter> mitarbeiter = new HashSet<Mitarbeiter>();
	
	public FileRead(){}
	
	public void readFileAndSaveUmsatz(Path path) {
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader(path.toString()));
			String l;
			boolean firstLine = true;
			while ((l = inputStream.readLine()) != null) {
				if(!firstLine) {
					String[] data = l.split(";");
					Mitarbeiter m = new Mitarbeiter(Integer.parseInt(data[0]), data[1], data[2], Float.parseFloat(data[3]));
					Mitarbeiter existingM = null;
					boolean exists = false;
					for(Mitarbeiter mita : mitarbeiter) {
						if(mita.getPersonalNummer() == m.getPersonalNummer()) {
							exists = true;
							existingM = mita;
						}
					}
					if(!exists) {
						mitarbeiter.add(m);
					}else {
						existingM.addUmsatz(Float.parseFloat(data[3]));
					}
				}else{
					firstLine = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				try { inputStream.close(); } catch (IOException e) { e.printStackTrace(); }
		}
	}
	
	public Set<Mitarbeiter> getMitarbeiter() {
		return mitarbeiter;
	}

	public void writeFile(Path path) {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileWriter(path.toString()));
			Iterator<Mitarbeiter> it = mitarbeiter.iterator();
			outputStream.write("PersonalNummer;Vorname;Nachname;Durchschnittsumsatz;Summe Umsatz;\n");
			while(it.hasNext()) {
				Mitarbeiter m = it.next();
				outputStream.write(m.getPersonalNummer() + ";" + m.getVorname() + ";" + m.getNachname() + ";" + m.getDurchschnittUmsatz() + ";" + m.getSummeUmsatz() + ";\n");
			}
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
