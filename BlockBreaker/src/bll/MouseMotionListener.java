package bll;

import java.awt.event.MouseEvent;

public class MouseMotionListener implements java.awt.event.MouseMotionListener {

	private Player player;
	private boolean isClicked = false;
	
	public MouseMotionListener(Player p) {
		player = p;
	}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		if(isClicked)
			player.setX(e.getX());
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	
}
