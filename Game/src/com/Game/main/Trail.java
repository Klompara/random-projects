package com.Game.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{
	
	private float alpha = 1;
	private float life;
	
	private boolean isround;
	
	private Handler handler;
	private Color color;
	
	private int widht, height;
	
	public Trail(float x, float y, ID id, Color color, int widht, int height, float life, Handler handler, boolean isround) {
		super((int)x, (int)y, id);
		this.color = color;
		this.widht = widht;
		this.height = height;
		this.life = life;
		this.handler = handler;
		this.isround = isround;
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
		if(!isround)g.fillRect((int)x, (int)y, widht, height);
		else g.fillOval((int)x, (int)y, widht, height);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	

	public Rectangle getBounds() {
		return null;
	}

}
