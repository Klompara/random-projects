package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

public class Entity {

	private TexturedModel model;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	
	private int textureIndex;
	
	public Entity(TexturedModel model, Vector3f position, float rotX,
			float rotY, float rotZ, float scale) {
		this(model, position, 0, rotX, rotY, rotZ, scale);
	}
	
	public Entity(TexturedModel model, Vector3f position, int textureIndex, float rotX,
			float rotY, float rotZ, float scale) {
		this.textureIndex = textureIndex;
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void increaseRotation(float rx, float ry, float rz) {
		this.rotX += rx;
		this.rotY += ry;
		this.rotZ += rz;
	}
	
	public float getTextureXOffset() {
		int column = textureIndex%model.getTexture().getNumberOfRows();
		return (float)column/(float)model.getTexture().getNumberOfRows();
	}
	
	public float getTextureYOffset() {
		int row = textureIndex%model.getTexture().getNumberOfRows();
		return (float)row/(float)model.getTexture().getNumberOfRows();
	}

	public TexturedModel getModel() { return model; } 
	public Vector3f getPosition() { return position; } 
	public float getRotX() { return rotX; } 
	public float getRotY() { return rotY; } 
	public float getRotZ() { return rotZ; } 
	public float getScale() { return scale; } 
}
