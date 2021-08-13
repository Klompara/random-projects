package quadtree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Quad {
	private int x, y, size;
	private BufferedImage map;
	public Quad[] childs;
	private boolean hasWhite = false, hasGreen = false;
	
	public Quad(int x, int y, int size, BufferedImage map) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.map = map;
		update();
	}
	
	public void update() {
		for(int xx = x; xx < size+x; xx++) {
			for(int yy = y; yy < size+y; yy++) {
				if(xx < 1024 && yy < 768) {
					int Pixel = map.getRGB(xx, yy);
					int red = (Pixel >> 16) & 0xff;
					int green = (Pixel >> 8) & 0xff;
					int blue = (Pixel) & 0xff;
					
					if(red == 0 && green == 255 && blue == 0) {
						hasGreen = true;
					}else{
						hasWhite = true;
					}
					if(hasWhite && hasGreen) {
						childs = new Quad[4];
						if(size > Main.quality) {
							childs[0] = new Quad(x, y, size/2, map);
							childs[1] = new Quad(x+size/2, y, size/2, map);
							childs[2] = new Quad(x+size/2, y+size/2, size/2, map);
							childs[3] = new Quad(x, y+size/2, size/2, map);
						}else{
							hasGreen = false;
							hasWhite = true;
						}
						xx = size+x;
						yy = size+y;
					}
				}
			}
		}
	}
	
	public void tick(ArrayList<Bomb> bombs, Car car) {
		if(childs == null) {
			if(hasGreen) {
				for(int i = 0; i < bombs.size(); i++) {
					Bomb b = bombs.get(i);
					if(b != null) {
						if(getRect().intersects(b.getRect())) {
							b.setExplode(true);
						}	
					}
				}
				
				if(car != null) {
					while(getRect().contains(new Point((int)car.getX1(), (int)car.getY1()))) {
						car.setDy1(0);
						car.setY1(car.getY1()-1);
					}
					while(getRect().contains(new Point((int)car.getX2(), (int)car.getY2()))) {
						car.setDy2(0);
						car.setY2(car.getY2()-1);
					}
				}

			}
		}else{
			for(int i = 0; i < childs.length; i++) {
				Quad q = childs[i];
				if(q != null)
					q.tick(bombs, car);
			}
		}
	}
	
	public void render(Graphics2D g) {
		if(childs != null) {
			for(int i = 0; i < childs.length; i++) {
				if(childs[i] != null) {
					childs[i].render(g);
				}
			}
		}
		if(hasGreen && !hasWhite) {
			g.setColor(Color.green);
			g.fillRect(x, y, size, size);
		}else if(!hasGreen && hasWhite) {
			g.setColor(Color.blue.brighter());
			g.fillRect(x, y, size, size);
		}
			
		if(Main.drawRig) {
			g.setColor(Color.black);
			g.drawRect(x, y, size, size);
		}
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, size, size);
	}
	
	public boolean isGreen() { return hasGreen; }
	public boolean isWhite() { return hasWhite; }
}
