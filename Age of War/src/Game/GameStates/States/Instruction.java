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

public class Instruction extends GameState {

	private List<String> lines = new ArrayList<String>();
	private Rectangle2D ButtonBounds;
	private boolean isHoveringButton;
	
	public Instruction(GameStates stateID) {
		super(stateID);
		lines.add("The goal of the game is to survive and destroy the enemy base.");
		lines.add("");
		lines.add("The game is divided in 5 ages. To move to the next age, you need");
		lines.add("Xp points. To gain these points, you have to kill enemy units.");
		lines.add("You also gain Xp points when one of your units is killed. You can");
		lines.add("also build defences. Finding the balance between defence and");
		lines.add("offence is the key.");
		lines.add("");
		lines.add("You will also be able to use a special attack. This attack will need");
		lines.add("time to be available again after you use it. Each age have its own");
		lines.add("special attack.");
		lines.add("");
		lines.add("You cannot repair your base, but it will gain health points");
		lines.add("everytime you evolve to the next age. Protect your base at all cost!");
	}

	public void tick() {
		if(ButtonBounds != null) {
			if(ButtonBounds.contains(new Point(MouseInput.mouseX, MouseInput.mouseY))) {
				isHoveringButton = true;
				if(MouseInput.isButtonDown) {
					Handler.currentState = GameStates.MainMenu;
				}
			}else{
				isHoveringButton = false;
			}
		}
	}

	public void render(Graphics2D g) {
		g.drawImage(Loader.getImage(0),
				-50 + MouseInput.mouseX/(Game.WIDTH/50),
				-50 + MouseInput.mouseY/(Game.HEIGHT/50),
				Game.WIDTH+50, Game.HEIGHT+50, null);
		
		g.setColor(new Color(220, 220, 220, 180));
		g.fillRoundRect(120, 60, Game.WIDTH-240, Game.HEIGHT-120, 90, 90);
		
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		for(int i = 0; i < lines.size(); i++) {
			String s = lines.get(i);
			g.setColor(Color.gray);
			g.drawString(s, 130, 112 + i*30);
			g.setColor(Color.black);
			g.drawString(s, 130, 110 + i*30);
			if(i == lines.size()-1) {
				if(isHoveringButton) 
					g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
				s = "Click herer to return to the menu";
				g.setColor(Color.gray);
				g.drawString(s, Game.WIDTH/2 - Maths.getFontWidth(s, g)/2, 110 + i*30 + 122);
				g.setColor(Color.black);
				g.drawString(s, Game.WIDTH/2 - Maths.getFontWidth(s, g)/2, 110 + i*30 + 120);
				if(ButtonBounds == null) {
					ButtonBounds = new Rectangle(Game.WIDTH/2 - Maths.getFontWidth(s, g)/2,
							110 + i*30 + 120 - 20, Maths.getFontWidth(s, g), 30);
				}
			}
		}
	}
}
