package bll;

import java.util.Date;

public class Reisetag {

	private Date datum;
	private String stadt;
	private double preis;
	private String beschreibung;
	public Reisetag(Date datum, String stadt, double preis, String beschreibung) {
		super();
		this.datum = datum;
		this.stadt = stadt;
		this.preis = preis;
		this.beschreibung = beschreibung;
	}
	public String getDatum() {
		return datum.toLocaleString().split(" ")[0];
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getStadt() {
		return stadt;
	}
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public String toString() {
		return getDatum() + ", " + stadt + ", " + String.valueOf(preis);
	}
}
