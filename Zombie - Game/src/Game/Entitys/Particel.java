package Game.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Game.Main;

public class Particel {
		
	private double x;
	private double y;
	private double dx;
	private double dy;
	private Color color;
	private int duration;
	private int radius;
	private int glowRadius;
	
	private long start;
	private long elapsed;
	
	private Light light;
	
	public Particel(double d1, double d2, double angle, Color c, int i1) {
		x = d1;
		y = d2;
		
		Random r = new Random();
		double speed = 2.5 + r.nextDouble() * 1.5;

		double rad = Math.toRadians(angle);
		dx = Math.cos(rad) * speed;
		dy = Math.sin(rad) * speed;
		
		color = c;
		duration = i1;
		//duration = 1000;
		start = System.nanoTime();
		//radius = Math.random() < 0.5 ? 1 : 2;
		radius = Main.random(3, 1);
		glowRadius = radius * 5;
		
		light = new Light((int)x, (int)y, radius*10, 75);
		Main.handler.lights.add(light);
	}
	
	public void tick() {
		if(elapsed > duration) {
			removethis();
		}
		
		light.x = (int)x;
		light.y = (int)y;
		
		elapsed = (System.nanoTime() - start) / 1000000;
		x += dx;
		y += dy;
		dx *= 0.99;
		dy *= 0.99;
	}
	
	public void render(Graphics2D g) {
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 128));
		g.fillOval((int) (x - radius), (int) (+ y - radius), radius * 2, radius * 2);
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 24));
		g.fillOval((int) (x - glowRadius), (int) (y - glowRadius), glowRadius * 2, glowRadius * 2);
	}
	
	private void removethis() {
		Main.handler.lights.remove(light);
		Main.handler.particel.remove(this);
	}
}