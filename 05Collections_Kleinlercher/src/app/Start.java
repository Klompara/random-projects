package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import bll.Bewertung;
import bll.Semesterzeugnis;

public class Start {

	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
		Semesterzeugnis s;
		try {
			s = new Semesterzeugnis(format.parse("15.10.2017"), "Michael", "Kleinlercher");
			s.bewertungHinzufuegen(new Bewertung("POS", 2));
			s.bewertungHinzufuegen(new Bewertung("BSPK", 1));
			s.bewertungHinzufuegen(new Bewertung("Englisch", 4));
			s.bewertungHinzufuegen(new Bewertung("DBI", 3));
			s.bewertungHinzufuegen(new Bewertung("NVS", 2));
			System.out.println("\n");
			s.bewertungenAusgebenMitForeach();
			System.out.println("\n");
			s.bewertungenAusgebenMitIterator();
			System.out.println("\n");
			s.bewertungenAusgebenMitLamdaExpression();
			System.out.println("\n");
			s.bewertungLoeschen(1);
			System.out.println("\n");
			s.bewertungLoeschen("Englisch");
			System.out.println("\n");
			s.listeSortierenAnonymousClass();
			s.listeSortierenComparable();
			s.listeSortierenComparator();
			s.listeSortierenLamda();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
