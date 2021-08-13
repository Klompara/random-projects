package Game.Entity.Entitys.Lehrer;

import java.awt.Color;
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
import Game.Entity.Entitys.Trail_Weed;

public class Haidacha extends Entity{
	private int delay = 0;
	private BufferedImage Lehrer;
	private Handler handler;
	private boolean MoveX = false, MoveY = false;
	
	private WeedOval weedo;
	public Haidacha(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		try { Lehrer = ImageIO.read(getClass().getResourceAsStream("/Lehrer/Haidacha.png"));
		} catch (IOException e) { e.printStackTrace(); }
		this.handler = handler;
		hp = 100;
		width = 48;
		height = 48;
		
		weedo = new WeedOval();
	}

	public void tick() {
		weedo.tick(x+width/2, y+height/2);
		
		if(hp <= 0){ handler.entitys.add(new Explosion(x+width/2, y+height/2, ID.Explosion, handler)); handler.entitys.remove(this); }
		if(delay < 1)delay++;
		else{
			if(Main.random(8, 1) == 2){
				int size = Main.random(32, 8);
				handler.entitys.add(new Trail_Weed(x+width/2-size/2 +Main.random(10, -10), y+width/2 +Main.random(10, -10)-Main.random(32, 8), ID.Trail, size, handler));
			}
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
			delay = 0;
		}	
	}
	private void collision(){
		x = Main.clamp(x, 3, (int) (Main.WIDTH-width*1.5));
		y = Main.clamp(y, 3, Main.HEIGHT-height*2);
		
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempO = handler.entitys.get(i);
			if(tempO.getId() == ID.Kofla || tempO.getId() == ID.Gous || tempO.getId() == ID.Wurza || tempO.getId() == ID.Willi || tempO.getId() == ID.Haidacha || tempO.getId() == ID.Player){
				if(tempO.BBottom().intersects(BTop())){MoveY = false; velY = 0; y+=1;}
				if(tempO.BTop().intersects(BBottom())){MoveY = false; velY = 0; y-=1;}
				if(tempO.BLeft().intersects(BRight())){MoveX = false; velX = 0; x-=1;}
				if(tempO.BRight().intersects(BLeft())){MoveX = false; velX = 0; x+=1;}
				
				//if(tempO.BInner().intersects(BInner()) && tempO != this){ x += Main.random(width, width*-1); y -= Main.random(height, height*-1); }
			}
		}
	}
	
	public void render(Graphics g) {
		weedo.render(g);
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
		return weedo.hitbox();
	}
	
	// Weed oval
	public class WeedOval{
		private int x, y, durchm = 0;
		public WeedOval(){ }
	
		void tick(int x, int y){
			if(durchm > 120)durchm = 0;
			durchm+=2;
			this.x = x;
			this.y = y;
		}
		
		void render(Graphics g){
			g.setColor(new Color(0, 255, 0, 90));
			g.fillRoundRect(x-durchm/2, y-durchm/2, durchm, durchm, 50, 50);
		}
		
		Rectangle2D hitbox(){
			return new Rectangle(x-durchm/2, y-durchm/2, durchm, durchm);
		}
	}
}
