package Shooter.Entitys;

import java.awt.Graphics;
import java.util.LinkedList;

public class entityHandler {
	public LinkedList<Entity> entitys = new LinkedList<Entity>();
	public void tick() {
		for(int i = 0; i < entitys.size(); i++) {
			Entity tempObject = entitys.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < entitys.size(); i++) {
			Entity tempObject = entitys.get(i);
			tempObject.render(g);
		}
	}
}
