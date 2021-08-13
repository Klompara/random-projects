package bll;

public class Schueler {

	private String vorname;
	private String nachname;
	private int katnr;
	
	public Schueler(String vorname, String nachname, int katnr) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.katnr = katnr;
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
	public int getKatnr() {
		return katnr;
	}
	public void setKatnr(int katnr) {
		this.katnr = katnr;
	}
	@Override
	public String toString() {
		return "Schueler [vorname=" + vorname + ", nachname=" + nachname + ", katnr=" + katnr + "]";
	}
}
