package bll;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Branch {

	private Branch[] childs = new Branch[2];
	
	private Branch branchIfTrunk;
	
	private Point start, end;
	private boolean isTrunk;
	
	public Branch(Point start, Point end, double length, int angle, boolean isTrunk) {
		this.start = start;
		this.end = end;
		this.isTrunk = isTrunk;
		generateChilds(length, angle);
		if(isTrunk) {
			double rad = Math.toRadians(angle + ((-90 - angle)*2));
			int nextX = (int) ((int) (Math.cos(rad) * length) + end.getX());
			int nextY = (int) ((int) (Math.sin(rad) * length) + end.getY());
			branchIfTrunk = new Branch(end, new Point(nextX, nextY), length, (int)(angle + ((-90 - angle)*2)), false);
		}
	}
	
	private void generateChilds(double length, int angle) {
		double rad = Math.toRadians(angle);
		int nextX1 = (int) ((int) (Math.cos(rad) * length) + end.getX());
		int nextY1 = (int) ((int) (Math.sin(rad) * length) + end.getY());
		int nextX2 = (int) ((int) (Math.cos(rad) * length) + end.getX());
		int nextY2 = (int) ((int) (Math.sin(rad) * length) + end.getY());
		if(length > 10) {
			childs[0] = new Branch(end, new Point(nextX1, nextY1), length*0.8, (int)(angle + 20), false);
			childs[1] = new Branch(end, new Point(nextX2, nextY2), length*0.8, (int)(angle - 20), false);
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.setStroke(new BasicStroke(1));
		g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
		if(childs != null) {
			for(int i = 0; i < childs.length; i++) {
				if(childs[i] != null)
					childs[i].render(g);
			}
		}
		if(isTrunk) {
			branchIfTrunk.render(g);
		}
	}
	
}
