package Shooter.Inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Shooter.Main;
import Shooter.Entitys.Entity;
import Shooter.Entitys.ID;
import Shooter.Entitys.entityHandler;

public class KeyInput extends KeyAdapter{
	public static boolean keydown = false;
	private entityHandler handler;
	
	public KeyInput(entityHandler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		keydown = true;
		int key = e.getKeyCode();
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempObject = handler.entitys.get(i);
			if (tempObject.getID() == ID.Player){
				if(key == KeyEvent.VK_SPACE) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-3);
				if(key == KeyEvent.VK_D) tempObject.setVelX(3);
			}
		}
		
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		if (key == KeyEvent.VK_F9){
			if(Main.hitboxes){
				Main.hitboxes = false;
			}else{
				Main.hitboxes = true;
			}
		}
				
	}
	
	public void keyReleased(KeyEvent e) {
		keydown = false;
		int key = e.getKeyCode();
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempObject = handler.entitys.get(i);
			if (tempObject.getID() == ID.Player){
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
			}
		}
	}
}