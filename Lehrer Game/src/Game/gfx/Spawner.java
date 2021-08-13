package Game.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Game.Main;
import Game.Entity.Entity;
import Game.Entity.Handler;
import Game.Entity.Entitys.Lehrer.Gous;
import Game.Entity.Entitys.Lehrer.Haidacha;
import Game.Entity.Entitys.Lehrer.ID;
import Game.Entity.Entitys.Lehrer.Kofla;
import Game.Entity.Entitys.Lehrer.Wurza;

public class Spawner {
	
	private long waveStartTimer = 0;
	private long waveStartTimerDiff = 0;
	private int waveNumber = 3;
	private int waveDelay = 2000;
	private boolean waveStart = true;
	
	private Handler handler;
	public Spawner(Handler handler){
		this.handler = handler;
	}
	
	public void tick(){
		if(waveStartTimer == 0 && EnemysOnScreen() == 0){
			waveNumber++;
			waveStart = false;
			waveStartTimer = System.nanoTime();
		}else{
			waveStartTimerDiff = (System.nanoTime() - waveStartTimer) / 1000000;
			if(waveStartTimerDiff > waveDelay){
				waveStart = true;
				waveStartTimer = 0;
				waveStartTimerDiff = 0;
			}
		}
		
		if(waveStart && EnemysOnScreen() == 0){
			if(waveNumber == 1){
				spawn("Gous", 5);
			}
			if(waveNumber == 2){
				spawn("Gous", 7);
			}
			if(waveNumber == 3){
				spawn("Gous", 5);
				spawn("Wurza", 2);
			}
			if(waveNumber == 4){
				spawn("Gous", 6);
				spawn("Wurza", 2);
				spawn("Haidacha", 1);
			}
		}
	}
	
	public void render(Graphics g){
		if(waveStartTimer != 0){
			g.setFont(new Font("Century Gothic", Font.PLAIN, 32));
			String s = "-  W A V E  " + waveNumber + "  -";
			int length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
			int height = (int) g.getFontMetrics().getStringBounds(s, g).getHeight();
			int alpha = (int) (255 * Math.sin(Math.PI * waveStartTimerDiff / waveDelay));
			if(alpha > 255) alpha = 255;
			if(alpha < 0) alpha = 0;
			g.setColor(new Color(255, 255, 255, alpha));
			g.drawString(s, Main.WIDTH/2 - length/2, Main.HEIGHT/2 - height/2);
		}
	}
	
	private void spawn(String object, int reapeat){
		for(int i = 0; i < reapeat; i++){
			if(object == "Kofla")handler.entitys.add(new Kofla(Main.random(0, Main.WIDTH), Main.random(0, 150), ID.Kofla, handler));
			else if(object == "Haidacha")handler.entitys.add(new Haidacha(Main.random(0, Main.WIDTH), Main.random(0, 150), ID.Haidacha, handler));
			else if(object =="Gous")handler.entitys.add(new Gous(Main.random(0, Main.WIDTH), Main.random(0, 150), ID.Gous, handler));
			else if(object == "Wurza")handler.entitys.add(new Wurza(Main.random(0, Main.WIDTH), Main.random(0, 150), ID.Wurza, handler));
			else System.out.println("Name nicht gefunden!");
		}
	}
	
	private int EnemysOnScreen(){
		int enemysCount = 0;
		for(int i = 0; i < handler.entitys.size(); i++){
			Entity tempO = handler.entitys.get(i);
			if(tempO.getId() == ID.Kofla || tempO.getId() == ID.Willi || tempO.getId() == ID.Gous || tempO.getId() == ID.Haidacha || tempO.getId() == ID.Wurza)
				enemysCount++;
		}
		return enemysCount;
	}
}
