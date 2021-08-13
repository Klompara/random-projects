package Game;

import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Mash {
	private Vector3f[] vertices;
	private float red, green, blue;
	
	public Mash(Vector3f[] vertices) {
		this.vertices = vertices;
		Random r = new Random();
		this.red = r.nextFloat();
		this.green = r.nextFloat();
		this.blue = r.nextFloat();
	}
	
	public void render() {
		glColor3f(red, green, blue);
		for(int i = 0; i < vertices.length; i++) {
			Vector3f vec = vertices[i];
			glVertex3f(vec.x, vec.y, vec.z);
		}
	}
}
