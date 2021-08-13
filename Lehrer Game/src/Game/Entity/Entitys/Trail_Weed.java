package Game.Entity.Entitys;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Game.Main;
import Game.Entity.Entity;
import Game.Entity.Handler;
import Game.Entity.Entitys.Lehrer.ID;

public class Trail_Weed extends Entity{
	private float alpha = 1F;
	private int size;
	private Handler handler;
	private BufferedImage textur;
	public Trail_Weed(int x, int y, ID id, int size, Handler handler) {
		super(x, y, id);
		this.size = size;
		this.handler = handler;
		textur = handler.texturTrail();
	}

	public void tick() {
		//y-=2;
		if(alpha > 0.09)alpha-=0.02;
		else handler.entitys.remove(this);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(Main.makeTransparent(alpha));
		g.drawImage(textur, x, y, size, size, null);
		g2d.setComposite(Main.makeTransparent(1));
	}
	
	public Rectangle2D BTop() {
		return new Rectangle(-10000,-1000,0,0);
	}
	public Rectangle2D BBottom() {
		return BTop();
	}
	public Rectangle2D BLeft() {
		return BTop();
	}
	public Rectangle2D BRight() {
		return BTop();
	}
	public Rectangle2D BInner() {
		return BTop();
	}	
}
