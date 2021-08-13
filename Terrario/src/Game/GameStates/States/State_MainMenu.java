package Game.GameStates.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Game.GameStates.StateHandler;
import Game.GameStates.StateHandler.GAMESTATES;
import Game.Main.MainLoop;

public class State_MainMenu {
	/*
	 * TODO:
	 * Überschrift
	 */
	private ArrayList<String> buttons;
	private int selectedB = 2;
	private StateHandler handler; 
	
	public State_MainMenu(StateHandler handler) {
		this.handler = handler;
		buttons = new ArrayList<String>();
		buttons.add("Exit");
		buttons.add("Options");
		buttons.add("Play");
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		int h = MainLoop.HEIGHT;
		g.setFont(new Font("sansserif", 0, 45));
		for(int i = 0; i < buttons.size(); i++) {
			String s = buttons.get(i);
			if(i == selectedB){
				g.setColor(Color.green);
			}else {
				g.setColor(Color.white);
			}
			g.drawString(s, 10, h-55-i*43);
		}
	}
	
	public void buttonnext(int key){ 
		selectedB += key;
		if(selectedB < 0) { selectedB = buttons.size()-1; }
		if(selectedB > buttons.size()-1) { selectedB = 0; }
	}
	public void enter() {
		if(selectedB == 0)MainLoop.running = false;
		if(selectedB == 1)handler.currentState = GAMESTATES.options;
		if(selectedB == 2)handler.currentState = GAMESTATES.play;
	}
}
