package Game.Toolkit;

import java.awt.Graphics2D;

public class Functions {
	public static int getFontWidth(Graphics2D g, String s) {
		int fw = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
		return fw;
	}
	
	public static int getFontHeight(Graphics2D g, String s) {
		int fh = (int) g.getFontMetrics().getStringBounds(s, g).getHeight();
		return fh;
	}
	
	public static boolean isInteger(String s) {
		int[] numbers = new int[10];
		String[] sArray = s.split("");
		for(int i = 0; i <= 9; i++) {
			numbers[i] = i;
		}

		for(String str : sArray) {
			int isInt = 0;
			for(int i : numbers) {
				if(str.equalsIgnoreCase(Integer.toString(i))) {
					isInt++;
				}
			}
			if(isInt != 1){
				return false;
			}
		}
		
		return true;
	}
}
