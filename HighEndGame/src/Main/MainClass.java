package Main;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class MainClass {
    private static final String WINDOW_TITLE = "";
    private static final int[] WINDOW_DIMENSIONS = {800, 600};
	
    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
    }
    
    private static void logic() {
    	
    }
    
    private static void input() {
    	if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) { System.exit(0); }
    }
    
    private static void cleanUp(boolean asCrash) {
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }
    
    private static void setUpMatrices() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 320, 0, 240, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }
    
    private static void setUpObjects() {
    	
    }
    
    private static void update() {
        Display.update();
        Display.sync(60);
    }
    
    private static void enterGameLoop() {
        while (!Display.isCloseRequested()) {
            render();
            logic();
            input();
            update();
        }
    }
    
    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]));
        	Display.setFullscreen(true);
            Display.setTitle(WINDOW_TITLE);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    public static void main(String[] args) {
        setUpDisplay();
        setUpObjects();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }
}
