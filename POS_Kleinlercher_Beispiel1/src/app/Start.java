package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import bll.Tour;
import bll.Traveller;

public class Start {

	public static void main(String[] args) {
		Tour t1 = new Tour("Citytrip Villach", "Villach", "Villach", 1, 1);
		Tour t2 = new Tour("Vienna fahrt", "Villach", "Wien", 1, 2);
		Tour t3 = new Tour("Africa wololo", "Villach", "Africa", 1, 7);
		Tour t4 = new Tour("Japan ching chong chung", "Villach", "Japan", 1, 20);
		Tour t5 = new Tour("Antarctica expidition", "Villach", "Antarctica", 1, 100);
		
		SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy"); 

		Traveller traveller1 = null;
		Traveller traveller2 = null;
		Traveller traveller3 = null;
		
		try {
			traveller1 = new Traveller(1, "Michael", "Kleinlercher", format.parse("08.02.2001"));
			traveller2 = new Traveller(2, "Franz", "Peter", format.parse("05.11.1986"));
			traveller3 = new Traveller(3, "Gerhard", "Steiner", format.parse("20.11.1940"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		traveller1.addTour(t1);
		traveller1.addTour(t2);
		traveller1.addTour(t3);
		
		traveller2.addTour(t2);
		traveller2.addTour(t3);
		traveller2.addTour(t4);
		
		traveller3.addTour(t3);
		traveller3.addTour(t4);
		traveller3.addTour(t5);
		
		System.out.println("traveller 1 average duration: " + traveller1.getAverageDurationLength());
		System.out.println("traveller 2 average duration: " + traveller2.getAverageDurationLength());
		System.out.println("traveller 3 average duration: " + traveller3.getAverageDurationLength());
		
		System.out.println("traveller 1 to String " + traveller1.toString());
		System.out.println("traveller 2 to String " + traveller2.toString());
		System.out.println("traveller 3 to String " + traveller3.toString());
		
		System.out.println("traveller 1 total time traveled: " + traveller1.getTotalDurationTimeTravelled());
		System.out.println("traveller 2 total time traveled: " + traveller2.getTotalDurationTimeTravelled());
		System.out.println("traveller 3 total time traveled: " + traveller3.getTotalDurationTimeTravelled());
		
		System.out.println("traveller 1 succes delete: " + traveller1.deleteTour(t1));
		System.out.println("traveller 2 succes delete: " + traveller2.deleteTour(t4));
		System.out.println("traveller 3 succes delete: " + traveller3.deleteTour(t5));
		System.out.println("traveller 3 succes delete: " + traveller3.deleteTour(t5));
		
		System.out.println("Tour ids:");
		System.out.println(t1.getId());
		System.out.println(t2.getId());
		System.out.println(t3.getId());
		System.out.println(t4.getId());
		System.out.println(t5.getId());
		
		System.out.println(traveller1.Information());
		System.out.println(t1.Information());
		
		System.out.println(traveller1.printAsCSV());
	}
	
}
