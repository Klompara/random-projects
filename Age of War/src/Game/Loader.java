package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Loader {

	private static List<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public Loader() {}
	
	public void loadImages(List<String> filepaths) {
		for(String s : filepaths) {
			loadImage(s);
		}
	}
	
	public static BufferedImage getImage(int Image) {
		return images.get(Image);
	}
	
	private void loadImage(String filename) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/"+filename+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		images.add(image);
	}
}
