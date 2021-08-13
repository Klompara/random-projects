package Game.Entity.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Game.Main;
import Game.Entity.Entity;
import Game.Entity.Handler;
import Game.Entity.Entitys.Lehrer.ID;

public class Bullet extends Entity{
	private Handler handler;
	private boolean move = true;
	public Bullet(int x, int y, ID id, float MouseX, float MouseY, Handler handler) {
		super(x, y, id);
		this.handler = handler;

        float length = (float) Math.sqrt((MouseX- x) * (MouseX - x) + (MouseY - y) * (MouseY - y));
        velX = (MouseX - x) / length * 20;
        velY = (MouseY - y) / length * 20;
        
        height = 8;
        width = 8;
	}

	public void tick() {
		if(x > Main.WIDTH)handler.entitys.remove(this);
		if(x < 0)handler.entitys.remove(this);
		if(y > Main.HEIGHT)handler.entitys.remove(this);
		if(y < 0)handler.entitys.remove(this);
		
		if(velX > 0){
			for(float i = velX; i > 0; i--){ x += 1; collision();}
		}else{
			for(float i = velX; i < 0; i++){ x -= 1; collision();}
		}
		
		if(velY > 0){
			for(float i = velY; i > 0; i--){ y += 1; collision();}
		}else{
			for(float i = velY; i < 0; i++){ y -= 1; collision();}
		}
		
	}
	
	private void collision(){
		for(int i = 0; i < handler.entitys.size(); i++){
			 Entity tempO = handler.entitys.get(i);
			 if(tempO.getId() == ID.Kofla || tempO.getId() == ID.Haidacha || tempO.getId() == ID.Wurza || tempO.getId() == ID.Gous || tempO.getId() == ID.Willi){
				 if(BTop().intersects(tempO.BTop()) && move){tempO.setHp(tempO.getHp()-handler.DMG); handler.entitys.remove(this); move = false;}
				 if(BTop().intersects(tempO.BBottom()) && move){tempO.setHp(tempO.getHp()-handler.DMG); handler.entitys.remove(this); move = false;}
				 if(BTop().intersects(tempO.BLeft()) && move){tempO.setHp(tempO.getHp()-handler.DMG); handler.entitys.remove(this); move = false;}
				 if(BTop().intersects(tempO.BRight()) && move){tempO.setHp(tempO.getHp()-handler.DMG); handler.entitys.remove(this); move = false;}
			 }
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);
	}
	
	public Rectangle2D BTop() {
		return new Rectangle(x,y,width,height);
	}
	public Rectangle2D BBottom() {	
		return BTop();
	}
	public Rectangle2D BLeft() {	
		return BBottom();
	}
	public Rectangle2D BRight() {	
		return BLeft();
	}
	public Rectangle2D BInner() {
		return BLeft();
	}
}