package bll;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Projekt {

	private int nr;
	private String bezeichnung;
	private int dauerInMonaten;
	private double kostenProMonat;
	private boolean intern;
	private SortedSet<Mitarbeiter> mitarbeiterCollection = new TreeSet<Mitarbeiter>();
	
	public Projekt(int nr, String bezeichnung, int dauerInMonaten, double kostenProMonat, boolean intern) {
		this.nr = nr;
		this.bezeichnung = bezeichnung;
		this.dauerInMonaten = dauerInMonaten;
		this.kostenProMonat = kostenProMonat;
		this.intern = intern;
	}
	
	public double getGesamtkosten() {
		return dauerInMonaten*kostenProMonat;
	}
	
	public boolean addMitarbeiter(Mitarbeiter m) {
		boolean hasAlreadyLeiter = false;
		boolean rgw = false;
		Iterator<Mitarbeiter> it = mitarbeiterCollection.iterator();
		Mitarbeiter m1;
		while(it.hasNext() && !hasAlreadyLeiter) {
			m1 = it.next();
			if(m1.isProjektLeiter())
				hasAlreadyLeiter = true;
		}
		if(hasAlreadyLeiter && m.isProjektLeiter()) {
			rgw = false;
		} else {
			rgw = mitarbeiterCollection.add(m);
		}
		return rgw;
	}
	
	public boolean deleteMitarbeiter(Mitarbeiter m) {
		return mitarbeiterCollection.remove(m);
	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getDauerInMonaten() {
		return dauerInMonaten;
	}

	public void setDauerInMonaten(int dauerInMonaten) {
		this.dauerInMonaten = dauerInMonaten;
	}

	public double getKostenProMonat() {
		return kostenProMonat;
	}

	public void setKostenProMonat(double kostenProMonat) {
		this.kostenProMonat = kostenProMonat;
	}

	public boolean isIntern() {
		return intern;
	}

	public void setIntern(boolean intern) {
		this.intern = intern;
	}

	public SortedSet<Mitarbeiter> getMitarbeiterCollection() {
		return mitarbeiterCollection;
	}

	public void setMitarbeiterCollection(SortedSet<Mitarbeiter> mitarbeiterCollection) {
		this.mitarbeiterCollection = mitarbeiterCollection;
	}
	
	
}
