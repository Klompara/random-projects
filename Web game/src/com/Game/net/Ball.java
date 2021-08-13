package com.Game.net;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends GameObject{
	private float velX, velY;
	private int timer1 = 0, timer2 = 0;
	private Random r;
	private Handler handler;
	public Ball(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		r = new Random();
		velX = (r.nextInt(5 - -5)+ -5);
		velY = -5;
	}

	public void tick() {
		if(Game.Faden && timer2 != 750){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player){
					double diffX = tempObject.x - x;
					double diffY = tempObject.y - y;
					double distance = Math.sqrt(diffX*diffX + diffY*diffY);
					velX -= (-1/distance) * diffX * 0.6;
					velY -= (-1/distance) * diffY * 0.6;
				}
			}
			timer2++;
		}else{
			Game.Faden = false;
			timer2 = 0;
		}
		
		if(Game.burningballs && timer1 != 750){
			r = new Random();
			int random = r.nextInt(3);
			if(random == 0)
				handler.addObject(new FireAnimation(x+(r.nextInt(32 - -16)+ -16),y,ID.FireAnimation,Color.red,16,16,0.1F,handler));
			else if(random == 1)
				handler.addObject(new FireAnimation(x+(r.nextInt(32 - -16)+ -16),y,ID.FireAnimation,Color.orange,16,16,0.1F,handler));
			else if(random == 2)
				handler.addObject(new FireAnimation(x+(r.nextInt(32 - -16)+ -16),y,ID.FireAnimation,Color.yellow,16,16,0.1F,handler));
			timer1++;
		}else{
			Game.burningballs = false;
			timer1 = 0;
		}
		
		if(velX == 0)velX = (r.nextInt(5 - -5)+ -5);
		if(velY != 6)velY += 0.06;
		collision();

		if(x+32 >= Game.WIDTH){velX *= -1;}
		if(x <= 0){velX *= -1; x = 1;}
		if(y <= 0){velY *= -1; y = 1;}
		if(y >= Game.HEIGHT){
			int Zaehler = 0;
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Ball)Zaehler++;
			}
			if(Zaehler == 1){
				Game.gameState = Game.STATE.GameOver;
				Game.sleeptime = 1;
				Game.Faden = false;
				Game.burningballs = false;
			}else{
				handler.object.remove(this);
			}
		}
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-32);
		y = Game.clamp(y, 0, Game.HEIGHT);
	}

	public void render(Graphics g) {
		g.setColor(new Color(236, 105, 164));
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.black);
		for(int i = 0; i < 3; i++)
			g.draw3DRect(x+i, y+i, 32-i*2, 32-i*2, true);
		
		if(Game.Faden){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player){
					g.drawLine(x+16, y+16, tempObject.getBoundsTopMiddleL().x+tempObject.getBoundsTopMiddleL().width, tempObject.getY()+tempObject.getBoundsTopMiddleL().height/2);
				}
			}
		}

		
		if(Game.entwickler){
			g.setColor(Color.cyan);
			g.drawRect(x,y+7,7,18);
			g.drawRect(x+25,y+7,7,18);
			g.drawRect(x+7,y,18,7);
			g.drawRect(x+7,y+25,18,7);
		}

	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Block){
				if(tempObject.getBoundsRight().intersects(getBoundsLeft())){
					if(!Game.burningballs)
						velX *= -1;
					remove(tempObject);
				}else if(tempObject.getBoundsLeft().intersects(getBoundsRight())){
					if(!Game.burningballs)
						velX *= -1;
					remove(tempObject);
				}else if(tempObject.getBoundsBottom().intersects(getBoundsTop())){
					if(!Game.burningballs)
						velY *= -1;
					remove(tempObject);
				}else if(tempObject.getBoundsTop().intersects(getBoundsBottom())){
					if(!Game.burningballs)
						velY *= -1;
					remove(tempObject);
				}
			}
			
			if(tempObject.getID() == ID.Player){
				if(tempObject.getBoundsRight().intersects(getBoundsLeft())){
					velX *= -1;
				}
				else if(tempObject.getBoundsLeft().intersects(getBoundsRight())){
					velX *= -1;
				}
				else if(tempObject.getBoundsTopLeft().intersects(getBoundsBottom())){
					velY *= -1;
					if(velX >= 0)velX *= -1;
				}
				else if(tempObject.getBoundsTopMiddleL().intersects(getBoundsBottom())){
					velY *= -1;
				}
				else if(tempObject.getBoundsTopMiddleR().intersects(getBoundsBottom())){
					velY *= -1;
				}
				else if(tempObject.getBoundsTopRight().intersects(getBoundsBottom())){
					velY *= -1;
					if(velX <= 0)velX *= -1;
				}
			}
			
			if(tempObject.getID() == ID.Ball){
				if(tempObject.getBoundsRight().intersects(getBoundsLeft()))velX *= -1;
				if(tempObject.getBoundsLeft().intersects(getBoundsRight()))velX *= -1;
				if(tempObject.getBoundsTop().intersects(getBoundsBottom()))velY *= -1;
				if(tempObject.getBoundsBottom().intersects(getBoundsTop()))velY *= -1;
			}
		}
	}
	private void remove(GameObject tempObject){
		handler.object.remove(tempObject);
		r = new Random();
		int random = r.nextInt(21);
		if(random == 20 || random == 19)
			handler.addObject(new Special(x,y,ID.Special,"Bigger"));
		else if(random == 18 || random == 17)
			handler.addObject(new Special(x, y, ID.Special, "Smaller"));
		else if(random == 16 || random == 15)
			handler.addObject(new Special(x, y, ID.Special, "PlusBall"));
		else if(random == 14 || random == 13)
			handler.addObject(new Special(x, y, ID.Special, "Burning"));
		else if(random == 12 || random == 11)
			handler.addObject(new Special(x, y, ID.Special, "Faden"));
	}
	

	public Rectangle getBoundsLeft() {
		return new Rectangle(x,y+7,7,18);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle(x+25,y+7,7,18);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle(x+7,y,18,7);
	}
	public Rectangle getBoundsBottom() {
		return new Rectangle(x+7,y+25,18,7);
	}
	public Rectangle getBoundsTopLeft() {
		return null;
	}
	public Rectangle getBoundsTopRight() {
		return null;
	}
	public String getArt() {
		return null;
	}
	public Rectangle getBoundsTopMiddleL() {
		return null;
	}
	public Rectangle getBoundsTopMiddleR() {
		return null;
	}

}
