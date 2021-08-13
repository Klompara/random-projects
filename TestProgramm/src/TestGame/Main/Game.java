package TestGame.Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import TestGame.Inputs.KeyInput;
import TestGame.Inputs.MouseInput;


public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	public Thread thread;
	
	public static boolean running = false;
	
	public static int WIDTH = 768;
	public static int HEIGHT = WIDTH / 12 * 9; 
	
	public JFrame frame;
	
	private Handler handler;
	
	private KeyInput keyinput;
	private MouseInput mouseinput;
	
	public Game() {
		loadFrame("Huhu");
		start();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
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
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		handler.render(g);
		
		
		g.dispose();
		bs.show();
	}
	
	
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
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
				frame.setTitle("HUHU! - ticks: " + ticks + ", frames: "+frames);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	private synchronized void start() {
		handler = new Handler();
		keyinput = new KeyInput(handler);
		mouseinput = new MouseInput(handler);
	
		this.addMouseListener(mouseinput);
		this.addMouseMotionListener(mouseinput);
		this.addKeyListener(keyinput);
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void loadFrame(String titel) {
		frame = new JFrame(titel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public static int random(int max, int min) {
		int range = (max - min) +1;
		return (int) ((Math.random() * range) + min);
	}
	
	public static void main(String[] args) { new Game(); }
}
