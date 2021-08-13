package Paint;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListener extends MouseAdapter implements MouseMotionListener{
	public static boolean isBrushing = false;
	public static int x, y;
	private Main main;
	public MouseListener(Main main) {
		this.main = main;
	}

	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		main.drawing(x, y);
	}
}
