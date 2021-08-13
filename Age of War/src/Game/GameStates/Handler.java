package Game.GameStates;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import Game.Entitys.Camera;
import Game.GameStates.States.Instruction;
import Game.GameStates.States.Loading;
import Game.GameStates.States.MainMenu;
import Game.GameStates.States.Play.Play;

public class Handler {
	
	private List<GameState> states = new ArrayList<GameState>();
	public static GameStates currentState = GameStates.Loading;
	
	public Handler(Camera cam) {
		states.add(new Loading(GameStates.Loading));
		states.add(new MainMenu(GameStates.MainMenu));
		states.add(new Play(GameStates.Play, cam));
		states.add(new Instruction(GameStates.Instruction));
	}
	
	public void tick() {
		for(GameState s : states) {
			if(s.getStateID() == currentState) { s.tick(); }
		}
	}
	
	public void render(Graphics2D g) {
		for(GameState s : states) {
			if(s.getStateID() == currentState) { s.render(g); break; }
		}
	}
}
