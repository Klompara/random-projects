package app;

import bll.Artikel;
import bll.Einzelperson;
import bll.Familie;
import bll.Firma;
import bll.Zeitung;

public class Start {

	public static void main(String[] args) {
		Zeitung z = new Zeitung();
		Einzelperson e = new Einzelperson();
		Familie f = new Familie();
		Firma fi = new Firma();
		z.addObserver(e);
		z.addObserver(f);
		z.neuerArtikelOnlineStellen(new Artikel("erster Artikel", "das ist der Autor", "https:\\dasistdieurl.com"));
		z.addObserver(fi);
		z.neuerArtikelOnlineStellen(new Artikel("zweiter Artikel", "das ist der zweite Autor", "https:\\dasistdie2.url.com"));
		z.removeObserver(e);
		z.neuerArtikelOnlineStellen(new Artikel("irgend ein Artikel", "noch ein Autor", "https:\\dasistdie3.url.com"));
	}

}
