package Game.GameStates.States;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Game.Mainloop;
import Game.Entitys.Block;
import Game.Entitys.Player;
import Game.gfx.VCam;

public class State_Play {
	
	/*
	 * TODO:
	 * Worldgenerator
	 * Npc
	 * Textures
	 * Shadow
	 */
	public ArrayList<Block> blocks;
	public VCam cam;
	public Player player;
	
	public State_Play() {
		blocks = new ArrayList<Block>();
		cam = new VCam(this);
	}
	
	public void tick() {
		cam.tick();
		player.tick();
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Mainloop.WIDTH, Mainloop.HEIGHT);
		
		g.translate(-cam.getX(), -cam.getY());

		player.render(g);

		g.translate(cam.getX(), cam.getY());
	}
}
