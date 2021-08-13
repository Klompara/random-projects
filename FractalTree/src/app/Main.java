package app;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import bll.Branch;

public class Main extends Canvas implements Runnable {

private static final long serialVersionUID = 4433339372853233415L;
	
	public static int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;
	public Thread thread;
	public boolean running = false;
	private JFrame frame;
	
	private Branch tree;
	
	private double angle = Math.PI / 4, reduction = 1, angularChange = 1;
	
	public Main(){
		WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		tree = new Branch(new Point(WIDTH/2, HEIGHT), new Point(WIDTH/2, HEIGHT-200), 100, -60, true);
		loadFrame();
		start();
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("test");
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		System.exit(0);
	}
	
	public void run(){
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
	
	private void tick(){
		
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//tree.render(g);
		g.translate(WIDTH/2, HEIGHT-50);
		drawTree(g, 200);
			
		g.dispose();
		bs.show();
	}
	
	private void drawTree(Graphics2D g, double len) {
		g.setColor(Color.white);
		g.drawLine(0, 0, 0, (int)-len);
		g.translate(0, -len);
		
		if(len > 10) {
			java.awt.geom.AffineTransform transform = g.getTransform();
			g.rotate(angle);
			drawTree(g, len*reduction);
			g.setTransform(transform);
			transform = g.getTransform();
			g.rotate(-(angle*angularChange));
			drawTree(g, len*reduction);
			g.setTransform(transform);
		}
	}

	private void loadFrame() {
		frame = new JFrame("Fractal Tree");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(0);
				if(e.getKeyCode() == KeyEvent.VK_UP) 
					angle+=0.01;
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
					angle-=0.01;
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					reduction+=0.0025;
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
					reduction-=0.0025;
				
				if(e.getKeyCode() == KeyEvent.VK_A)
					angularChange-=0.01;
				if(e.getKeyCode() == KeyEvent.VK_D)
					angularChange+=0.01;
			}
		});
		frame.add(this);
		frame.setVisible(true);
	}
	
	public static void main (String args[]){
		new Main();
	}
	
}
