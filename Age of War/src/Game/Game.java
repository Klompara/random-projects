package Game;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Game.Entitys.Camera;
import Game.GameStates.Handler;
import Game.Input.KeyInput;
import Game.Input.MouseInput;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 1280;
	public static int HEIGHT = 768;
	
	public static String titel = "Game";
	
	public JFrame frame;
	public static boolean running = false;
	public Thread thread;
	
	private Handler handler;
	
	public Camera cam;
	
	private KeyInput keyInput;
	private MouseInput mouseInput;
	
	private static long starttime;
	
	public Game(){
		cam = new Camera(0, 0);
		keyInput = new KeyInput();
		mouseInput = new MouseInput(cam);
		handler = new Handler(cam);
		
		createFrame(titel);
		
		starttime = System.currentTimeMillis();
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private void tick(){
		handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void run(){
		this.requestFocus();
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
			if(running){
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle(titel + " - ticks: "+ticks+", frames: "+frames);
				frames = 0;
				ticks = 0;
			}
		}
		destruct();
	}
	
	public void destruct(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void createFrame(String titel) {
		frame = new JFrame(titel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		this.addMouseMotionListener(mouseInput);
		this.addMouseListener(mouseInput);
		this.addKeyListener(keyInput);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public static long getCurrentTime() {
		return System.currentTimeMillis() - starttime; 
	} 
	
	public static void main (String args[]){ new Game(); }
}
