package app;
import java.io.IOException;

import bll.*; 

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Student s1 = new Student( 1, "Hans", "Huber", "hhuber", "pwd123!");
		Student s2 = null; 
		
		try {
			SerializationHelper.Serializable( s1, "Student1");
			
			s2 =(Student)SerializationHelper.Deserializable("Student1");
			System.out.println(s2);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
