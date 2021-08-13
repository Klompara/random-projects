package Game.GameStates;

import Game.GameStates.States.Intro;
import Game.GameStates.States.Play;

public class GameState {
	
	private Intro intro;
	
	private Play play;
	
	public static enum States {
		Intro(),
		Menu(),
		Options(),
		Play(),
	};
	
	public static States CURRENT_STATE = States.Intro;
	
	public GameState() { 
		intro = new Intro();
		play = new Play();
	}
	
	public void update() {
		switch(CURRENT_STATE) {
		
		case Intro:
			intro.update();
			break;
			
		case Menu:
			break;
			
		case Options:
			break;
			
		case Play:
			play.update();
			break;
			
		}
	}
}
