package programm;

import java.awt.AWTException;
import java.awt.Robot;

public class Class {

	public static void main(String[] args) {
		int timeMilliseconds = Integer.parseInt(args[0]);
		int x = 0, y = 0;
		if(args.length >= 3)
		{
			System.out.println("Funkt");
			x = Integer.parseInt(args[1]);
			y = Integer.parseInt(args[2]);
		}
		
		System.out.println("Millis: " + timeMilliseconds);
		long currentTime = System.currentTimeMillis();
		Robot r = null;
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		while(System.currentTimeMillis()-currentTime < timeMilliseconds) {
			System.out.println(System.currentTimeMillis()-currentTime);
			r.mouseMove(x, y);
		}
	}

}
