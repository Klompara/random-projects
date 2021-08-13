package Render;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DisplayManager {
	
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	private static final int FPS_CAP = 120;
	
	private static String titel = "Game";
	
	private static long lastFrameTime;
	private static float delta = 0f;
	private static long fps;
	private static long lastFPS;
	
	public static void createDisplay(){
		//ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			//Display.create(new PixelFormat(), attribs);
			Display.setVSyncEnabled(false);
			Display.create();
			Display.setTitle(titel);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		lastFrameTime = getCurrentTime();
		lastFPS = getCurrentTime();
	}
	
	public static void setCamera() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}
	
	public static void updateDisplay(){
		Display.sync(FPS_CAP);
		Display.update();
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
		updateFPS();
	}
	
	public static void updateFPS() {
	    if (getCurrentTime() - lastFPS > 1000) {
	        Display.setTitle(titel + ", fps: " + fps); 
	        fps = 0;
	        lastFPS += 1000;
	    }
	    fps++;
	}
	
	public static void closeDisplay(){
		Display.destroy();
	}
	
	public static float getFrameTimeSeconds() { return delta; } 
	private static long getCurrentTime() { return Sys.getTime()*1000/Sys.getTimerResolution(); } 
}
