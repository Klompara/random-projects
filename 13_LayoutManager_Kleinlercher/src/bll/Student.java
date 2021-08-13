package bll;

public class Student {
	private int katNr;
	private String vorname;
	private String nachname;
	public Student(int katNr, String vorname, String nachname) {
		super();
		this.katNr = katNr;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	public int getKatNr() {
		return katNr;
	}
	public void setKatNr(int katNr) {
		this.katNr = katNr;
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
}
