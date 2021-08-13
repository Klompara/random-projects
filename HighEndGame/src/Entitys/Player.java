package Entitys;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Player extends Entity {

	private double width = 32, height = 32;
	
	public Player(double x, double y) {
		super(x, y);
	}

	public void tick() {
		checkInput();
		
		x += dx;
		y += dy;
	}

	public void render() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x+width, y);

		GL11.glVertex2d(x+width, y+height);
		GL11.glVertex2d(x, y+height);
		GL11.glEnd();
	}
	
	private void checkInput() {
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			dx = 1;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			dx = -1;
		}else{
			dx = 0;
		}
	}
}
