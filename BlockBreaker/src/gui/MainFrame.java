package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import app.BlockBreaker;
import bll.Ball;
import bll.Block;
import bll.MouseListener;
import bll.MouseMotionListener;
import bll.Player;

public class MainFrame extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private Thread thread;
	
	private Player player;
	private List<Ball> balls = new ArrayList<Ball>();
	private List<Block> blocks = new ArrayList<Block>();
	
	public void setUpFrame(Thread thread) {
		player = new Player(300, 700);
		for(int i = 0; i < 10; i++)
			balls.add(new Ball(300, 700-10, blocks, player));
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 25; j++)
				blocks.add(new Block(20+i*11, 20+j*11, Color.yellow));
		}
		blocks.add(new Block(300, 500, Color.green));
		
		this.thread = thread;
		frame = new JFrame("Block Breaker");
		frame.setResizable(false);
		frame.setSize(600, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		MouseMotionListener mouseMotionListener = new MouseMotionListener(player);
		MouseListener mouseListener = new MouseListener(mouseMotionListener);
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseMotionListener);
		frame.add(this);
		
		thread = new Thread(this);
		thread.start();
		BlockBreaker.RUNNING = true;
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
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 800);
		
		player.render(g);
		for(int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			b.render(g);
		}
		for(int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			b.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	private void tick() {
		player.tick();
		for(int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			b.tick();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		int frames = 0;
		int ticks = 0;
		while(BlockBreaker.RUNNING){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				ticks++;
				delta--;
			}
			if(BlockBreaker.RUNNING){
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " Ticks pro Sekunde: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	private void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
