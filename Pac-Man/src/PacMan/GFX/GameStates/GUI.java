package PacMan.GFX.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import PacMan.Handler;
import PacMan.PacMan;

public class GUI {
	private Handler handler;
	
	public GUI(Handler handler) {
		this.handler = handler;
	}
	
	
	public void tick() {
		
	}
	
	
	public void render(Graphics2D g) {
		// Draw score
		g.setColor(Color.white);
		g.setFont(new Font("sansserif", 1, 27));
		g.drawString("Score: ", 10, PacMan.HEIGHT-100);
		g.setColor(Color.yellow);
		g.drawString(""+handler.tempscore, (int) (g.getFontMetrics().getStringBounds("Score: ", g).getWidth()+10), PacMan.HEIGHT-100);
	}
}
