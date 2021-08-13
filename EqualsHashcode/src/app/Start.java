package app;
import java.util.HashSet;
import java.util.Set;

import bll.*; 

public class Start {

	public static void main(String[] args) { 
		// TODO Auto-generated method stub 
		Set<Adresse> adressen = new HashSet<Adresse>(); 
		Adresse a1 = new Adresse(true, "Fichtenweg", "Villach", 11, 9500); 
		Adresse a2 = new Adresse(false, "Fichtenweg", "Villach", 11, 9500); 
		
		System.out.println(adressen.add(a1)); 
		System.out.println(adressen.add(a2)); 
	}
}
