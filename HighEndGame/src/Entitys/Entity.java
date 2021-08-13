package Entitys;

public abstract class Entity {
	
	protected double x, y, dx, dy;
	
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render();
}
