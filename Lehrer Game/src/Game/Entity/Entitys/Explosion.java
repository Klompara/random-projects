package Game.Entity.Entitys;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Game.Entity.Entity;
import Game.Entity.Handler;
import Game.Entity.Entitys.Lehrer.ID;

public class Explosion extends Entity{
	private Handler handler;
	public Explosion(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		width = 0;
		height = 0;
	}

	public void tick() {
		width+=2;
		height+=2;
		x--;
		y--;
		if(width > 120)handler.entitys.remove(this);
	}

	public void render(Graphics g) {
		g.setColor(new Color(115, 194, 251));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));
		g2d.drawOval(x, y, width, height);
		g2d.setStroke(new BasicStroke(1));
	}

	public Rectangle2D BTop() {
		return new Rectangle(new Point(-1000, 50000));
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
