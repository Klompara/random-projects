package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class TextureLoader {
	private static List<BufferedImage> images = new ArrayList<BufferedImage>();
	private static List<String> imageNames = new ArrayList<String>();
	
	public TextureLoader() {}
	
	public static void loadTextures(List<String> files) {
		System.out.println("Texturen laden...");
		for(String s : files) {
			try {
				images.add(ImageIO.read(new File("res/textures/"+s)));
				imageNames.add(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Texturen geladen!");
	}
	
	public static BufferedImage getImage(String ID) {
		System.out.println("Lade Textur " + ID + " ...");
		for(int i = 0; i < imageNames.size(); i++) {
			String s = imageNames.get(i);
			if(s.equalsIgnoreCase(ID)) {
				return images.get(i);
			}
		}
		System.err.println("Kann Texturnamen nich erkennen!: " + ID);
		return null;
	}
}
