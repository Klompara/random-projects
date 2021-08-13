package Game.GameStates.States;

import java.awt.Color;
import java.awt.Graphics2D;

import Game.Mainloop;
import Game.GameStates.StateHandler;
import Game.GameStates.StateHandler.GAMESTATES;

public class State_Intro {
	
	/*
	 * TODO:
	 * Logo zeichnen
	 * Texture Loadingscreen
	 */
	
	private StateHandler handler;
	
	private int alpha;
	private int timer;
	
	public State_Intro(StateHandler handler) { 
		this.handler = handler;
		alpha = 255;
		timer = 0;
	}
	
	public void tick() {
		timer++;
		if(timer < 255/2) {
			alpha-=2;
		}else if(timer < 350 && timer > 350-(255/2)) {
			alpha+=2;
		}else if(timer > 350){
			handler.currentState = GAMESTATES.mainmenu;
		}
		if(alpha > 255) alpha = 255;
		if(alpha < 0) alpha = 0;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, Mainloop.WIDTH, Mainloop.HEIGHT);
		g.setColor(new Color(0,0,0,alpha));
		g.fillRect(0, 0, Mainloop.WIDTH, Mainloop.HEIGHT);
	}
}
