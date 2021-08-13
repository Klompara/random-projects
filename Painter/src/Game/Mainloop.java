package Game;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Game.GameStates.StateHandler;
import Game.Inputs.KeyInput;

public class Mainloop extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 1024;
	public static int HEIGHT = WIDTH / 12 * 9;
	
	public static boolean running = false;
	
	public static Thread thread;
	private JFrame frame;
	private StateHandler stateHandler;
	private KeyInput keyinput;
	
	public Mainloop() {
		loadFrame();
	}
	
	private void tick() {
		stateHandler.tick();
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
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHints(rh);
		stateHandler.render(g);
			
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
				frame.setTitle("Painter - ticks: " + ticks + ", frames: "+frames);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	private synchronized void start() {
		stateHandler = new StateHandler();
		keyinput = new KeyInput(stateHandler);
		this.addKeyListener(keyinput);
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private synchronized void stop() {
		System.out.println("stop");
		running = false;
		frame.dispose();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	private void loadFrame() {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.requestFocus();
		frame.add(this);
		frame.setVisible(true);
		start();
	}
	
	public static int random(int max, int min) {
		int range = (max - min) + 1;
		return (int) ((Math.random()*range) + min);
	}
	
	public static void main(String[] args) { new Mainloop(); }
}
