package TestGame.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import TestGame.Main.Game;

public class Player {
	
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	private int Width;
	private int Height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.Width = 32;
		this.Height = 32;
	}
	
	public void tick() {
		x += dx;
		y += dy;
		
		if(x < 0) x = 0;
		if(x+38 > Game.WIDTH) x = Game.WIDTH-38;
		if(y < 0) y = 0;
		if(y+61 > Game.HEIGHT) y = Game.HEIGHT-61;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.green);
		g.fillRect(x, y, Width, Height);
	}
	
	public Rectangle2D BTop() {
		return new Rectangle(x+4, y, Width-8, 4);
	}
	public Rectangle2D BBottom() {
		return new Rectangle(x+4, y+Height-4, Width-8, 4);
	}
	public Rectangle2D BLeft() {
		return new Rectangle(x, y+4, 4, Height-8);
	}
	public Rectangle2D BRight() {
		return new Rectangle(x+Width-4, y+4, 4, Height-8);
	}

	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
    public int getDx() { return dx; }
	public void setDx(int dx) { this.dx = dx; }
	public int getDy() { return dy; }
	public void setDy(int dy) { this.dy = dy; }
}
