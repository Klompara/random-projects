package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class stateHandler {

	public enum states{
		MAIN, GAME
	}
	
	private stateMain MainState;
	private stateGame GameState;
	
	public states currentState = states.MAIN;
	
	private Game.Button[] buttons = new Game.Button[2];
	private Color[] colors = new Color[] {Color.black, Color.yellow, Color.blue, Color.orange, Color.green, Color.red, new Color(255,0,255), Color.cyan};
	
	public stateHandler(Main main) {
		MainState = new stateMain(this, buttons, colors);
		GameState = new stateGame(colors);
		System.out.println("create handler...");
		main.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent e) {
				for(int i = 0; i < buttons.length; i++) {
					if(currentState == states.MAIN) {
						if(buttons[0].getHitbox().contains(e.getPoint())) { // Play
							currentState = states.GAME;
							System.out.println("PLAY");
						}
						else if(buttons[1].getHitbox().contains(e.getPoint())) { // Credits
							System.out.println("CREDITS");
						}
					}
				}
			}
		});
	}
	
	public void tick() {
		if(currentState == states.MAIN) {
			MainState.tick();
		}
		else if(currentState == states.GAME) {
			GameState.tick();
		}
	}
	
	public void render(Graphics2D g) {
		if(currentState == states.MAIN) {
			MainState.render(g);
		}
		else if(currentState == states.GAME) {
			GameState.render(g);
		}
	}
}
