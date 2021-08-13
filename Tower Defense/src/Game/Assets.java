package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Assets {
	private static List<BufferedImage> images = new ArrayList<BufferedImage>();
	private static List<String> imageNames = new ArrayList<String>();
	
	public Assets() {}
	
	public static void loadTextures(List<String> files) {
		for(String s : files) {
			try {
				images.add(ImageIO.read(new File("res/textures/"+s)));
				imageNames.add(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static BufferedImage getImage(String ID) {
		for(int i = 0; i < imageNames.size(); i++) {
			String s = imageNames.get(i);
			if(s.equalsIgnoreCase(ID)) {
				return images.get(i);
			}
		}
		System.err.println("Kann texturnamen nich erkennen!: " + ID);
		return null;
	}
}
