package Game.Entitys;

import java.awt.Graphics2D;

public abstract class Entity {
	protected double x, y;
	protected double dx, dy;
	
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);

	protected double getX() { return x; } 
	protected void setX(double x) { this.x = x; } 

	protected double getY() { return y; } 
	protected void setY(double y) { this.y = y; } 

	protected double getDx() { return dx; } 
	protected void setDx(double dx) { this.dx = dx; } 

	protected double getDy() { return dy; } 
	protected void setDy(double dy) { this.dy = dy; } 

}
