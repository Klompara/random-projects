package guis;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {

	private int texture;
	private Vector2f position;
	private Vector2f scale;
	
	public GuiTexture(int texture, Vector2f position, Vector2f scale) {
		this.texture = texture;
		this.position = position;
		this.scale = scale;
	}

	protected int getTexture() { return texture; } 
	protected Vector2f getPosition() { return position; } 
	protected Vector2f getScale() { return scale; } 
}
