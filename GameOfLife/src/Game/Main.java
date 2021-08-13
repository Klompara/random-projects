package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 800;
	public static int HEIGTH = 600;
	
	public static int CELL_SIZE = 5;
	
	public static int LINES = 0;
	public static int COLLS = 0;
	
	public static boolean running = false;
	
	public static boolean update = true;
	
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	
	private JFrame frame;
	
	private MouseInput mouseinput;
	private KeyInput keyinput;

	public Thread thread;

	public Main() {
		CELL_SIZE = Integer.parseInt(JOptionPane.showInputDialog("Cell size: "));
		
		Toolkit tk = this.getToolkit();
		WIDTH = (int) tk.getScreenSize().getWidth();
		HEIGTH = (int) tk.getScreenSize().getHeight();
		
		createJFrame("GAME OF LIFE");
		
		int ID = 0;
		LINES = HEIGTH/CELL_SIZE;
		COLLS = WIDTH/CELL_SIZE;
		for(int y = 0; y < HEIGTH/CELL_SIZE; y++) {
			for(int x = 0; x < WIDTH/CELL_SIZE; x++) {
				cells.add(new Cell(x, y, ID));
				ID++;
			}
		}

		mouseinput = new MouseInput(cells);
		this.addMouseListener(mouseinput);
		this.addMouseMotionListener(mouseinput);
		keyinput = new KeyInput();
		this.addKeyListener(keyinput);
		
		running = true;
		thread = new Thread(this);
		thread.start();
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
				frame.setTitle("GAME OF LIFE - M.K. - fps: "+frames+", ticks: "+ticks);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(update) {
			for(int i = 0; i < cells.size(); i++) {
				Cell c = cells.get(i);
				c.tick(cells);
			}
			for(int i = 0; i < cells.size(); i++) {
				Cell c = cells.get(i);
				c.setFinalLive();
			}
		}
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
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGTH);

		for(int i = 0; i < cells.size(); i++) {
			Cell c = cells.get(i);
			c.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	private void stop() {
		frame.setVisible(false);
		frame.dispose();
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	private void createJFrame(String titel) {
		frame = new JFrame(titel);
		frame.setSize(WIDTH, HEIGTH);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public static boolean clamp(int var, int min, int max) {
		boolean value = true;
		if(var < min) value = false;
		if(var > max) value = false;
		return value;
	}
	
	public static void main(String[] args) { new Main(); }
}
