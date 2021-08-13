package Game.gfx;

import Game.Handler;
import Game.Main;


public class VCam {
	
	private float x;
	private float y;
	
	public VCam(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		x = -Handler.player.x + Main.WIDTH/2 - Handler.player.width/2;
		//y = -Handler.player.y + Main.HEIGHT/2 - Handler.player.height/2;
	}

	public float getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
