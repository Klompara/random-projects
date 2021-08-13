package Game.gfx.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import Game.Main;
import Game.Main.STATE;

public class Options {
	public int selected = 0;
	private LinkedList<String> buttons = new LinkedList<String>();
	public Options(){
		buttons.add("back");
	}
	
	public void enter(){
		Main.gameState = STATE.MainMenu;
	}
	
	public void render(Graphics g) {
		if(selected == -1)selected = buttons.size()-1;
		if(selected == buttons.size()) selected = 0;
		
		g.setFont(new Font("Century Gothic", Font.PLAIN, 48));
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
	}
}
