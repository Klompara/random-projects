package Game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Entitys.Block;
import Game.Entitys.Player;
import Game.GameStates.States.State_Play;

public class Worldloader {
	
	private State_Play handler;
	
	public Worldloader(State_Play handler) {
		this.handler = handler;
	}
	
	public void loadworld(int levelnum) {
		BufferedImage level = null;
		try {
			level = ImageIO.read(getClass().getResourceAsStream("/images/levels/level_"+levelnum+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int w = level.getWidth();
		int h = level.getHeight();
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0; yy < h; yy++) {
				int Pixel = level.getRGB(xx, yy);
				int red = (Pixel >> 16) & 0xff;
				int green = (Pixel >> 8) & 0xff;
				int blue = (Pixel) & 0xff;
				
				if(red == 0   && green == 0   && blue == 0  ) handler.blocks.add(new Block(xx*16, yy*32));
				if(red == 0   && green == 255 && blue == 0  ){ handler.player = new Player(xx*16, yy*16, handler); }
			}
		}
	}
}
