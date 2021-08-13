package Game.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Block {
	private int x, y;
	private int width, height;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		width = 32;
		width = 32;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.drawRect(x, y, width, height);
	}
	
	public void tick() {
		
	}
	
	public Rectangle2D BTop() { return new Rectangle((int)x+3, (int)y, width-6, 3); }
	public Rectangle2D BBottom() { return new Rectangle((int)x+3, (int)y+height-3, width-6, 3); }
	public Rectangle2D BRight() { return new Rectangle((int)x+width-3, (int)y+3, 3, height-6); }
	public Rectangle2D BLeft() { return new Rectangle((int)x, (int)y+3, 3, height-6); }
}
