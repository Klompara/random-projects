package Game.Entity.Entitys.Upgrades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Game.Main;
import Game.Entity.Entity;
import Game.Entity.Handler;
import Game.Entity.Entitys.Lehrer.ID;

public class FastShoot extends Entity{
	private Handler handler;
	private boolean pickedup = false;
	private int duration = 450, timer = 0;
	
	public FastShoot(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		width = 32;
		height = 32;
	}

	public void tick() {
		if(!pickedup) collision();
		else timer++;
		if(timer > duration){ 
			Main.shootSpeed = 1;
			handler.entitys.remove(this); 
		}
	}
	private void collision(){
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempO = handler.entitys.get(i);
			if(tempO.getId() == ID.Player){
				if(tempO.BBottom().intersects(BInner()) || tempO.BInner().intersects(BInner()) || tempO.BLeft().intersects(BInner()) || tempO.BRight().intersects(BInner()) || tempO.BTop().intersects(BInner())) {
					Main.shootSpeed = 2;
					pickedup = true;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(!pickedup){
			g.setColor(new Color(255, 102, 0));
			g.fillRoundRect(x, y, width, height, 10, 10);
		}
	}

	public Rectangle2D BTop() { return BInner(); }
	public Rectangle2D BBottom() { return BInner(); }
	public Rectangle2D BLeft() { return BInner(); }
	public Rectangle2D BRight() { return BInner(); }
	public Rectangle2D BInner() { return new Rectangle(x, y, width, height); }
}
