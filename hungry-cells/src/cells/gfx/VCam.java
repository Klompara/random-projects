package cells.gfx;

import cells.Main.Handler;
import cells.Main.Main;

public class VCam {
	private int x;
	private int y;
	
	private double zoom;
	
	public VCam() {}
	
	public void tick(Handler handler) {
		zoom = 1-handler.player.getR()/250;
		
		x = (int) ((int) -(handler.player.getX())+Main.WIDTH/2);
		y = (int) ((int) -handler.player.getY()+Main.HEIGHT/2);
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public double getZoom() { System.out.println("getZoom");return zoom; }
}
