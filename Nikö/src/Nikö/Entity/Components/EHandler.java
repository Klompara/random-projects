package Nikö.Entity.Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class EHandler {
	public LinkedList<Entity> entitys = new LinkedList<Entity>();
	public static boolean show_hitboxes = false;
	
	public void tick(){
		for(int i = 0; i < entitys.size(); i++){
			Entity tempO = entitys.get(i);
			tempO.tick();
		}
	}
	public void render(Graphics g){
		for(int i = 0; i < entitys.size(); i++){
			Entity tempO = entitys.get(i);
			tempO.render(g);
			if(show_hitboxes){
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.cyan);
				g2d.draw(tempO.Bounds_Bottom());
				g2d.draw(tempO.Bounds_Left());
				g2d.draw(tempO.Bounds_Right());
				g2d.draw(tempO.Bounds_Top());
			}
		}
	}
}
