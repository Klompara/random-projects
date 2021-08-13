package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button {

	private String text;
	private Font f;
	private int x, y;
	private Rectangle hitbox;
	
	public Button(String text, Font f, int x, int y) {
		this.text = text;
		this.f = f;
		this.x = x;
		this.y = y;	
	}
	
	public void render(Graphics2D g) {
		if(hitbox == null) {
			g.setFont(f);
			hitbox = new Rectangle(x, y-Main.getFHeight(text, g), Main.getFWidth(text, g), Main.getFHeight(text, g));
		}
		//test
		//g.setColor(Color.blue);
		//g.fillRect((int)hitbox.getX(), (int)(hitbox.getY()), (int)hitbox.getWidth(), (int)hitbox.getHeight());
		
		g.setColor(Color.white);
		g.setFont(f);
		g.drawString(text, x, y);
	}

	public Rectangle getHitbox() { return hitbox; }
	public void setHitbox(Rectangle hitbox) { this.hitbox = hitbox; }
}
