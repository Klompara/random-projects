package Game.GameStates.States;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Game.Entitys.Block;
import Game.Entitys.Player;
import Game.Main.MainLoop;
import Game.gfx.VCam;
import Game.gfx.WorldGenerator;

public class State_Play {
	
	/*
	 * TODO:
	 * Worldgenerator
	 * Npc
	 * Textures
	 * Shadow
	 */
	
	public Player player;
	public ArrayList<Block> blocks;
	private VCam cam;
	private WorldGenerator worldGenerator;
	
	public State_Play() {
		player = new Player(0, -48, this);
		blocks = new ArrayList<Block>();
		worldGenerator = new WorldGenerator(this);
		worldGenerator.generateworld();
		cam = new VCam(this);

	}
	
	public void tick() {
		player.tick();
		cam.tick();
		for(int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			b.tick();
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, MainLoop.WIDHT, MainLoop.HEIGHT);
		
		
		g.translate(-cam.getX(), -cam.getY());

		player.render(g);
		for(int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			b.render(g);
		}

		
		g.translate(cam.getX(), cam.getY());
	}
}
