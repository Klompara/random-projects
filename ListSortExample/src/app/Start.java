package app;

import bll.Student;
import bll.StudentIdComparator;

import java.util.*;
public class Start {

	public static void main(String[] args) {

	   Student a = new Student(1, "Adam", "Sauer", "Hermagor City"); 
	   Student b = new Student(2, "Berta", "Perger", "Marktgemeinde Finkenstein"); 
	   Student c = new Student(3, "Chris", "Kaos", "Villach"); 
	   List<Student> list = new ArrayList<Student>(); 
	   
	   list.add(a); 
	   list.add(b); 
	   list.add(c); 
	   
	   //list.forEach(System.out::println);
	   list.forEach(s -> System.out.println(s));
	   list.sort(null);
	   System.out.println("Liste nach Sortieren: "); 
	   System.out.println("=====================");
	   list.forEach(s -> System.out.println(s));
	   
	   System.out.println("Liste nach zweitem Sortieren: "); 
	   System.out.println("=====================");
	   list.sort( new StudentIdComparator());
	   list.forEach( s -> System.out.println(s));
	   
	   Comparator<Student> cmpHomeTown = new Comparator<Student>(){

		@Override
		public int compare(Student o1, Student o2) {
			return o1.getHomeTown().compareTo(o2.getHomeTown()); 
		}
		   
	   };
	   System.out.println("Liste nach drittem Sortieren: "); 
	   System.out.println("=============================");
	   list.sort( cmpHomeTown);
	   list.forEach( s -> System.out.println(s));
	   
	   System.out.println("Liste nach viertem Sortieren: "); 
	   System.out.println("=============================");
	   list.sort( (Student s1, Student s2) -> s1.getFirstName().compareTo(s2.getFirstName()));
	   list.forEach( s -> System.out.println(s));
	   
	   list.sort(cmpHomeTown.reversed());
	   Collections.sort(list, Collections.reverseOrder());
	}

}
