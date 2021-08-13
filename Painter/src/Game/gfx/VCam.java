package Game.gfx;

import Game.Mainloop;
import Game.GameStates.States.State_Play;

public class VCam {
	private State_Play handler;
	private float x, y;
	private float dx, dy;
	public VCam(State_Play handler) {
		this.handler = handler;
	}
	
	public void tick() {
		if(x >= handler.player.getX()+48 && x <= handler.player.getX()-16 && y >= handler.player.getY()-16 && y <= handler.player.getY()+48+16) {}else{
			float diffX = (x+Mainloop.WIDTH/2) - (handler.player.getX()+16);
			float diffY = (y+Mainloop.HEIGHT/2) - (handler.player.getY()+48/2);
			float distance = (float) Math.sqrt((diffX*diffX)+(diffY*diffY));
			dx = ((-1/distance) * diffX)*distance/10;
			dy = ((-1/distance) * diffY)*distance/8;
			x+=dx;
			y+=dy;	
		}
	}

	public int getX() { return (int)x; }
	public int getY() { return (int)y; }
}
