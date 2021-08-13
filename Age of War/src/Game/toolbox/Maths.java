package Game.toolbox;

import java.awt.Graphics2D;

public class Maths {
	public static int getFontWidth(String s, Graphics2D g) {
		return (int) (g.getFontMetrics().getStringBounds(s, g).getWidth());
	}
	
	public static int getFontHeight(String s, Graphics2D g) {
		return (int) (g.getFontMetrics().getStringBounds(s, g).getHeight());
	}
	
	public static int limit(int var, int min, int max) {
		if(var < min) {
			var = min;
		}else if(var > max) {
			var = max;
		}
		return var;
	}
}
