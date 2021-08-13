package Game.Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.Handler;

public class KeyInput extends KeyAdapter implements KeyListener{

	private Handler handler;
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A){ handler.getPlayer().setMLeft(true); }
		if(key == KeyEvent.VK_D){ handler.getPlayer().setMRight(true); }
		if(key == KeyEvent.VK_W){ handler.getPlayer().setMUp(true); }
		if(key == KeyEvent.VK_S){ handler.getPlayer().setMDown(true); }
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
		if(key == KeyEvent.VK_1){ handler.selectedWeapon = 1; handler.firingDelay = 250; handler.spray = 2; }
		if(key == KeyEvent.VK_2){ handler.selectedWeapon = 2; handler.firingDelay = 75;  handler.spray = 3; }
		if(key == KeyEvent.VK_3){ handler.selectedWeapon = 3; handler.firingDelay = 500; handler.spray = 0; }
		if(key == KeyEvent.VK_4){ handler.selectedWeapon = 4; handler.firingDelay = 3000; handler.spray = 2; }
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A){ handler.getPlayer().setMLeft(false); }
		if(key == KeyEvent.VK_D){ handler.getPlayer().setMRight(false); }
		if(key == KeyEvent.VK_W){ handler.getPlayer().setMUp(false); }
		if(key == KeyEvent.VK_S){ handler.getPlayer().setMDown(false); }
	}
	
}
