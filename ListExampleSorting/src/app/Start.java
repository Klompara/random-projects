package app;

import java.util.*; 

import bll.Student;
import bll.StudentIdComparator;

public class Start {

	public static void main(String[] args) {
		Student a = new Student( 1, "Adam", "Berger", "Villach"); 
		Student b = new Student (2, "Berta", "Hofer", "Klagenfurt"); 
		Student c = new Student (3, "Chris", "Gruber", "Spittal"); 
		List<Student> list = new ArrayList<Student>(); 
		ListIterator<Student> it = null; 
		list.add(a); 
		list.add(b); 
		list.add(c); 
		it = list.listIterator(); 
		System.out.println(it.hasPrevious()); 
		System.out.println("Unsortierte Liste");
		list.forEach(s -> System.out.println(s));
		System.out.println("Sortierung LastName (natural)");
		list.sort(null);
		list.forEach(s -> System.out.println(s));
		
		Comparator<Student> compHomeTown = new Comparator<Student>(){

			@Override
			public int compare(Student o1, Student o2) {
				return o1.getHomeTown().compareTo(o2.getHomeTown()); 
			}
			
		}; 
		
		list.sort( new StudentIdComparator());
		System.out.println("Sortierung nach Id");
		list.forEach(s -> System.out.println(s));
			
		list.sort( compHomeTown);
		System.out.println("Sortierung nach HomeTown");
		list.forEach(s -> System.out.println(s));
		
		System.out.println("Sortierung nach FirstName");
		list.sort((Student s1, Student s2) -> s1.getFirstName().compareTo(s2.getFirstName()));
		list.forEach(s -> System.out.println(s));
		
		Collections.sort(list, compHomeTown);
				
	}

}
