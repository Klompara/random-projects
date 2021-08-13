package bll;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Unternehmen {

	private Set<Projekt> projekte = new TreeSet<Projekt>();
	private Map<String, Weiterbildung> weiterbildungen = new HashMap<String, Weiterbildung>();
	
	public Unternehmen() {
		
	}
	
	public boolean addProject(Projekt p) {
		return projekte.add(p);
	}
	
	public boolean deleteProject(Projekt p) {
		return projekte.remove(p);
	}
	
	public boolean addWeiterbildung(Weiterbildung w) {
		boolean rgw = weiterbildungen.containsValue(w);
		if(!rgw)
			weiterbildungen.put(w.getKurskennzahl(), w);
		return rgw;
	}
	
	public void deleteWeiterbildung(Weiterbildung w) {
		weiterbildungen.remove(w.getKurskennzahl());
	}
	
	public void printProjectSortedByNr(boolean aufsteigend) {
		Comparator<Projekt> comp = new Comparator<Projekt>(){
			@Override
			public int compare(Projekt p1, Projekt p2) {
				return p1.getNr() - p2.getNr();
			}
		};
		
	}
	
	public double getGesamtkostenInterneProjekte() {
		double rgw = 0;
		for(Projekt p : projekte) {
			rgw += p.getGesamtkosten();
		}
		return rgw;
	}
	
	public ArrayList<Mitarbeiter> getProjektleiterAllerProjekte() {
		ArrayList<Mitarbeiter> rgw = new ArrayList<Mitarbeiter>();
		for(Projekt p : projekte) {
			for(Mitarbeiter m : p.getMitarbeiterCollection()) {
				if(m.isProjektLeiter())
					rgw.add(m);
			}
		}
		return rgw;
	}
}
