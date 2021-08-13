package Simulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Trail {
	private int x, nx;
	private int y, ny;
	
	private int timer = 0;
	private int duration = 800;
	
	private Ball ball;
	
	public Trail(int x, int y, Ball ball) {
		this.x = x;
		this.y = y;
		this.nx = (int) (x + ball.getDx());
		this.ny = (int) (y + ball.getDy());
		this.ball = ball;
	}
	
	public void tick() {
		timer++;
		if(timer >= duration) { ball.trails.remove(this); }
	}
	
	public void render(Graphics2D g) {
		g.setStroke(new BasicStroke(3));
		g.setColor(new Color(0,32,0));
		g.drawLine(x, y, nx, ny);
		g.setStroke(new BasicStroke(1));
	}
}
