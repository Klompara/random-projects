package Game.Entity.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import Game.Main;
import Game.Entity.Entity;
import Game.Entity.Handler;
import Game.Entity.Entitys.Lehrer.ID;

public class Player extends Entity{
	private Handler handler;
	
	private boolean MoveX, MoveY, high;
	private int hightimer = 0, highduration = 500;
	
	private LinkedList<HighObjects> highobjects = new LinkedList<HighObjects>();
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		hp = 100;
		width = 32;
		height = 32;
	}

	public void tick() {
		if(high){
			hightimer++;
			if(highobjects.size() < 50) highobjects.add(new HighObjects(Main.random(Main.WIDTH, 0), Main.random(Main.HEIGHT, 0), highobjects));
		}
		if(highobjects.size() > 0){
			for(int i = 0; i < highobjects.size(); i++){
				HighObjects Object = highobjects.get(i);
				Object.tick();
			}
		}
		
		if(hightimer >= highduration){ high = false; hightimer = 0; }

		
		if(velX > 0 || velX < 0)MoveX = true;
		if(velY > 0 || velY < 0)MoveY = true;
		
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

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		if(highobjects.size() > 0){
			for(int i = 0; i < highobjects.size(); i++){
				HighObjects Object = highobjects.get(i);
				Object.render(g);
			}
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
				
				if(tempO.getId() != ID.Haidacha){
					if(tempO.BInner().intersects(BInner()) && tempO != this){ x += Main.random(width, width*-1); y -= Main.random(height, height*-1); }
				}else{
					if(tempO.BInner().intersects(BInner()) && tempO != this){
						high = true;
					}
				}
			}
		}
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
	
	public class HighObjects{
		private int x, y, durchm;
		private Color color;
		private int  duration, alpha, red, green, blue;
		private LinkedList<HighObjects> highobjects;
		public HighObjects(int x, int y, LinkedList<HighObjects> list){
			this.x = x;
			this.y = y;
			this.highobjects = list;
			duration = Main.random(300, 100);
			alpha = Main.random(200, 100);
			red = Main.random(255, 150);
			green = Main.random(255, 150);
			blue = Main.random(255, 150);
			color = new Color(red, green, blue, alpha);
		}
		
		void tick(){
			if(durchm > duration){alpha--; color = new Color(red, green, blue, alpha);}
			if(alpha <= 0) highobjects.remove(this);
			durchm+=2;
		}
		
		void render(Graphics g){
			g.setColor(color);
			g.fillOval(x-durchm/2, y-durchm/2, durchm, durchm);
		}
	}
}
