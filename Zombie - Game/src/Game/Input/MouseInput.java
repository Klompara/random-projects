package Game.Input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Game.Handler;
import Game.gfx.VCam;

public class MouseInput extends MouseAdapter implements MouseMotionListener, MouseListener{
	
	public static int mouseX;
	public static int mouseY;
	
	public static boolean isMouseDown;
	
	private VCam cam;
	
	public MouseInput(Handler handler, VCam cam) {
		this.cam = cam;
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX()-cam.getX();
		mouseY = e.getY()-cam.getY();
	}
	
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX()-cam.getX();
		mouseY = e.getY()-cam.getY();
		isMouseDown = true;
	}
	
	public void mouseReleased(MouseEvent e) {
		mouseX = e.getX()-cam.getX();
		mouseY = e.getY()-cam.getY();
		isMouseDown = false;
	}
	
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX()-cam.getX();
		mouseY = e.getY()-cam.getY();
		isMouseDown = true;
	}
	
}
