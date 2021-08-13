package bll;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Mitarbeiter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2610554186977467241L;

	private boolean isProjektLeiter;
	private String vorname;
	private String nachname;
	private transient Date eintrittsdatum;
	private List<Weiterbildung> weiterbildungen = new ArrayList<Weiterbildung>();
	
	public Mitarbeiter(boolean isProjektLeiter, String vorname, String nachname, Date eintrittsdatum) {
		this.vorname = vorname;
		this.isProjektLeiter = isProjektLeiter;
		this.nachname = nachname;
		this.eintrittsdatum = eintrittsdatum;
	}
	
	public void printWeiterbildungenSortedByKurskennzahl(boolean aufsteigend) {
		Comparator<Weiterbildung> comp = new Comparator<Weiterbildung>(){
			@Override
			public int compare(Weiterbildung w1, Weiterbildung w2) {
				return w1.getKurskennzahl().compareTo(w2.getKurskennzahl());
			}
		};
		if(aufsteigend)
			weiterbildungen.sort(comp);
		else
			weiterbildungen.sort(comp.reversed());
		
		Iterator<Weiterbildung> it = weiterbildungen.iterator();
		Weiterbildung w;
		while(it.hasNext()) {
			w = it.next();
			System.out.println(w);
		}
	}
	
	public boolean addWeiterbildung(Weiterbildung w) {
		return weiterbildungen.add(w);
	}
	
	public boolean deleteWeiterbildung(Weiterbildung w) {
		return weiterbildungen.remove(w);
	}

	public boolean isProjektLeiter() {
		return isProjektLeiter;
	}

	public void setProjektLeiter(boolean isProjektLeiter) {
		this.isProjektLeiter = isProjektLeiter;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Date getEintrittsdatum() {
		return eintrittsdatum;
	}

	public void setEintrittsdatum(Date eintrittsdatum) {
		this.eintrittsdatum = eintrittsdatum;
	}

	public List<Weiterbildung> getWeiterbildungen() {
		return weiterbildungen;
	}

	public void setWeiterbildungen(List<Weiterbildung> weiterbildungen) {
		this.weiterbildungen = weiterbildungen;
	}
	
}
