package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	public static boolean isKeyDown = false;
	public static int currentKeyDown;
	
	public KeyInput() {}
	
	public void keyPressed(KeyEvent e) {
		isKeyDown = true;
		currentKeyDown = e.getKeyCode();
	}
	
	public void keyReleased(KeyEvent e) {
		isKeyDown = false;
	}
}
