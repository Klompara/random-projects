package Game.Inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.GameStates.StateHandler;
import Game.GameStates.StateHandler.GAMESTATES;

public class KeyInput extends KeyAdapter implements KeyListener{
	private StateHandler handler;
	private boolean mainmenukeydown;
	public KeyInput(StateHandler handler) { this.handler = handler; }
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (handler.currentState) {
		case intro:
			handler.currentState = GAMESTATES.mainmenu;
			break;

		case mainmenu:
			if(key == KeyEvent.VK_DOWN && !mainmenukeydown){ handler.mainmenu.next(1); mainmenukeydown = true; }
			if(key == KeyEvent.VK_UP && !mainmenukeydown){ handler.mainmenu.next(-1); mainmenukeydown = true; }
			if(key == KeyEvent.VK_ENTER)handler.mainmenu.next(0);
			break;
		
		case options:
			break;
			
		case play:
			if(key == KeyEvent.VK_A)
				handler.play.player.setLeft(true);
			if(key == KeyEvent.VK_D)
				handler.play.player.setRight(true);
			break;
		
		default:
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (handler.currentState) {
		case play:
			if(key == KeyEvent.VK_A)
				handler.play.player.setLeft(false);
			if(key == KeyEvent.VK_D)
				handler.play.player.setRight(false);
			break;
		
		case mainmenu:
			mainmenukeydown = false;
			break;
			
		default:
			break;
		}
	}
}
