package quadtree;

import java.awt.Color;
import java.awt.Graphics2D;

public class Car {
	
	private double x1, y1, x2, y2;
	private double dx1, dy1, dx2, dy2;
	private final double max = 45, min = 15;
	
	public Car(double x, double y) {
		this.x1 = x;
		this.y1 = y;
		this.y2 = y1;
		this.x2 = x1+30;
	}
	
	public void tick() {
		dy1 += 0.1;
		dy2 += 0.1;
		
		x1 += dx1;
		x2 += dx2;
		y1 += dy1;
		y2 += dy2;
		
		double diffx = x1 - x2;
		double diffy = y1 - y2;
		double distance = Math.sqrt(diffx*diffx + diffy*diffy);
		
		if(distance > max) {
			x1 += (-1/distance)*diffx;
			y1 += (-1/distance)*diffy;
			x2 += (1/distance)*diffx;
			y2 += (1/distance)*diffy;
		}else if(distance < min) {
			x1 += (1/distance)*diffx * distance;
			y1 += (1/distance)*diffy * distance;
			x2 += (-1/distance)*diffx * distance;
			y2 += (-1/distance)*diffy * distance;
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.yellow);
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		g.drawLine((int)x1, (int)y1-10, (int)x2, (int)y2-10);
		g.drawLine((int)x1, (int)y1, (int)x1, (int)y1-10);
		g.drawLine((int)x2, (int)y2, (int)x2, (int)y2-10);
	}

	public double getX1() { return x1; } 
	public void setX1(double x1) { this.x1 = x1; } 

	public double getY1() { return y1; } 
	public void setY1(double y1) { this.y1 = y1; } 

	public double getX2() { return x2; } 
	public void setX2(double x2) { this.x2 = x2; } 

	public double getY2() { return y2; } 
	public void setY2(double y2) { this.y2 = y2; } 

	public double getDx1() { return dx1; } 
	public void setDx1(double dx1) { this.dx1 = dx1; } 

	public double getDy1() { return dy1; } 
	public void setDy1(double dy1) { this.dy1 = dy1; } 

	public double getDx2() { return dx2; } 
	public void setDx2(double dx2) { this.dx2 = dx2; } 

	public double getDy2() { return dy2; } 
	public void setDy2(double dy2) { this.dy2 = dy2; } 

}
