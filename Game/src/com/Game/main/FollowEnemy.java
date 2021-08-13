package com.Game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FollowEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	
	public FollowEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getID() == ID.Player) this.player = handler.object.get(i);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x+8 - player.getX() - 16;
		float diffY = y+8 - player.getY() - 16;
		float distance = (float) Math.sqrt((diffX*diffX) + (diffY*diffY));
		velX = ((-2/distance) * diffX);
		velY = ((-2/distance) * diffY);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.03f, handler, true));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval((int)x, (int)y, 16, 16);
		g.setColor(Color.BLACK);
		g.drawOval((int)x, (int)y, 16, 16);
	}	
}