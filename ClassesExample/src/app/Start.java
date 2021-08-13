package app;
import bll.*; 

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animal cat = new Animal( "cat", 4){
		   int i = 0; 
			public String toString(){
				return super.toString() + " I'm a cat" + this.gimmeMore() + i; 
			}
			String gimmeMore(){
				return " whatever"; 
			}
		}; 
		Animal centipede = new Animal( "centipede", 1000){
			
			public String toString(){
				return super.toString() + " I'm a centipede"; 
			}
			String gimmeMore(){
				return this.toString()+(" whatever"); 
			}
		}; 
        System.out.println( cat);
        System.out.println( centipede);
        
	}

}
