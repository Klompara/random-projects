package Game.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.Handler;
import Game.Input.MouseInput;
import Game.gfx.Textures;
import Game.gfx.VCam;

public class Player {
	public boolean firing;
	private long firingTimer;
	private long firingDelay;
	
	public int x;
	public int y;
	
	public double dx;
	public double dy;
	public int speed;
	
	private double gravity;
	public double jumpstart;
	public boolean falling;
	public boolean jumping;
	
	public int height;
	public int width;
	
	private VCam cam;
	
	
	// constructor
	public Player(VCam cam) {
		this.x = 0;
		this.y = 0;
		this.cam = cam;
		
		height = 48;
		width  = 32;
		
		firing = false;
		firingTimer = System.nanoTime();
		firingDelay = 200;
		
		falling = true;
		jumping = false;
		jumpstart = 12;
		
		gravity = 0.55;
		
		dx = 0;
		dy = 0;
		speed = 4;
	}
	
	
	public void tick() {
		if(falling || jumping){
			dy += gravity;
			
			if(dy > speed*2.5)
				dy = speed*2.5;
		}
		
		// move x
		if(dx > 1){
			for(int i = 0; i < dx; i++){ x += 1; collision(); }
		}else if (dx < 1) { 
			for(int i = (int) dx; i < 0; i++){ x -= 1; collision(); }
		}
		
		// move y
		if(dy > 1){
			for(int i = 0; i < dy; i++){ if(falling || jumping){ y += 1;} collision(); }
		}else if (dy < 1) {
			for(int i = (int) dy; i < 0; i++){ if(falling || jumping){y -= 1;} collision(); }
		}
		
		// firing
		if(firing) {
			long elapsed = (System.nanoTime() - firingTimer) / 1000000;
			if(elapsed > firingDelay) {
				firingTimer = System.nanoTime();
				Handler.bullets.add(new Bullet(x+width/2, y+height/2, MouseInput.x-cam.getX(), MouseInput.y-cam.getY()));
			}
		}
	}
	
	private void collision() {
		for(int i = 0; i < Handler.blocks.size(); i++){
			Block block = Handler.blocks.get(i);
			if(Hitbox_Bottom().intersects(block.Hitbox_Top())){ y-=1; jumping = false; falling = false; dy = 0; } falling = true;
			if(Hitbox_Top().intersects(block.Hitbox_Bottom())){ y+=1; dy = 0; } falling = true;
			if(Hitbox_Left().intersects(block.Hitbox_Right())){ x+=1; }
			if(Hitbox_Right().intersects(block.Hitbox_Left())){ x-=1; }
		}
	}
	
	public void render(Graphics2D g) {
		if(!firing)g.drawImage(Textures.player_default, x, y, width, height, null);
		else g.drawImage(Textures.player_shoot, x, y, width, height, null);
	}
	
	public Rectangle Hitbox_Top(){
		return new Rectangle(x+3, y, width-6, 3);
	}
	public Rectangle Hitbox_Bottom(){
		return new Rectangle(x+3, y+height-3, width-6, 3);
	}
	public Rectangle Hitbox_Left(){
		return new Rectangle(x, y+3, 3, height-6);
	}
	public Rectangle Hitbox_Right(){
		return new Rectangle(x+width-3, y+3, 3, height-6);
	}


	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}
