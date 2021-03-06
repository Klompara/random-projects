package app;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public Main(String[] args) {
		int steps = 5;
		BufferedImage img = null;
		args = new String[2];
		args[0] = "C:\\Users\\Michael\\Desktop\\Untitled.png";
		args[1] = "C:\\Users\\Michael\\Desktop\\Untitled1.png";
		try {
			img = ImageIO.read(new File(args[0]));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				int clr = img.getRGB(x, y);
				int red = (clr & 0x00ff0000) >> 16;
				int green = (clr & 0x0000ff00) >> 8;
				int blue = clr & 0x000000ff;
				//red = steppingValues(red, steps);
				//green = steppingValues(green, steps);
				//blue = steppingValues(blue, steps);
				//System.out.println("R: " + red + ", G: " + green +", B: " + blue);
				img.setRGB(x, y, grayScale(red, green, blue).getRGB());
			}
		}
		
		try {
			ImageIO.write(img, "PNG", new File(args[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Main(args);
	}

	public int steppingValues(double value, int steps) {
		value /= 255d;
		double foo = Math.floor(value * steps);
		value = foo / steps;
		value *= 255;
		if(value > 255)
			value = 255;
		if(value < 0)
			value = 0;
		return (int) value;
	}
	
	public Color grayScale(int r, int g, int b) {
		int mittelwert = (r+g+b)/3;
		return new Color(mittelwert, mittelwert, mittelwert);
	}
	
}
