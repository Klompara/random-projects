package Game.gfx.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import Game.Main;
import Game.Main.STATE;

public class Help {
	private LinkedList<String> infos = new LinkedList<String>();
	public Help(){ 
		infos.add("Dieses Spiel sollte niemanden beleidigen oder verstören!");
		infos.add("Falls doch tut es mir schrecklich leid!");
		infos.add("Es wurde nur aus Langeweile/Spaß gemacht!");
		infos.add("Ich übernehme keine Haftung für Sachen!");
		infos.add("Dass Herr Haidacher in diesem Spiel mit Drogen");
		infos.add("in Verbindung gebracht wurde, bedeutet nicht, dass");
		infos.add("er welche nimmt! (doch pass auf seine Weed-Wolke auf ^^)");
		infos.add(" ");
		infos.add("Steuerung: ");
		infos.add("Links: Links / A");
		infos.add("Rechts: Rechts / D");
		infos.add("Hoch: Hoch / W");
		infos.add("Runter: Runter / S");
		infos.add("Schießen: Leertaste");
		
	}
	
	public void enter(){
		Main.gameState = STATE.MainMenu;
		System.out.println("Help geladen");
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		for(int i = 0; i < infos.size(); i++){
			String s = infos.get(i);
			int width = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
			int height = (int) g.getFontMetrics().getStringBounds(s, g).getHeight();
			g.drawString(s, Main.WIDTH/2-width/2, height*i+18);
		}
	}
}
