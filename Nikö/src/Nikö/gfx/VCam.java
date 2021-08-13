package Nikö.gfx;

import Nikö.Main;
import Nikö.Entity.Components.EHandler;
import Nikö.Entity.Components.Entity;
import Nikö.Entity.Components.ID;

public class VCam {
	private boolean[] richtung = new boolean[4];
	private EHandler handler;
	public VCam(EHandler handler){
		this.handler = handler;
		richtung[0] = false; // Links
		richtung[1] = false; // Rechts
		richtung[2] = false; // Hoch
		richtung[3] = false; // Runter
	}
	
	public void tick() {
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempO = handler.entitys.get(i);
			if(tempO.getId() == ID.Player){
				if(tempO.getX()+48/2 < 48)				{ richtung[0] = true; }else{ richtung[0] = false; }
				if(tempO.getX()+48/2 > Main.WIDTH-48)	{ richtung[1] = true; }else{ richtung[1] = false; }
				if(tempO.getY()+48/2 < 48)				{ richtung[2] = true; }else{ richtung[2] = false; }
				if(tempO.getY()+48 > Main.HEIGHT-48)	{ richtung[3] = true; }else{ richtung[3] = false; }
			}
			for(int i2 = 0; i2 < 8; i2++){
				if(richtung[0]){ tempO.setX(tempO.getX()+1); }
				if(richtung[1]){ tempO.setX(tempO.getX()-1); }
				if(richtung[2]){ tempO.setY(tempO.getY()+1); }
				if(richtung[3]){ tempO.setY(tempO.getY()-1); }
				tempO.collision();
			}
		}
	}
}
