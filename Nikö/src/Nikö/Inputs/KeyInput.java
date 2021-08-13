package Nikö.Inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Nikö.Entity.Components.EHandler;
import Nikö.Entity.Components.Entity;
import Nikö.Entity.Components.ID;

public class KeyInput extends KeyAdapter implements KeyListener{
	private EHandler ehandler;
	public KeyInput(EHandler ehandler){
		this.ehandler = ehandler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < ehandler.entitys.size(); i++){
			Entity tempO = ehandler.entitys.get(i);
			if(tempO.getId() == ID.Player){
				if(key == KeyEvent.VK_D){ tempO.setVelX(8); tempO.setRichtung("Rechts"); }
				if(key == KeyEvent.VK_A){ tempO.setVelX(-8); tempO.setRichtung("Links"); }
				if(key == KeyEvent.VK_W){ tempO.setVelY(-8); tempO.setRichtung("Hoch"); }
				if(key == KeyEvent.VK_S){ tempO.setVelY(8); tempO.setRichtung("Runter"); }
			}
		}

		if(key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		if(key == KeyEvent.VK_F10)
			if(!EHandler.show_hitboxes)
				EHandler.show_hitboxes = true;
			else
				EHandler.show_hitboxes = false;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < ehandler.entitys.size(); i++){
			Entity tempO = ehandler.entitys.get(i);
			if(tempO.getId() == ID.Player){
				if(key == KeyEvent.VK_D){ tempO.setVelX(0); tempO.setRichtung("Stehen"); }
				if(key == KeyEvent.VK_A){ tempO.setVelX(0); tempO.setRichtung("Stehen"); }
				if(key == KeyEvent.VK_S){ tempO.setVelY(0); tempO.setRichtung("Stehen"); }
				if(key == KeyEvent.VK_W){ tempO.setVelY(0); tempO.setRichtung("Stehen"); }
			}
		}
	}
}
