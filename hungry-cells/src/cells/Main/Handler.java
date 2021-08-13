package cells.Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cells.Entity.Cell;
import cells.Entity.Player;
import cells.Entity.PlayerMP;
import cells.Inputs.MouseInput;
import cells.gfx.VCam;

public class Handler {
	public Player player;
	public ArrayList<PlayerMP> otherPlayers;
	private ArrayList<Cell> cells;
	public ArrayList<Color> colors;
	
	private int gridblocksize;
	private int gridheight;
	private int gridwidth;
	
	private VCam cam;
	
	public Handler(MouseInput mouseInput, VCam cam) {
		this.cam = cam;
		otherPlayers = new ArrayList<PlayerMP>();
		colors = new ArrayList<Color>();
		
		colors.add(new Color(102, 153, 255)); // hellblau
		colors.add(new Color(51 , 102, 204)); // dunkelblau
		colors.add(new Color(255, 51 , 255)); // pink
		colors.add(new Color(204, 51 , 204)); // lila
		colors.add(new Color(0  , 204, 51 )); // dunkelgrün
		colors.add(new Color(51 , 255, 51 )); // hellgrün
		colors.add(new Color(255, 204, 51 )); // orange
		colors.add(new Color(255, 51 , 51 )); // rot
		
		gridblocksize = 25;
		gridheight = 150;
		gridwidth = 150;
		
		int[] map = new int[3];
		map[0] = gridblocksize;
		map[1] = gridheight;
		map[2] = gridwidth;
		
		cells = new ArrayList<Cell>();
		player = new Player(Main.random(gridwidth*gridblocksize, 0), Main.random(gridheight*gridblocksize, 0), JOptionPane.showInputDialog("Nickname"), mouseInput, colors.get(Main.random(colors.size()-1, 0)), map);
	}
	
	public void tick() {
		player.tick();
		
		if(!Main.isHost) {

		}else{
			// cell collision
			int px = player.getX();
			int py = player.getY();
			int pr = player.getR();
			for(int i = 0; i < cells.size(); i++) {
				int cx = cells.get(i).getX();
				int cy = cells.get(i).getY();
				int cr = cells.get(i).getR();
					
				int dx = px - cx;
				int dy = py - cy;
				double dist = Math.sqrt(dx * dx + dy *dy);
				
				if(dist < pr + cr) {
					player.setR(player.getR()+1);
					cells.remove(cells.get(i));
				}
			}
			
			// cell collision
			for(int i = 0; i < otherPlayers.size(); i++){
				int p2x = otherPlayers.get(i).getX();
				int p2y = otherPlayers.get(i).getY();
				int p2r = otherPlayers.get(i).getR();
				for(int j = 0; j < cells.size(); j++) {
					int cx = cells.get(j).getX();
					int cy = cells.get(j).getY();
					int cr = cells.get(j).getR();
						
					int dx = p2x - cx;
					int dy = p2y - cy;
					double dist = Math.sqrt(dx * dx + dy *dy);
					
					if(dist < p2r + cr) {
						player.setR(player.getR()+1);
						cells.remove(cells.get(i));
					}
				}
			}

			for(int i = 0; i < otherPlayers.size(); i++) {
				PlayerMP p = otherPlayers.get(i);
				int p2x = p.getX();
				int p2y = p.getY();
				
				for(int j = 0; j < cells.size(); j++) {
					Cell c = cells.get(i);
					
					if(c.getX() > p2x-Main.WIDTH/2 && c.getX() < p2x+Main.WIDTH && c.getY() > p2y-Main.HEIGHT/2 && c.getY() < p2y+Main.HEIGHT/2) {
						
					}
				}
			}
			
			
			try {
				Main.server.sendtoall(("01;" + player.getR()+";"+player.getX()+";"+player.getY()+";").getBytes(), InetAddress.getByName("localhost"));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			if(cells.size() < 1250){
				cells.add(new Cell(Main.random(gridblocksize*gridwidth, 0), Main.random(gridblocksize*gridheight, 0), colors.get(Main.random(colors.size()-1, 0))));
				cells.add(new Cell(Main.random(gridblocksize*gridwidth, 0), Main.random(gridblocksize*gridheight, 0), colors.get(Main.random(colors.size()-1, 0))));
			}else if(Main.random(10, 0) == 5) {
				cells.add(new Cell(Main.random(gridblocksize*gridwidth, 0), Main.random(gridblocksize*gridheight, 0), colors.get(Main.random(colors.size()-1, 0))));
			}

		}
		// andere spieler ticken
		for(int i = 0; i < otherPlayers.size(); i++) {
			PlayerMP player = otherPlayers.get(i);
			player.tick();
		}
	}
	
	public void render(Graphics2D g) {
		g.translate(cam.getX(), cam.getY()); // virtuelle kamera start
		
		
		// zeichne raster
		g.setColor(Color.gray);
		for(int i = -500; i <= gridheight+500; i++) {
			g.drawLine(-500, i*gridblocksize, gridheight*gridblocksize+500, i*gridblocksize);
		}
		for(int i = -500; i <= gridwidth+500; i++) {
			g.drawLine(i*gridblocksize, -500, i*gridblocksize, gridwidth*gridblocksize+500);
		}
		
		// andere spieler rendern
		for(int i = 0; i < otherPlayers.size(); i++) {
			PlayerMP p = otherPlayers.get(i);
			if(p.getX() > -cam.getX() && p.getX() < -cam.getX()+Main.WIDTH && p.getY() > -cam.getY() && p.getY() < -cam.getY()+Main.HEIGHT){
				p.render(g);
			}
		}
		
		// cellen
		for(int i = 0; i < cells.size(); i++) {
			Cell c = cells.get(i);
			if(c.getX() > -cam.getX() && c.getX() < -cam.getX()+Main.WIDTH && c.getY() > -cam.getY() && c.getY() < -cam.getY()+Main.HEIGHT){
				c.render(g);
			}
		}
		
		player.render(g);
		
		g.translate(-cam.getX(), -cam.getY()); // virutelle kamera ende
	}
}
