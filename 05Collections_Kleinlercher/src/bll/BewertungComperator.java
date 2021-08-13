package bll;

import java.util.Comparator;

public class BewertungComperator implements Comparator<Bewertung>{

	public int compare(Bewertung b1, Bewertung b2) {
		return b1.getGegenstand().compareTo(b2.getGegenstand());
	}
}
