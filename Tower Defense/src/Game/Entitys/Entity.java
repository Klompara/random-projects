package Game.Entitys;

import java.awt.Graphics2D;

import Game.States.StateHandler;

public abstract class Entity {
	protected double x, y;
	protected StateHandler handler;
	
	public Entity(StateHandler handler, double x, double y) {
		this.x = x;
		this.y = y;
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
}