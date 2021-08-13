package Game.Entitys;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Base extends Entity {
	
	private int width, height;
	
	public Base(double x, double y, boolean isHTL) {
		super(x, y);
		height = 80;
		if(isHTL) {
			width = height;
		}else{
			width = -height;
		}
	}

	public void tick() {
		
	}

	public void render(Graphics2D g) {
		
	}
	
	protected Rectangle2D BTop() { return new Rectangle((int)x+3, (int)y, width-6, 3); }
	protected Rectangle2D BBottom() { return new Rectangle((int)x+3, (int)y+height-3, width-6, 3); }
	protected Rectangle2D BLeft() { return new Rectangle((int)x, (int)y+3, 3, height-6); }
	protected Rectangle2D BRight() { return new Rectangle((int)x+width-3, (int)y+3, 3, height-6); }
}
