package com.Game.net;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class FireAnimation extends GameObject{
	
	private float alpha = 1;
	private float life;
	private Handler handler;
	private Color color;
	private int widht, height;
	
	public FireAnimation(float x, float y, ID id, Color color, int widht, int height, float life, Handler handler) {
		super((int)x, (int)y, id);
		this.color = color;
		this.widht = widht;
		this.height = height;
		this.life = life;
		this.handler = handler;
	}

	public void tick() {
		if(alpha > life){
			alpha -= (life - 0.001f);
		}else handler.removeObject(this);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));

		g.setColor(color);
		g.fillRect((int)x, (int)y, widht, height);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}



	public String getArt() {
		return null;
	}
	public Rectangle getBoundsLeft() {
		return null;
	}
	public Rectangle getBoundsRight() {
		return null;
	}
	public Rectangle getBoundsTop() {
		return null;
	}
	public Rectangle getBoundsTopLeft() {
		return null;
	}
	public Rectangle getBoundsTopRight() {
		return null;
	}
	public Rectangle getBoundsTopMiddleL() {
		return null;
	}
	public Rectangle getBoundsTopMiddleR() {
		return null;
	}
	public Rectangle getBoundsBottom() {
		return null;
	}
}
