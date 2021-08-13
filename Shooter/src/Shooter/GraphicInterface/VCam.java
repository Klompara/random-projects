package Shooter.GraphicInterface;

import Shooter.Main;
import Shooter.Entitys.Entity;
import Shooter.Entitys.ID;
import Shooter.Entitys.entityHandler;

public class VCam {
	private entityHandler handler;
	public VCam(entityHandler handler){
		this.handler = handler;
	}
	
	public void tick() {
		String richtung = null;
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempObject = handler.entitys.get(i);
			if(tempObject.getID() == ID.Player){
				if(tempObject.getX()-32 > Main.WIDTH/2){
					richtung = "rechts";
				}
				if(tempObject.getX()-32 < Main.WIDTH/4){
					richtung = "links";
				}
			}
			if(richtung == "rechts")
				tempObject.setX((int) (tempObject.getX()-9));
			if(richtung == "links")
				tempObject.setX((int) (tempObject.getX()+9));
			
		}
	}
}
