package cells.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class Cell {
	private int x;
	private int y;
	private Color color;
	private int r;
	
	public Cell(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		r = 3;
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x-r, y-r, 2*r, 2*r);
	}

	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public int getR() { return r; }
	public void setR(int r) { this.r = r; }
}
