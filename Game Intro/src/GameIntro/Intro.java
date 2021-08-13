package GameIntro;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameIntro.Main.STATE;

public class Intro{
	private BufferedImage IntroIMG;
	private float alpha = 1;
	private boolean blur = false;
	public Intro() {
		try { IntroIMG = ImageIO.read(getClass().getResourceAsStream("/intro_logo.png"));
		} catch (IOException e) { e.printStackTrace(); }
	}

	public void tick() {
		if(!blur){
			if(alpha > 0)  alpha -= 0.005; 
			if(alpha <= 0.005) blur = true;
		}else{
			if(alpha < 1) alpha += 0.005;
			if(alpha >= 1) Main.gameState = STATE.Game;
		}
	}

	public void render(Graphics g) {
		g.drawImage(IntroIMG, 0, 100, (int) (IntroIMG.getWidth()*0.5), (int) (IntroIMG.getHeight()*0.5), null);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g.setColor(Color.black);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		g2d.dispose();
	}
}
