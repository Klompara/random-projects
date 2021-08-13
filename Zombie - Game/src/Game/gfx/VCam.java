package Game.gfx;

import Game.Handler;
import Game.Main;

public class VCam {
	
	private int x;
	private int y;
	
	private int maxX;
	private int maxY;
	private int min;
	
	public VCam() {
		maxX = -768;
		maxY = -960;
		min = 0;
	}
	
	public void tick(Handler handler) {
		
		x = -handler.getPlayer().getX() + Main.WIDHT/2 - handler.getPlayer().getWidth()/2;
		if(x < maxX) x = maxX;
		if(x > min) x = min;
		y = -handler.getPlayer().getY() + Main.HEIGHT/2 - handler.getPlayer().getHeight()/2;
		if(y < maxY) y = maxY;
		if(y > min) y = min;
	}
	
	
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
}
