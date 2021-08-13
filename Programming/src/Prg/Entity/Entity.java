package Prg.Entity;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
	protected int x, y;
	protected float velX, velY;
	protected ID id;
	
	public Entity(int x, int y, ID id){
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public abstract Rectangle2D BLeft();
	public abstract Rectangle2D BRight();
	public abstract Rectangle2D BBottom();
	public abstract Rectangle2D BTop();
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public float getVelX() {
		return velX;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public float getVelY() {
		return velY;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
}
