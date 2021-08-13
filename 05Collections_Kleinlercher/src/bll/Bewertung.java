package bll;

public class Bewertung {

	private String gegenstand;
	private int note;
	
	public Bewertung(String gegenstand, int note) {
		this.gegenstand = gegenstand;
		this.note = note;
	}

	public String getGegenstand() {
		return gegenstand;
	}

	public void setGegenstand(String gegenstand) {
		this.gegenstand = gegenstand;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Bewertung [gegenstand=" + gegenstand + ", note=" + note + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gegenstand == null) ? 0 : gegenstand.hashCode());
		result = prime * result + note;
		return result;
	}
	
	public int compareTo(Bewertung b) {
		return this.gegenstand.compareTo(b.getGegenstand());
	} 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bewertung other = (Bewertung) obj;
		if (gegenstand == null) {
			if (other.gegenstand != null)
				return false;
		} else if (!gegenstand.equals(other.gegenstand))
			return false;
		if (note != other.note)
			return false;
		return true;
	}
}
