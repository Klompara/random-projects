package cells.Entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.net.InetAddress;

public class PlayerMP {
	private int x;
	private int y;
	
	private int r;
	
	private int fontsize;
	private float shadowoffset;
	
	private Color color;
	
	private String username;
	
	private InetAddress ipAddress;
	private int port;
	
	public PlayerMP(int x, int y, Color color, String username, InetAddress ipAddress, int port){
		this.x = x;
		this.y = y;
		this.username = username;
		this.color = color;
		this.ipAddress = ipAddress;
		this.port = port;
		fontsize = 0;
		shadowoffset = 1.75f;
		this.r = 8;
	}
	
	public void tick() {
		
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
	
	
	public int getR() { return r; }
	public void setR(int r) { this.r = r; }
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public InetAddress getIpAddress() { return ipAddress; }
	public void setIpAddress(InetAddress ipAddress) { this.ipAddress = ipAddress; }
	public int getPort() { return port; }
	public void setPort(int port) { this.port = port; }
	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }
}
