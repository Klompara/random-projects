package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements KeyListener, Runnable{

	public Main(){
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode() + " - PRESSED");
	}

	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode() + " - RELEASED");
	}

	public void keyTyped(KeyEvent e) {
	
	}

	public void run() {
		
	}
}
