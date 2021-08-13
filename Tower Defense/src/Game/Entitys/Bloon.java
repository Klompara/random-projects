package Game.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;

import Game.States.StateHandler;

public class Bloon extends Entity {

	private int reachedWayP = -1;
	private double distToNextWayP;
	private double dx, dy, speed, diffX, diffY;
	
	public Bloon(StateHandler handler, double x, double y) {
		super(handler, x, y);
		speed = 3;
	}

	public void tick() {
		if(distToNextWayP < speed) {
			System.out.println(reachedWayP);
			reachedWayP++;
			if(reachedWayP+1 == handler.play.wayPoints.size()) {
				reachedWayP = -1;
			}
		}
		WayPoint p = handler.play.wayPoints.get(reachedWayP+1);
		diffX = x - p.x;
		diffY = y - p.y;
		distToNextWayP = Math.sqrt(diffX*diffX + diffY*diffY);
		dx = (-1/distToNextWayP) * diffX * speed;
		dy = (-1/distToNextWayP) * diffY * speed;
		
		x += dx;
		y += dy;
	}

	public void render(Graphics2D g) {
		g.setColor(Color.red);
		g.fillOval((int)x-16, (int)y-16, 32, 32);
	}

	protected int getReachedWayP() { return reachedWayP; }
	protected double getDistToNextWayP() { return distToNextWayP; }
}
