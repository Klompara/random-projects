package com.Game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	public static Color farbe = Color.green;
	public static int HEALTH = 100;
	
	public static boolean Death = false;
	public static int score = 0;
	public static int level = 1;
	
	private int greencolor = 255;
	private int redcolor = 0;
	public static int fps;
	
	
	public void tick(){
		//HEALTH++;
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
		greencolor = Game.clamp(greencolor, 0, 255);
		greencolor = (int) (HEALTH*2.55);
		redcolor = Game.clamp(redcolor, 0, 255);
		redcolor = 255 - greencolor;
		
		
		if(HEALTH == 0){
			Death = true;
		}
		if(!Death)score++;
	}
	
	public void render(Graphics g){
		g.setColor(new Color(230,230,230));
		g.fillRect(15, 15, 400, 25);
		g.setColor(new Color(redcolor, greencolor, 0));
		g.fillRect(15, 15, HEALTH * 4, 25);
		g.setColor(Color.white);
		g.draw3DRect(15, 15, 400, 25, true);
		g.draw3DRect(14, 14, 402, 27, true);
		
		Font font = new Font("serif", 1, 28);
		g.setFont(font);
		g.setColor(Color.gray);
		g.drawString("Health", 187, 40);
		g.setColor(Color.black);
		g.drawString("Health", 185, 38);
		
		font = new Font("serif", 1, 20);
		g.setFont(font);
		g.setColor(Color.gray);
		g.drawString("Score: " + score, 17, 62);
		g.drawString("Level: " + level, 17, 82);
		g.drawString("FPS: " + fps, Game.WIDTH - 102, 27);
		g.setColor(Color.cyan);
		g.drawString("Score: " + score, 15, 60);
		g.drawString("Level: " + level, 15, 80);
		g.drawString("FPS: " + fps, Game.WIDTH - 100, 25);
		
		
		if(HUD.HEALTH <= 0){
			Font DEATHFONT = new Font("serif", 1, 100);
			g.setFont(DEATHFONT);
			g.setColor(Color.red);
			g.drawString("Death", Game.WIDTH/2-150, Game.HEIGHT/2);
			g.setColor(Color.green);
			DEATHFONT = new Font("serif", 1, 33);
			g.setFont(DEATHFONT);
			g.drawString("Score: " + score, Game.WIDTH/2 - 100, Game.HEIGHT/2 + 50);
			g.drawString("Level: " + level, Game.WIDTH/2 - 80, Game.HEIGHT/2 + 100);
			g.setColor(Color.cyan);
			g.drawString("hit space", Game.WIDTH/2 - 90, Game.HEIGHT/2 + 150);
		}
	}
	
	public void setscore(int score){
		HUD.score = score;
	}
	
	public int getscore(){
		return score;
	}
	
	public void setlevel(int level){
		HUD.level = level;
	}
	
	public int getlevel(){
		return level;
	}
	
	public boolean getDeath(){
		return Death;
	}
	
	public void setDeath(boolean Death){
		HUD.Death = Death;
	}
}
