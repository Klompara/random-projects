package Nikö;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Nikö.Entity.Components.EHandler;
import Nikö.Inputs.KeyInput;
import Nikö.gfx.VCam;
import Nikö.gfx.loader;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = 681979688295566469L;
	public static int WIDTH = 1152, HEIGHT = WIDTH/14*9;
	public static boolean running = false;
	private Thread thread;
	private VCam cam;
	private EHandler ehandler;
	private loader Loader;
	
	public Main(){
		start();
		newJavaFrame();
		Loader.LoadLevelImage();
		//run();
	}
	
	private void tick(){
		ehandler.tick();
		cam.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		ehandler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	
	private synchronized void start(){
		thread = new Thread();
		thread.start();
		running = true;
		
		ehandler = new EHandler();
		Loader = new loader(ehandler);
		cam = new VCam(ehandler);
	}
	private synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
				System.out.println("ticks: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public static int random(int min, int max){
		int range = (max-min)+1;
		return (int) (Math.random()*range+min);
	}
	

	
	public void newJavaFrame(){
		this.addKeyListener(new KeyInput(ehandler));
		JFrame frame = new JFrame("Nikö");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(this);
		
	    frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}
	
	public static void main(String args[]){ new Main();	}
}
