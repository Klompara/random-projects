package TestGame.Entitys;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import TestGame.Main.Game;
import TestGame.Main.Handler;

public class Kugel {
	
	private double x;
	private double y;
	
	private double r;
	
	private double dx;
	private double dy;
	
	private double gravity;
	private double maxfall;
	
	private boolean falling = false;
	
	private Handler handler;
	
	public Kugel(double x, double y, Handler handler) {
		this.x = x;
		this.y = y;
		
		this.handler = handler;
		
		this.gravity = 1.5;
		this.maxfall = 5.5;
		
		r = 20;
	}
	
	public void tick() {
		if(falling) {
			dy += gravity;
			if(dy > maxfall) dy = maxfall;
		}
		
		if (y+48*2-4 > Game.HEIGHT){
			y = Game.HEIGHT-48*2-4;
			falling = false;
			dy = 0;
		}else {
			
		}
		
		for(int i = 0; i < handler.kugeln.size(); i++) {
			Kugel k = handler.kugeln.get(i);
			if(k != this) {
				int kx = (int) k.getX();
				int ky = (int) k.getY();
				int kr = (int) k.getR();
				
				double distx = kx - x;
				double disty = ky - y;
				double distance = Math.sqrt(distx*distx + disty*disty);
				
				if(distance < r + kr) {
					dx = (-2/distance) * distx;
					dy = (-2/distance) * disty;
				}else{
					//if(dx > 0) dx -= 0.02;
					//if(dx < 0) dx += 0.02;
				}
			}
		}
		
		x+=dx;
		y+=dy;
	}
	
	public void shoot(double tempx, double tempy) {
		double diffx = x - tempx;
		double diffy = y - tempy;
		double distance = Math.sqrt(tempx*tempx + tempy*tempy);
		dx = (-10/distance) * diffx;
		dy = (-10/distance) * diffy;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.green);
		g.fillOval((int)(x-r), (int)(y-r), (int)r*2, (int)r*2);
		g.setColor(Color.green.darker());
		g.setStroke(new BasicStroke(7));
		g.drawOval((int)(x-r), (int)(y-r), (int)r*2, (int)r*2);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}
}
