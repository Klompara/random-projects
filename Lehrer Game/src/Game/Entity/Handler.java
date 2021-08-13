package Game.Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import Game.Entity.Entitys.Lehrer.ID;
import Game.Entity.Entitys.Lehrer.Lehrer;

public class Handler {
	public boolean hitboxes = false;
	public static LinkedList<Lehrer> lehrer = new LinkedList<Lehrer>();
	public int DMG = 30;
	private BufferedImage textureTrail;
	public Handler(){
		try { textureTrail = ImageIO.read(getClass().getResourceAsStream("/Weed.png"));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void tick(){
		for(int i = 0; i < lehrer.size(); i++){
			Lehrer tempObject = lehrer.get(i);
			Lehrer.tick();
		}
	}
	public void render(Graphics g){
		for(int i = 0; i < entitys.size(); i++){
			Entity tempO = entitys.get(i);
			if(tempO.getId() == ID.Trail || tempO.getId() == ID.Explosion) tempO.render(g);
		}
		for(int i = 0; i < entitys.size(); i++){
			Entity tempObject = entitys.get(i);
			if(tempObject.getId() != ID.Trail && tempObject.getId() != ID.Explosion && tempObject.getId() != ID.Player)tempObject.render(g);
			if(hitboxes){
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.cyan);
				g2d.draw(tempObject.BBottom());
				g2d.draw(tempObject.BTop());
				g2d.draw(tempObject.BLeft());
				g2d.draw(tempObject.BRight());
				g2d.draw(tempObject.BInner());
				g.setColor(Color.white);
				g.setFont(new Font("sansserif",  1, 15));
				g.drawString("HP: "+tempObject.hp, tempObject.x, tempObject.y-10);
			}
		}
		// Für high sein
		for(int i = 0; i < entitys.size(); i++){
			Entity tempO = entitys.get(i);
			if(tempO.getId() == ID.Player){
				tempO.render(g);
			}
		}
	}
	
	public BufferedImage texturTrail(){
		return textureTrail;
	}
}