package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	
	private static long starttime;

	public static void main(String[] args) {
		starttime = System.nanoTime();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String[] array;
		Date dateobj;
		long currenttime;
		while(true) {
			currenttime = (System.nanoTime() - starttime)/1000000;
			//System.out.println(currenttime);
			if(currenttime > 1000) {
				starttime = System.nanoTime();
				dateobj = new Date();
				array = df.format(dateobj).split(":");
				System.out.println(Integer.toBinaryString(Integer.parseInt(array[0])) + ":" +
								   Integer.toBinaryString(Integer.parseInt(array[1])) + ":" +
								   Integer.toBinaryString(Integer.parseInt(array[2])));
			}
		}
	}
}
