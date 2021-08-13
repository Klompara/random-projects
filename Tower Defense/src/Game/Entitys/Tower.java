package Game.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Game.Assets;
import Game.States.StateHandler;

public class Tower extends Entity {
	
	private int towerID;
	private int shootRadius = 200;
	private int towerRadius = 20;

	private boolean shoot = false;
	private long shootTimer;
	private long shootDelay = 750;
	
	private double angle;
	
	private Bloon target;
	
	private BufferedImage texture = Assets.getImage("Dart_Monkey.png");
	
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public Tower(StateHandler handler, double x, double y, int towerID) {
		super(handler, x, y);
		this.towerID = towerID;
	}

	public void tick() {
		long elapsed = (System.nanoTime() - shootTimer) / 1000000;
		if(elapsed > shootDelay) {
			shoot = false;
			int highestReachedP = -2;
			double highestDistance = 0;
			for(Bloon b : handler.play.bloons) {
				double diffX = x - b.x;
				double diffY = y - b.y;
				double distance = Math.sqrt(diffX*diffX + diffY*diffY);
				if(distance <= shootRadius) {
					if(b.getReachedWayP() > highestReachedP) {
						highestReachedP = b.getReachedWayP();
						if(b.getDistToNextWayP() > highestDistance) {
							highestDistance = b.getDistToNextWayP();
							target = b;
							shoot = true;
						}
					}
				}
			}
			if(target != null && shoot) {
				projectiles.add(new Projectile(handler, x, y, target.x, target.y));
				shootTimer = System.nanoTime();
			}else if(!shoot) {
				target = null;
			}
		}
		if(target != null)
			angle = Math.atan2(x-target.x, target.y-y);
		
		
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if(p.alive) {
				p.tick();
			}else{
				projectiles.remove(p);
			}
		}
	}

	public void render(Graphics2D g) {
		g.setColor(new Color(128,128,128,128));
		g.fillOval((int)x-shootRadius, (int)y-shootRadius, shootRadius*2, shootRadius*2);
		
		for(Projectile p : projectiles) {
			p.render(g);
		}
		
		double locationX = texture.getWidth() / 2;
		double locationY = texture.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(angle, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		g.drawImage(op.filter(texture, null), (int)x-towerRadius, (int)y-towerRadius, null);
	}

	protected int getTowerID() { return towerID; } 
	protected void setTowerID(int towerID) { this.towerID = towerID; } 

	protected int getShootRadius() { return shootRadius; } 
	protected void setShootRadius(int shootRadius) { this.shootRadius = shootRadius; } 

	protected int getTowerRadius() { return towerRadius; } 
	protected void setTowerRadius(int towerRadius) { this.towerRadius = towerRadius; } 

}
