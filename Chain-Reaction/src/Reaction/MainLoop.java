package Reaction;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class MainLoop extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static boolean running = false;
	public Thread thread;
	public JFrame frame;
	public ArrayList<Point> points = new ArrayList<Point>();
	public ArrayList<Explosions> explosions = new ArrayList<Explosions>();
	private int level = 1;
	private boolean roundstarted = false;
	private boolean hasClicked = false;
	
	public MainLoop() {
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
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
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
				frame.setTitle("Game - fps: "+frames+", ticks: "+ticks);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		for(int i = 0; i < points.size(); i++) {
			Point p = points.get(i);
			p.tick();	
		}
		
		if(!roundstarted) {
			roundstarted = true;
			if(level == 1) {
				for(int i = 0; i < 10; i++) {
					points.add(new Point(new Random().nextInt(WIDTH), new Random().nextInt(HEIGHT)));
				}
			}else if(level == 2) {
				for(int i = 0; i < 50; i++) {
					points.add(new Point(new Random().nextInt(WIDTH), new Random().nextInt(HEIGHT)));
				}
			}else if(level == 3) {
				for(int i = 0; i < 100; i++) {
					points.add(new Point(new Random().nextInt(WIDTH), new Random().nextInt(HEIGHT)));
				}
			}
		}
		if(roundstarted && explosions.size() == 0 && hasClicked) {
			roundstarted = false;
			hasClicked = false;
			if(level == 1) {
				if(points.size() < 5) {
					level++;
				}
			}else if(level == 2) {
				if(points.size() < 10) {
					level++;
				}
			}else if(level == 3) {
				if(points.size() < 3) {
					level++;
				}
			}
			points.clear();
		}
		
		for(int i = 0; i < explosions.size(); i++) {
			Explosions e = explosions.get(i);
			e.tick();
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
		
		for(Point p : points)
			p.render(g);
		
		for(Explosions e : explosions)
			e.render(g);
		
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
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				if(!hasClicked) {
					explosions.add(new Explosions(e.getX(), e.getY()));
					hasClicked = true;
				}
			}
		});
		
		frame.add(this);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {new MainLoop();}
	
	public class Point {
		private double x, y, dx, dy, r;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
			double rad = Math.toRadians(new Random().nextInt(360));
			dx = Math.cos(rad)*5;
			dy = Math.sin(rad)*5;
			r = 15;
		}
		
		public void tick() {
			x += dx;
			y += dy;
			
			if(x > WIDTH) x = 0;
			if(x < 0) x = WIDTH;
			if(y > HEIGHT) y = 0;
			if(y < 0) y = HEIGHT;
		}
		
		public void render(Graphics2D g) {
			g.setStroke(new BasicStroke(2f));
			g.setColor(Color.green);
			g.drawOval((int)(x-r), (int)(y-r), (int)(r*2), (int)(r*2));
			g.setStroke(new BasicStroke(1f));
		}
	}
	
	public class Explosions {
		private int x, y, r;
		private double durability = 255;
		private Color c;
		public Explosions(int x, int y) {
			this.x = x;
			this.y = y;
			r = 30;
			c = new Color(0,0,0);
		}
		
		public void tick() {
			if(durability == 0)
				explosions.remove(this);
			c = new Color((int)durability, (int)durability/7, (int)durability/10);
			durability--;
			
			for(int i = 0; i < points.size(); i++) {
				Point p = points.get(i);
				double diffX = x - p.x;
				double diffY = y - p.y;
				double distance = Math.sqrt(diffX*diffX + diffY*diffY);
				if(distance < r + p.r) {
					explosions.add(new Explosions((int)p.x-(int)p.dx, (int)p.y-(int)p.dy));
					points.remove(p);
					break;
				}
			}
		}
		
		public void render(Graphics2D g) {
			g.setColor(c);
			g.fillOval(x-r, y-r, r*2, r*2);
		}
	}
}
