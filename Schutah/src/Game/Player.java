package Game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Player {
	
	private Vector3f pos;
	private Vector2f rotation;
	private float speed;

	public Player(float x, float y, float z) {
		pos = new Vector3f(x, y, z);
		rotation = new Vector2f();
		speed = 20.5f;
	}
	
	public void update(float delta) {
		float dx = Mouse.getX() - Display.getWidth()/2;
		float dy = Mouse.getY() - Display.getHeight()/2;
		
		rotation.y += dx / 5;
		rotation.x -= dy / 5;
		
		if(rotation.x > 90) rotation.x = 90;
		if(rotation.x < -90)   rotation.x = -90;
		
		Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			pos.x += Math.cos(Math.toRadians(rotation.y - 90)) * speed * delta;
			pos.z += Math.sin(Math.toRadians(rotation.y - 90)) * speed * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			pos.x -= Math.cos(Math.toRadians(rotation.y - 90)) * speed * delta;
			pos.z -= Math.sin(Math.toRadians(rotation.y - 90)) * speed * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			pos.x -= Math.cos(Math.toRadians(rotation.y)) * speed * delta;
			pos.z -= Math.sin(Math.toRadians(rotation.y)) * speed * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			pos.x += Math.cos(Math.toRadians(rotation.y)) * speed * delta;
			pos.z += Math.sin(Math.toRadians(rotation.y)) * speed * delta;
		}
	}

	public Vector3f getPos() {
		return pos;
	}

	public Vector2f getRotation() {
		return rotation;
	}
}
