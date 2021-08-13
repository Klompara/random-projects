package GameIntro;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = -5791619865869894409L;
	public static final int WIDTH = 1024, HEIGHT = WIDTH/12*9;
	public static boolean running = false;
	
	private Thread thread;
	private Intro intro;
	
	public enum STATE{
		Intro(),
		Game(),
	};
	public static STATE gameState = STATE.Intro;
	
	public Main(){
		loadframe("Intro Demo");
		start();
		run();
	}
	
	private void tick(){
		if(gameState == STATE.Intro){
			intro.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == STATE.Intro){
			intro.render(g);
		}
		
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
	public synchronized void start(){
		thread = new Thread();
		thread.start();
		running = true;
		
		intro = new Intro();
	}
	public synchronized void stop(){
		try {
			running = false;
			thread.join();
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	private void loadframe(String titel){
		JFrame frame = new JFrame(titel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(this);
	}
	public static void main(String[] args) { new Main(); }
}
