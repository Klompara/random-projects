package Nikö.Entity.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Nikö.Entity.Components.Entity;
import Nikö.Entity.Components.ID;
import Nikö.Entity.Sprite_Animation.Animation;
import Nikö.Entity.Sprite_Animation.Sprite;

public class Block extends Entity{
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private BufferedImage[] WaterSprite = {Sprite.getSprite(0, 0,  16, "Water"), Sprite.getSprite(0, 1,  16,"Water"),
										   Sprite.getSprite(0, 2,  16, "Water"), Sprite.getSprite(0, 3,  16,"Water"),
										   Sprite.getSprite(0, 4,  16, "Water"), Sprite.getSprite(0, 5,  16,"Water"),
										   Sprite.getSprite(0, 6,  16, "Water"), Sprite.getSprite(0, 7,  16,"Water"),
										   Sprite.getSprite(0, 8,  16, "Water"), Sprite.getSprite(0, 9,  16,"Water"),
										   Sprite.getSprite(0, 10, 16, "Water"), Sprite.getSprite(0, 11, 16,"Water"),
										   Sprite.getSprite(0, 12, 16, "Water"), Sprite.getSprite(0, 13, 16,"Water"),
										   Sprite.getSprite(0, 14, 16, "Water"), Sprite.getSprite(0, 15, 16,"Water"),};
	private Animation WaterAnimation = new Animation(WaterSprite, 2);
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//private BufferedImage[] GrassSprite = {Sprite.getSprite(0, 0, 16, "Grass")};
	//private Animation GrassAnimation = new Animation(GrassSprite, 10000);
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Animation animation;
	
	public Block(int x, int y, ID id, String texture) {
		super(x, y, id);
		switch(texture){
			case "Water":
				animation = WaterAnimation;
				animation.start();
				break;
			default:
				animation = new Animation(new BufferedImage[]{Sprite.getSprite(0, 0, 16, texture)}, 1000000);
		}
	}

	public void tick() {
		animation.update();
	}

	public void render(Graphics g) {
		if(animation == null){
			g.setColor(Color.white);
			g.drawRect(x, y, width(), height());
		}else
			g.drawImage(animation.getSprite(), x, y, width(), height(), null);
	}

	public Rectangle2D Bounds_Top() {
		return new Rectangle(x+3,y,width()-6, 3);
	}
	public Rectangle2D Bounds_Bottom() {
		return new Rectangle(x+3,y+height()-3,width()-6, 3);
	}
	public Rectangle2D Bounds_Right() {
		return new Rectangle(x+width()-3,y+3,3,height()-6);
	}
	public Rectangle2D Bounds_Left() {
		return new Rectangle(x,y+3,3,height()-6);
	}
	public int width() { return 48; }
	public int height() { return 48; }

	public void collision() {
		
	}
}
