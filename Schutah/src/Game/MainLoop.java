package Game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class MainLoop {

	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(800, 500));
			Display.setTitle("Schutah");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		Frame frame = new Frame();
		long lastFrame = System.currentTimeMillis();
		while(!Display.isCloseRequested()) {
			long thisFrame = System.currentTimeMillis();
			float delta = (thisFrame - lastFrame) / 1000f;
			lastFrame = thisFrame;
			
			frame.update(delta);
			
			frame.render();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
}
