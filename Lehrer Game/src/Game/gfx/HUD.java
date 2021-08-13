package Game.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Game.Main;
import Game.Entity.Entity;
import Game.Entity.Handler;
import Game.Entity.Entitys.Lehrer.ID;

public class HUD {
	private Handler handler;
	
	public int score;
	
	public HUD(Handler handler){
		this.handler = handler;
		score = 0;
	}
	
	public void render(Graphics g){
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempO = handler.entitys.get(i);
			if(tempO.getId() == ID.Player){
				g.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				g.setColor(Color.white);
				String s = "Hp: "+tempO.getHp();
				g.drawString(s, 10, getFontHeight(s, g));
			}
		}
		String s = "Score: "+score;
		g.drawString(s, Main.WIDTH-getFontWidth(s, g)-10, getFontHeight(s, g)+5);
	}
	private int getFontHeight(String s, Graphics g){
		int height = (int) g.getFontMetrics().getStringBounds(s, g).getHeight();
		return height;
	}
	private int getFontWidth(String s, Graphics g) {
		int width = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
		return width;
	}
}
