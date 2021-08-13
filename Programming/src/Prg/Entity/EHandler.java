package Prg.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class EHandler {
	public boolean showhitboxes = false;
	public LinkedList<Entity> entitys = new LinkedList<Entity>();
	
	public void tick(){
		for(int i = 0; i < entitys.size(); i++){
			Entity tempO = entitys.get(i);
			tempO.tick();
		}
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i < entitys.size(); i++){
			Entity tempO = entitys.get(i);
			tempO.render(g);
			
			if(showhitboxes){
				g2d.setColor(Color.cyan);
				g2d.draw(tempO.BBottom());
				g2d.draw(tempO.BLeft());
				g2d.draw(tempO.BRight());
				g2d.draw(tempO.BTop());
			}
		}
	}
}
