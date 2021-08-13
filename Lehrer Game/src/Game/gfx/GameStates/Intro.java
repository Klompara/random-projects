package Game.gfx.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Main;
import Game.Main.STATE;

public class Intro {
	private BufferedImage introIMG;
	private float alpha = 1;
	private int tick;
	public Intro(){
		try { introIMG = ImageIO.read(getClass().getResourceAsStream("/Intro.png"));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void tick(){
		tick++;
		if(tick < 200 && alpha > 0.05)alpha-=0.01;
		else if(tick > 225 && alpha < 1) alpha+=0.01;
		if(tick > 350)Main.gameState = STATE.MainMenu;
	}
	
	public void render(Graphics g){
		g.drawImage(introIMG, 0, 0, Main.WIDTH, Main.HEIGHT, null);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(Main.makeTransparent(alpha));
		g.setColor(Color.black);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g2d.setComposite(Main.makeTransparent(1));
	}
}
