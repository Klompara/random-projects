package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Game.GameState.Handler;
import Game.Input.KeyInput;
import Game.Input.MouseInput;

public class MainClass extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static boolean running = false;
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	public static String frameTitel = "Blobby Volley";
	
	private JFrame frame;
	private Thread thread;
	
	private Handler handler;
	
	private KeyInput keyInput;
	private MouseInput mouseInput;

	public MainClass() {
		loadTextures();
		initiate();
		loadFrame();

		thread = new Thread(this);
		thread.start();
		running = true;
	}

	private void tick(){
		handler.tick();
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
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void run(){
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
				frame.setTitle(frameTitel + " - ticks: "+ticks+", frames: "+frames);
				frames = 0;
				ticks = 0;
			}
		}
		destruct();
	}
	
	public void destruct(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		TextureLoader.loadTextures(l);
	}
	
	private void initiate() {
		handler = new Handler();
		keyInput = new KeyInput();
		mouseInput = new MouseInput();
	}
	
	private void loadFrame() {
		frame = new JFrame(frameTitel);
		
		frame.setSize(WIDTH, HEIGHT);
		
//		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice device = env.getScreenDevices()[0];
//		DisplayMode oldMode = device.getDisplayMode();
//		DisplayMode newMode = new DisplayMode(WIDTH,HEIGHT,oldMode.getBitDepth(),oldMode.getRefreshRate());
//		  
//	    device.setFullScreenWindow(frame);
//	    device.setDisplayMode(newMode);
	    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		this.addKeyListener(keyInput);
		this.addMouseListener(mouseInput);
		this.addMouseMotionListener(mouseInput);
		frame.add(this);
		
		frame.setVisible(true);
	}

	public static void main(String[] args) { new MainClass(); }
}
