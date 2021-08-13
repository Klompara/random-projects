package Game.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;

import Game.MainClass;
import Game.TextureLoader;
import Game.Toolkit.MixerFilter;

public class Player {
	protected double x;
	protected double y;
	protected double vx;
	protected double vy;
	protected boolean isFalling;
	protected Color color;
	
	private Image Texture;
	public Player(double x, double y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		
		MixerFilter myFilter = new MixerFilter(color);
		Image imSrc = TextureLoader.getImage("Player.png");
		Texture = Toolkit.getDefaultToolkit().createImage(new
				FilteredImageSource(imSrc.getSource(), myFilter));
	}
	
	public void tick() {
		x += vx;
		y += vy;
		
		if(isFalling) {
			vy += 0.2d;
		}
		
		if(y > MainClass.HEIGHT-120-TextureLoader.getImage("Player.png").getHeight()) {
			isFalling = false;
			y = MainClass.HEIGHT-120-TextureLoader.getImage("Player.png").getHeight();
		}else if(!isFalling){
			isFalling = true;
		}
	}
	
	public void render(Graphics2D g) {
		g.drawImage(Texture, (int)x, (int)y, null);
	}
}
