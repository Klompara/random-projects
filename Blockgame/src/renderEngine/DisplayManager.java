package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int FPS_CAP = 120;

	private static String titel = "M Replica";

	private static long lastFrameTime;
	private static float delta = 0f;
	private static long fps;
	private static long lastFPS;

	public static void createDisplay() {
		ContextAttribs attribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setFullscreen(false);
			Display.create(new PixelFormat(), attribs);
			Display.setVSyncEnabled(false);
			// Display.create();
			Display.setTitle(titel);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		lastFrameTime = getCurrentTime();
		lastFPS = getCurrentTime();
		System.out.println("OpenGL: " + GL11.glGetString(GL11.GL_VERSION));
	}

	public static void updateDisplay() {
		Display.sync(FPS_CAP);
		Display.update();
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime) / 1000f;
		lastFrameTime = currentFrameTime;
		updateFPS();
	}

	public static void updateFPS() {
		if (getCurrentTime() - lastFPS > 1000) {
			Display.setTitle(titel + "FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public static void closeDisplay() {
		Display.destroy();
	}

	public static float getFrameTimeSeconds() {
		return delta;
	}

	private static long getCurrentTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
}
