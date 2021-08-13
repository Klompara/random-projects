package bll;

public class Adresse {
	private boolean hauptwohnsitz; 
	private String strasse, ort; 
	private int hausnummer, plz;
	
	public Adresse(boolean hauptwohnsitz, String strasse, String ort, int hausnummer, int plz) {
		super();
		this.hauptwohnsitz = hauptwohnsitz;
		this.strasse = strasse;
		this.ort = ort;
		this.hausnummer = hausnummer;
		this.plz = plz;
	}

	public boolean isHauptwohnsitz() {
		return hauptwohnsitz;
	}

	public void setHauptwohnsitz(boolean hauptwohnsitz) {
		this.hauptwohnsitz = hauptwohnsitz;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	@Override
	public String toString() {
		return "Adresse [hauptwohnsitz=" + hauptwohnsitz + ", strasse=" + strasse + ", ort=" + ort + ", hausnummer="
				+ hausnummer + ", plz=" + plz + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hausnummer;
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result + plz;
		result = prime * result + ((strasse == null) ? 0 : strasse.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		if (hausnummer != other.hausnummer)
			return false;
		if (ort == null) {
			if (other.ort != null)
				return false;
		} else if (!ort.equals(other.ort))
			return false;
		if (plz != other.plz)
			return false;
		if (strasse == null) {
			if (other.strasse != null)
				return false;
		} else if (!strasse.equals(other.strasse))
			return false;
		return true;
	} 
	
	

}
