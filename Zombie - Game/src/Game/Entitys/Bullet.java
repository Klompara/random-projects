package Game.Entitys;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Game.Handler;
import Game.Main;

public class Bullet {
	
	private double x;
	private double y;
	
	private int r;
	
	private double dx;
	private double dy;
	
	private double linelenght;
	
	private Light light;
	
	private int type;
	
	private int timer;
	
	public Bullet(int x, int y, double dx, double dy, int type) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		
		switch (type) {
		case 1: // Pistol
			linelenght = 1.5;
			r = 3;
			light = new Light(x, y, 45, 75);
			break;
		
		case 2: // Uzi
			linelenght = 1;
			r = 2;
			light = new Light(x, y, 20, 50);
			break;

		case 3: // Shotgun
			linelenght = 0.45;
			r = Main.random(3, 1);
			light = new Light(x, y, Main.random(r*25, r*5), Main.random(150, 20));
			break;
			
		case 4: // Granade
			linelenght = 2.3;
			r = 4;
			light = new Light(x, y, 50, 200);
			break;
			
		default:
			System.out.println("FEHLERHAFTE WAFFE");
			break;
		}
		
		Main.handler.lights.add(light);
	}
	
	public void tick() {
		timer++;
		if(type == 3) {
			dx -= dx/75;
			dy -= dy/75;
			if(timer > 50) removethis(Main.handler);
		}
		if(type == 4) {
			for(int i = 0; i < 3; i++) {
				Main.handler.particel.add(new Particel(x, y, Main.random(260, 280), Color.yellow, 60));
			}
			
			dx -= dx/40;
			dy -= dy/40;
			if(timer > 120){
				Main.handler.lights.add(new Light((int)x, (int)y, 150, 250, 60));
				for(int i = 0; i < Main.random(250, 150); i++) {
					Main.handler.particel.add(new Particel(x, y, Main.random(360, 0), Color.red, 1000));
					Main.handler.particel.add(new Particel(x, y, Main.random(360, 0), Color.orange, 900));
					Main.handler.particel.add(new Particel(x, y, Main.random(360, 0), Color.yellow, 800));
				}
				removethis(Main.handler);
			}
		}

		x += dx;
		y += dy;
		light.x = (int) x;
		light.y = (int) y;
		
		for(int i = 0; i < Main.handler.bloecke.size(); i++) {
			Block b = Main.handler.bloecke.get(i);
			if(Bounds().intersects(b.BBounds()) && b.getType() != 1) {
				if(type == 1) {
					for(int i2 = 0; i2 < Main.random(15, 5); i2++) {
						Main.handler.particel.add(new Particel(x, y, Main.random(360, 0), Color.yellow, 500));
					}
					removethis(Main.handler);
				}else if(type == 2) {
					for(int i2 = 0; i2 < Main.random(5, 1); i2++) {
						Main.handler.particel.add(new Particel(x, y, Main.random(360, 0), Color.yellow, 200));
					}
					removethis(Main.handler);
				}else if(type == 3) {
					for(int i2 = 0; i2 < Main.random(10, 5); i2++) {
						Main.handler.particel.add(new Particel(x, y, Main.random(360, 0), Color.yellow, 320));
					}
					removethis(Main.handler);
				}else{
					while(Bounds().intersects(b.BTop())){ y--; dy = 0; }
					while(Bounds().intersects(b.BLeft())) { x--; dx = 0; }
					while(Bounds().intersects(b.BBottom())) { y++; dy = 0; }
					while(Bounds().intersects(b.BRight())) { x++; dx = 0; }
				}
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.fillOval((int)x-r, (int)y-r, r*2, r*2);
		g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setColor(Color.yellow.brighter());
		g.drawLine((int)x, (int)y, (int)(x-(dx*linelenght)), (int)(y-(dy*linelenght)));
		g.setStroke(new BasicStroke(1));
	}
	
	public void removethis(Handler handler) {
		handler.lights.remove(light);
		handler.bullets.remove(this);
	}

	public double getX() { return x; }
	public void setX(double x) { this.x = x; }
	public double getY() { return y; }
	public void setY(double y) { this.y = y; }
	
	public Rectangle2D Bounds() {
		return new Rectangle((int)(x-r), (int)(y-r), r*2, r*2);
	}
}
