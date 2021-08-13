package bll;

import java.util.Date;

public class Person {

	private String firstName;
	private String lastName;
	private Date birthDate;
	private int id;
	
	public Person(int id, String firstName, String lastName, Date birthDate) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
	}
	
	public Date getBirthdate() {
		return birthDate;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public String toString() {
		return getFullName() + " " + getBirthdate().toString() + " " + this.id;
	}
}
