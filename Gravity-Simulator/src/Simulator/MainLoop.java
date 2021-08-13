package Simulator;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainLoop extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public static int collisionAtribute = 1;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public double amountOfTicks, ns;
	
	private BufferedImage optionicon;

	public ArrayList<Ball> balls = new ArrayList<Ball>();
	
	public static boolean running = false;
	
	private JFrame frame;
	public Thread thread;
	private MouseListener mouselistener;
	
	private Cam cam;
	
	public MainLoop() {
		try {
			optionicon = ImageIO.read(getClass().getResourceAsStream("/gear.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		createFrame("Gravitations Simulator - v. 1.1");
		
		Ball b = new Ball(WIDTH/2, HEIGHT/2);
		b.setSize(25);
		b.setMainball(true);
		b.setEnabled(true);
		balls.add(b);
		
		cam = new Cam();
		mouselistener = new MouseListener(this, cam);
		this.addMouseListener(mouselistener);
		this.addMouseMotionListener(mouselistener);
		
		thread = new Thread(this);
		thread.start();
		
		running = true;
	}
	
	private void destruct() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		
		int mainballs = 0;
		for(int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			if(b.isMainball())mainballs++;
		}
		if(mainballs != 1) {
			for(int i = 0; i < balls.size(); i++) {
				Ball b = balls.get(i);
				b.setMainball(false);
			}
			balls.get(balls.size()-1).setMainball(true);
		}
		
		if(mouselistener.clickcounter == 1) {
			for(int i = 0; i < balls.size(); i++) {
				Ball b = balls.get(i);
				b.tick(balls);
			}
		}
		
		if(mouselistener.clickcounter == 2) {
			Ball b = balls.get(balls.size()-1);
			double diffX = (b.getX() - mouselistener.MouseX);
			double diffY = (b.getY() - mouselistener.MouseY);
			double distance = Math.sqrt(diffX*diffX + diffY*diffY);
			b.setSize((int)distance);
		}
		cam.tick(balls);
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		g.translate(cam.getX(), cam.getY());
		
		for(int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			for(int j = 0; j < b.trails.size(); j++) {
				Trail t = b.trails.get(j);
				t.render(g);
			}
		}
		
		for(int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			b.render(g);
		}

		if(mouselistener.clickcounter == 3) {
			Ball b = balls.get(balls.size()-1);
			g.setColor(Color.red);
			g.setStroke(new BasicStroke(3));
			g.drawLine((int)b.getX(), (int)b.getY(), mouselistener.MouseX, mouselistener.MouseY);
			g.setStroke(new BasicStroke(1));
		}
		g.translate(-cam.getX(), -cam.getY());
		
		g.drawImage(optionicon, (int) (WIDTH-optionicon.getWidth()/2.5), 0, optionicon.getWidth()/3, optionicon.getHeight()/3, null);
		
		
		g.dispose();
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		amountOfTicks = 60.0;
		ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		int frames = 0;
		int ticks = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				ticks++;
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle("Gravitations Simulator - v. 1.1 - ticks: " + ticks + ", frames: "+frames);
				frames = 0;
				ticks = 0;
			}
		}
		destruct();
	}
	
	private void createFrame(String titel) {
		frame = new JFrame(titel);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public Rectangle2D getbuttonBounds() {
		return new Rectangle((int)(WIDTH-optionicon.getWidth()/2.5), 0, optionicon.getWidth()/3, optionicon.getHeight()/3);
	}
	
	public static void main(String[] args) { new MainLoop(); }
}
