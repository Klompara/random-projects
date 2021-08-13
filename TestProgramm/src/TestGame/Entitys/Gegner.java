package TestGame.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import TestGame.Main.Handler;

public class Gegner {
	
	private double x;
	private double y;
	
	private double dx;
	private double dy;
	
	private int Height;
	private int Width;
	
	private Handler handler;
	
	public Gegner(Handler handler, double x, double y) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		
		this.Height = 32;
		this.Width = 32;
	}
	
	public void tick() {
		// Movement
		int px = handler.spieler.getX();
		int py = handler.spieler.getY();
		
		double diffX = x - px;
		double diffY = y - py;
		double distance = (Math.sqrt(diffX*diffX + diffY*diffY));
		dx = (-1/distance) * diffX;
		dy = (-1/distance) * diffY;
		
		x += dx;
		y += dy;
		
		colission();
	}
	
	
	private void colission() {
		// Collision Player
		Player p = handler.spieler;
		while(p.BLeft().intersects(BRight())) {
			x--;
		}
		while(p.BRight().intersects(BLeft())) {
			x++;
		}
		while(p.BTop().intersects(BBottom())) {
			y--;
		}
		while(p.BBottom().intersects(BTop())) {
			y++;
		}
		while(p.BLeft().intersects(BBottom())) {
			y--;
		}
		while(p.BRight().intersects(BBottom())) {
			y--;
		}
		while(p.BLeft().intersects(BTop())) {
			y++;
		}
		while(p.BRight().intersects(BTop())) {
			y++;
		}
		
		// Collision Gegner
		for(int i = 0; i < handler.gegner.size(); i++) {
			Gegner g = handler.gegner.get(i);
			
			if(g != this) {
				while(g.BLeft().intersects(BRight())) {
					x--;
				}
				while(g.BRight().intersects(BLeft())) {
					x++;
				}
				while(g.BTop().intersects(BBottom())) {
					y--;
				}
				while(g.BBottom().intersects(BTop())) {
					y++;
				}
				while(g.BLeft().intersects(BTop())) {
					y++;
				}
				while(g.BRight().intersects(BTop())) {
					y++;
				}
				while(g.BTop().intersects(BLeft())) {
					y--;
				}
				while(g.BTop().intersects(BRight())) {
					y--;
				}
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 32, 32);
	}
	
	public Rectangle2D BTop() {
		return new Rectangle((int)x+4, (int)y, Width-8, 4);
	}
	public Rectangle2D BBottom() {
		return new Rectangle((int)x+4, (int)y+Height-4, Width-8, 4);
	}
	public Rectangle2D BLeft() {
		return new Rectangle((int)x, (int)y+4, 4, Height-8);
	}
	public Rectangle2D BRight() {
		return new Rectangle((int)x+Width-4, (int)y+4, 4, Height-8);
	}
}
