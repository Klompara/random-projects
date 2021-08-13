package com.Game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bullets extends GameObject{
	private Random r;
	private Handler handler;
	
	public Bullets(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		r = new Random();
		velX = (r.nextInt(5 - -5)+ -5);
		velY = 4;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.WIDTH - 20) velX *= -1;
		if(y > Game.HEIGHT)handler.removeObject(this);
		
		handler.addObject(new Trail(x + 4 + r.nextInt(2 - -2), y - 4, ID.Trail, Color.white, 8, 8, 0.1f, handler, true));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x, (int)y, 16, 16);
		g.setColor(Color.black);
		g.drawOval((int)x+4, (int)y+4, 4, 4); // Auge
		g.drawOval((int)x+8, (int)y+4, 4, 4); // Auge
		g.drawLine((int)x+4, (int)y+10, (int)x+12, (int)y+10); // Mund
		g.drawLine((int)x+4, (int)y+10, (int)x+8, (int)y+12); // Mund
		g.drawLine((int)x+6, (int)y+6, (int)x+6, (int)y+6); // AugenPunkt
		g.drawLine((int)x+10, (int)y+6, (int)x+10, (int)y+6); // AugenPunkt
	}	
}
