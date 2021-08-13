package Nikö.Entity.Components;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
	protected int x, y;
	protected int velX, velY;
	protected ID id;
	protected String richtung;
	
	public Entity(int x, int y, ID id){
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void collision();
	
	public abstract Rectangle2D Bounds_Top();
	public abstract Rectangle2D Bounds_Bottom();
	public abstract Rectangle2D Bounds_Right();
	public abstract Rectangle2D Bounds_Left();
	
	public abstract int width();
	public abstract int height();

	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public int getVelX() { return velX; }
	public void setVelX(int velX) { this.velX = velX; }
	public int getVelY() { return velY; }
	public void setVelY(int velY) { this.velY = velY; }
	public ID getId() { return id; }
	public void setId(ID id) { this.id = id; }

	public String getRichtung() { return richtung; }
	public void setRichtung(String richtung) { this.richtung = richtung; }
}