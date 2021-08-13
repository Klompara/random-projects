package Game.Entity.Entitys.Lehrer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Main;
import Game.Entity.Entity;
import Game.Entity.Handler;
import Game.Entity.Entitys.Explosion;

public class Wurza extends Entity{
	private BufferedImage Lehrer;
	private Handler handler;
	private boolean MoveX = false, MoveY = false;
	public Wurza(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		try { Lehrer = ImageIO.read(getClass().getResourceAsStream("/Lehrer/Wurza.png"));
		} catch (IOException e) { e.printStackTrace(); }
		this.handler = handler;
		hp = 80;
		width = 48;
		height = 48;
	}

	public void tick() {
		if(hp <= 0){ handler.entitys.add(new Explosion(x+width/2, y+height/2, ID.Explosion, handler)); handler.entitys.remove(this); }
		if(velX > 0 || velX < 0)MoveX = true;
		if(velY > 0 || velY < 0)MoveY = true;
		
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempObject = handler.entitys.get(i);
			if(tempObject.getId() == ID.Player){
				float length = (float) Math.sqrt((tempObject.getX()- x) * (tempObject.getX() - x) + (tempObject.getY() - y) * (tempObject.getY() - y));
				velX = (tempObject.getX() - x) / length * 2;
			    velY = (tempObject.getY() - y) / length * 2;
			}
		}
			
		if(velX > 0){
			for(float i = velX; i > 0; i--){if(MoveX){ x += 1; collision();}}
		}else{
			for(float i = velX; i < 0; i++){if(MoveX){ x -= 1; collision();}}
		}
		
		if(velY > 0){
			for(float i = velY; i > 0; i--){if(MoveY){ y += 1; collision();}}
		}else{
			for(float i = velY; i < 0; i++){if(MoveY){ y -= 1; collision();}}
		}
	}
	private void collision(){
		x = Main.clamp(x, 3, (int) (Main.WIDTH-width*1.5));
		y = Main.clamp(y, 3, Main.HEIGHT-height*2);
		
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempO = handler.entitys.get(i);
			if(tempO.getId() == ID.Kofla || tempO.getId() == ID.Haidacha || tempO.getId() == ID.Gous || tempO.getId() == ID.Willi || tempO.getId() == ID.Wurza || tempO.getId() == ID.Player){
				if(tempO.BBottom().intersects(BTop())){MoveY = false; velY = 0; y+=1;}
				if(tempO.BTop().intersects(BBottom())){MoveY = false; velY = 0; y-=1;}
				if(tempO.BLeft().intersects(BRight())){MoveX = false; velX = 0; x-=1;}
				if(tempO.BRight().intersects(BLeft())){MoveX = false; velX = 0; x+=1;}
				
				if(tempO.getId() != ID.Haidacha)
					if(tempO.BInner().intersects(BInner()) && tempO != this){ x += Main.random(width, width*-1); y -= Main.random(height, height*-1); }
			}
			
			if(tempO.getId() == ID.Player){
				if(tempO.BBottom().intersects(BTop())){if(Main.random(5, 1) == 3)tempO.setHp(tempO.getHp()-5);}
				if(tempO.BTop().intersects(BBottom())){if(Main.random(5, 1) == 3)tempO.setHp(tempO.getHp()-5);}
				if(tempO.BLeft().intersects(BRight())){if(Main.random(5, 1) == 3)tempO.setHp(tempO.getHp()-5);}
				if(tempO.BRight().intersects(BLeft())){if(Main.random(5, 1) == 3)tempO.setHp(tempO.getHp()-5);}
				
				if(tempO.BBottom().intersects(BTop())){MoveY = false; velY = 0;}
				if(tempO.BTop().intersects(BBottom())){MoveY = false; velY = 0;}
				if(tempO.BLeft().intersects(BRight())){MoveX = false; velX = 0;}
				if(tempO.BRight().intersects(BLeft())){MoveX = false; velX = 0;}
			}
		}
	}
	
	
	public void render(Graphics g) {
		g.drawImage(Lehrer, x, y, width, height, null);
	}

	public Rectangle2D BTop() {
		return new Rectangle(x+3, y, width-6, 3);
	}
	public Rectangle2D BBottom() {
		return new Rectangle(x+3, y+height-3, width-6, 3);
	}
	public Rectangle2D BLeft() {
		return new Rectangle(x, y+3, 3, height-6);
	}
	public Rectangle2D BRight() {
		return new Rectangle(x+width-3, y+3, 3,height-6);
	}
	public Rectangle2D BInner() {
		return new Rectangle(x+3, y+3, width-6, height-6);
	}
}
