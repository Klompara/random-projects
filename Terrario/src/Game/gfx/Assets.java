package Game.gfx;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {

	public static BufferedImage[] texture_1; // dirt
	public static BufferedImage[] texture_2; // stone
	private BufferedImage dirt_dest;
	private BufferedImage stone_dest;
	
	public Assets() { }
	
	public void loadRes() throws IOException {
		dirt_dest = ImageIO.read(getClass().getResourceAsStream("/images/Tiles_0.png"));
		stone_dest = ImageIO.read(getClass().getResourceAsStream("/images/Tiles_1.png"));
		
		texture_1 = new BufferedImage[(dirt_dest.getHeight()/18) * (dirt_dest.getWidth()/18)];
		texture_2 = new BufferedImage[(stone_dest.getHeight()/18) * (stone_dest.getWidth()/18)];
		
		for(int i = 0; i < dirt_dest.getHeight()/18; i++) {
			for(int j = 0; j < dirt_dest.getWidth()/18; j++) {
				texture_1[(i*dirt_dest.getWidth()/18)+j] = cropimage(dirt_dest, new Rectangle(j*18, i*18, 16, 16));
			}
		}
		
		for(int i = 0; i < stone_dest.getHeight()/18; i++) {
			for(int j = 0; j < stone_dest.getWidth()/18; j++) {
				texture_2[(i*stone_dest.getWidth()/18)+j] = cropimage(stone_dest, new Rectangle(j*18, i*18, 16, 16));
			}
		}

	}
	
	private BufferedImage cropimage(BufferedImage src, Rectangle rekt) {
		BufferedImage dest = src.getSubimage(rekt.x, rekt.y, rekt.width, rekt.height);
		return dest;
	}
}
