package Game.Entitys;

import Game.Game;
import Game.Input.MouseInput;

public class Camera {
	private double x, y;
	private int border, slowing;
	
	public Camera(double x, double y) {
		this.x = x;
		this.y = y;
		border = 100;
		slowing = 7;
	}
	
	public void tick() {
		if(MouseInput.mouseX+x < border && x < 0) {
			double movement = ((MouseInput.mouseX+x)-border) /-slowing;
			x+=movement;
			MouseInput.mouseX-=movement;
		}else if(x > 0) {
			x = 0;
		}
		if(MouseInput.mouseX+x > Game.WIDTH-border && x > -(Game.WIDTH*2)) {
			double movement = ((MouseInput.mouseX+x - Game.WIDTH) + border) /-slowing;
			x+=movement;
			MouseInput.mouseX-=movement;
		}else if(x < -(Game.WIDTH*2)) {
			x = -(Game.WIDTH*2);
		}
	}

	public double getX() { return x; } 
	public void setX(double x) { this.x = x; } 

	public double getY() { return y; } 
	public void setY(double y) { this.y = y; } 
}
