package Schoner;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends Canvas{
	private static final long serialVersionUID = 8013998713207466070L;
	public Window(int width, int height, String title, Main main, boolean fullscreen){
		JFrame frame = new JFrame(title);
		if(!fullscreen){
			frame.setPreferredSize(new Dimension(width, height ));
			frame.setMaximumSize(new Dimension(width, height));
			frame.setMinimumSize(new Dimension(width, height));
		}else{
			Toolkit tk = Toolkit.getDefaultToolkit();
			frame.setUndecorated(true);
			int Width = (int) tk.getScreenSize().getWidth();
			int Height = (int) tk.getScreenSize().getHeight();
			frame.setSize(Width, Height);
			System.out.println(Width + " "+ Height);
			Main.WIDTH = Width;
			Main.HEIGHT = Height;
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(main);
		frame.setVisible(true);
		main.start();
	}
}
