package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Game.States.StateHandler;
import Game.States.StateTypes;

public class MainClass extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static String frameTitle;
	public static int frameWidth = 1280;
	public static int frameHeight = 768;
	private JFrame frame;
	
	public static boolean running;
	public Thread thread;
	
	private StateHandler stateHandler;
	
	public MainClass() {
		loadWindow();
		loadTextures();
		init();
		running = true;
	}
	
	
	private void tick() {
		stateHandler.tick();
	}
	
	private void render() {
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
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		
		stateHandler.render(g);
		
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
		int ticks = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				ticks++;
				delta--;
			}
			if(running){
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle(frameTitle + " - ticks: "+ticks+", frames: "+frames);
				frames = 0;
				ticks = 0;
			}
		}
		destruct();
	}
	
	private void destruct() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		stateHandler = new StateHandler(StateTypes.Play);
		
		thread = new Thread(this);
		thread.start();
	}
	
	private void loadTextures() {
		List<String> l = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader("res/textureFiles"))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	if(!line.startsWith("//"))
		    		l.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assets.loadTextures(l);
	}

	private void loadWindow() {
		frame = new JFrame(frameTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		MouseMotionListener test = new MouseMotionListener() {			
			public void mouseMoved(MouseEvent e) {
				
			}
			public void mouseDragged(MouseEvent e) {
				
			}
		};
		this.addMouseMotionListener(test);
		frame.addMouseMotionListener(test);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) { new MainClass(); }
}
