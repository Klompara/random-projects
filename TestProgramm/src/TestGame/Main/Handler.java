package TestGame.Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import TestGame.Entitys.Gegner;
import TestGame.Entitys.Kugel;
import TestGame.Entitys.Player;
import TestGame.Inputs.MouseInput;

public class Handler {
	
	public Player spieler;
	public LinkedList<Gegner> gegner;
	public LinkedList<Kugel> kugeln;
	
	public Handler() {
		spieler = new Player(50, 50);
		gegner = new LinkedList<Gegner>();
		kugeln = new LinkedList<Kugel>();
		
		for(int i = 0; i < 3; i++) {
			gegner.add(new Gegner(this, Game.random(Game.WIDTH-32, 0), Game.random(Game.HEIGHT-32, 0)));
		}
	}
	
	public void tick() {
		spieler.tick();
		for(int i = 0; i < gegner.size(); i++) {
			gegner.get(i).tick();
		}
		
		for(int i = 0; i < kugeln.size(); i++) {
			Kugel k = kugeln.get(i);
			k.tick();
		}
	}
	
	public void render(Graphics2D g) {
		spieler.render(g);
		
		for(int i = 0; i < gegner.size(); i++) {
			Gegner geg = gegner.get(i);
			
			geg.render(g);
			g.setColor(Color.black);
			g.draw(geg.BBottom());
			g.draw(geg.BTop());
			g.draw(geg.BLeft());
			g.draw(geg.BRight());
		}
		
		for(int i = 0; i < kugeln.size(); i++) {
			Kugel k = kugeln.get(i);
			k.render(g);
		}
		
		g.setColor(Color.white);
		g.drawLine(MouseInput.firsttempx, MouseInput.firsttempy, MouseInput.lasttempx, MouseInput.lasttempy);
	}
}
