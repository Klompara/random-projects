package Game.gfx.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import Game.Main;
import Game.Main.STATE;

public class MainMenu {
	public int selected = 0;
	private int alpha = 254;
	private LinkedList<String> buttons = new LinkedList<String>();
	private LinkedList<AnimatedBackgroundTiles> background = new LinkedList<AnimatedBackgroundTiles>();
	public MainMenu() {
		buttons.add("Play");
		buttons.add("Options");
		buttons.add("Help");
		buttons.add("Exit");
	}
	
	public void tick() {
		if(alpha > 0) alpha-=2;
		
		if(background.size() < 30 && Main.random(20, 0) == 5){ background.add(new AnimatedBackgroundTiles()); }
		for(int i = 0; i < background.size(); i++){
			AnimatedBackgroundTiles tempO = background.get(i);
			tempO.tick();
		}
	}

	public void render(Graphics g) {	
		for(int i = 0; i < background.size(); i++){
			AnimatedBackgroundTiles tempO = background.get(i);
			tempO.render(g);
		}
		if(selected == -1)selected = buttons.size()-1;
		if(selected == buttons.size()) selected = 0;
		
		g.setFont(new Font("Century Gothic", Font.PLAIN, 72));
		for(int i = 0; i < buttons.size(); i++){
			String s = buttons.get(i);
			int length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
			int height = (int) g.getFontMetrics().getStringBounds(s, g).getHeight();
			g.setColor(Color.WHITE);
			g.drawString(s, Main.WIDTH/2-length/2, 125*i+height);
			if(i == selected){
				for(int i2 = 0; i2 < 5; i2++)
					g.drawLine(Main.WIDTH/2-length/2, 125*i+height+16+i2, Main.WIDTH/2+length/2, 125*i+height+16);
			}
		}
		
		if(alpha > 0){
			g.setColor(new Color(0,0,0,alpha));
			g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		}
	}
	
	public void enter(){
		switch(selected){
			case 0: Main.gameState = STATE.Game;
				break;
			case 1: Main.gameState = STATE.Options;
					System.out.println("Switch");
				break;
			case 2: Main.gameState = STATE.Help;
				break;
			case 3: System.exit(0);
				break;
			default : System.err.println("Button not found!");
		}
	}
	
	public class AnimatedBackgroundTiles{
		private int alpha, x, y, fontsize;
		private boolean grow = true;
		public AnimatedBackgroundTiles(){ 
			alpha = 0;
			x = Main.random(Main.WIDTH, 0);
			y = Main.random(Main.HEIGHT, 0);
			fontsize = Main.random(28, 8);
		}
		
		void tick(){
			if(grow){
				alpha+=5;
				if(alpha == 255)grow = false;
			}else{
				alpha-=5;
				if(alpha == 0){
					x = Main.random(Main.WIDTH, 0);
					y = Main.random(Main.HEIGHT, 0);
					fontsize = Main.random(28, 8);
					grow = true;
				}
			}
		}
		
		void render(Graphics g){
			String s = "Hintergrund :o";
			int width = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
			g.setColor(new Color(255, 255, 255, alpha));
			g.setFont(new Font("Century Gothic", Font.PLAIN, fontsize));
			g.drawString(s, x-width/2, y);
		}
	}
}
