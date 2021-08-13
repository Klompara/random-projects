package bll;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener{

	private MouseMotionListener motionListener;
	
	public MouseListener(MouseMotionListener motionListener) {
		this.motionListener = motionListener;
	}
	
	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		motionListener.setClicked(!motionListener.isClicked());
	}

	public void mouseReleased(MouseEvent e) {}

}
