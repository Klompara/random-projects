package Nikö.Entity.Entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Nikö.Entity.Components.EHandler;
import Nikö.Entity.Components.Entity;
import Nikö.Entity.Components.ID;
import Nikö.Entity.Sprite_Animation.Animation;
import Nikö.Entity.Sprite_Animation.Sprite;

public class Player extends Entity{
	private boolean moveing = false;
	private EHandler ehandler;
	
	
	// Images for each animation
	private BufferedImage[] walkingUp = {Sprite.getSprite(0, 0,32, "Player"), Sprite.getSprite(1, 0,32,"Player"), Sprite.getSprite(2, 0,32,"Player")};
	private BufferedImage[] walkingLeft = {Sprite.getSprite(0, 1,32, "Player"), Sprite.getSprite(1, 1,32,"Player"), Sprite.getSprite(2, 1,32,"Player")};
	private BufferedImage[] walkingRight = {Sprite.getSprite(0, 2,32,"Player"), Sprite.getSprite(1, 2,32,"Player"), Sprite.getSprite(2, 2,32,"Player")};
	private BufferedImage[] walkingDown = {Sprite.getSprite(0, 3,32, "Player"), Sprite.getSprite(1, 3,32,"Player"), Sprite.getSprite(2, 3,32,"Player")};
	private BufferedImage[] standing = {Sprite.getSprite(1, 0,32,"Player")};

	// These are animation states
	private Animation walkUp = new Animation(walkingDown, 8);
	private Animation walkDown = new Animation(walkingUp, 8);
	private Animation walkLeft = new Animation(walkingLeft, 8);
	private Animation walkRight = new Animation(walkingRight, 8);
	private Animation stand = new Animation(standing, 8);

	// This is the actual animation
	private Animation animation = stand;
	
	
	public Player(int x, int y, ID id, EHandler ehandler) {
		super(x, y, id);
		this.ehandler = ehandler;
		velX = 0;
		velY = 0;
		animation.start();
	}

	public void tick() {
		animation.update();
		switch_Left();
		switch_Right();
		switch_Hoch();
		switch_Runter(); 
		switch_Standing();
		
		if(velX > 0){
			moveing = true;
			for(int i = velX; i > 0; i--){
				if(moveing)
					x += 1;
				collision();
			}
		}else{
			moveing = true;
			for(int i = velX; i < 0; i++){
				if(moveing)
					x -= 1;
				collision();
			}
		}
		
		if(velY > 0){
			for(int i = velY; i > 0; i--){
				y += 1;
				collision();
			}
		}else{
			for(int i = velY; i < 0; i++){
				y -= 1;
				collision();
			}
		}
	}
	public void collision(){
		for(int i = 0; i < ehandler.entitys.size(); i++){
			Entity tempO = ehandler.entitys.get(i);
			if(tempO.getId() == ID.Block){
				if(tempO.Bounds_Top().intersects(Bounds_Bottom())){
					velY = 0;
					y-=2;
				}
				if(tempO.Bounds_Left().intersects(Bounds_Right())){
					velX = 0;
					x-=2;
				}
				if(tempO.Bounds_Right().intersects(Bounds_Left())){
					velX = 0;
					x+=2;
				}
				if(tempO.Bounds_Bottom().intersects(Bounds_Top())){
					velY = 0;
					y+=2;
				}
				if(tempO.Bounds_Top().intersects(Bounds_Right())){
					velY=0;
					velX=0;
					x-=2;
				}
			}
		}
	}
	private void switch_Left(){
		if(richtung == "Links" && animation != walkLeft){
			animation = walkLeft;
			animation.start();
			System.out.println("wechsel: [" + richtung+"]");
		}
	}
	private void switch_Right(){
		if(richtung == "Rechts" && animation != walkRight){
			animation = walkRight;
			animation.start();
			System.out.println("wechsel: [" + richtung+"]");
		}
	}
	private void switch_Hoch(){
		if(richtung == "Hoch" && animation != walkUp){
			animation = walkUp;
			animation.start();
			System.out.println("wechsel: [" + richtung+"]");
		}
	}
	private void switch_Runter(){
		if(richtung == "Runter" && animation != walkDown){
			animation = walkDown;
			animation.start();
			System.out.println("wechsel: [" + richtung+"]");
		}
	}
	private void switch_Standing(){
		if(richtung == "Stehen" && animation != stand){
			animation = stand;
			animation.start();
			System.out.println("wechsel: [" + richtung+"]");
		}
	}
	
	
	public void render(Graphics g) {
		g.drawImage(animation.getSprite(), x, y, width(), height(), null);
	}
	
	public Rectangle2D Bounds_Top() {
		return new Rectangle(x+3,y,width()-6, 3);
	}

	public Rectangle2D Bounds_Bottom() {
		return new Rectangle(x+3,y+height()-3,width()-6, 3);
	}

	public Rectangle2D Bounds_Right() {
		return new Rectangle(x+width()-3,y+3,3,height()-6);
	}

	public Rectangle2D Bounds_Left() {
		return new Rectangle(x,y+3,3,height()-6);
	}

	public int width() {
		return 48;
	}
	public int height() {
		return 48;
	}
}
