package quadtree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bomb {
	
	private double x, y, dx, dy;
	private boolean explode = false;
	private int width = 5, height = 10;
	
	public Bomb(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		dy+=0.1;
		
		x += dx;
		y += dy;
	}
	
	public void render(Graphics2D g) {
		if(Main.normalBomb)
			g.setColor(Color.green.darker());
		else
			g.setColor(Color.red.darker());
		g.fillOval((int)x, (int)y, width, height);
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public void setExplode(boolean explode) { this.explode = explode; }
	public boolean isExplode() { return explode; }
	protected double getX() { return x; }
	protected double getY() { return y; }
}
