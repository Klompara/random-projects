package TestGame.Inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import TestGame.Main.Handler;

public class KeyInput extends KeyAdapter implements KeyListener {
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D) {
			handler.spieler.setDx(2);
		}else if(key == KeyEvent.VK_A) {
			handler.spieler.setDx(-2);
		}else if(key == KeyEvent.VK_S) {
			handler.spieler.setDy(2);
		}else if(key == KeyEvent.VK_W) {
			handler.spieler.setDy(-2);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D) {
			handler.spieler.setDx(0);
		}else if(key == KeyEvent.VK_A) {
			handler.spieler.setDx(0);
		}else if(key == KeyEvent.VK_S) {
			handler.spieler.setDy(0);
		}else if(key == KeyEvent.VK_W) {
			handler.spieler.setDy(0);
		}
	}
}
