package pack;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class MainClass extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 1920;
	public static int HEIGHT = 1080;
	public static boolean running = false;
	public Thread thread;
	public JFrame frame;
	
	public int factor = 2;
	public boolean addCorners = true;
	public boolean setStartingPoint = false;
	public boolean started = false;
	public ArrayList<Point> points = new ArrayList<Point>();
	public ArrayList<Point> dots = new ArrayList<Point>();
	
	public MainClass() {
		loadFrame();
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		running = false;
	}
	
	public void tick() {
		if(started) {
			Random r = new Random();
			int randomnr = r.nextInt(points.size());
			int x = dots.get(dots.size()-1).x - ((dots.get(dots.size()-1).x - points.get(randomnr).x) / factor);
			int y = dots.get(dots.size()-1).y - ((dots.get(dots.size()-1).y - points.get(randomnr).y) / factor);
			dots.add(new Point(x, y));
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.blue);
		g.setFont(new Font("Sanserif", 1, 20));
		g.drawString("Anzahl: " + dots.size(), 25, 25);
		
		for(int i = 0; i < points.size(); i++) {
			Point p = points.get(i);
			g.setColor(Color.red);
			g.drawRect(p.x, p.y, 1, 1);
		}
		
		for(int i = 0; i < dots.size(); i++) {
			Point p = dots.get(i);
			g.setColor(Color.green);
			g.drawRect(p.x, p.y, 1, 1);
		}
		
		g.dispose();
		bs.show();
	}
	
	private void loadFrame() {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				if(addCorners) {
					points.add(e.getPoint());
				}
				else if(setStartingPoint) {
					dots.add(e.getPoint());
					setStartingPoint = false;
					started = true;
				}
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
			}
		});
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(addCorners) {
						addCorners = false;
						setStartingPoint = true;
					}
					else
					{
						if(setStartingPoint && started) {
							started = false;
						}
					}
				}
			}
		});
		
		frame.add(this);
		frame.setVisible(true);
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 2000.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		int ticks = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			if(running) {
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle("Chaosgame - fps: "+frames+", ticks: "+ticks);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	public static void main(String[] args) {
		new MainClass();
	}

}
