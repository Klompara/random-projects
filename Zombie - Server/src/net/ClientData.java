package net;

import java.net.InetAddress;

public class ClientData {
	private String username;
	private InetAddress ipAddress;
	private int port;
	private int type;
	private int x;
	private int y;
	
	private int ammo_pistol;
	private int ammo_uzi;
	private int ammo_shotgun;
	private int ammo_granade;
	
	public ClientData(String username, InetAddress ipAddress, int port, int type) {
		this.username = username;
		this.ipAddress = ipAddress;
		this.port = port;
		this.type = type;
		
		ammo_pistol = 999;
		ammo_uzi = 999;
		ammo_shotgun = 999;
		ammo_granade = 999;
	}


	public int getAmmo_pistol() { return ammo_pistol; } 
	public void setAmmo_pistol(int ammo_pistol) { this.ammo_pistol = ammo_pistol; } 

	public int getAmmo_uzi() { return ammo_uzi; } 
	public void setAmmo_uzi(int ammo_uzi) { this.ammo_uzi = ammo_uzi; } 

	public int getAmmo_shotgun() { return ammo_shotgun; } 
	public void setAmmo_shotgun(int ammo_shotgun) { this.ammo_shotgun = ammo_shotgun; } 

	public int getAmmo_granade() { return ammo_granade; } 
	public void setAmmo_granade(int ammo_granade) { this.ammo_granade = ammo_granade; } 

	public int getX() { return x; } 
	public void setX(int x) { this.x = x; } 
	
	public int getY() { return y; } 
	public void setY(int y) { this.y = y; } 
	
	public int getType() { return type; } 
	public void setType(int type) { this.type = type; } 
	
	public String getUsername() { return username; } 
	public void setUsername(String username) { this.username = username; } 
	
	public InetAddress getIpAddress() { return ipAddress; } 
	public void setIpAddress(InetAddress ipAddress) { this.ipAddress = ipAddress; } 
	
	public int getPort() { return port; } 
	public void setPort(int port) { this.port = port; } 
}
