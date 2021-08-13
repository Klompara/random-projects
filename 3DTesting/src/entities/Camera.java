package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private float distanceFromPlayer = 20;
	private float maxDistanceFP = 8599999;
	private float minDistanceFP = 5;
	private float angleAroundPlayer = 0;
	
	private Vector3f position = new Vector3f(0,2,0);
	private float pitch = 20f;
	private float maxpitch = 75f;
	private float minpitch = 5f;
	private float yaw = 0;
	
	private Player player;
	
	public Camera(Player player) {
		this.player = player;
	}

	public void move() {
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculatePosition(horizontalDistance, verticalDistance);
	}
	
	private void calculatePosition(float horizDistance, float verticDistance) {
		float theta = player.getRotY() + angleAroundPlayer;
		float xOffset = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float zOffset = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		this.position.x = player.getPosition().x - xOffset;
		this.position.y = player.getPosition().y + verticDistance;
		this.position.z = player.getPosition().z - zOffset;
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
	}
	
	private void calculateZoom() {
		float zoomlevel = Mouse.getDWheel() * 0.02f;
		distanceFromPlayer += zoomlevel;
		if(distanceFromPlayer > maxDistanceFP) distanceFromPlayer = maxDistanceFP;
		if(distanceFromPlayer < minDistanceFP) distanceFromPlayer = minDistanceFP;
	}
	
	private void calculatePitch() {
		if(Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch += pitchChange;
			if(pitch > maxpitch) pitch = maxpitch;
			if(pitch < minpitch) pitch = minpitch;
		}
	}
	
	private void calculateAngleAroundPlayer() {
		if(Mouse.isButtonDown(0)) {
			float angleAroundPlayerChange = Mouse.getDX() * 0.3f;
			angleAroundPlayer += angleAroundPlayerChange;
		}
	}
	
	public void invertPitch() {
		this.pitch = -pitch;
	} 
	
	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	public Vector3f getPosition() { return position; } 
	public float getPitch() { return pitch; } 
	public float getYaw() { return yaw; }


}
