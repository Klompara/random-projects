package bll;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ball {

	private double x, y;
	private double dx, dy;
	
	private int size;
	private List<Block> blocks = new ArrayList<Block>();
	private Rectangle collisionBox;
	private int speed;
	private Player player;
	
	public Ball(double x, double y, List<Block> blocks, Player p) {
		this.x = x;
		this.y = y;
		this.blocks = blocks;
		player = p;
		size = 8;
		collisionBox = new Rectangle((int)x-size/2, (int)y-size/2, size, size);
		speed = 5;
		double rad = Math.toRadians(-135 + new Random().nextInt(90));
		dx = Math.cos(rad) * speed;
		dy = Math.sin(rad) * speed;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.fillOval((int)x-size/2, (int)y-size/2, size, size);
		g.setColor(Color.red);
		g.drawRect((int)collisionBox.getX(), (int)collisionBox.getY(), (int)collisionBox.getWidth(), (int)collisionBox.getHeight());
	}
	
	public void tick() {
		move();
	}
	
	private void move() {
		for(int i = 0; i < speed*10; i++) {
			x += dx/(speed*10);
			y += dy/(speed*10);
			collisionBox = new Rectangle((int)x-size/2, (int)y-size/2, size, size);
			checkCollision();
		}
	}
	
	private void checkCollision() {
		if(x < 0) {
			dx *= -1;
			x = 0;
		}
		if(y < 0) {
			dy *= -1;
			y = 0;
		}
		if(x > 600) {
			dx *= -1;
			x = 600;
		}
		
		if(collisionBox.intersects(player.getCollisionBox())) {
			dy *= -1;
		}
		
		for(int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			if(collisionBox.intersects(b.getCollisionBoxLeft()) || collisionBox.intersects(b.getCollisionBoxRight())) {
				dx *= -1;
			}
			if(collisionBox.intersects(b.getCollisionBoxBottom()) || collisionBox.intersects(b.getCollisionBoxTop())) {
				dy *= -1;
			}
		}
	}
	
}
