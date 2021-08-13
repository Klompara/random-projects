package app;
import bll.*;
import bll.Animal.AnimalType; 

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animal cat;
		try {
			cat = new Animal("Cat", 4, AnimalType.getList().get(1)){
				public String toString(){
					return super.toString() +" " + this.gimmeMore()+" I'm a cat"; 
					
				}
				public String gimmeMore(){
					return "more!"; 
				}
			};
			System.out.println(cat.toString());
		} catch (FeetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
				
		Animal dog;
		try {
			dog = new Animal("Dog", 4, AnimalType.getList().get(1)){
				public String toString(){
					return super.toString() + " I'm a dog"; 
				}
				
			};
			System.out.println(dog.toString());
		} catch (FeetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}
	

}
