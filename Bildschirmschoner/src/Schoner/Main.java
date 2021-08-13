package Schoner;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = -72478326243153197L;
	public static int WIDTH = 1280, HEIGHT = WIDTH/12*9;
	public boolean running = false;
	public Thread thread;
	private int timer = 0;
	private Random r;
	LinkedList<ball> baelle = new LinkedList<ball>();
	
	public Main() {
		this.addKeyListener(new KeyInput());
		new Window(WIDTH, HEIGHT, "Titel", this, true);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void tick(){
		timer++;
		if(timer == 10){
			timer = 0;
			r = new Random();
			baelle.add(new ball(r.nextInt(WIDTH), r.nextInt(HEIGHT)));
		}
		

	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
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
		
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		

		for(int i = 0; i < baelle.size(); i++){
			ball tempObject = baelle.get(i);
			tempObject.tick();
			tempObject.render(g);
			if(tempObject.r > 400){
				baelle.remove(tempObject);
			}
		}
		
		
		g.dispose();
		bs.show();
	}
	
	
	
	public void run() {
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
	
	public class ball{
		public int x, y, r = 0;
		private int red, green, blue;
		private Random rand;
		
		public ball(int x, int y){
			this.x = x;
			this.y = y;
			
			rand = new Random();
			red = rand.nextInt(256);
			green = rand.nextInt(256);
			blue = rand.nextInt(256);
		}
		
		void tick(){
			r += 2;
			x--;
			y--;
		}
		
		void render(Graphics2D g){
			g.setColor(new Color(red, green, blue));
			g.fillRoundRect(x, y, r, r, r, r);
			g.setColor(Color.black);
			g.setStroke(new BasicStroke(3));
			g.drawRoundRect(x, y, r, r, r, r);
		}
	}
	
	
	public static void main(String[] args) {
		new Main();
	}
}
