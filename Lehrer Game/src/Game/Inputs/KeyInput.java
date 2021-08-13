package Game.Inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.Main;
import Game.Main.STATE;
import Game.Entity.Entity;
import Game.Entity.Handler;
import Game.Entity.Entitys.Lehrer.ID;
import Game.gfx.GameStates.Help;
import Game.gfx.GameStates.MainMenu;
import Game.gfx.GameStates.Options;

public class KeyInput extends KeyAdapter implements KeyListener{
	public static int SpeedP = 3;
	private Handler handler;
	private MouseInput mouseinput;
	
	private MainMenu menu;
	private Help help;
	private Options options;
	
	public KeyInput(Handler handler, MouseInput mouseinput, MainMenu menu, Help help, Options options){
		this.handler = handler;
		this.mouseinput = mouseinput;
		this.menu = menu;
		this.options = options;
		this.help = help;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(Main.gameState == STATE.Game){
			for(int i = 0; i < handler.entitys.size(); i++){
				Entity tempObject = handler.entitys.get(i);
				if(tempObject.getId() == ID.Player){
					if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){ tempObject.setVelY(SpeedP*-1);; }
					if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){ tempObject.setVelY(SpeedP);; }
					if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){ tempObject.setVelX(SpeedP*-1);; }
					if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){ tempObject.setVelX(SpeedP);; }
				}
			}
			if(key == KeyEvent.VK_F9){ 
				if(!handler.hitboxes)handler.hitboxes = true;
				else handler.hitboxes = false;
			}
			if(key == KeyEvent.VK_ESCAPE) System.exit(0);
			if(key == KeyEvent.VK_SPACE) mouseinput.shooting = true;
		}
		else if(Main.gameState == STATE.Help){ if(key == KeyEvent.VK_ENTER){ help.enter(); } }
		else if(Main.gameState == STATE.Options){
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){ menu.selected--; }
			if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){ menu.selected++; }
			if(key == KeyEvent.VK_ENTER){ options.enter(); }
		}
		else if(Main.gameState == STATE.MainMenu){
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){ menu.selected--; }
			if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){ menu.selected++; }
			if(key == KeyEvent.VK_ENTER){ menu.enter(); }
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(Main.gameState == STATE.Game){
			for(int i = 0; i < handler.entitys.size(); i++){
				Entity tempObject = handler.entitys.get(i);
				if(tempObject.getId() == ID.Player){
					if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){ tempObject.setVelY(0);; }
					if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){ tempObject.setVelY(0);; }
					if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){ tempObject.setVelX(0);; }
					if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){ tempObject.setVelX(0);; }
				}
			}
			if(key == KeyEvent.VK_SPACE) mouseinput.shooting = false;
		}
	}
}
