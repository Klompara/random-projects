package Prg.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Block extends Entity{

	public Block(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.darkGray);
		for(int i = 0; i < 3; i++)
			g.drawRect(x+i, y+i, 32-i*2, 32-i*2);
	}

	public Rectangle2D BLeft() {
		return new Rectangle(x,y+2,2,28);
	}
	public Rectangle2D BRight() {
		return new Rectangle(x+30,y+2,2,28);
	}
	public Rectangle2D BBottom() {
		return new Rectangle(x+2,y+30,28,2);
	}
	public Rectangle2D BTop() {
		return new Rectangle(x+2,y,28,2);
	}
}
