package PacMan.GFX.GameStates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import PacMan.net.NetServer;

public class Lobby {
	private NetServer server;
	public Lobby(NetServer server) {
		this.server = server;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		if(server != null){
			for(int i = 0; i < server.connectedPlayers.size(); i++) {
				g.setStroke(new BasicStroke(3));
				g.setColor(Color.white);
				g.drawRect(100*i+25, 100, 65, 65);
			    g.setStroke(new BasicStroke(1));
			    g.setFont(new Font("sansserif", 1, 12));
				g.drawString(server.connectedPlayers.get(i).getUsername(), (int) (100*i+25+(65/2)-(g.getFontMetrics().getStringBounds(server.connectedPlayers.get(i).getUsername(), g).getWidth()/2)), 100+65/2);
			}
		}else{
			
		}
	}
}
