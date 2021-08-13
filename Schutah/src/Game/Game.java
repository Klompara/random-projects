package Game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Game {

	private Player player;
	private Level level;
	
	public Game() {
		player = new Player(0, 1, 5);
		level = new Level();
	}
	
	public void update(float delta) {
		player.update(delta);
		
		checkinput();
	}
	
	private void checkinput() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}
	}

	public void render() {
		glRotatef(player.getRotation().x, 1, 0, 0);
		glRotatef(player.getRotation().y, 0, 1, 0);
		glTranslated(-player.getPos().x, -player.getPos().y, -player.getPos().z);
		glBegin(GL_QUADS);
		glColor3f(1, 0, 0);
		glVertex3f(0, 0, 0);
		glColor3f(0, 1, 0);
		glVertex3f(1, 0, 0);
		glColor3f(0, 0, 1);
		glVertex3f(1, 1, 0);
		glColor3f(1, 0, 1);
		glVertex3f(0, 1, 0);
		
		level.render();
		
		glEnd();
	}
}
