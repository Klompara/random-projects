package entities;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;
import terrain.Terrain;

public class Player extends Entity {
	
	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	private static final float GRAVITY = -40;
	private static final float JUMP_POWER = 15;
	private float TERRAIN_HEIGHT;
	
	private boolean isInAir = false;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;

	public Player(TexturedModel model, Vector3f position, float rotX,
			float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}

	public void move(List<Terrain> terrains) {
		checkInput();
		increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(getRotY())));
		
		Terrain currentTerrain = null;
		for(int i = 0; i < terrains.size(); i++) {
			Terrain t = terrains.get(i);
			if(getPosition().x > t.getX() && getPosition().x < t.getX()+t.getSize() && getPosition().z > t.getZ() && getPosition().z < t.getZ()+t.getSize()) {
				currentTerrain = t;
				break;
			}
		}
		
		if(currentTerrain != null) {
			if(currentTerrain.getHeightsOfTerrain(getPosition().x+dx, getPosition().z+dz)-getPosition().y < 1.5f)
				increasePosition(dx, 0, dz);
			
			upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
			increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
			TERRAIN_HEIGHT = currentTerrain.getHeightsOfTerrain(getPosition().x, getPosition().z);
			if(getPosition().getY() < TERRAIN_HEIGHT) {
				upwardsSpeed = 0;
				isInAir = false;
				getPosition().y = TERRAIN_HEIGHT;
			}else if(getPosition().getY() > TERRAIN_HEIGHT) {
				isInAir = true;
			}
		}
	}
	
	private void checkInput() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			currentSpeed = RUN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			currentSpeed = -RUN_SPEED;
		}else{
			currentSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			currentTurnSpeed = TURN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			currentTurnSpeed = -TURN_SPEED;
		}else{
			currentTurnSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			currentSpeed *= 5;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
		}
	}
	
	private void jump() {
		if(!isInAir) {
			this.upwardsSpeed = JUMP_POWER;
			isInAir = true;
		}
	}

	public float getTERRAIN_HEIGHT() {
		return TERRAIN_HEIGHT;
	}
}
