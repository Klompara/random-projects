package POSMain;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		
		Student firstStudent = new Student("Hans", "Bauer", 55);
		Student secondStudent = new Student("Gerhard", "Steiner", 14);
		Student thirdStudent = new Student("Franz", "Lercher", 33);
		Student[] studentArray = {firstStudent, secondStudent, thirdStudent};
		
		for(int i = 0; i < studentArray.length; i++) {
			System.out.println("Name: " + studentArray[i].getFirstName() + " " + studentArray[i].getLastName() + ", ID: " + studentArray[i].getID());
		}
		
	}
	
}
