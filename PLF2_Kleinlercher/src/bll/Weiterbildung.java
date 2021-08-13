package bll;

public class Weiterbildung {

	private String kurskennzahl;
	private String bezeichnung;
	private String ort;
	private int dauerInTagen;
	
	public Weiterbildung(String kurskennzahl, String bezeichnung, String ort, int dauerInTagen) {
		this.kurskennzahl = kurskennzahl;
		this.bezeichnung = bezeichnung;
		this.ort = ort;
		this.dauerInTagen = dauerInTagen;
	}

	public String getKurskennzahl() {
		return kurskennzahl;
	}

	public void setKurskennzahl(String kurskennzahl) {
		this.kurskennzahl = kurskennzahl;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getDauerInTagen() {
		return dauerInTagen;
	}

	public void setDauerInTagen(int dauerInTagen) {
		this.dauerInTagen = dauerInTagen;
	}

	@Override
	public String toString() {
		return "Weiterbildung [kurskennzahl=" + kurskennzahl + ", bezeichnung=" + bezeichnung + ", ort=" + ort
				+ ", dauerInTagen=" + dauerInTagen + "]";
	}
	
	
}
