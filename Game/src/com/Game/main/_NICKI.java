package com.Game.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class _NICKI extends GameObject{
	private Handler handler;
	
	private BufferedImage KopfIMG;
	private BufferedImage KopfIMGPat;
	
	private Random r;
	
	public static int Geschw = 4;
	
	private int Spawn;
	private int suck = 10;
	private int timer2 = 50;
	private int timer = 164;
	private int velX, velY;
	
	public _NICKI(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		try{
			KopfIMGPat = ImageIO.read(getClass().getResourceAsStream("/Patrick.png"));
			KopfIMG = ImageIO.read(getClass().getResourceAsStream("/Niki.png"));
		} catch(IOException e){
			e.printStackTrace();
		}
		Player.y_clamp = 150;
		this.handler.clearEnemys();
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 128, 128);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(timer != 0){
			timer--;
			velY = 1;
		}
		if(timer <= 0) {
			timer2--; 
			if(velY != 0){
				velY = 0;
			}
		}
		if(timer2 <= 0){
			r = new Random();
			
			if(velX == 0) velX = 2;
			if(velX < 0) velX -= 0.5f;
			else if(velX > 0)velX += 0.5f;
			Spawn = (r.nextInt(suck));
			if(Spawn == 0){
				handler.addObject(new Bullets((int)x+64, (int)y+100, ID.Bullet, handler));
			}
			if(suck != 1)suck -= 0.000000000000000000000001;
		}
		
		if(x <= 0 || x >= Game.WIDTH - 150) velX *= -1;
		//if(y <= 0 || y >= Game.HEIGHT - 42) velY *= -1;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler, true));
	}
	
	public void render(Graphics g) {
		g.drawImage(KopfIMG, (int)x, (int)y, 128, 128, null);
		g.drawImage(KopfIMGPat, (int)x, (int) y, 128, 128, null);
	}	
	
	public static int getGesch(){
		return Geschw;
	}
	public static void setGesch(int Geschw){
		_NICKI.Geschw = Geschw;
	}
}