package Game.Input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Game.Entitys.Camera;

public class MouseInput extends MouseAdapter implements MouseMotionListener {
	
	public static int mouseX, mouseY;
	public static boolean isButtonDown;
	private Camera cam;
	
	public MouseInput(Camera cam){ 
		this.cam = cam;
	}
	
	public void mouseMoved(MouseEvent e) {
		setCoords(e);
	}

	public void mouseDragged(MouseEvent e) {
		isButtonDown = false;
		setCoords(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		isButtonDown = false;
		setCoords(e);
	}
	
	public void mousePressed(MouseEvent e) {
		isButtonDown = true;
		setCoords(e);
	}	
	
	private void setCoords(MouseEvent e) {
		mouseX = (int) (e.getX() - cam.getX());
		mouseY = (int) (e.getY() - cam.getY());
//		mouseX = (int) (e.getX());
//		mouseY = (int) (e.getY());
	}
}
