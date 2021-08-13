package Game.GameStates;

import java.awt.Graphics2D;

import Game.GameStates.States.State_Intro;
import Game.GameStates.States.State_MainMenu;
import Game.GameStates.States.State_Options;
import Game.GameStates.States.State_Play;

public class StateHandler {
	
	public enum GAMESTATES {
		intro(),
		mainmenu(),
		play(),
		options(),
	};
	public GAMESTATES currentState = GAMESTATES.play; // momentan
	
	public State_Intro intro;
	public State_MainMenu mainmenu;
	public State_Play play;
	public State_Options options;
	
	public StateHandler() {
		intro = new State_Intro(this);
		mainmenu = new State_MainMenu(this);
		play = new State_Play();
		options = new State_Options();
	}
	
	public void tick() {
		switch(currentState) {
		case intro:
			intro.tick();
			break;
		case mainmenu:
			mainmenu.tick();
			break;
		case options:
			options.tick();
			break;
		case play:
			play.tick();
			break;
		
		default:
			System.err.println("Nicht identifizierbares GameState!");
			break;
		}
	}
	
	public void render(Graphics2D g) {
		switch(currentState) {
		case intro:
			intro.render(g);
			break;
		case mainmenu:
			mainmenu.render(g);
			break;
		case options:
			options.render(g);
			break;
		case play:
			play.render(g);
			break;
		
		default:
			System.err.println("Nicht identifizierbares GameState!");
			break;
		}
	}
}
