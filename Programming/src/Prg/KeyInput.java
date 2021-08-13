package Prg;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Prg.Entity.EHandler;
import Prg.Entity.Entity;
import Prg.Entity.ID;

public class KeyInput extends KeyAdapter implements KeyListener{
	private EHandler ehandler;
	
	public KeyInput(EHandler ehandler){
		this.ehandler = ehandler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		
		for(int i = 0; i < ehandler.entitys.size(); i ++){
			Entity tempO = ehandler.entitys.get(i);
			if(tempO.getId() == ID.Player){
				if(key == KeyEvent.VK_D){ tempO.setVelX(5); }
				if(key == KeyEvent.VK_A){ tempO.setVelX(-5); }
				if(key == KeyEvent.VK_S){ tempO.setVelY(5); }
				if(key == KeyEvent.VK_W){ tempO.setVelY(-5); }
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < ehandler.entitys.size(); i ++){
			Entity tempO = ehandler.entitys.get(i);
			if(tempO.getId() == ID.Player){
				if(key == KeyEvent.VK_D){ tempO.setVelX(0); }
				if(key == KeyEvent.VK_A){ tempO.setVelX(0); }
				if(key == KeyEvent.VK_S){ tempO.setVelY(0); }
				if(key == KeyEvent.VK_W){ tempO.setVelY(0); }
			}
		}
	}
}
