package bll;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block {

	private int x, y;
	private Color color;
	private int size;
	private Rectangle collisionBoxLeft;
	private Rectangle collisionBoxRight;
	private Rectangle collisionBoxTop;
	private Rectangle collisionBoxBottom;
	
	public Block(int x, int y, Color c) {
		this.x = x;
		this.y = y;
		color = c;
		size = 20;
		collisionBoxLeft = new Rectangle((int)x-size/2, (int)y-size/2, size/5, size);
		collisionBoxRight = new Rectangle((int)x+size/2-size/5, (int)y-size/2, size/5, size);
		collisionBoxTop = new Rectangle((int)x-size/2, (int)y-size/2, size, size/5);
		collisionBoxBottom = new Rectangle((int)x-size/2, (int)((y+size/2)-size/5), size, size/5);
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int)x-size/2, (int)y-size/2, size, size);
		g.setColor(Color.red);
		g.drawRect((int)collisionBoxLeft.getX(), (int)collisionBoxLeft.getY(), (int)collisionBoxLeft.getWidth(), (int)collisionBoxLeft.getHeight());
		g.drawRect((int)collisionBoxRight.getX(), (int)collisionBoxRight.getY(), (int)collisionBoxRight.getWidth(), (int)collisionBoxRight.getHeight());
		g.drawRect((int)collisionBoxBottom.getX(), (int)collisionBoxBottom.getY(), (int)collisionBoxBottom.getWidth(), (int)collisionBoxBottom.getHeight());
		g.drawRect((int)collisionBoxTop.getX(), (int)collisionBoxTop.getY(), (int)collisionBoxTop.getWidth(), (int)collisionBoxTop.getHeight());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}

	public Rectangle getCollisionBoxLeft() {
		return collisionBoxLeft;
	}

	public Rectangle getCollisionBoxRight() {
		return collisionBoxRight;
	}

	public Rectangle getCollisionBoxTop() {
		return collisionBoxTop;
	}

	public Rectangle getCollisionBoxBottom() {
		return collisionBoxBottom;
	}
}
