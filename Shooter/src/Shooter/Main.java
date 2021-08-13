package Shooter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Shooter.Entitys.Block;
import Shooter.Entitys.ID;
import Shooter.Entitys.Player;
import Shooter.Entitys.entityHandler;
import Shooter.GraphicInterface.graphicHandler;
import Shooter.Inputs.KeyInput;
import Shooter.Inputs.MouseInput;
import Shooter.Weapons.weaponHandler;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = -83187028448178979L;
	public static int WIDTH = 1024, HEIGHT = WIDTH/12*9;
	private Thread thread;
	public static boolean running = false;
	public static boolean hitboxes = false;
	private entityHandler eHandler;
	private graphicHandler gHandler;
	private weaponHandler wHandler;
	//private MouseInput MInput;
	public Main() {
		eHandler = new entityHandler();
		wHandler = new weaponHandler(eHandler);
		gHandler = new graphicHandler(eHandler, wHandler);
		
		//MInput = new MouseInput(wHandler);
		
		eHandler.entitys.add(new Player(WIDTH/4,HEIGHT-200,ID.Player, eHandler));
		for(int i = 0; i < 50; i++)
			eHandler.entitys.add(new Block(0+i*64, HEIGHT-100-i*16, ID.Block, 64, 32));
		
		
		this.addKeyListener(new KeyInput(eHandler));
		this.addMouseListener(new MouseInput(wHandler));
		this.addMouseMotionListener(new MouseInput(wHandler));
		
		
		JFrame frame = new JFrame("Shooter");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(this);
		this.start();
	}
	
	public synchronized void start() {
		thread = new Thread();
		thread.start();
		running = true;
		this.run();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void tick() {
		wHandler.tick();
		eHandler.tick();
		gHandler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		eHandler.render(g);
		gHandler.render(g);
		
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
	
	public static int clamp(int var, int min, int max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static int rand(int min, int max) {
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min;
	}
	
	public static void main(String args[]){
		new Main();
	}
}
