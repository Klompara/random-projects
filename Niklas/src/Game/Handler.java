package Game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Game.Entity.Block;
import Game.Entity.Bullet;
import Game.Entity.Player;
import Game.gfx.Textures;
import Game.gfx.VCam;

public class Handler {
	
	public static Player player;
	public static ArrayList<Block> blocks;
	public static ArrayList<Bullet> bullets;
	
	private WorldLoader wloader;
	
	private Textures tex = null;
	
	public Handler(VCam cam) {
		blocks = new ArrayList<Block>();
		bullets = new ArrayList<Bullet>();
		player = new Player(cam);
		
		wloader = new WorldLoader();
		wloader.loadworld();
		tex = new Textures();
		tex.loadTextures();
	}
	
	public void tick() {
		player.tick();
		
		for(int i = 0; i < blocks.size(); i++){
			Block block = blocks.get(i);
			block.tick();
		}
		
		for(int i = 0; i < bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			bullet.tick();
		}
	}
	
	public void render(Graphics2D g){
		player.render(g);
		
		for(int i = 0; i < blocks.size(); i++){
			Block block = blocks.get(i);
			block.render(g);
		}
		
		for(int i = 0; i < bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			bullet.render(g);
		}
	}
}
