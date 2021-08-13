package bll;

import java.util.Date;

public class TourGuide extends Person {

	private int yearsOfExperience;
	
	public TourGuide(int id, String firstName, String lastName, Date birthDate, int yearsOfExperience) {
		super(id, firstName, lastName, birthDate);
		this.yearsOfExperience = yearsOfExperience;
	}
	
	public int getLevelOfExperience() {
		return yearsOfExperience;
	}

}
