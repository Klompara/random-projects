package Paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 4433339372853233415L;
	
	public static final int WIDTH = 1024, HEIGHT = WIDTH / 12 * 9;
	public Thread thread;
	public boolean running = false;
	
	private int red, green, blue;
	public static int radius = 64;
	
	LinkedList<Brush> brushes = new LinkedList<Brush>();
	
	public Main(){
		this.addMouseListener(new MouseListener(this));
		this.addMouseMotionListener(new MouseListener(this));
		
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(this);
		

		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);
		
		
		this.start();
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
		setColors();
	}
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void setColors(){
		red = Integer.valueOf(JOptionPane.showInputDialog("Red"));
		green = Integer.valueOf(JOptionPane.showInputDialog("Green"));
		blue = Integer.valueOf(JOptionPane.showInputDialog("Blue"));
	}
	
	private void tick(){
		
	}
	
	public void drawing(int x, int y){
		brushes.add(new Brush(x-radius, y-radius, new Color(red, green, blue)));
	}
	
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < brushes.size(); i++){
			Brush tempObject = brushes.get(i);
			tempObject.draw(g);
		}
		
		g.setColor(Color.white);
		g.drawRect(MouseListener.x, MouseListener.y, 8, 8);
		
		g.dispose();
		bs.show();
	}
	

	
	public static void main (String args[]){
		new Main();
	}
	
	public class Brush {
		public int x, y;
		public Color color;
		private int BRadius;
		Brush(int x, int y, Color color){
			this.x = x;
			this.y = y;
			this.color = color;
			this.BRadius = radius;
		}
		
		void draw(Graphics g){
			g.setColor(color);
			g.fillOval(x, y, BRadius*2, BRadius*2);
		}
	}
}