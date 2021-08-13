package Game.Entitys;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import Game.States.StateHandler;

public class Projectile extends Entity {
	
	private double speed;
	private double shootPX, shootPY;
	public boolean alive = true;
	
	public Projectile(StateHandler handler, double x, double y, double shootPX, double shootPY) {
		super(handler, x, y);
		speed = 15;
		this.shootPX = shootPX;
		this.shootPY = shootPY;
	}

	public void tick() {
		double diffX = x - shootPX;
		double diffY = y - shootPY;
		double distance = Math.sqrt(diffX*diffX + diffY*diffY);
		x += (-1/distance) * diffX * speed;
		y += (-1/distance) * diffY * speed;
		if(distance < speed) {
			alive = false;
		}
	}

	public void render(Graphics2D g) {
		g.setStroke(new BasicStroke(5));
		g.setColor(Color.pink);
		g.drawLine((int)x, (int)y, (int)x, (int)y);
		g.setStroke(new BasicStroke(1));
	}

}
