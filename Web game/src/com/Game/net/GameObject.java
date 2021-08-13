package com.Game.net;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x, y;
	protected ID id;
	protected int velX, velY;
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract String getArt();
	public abstract Rectangle getBoundsLeft();
	public abstract Rectangle getBoundsRight();
	public abstract Rectangle getBoundsTop();
	public abstract Rectangle getBoundsTopLeft();
	public abstract Rectangle getBoundsTopRight();
	public abstract Rectangle getBoundsTopMiddleL();
	public abstract Rectangle getBoundsTopMiddleR();
	public abstract Rectangle getBoundsBottom();
	
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setID(ID id){
		this.id = id;
	}
	public ID getID(){
		return id;
	}
	public void setVelX(int velX){
		this.velX = velX;
	}
	public void setVelY(int velY){
		this.velY = velY;
	}
	public int getVelX(){
		return velX;
	}
	public int getVelY(){
		return velY;
	}
	
}
