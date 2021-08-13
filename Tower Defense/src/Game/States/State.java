package Game.States;

import java.awt.Graphics2D;

public abstract class State {
	
	protected StateTypes currentState = null;
	protected StateHandler handler;
	
	public State(StateTypes state, StateHandler handler) {
		currentState = state;
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
}
