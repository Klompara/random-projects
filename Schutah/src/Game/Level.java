package Game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

public class Level {
	
	public ArrayList<Mash> world;
	
	public Level() {
		world = new ArrayList<Mash>();
		loadlevel(0);
	}
	
	public void loadlevel(int LevelNum) {
		world.clear();
		
		BufferedImage level = ImageLoader.laodImage("map"+LevelNum);
		
		for(int x = 0; x < level.getWidth(); x++) {
			for(int y = 0; y < level.getHeight(); y++) {
				Color c = new Color(level.getRGB(x, y));
				
				if(c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 255) {
					world.add(new Mash(new Vector3f[]{
							new Vector3f(x, 0, y),
							new Vector3f(x, 0, y + 1),
							new Vector3f(x + 1, 0, y + 1),
							new Vector3f(x + 1, 0, y),
					}));
					world.add(new Mash(new Vector3f[]{
							new Vector3f(x, 2, y),
							new Vector3f(x + 1, 2, y),
							new Vector3f(x + 1, 2, y + 1),
							new Vector3f(x, 2, y + 1),
					}));
					checkWalls(x, y, level);
				}
			}
		}
	}

	public void render() {
		for(Mash mash : world) {
			mash.render();
		}
	}
	
	
	private void checkWalls(int x, int y, BufferedImage level) {
		if(checkLeft(x, y, level)) {
			world.add(new Mash(new Vector3f[] {
					new Vector3f(x, 1, y),
					new Vector3f(x, 1, y + 1),
					new Vector3f(x, 0, y + 1),
					new Vector3f(x, 0, y),
			}));
			world.add(new Mash(new Vector3f[] {
					new Vector3f(x, 2, y),
					new Vector3f(x, 2, y + 1),
					new Vector3f(x, 1, y + 1),
					new Vector3f(x, 1, y),
			}));
		}
		
		if(checkTop(x, y, level)) {
			world.add(new Mash(new Vector3f[] {
					new Vector3f(x, 1, y),
					new Vector3f(x, 0, y),
					new Vector3f(x + 1, 0, y),
					new Vector3f(x + 1, 1, y),
			}));
			world.add(new Mash(new Vector3f[] {
					new Vector3f(x, 2, y),
					new Vector3f(x, 1, y),
					new Vector3f(x + 1, 1, y),
					new Vector3f(x + 1, 2, y),
			}));
		}
		
		if(checkRight(x, y, level)) {
			world.add(new Mash(new Vector3f[] {
					new Vector3f(x+1, 1, y),
					new Vector3f(x+1, 0, y),
					new Vector3f(x+1, 0, y + 1),
					new Vector3f(x+1, 1, y + 1),
			}));
			world.add(new Mash(new Vector3f[] {
					new Vector3f(x+1, 2, y),
					new Vector3f(x+1, 1, y),
					new Vector3f(x+1, 1, y + 1),
					new Vector3f(x+1, 2, y + 1),
			}));
		}
		
		if(checkBottom(x, y, level)) {
			world.add(new Mash(new Vector3f[] {
					new Vector3f(x, 1, y+1),
					new Vector3f(x + 1, 1, y+1),
					new Vector3f(x + 1, 0, y+1),
					new Vector3f(x, 0, y+1),
			}));
			world.add(new Mash(new Vector3f[] {
					new Vector3f(x, 2, y+1),
					new Vector3f(x + 1, 2, y+1),
					new Vector3f(x + 1, 1, y+1),
					new Vector3f(x, 1, y+1),
			}));
		}
	}
	
	
	private boolean checkLeft(int x, int y, BufferedImage level) {
		int x2 = x-1;
		if(x2 < 0) return true;
		if(x2 > level.getHeight()) return true;
		Color c = new Color(level.getRGB(x2, y));
		return (c.getRed() == 0 || c.getGreen() == 0 || c.getBlue() == 0);
	}
	private boolean checkTop(int x, int y, BufferedImage level) {
		int y2 = y-1;
		if(y2 < 0) return true;
		if(y2 > level.getHeight()) return true;
		Color c = new Color(level.getRGB(x, y2));
		return (c.getRed() == 0 || c.getGreen() == 0 || c.getBlue() == 0);
	}
	private boolean checkRight(int x, int y, BufferedImage level) {
		int x2 = x+1;
		if(x2 < 0) return true;
		if(x2 > level.getHeight()) return true;
		Color c = new Color(level.getRGB(x2, y));
		return (c.getRed() == 0 || c.getGreen() == 0 || c.getBlue() == 0);
	}
	private boolean checkBottom(int x, int y, BufferedImage level) {
		int y2 = y+1;
		if(y2 < 0) return true;
		if(y2 > level.getHeight()-1) return true;
		Color c = new Color(level.getRGB(x, y2));
		return (c.getRed() == 0 || c.getGreen() == 0 || c.getBlue() == 0);
	}
}
