package com.Game.net;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameObject{
	private Color BlockColor;
	
	public Block(int x, int y, ID id, Color color) {
		super(x, y, id);
		this.BlockColor = color;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(BlockColor);
		g.fillRect(x, y, 64, 32);
		g.setColor(Color.black);
		for(int i = 0; i < 3; i++)
			g.draw3DRect(x+i, y+i, 64-i*2, 32-i*2, true);
		
		if(Game.entwickler){
			g.setColor(Color.cyan);
			g.drawRect(x,y+5,10,22);
			g.drawRect(x+54,y+5,10,22);
			g.drawRect(x+5,y,54,10);
			g.drawRect(x+5,y+22,54,10);
		}
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(x,y+5,10,22);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(x+54,y+5,10,22);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle(x+5,y,54,10);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(x+5,y+22,54,10);
	}

	public Rectangle getBoundsTopLeft() {
		return null;
	}
	public Rectangle getBoundsTopRight() {
		return null;
	}
	public String getArt() {
		return null;
	}
	public Rectangle getBoundsTopMiddleL() {
		return null;
	}
	public Rectangle getBoundsTopMiddleR() {
		return null;
	}

}