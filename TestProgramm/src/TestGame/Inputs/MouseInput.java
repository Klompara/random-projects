package TestGame.Inputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import TestGame.Entitys.Kugel;
import TestGame.Main.Handler;

public class MouseInput extends MouseAdapter implements MouseMotionListener{
	
	private int mousex;
	private int mousey;
	
	public static int firsttempx;
	public static int firsttempy;
	
	public static int lasttempx;
	public static int lasttempy;
	
	private Handler handler;
	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	
	public void mouseMoved(MouseEvent e) {
		this.mousex = e.getX();
		this.mousey = e.getY();
	}


	
	public void mouseDragged(MouseEvent e) {
		for(int i = 0; i < handler.kugeln.size(); i++) {
			Kugel k = handler.kugeln.get(i);
			int kx = (int) k.getX();
			int ky = (int) k.getY();
			int kr = (int) k.getR();
			
			double distx = kx - e.getX();
			double disty = ky - e.getY();
			double distance = Math.sqrt(distx*distx + disty*disty);
			
			if(distance < 0 + kr) {
				k.setX(e.getX());
				k.setY(e.getY());
			}
		}
		lasttempx = e.getX();
		lasttempy = e.getY();
	}
	
	public void mouseClicked(MouseEvent e) {
		firsttempx = e.getX();
		firsttempy = e.getY();
	}
	
	public void mousePressed(MouseEvent e) {
		handler.kugeln.add(new Kugel(mousex, mousey, handler));
		firsttempx = e.getX();
		firsttempy = e.getY();
	}
	
	public void mouseReleased(MouseEvent e) {
		handler.kugeln.getLast().shoot(firsttempx, firsttempy);
		
		lasttempx = -100;
		lasttempy = -100;
		firsttempx = -100;
		firsttempy = -100;
	}
	
	public int getMousex() { return mousex; }
	public void setMousex(int mousex) { this.mousex = mousex; }
	public int getMousey() { return mousey; }
	public void setMousey(int mousey) { this.mousey = mousey; }
}
