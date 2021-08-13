package com.Game.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	
	private Random r;
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		if(!hud.getDeath())scoreKeep++;
		else{
			scoreKeep = 0;
		}
		if(scoreKeep >= 150){
			scoreKeep = 0;
			hud.setlevel(hud.getlevel() + 1);
			
			r = new Random();
			
			if(hud.getlevel() == 2){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.BasicEnemy, handler));
			}
			if(hud.getlevel() == 3){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.BasicEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy, handler));
			}
			if(hud.getlevel() == 4){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
				handler.addObject(new FollowEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FollowEnemy, handler));
			}
			if(hud.getlevel() == 5){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.BasicEnemy, handler));
			}
			if(hud.getlevel() == 6){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
			}
			if(hud.getlevel() == 7){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
			}
			if(hud.getlevel() == 8){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.BasicEnemy, handler));
			}
			if(hud.getlevel() == 9){
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
			}
			if(hud.getlevel() == 10){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
			}
			if(hud.getlevel() == 11){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.BasicEnemy, handler));
			}
			if(hud.getlevel() == 12){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
			}
			if(hud.getlevel() == 13){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
			}
			if(hud.getlevel() == 14){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.BasicEnemy, handler));
			}
			if(hud.getlevel() == 15){
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
			}
			if(hud.getlevel() == 16){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy, handler));
				handler.addObject(new FastEnemy2(r.nextInt(Game.WIDTH-32) ,Game.HEIGHT/2-16, ID.FastEnemy2, handler));
			}
			if(hud.getlevel() == 17){
				HUD.HEALTH = 100;
				handler.addObject(new _NICKI(Game.WIDTH/2-64, -128, ID.Nicki, handler));
			}
		}
		//handler.addObject(new Bullets((int)64, (int)64, ID.Bullet, handler));  // SPERMIEN!!
	}
}
