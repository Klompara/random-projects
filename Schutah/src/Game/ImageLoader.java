package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class ImageLoader {
	
	public static BufferedImage laodImage(String filename) {
		try {
			return ImageIO.read(ImageLoader.class.getResourceAsStream("/images/"+filename+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Texture loadTexture(String filename) {
		try {
			return TextureLoader.getTexture("PNG", ImageLoader.class.getResourceAsStream("/images/"+filename+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
