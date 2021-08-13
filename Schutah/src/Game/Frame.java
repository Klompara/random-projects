package Game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

public class Frame {

	private Game game;
	
	public Frame() {
		initGL();
		game = new Game();
	}
	
	public void update(float delta) {
		game.update(delta);
	}

	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glPushMatrix();
		
		game.render();
		
		glPopMatrix();
	}
	
	private void initGL() {
		glClearColor(0.2f, 0.2f, 0.2f, 1);
		glEnable(GL_DEPTH_TEST);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(60, (float)Display.getWidth()/(float)Display.getHeight(), 0.1f, 1000);
		glMatrixMode(GL_MODELVIEW);
		glFrontFace(GL_CCW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		
		Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
		Mouse.setGrabbed(true);
	}
}
