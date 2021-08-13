package Shooter.Weapons;

import java.util.LinkedList;

import Shooter.Main;
import Shooter.Entitys.Entity;
import Shooter.Entitys.ID;
import Shooter.Entitys.entityHandler;
import Shooter.Inputs.MouseInput;

public class weaponHandler {
	private entityHandler eHandler;
	public LinkedList<String> weapons = new LinkedList<String>();
	public int Slot = 1;
	
	public weaponHandler(entityHandler eHandler) {
		this.eHandler = eHandler;
		
		weapons.add("Revolver");
		weapons.add("MG");
		weapons.add("Shotgun");
		weapons.add("Rifle");
		weapons.add("Bazooka");
		weapons.add("Ray-Gun");
	}
	
	public void tick(){
		if(MouseInput.shoot){
			for(int i = 0; i < eHandler.entitys.size(); i++){
				Entity tempObject = eHandler.entitys.get(i);
				if(tempObject.getID() == ID.Player){
					switch (Slot){
					case 0 : eHandler.entitys.add(new DefaultBullet(tempObject.getX()+16-4, tempObject.getY()+16-4, ID.Revolver, MouseInput.x-8+Main.rand(-50, 50), MouseInput.y-8+Main.rand(-50, 50), eHandler));
						break;
					case 1 :
						eHandler.entitys.add(new DefaultBullet(tempObject.getX()+16-4, tempObject.getY()+16-4, ID.Revolver, MouseInput.x-8+Main.rand(-50, 50), MouseInput.y-8+Main.rand(-50, 50), eHandler));
						break;
					case 2 :
						for(int i2 = 0; i2 < 5; i2++)
							eHandler.entitys.add(new DefaultBullet(tempObject.getX()+16-4, tempObject.getY()+16-4, ID.Revolver, MouseInput.x-8+Main.rand(-120, 120), MouseInput.y-8+Main.rand(-120, 120), eHandler));
						break;
					}
				}
			}
		}
	}
}
