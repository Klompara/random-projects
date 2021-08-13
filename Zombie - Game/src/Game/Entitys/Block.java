package Game.Entitys;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Game.Handler;

public class Block {
	
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private int type;
	
	private BufferedImage texture;
	
	public Block(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		
		width = 48;
		height = 48;
		
		if(type == 1) {
			texture = Handler.Texture_Dirt;
		}else if(type == 2) {
			texture = Handler.Texture_Wall;
		}
	}
	
	public void render(Graphics2D g) {
		g.drawImage(texture ,x, y, width, height, null);
	}
	
	public Rectangle2D BTop() { return new Rectangle(x+4, y, width-8, 4); }
	public Rectangle2D BBottom() { return new Rectangle(x+4, y+height-4, width-8, 4); }
	public Rectangle2D BLeft() { return new Rectangle(x, y+4, 4, height-8); }
	public Rectangle2D BRight() { return new Rectangle(x+width-4, y+4, 4, height-8); }
	public Rectangle2D BBounds() { return new Rectangle(x, y, width, height); }

	public int getType() { return type; }
	public void setType(int type) { this.type = type; }
}
