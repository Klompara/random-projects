package bll;

import java.util.Date;

public class Traveller extends Person implements Printable{

	private int preferences;
	private Tour[] tours = new Tour[20];
	
	public Traveller(int id, String firstName, String lastName, Date birthDate) {
		super(id, firstName, lastName, birthDate);
	}

	public int getPreferences() {
		return preferences;
	}
	
	public boolean addTour(Tour tour) {
		boolean rgw = false;
		for(int i = 0; i < tours.length && !rgw; i++) {
			if(tours[i] == null) {
				tours[i] = tour;
				rgw = true;
			}
		}
		return rgw;
	}
	
	public int getTotalDurationTimeTravelled() {
		int totalDuration = 0;
		for(int i = 0; i < tours.length; i++) {
			if(tours[i] != null) {
				totalDuration += tours[i].getDuration();
			}
		}
		return totalDuration;
	}
	
	public Tour getTourWithLongestDuration() {
		Tour longest = null;
		int longestDuration = 0;
		for(int i = 0; i < tours.length; i++) {
			if(tours[i] != null)
			{
				if(tours[i].getDuration() > longestDuration) {
					longestDuration = tours[i].getDuration();
					longest = tours[i];
				}
			}
		}
		return longest;
	}
	
	public double getAverageDurationLength() {
		double summe = 0;
		int divider = 0;
		
		for(int i = 0; i < tours.length && tours[i] != null; i++) {
			if(tours[i] != null) {
				summe += tours[i].getDuration();
				divider++;
			}
		}
		
		return (double)(summe/divider);
	}
	
	public boolean deleteTour(Tour tour) {
		boolean succesfull = false;
		for(int i = 0; i < tours.length; i++) {
			if(tours[i] != null) {
				if(tours[i] == tour) {
					for(int j = i; j < tours.length-1; j++) {
						tours[j] = tours[j+1];
					}
					succesfull = true;
				}
			}
		}
		return succesfull;
	}
	
	public String Information() {
		String rgw = "this class has\nint Preferences\nTour[] (Array) tours\nIs a Subclass of Persons with informations like Birthday, firstname, ect\n";
		rgw = rgw + "Functions:\naddTour(Tour) (return boolean) returns if succesfull added tour\ngetTotalDurationTimeTravelled() returns int of the full amount of days traveling\n";
		rgw = rgw + "getTourWithLongestDuration() returns int with the longest duration of one of its tours\ngetAverageDurationLength() return double with the average tour duration\n";
		rgw = rgw + "deleteTour(tour) returns boolean if succesful deleted tour";
		return rgw;
	}
	
	public boolean tourarrayContainsTour(Tour t) {
		boolean rgw = false;
		for(int i = 0; i < tours.length; i++) {
			if(tours[i] != null)
			{
				if(tours[i] == t)
					rgw = true;
			}
		}
		return rgw;
	}
	
	public String toString() {
		return super.toString();
	}

	public String printAsCSV() {
		String rgw = "int_preferences;";
		for(int i = 0; i < tours.length; i++) {
			if(tours[i] != null) {
				rgw += "Tour_" + tours[i] + ";";
			}
		}
		return rgw;
	}

	public String printAsHTML() {
		return null;
	}
}
