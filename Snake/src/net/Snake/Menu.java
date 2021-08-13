package net.Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import net.Snake.Main.State;

public class Menu extends MouseAdapter{
	private Main main;
	public Menu(Main main){
		this.main = main;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(Main.gameState == State.Menu){
			if(e.getX() > Main.WIDTH/2-64 && e.getX() < Main.WIDTH/2-64+128 && e.getY() > 256 && e.getY() < 256+64){
				Main.gameState = State.Game;
			}
		}
		else if(Main.gameState == State.GameOver){
			if(e.getX() > Main.WIDTH/2-100 && e.getX() < Main.WIDTH/2-100+200 && e.getY() > 256 && e.getY() < 256+64) {
				Main.gameState = State.Game;
				main.Schlange.clear();
				main.Apples.clear();
				Main.Richtung = "Rechts";
				Main.sekunden = 0;
				Main.minuten = 0;
			}
		}
		else if(Main.gameState == State.Pause){
			if(e.getX() > Main.WIDTH/2-80 && e.getX() < Main.WIDTH/2-80+220 && e.getY() > 270 && e.getY() < 270+64) {
				Main.gameState = State.Game;
				Main.sekunden = 0;
				Main.minuten =  0;
			}
			if(e.getX() > Main.WIDTH/2-30 && e.getX() < Main.WIDTH/2-30+120 && e.getY() > 350 && e.getY() < 350+64) {
				System.exit(0);
			}
		}
	}
	public void render(Graphics2D g) {
		if(Main.gameState == State.Menu){
			for(int i = 0; i < 5; i++)
				g.fill3DRect(Main.WIDTH/2-128+i, 128+i, 256-i*2, 90-i*2, true);
			g.setColor(Color.white);
			g.setFont(new Font("sansserif", 1, 72));
			g.drawString("Snake!", Main.WIDTH/2-120, 200);
			g.setFont(new Font("sansserif", 1, 10));
			g.drawString("a Mini-Game by M.K", Main.WIDTH/2+10, 210);
			
			g.setColor(Color.darkGray);
			for(int i = 0; i < 5; i++)
				g.fill3DRect(Main.WIDTH/2-64+i, 256+i, 128-i*2, 64-i*2, true);
			g.setColor(Color.white);
			g.setFont(new Font("sansserif", 1, 50));
			g.drawString("Play", Main.WIDTH/2-50, 302);
		}
		if(Main.gameState == State.GameOver){
			g.setColor(Color.darkGray);
			for(int i = 0; i < 5; i++)
				g.fill3DRect(Main.WIDTH/2-180+i, 128+i, 410-i*2, 90-i*2, true);
			g.setColor(Color.white);
			g.setFont(new Font("sansserif", 1, 72));
			g.drawString("Game Over!", Main.WIDTH/2-175, 200);
			
			g.setColor(Color.darkGray);
			for(int i = 0; i < 5; i++)
				g.fill3DRect(Main.WIDTH/2-100+i, 256+i, 200-i*2, 64-i*2, true);
			g.setColor(Color.white);
			g.setFont(new Font("sansserif", 1, 50));
			g.drawString("Restart", Main.WIDTH/2-90, 302);
		}
		if(Main.gameState == State.Pause){
			g.setColor(Color.darkGray);
			for(int i = 0; i < 5; i++)
				g.fill3DRect(Main.WIDTH/2-120+i, 128+i, 300-i*2, 90-i*2, true);
			g.setColor(Color.white);
			g.setFont(new Font("sansserif", 1, 90));
			g.drawString("Pause", Main.WIDTH/2-100, 205);
			
			g.setColor(Color.darkGray);
			for(int i = 0; i < 5; i++)
				g.fill3DRect(Main.WIDTH/2-80+i, 270+i, 220-i*2, 64-i*2, true);
			g.setColor(Color.white);
			g.setFont(new Font("sansserif", 1, 50));
			g.drawString("Resume", Main.WIDTH/2-70, 320);
			
			g.setColor(Color.darkGray);
			for(int i = 0; i < 5; i++)
				g.fill3DRect(Main.WIDTH/2-30+i, 350+i, 120-i*2, 64-i*2, true);
			g.setColor(Color.white);
			g.setFont(new Font("sansserif", 1, 50));
			g.drawString("Quit", Main.WIDTH/2-20, 400);
		}
	}
}
