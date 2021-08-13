package POSMain;

public class Student {

	private String FirstName, LastName;
	private int ID;
	
	public Student(String FirstName, String LastName, int ID) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.ID = ID;
	}

	public int getID() { return ID; }
	public void setID(int ID) { this.ID = ID; }
	
	public String getFirstName() { return FirstName; }
	public void setFirstName(String FirstName) { this.FirstName = FirstName; }
	
	public String getLastName() { return LastName; }
	public void setLastName(String LastName) { this.LastName = LastName; }
	
}
