package PacMan.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Block {
	public int x;
	public int y;
	
	private int type;
	
	private int width;
	private int height;
	
	public Block(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		
		width = 32;
		height = 32;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		if(type == 0) {
			g.setColor(Color.blue.brighter());
			g.fillRect(x, y, width, height);
		}else if(type == 1) {
			g.setColor(Color.gray.brighter());
			g.fillRect(x, y, width, height);
		}
	}
	
	public Rectangle2D BLeft() { return new Rectangle(x, y+3, 3, height-6); }
	public Rectangle2D BTop() { return new Rectangle(x+3, y, width-6, 3); }
	public Rectangle2D BRight() { return new Rectangle(x+width-3, y+3, 3, height-6); }
	public Rectangle2D BBottom() { return new Rectangle(x+3, y+height-3, width-6, 3); }
}
