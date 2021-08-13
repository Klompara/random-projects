package bll;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {

	private int x, y;
	private int width, height;
	private Rectangle collisionBox;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		collisionBox = new Rectangle(x-width/2, y, width, height/2);
		width = 100;
		height = 20;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(x-width/2, y, width, height);
		g.setColor(Color.red);
		g.drawRect((int)collisionBox.getX(), (int)collisionBox.getY(), (int)collisionBox.getWidth(), (int)collisionBox.getHeight());
	}
	
	public void tick() {
		collisionBox = new Rectangle(x-width/2, y, width, height/2);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}
	
}
