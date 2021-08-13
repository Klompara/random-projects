package bll;

import java.util.ArrayList;
import java.util.Date;

import util.DateHelper;

public class Fuﬂballer {

	private String vorname;
	private String nachname;
	private VEREINE verein;
	private boolean gesperrt;
	private Date eintrittsdatum;

	public enum VEREINE {
		WAC, RAPID_WIEN
	}

	public Fuﬂballer(String vorname, String nachname, VEREINE verein, boolean gesperrt, Date eintrittsdatum) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.verein = verein;
		this.gesperrt = gesperrt;
		this.eintrittsdatum = eintrittsdatum;
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

	public VEREINE getVerein() {
		return verein;
	}

	public void setVerein(VEREINE verein) {
		this.verein = verein;
	}

	public boolean isGesperrt() {
		return gesperrt;
	}

	public void setGesperrt(boolean gesperrt) {
		this.gesperrt = gesperrt;
	}

	public Date getEintrittsdatum() {
		return eintrittsdatum;
	}

	public void setEintrittsdatum(Date eintrittsdatum) {
		this.eintrittsdatum = eintrittsdatum;
	}

	public static String[] getAllVereine() {
		String[] rgw = new String[VEREINE.values().length];
		for (int i = 0; i < VEREINE.values().length; i++) {
			rgw[i] = VEREINE.values()[i].toString();
		}
		return rgw;
	}

	public static Object[][] fuﬂballerToArray(ArrayList<Fuﬂballer> list) {
		DateHelper dh = DateHelper.getInstance();
		Object[][] rgw = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			Fuﬂballer f = list.get(i);
			rgw[i][0] = f.getVorname();
			rgw[i][1] = f.getNachname();
			rgw[i][2] = f.getVerein().toString();
			rgw[i][3] = f.isGesperrt();
			rgw[i][4] = dh.dateFormat(f.getEintrittsdatum());
		}
		return rgw;
	}

}
