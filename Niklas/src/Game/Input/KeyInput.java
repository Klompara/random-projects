package Game.Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.Handler;

public class KeyInput extends KeyAdapter implements KeyListener {

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A){ Handler.player.dx = -Handler.player.speed; }
		if(key == KeyEvent.VK_D){ Handler.player.dx = Handler.player.speed; }
		if(key == KeyEvent.VK_SPACE && !Handler.player.jumping){ Handler.player.jumping = true; Handler.player.dy = Handler.player.jumpstart*-1; }
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A){ Handler.player.dx = 0; }
		if(key == KeyEvent.VK_D){ Handler.player.dx = 0; }
	}
}
