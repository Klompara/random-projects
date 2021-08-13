package Simulator;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MouseListener extends MouseAdapter implements MouseMotionListener {

	public int clickcounter = 1;

	private MainLoop main;
	private Cam cam;
	
	public int MouseX, MouseY;
	
	public MouseListener(MainLoop main, Cam cam) {
		this.main = main;
		this.cam = cam;
	}
	
	public void mouseMoved(MouseEvent e) {
		this.MouseX = e.getX() - cam.getX();
		this.MouseY = e.getY() - cam.getY();
	}
	
	public void mouseClicked(MouseEvent e) {
		Point2D p = new Point(e.getX(), e.getY());
		if(main.getbuttonBounds().contains(p)) {
			clickcounter = -1;
			main.amountOfTicks = Double.parseDouble(JOptionPane.showInputDialog("Tick geschwindigkeit (normal: 60)"));
			main.ns = 1000000000 / main.amountOfTicks;
			
			String[] buttons = { "Abstoßen (glitchy)", "Zusammenfügen", "ignorieren"};
		    int rc = JOptionPane.showOptionDialog(null, "Kollisionseigenschaft", "Abfrage",
		        JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[2]);
			MainLoop.collisionAtribute = rc;
		    
			clickcounter = 1;
		}else if(SwingUtilities.isMiddleMouseButton(e)) {
			System.out.println("Switch");
			for(int i = 0; i < main.balls.size(); i++) {
				if(main.balls.get(i).isMainball()) {
					if(i+1 < main.balls.size()) {
						main.balls.get(i+1).setMainball(true);
						main.balls.get(i).setMainball(false);
					}else{
						main.balls.get(0).setMainball(true);
						main.balls.get(i).setMainball(false);
					}
					i = main.balls.size()+11;
				}
			}
		} else {
			if(clickcounter == 1) {
				main.balls.add(new Ball(MouseX, MouseY));
				clickcounter++;
			}else if(clickcounter == 2) {
				clickcounter++;
			}else if(clickcounter == 3) {
				Ball b = main.balls.get(main.balls.size()-1);
				double diffX = (b.getX() - MouseX);
				double diffY = (b.getY() - MouseY);
				double distance = Math.sqrt(diffX*diffX + diffY*diffY);
				b.setDx((1/distance)*diffX*(distance/50));
				b.setDy((1/distance)*diffY*(distance/50));
				b.setEnabled(true);
				clickcounter = 1;
			}
		}
	}
}
