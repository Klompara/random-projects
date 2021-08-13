package Shooter.GraphicInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Shooter.Weapons.weaponHandler;

public class HUD {
	private weaponHandler whandler;
	public HUD(weaponHandler whandler) {
		this.whandler = whandler;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.setFont(new Font("sansserif", 1, 50));
		g.drawString(whandler.weapons.get(whandler.Slot), 25, 50);
	}
}
