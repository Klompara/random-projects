package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Game.Input.KeyInput;
import Game.Input.MouseInput;
import Game.gfx.VCam;

public class Main extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -2023456126816990679L;

	public static int WIDTH  = 640;
	public static int HEIGHT = WIDTH/12*9;
	
	private Thread thread;
	
	public static boolean running = false;
	
	private Handler handler;
	
	private KeyInput keyinput;
	private MouseInput mouseinput;
	
	private VCam cam;
	
	// CONSTRUCTOR
	public Main() {
		loadFrame("titel");
		start();
		running = true;
	}
	
	// TICK FUNCTION
	private void tick() {
		handler.tick();
		cam.tick();
	}
	
	// RENDER FUNCTION
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}

		
		// generiere graphic
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// Background
		//g.setColor(Color.darkGray.darker());
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		Rectangle r=new Rectangle(0,0,Main.WIDTH,Main.HEIGHT);
		RadialGradientPaint rgp=new RadialGradientPaint(new Point2D.Double(Main.WIDTH/2,Main.HEIGHT/2),Main.HEIGHT-200,new float[]{0.0f,1.0f},new Color[]{Color.gray,Color.gray.darker()});
		g.setPaint(rgp);
  		g.fill(r);


		//g.drawImage(Textures.sky, 0, 0, WIDTH, HEIGHT, null);
		g.translate(cam.getX(), cam.getY());
			handler.render(g);
		g.translate(-cam.getX(), -cam.getY());
		
		g.dispose();
		bs.show();
	}
	
	
	// START FUNCTION
	private synchronized void start() {
		cam = new VCam(0, 0);
		handler = new Handler(cam);
		keyinput = new KeyInput();
		mouseinput = new MouseInput();
		
		this.addKeyListener(keyinput);
		this.addMouseListener(mouseinput);
		this.addMouseMotionListener(mouseinput);
		
		thread = new Thread(this);
		thread.start();
	}
	// STOP FUNCTION
	private synchronized void stop() {
		System.out.println("Stop!");
		running = false;
		try { thread.join();
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	
	// FRAMES FUNCTION
	public void run() {
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
	
	// LOAD FRAME
	private void loadFrame(String titel) {
		JFrame frame = new JFrame(titel);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocus();
	}
	
	public static void main(String[] args){ new Main(); }
}
