package com.Game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	public static int y_clamp = 0;
	
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler){
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x, 0, Game.WIDTH - 38);
		y = Game.clamp((int)y, y_clamp, Game.HEIGHT - 61);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.03f, handler, false));
		
		collision();
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.BasicEnemy ||  tempObject.getID() == ID.Bullet){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH--;
				}
			}
			if(tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.FastEnemy2 || tempObject.getID() == ID.FollowEnemy || tempObject.getID() == ID.Nicki){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 1;
				}
			}
		}
	}
	
	public int getX(){
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
	}
}
