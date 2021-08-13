package Game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Textures {
	public static BufferedImage player_default;
	public static BufferedImage player_shoot;
	public static BufferedImage sky;
	public static BufferedImage clouds;
	public static BufferedImage mountains;
	
	public Textures() { }
	
	public void loadTextures() {
		try {
			player_default = ImageIO.read(getClass().getResourceAsStream("/Player_Default.png"));
			player_shoot = ImageIO.read(getClass().getResourceAsStream("/Player_Shoot.png"));
			sky = ImageIO.read(getClass().getResourceAsStream("/background/sky.gif"));
			clouds = ImageIO.read(getClass().getResourceAsStream("/background/clouds.gif"));
			mountains = ImageIO.read(getClass().getResourceAsStream("/background/mountains.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
