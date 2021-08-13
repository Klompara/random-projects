package Nikö.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Nikö.Entity.Components.EHandler;
import Nikö.Entity.Components.ID;
import Nikö.Entity.Entitys.Background;
import Nikö.Entity.Entitys.Block;
import Nikö.Entity.Entitys.Player;

public class loader {
	private EHandler ehandler;
	public loader(EHandler ehandler){
		this.ehandler = ehandler;
	}
	
	public BufferedImage loadimage(String path){
		BufferedImage img = null;
		try { img = ImageIO.read(getClass().getResource(path)); } catch (IOException e) { e.printStackTrace(); }
		return img;
	}
	
	public void LoadLevelImage(){
		BufferedImage level = null;
		level = loadimage("/Level.png");
		int w = level.getWidth();
		int h = level.getHeight();
		
		for(int xx = 0; xx < w; xx++){
			for(int yy = 0; yy < h; yy++){
				int Pixel = level.getRGB(xx, yy);
				int red = (Pixel >> 16) & 0xff;
				int green = (Pixel >> 8) & 0xff;
				int blue = (Pixel) & 0xff;
				
				if(red == 0   && green == 0   && blue == 255){ ehandler.entitys.add(new Block 		(xx*48, yy*48, ID.Block, "Water"  		)); }
				if(red == 0   && green == 255 && blue == 0  ){ ehandler.entitys.add(new Background	(xx*48, yy*48, ID.Background, "Grass"  	)); }
				if(red == 255 && green == 0   && blue == 0  ){ ehandler.entitys.add(new Player		(xx*48, yy*48, ID.Player, ehandler		)); }
				
			}
		}
		System.out.println(ehandler.entitys.size());
	}
}
