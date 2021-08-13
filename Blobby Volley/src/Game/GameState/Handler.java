package Game.GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Game.MainClass;
import Game.GameState.States.MainMenu;
import Game.GameState.States.Play;
import Game.Input.KeyInput;

public class Handler {
	
	private List<GameState> states = new ArrayList<GameState>();
	private GameStates currentState = GameStates.MainMenu;
	
	public Handler() {
		states.add(new Play(GameStates.Play, this));
		states.add(new MainMenu(GameStates.MainMenu, this));
	}
	
	public void tick() {
		for(int i = 0; i < states.size(); i++) {
			GameState s = states.get(i);
			if(s.getStateID() == currentState) s.tick();
		}
		
		if(KeyInput.isKeyDown) {
			if(KeyInput.currentKeyDown == KeyEvent.VK_ESCAPE) MainClass.running = false;
		}
	}
	
	public void render(Graphics2D g) {
		for(int i = 0; i < states.size(); i++) {
			GameState s = states.get(i);
			if(s.getStateID() == currentState) s.render(g);
		}
	}

	public List<GameState> getStates() { return states; } 
	public void setStates(List<GameState> states) { this.states = states; } 

	public GameStates getCurrentState() { return currentState; } 
	public void setCurrentState(GameStates currentState) { this.currentState = currentState; } 
}
