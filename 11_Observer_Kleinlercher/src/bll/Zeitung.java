package bll;

import java.util.ArrayList;

public class Zeitung implements Observable {

	private ArrayList<Observer> abbonenten = new ArrayList<Observer>();
	
	private ArrayList<Artikel> artikel = new ArrayList<Artikel>();
	
	public void neuerArtikelOnlineStellen(Artikel a) {
		artikel.add(a);
		notifyObserver();
	}

	@Override
	public void addObserver(Observer o) {
		abbonenten.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		abbonenten.remove(o);
	}

	@Override
	public void notifyObserver() {
		for(Observer o : abbonenten) 
			o.update(artikel.get(artikel.size()-1));
	}
}
