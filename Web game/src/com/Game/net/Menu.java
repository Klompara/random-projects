package com.Game.net;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{
	private Handler handler;
	public Menu(Handler handler){
		this.handler = handler;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(Game.gameState == Game.STATE.Menu){
			if(e.getX() >= 452 && e.getX() <= 602 && e.getY() >= 250 && e.getY() <= 320){
				handler.addObject(new Player(Game.WIDTH/2-64/2, Game.HEIGHT-32, ID.Player, handler));
				handler.addObject(new Ball(Game.WIDTH/2-32/2, Game.HEIGHT-128, ID.Ball, handler));

				for(int i = 0; i < 13; i++){
					handler.addObject(new Block(64*i+i*8+34, 60, ID.Block, new Color(0, 72, 153)));
				}
				for(int i = 0; i < 12; i++){
					handler.addObject(new Block(64*i+i*8+66, 120, ID.Block, new Color(255, 237, 0)));
				}
				for(int i = 0; i < 13; i++){
					handler.addObject(new Block(64*i+i*8+34, 180, ID.Block, new Color(227, 6, 19)));
				}
				for(int i = 0; i < 12; i++){
					handler.addObject(new Block(64*i+i*8+66, 240, ID.Block, new Color(0, 150, 64)));
				}
				Game.sleeptime = 20;
				Game.gameState = Game.STATE.Game;
			}
		}
		if(Game.gameState == Game.STATE.Menu){
			if(e.getX() >= 452 && e.getX() <= 452+150 && e.getY() >= 350 && e.getY() <= 350+70){
				Game.gameState = Game.STATE.Help;
			}
		}
		if(Game.gameState == Game.STATE.Help){
			if(e.getX() >= Game.WIDTH/2-120+30 && e.getX() <= Game.WIDTH/2-120+30+200 && e.getY() >= 635 && e.getY() <= 635+50){
				Game.gameState = Game.STATE.Menu;
			}
		}
	}
	
	public void render(Graphics g){
		if(Game.gameState == Game.STATE.Menu){
			g.setFont(new Font("sansserif", 1, 72));
			g.drawString("Breakout!", Game.WIDTH/2-150, 128);
			g.setColor(Color.gray);
			for(int i = 0; i < 10; i++){
				g.draw3DRect(512-166+i, 60+i, 360-i*2, 87-i*2, true);
			}
			g.setColor(Color.black);
			g.setFont(new Font("sansserif", 1, 48));
			g.drawString("Play !", Game.WIDTH/2-50, 300);
			g.drawString("Help", Game.WIDTH/2-35, 400);
			g.setColor(Color.gray);
			for(int i = 0; i < 6; i++){
				g.draw3DRect(452+i, 250+i, 150-i*2, 70-i*2, true);
			}
			for(int i = 0; i < 6; i++){
				g.draw3DRect(452+i, 350+i, 150-i*2, 70-i*2, true);
			}
			
			g.setColor(Color.black);
			g.setFont(new Font("sansserif", 1, 10));
			g.drawString("a Mini-Game by M.K.", Game.WIDTH/2+64, 137);
		}
		if(Game.gameState == Game.STATE.Help){
			g.setColor(Color.black);
			g.setFont(new Font("sansserif", 1, 72));
			g.drawString("Help", Game.WIDTH/2-100, 160);
			g.setFont(new Font("sansserif", 1, 30));
			g.drawString("Controls", Game.WIDTH/2-120, 270);
			g.drawString("Objects", Game.WIDTH/2-120, 420);
			g.setFont(new Font("sansserif", 1, 20));
			g.drawString("Right:  D    OR   ->", Game.WIDTH/2-120, 320);
			g.drawString("Left :   A    OR   <-", Game.WIDTH/2-120, 350);
			
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2-120, 430, 32, 32);
			g.setColor(Color.black);
			for(int a = 0; a < 3; a++)
				g.drawRect(Game.WIDTH/2-120+a, 430+a, 32-(a*2), 32-(a*2));
			g.setFont(new Font("sansserif", 1, 25));
			g.drawString("B", Game.WIDTH/2-120+7, 430+25);
			g.drawString("become bigger!", Game.WIDTH/2-120+50, 430+25);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2-120, 470, 32, 32);
			g.setColor(Color.black);
			for(int a = 0; a < 3; a++)
				g.drawRect(Game.WIDTH/2-120+a, 470+a, 32-(a*2), 32-(a*2));
			g.setFont(new Font("sansserif", 1, 25));
			g.drawString("S", Game.WIDTH/2-120+7, 470+25);
			g.drawString("become smaller!", Game.WIDTH/2-120+50, 470+25);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2-120, 510, 32, 32);
			g.setColor(Color.black);
			for(int a = 0; a < 3; a++)
				g.drawRect(Game.WIDTH/2-120+a, 510+a, 32-(a*2), 32-(a*2));
			g.setFont(new Font("sansserif", 1, 25));
			g.drawString("+", Game.WIDTH/2-120+7, 510+25);
			g.drawString("get an extra ball!", Game.WIDTH/2-120+50, 510+25);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2-120, 550, 32, 32);
			g.setColor(Color.black);
			for(int a = 0; a < 3; a++)
				g.drawRect(Game.WIDTH/2-120+a, 550+a, 32-(a*2), 32-(a*2));
			g.setFont(new Font("sansserif", 1, 25));
			g.drawString("F", Game.WIDTH/2-120+7, 550+25);
			g.drawString("your balls will burn! x3", Game.WIDTH/2-120+50, 550+25);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2-120, 590, 32, 32);
			g.setColor(Color.black);
			for(int a = 0; a < 3; a++)
				g.drawRect(Game.WIDTH/2-120+a, 590+a, 32-(a*2), 32-(a*2));
			g.setFont(new Font("sansserif", 1, 25));
			g.drawString("I", Game.WIDTH/2-120+14, 590+25);
			g.drawString("get a string between player and balls", Game.WIDTH/2-120+50, 590+25);
			
			g.drawString("back to menu", Game.WIDTH/2-120+50, 670);
			g.setColor(Color.GRAY);
			for(int i = 0; i < 3; i++){
				g.draw3DRect(Game.WIDTH/2-120+30+i, 635+i, 200-i*2, 50-i*2, true);
			}
		}
	}
}
