package Game.States;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import Game.States.states.Play;

public class StateHandler {
	
	public StateTypes currentState;
	private List<State> states = new ArrayList<State>();
	
	// States
	public Play play;
	
	public StateHandler(StateTypes state) {
		currentState = state;
		
		play = new Play(StateTypes.Play, this);
		
		states.add(play);
	}
	
	public void tick() {
		for(State s : states) {
			if(s.currentState == currentState) {
				s.tick();
				break;
			}
		}
	}
	
	public void render(Graphics2D g) {
		for(State s : states) {
			if(s.currentState == currentState) {
				s.render(g);
				break;
			}
		}
	}
}
