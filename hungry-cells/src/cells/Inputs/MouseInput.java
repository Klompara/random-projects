package cells.Inputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import cells.gfx.VCam;

public class MouseInput extends MouseAdapter implements MouseMotionListener{
	private int mouseX;
	private int mouseY;
	
	private VCam cam;
	
	public MouseInput(VCam cam) {
		mouseX = 0;
		mouseY = 0;
		
		this.cam = cam;
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX()-cam.getX();
		mouseY = e.getY()-cam.getY();
	}

	public int getMouseX() { return mouseX; }
	public int getMouseY() { return mouseY; }
}
