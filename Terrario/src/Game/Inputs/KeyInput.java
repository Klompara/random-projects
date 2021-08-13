package Game.Inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.GameStates.StateHandler;
import Game.GameStates.StateHandler.GAMESTATES;
import Game.Main.MainLoop;

public class KeyInput extends KeyAdapter implements KeyListener{
	private StateHandler handler;
	public KeyInput(StateHandler handler) { this.handler = handler; }
	

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (handler.currentState) {
		case intro:
			handler.currentState = GAMESTATES.mainmenu;
			break;

		case mainmenu:
			if(key == KeyEvent.VK_DOWN){ handler.mainmenu.buttonnext(-1); }
			if(key == KeyEvent.VK_UP){ handler.mainmenu.buttonnext(1); }
			if(key == KeyEvent.VK_ENTER){ handler.mainmenu.enter(); }
			break;
		
		case options:
			handler.currentState = GAMESTATES.mainmenu;
			break;
			
		case play:
			if(key == KeyEvent.VK_A) { handler.play.player.setLeft(true); }
			if(key == KeyEvent.VK_D) { handler.play.player.setRight(true); }
			if(key == KeyEvent.VK_SPACE) { handler.play.player.setJump(true); }
			if(key == KeyEvent.VK_R) { handler.play.blocks.get(MainLoop.random(handler.play.blocks.size()-1, 0)).remove(); }
			break;
		
		default:
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (handler.currentState) {
		case play:
			if(key == KeyEvent.VK_A) { handler.play.player.setLeft(false); }
			if(key == KeyEvent.VK_D) { handler.play.player.setRight(false); }
			break;
		
		default:
			break;
		}
	}
}
