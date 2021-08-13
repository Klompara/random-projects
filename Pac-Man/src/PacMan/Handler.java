package PacMan;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import PacMan.Entity.Block;
import PacMan.Entity.Player;
import PacMan.Entity.Punkt;

public class Handler {
	public LinkedList<Block> blocks;
	public LinkedList<Punkt> punkte;
	public Player player;
	
	public boolean hitboxes;
	
	public int score;
	public int tempscore;
	
	public static BufferedImage Viagra,
								Apfel;
	
	public Handler(PacMan game) {
		//game.cam = new VCam(this);
		
		score = 0;
		tempscore = 0;
		
		blocks = new LinkedList<Block>();
		punkte = new LinkedList<Punkt>();
		loadTextures();
		loadLevel();
	}
	
	private void loadLevel() {
		BufferedImage level = null;
		try { level = ImageIO.read(getClass().getResourceAsStream("/Level.png"));
		} catch (IOException e) { e.printStackTrace(); }
		
		int w = level.getWidth();
		int h = level.getHeight();
		for(int xx = 0; xx < w; xx++){
			for(int yy = 0; yy < h; yy++){
				int Pixel = level.getRGB(xx, yy);
				int red = (Pixel >> 16) & 0xff;
				int green = (Pixel >> 8) & 0xff;
				int blue = (Pixel) & 0xff;
				
				if(red == 0   && green == 0   && blue == 255){ blocks.add(new Block(xx*32, yy*32, 0));  }
				if(red == 0   && green == 255 && blue == 0  ){ blocks.add(new Block(xx*32, yy*32, 1));  }
				if(red == 227 && green == 6   && blue == 19 ){ punkte.add(new Punkt(xx*32, yy*32, 0));  }
				if(red == 102 && green == 102 && blue == 102){ punkte.add(new Punkt(xx*32, yy*32, 1));  }
				//if(red == 255 && green == 255 && blue == 0  ){ player = new Player(xx*32, yy*32, this); }
			}
		}
	}
	
	public void render(Graphics2D g) {
		for(int i = 0; i < blocks.size(); i++) {
			Block block = blocks.get(i);
			block.render(g);
			
			if(hitboxes){
				g.setColor(Color.cyan);
				g.draw(block.BBottom());
				g.draw(block.BLeft());
				g.draw(block.BRight());
				g.draw(block.BTop());
			}
		}
		
		for(int i = 0; i < punkte.size(); i++) {
			Punkt punkt = punkte.get(i);
			punkt.render(g);
			
			if(hitboxes){
				g.setColor(Color.cyan);
				g.draw(punkt.bound());
			}
		}
		
		if(hitboxes){
			g.draw(player.BBottom());
			g.draw(player.BLeft());
			g.draw(player.BRight());
			g.draw(player.BTop());
		}
		player.render(g);

	}
	
	public void tick() {
		for(int i = 0; i < blocks.size(); i++) {
			Block block = blocks.get(i);
			block.tick();
		}
		
		player.tick();
		
		for(int i = 0; i < punkte.size(); i++) {
			Punkt punkt = punkte.get(i);
			punkt.tick();
			
			if(player.Bounds().intersects(punkt.bound())){
				if(punkt.type == 0)			score += 10;
				else if(punkt.type == 1) 	score += 50;
				else System.out.println("invalid type!");
				
				punkte.remove(punkt);
			}
		}
		
		if(tempscore < score) { tempscore++; }
	}
	
	private void loadTextures() {
		try {
			Viagra = ImageIO.read(getClass().getResourceAsStream("/viagra.png"));
			Apfel = ImageIO.read(getClass().getResourceAsStream("/Apfel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
