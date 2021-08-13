package Game.Inputs;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Game.Main;
import Game.Entity.Entity;
import Game.Entity.Handler;
import Game.Entity.Entitys.Bullet;
import Game.Entity.Entitys.Lehrer.ID;

public class MouseInput extends MouseAdapter implements MouseMotionListener{
	public int timer;
	public boolean shooting = false;
	private Handler handler;
	private int mouseX, mouseY;
	public MouseInput(Handler handler){
		this.handler = handler;
	}
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	public void tick(){
		timer+=Main.shootSpeed;
		if(shooting){
			for(int i = 0; i < handler.entitys.size(); i++){
				Entity tempObject = handler.entitys.get(i);
				if(tempObject.getId() == ID.Player){
					if(!new Rectangle(tempObject.getX(),tempObject.getY(),32,32).intersects(new Rectangle(mouseX,mouseY,1,1)) && timer > 20){
						handler.entitys.add(new Bullet(tempObject.getX()+16-4, tempObject.getY()+16-4, ID.Bullet, mouseX, mouseY, handler));
						timer = 0;
					}
				}
			}	
		}
	}
}
