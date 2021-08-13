package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private enum GAMESTATES {
		Menue,
		Play
	};
	private GAMESTATES currentState = GAMESTATES.Menue;
	
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 700, HEIGHT = (int)(WIDTH * 1.33);
	
	private boolean running = false;
	
	private JFrame frame;
	private Thread thread;
	
	private String title;
	
	private Color backgroundC1 = new Color(0, 0, 255);
	private int backgroundCSwitch1 = 0;
	private Color backgroundC2 = new Color(255, 0, 255);
	private int backgroundCSwitch2 = 1;
	
	public Game() {
		title = "balls";
		loadFrame(title);
		running = true;
		thread = new Thread(this, "Balls");
		thread.start();
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
		
		
		
		if(currentState == GAMESTATES.Menue) {
			;
			String text = "Press enter to start";
			// farbverlauf
			Rectangle r = new Rectangle(0, 0, WIDTH, HEIGHT);
			RadialGradientPaint rgp = new RadialGradientPaint(new Point2D.Double(WIDTH/2,HEIGHT/2),HEIGHT-200,new float[]{0.0f,1.0f},new Color[]{backgroundC1,backgroundC2});
			g.setPaint(rgp);
	  		g.fill(r);
	  		
	  		// press start schreiben
	  		g.setFont(font(50));
	  		g.setColor(Color.white);
	  		g.drawString(text, WIDTH/2-getFWidth(text, g)/2, HEIGHT/2-getFHeight(text, g)/2);
		}else if(currentState == GAMESTATES.Play) {
			
		}else{
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}
		
		g.dispose();
		bs.show();
	}
	
	private void tick() {
		if(currentState == GAMESTATES.Menue) {
			calculateBackground(backgroundC1, backgroundCSwitch1, 1);
			calculateBackground(backgroundC2, backgroundCSwitch2, 2);
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		int ticks = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			if(running) {
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle(title + " - fps: "+frames+", ticks: "+ticks);
				frames = 0;
				ticks = 0;
			}
		}
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void loadFrame(String title) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}
	
	private void calculateBackground(Color c, int state, int colorNumber) {
		if(state == 0) {
			if(c.getRed() < 255) {
				c = new Color(c.getRed()+1, c.getGreen(), c.getBlue());
			}else {
				state++;
			}
		}else if(state == 1) {
			if(c.getBlue() > 0) {
				c = new Color(c.getRed(), c.getGreen(), c.getBlue()-1);
			} else {
				state++;
			}	
		}else if(state == 2) {
			if(c.getGreen() < 255)
				c = new Color(c.getRed(), c.getGreen()+1, c.getBlue());
			else
				state++;
		}else if(state == 3) {
			if(c.getRed() > 0)
				c = new Color(c.getRed()-1, c.getGreen(), c.getBlue());
			else
				state++;
		}else if(state == 4) {
			if(c.getBlue() < 255)
				c = new Color(c.getRed(), c.getGreen(), c.getBlue()+1);
			else
				state++;
		}
		else if(state == 5) {
			if(c.getGreen() > 0)
				c = new Color(c.getRed(), c.getGreen()-1, c.getBlue());
			else
				state = 0;
		}
		
		if(colorNumber == 1) {
			backgroundC1 = c;
			backgroundCSwitch1 = state;
		}else if(colorNumber == 2) {
			backgroundC2 = c;
			backgroundCSwitch2 = state;
		}
	}

	public static void main(String[] args) { new Game(); }
	
	public static Font font(double size){
		return new Font("Purisa", Font.BOLD, (int) size);
	}
	
	public static int getFWidth(String s, Graphics2D g) {
		return (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
	}
	
	public static int getFHeight(String s, Graphics2D g) {
		return (int) g.getFontMetrics().getStringBounds(s, g).getHeight();
	}
}
