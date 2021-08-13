package Game.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Game.Game;
import Game.Loader;
import Game.GameStates.States.Play.Play;

public abstract class Teacher extends Entity {
	
	private Play play;
	
	private BufferedImage texture;
	
	private int ID;
	private int height, width;
	private double speed;
	private boolean moving;
	
	public Teacher(double x, double y, int ID, Play play) {
		super(x, y);
		ID++;
		this.ID = ID;
		this.play = play;
		moving = true;
		texture = Loader.getImage(ID);
		width = Loader.getImage(ID).getWidth();
		height = Loader.getImage(ID).getHeight();
		if(ID-1 == 1) {
			speed = 1.5;
		}
	} 

	public void tick() {
		if(y != Game.HEIGHT-150)y = Game.HEIGHT-150;
		dx = speed;
		if(moving) {
			if(dx < 0) {
				for(double i = dx; i < 0; i++) {
					if(moving) {
						x--;
						collision();
					}
				}
			}else if(dx > 0) {
				for(double i = dx; i > 0; i--) {
					if(moving) {
						x++;
						collision();
					}
				}
			}
		}
	}
	
	private void collision() {
		for(Teacher t : play.teacherHTL) {
			if(dx > 0) {
				if(BRight().intersects(t.BLeft())) {
					moving = false;
					dx = 0;
				}
			}else if(dx < 0) {
				if(BLeft().intersects(t.BRight())) {
					moving = false;
					dx = 0;
				}
			}
		}
	}

	public void render(Graphics2D g) {
		g.drawImage(texture, (int)x, (int)y, null);
		g.setColor(Color.cyan);
		g.draw(BTop());
		g.draw(BBottom());
		g.draw(BLeft());
		g.draw(BRight());
	}

	protected Rectangle2D BTop() { return new Rectangle((int)x+3, (int)y, width-6, 3); }
	protected Rectangle2D BBottom() { return new Rectangle((int)x+3, (int)y+height-3, width-6, 3); }
	protected Rectangle2D BLeft() { return new Rectangle((int)x, (int)y+3, 3, height-6); }
	protected Rectangle2D BRight() { return new Rectangle((int)x+width-3, (int)y+3, 3, height-6); }

	protected int getID() { return ID; }
	protected void setID(int iD) { ID = iD; }
}
