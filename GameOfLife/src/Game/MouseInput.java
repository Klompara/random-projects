package Game;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class MouseInput extends MouseAdapter implements MouseMotionListener {
	
	private int MouseX, MouseY;
	private ArrayList<Cell> cells;
	
	public MouseInput(ArrayList<Cell> cells) {
		this.cells = cells;
	}
	
	public void mouseMoved(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
	}
	
	public void mouseDragged(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
		
		for(int i = 0; i < cells.size(); i++) {
			Cell c = cells.get(i);
			Point p = new Point(MouseX, MouseY);
			if(c.bounds().contains(p)) {
				if(SwingUtilities.isLeftMouseButton(e))c.setLive(true);
				if(SwingUtilities.isRightMouseButton(e))c.setLive(false);
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
		
		for(int i = 0; i < cells.size(); i++) {
			Cell c = cells.get(i);
			Point p = new Point(MouseX, MouseY);
			if(c.bounds().contains(p)) {
				if(e.getButton() == 1)c.setLive(true);
				if(e.getButton() == 3)c.setLive(false);
			}
		}
	}
}
