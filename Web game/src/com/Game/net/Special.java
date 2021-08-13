package com.Game.net;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Special extends GameObject{
	private String art;
	
	public Special(int x, int y, ID id, String art) {
		super(x, y, id);
		velX = 0;
		velY = 4;
		this.art = art;
	}

	public void tick() {
		x += velX;
		y += velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.black);
		for(int i = 0; i < 3; i++)
			g.drawRect(x+i, y+i, 32-(i*2), 32-(i*2));
		g.setFont(new Font("sansserif", 1, 25));
		if(art == "Bigger")
			g.drawString("B", x+7, y+25);
		if(art == "Smaller")
			g.drawString("S", x+7, y+25);
		if(art == "PlusBall")
			g.drawString("+", x+7, y+25);
		if(art == "Burning")
			g.drawString("F", x+7, y+25);
		if(art == "Faden")
			g.drawString("I", x+14, y+25);
		if(Game.entwickler)
			g.drawRect(x, y, 32, 32);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(x, y, 32, 32);
	}
	public String getArt() {
		return art;
	}
	public Rectangle getBoundsTopMiddleL() {
		return null;
	}
	public Rectangle getBoundsTopMiddleR() {
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
	
}
