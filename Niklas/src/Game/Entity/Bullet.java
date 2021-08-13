package Game.Entity;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private int r;
	
	private int x;
	private int y;
	
	private float dx;
	private float dy;
	
	private float speed;
	
	public Bullet(int x, int y, float mouseX, float mouseY) {
		this.x = x;
		this.y = y;
		
		speed = 15;
		
		float diffX = x - mouseX;
		float diffY = y - mouseY;
		float distance = (float) Math.sqrt((diffX*diffX) + (diffY*diffY));
		dx = ((-1/distance) * diffX) * speed;
		dy = ((-1/distance) * diffY) * speed;
		
		r = 2;
	}

	public void tick() {
		x+=dx;
		y+=dy;
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((x - r), (y - r), 2 * r, 2 * r);
	}
}