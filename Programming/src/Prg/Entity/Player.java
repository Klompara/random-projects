package Prg.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Player extends Entity{
	private EHandler ehandler;
	
	public Player(int x, int y, ID id, EHandler ehandler) {
		super(x, y, id);
		this.ehandler = ehandler;
		velX = 0;
		velY = 0;
	}

	public void tick() {
		if(velX < 0){
			for(int i = (int) velX; i < 0; i++){
				x += velX;
				collision();
			}
		}else{
			for(int i = (int) velX; i > 0; i--){
				x += velX;
				collision();
			}
		}
		
		if(velY < 0){
			for(int i = (int) velY; i < 0; i++){
				y += velY;
				collision();
			}
		}else{
			for(int i = (int) velY; i > 0; i--){
				y += velY;
				collision();
			}
		}
	}
	
	private void collision() {
		for(int i = 0; i < ehandler.entitys.size(); i++){
			Entity tempO = ehandler.entitys.get(i);
			if(tempO.getId() == ID.Block){
				if(BBottom().intersects(tempO.BTop())){
					y = tempO.getY()-33;
					velY = 0;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.darkGray);
		for(int i = 0; i < 3; i++)
			g.drawRect(x+i, y+i, 32-i*2, 32-i*2);
	}

	public Rectangle2D BLeft() {
		return new Rectangle(x,y+2,2,28);
	}
	public Rectangle2D BRight() {
		return new Rectangle(x+30,y+2,2,28);
	}
	public Rectangle2D BBottom() {
		return new Rectangle(x+2,y+30,28,2);
	}
	public Rectangle2D BTop() {
		return new Rectangle(x+2,y,28,2);
	}
}
