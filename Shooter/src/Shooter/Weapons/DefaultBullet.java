package Shooter.Weapons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Shooter.Main;
import Shooter.Entitys.Entity;
import Shooter.Entitys.ID;
import Shooter.Entitys.entityHandler;

public class DefaultBullet extends Entity {
	private entityHandler handler;
	
	public DefaultBullet(float x, float y, ID id, float tempX, float tempY, entityHandler handler) {
		super(x, y, id);
		this.handler = handler;
		
		float diffX = x+8 - tempX - 16;
		float diffY = y+8 - tempY - 16;
		float distance = (float) Math.sqrt((x-tempX)*(x-tempX) + (y-tempY)*(y-tempY));
		velX = ((-1/distance) * diffX);
		velY = ((-1/distance) * diffY);
	}

	public void tick() {
		if(x < -8 || x > Main.WIDTH || y < 0 || y > Main.HEIGHT+8)
			handler.entitys.remove(this);
		
		int za = 0;
		while(za < 20){
			za++;
			x += velX;
			y += velY;
			for(int i = 0; i < handler.entitys.size(); i++){
				Entity tempObject = handler.entitys.get(i);
				if(tempObject.getID() == ID.Block){
					if(tempObject.getBTop().intersects(getBLeft()) ||tempObject.getBLeft().intersects(getBLeft()) || tempObject.getBRight().intersects(getBLeft()) || tempObject.getBBottom().intersects(getBLeft())){
						handler.entitys.remove(this);
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 8, 8);
	}

	public Rectangle getBLeft() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}

	public Rectangle getBRight() {
		return null;
	}

	public Rectangle getBTop() {
		return null;
	}

	public Rectangle getBBottom() {
		return null;
	}

	public int getHeight() {
		return 8;
	}

	@Override
	public int getWidth() {
		return 8;
	}
	
}
