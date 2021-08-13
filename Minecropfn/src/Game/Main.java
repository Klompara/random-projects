package Game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import Game.GameStates.GameState;

public class Main {
	private int fps;
	private long lastFPS;

	private GameState gamestatemanager;

	public Main() {
		initDisplay();
		load();
		loop();
		cleanup();
	}

	private void loop() {
		Mouse.setGrabbed(true);

		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();

			gamestatemanager.update();

			updateFPS();
			Display.update();
		}
	}

	private void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 800 / 12 * 9));
			Display.setTitle("3 Tee");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	private void cleanup() {
		Display.destroy();
		System.exit(0);
	}

	private void load() {
		gamestatemanager = new GameState();
		lastFPS = ((Sys.getTime() * 1000) / Sys.getTimerResolution());
	}

	public void updateFPS() {
		if (((Sys.getTime() * 1000) / Sys.getTimerResolution()) - lastFPS > 1000) {
			Display.setTitle("Minecropfn: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public static int random(int max, int min) {
		int range = (max - min) + 1;
		return (int) ((Math.random() * range) + min);
	}

	public static Texture loadTexture(String key) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + key + ".png")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		new Main();
	}
}