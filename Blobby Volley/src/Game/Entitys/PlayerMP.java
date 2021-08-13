package Game.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;

import Game.MainClass;
import Game.TextureLoader;
import Game.Toolkit.MixerFilter;

public class PlayerMP extends Player {

	private Image Texture;
	
	public PlayerMP(double x, double y, Color color) {
		super(x, y, color);
		
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
