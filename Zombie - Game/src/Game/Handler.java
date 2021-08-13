package Game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Game.Entitys.Block;
import Game.Entitys.Bullet;
import Game.Entitys.Light;
import Game.Entitys.Particel;
import Game.Entitys.PlayerMP;
import Game.Input.MouseInput;
import Game.net.Client;

public class Handler {

	private Client client;
	
	private String playerName;
	
	private boolean moveL, moveR, moveU, moveD;
	
	public int selectedWeapon;
	
	public static BufferedImage	Texture_Dirt,
								Texture_Wall;
	
	public LinkedList<Block> bloecke;
	public LinkedList<Bullet> bullets;
	public LinkedList<Particel> particel;
	
	private JFrame window;
	
	private long firingTimer;
	public long firingDelay;
	public int spray;
	
	private BufferedImage lightMap = new BufferedImage(48*32, 48*32, BufferedImage.TYPE_INT_ARGB);
	public LinkedList<Light> lights = new LinkedList<Light>();
	Graphics2D gg = null;
	
	public Handler(Client client, String playerName, JFrame window) {
		bloecke = new LinkedList<Block>();
		bullets = new LinkedList<Bullet>();
		particel = new LinkedList<Particel>();

		selectedWeapon = 1;
		
		firingDelay = 500;
		firingTimer = 0;
		spray = 1;

		this.client = client;
		this.playerName = playerName;
		this.window = window;
		
		loadTextures();
		loadWorld();
	}
	
	
	public void tick() {
		for(int i = 0; i < client.otherPlayers.size(); i++) {
			PlayerMP p = client.otherPlayers.get(i);
			p.tick();
		}
		
		// Bullet checks
		for(int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			if(b.getX() > 48*32+10 || b.getX() < -10 || b.getY() > 48*32+10 || b.getY() < -10) {
				b.removethis(this);
			}else {
				b.tick();
			}
		}
		
		for(int i = 0; i < lights.size(); i++) {
			lights.get(i).tick();
		}
		
		for(int i = 0; i < particel.size(); i++) {
			Particel p = particel.get(i);
			p.tick();
		}
		
		if(MouseInput.isMouseDown) {
			long elapsed = (System.nanoTime() - firingTimer) / 1000000;
			if(elapsed > firingDelay) {
				firingTimer = System.nanoTime();
				
				String weapon = "INVALID WEAPON";
				int speed = 10;
				
				if(selectedWeapon == 1) { weapon = "PISTOL"; }
				else if(selectedWeapon == 2) { weapon = "UZI"; }
				else if(selectedWeapon == 3) { weapon = "SHOTGUN"; }
				else if(selectedWeapon == 4) { weapon = "GRANADE"; }
				
				// spray
				double distX = getPlayer().getX() + getPlayer().getWidth()/2 - MouseInput.mouseX;
				double distY = getPlayer().getY() + getPlayer().getHeight()/2 - MouseInput.mouseY;
				double difference = (Math.sqrt(distX*distX + distY*distY));
				double dx = (-speed/difference) * distX;
				double dy = (-speed/difference) * distY;
				
				dx += Main.random(spray, -spray);
				dy += Main.random(spray, -spray);
				
				if(weapon != "SHOTGUN")client.sendData(("03;"+getPlayer().getUsername()+";"+weapon+";"+dx+";"+dy+";").getBytes());
				else {
					for(int i = 0; i < Main.random(15, 10); i++) {
						client.sendData(("03;"+getPlayer().getUsername()+";"+weapon+";"+(dx+Main.random(5, -5))+";"+(dy+Main.random(5, -5))+";").getBytes());
					}
				}
			}
		}
		
		
		// Ghost move fix
		if(!window.isFocused()) { stopWalk(); }
		if(getPlayer().isMLeft() != moveL || getPlayer().isMRight() != moveR || getPlayer().isMDown() != moveD || getPlayer().isMUp() != moveU) {
			moveL = getPlayer().isMLeft();
			moveR = getPlayer().isMRight();
			moveD = getPlayer().isMDown();
			moveU = getPlayer().isMUp();
			client.sendData(("01;"+playerName+";"+moveL+";"+moveR+";"+moveD+";"+moveU+";"+getPlayer().getX()+";"+getPlayer().getY()+";").getBytes());
		}
	}

	
	public void render(Graphics2D g) {
		for(int i = 0; i < bloecke.size(); i++) {
			Block b = bloecke.get(i);
			b.render(g);
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			b.render(g);
		}
		
		for(int i = 0; i < client.otherPlayers.size(); i++) {
			PlayerMP p = client.otherPlayers.get(i);
			p.render(g);
		}
		
		for(int i = 0; i < particel.size(); i++) {
			Particel p = particel.get(i);
			p.render(g);
		}
		
		gg = (Graphics2D) lightMap.getGraphics();
		gg.setColor(new Color(0, 0, 0, 255));
		gg.fillRect(0, 0, lightMap.getWidth(), lightMap.getHeight());
		gg.setComposite(AlphaComposite.DstOut);
		for(int i = 0; i < lights.size(); i++) {
			Light l = lights.get(i);
			l.render(gg);
		}
		gg.dispose();
		
		g.drawImage(lightMap, 0, 0, null);
	}

	public PlayerMP getPlayer() {
		for(int i = 0; i < client.otherPlayers.size(); i++) {
			PlayerMP p2 = client.otherPlayers.get(i);
			if(p2.getUsername().trim().equalsIgnoreCase(playerName)){
				return p2;
			}
		}
		return new PlayerMP("DIESER SPIELER EXISTIERT NICHT!", -1, client);
	}
	
	public void stopWalk() { // das er sich nicht bewegt wenn nicht focusiert
		getPlayer().setMDown(false);
		getPlayer().setMLeft(false);
		getPlayer().setMUp(false);
		getPlayer().setMRight(false);
	}
	
	private void loadTextures() {
		try {
			Texture_Dirt = ImageIO.read(getClass().getResourceAsStream("/dirt.png"));
			Texture_Wall = ImageIO.read(getClass().getResourceAsStream("/stone.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadWorld() {
		BufferedImage world = null;
		try {
			world = ImageIO.read(getClass().getResourceAsStream("/level.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int w = world.getWidth();
		int h = world.getHeight();
		for(int xx = 0; xx < w; xx++){
			for(int yy = 0; yy < h; yy++){
				int Pixel = world.getRGB(xx, yy);
				int red = (Pixel >> 16) & 0xff;
				int green = (Pixel >> 8) & 0xff;
				int blue = (Pixel) & 0xff;
				
				if(red == 129 && green == 129 && blue == 129) { bloecke.add(new Block(xx*48, yy*48, 2)); }
				if(red == 0   && green == 150 && blue == 64 ) { bloecke.add(new Block(xx*48, yy*48, 1)); }
			}
		}
	}
}
