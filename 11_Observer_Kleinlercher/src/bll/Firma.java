package bll;

public class Firma implements Observer{

	@Override
	public void update(Artikel a) {
		System.out.println("neuer Artikel online autor: " + a.getAutor() + " titel: " + a.getTitel() + " link: " + a.getLink());
	}

	
}
