package bll;

public class Buch {

	private String isbn;
	private String title;
	private String publisher;
	private double salePrice;
	private String AuthorLastName;
	private String AuthorFirstName;
	private int amount;

	public Buch(String isbn, String title, String publisher, double salePrice, String authorLastName,
			String authorFirstName, int amount) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.publisher = publisher;
		this.salePrice = salePrice;
		AuthorLastName = authorLastName;
		AuthorFirstName = authorFirstName;
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public String getAuthorLastName() {
		return AuthorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		AuthorLastName = authorLastName;
	}

	public String getAuthorFirstName() {
		return AuthorFirstName;
	}

	public void setAuthorFirstName(String authorFirstName) {
		AuthorFirstName = authorFirstName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String toString() {
		return "Buch [isbn=" + isbn + ", publisher=" + publisher + ", salePrice=" + salePrice + ", AuthorLastName="
				+ AuthorLastName + ", AuthorFirstName=" + AuthorFirstName + ", amount=" + amount + "]";
	}

}
