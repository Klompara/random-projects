package Shooter.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Shooter.Main;

public class Player extends Entity{
	private entityHandler handler;
	public Player(int x, int y, ID id, entityHandler handler) {
		super(x, y, id);
		velX = 0;
		velY = 0;
		this.handler = handler;
	}

	public void tick() {
		if(velY < 5)
			velY+=0.5;
	
		int za = 0;
		while(za < 3){
			collision();
			za++;
			x += velX;
			y += velY;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.drawRoundRect((int)x, (int)y, 32, 32, 16, 16);
		
		if(Main.hitboxes){
			g.setColor(Color.cyan);
			g.drawRect((int)x, (int)y+3, 3, 26);
			g.drawRect((int)x+29, (int)y+3, 3, 26);
			g.drawRect((int)x+3, (int)y, 26, 3);
			g.drawRect((int)x+3, (int)y+29, 26, 3);
		}
	}
	
	private void collision() {
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempObject = handler.entitys.get(i);
			if(tempObject.getID() == ID.Block){
				if(getBBottom().intersects(tempObject.getBTop())){
					y = tempObject.getY()-getHeight();
					velY = 0;
				}
				if(getBTop().intersects(tempObject.getBBottom())){
					y = tempObject.getY()+tempObject.getHeight();
					velY = 0;
				}
				if(getBRight().intersects(tempObject.getBLeft())){
					x = tempObject.getX()-getWidth();
					velX = 0;
				}
				if(getBLeft().intersects(tempObject.getBRight())){
					x = tempObject.getX()+tempObject.getWidth();
					velX = 0;
				}
			}
		}
	}

	public Rectangle getBLeft() {
		return new Rectangle((int)x, (int)y+3, 3, 29);
	}

	public Rectangle getBRight() {
		return new Rectangle((int)x+29, (int)y+3, 3, 26);
	}

	public Rectangle getBTop() {
		return new Rectangle((int)x+3, (int)y, 26, 3);
	}

	public Rectangle getBBottom() {
		return new Rectangle((int)x+3, (int)y+29, 26, 3);
	}

	public int getHeight() {
		return 32;
	}

	public int getWidth() {
		return 32;
	}
}
