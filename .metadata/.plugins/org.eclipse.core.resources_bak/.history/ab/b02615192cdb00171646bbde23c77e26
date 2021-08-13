package Game.Toolkit;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class MixerFilter extends RGBImageFilter {
	float ratioR, ratioG, ratioB;

	public MixerFilter(Color color) {
		ratioR = color.getRed() / 255f;
		ratioG = color.getGreen() / 255f;
		ratioB = color.getBlue() / 255f;
	}

	public int filterRGB(int x, int y, int rgb) {
		int b = rgb & 0xff,
			g = (rgb >>= 8) & 0xff,
			r = (rgb >>= 8) & 0xff;
		r = (int) Math.round(r * ratioR);
		g = (int) Math.round(g * ratioG);
		b = (int) Math.round(b * ratioB);
		if (r < 0) r = 0; else if (r > 255) r = 255;
		if (g < 0) g = 0; else if (g > 255) g = 255;
		if (b < 0) b = 0; else if (b > 255) b = 255;
		return (rgb & 0xff000000) + ((((r << 8) + g) << 8) + b);
	}
}