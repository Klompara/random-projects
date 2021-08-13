import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = -72478326243153197L;
	public static int WIDTH = 1280, HEIGHT = WIDTH/12*9;
	public boolean running = false;
	public Thread thread;
	private Random r;
	private ArrayList<Integer> values = new ArrayList<Integer>();
	private ArrayList<Boolean> right = new ArrayList<Boolean>();
	private int swaps = 0, compares = 0;
	
	public Main() {
		r = new Random();
		int random;
		for(int i = 0; i < WIDTH/2; i++) {
			random = r.nextInt(WIDTH/2)+1;
			if(!values.contains(random)) {
				values.add(random);
				right.add(false);
			}else{
				i--;
			}
		}

		JFrame frame = new JFrame("Sort");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
		
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
		boolean finished = true;
		for(Boolean b : right) {
			if(!b) {
				finished = false;
				break;
			}
		}
		
		if(!finished) {
			for(int i = 0; i < values.size(); i++) {
				if(i != values.size()-1) {
					if(values.get(i) > values.get(i+1)) {
						Collections.swap(values, i, i+1);
						swaps++;
					}else if(values.get(i) == i+1){
						right.set(i, true);
					}
					compares++;
				}else{
					right.set(i, true);
				}
			}
		}else{
			long finishTime = System.currentTimeMillis();
			long currentTime = System.currentTimeMillis();
			while(finishTime+2000 > currentTime) {
				currentTime = System.currentTimeMillis();
			}
			values.clear();
			right.clear();
			r = new Random();
			int random;
			for(int i = 0; i < WIDTH/2; i++) {
				random = r.nextInt(WIDTH/2)+1;
				if(!values.contains(random)) {
					values.add(random);
					right.add(false);
				}else{
					i--;
				}
			}
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
		
		for(int i = 0; i < values.size(); i++) {
			if(!right.get(i))
				g.setColor(Color.white);
			else
				g.setColor(Color.green);
			g.fillRect(i*2, 0, 2, values.get(i));
		}
		
		g.setColor(Color.cyan);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
		g.drawString("Vertauschungen: "+swaps+", Vergleiche: "+compares, 15, HEIGHT-50);
		
		g.dispose();
		bs.show();
	}
	
	
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 120.0;
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
	
	public static void main(String[] args) {
		new Main();
	}
}
