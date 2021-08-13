package Game.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Game.GameStates.States.State_Play;

public class Player {
	
	private boolean left, right, jump, falling;
	
	private float x, y;
	private float dx, dy;
	private int width, height;
	
	private float maxmovespeed;
	private float maxfallspeed;
	
	private float gravity;
	private float jumpspeed;
	
	private State_Play handler;
	
	public Player(float x, float y, State_Play handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		
		width = 32;
		height = 48;
		
		maxmovespeed = 11.36f;
		maxfallspeed = 35f;
		
		gravity = 1f;
		jumpspeed = 16;
	}
	
	public void tick() {
		if(left) {
			dx--;
			if(dx < -maxmovespeed){ dx = -maxmovespeed; }
		}else if(right) {
			dx++;
			if(dx > maxmovespeed){ dx = maxmovespeed; }
		}
		if(!left && !right) {
			if(dx > 0)dx-=0.75f;
			if(dx < 0)dx+=0.75f;
		}
		
		if(jump) {
			jump = false;
			if(!falling && dy > -1 && dy < 1) {
				dy = -jumpspeed;
			}
			falling = true;
		}
		
		dy += gravity;
		if(dy > maxfallspeed)dy = maxfallspeed;
		
		if(dx < 0) {for(int i = (int)dx; i < 0; i++) { if(dx < 0) {
			x--;
			collision();
		}}}else if(dx > 0) { for(int i = 0; i < (int)dx; i++) { if(dx > 0) {
			x++;
			collision();
		}}}
		
		if(dy < 0) {for(int i = (int)dy; i < 0; i++) { if(dy < 0) {
			y--;
			collision();
		}}}else if(dy > 0) { for(int i = 0; i < (int)dy; i++) { if(dy > 0) {
			y++;
			collision();
		}}}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	private void collision() {
		for(int i = 0; i < handler.blocks.size(); i++) {
			Block b = handler.blocks.get(i);
			
			// Stufen steigen
			if(BLBottomB().intersects(b.BRight())) {
				boolean step = true;
				for(Block b2 : handler.blocks) {
					if(b2 != b) {
						if(BLBottomT().intersects(b2.BRight()) || falling) {
							step = false;
						}
					}
				}
				if(step) {
					dx = -4;
					y-=16;
				}
			}
			if(BRBottomB().intersects(b.BLeft())) {
				boolean step = true;
				for(Block b2 : handler.blocks) {
					if(b2 != b) {
						if(BRBottomT().intersects(b2.BLeft()) || falling) {
							step = false;
						}
					}
				}
				if(step) {
					dx = 4;
					y-=16;
				}
			}

			
			
			if(BBottom().intersects(b.BRight())  || 
					BTop().intersects(b.BRight())||
					BTop().intersects(b.BLeft()) ||
					BBottom().intersects(b.BLeft())) {
				
				falling = false;
				y--;
				dy = 0;
			}
			if(BBottom().intersects(b.BTop())) {
				dy = 0;
				falling = false;
				y--;
			}
			if(BTop().intersects(b.BBottom())) {
				dy = 0;
				y++;
			}
			if(BLeft().intersects(b.BRight())) {
				dx = 0;
				x++;
			}
			if(BRight().intersects(b.BLeft())) {
				dx = 0;
				x--;
			}
		}
	}
	
	public Rectangle2D BTop() { return new Rectangle((int)x+3, (int)y, width-6, 3); }
	public Rectangle2D BBottom() { return new Rectangle((int)x+3, (int)y+height-3, width-6, 3); }
	public Rectangle2D BRight() { return new Rectangle((int)x+width-3, (int)y+3, 3, height-6); }
	public Rectangle2D BLeft() { return new Rectangle((int)x, (int)y+3, 3, height-6); }
	// Stufen gehen
	public Rectangle2D BLBottomB() { return new Rectangle((int)x, (int)y+height-10, 3, 2); }
	public Rectangle2D BLBottomT() { return new Rectangle((int)x, (int)y+height-20, 3, 2); }
	public Rectangle2D BRBottomB() { return new Rectangle((int)x+width-3, (int)y+height-10, 3, 2); }
	public Rectangle2D BRBottomT() { return new Rectangle((int)x+width-3, (int)y+height-20, 3, 2); }

	public boolean isJump() { return jump; } 
	public void setJump(boolean jump) { this.jump = jump; } 

	public boolean isLeft() { return left; } 
	public void setLeft(boolean left) { this.left = left; } 

	public boolean isRight() { return right; } 
	public void setRight(boolean right) { this.right = right; } 

	public float getX() { return x; } 
	public void setX(float x) { this.x = x; } 

	public float getY() { return y; } 
	public void setY(float y) { this.y = y; } 

	public float getDx() { return dx; } 
	public void setDx(float dx) { this.dx = dx; } 

	public float getDy() { return dy; } 
	public void setDy(float dy) { this.dy = dy; }

	public float getMaxmovespeed() { return maxmovespeed; }
	public void setMaxmovespeed(float maxmovespeed) { this.maxmovespeed = maxmovespeed; }

	public float getMaxfallspeed() { return maxfallspeed; }
	public void setMaxfallspeed(float maxfallspeed) { this.maxfallspeed = maxfallspeed; }
}
