package Simulator;

import java.util.ArrayList;

public class Cam {

	private int x, y;
	
	public void tick(ArrayList<Ball> balls) {
		for(int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			if(b.isMainball()) {
				x = (int) (-b.getX() + MainLoop.WIDTH/2);
				y = (int) (-b.getY() + MainLoop.HEIGHT/2);
			}
		}
	}

	public int getX() { return x; } 
	public void setX(int x) { this.x = x; } 

	public int getY() { return y; } 
	public void setY(int y) { this.y = y; }

}
