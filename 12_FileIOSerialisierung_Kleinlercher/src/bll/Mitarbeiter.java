package bll;

import java.util.ArrayList;
import java.util.List;

public class Mitarbeiter {

	private int personalNummer;
	private String vorname;
	private String nachname;
	private List<Float> umsaetze = new ArrayList<Float>();
	private float summeUmsatz;
	private float durchschnittUmsatz;
	
	public Mitarbeiter(int personalNummer, String vorname, String nachname, float umsatz) {
		super();
		this.personalNummer = personalNummer;
		this.vorname = vorname;
		this.nachname = nachname;
		addUmsatz(umsatz);
	}
	
	public void addUmsatz(float newUmsatz) {
		umsaetze.add(newUmsatz);
	}
	
	public void calculateUmsaetze() {
		for(float f : umsaetze) {
			summeUmsatz += f;
		}
		durchschnittUmsatz = summeUmsatz / umsaetze.size();
	}
	
	
	public float getSummeUmsatz() {
		return summeUmsatz;
	}

	public float getDurchschnittUmsatz() {
		return durchschnittUmsatz;
	}


	public int getPersonalNummer() {
		return personalNummer;
	}
	public void setPersonalNummer(int personalNummer) {
		this.personalNummer = personalNummer;
	}
	public String getVorname() {
		return vorname;
	}
	public void setNorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	@Override
	public String toString() {
		return "Mitarbeiter [personalNummer=" + personalNummer + ", vorname=" + vorname + ", nachname=" + nachname
				+ ", umsaetze=" + umsaetze + ", summeUmsatz=" + summeUmsatz + ", durchschnittUmsatz="
				+ durchschnittUmsatz + "]";
	}



	
}
