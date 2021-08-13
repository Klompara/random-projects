package PacMan.Entity;

import java.awt.Graphics2D;
import java.net.InetAddress;

public class PlayerMP {
	
	
	public InetAddress ipAddress;
	public int port;
	private String username;
	
	public PlayerMP(String username, InetAddress ipAddress, int port) {
		this.ipAddress = ipAddress;
		this.port = port;
		this.username = username;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		
	}
	
	public String getUsername() {
		return this.username;
	}
}
