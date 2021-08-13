package Shooter.GraphicInterface;

import java.awt.Graphics;

import Shooter.Entitys.entityHandler;
import Shooter.Weapons.weaponHandler;

public class graphicHandler {
	private HUD hud;
	private GUI gui;
	private VCam vcam;
	
	public graphicHandler(entityHandler eHandler, weaponHandler wHandler) {
		this.hud = new HUD(wHandler);
		this.gui = new GUI();
		this.vcam = new VCam(eHandler);
	}
	
	public void tick(){
		hud.tick();
		gui.tick();
		vcam.tick();
	}
	
	public void render(Graphics g){
		hud.render(g);
		gui.render(g);
	}
}
