package com.Game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy2 extends GameObject{
	
	private Handler handler;
	
	public FastEnemy2(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 9;
		velY = 3;
	}


	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.WIDTH - 20) velX *= -1;
		if(y <= 0 || y >= Game.HEIGHT - 42) velY *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.orange, 16, 16, 0.05f, handler, true));
	}

	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillOval((int)x, (int)y, 16, 16);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	
}
