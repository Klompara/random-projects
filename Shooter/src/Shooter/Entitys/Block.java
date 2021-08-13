package Shooter.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Shooter.Main;

public class Block extends Entity{
	private int bWidth, bHeight;
	
	public Block(float x, float y, ID id, int bWidth, int bHeight) {
		super(x, y, id);
		this.bWidth = bWidth;
		this.bHeight = bHeight;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.drawRoundRect((int)x, (int)y, bWidth, bHeight, 8, 8);
		
		if(Main.hitboxes){
			g.setColor(Color.orange);
			g.drawRect((int)x, (int)y+7, 7, bHeight-14);
			g.drawRect((int)x+bWidth-7, (int)y+7, 7, bHeight-14);
			g.drawRect((int)x+7, (int)y, bWidth-14, 7);
			g.drawRect((int)x+7, (int)y+bHeight-7, bWidth-14, 7);
		}
	}

	public Rectangle getBLeft() {
		return new Rectangle((int)x, (int)y+7, 7, bHeight-14);
	}

	public Rectangle getBRight() {
		return new Rectangle((int)x+bWidth-7, (int)y+7, 7, bHeight-14);
	}

	public Rectangle getBTop() {
		return new Rectangle((int)x+7, (int)y, bWidth-14, 7);
	}

	public Rectangle getBBottom() {
		return new Rectangle((int)x+7, (int)y+bHeight-7, bWidth-14, 7);
	}

	public int getHeight() {
		return bHeight;
	}

	@Override
	public int getWidth() {
		return bWidth;
	}

}
