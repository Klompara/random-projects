package PacMan.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import PacMan.Handler;

public class Punkt {
	private int x;
	private int y;
	
	public int type;
	
	private float timer = 0;
	private boolean grow = false;
	
	private BufferedImage texture;
	
	public Punkt(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		
		timer = 0;
		
		if(type == 0) { texture = Handler.Apfel;
		} else if (type == 1) { texture = Handler.Viagra;}
	}
	
	public void render(Graphics2D g) {
		if(type == 0) {
			g.drawImage(texture, x+16-5, y+16-5, 10, 10, null);
		}
		
		if(type == 1) {
			g.drawImage(texture, (int)(x+16-((20+timer)/2)), (int)(y+16-((20+timer)/2)), (int)(20+timer), (int)(20+timer), null);
		}
	}
	
	public void tick() {
		if(grow) timer+=0.25;
		if(timer == 5)grow = false;
		
		if(!grow) timer-=0.25;
		if(timer == -1)grow = true;
	}
	
	public Rectangle2D bound() {
		return new Rectangle(x+15, y+15, 2, 2);
	}
}
