package bll;

public class Buch implements Comparable<Buch> {
	private String iSBN;
	private String title;
	private String publisher;
	private double salePrice;
	private String authorLastName;
	private String authorFirstName;
	private int amount;
	public Buch(String iSBN, String title, String publisher, double salePrice, String authorLastName,
			String authorFirstName, int amount) {
		super();
		this.iSBN = iSBN;
		this.title = title;
		this.publisher = publisher;
		this.salePrice = salePrice;
		this.authorLastName = authorLastName;
		this.authorFirstName = authorFirstName;
		this.amount = amount;
	}
	public String getISBN() {
		return iSBN;
	}
	public String getTitle() {
		return title;
	}
	public String getPublisher() {
		return publisher;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public String getAuthorLastName() {
		return authorLastName;
	}
	public String getAuthorFirstName() {
		return authorFirstName;
	}
	public int getAmount() {
		return amount;
	}
	public int compareTo(Buch o) {
		return this.title.compareTo(o.getTitle());
	}
	@Override
	public String toString() {
		return "Buch [iSBN=" + iSBN + ", title=" + title + ", publisher=" + publisher + ", salePrice=" + salePrice
				+ ", authorLastName=" + authorLastName + ", authorFirstName=" + authorFirstName + ", amount=" + amount
				+ "]";
	}
	
}
