package Game;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class stateMain {
	
	private String[] text = new String[] {"TETRIS", "PLAY", "CREDITS"};
	private Game.Button buttonStart;
	private Game.Button buttonCredits;
	private Game.Button[] buttons;
	private int themeSpaced = 0;
	private Color[] colors;
	
	public stateMain(stateHandler handler, Button[] buttons, Color[] colors) {
		this.buttons = buttons;
		this.colors = colors;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {	
		if(buttonStart == null) {
			g.setFont(Main.font(35));
			buttonStart = new Game.Button(text[1], Main.font(35),(int)(Main.WIDTH/2-Main.getFWidth(text[1], g)/2), 400);
			buttonCredits = new Game.Button(text[2], Main.font(35), (int)(Main.WIDTH/2-Main.getFWidth(text[2], g)/2), 450);
			buttons[0] = buttonStart;
			buttons[1] = buttonCredits;
		}
		
		//Hintergrund
		GradientPaint redtowhite = new GradientPaint(Main.WIDTH/3, 0, Color.gray, Main.WIDTH/10*6, Main.HEIGHT/10*8, Color.black);
		g.setPaint(redtowhite);
		g.fill(new Rectangle2D.Double(0, 0, Main.WIDTH, Main.HEIGHT));
		
		g.setFont(Main.font(70));
		for(int i = 0; i < text[0].length(); i++) {
			g.setColor(colors[i+1]);
			g.drawString(text[0].substring(i, i+1), Main.WIDTH/2 - Main.getFWidth(text[0], g)/2 + themeSpaced, 140);
			themeSpaced += Main.getFWidth(text[0].substring(i, i+1), g);
		}
		themeSpaced = 0;
		buttonStart.render(g);
		buttonCredits.render(g);
	}
}
