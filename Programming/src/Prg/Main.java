package Prg;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Prg.Entity.Block;
import Prg.Entity.EHandler;
import Prg.Entity.ID;
import Prg.Entity.Player;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = -8637654948469937916L;
	public static boolean running = false;
	public static int WIDTH = 1024, HEIGHT = WIDTH/12*9;
	private Thread thread;
	
	private EHandler ehandler;
	
	public Main() {
		createFrame();
		loader();

		this.start();
		//run();
	}
	
	private void loader() {
		ehandler = new EHandler();
		
		ehandler.entitys.add(new Player(50,50,ID.Player,ehandler));
		for(int i = 0; i < 50; i++)
			ehandler.entitys.add(new Block(i*32,500,ID.Block));
		thread = new Thread(this);
		thread.start();
	}
	
	private void tick() {
		ehandler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		ehandler.render(g);
		
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
	}
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void createFrame(){
		JFrame frame = new JFrame("Titel");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(new KeyInput(ehandler));
		this.addKeyListener(new KeyInput(ehandler));
		frame.add(this);
	}
	
	public static void main(String args[]){ new Main();	}
}
