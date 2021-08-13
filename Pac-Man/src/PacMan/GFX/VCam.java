package PacMan.GFX;

import PacMan.Handler;
import PacMan.PacMan;
import PacMan.Entity.Block;

public class VCam {
	public int x;
	public int y;
	
	private int grenze;
	private int max;
	
	private Handler handler;
	
	public VCam(Handler handler) {
		this.x = handler.player.x-PacMan.WIDTH/2;
		this.y = 0;
		this.handler = handler;
		
		for(int i = 0; i < handler.blocks.size(); i++) {
			Block block = handler.blocks.get(i);
			if(block.x > max){
				max = block.x;
			}
		}
		
		grenze = 200;
	}
	
	public void tick() {
		if(handler.player.x - x > PacMan.WIDTH-grenze && x < max-PacMan.WIDTH+32){
			x = handler.player.x - PacMan.WIDTH+grenze;
		}else if(x > max-PacMan.WIDTH+32){
			x = max-PacMan.WIDTH+32;
		}else if(handler.player.x - x < grenze && x > 0) {
			x = handler.player.x - grenze;
		}else if(x < 0){
			x = 0;
		}else if(handler.player.x == max+64){
			handler.player.x = -32;
		}else if(handler.player.x == -64){
			handler.player.x = max+32;
		}
		
	}
}