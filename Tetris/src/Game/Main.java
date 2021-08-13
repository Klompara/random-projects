package Game;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 4433339372853233415L;
	
	public static final int WIDTH = 500, HEIGHT = (int)(WIDTH*1.65);
	public Thread thread;
	public boolean running = false;
	private JFrame frame;
	private stateHandler handler;

	
	public Main(){
		loadTextures("textureFiles");
		loadFrame("Tetris");
		handler = new stateHandler(this);
		this.addKeyListener(new KeyInput());
		start();
	}

	private void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private synchronized void stop(){
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
	
	private void tick(){
		handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
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
		
		
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}

	private void loadTextures(String src) {
		List<String> l = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader("res/" + src))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	if(!line.startsWith("//"))
		    		l.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		TextureLoader.loadTextures(l);
	}
	
	private void loadFrame(String titel) {
		frame = new JFrame(titel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(this);
	}
	
	public static Font font(double size){
		return new Font("SansSerif", Font.BOLD, (int) size); // Purisa
	}
	public static int getFWidth(String s, Graphics2D g) {
		return (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
	}
	public static int getFHeight(String s, Graphics2D g) {
		return (int) g.getFontMetrics().getStringBounds(s, g).getHeight();
	}
	
	public static void main (String args[]){
		new Main();
	}
}