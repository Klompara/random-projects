package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ENTER) {
			Main.update = !Main.update;
		}
		if(key == KeyEvent.VK_ESCAPE) {
			Main.running = false;
		}
	}
}
