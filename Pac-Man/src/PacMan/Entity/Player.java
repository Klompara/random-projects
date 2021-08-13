package PacMan.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import PacMan.Handler;
import PacMan.Animations.Animation;
import PacMan.Animations.Sprite;

public class Player {

	private BufferedImage[] walkingRight = {Sprite.getSprite(0, 0,32, "Player"), Sprite.getSprite(1, 0,32,"Player"), Sprite.getSprite(2, 0,32,"Player"), Sprite.getSprite(3, 0, 32, "Player")};
	private BufferedImage[] walkingLeft = {Sprite.getSprite(0, 1,32, "Player"), Sprite.getSprite(1, 1,32,"Player"), Sprite.getSprite(2, 1,32,"Player"), Sprite.getSprite(3, 1, 32, "Player")};
	private BufferedImage[] walkingUp = {Sprite.getSprite(0, 2,32, "Player"), Sprite.getSprite(1, 2,32,"Player"), Sprite.getSprite(2, 2,32,"Player"), Sprite.getSprite(3, 2, 32, "Player")};
	private BufferedImage[] walkingDown = {Sprite.getSprite(0, 3,32, "Player"), Sprite.getSprite(1, 3,32,"Player"), Sprite.getSprite(2, 3,32,"Player"), Sprite.getSprite(3, 3, 32, "Player")};

	private Animation walkUp = new Animation(walkingUp, 3);
	private Animation walkDown = new Animation(walkingDown, 3);
	private Animation walkLeft = new Animation(walkingLeft, 3);
	private Animation walkRight = new Animation(walkingRight, 3);

	private Animation animation = walkLeft;
	
	public boolean[] keydown = new boolean[4];
	public boolean[] richtung = new boolean[4];
	private boolean[] reserve = new boolean[4];
	
	public int x;
	public int y;
	
	private int velX;
	private int velY;
	
	private int movetimer[] = new int[4];
	
	private int width;
	private int height;
	
	private int speed;
	
	private Handler handler;
	
	
	public Player(int x, int y, Handler handler) {
		this.x = x;
		this.y = y;
		
		this.handler = handler;
		
		velX = 0;
		velY = 0;
		
		speed = 4; // immer durch 32 dividierbar sein
		
		width = 32;
		height = 32;
		
		for(int i = 0; i <  4; i++) {
			keydown[i] = false;
			richtung[i] = false;
			reserve[i] = false;
			movetimer[i] = 0;
		}
		
		animation.start();
	}
	
	public void tick() {
		animation.update();
		
		if(richtung[0] || richtung[1] || richtung[2] || richtung[3]) {
			if(keydown[0]){
				reserve[0] = true;
				reserve[1] = false;
				reserve[2] = false;
				reserve[3] = false;
			}else if(keydown[1]){
				reserve[0] = false;
				reserve[1] = true;
				reserve[2] = false;
				reserve[3] = false;
			}else if(keydown[2]){
				reserve[0] = false;
				reserve[1] = false;
				reserve[2] = true;
				reserve[3] = false;
			}else if(keydown[3]){
				reserve[0] = false;
				reserve[1] = false;
				reserve[2] = false;
				reserve[3] = true;
			}
		} else {
			// move left
			if(keydown[0] && !checkleft() && !keydown[1] && !keydown[2] && !keydown[3]){
				richtung[0] = true;
				velX = -speed;
			}
			
			// move up
			if(keydown[1] && !checktop() && !keydown[0] && !keydown[2] && !keydown[3]){
				richtung[1] = true;
				velY = -speed;
			}
			
			// move right
			if(keydown[2] && !checkright() && !keydown[1] && !keydown[0] && !keydown[3]){
				richtung[2] = true;
				velX = speed;
			}
			
			// move down
			if(keydown[3] && !checkbottom() && !keydown[1] && !keydown[2] && !keydown[0]){
				richtung[3] = true;
				velY = speed;
			}
		}

		
		//blockabfrage: links
		if(movetimer[0] >= 32){
			movetimer[0] = 0;
			if(reserve[1] && !checktop()) {
				richtung[1] = true;
				velY = -speed;
				richtung[0] = false;
				velX = 0;
				movetimer[1] = 0;
				reserve[1] = false;
			}else if(reserve[3] && !checkbottom()) {
				richtung[3] = true;
				velY = speed;
				richtung[0] = false;
				velX = 0;
				movetimer[3] = 0;
				reserve[3] = false;
			} else if(checkleft()) {
				richtung[0] = false;
				velX = 0;
			} else if(reserve[2]){
				richtung[0] = false;
				velX = 0;
				richtung[2] = true;
				velX = speed;
				movetimer[2] = 0;
				reserve[2] = false;
			}
		}
		//blockabfrage: hoch
		if(movetimer[1] >= 32){
			movetimer[1] = 0;
			if(reserve[0] && !checkleft()) {
				richtung[0] = true;
				velX = -speed;
				richtung[1] = false;
				velY = 0;
				movetimer[0] = 0;
				reserve[0] = false;
			}else if(reserve[2] && !checkright()) {
				richtung[2] = true;
				velX = speed;
				richtung[1] = false;
				velY = 0;
				movetimer[2] = 0;
				reserve[2] = false;
			} else if(checktop()) {
				richtung[1] = false;
				velY = 0;
			} else if(reserve[3]) {
				richtung[1] = false;
				velY = 0;
				richtung[3] = true;
				velY = speed;
				movetimer[3] = 0;
				reserve[3] = false;
			}
		}
		//blockabfrage: rechts
		if(movetimer[2] >= 32){
			movetimer[2] = 0;
			if(reserve[1] && !checktop()) {
				richtung[1] = true;
				velY = -speed;
				richtung[2] = false;
				velX = 0;
				movetimer[1] = 0;
				reserve[1] = false;
			}else if(reserve[3] && !checkbottom()) {
				richtung[3] = true;
				velY = speed;
				richtung[2] = false;
				velX = 0;
				movetimer[3] = 0;
				reserve[3] = false;
			} else if(checkright()) {
				richtung[2] = false;
				velX = 0;
			} else if(reserve[0]) {
				richtung[2] = false;
				velX = 0;
				richtung[0] = true;
				velX = -speed;
				movetimer[0] = 0;
				reserve[0] = false;
			}
		}
		//blockabfrage: runter
		if(movetimer[3] >= 32){
			movetimer[3] = 0;
			if(reserve[0] && !checkleft()) {
				richtung[0] = true;
				velX = -speed;
				richtung[3] = false;
				velY = 0;
				movetimer[0] = 0;
				reserve[0] = false;
			}else if(reserve[2] && !checkright()) {
				richtung[2] = true;
				velX = speed;
				richtung[3] = false;
				velY = 0;
				movetimer[2] = 0;
				reserve[2] = false;
			} else if(checkbottom()) {
				richtung[3] = false;
				velY = 0;
			} else if(reserve[1]) {
				richtung[3] = false;
				velY = 0;
				richtung[1] = true;
				velY = -speed;
				movetimer[1] = 0;
				reserve[1] = false;
			}
		}

		if(velX == -speed){ movetimer[0]+=speed; animation = walkLeft; animation.start();}
		if(velY == -speed){ movetimer[1]+=speed; animation = walkUp;   animation.start();}
		if(velX == speed){ movetimer[2]+=speed;  animation = walkRight;animation.start();}
		if(velY == speed){ movetimer[3]+=speed;  animation = walkDown; animation.start();}
		if(velX == 0 && velY == 0){ animation.stop(); }
		
		x += velX;
		y += velY;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(animation.getSprite(), x, y, 32, 32, null);
	}
	
	
	// hitboxen für collisionsabfrage
	private boolean checkleft() {
		boolean intersect = false;
		for(int i = 0; i < handler.blocks.size(); i++) {
			Block block = handler.blocks.get(i);
			if(BLeft().intersects(block.BRight())){ intersect = true; }
		}
		return intersect;
	}
	private boolean checkright() {
		boolean intersect = false;
		for(int i = 0; i < handler.blocks.size(); i++) {
			Block block = handler.blocks.get(i);
			if(BRight().intersects(block.BLeft())){ intersect = true; }
		}
		return intersect;
	}
	private boolean checktop() {
		boolean intersect = false;
		for(int i = 0; i < handler.blocks.size(); i++) {
			Block block = handler.blocks.get(i);
			if(BTop().intersects(block.BBottom())){ intersect = true; }
		}
		return intersect;
	}
	private boolean checkbottom() {
		boolean intersect = false;
		for(int i = 0; i < handler.blocks.size(); i++) {
			Block block = handler.blocks.get(i);
			if(BBottom().intersects(block.BTop())){ intersect = true; }
		}
		return intersect;
	}
	
	public Rectangle2D Bounds() { return new Rectangle(x, y, width, height);       }
	public Rectangle2D BLeft()  { return new Rectangle(x-3, y+3, 3, height-6);     }
	public Rectangle2D BTop()   { return new Rectangle(x+3, y-3, width-6, 3);      }
	public Rectangle2D BRight() { return new Rectangle(x+width, y+3, 3, height-6); }
	public Rectangle2D BBottom(){ return new Rectangle(x+3, y+height, width-6, 3); }
}
