package Simulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Ball {
	
	public List<Trail> trails = new ArrayList<Trail>();
	
	private boolean mainball = false;
	private boolean enabled = false;
	private boolean intersect = false;
	
	private double dx, dy;
	private double x, y;
	
	private int size;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(ArrayList<Ball> balls) {
		double tempDX = 0;
		double tempDY = 0;
		for(int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			if(b != this && b.isEnabled() && enabled) {
				double diffX = x - b.getX();
				double diffY = y - b.getY();
				double distance = Math.sqrt(diffX*diffX + diffY*diffY);
				tempDX += ((-2/distance) * diffX * distance/2000 * b.getSize()/10)/size;
				tempDY += ((-2/distance) * diffY * distance/2000 * b.getSize()/10)/size;
			}
		}
		
		dx+=tempDX;
		dy+=tempDY;
		
		trails.add(new Trail((int)x, (int)y, this));
		for(int i = 0; i < trails.size(); i++) {
			Trail t = trails.get(i);
			t.tick();
		}
		
		x+=dx;
		y+=dy;
		
		collision(balls);
	}
	
	private void collision(ArrayList<Ball> balls) {
		for(int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			if(b != this && b.isEnabled() && enabled) {
				double diffX = x - b.getX();
				double diffY = y - b.getY();
				double distance = Math.sqrt(diffX*diffX + diffY*diffY);
				if(distance < size+b.getSize()) {
					if(MainLoop.collisionAtribute == 0) {
						double tempdx = dx;
						double tempdy = dy;
						dx = b.getDx();
						dy = b.getDy();
						b.setDx(tempdx);
						b.setDy(tempdy);
					}else if(MainLoop.collisionAtribute == 1) {
						size += b.getSize();
						balls.remove(b);
					} else {
						intersect = true;
						break;
					}
				}else{
					intersect = false;
				}
			}
		}
	}

	public void render(Graphics2D g) {
		if(intersect){
			g.setColor(Color.red);
		} else if(mainball) {
			g.setColor(Color.cyan);
		} else {
			g.setColor(Color.darkGray);
		}
		g.fillOval((int)x-size, (int)y-size, (int)size*2, (int)size*2);
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.black);
		g.drawOval((int)x-size, (int)y-size, (int)size*2, (int)size*2);
		g.setStroke(new BasicStroke(1));
		
		Rectangle r = new Rectangle();
		r.setBounds((int)x-size, (int)y-size, size*2, size*2);
		g.setColor(Color.black);
		g.draw(r);
	}

	public double getDx() { return dx; } 
	public void setDx(double dx) { this.dx = dx; } 

	public double getDy() { return dy; } 
	public void setDy(double dy) { this.dy = dy; } 

	public double getX() { return x; } 
	public void setX(double x) { this.x = x; } 

	public double getY() { return y; } 
	public void setY(double y) { this.y = y; } 

	public int getSize() { return size; } 
	public void setSize(int size) { this.size = size; }

	public boolean isMainball() { return mainball; }
	public void setMainball(boolean mainball) { this.mainball = mainball; }

	public boolean isEnabled() { return enabled; }
	public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
