package Game.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import Game.Handler;

public class MouseInput extends MouseMotionAdapter implements MouseListener, MouseMotionListener{
	
	public static int x;
	public static int y;

	public void mouseClicked(MouseEvent e) { }
	
	public void mouseEntered(MouseEvent e) { }
	
	public void mouseExited(MouseEvent e) { }

	public void mousePressed(MouseEvent e) {
		if(x == 0 && y == 0){ x = e.getX(); y = e.getY(); }
		Handler.player.firing = true;
	}
	
	public void mouseReleased(MouseEvent e) {
		Handler.player.firing = false;
	}
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		//System.out.println("x: "+x+", y: "+y);
	}
}