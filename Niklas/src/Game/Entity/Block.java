package Game.Entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block {
	public int x;
	public int y;
	
	public int width;
	public int height;
	
	public Block(int x, int y){
		this.x = x;
		this.y = y;
		
		width = 48;
		height = 48;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.green.darker());
		g.drawRoundRect(x+1, y+1, width-2, height-2, 5, 2);
		g.setStroke(new BasicStroke(1));
	}
	
	public Rectangle Hitbox_Top(){
		return new Rectangle(x+3, y, width-6, 3);
	}
	public Rectangle Hitbox_Bottom(){
		return new Rectangle(x+3, y+height-3, width-6, 3);
	}
	public Rectangle Hitbox_Left(){
		return new Rectangle(x, y+3, 3, height-6);
	}
	public Rectangle Hitbox_Right(){
		return new Rectangle(x+width-3, y+3, 3, height-6);
	}
}
