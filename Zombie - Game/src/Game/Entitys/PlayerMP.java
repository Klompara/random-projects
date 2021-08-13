package Game.Entitys;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Game.Handler;
import Game.Main;
import Game.net.Client;

public class PlayerMP {
	
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	private String username;
	
	private int width;
	private int height;
	
	private Color color;
	
	private boolean MLeft, MRight, MUp, MDown;
	private int speed;
	
	private Handler handler;

	private Client client;
	
	private Light light;
	
	public PlayerMP(String username, int type, Client client) {
		this.username = username;
		this.handler = Main.handler;
		this.client = client;
		
		light = new Light(x, y, 300, 255);
		handler.lights.add(light);
		
		switch(type) {
		case 1:
			width = 32;
			height = 32;
			color = Color.blue;
			speed = 5;
			break;
		case 2:
			width = 32;
			height = 32;
			color = Color.yellow;
			speed = 5;
			break;
		case 3:
			width = 32;
			height = 32;
			color = Color.green;
			speed = 5;
			break;
		case 4:
			width = 32;
			height = 32;
			color = Color.red;
			speed = 4;
			break;
		case 5:
			width = 32;
			height = 32;
			color = Color.orange;
			speed = 6;
			break;
		case 6:
			width = 32;
			height = 32;
			color = Color.cyan;
			speed = 4;
			break;
		case 7:
			width = 32;
			height = 32;
			color = Color.magenta;
			speed = 4;
			break;
		case 8:
			width = 32;
			height = 32;
			color = Color.pink;
			speed = 4;
			break;
		default:
			width = 16;
			height = 16;
			color = Color.black;
			this.username = "wenn du das hier lesen kannst ist ein fehler aufgetreten...";
			break;
		}
		x = 48*32/2;
		y = 48*32/2;
	}
	
	public void tick() {
		light.x = x+width/2;
		light.y = y+height/2;
		
		if(MRight) dx = speed;
		else if(MLeft) dx = -speed;
		else dx = 0;
		if(MUp) dy = -speed;
		else if(MDown) dy = speed;
		else dy = 0;
		
		
		if(dx > 0){
			for(int i = dx; i > 0; i--) {if(dx != 0) {
				x++;
				colission();
			}}
		}else if(dx < 0) {
			for(int i = dx; i < 0; i++) { if(dx != 0){
				x--;
				colission();
			}}
		}
		
		if(dy > 0){
			for(int i = dy; i > 0; i--) {if(dy != 0) {
				y++;
				colission();
			}}
		}else if(dy < 0) {
			for(int i = dy; i < 0; i++) { if(dy != 0){
				y--;
				colission();
			}}
		}
	}

	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
		g.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setColor(color.darker());
		g.drawRect((int)x+1, (int)y+1, width-2, height-2);
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.white);
		g.setFont(new Font("sansserif", 1, 15));
		g.drawString(username, (int) ((x+(width/2)) - ((g.getFontMetrics().getStringBounds(username, g).getWidth())/2)), (int)y-6);
	}
	
	private void colission() {
		for(int i = 0; i < handler.bloecke.size(); i++) {
			Block b = handler.bloecke.get(i);
			if(b.getType() != 1) {
				if(BLeft().intersects(b.BRight())) { x++; }
				if(BRight().intersects(b.BLeft())) { x--; }
				if(BTop().intersects(b.BBottom())) { y++; }
				if(BBottom().intersects(b.BTop())) { y--; }
				if(BBottom().intersects(b.BLeft())){ x--; }
				if(BBottom().intersects(b.BRight())){x++; }
				if(BTop().intersects(b.BLeft())) { x--;}
				if(BTop().intersects(b.BRight())){x++; }
			}
		}
		for(int i = 0; i < client.otherPlayers.size(); i++) {
			PlayerMP p = client.otherPlayers.get(i);
			if(p != this) {
				if(BLeft().intersects(p.BRight())) { x++; }
				if(BRight().intersects(p.BLeft())) { x--; }
				if(BTop().intersects(p.BBottom())) { y++; }
				if(BBottom().intersects(p.BTop())) { y--; }
				if(BBottom().intersects(p.BLeft())){ x--; }
				if(BBottom().intersects(p.BRight())){x++; }
				if(BTop().intersects(p.BLeft())) { x--;}
				if(BTop().intersects(p.BRight())){x++; }
			}
		}
	}
	
	public void remove() {
		handler.lights.remove(light);
		client.otherPlayers.remove(this);
	}
	
	public int getWidth() {return width; }
	public void setWidth(int width) { this.width = width; }
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }
	public boolean isMUp() { return MUp; } 
	public void setMUp(boolean mUp) { MUp = mUp; } 
	public boolean isMDown() { return MDown; } 
	public void setMDown(boolean mDown) { MDown = mDown; } 
	public boolean isMLeft() { return MLeft; } 
	public void setMLeft(boolean mLeft) { MLeft = mLeft; } 
	public boolean isMRight() { return MRight; } 
	public void setMRight(boolean mRight) { MRight = mRight; } 
	public String getUsername() { return username; } 
	public void setUsername(String username) { this.username = username; } 
	public int getX() { return x; } 
	public void setX(int x) { this.x = x; } 
	public int getY() { return y; } 
	public void setY(int y) { this.y = y; } 
	
	public Rectangle2D BTop() { return new Rectangle(x+4, y, width-8, 4); }
	public Rectangle2D BBottom() { return new Rectangle(x+4, y+height-4, width-8, 4); }
	public Rectangle2D BLeft() { return new Rectangle(x, y+4, 4, height-8); }
	public Rectangle2D BRight() { return new Rectangle(x+width-4, y+4, 4, height-8); }
}
