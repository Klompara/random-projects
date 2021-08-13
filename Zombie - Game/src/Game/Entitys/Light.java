package Game.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import Game.Main;

public class Light {

	public int x;
	public int y;
	public int radius;
	public int lightpower;
	private int duration;
	private boolean timing;
	private int timer;
	private BufferedImage image;
	
	public Light(int x, int y, int radius, int lightpower, int duration) {
		this.duration = duration;
		this.timing = true;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.lightpower = lightpower;
		
		Point2D center = new Point(radius, radius);
		
		float[] dist = {0F, 1F};
		Color[] colors = {new Color(0, 0, 0, lightpower), new Color(0, 0, 0, 0)};
		
		image = new BufferedImage(radius*2, radius*2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		RadialGradientPaint rgp = new RadialGradientPaint(center, radius, dist, colors);
		g.setPaint(rgp);
		
		g.fillOval(0, 0, radius*2, radius*2);
	}
	
	public Light(int x, int y, int radius, int lightpower) {
		this.timing = false;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.lightpower = lightpower;
		
		Point2D center = new Point(radius, radius);
		
		float[] dist = {0F, 1F};
		Color[] colors = {new Color(0, 0, 0, lightpower), new Color(0, 0, 0, 0)};
		
		image = new BufferedImage(radius*2, radius*2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		RadialGradientPaint rgp = new RadialGradientPaint(center, radius, dist, colors);
		g.setPaint(rgp);
		
		g.fillOval(0, 0, radius*2, radius*2);
	}
	
	public void tick() {
		if(timing) {
			if(timer > duration) {
				Main.handler.lights.remove(this);
			}
			timer++;
		}
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, x-radius, y-radius, null);
	}
}
