package Shooter.Inputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Shooter.Weapons.weaponHandler;

public class MouseInput extends MouseAdapter implements MouseMotionListener{
	public static int x, y;
	public static boolean shoot = false;
	public MouseInput(weaponHandler wHandler) {}
	public void mousePressed(MouseEvent e) {
		shoot = true;
	}
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	public void mouseReleased(MouseEvent e) {
		shoot = false;
	}
}
