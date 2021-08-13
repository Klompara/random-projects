package entities;

import java.util.List;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

public class Lamp extends Entity {

	private Light light;
	
	public Lamp(TexturedModel model, Vector3f position, float rotX, float rotY,
			float rotZ, float scale, List<Light> lights, Vector3f colour) {
		super(model, position, rotX, rotY, rotZ, scale);
		light = new Light(new Vector3f(position.x, position.y+14.7f, position.z), colour, new Vector3f(1, 0.01f, 0.002f));
		lights.add(light);
	}

}
