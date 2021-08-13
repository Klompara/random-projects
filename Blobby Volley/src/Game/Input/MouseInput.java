package Game.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener{
	
	public static int mouseX, mouseY;
	public static boolean isMousePressed;
	
	public MouseInput() {}

	public void mouseDragged(MouseEvent e) { 
		mouseX = e.getX();
		mouseY = e.getY();
	}
	public void mouseMoved(MouseEvent e) { 
		mouseX = e.getX();
		mouseY = e.getY();
	}
	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { 
		isMousePressed = true;
	}
	public void mouseReleased(MouseEvent e) { 
		isMousePressed = false;
	}
}
