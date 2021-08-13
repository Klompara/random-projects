package DontOpenThisPackage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class DontOpenThisClass extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 4433339372853233415L;
	public Thread thread;
	public boolean running = false;
	private JFrame frame;
	
	private int colorRed = 255, colorGreen = 0, colorBlue = 0, state = 0;
	
	public DontOpenThisClass() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(getToolkit().getScreenSize()));
		frame.setMaximumSize(new Dimension(getToolkit().getScreenSize()));
		frame.setMinimumSize(new Dimension(getToolkit().getScreenSize()));
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
		this.start();
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		long amountOfTicks = 2;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running){
			
			double ns = 1000000000 / amountOfTicks;
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
				System.out.println("FPS: " + frames + " ticks: "+amountOfTicks);
				frames = 0;
				amountOfTicks++;
			}
		}
		stop();
	}
	
	private void tick(){
		if(state == 0) {
			if(colorGreen < 255)
				colorGreen++;
			else
				state++;
		}else if(state == 1) {
			if(colorRed > 0)
				colorRed--;
			else
				state++;
		}else if(state == 2) {
			if(colorBlue < 255)
				colorBlue++;
			else
				state++;
		}else if(state == 3) {
			if(colorGreen > 0)
				colorGreen--;
			else
				state++;
		}else if(state == 4) {
			if(colorRed < 255)
				colorRed++;
			else
				state++;
		}else if(state == 5) {
			if(colorBlue > 0)
				colorBlue--;
			else
				state = 0;
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(colorRed, colorGreen, colorBlue));
		g.fillRect(0, 0, (int)getToolkit().getScreenSize().getWidth(), (int)getToolkit().getScreenSize().getHeight());
		
		g.dispose();
		bs.show();
	}

	
	public static void main (String args[]){
		new DontOpenThisClass();
	}
}