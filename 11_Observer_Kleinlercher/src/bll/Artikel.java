package bll;

public class Artikel {
	private String titel;
	private String autor;
	private String link;
		
	public Artikel(String titel, String autor, String link) {
		this.titel = titel;
		this.autor = autor;
		this.link = link;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
	
}
