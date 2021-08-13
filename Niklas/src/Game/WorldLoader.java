package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Entity.Block;

public class WorldLoader {
	// Constructor
	public WorldLoader() { }
	
	public void loadworld() {
		BufferedImage level = null;
		try { level = ImageIO.read(getClass().getResourceAsStream("/Level.png"));
		} catch (IOException e) { e.printStackTrace(); }
		
		int w = level.getWidth();
		int h = level.getHeight();
		for(int xx = 0; xx < w; xx++){
			for(int yy = 0; yy < h; yy++){
				int Pixel = level.getRGB(xx, yy);
				int red = (Pixel >> 16) & 0xff;
				int green = (Pixel >> 8) & 0xff;
				int blue = (Pixel) & 0xff;
				
				if(red == 0   && green == 0   && blue == 0  ) Handler.blocks.add(new Block(xx*48, yy*48));
				if(red == 0   && green == 255 && blue == 0  ){ Handler.player.setX(xx*48); Handler.player.setY(yy*48); }
			}
		}
	}
}
