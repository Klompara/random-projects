package Game.GameStates.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import Game.Game;
import Game.Loader;
import Game.GameStates.GameState;
import Game.GameStates.GameStates;
import Game.GameStates.Handler;
import Game.Input.MouseInput;
import Game.toolbox.Maths;

public class Loading extends GameState {
	
	private boolean isButtonHovering;
	private boolean loading = true;
	private Loader l;
	private Rectangle2D buttonBounds;

	public Loading(GameStates stateID) {
		super(stateID);
	}

	public void tick() {
		if(l == null && Game.getCurrentTime() > 500) {
			l = new Loader();
			List<String> files = new ArrayList<String>();
			files.add("sky");
			files.add("hills");
			files.add("Teacher/Gander");
			l.loadImages(files);
			loading = false;
		}
		if(buttonBounds != null) {
			if(buttonBounds.contains(new Point(MouseInput.mouseX, MouseInput.mouseY))) {
				isButtonHovering = true;
				if(MouseInput.isButtonDown) {
					Handler.currentState = GameStates.MainMenu;
					MouseInput.isButtonDown = false;
				}
			}else{
				isButtonHovering = false;
			}
		}
	}

	public void render(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.white);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 55));
		if(loading) {
			String s = "Loading";
			g.drawString(s, Game.WIDTH/2 - Maths.getFontWidth(s, g)/2, Game.HEIGHT/2 - Maths.getFontHeight(s, g)/2);
			if(buttonBounds == null) {
				buttonBounds = new Rectangle(Game.WIDTH/2 - Maths.getFontWidth("Play", g)/2,
						Game.HEIGHT/2 - Maths.getFontHeight(s, g)/2-40, Maths.getFontWidth("Play", g), 50);
			}
		}else{
			if(isButtonHovering) {
				g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 65));
			}
			String s = "Play";
			g.drawString(s, Game.WIDTH/2 - Maths.getFontWidth(s, g)/2, Game.HEIGHT/2 - Maths.getFontHeight(s, g)/2);
		}
	}
}
