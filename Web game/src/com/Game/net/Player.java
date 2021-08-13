package com.Game.net;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	private Handler handler;
	private int Width = 64+32;
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 0;
		velY = 0;
	}

	public void tick() {
		x += velX;
		y += velY;
		x = Game.clamp(x, 0, Game.WIDTH-Width);
		collision();
	}

	public void render(Graphics g) {
		g.setColor(new Color(236, 105, 164));
		g.fillRect(x, y, Width, 18);
		g.setColor(Color.black);
		for(int i = 0; i < 3; i++)
			g.draw3DRect(x+i, y+i, Width-i*2, 18-i*2, true);
		
		if(Game.entwickler){
			g.setColor(Color.cyan);
			g.drawRect(x, y+3, 3, 12);
			g.drawRect(x+Width-3, y+3, 3, 12);
			g.drawRect(x+3,y,(Width-6)/4,18);
			g.drawRect(x+3+(Width-6)/4,y,(Width-6)/4,18);
			g.drawRect((x+3+((Width-6)/4)*2),y,(Width-6)/4+1,18);
			g.drawRect((x+3+((Width-6)/4)*3)+1,y,(Width-6)/4+1,18);
		}
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Special){
				if(getBoundsTop().intersects(tempObject.getBoundsBottom())){
					if(tempObject.getArt() == "Bigger"){
						Width += 32;
						x -= 16;
					}
					if(tempObject.getArt() == "Smaller"){
						Width -= 32;
						x += 16;
					}
					if(tempObject.getArt() == "PlusBall"){
						handler.addObject(new Ball(Game.WIDTH/2-32/2, Game.HEIGHT-128, ID.Ball, handler));
					}
					if(tempObject.getArt() == "Burning"){
						Game.burningballs = true;
					}
					if(tempObject.getArt() == "Faden"){
						Game.Faden = true;
					}
					handler.object.remove(tempObject);
				}
			}
		}
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle(x, y, 3, 18);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle(x+Width-3, y, 3, 18);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle(x+6, y, Width-12, 3);
	}
	public Rectangle getBoundsTopLeft() {
		return new Rectangle(x+3,y,(Width-6)/4,18);
	}
	public Rectangle getBoundsTopMiddleL() {
		return new Rectangle(x+3+(Width-6)/4,y,(Width-6)/4,18);
	}
	public Rectangle getBoundsTopMiddleR() {
		return new Rectangle((x+3+((Width-6)/4)*2),y,(Width-6)/4+1,18);
	}
	public Rectangle getBoundsTopRight() {
		return new Rectangle((x+3+((Width-6)/4)*3)+1,y,(Width-6)/4+1,18);
	}
	public String getArt() {
		return null;
	}
	public Rectangle getBoundsBottom() {
		return null;
	}
}
