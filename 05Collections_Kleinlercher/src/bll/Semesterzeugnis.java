package bll;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Semesterzeugnis {
	private Date datum;
	private String vorname;
	private String klasse;
	private List<Bewertung> bewertungen = new ArrayList<Bewertung>();
	
	public Semesterzeugnis(Date datum, String vorname, String klasse) {
		this.datum = datum;
		this.vorname = vorname;
		this.klasse = klasse;
	}
	
	public void bewertungLoeschen(int pos) {
		if(bewertungen.get(pos) != null)
			bewertungen.remove(pos);
	}
	
	public void bewertungLoeschen(String gegenstand) {
		for(int i = 0; i < bewertungen.size(); i++) {
			if(bewertungen.get(i) != null) {
				if(bewertungen.get(i).getGegenstand() == gegenstand) {
					bewertungen.remove(bewertungen.get(i));
				}
			}
		}
	}
	
	public void bewertungHinzufuegen(Bewertung bewertung) {
		bewertungen.add(bewertung);
	}
	
	public void listeSortierenComparator() {
		this.bewertungen.sort(new BewertungComperator());
		
		System.out.println(("Ausgabe mit foreach"));
		bewertungenAusgebenMitForeach();
		System.out.println("\n\n");
		
		System.out.println("Ausgabe mit Iterator");
		bewertungenAusgebenMitIterator();
		System.out.println("\n\n");

		System.out.println("Ausgabe mit Lamda Expression");
		bewertungenAusgebenMitLamdaExpression();
	}
	
	public void listeSortierenComparable() {
		this.bewertungen.sort(new BewertungComperator());
		
		System.out.println(("Ausgabe mit foreach"));
		bewertungenAusgebenMitForeach();
		System.out.println("\n\n");
		
		System.out.println("Ausgabe mit Iterator");
		bewertungenAusgebenMitIterator();
		System.out.println("\n\n");

		System.out.println("Ausgabe mit Lamda Expression");
		bewertungenAusgebenMitLamdaExpression();
	}
	
	public void listeSortierenLamda() {
		bewertungen.sort((Bewertung o1, Bewertung o2)->o1.getGegenstand().compareTo(o2.getGegenstand()));
		
		System.out.println(("Ausgabe mit foreach"));
		bewertungenAusgebenMitForeach();
		System.out.println("\n\n");
		
		System.out.println("Ausgabe mit Iterator");
		bewertungenAusgebenMitIterator();
		System.out.println("\n\n");

		System.out.println("Ausgabe mit Lamda Expression");
		bewertungenAusgebenMitLamdaExpression();
	}
	
	public void listeSortierenAnonymousClass() {
		Comparator<Bewertung> c1 = new Comparator<Bewertung>(){
			public int compare(Bewertung o1, Bewertung o2) {
				return o1.getGegenstand().compareTo(o2.getGegenstand());
			}	
		};
		
		bewertungen.sort(c1);
		
		System.out.println(("Ausgabe mit foreach"));
		bewertungenAusgebenMitForeach();
		System.out.println("\n\n");
		
		System.out.println("Ausgabe mit Iterator");
		bewertungenAusgebenMitIterator();
		System.out.println("\n\n");

		System.out.println("Ausgabe mit Lamda Expression");
		bewertungenAusgebenMitLamdaExpression();
	}
	
	public void bewertungenAusgebenMitForeach() {
		for(Bewertung b: bewertungen) {
			System.out.println(b.toString());
		}
	}
	
	public void bewertungenAusgebenMitLamdaExpression() {
		bewertungen.forEach(b -> System.out.println(b));
	}
	
	public void bewertungenAusgebenMitIterator() {
		bewertungen.forEach(System.out::println);
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getKlasse() {
		return klasse;
	}

	public void setKlasse(String klasse) {
		this.klasse = klasse;
	}
}
