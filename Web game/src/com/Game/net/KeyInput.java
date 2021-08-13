package com.Game.net;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(GameObject tempObject : handler.object){
			if(tempObject.getID() == ID.Player){
				if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){tempObject.velX = 5;}
				if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){tempObject.velX = -5;}
			}
		}
		if(key == KeyEvent.VK_ENTER){
			if(Game.gameState == Game.STATE.GameOver || Game.gameState == Game.STATE.Win){
				Game.gameState = Game.STATE.Game;
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					handler.object.remove(tempObject);
				}
				handler.addObject(new Player(Game.WIDTH/2-64/2, Game.HEIGHT-32, ID.Player, handler));
				handler.addObject(new Ball(Game.WIDTH/2-32/2, Game.HEIGHT-128, ID.Ball, handler));
				
				for(int i = 0; i < 13; i++){
					handler.addObject(new Block(64*i+i*8+34, 60, ID.Block, new Color(0, 72, 153)));
				}
				for(int i = 0; i < 12; i++){
					handler.addObject(new Block(64*i+i*8+66, 120, ID.Block, new Color(255, 237, 0)));
				}
				for(int i = 0; i < 13; i++){
					handler.addObject(new Block(64*i+i*8+34, 180, ID.Block, new Color(227, 6, 19)));
				}
				for(int i = 0; i < 12; i++){
					handler.addObject(new Block(64*i+i*8+66, 240, ID.Block, new Color(0, 150, 64)));
				}
				Game.sleeptime = 20;
			}
		}
		if(key == KeyEvent.VK_F9){
			if(!Game.entwickler)Game.entwickler = true;
			else if(Game.entwickler)Game.entwickler = false;
		}
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player){
				if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){tempObject.velX = 0;}
				if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){tempObject.velX = 0;}
			}
		}
	}

}
