import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Main extends Canvas{
	Thread thread;
	
	public Main() {
		JFrame frame = new JFrame("Demo");
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.add(this);
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(1);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.DARK_GRAY.darker());
		g.fillRect(0, 0, 400, 400);
		g.setColor(Color.red);
		g.drawRect(15, 15, 350, 350);
		g.setStroke(new BasicStroke(8));
		g.setColor(Color.cyan);
		g.drawRect(15, 15, 350, 350);
		
		g.dispose();
		bs.show();
	}
	
	
	public static void main(String[] args) { new Main(); }
}
