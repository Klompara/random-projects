package Schoner;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	public void keyPressed(KeyEvent e) {
		//if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}
}
