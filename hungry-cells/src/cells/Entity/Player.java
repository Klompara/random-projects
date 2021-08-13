package cells.Entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import cells.Inputs.MouseInput;

public class Player {
	private double x;
	private double y;
	
	private double dx;
	private double dy;
	private double speed;
	
	private double r;
	
	private int[] map;
	
	private int fontsize;
	
	private String username;
	private float shadowoffset; // Textschatten
	
	private MouseInput mouseinput;
	
	private Color color;
	
	private int oldmouseX, oldmouseY;
	
	public Player(int x, int y, String username, MouseInput mouseinput, Color color, int[] map) {
		this.x = x;
		this.y = y;
		this.username = username;
		this.shadowoffset = 1.75f;
		this.color = color;
		this.map = map;
		this.mouseinput = mouseinput;
		this.speed = 4.5;
		this.fontsize = 0;
		this.r = 8;
	}
	
	public void tick() {
		
		if(mouseinput.getMouseX() != oldmouseX || mouseinput.getMouseY() != oldmouseY) { // maus update fix
			double diffX = x - mouseinput.getMouseX();
			double diffY = y - mouseinput.getMouseY();
			double distance =  Math.sqrt((x-mouseinput.getMouseX())*(x-mouseinput.getMouseX()) + (y-mouseinput.getMouseY())*(y-mouseinput.getMouseY()));
			dx = ((-speed/distance) * diffX);
			dy = ((-speed/distance) * diffY);
		}
		oldmouseX = mouseinput.getMouseX();
		oldmouseY = mouseinput.getMouseY();
		
		x+=dx;
		y+=dy;
		// weltgrenze
		if(x < 0)x = 0;
		if(x > map[0]*map[2])x = map[0]*map[2];
		if(y < 0)y = 0;
		if(y > map[0]*map[1])y = map[0]*map[1];
	}
	
	public void render(Graphics2D g) {
		// füllung
		g.setColor(color);
		g.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
		// ring
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()).darker());
		g.setStroke(new BasicStroke(5));
		g.drawOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
		g.setStroke(new BasicStroke(0));

		//name
		g.setFont(new Font("sansserif", 1, fontsize));
		if(g.getFontMetrics().getStringBounds(username, g).getWidth() > r*2-2)fontsize--;
		if(g.getFontMetrics().getStringBounds(username, g).getWidth() < r*2-1)fontsize++;
			
		int fontwidth = (int) g.getFontMetrics().getStringBounds(username, g).getWidth();
		int fontheight = (int) g.getFontMetrics().getStringBounds(username, g).getWidth();
		
		g.setColor(Color.darkGray);
		g.drawString(username, (int)(x-fontwidth/2)+shadowoffset, (int)(y+fontheight/5)+shadowoffset);
		g.setColor(Color.white);
		g.drawString(username, (int)(x-fontwidth/2), (int)(y+fontheight/5));
	}

	public int getR() { return (int)r; }
	public void setR(int r) { this.r = r; }
	public int getX() { return (int) x; }
	public int getY() { return (int) y; }
	public String getUsername() { return username; }
}
