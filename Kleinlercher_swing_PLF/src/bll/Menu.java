package bll;

public class Menu {

	public enum WOCHENTAG {
		MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG
	}

	private WOCHENTAG wochentag;
	private String vorspeise;
	private String hauptspeise;
	private String nachspeise;
	private int anzahlBestellt = 0;
	private int anzahlStorniert = 0;

	public Menu(WOCHENTAG tag, String vorspeise, String haupspeise, String nachspeise) {
		this.vorspeise = vorspeise;
		this.hauptspeise = haupspeise;
		this.wochentag = tag;
		this.nachspeise = nachspeise;
	}

	public WOCHENTAG getWochentag() {
		return wochentag;
	}

	public void setWochentag(WOCHENTAG wochentag) {
		this.wochentag = wochentag;
	}

	public String getVorspeise() {
		return vorspeise;
	}

	public void setVorspeise(String vorspeise) {
		this.vorspeise = vorspeise;
	}

	public String getHauptspeise() {
		return hauptspeise;
	}

	public void setHauptspeise(String hauptspeise) {
		this.hauptspeise = hauptspeise;
	}

	public String getNachspeise() {
		return nachspeise;
	}

	public void setNachspeise(String nachspeise) {
		this.nachspeise = nachspeise;
	}

	public String toString() {
		return wochentag.name() + ": " + vorspeise + ", " + hauptspeise + ", " + nachspeise;
	}

	public void addBestellung() {
		anzahlBestellt++;
	}

	public void addStornierung() {
		if (anzahlStorniert + 1 <= anzahlBestellt)
			anzahlStorniert++;
	}

	public int getTotalAmount() {
		return anzahlBestellt - anzahlStorniert;
	}

	public String getMenuText() {
		String rgw = "";
		if (anzahlBestellt == 0) {
			rgw = "Das Menü für den " + wochentag.name() + " wurde bisher noch nicht bestellt";
		} else if (anzahlBestellt > 0 && anzahlStorniert == 0) {
			rgw = "Das Menü für den " + wochentag.name() + " wurde bisher " + anzahlBestellt + " mal bestellt.";
		} else {
			rgw = "Das Menü für den " + wochentag.name() + " wurde bisher " + anzahlBestellt + " mal bestellt. Es wurde allerdings auch schon " + anzahlStorniert + " mal storniert. Die aktuelle Bestellanzahl liegt bei " + getTotalAmount();
		}
		return rgw;
	}
}
