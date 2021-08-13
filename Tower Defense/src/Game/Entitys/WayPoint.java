package Game.Entitys;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import Game.Assets;
import Game.States.StateHandler;

public class WayPoint extends Entity {

	private int pointID;
	private BufferedImage texture = Assets.getImage("dirt.png");
	public WayPoint(StateHandler handler, double x, double y, int pointID) {
		super(handler, x, y);
		this.pointID = pointID;
	}

	public void tick() { }
	public void render(Graphics2D g) {
		TexturePaint paint = new TexturePaint(texture, new Rectangle(0, 0, texture.getWidth(), texture.getHeight()));
		g.setPaint(paint);
		g.setStroke(new BasicStroke(32f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		
		int next = pointID+1;
		if(next >= handler.play.wayPoints.size()) next = 0;
		WayPoint p = handler.play.wayPoints.get(next);
		
		g.drawLine((int)x, (int)y, (int)p.x, (int)p.y);
	}

	protected int getPointID() { return pointID; }
}
