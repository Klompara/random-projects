package Game.GameState;

import java.awt.Graphics2D;

public abstract class GameState {
	protected GameStates stateID;
	protected Handler handler;
	
	public GameState(GameStates stateID, Handler handler) {
		this.stateID = stateID;
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);

	protected GameStates getStateID() { return stateID; }
}
