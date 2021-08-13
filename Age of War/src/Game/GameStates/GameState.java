package Game.GameStates;

import java.awt.Graphics2D;

public abstract class GameState {
	
	private GameStates stateID;
	
	public GameState(GameStates stateID) {
		this.stateID = stateID;
	}
	
	protected GameStates getStateID() { return stateID; } 
	protected void setStateID(GameStates stateID) { this.stateID = stateID; } 

	public abstract void tick();
	public abstract void render(Graphics2D g);
}
