package quadtree;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private Car car;
	public static Quad mainQuad;
	ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	
	private BufferedImage map = null;
	
	public static boolean normalBomb = true;
	public static boolean drawRig = false;
	public static boolean running = false;
	public static Thread thread;
	
	private JFrame frame;
	
	public static int quality = 16;
	
	public Main() {
		loadFrame();

		try {
			map = ImageIO.read(new File("res/map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mainQuad = new Quad(0, 0, map.getWidth(), map);
		//bombs.add(new Bomb(100, 100)); testbomb
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private void tick() {
		mainQuad.tick(bombs, car);
		
		if(car != null)
			car.tick();
		
		for(int i = 0; i < bombs.size(); i++) {
			Bomb b = bombs.get(i);
			b.tick();
			if(b.isExplode()) {
				Graphics gra = map.getGraphics();
				if(normalBomb)
					gra.setColor(Color.white);
				else
					gra.setColor(Color.green);
				gra.fillOval((int)b.getX()-50, (int) (b.getY()-50), 100, 100);
				
				mainQuad = new Quad(0, 0, map.getWidth(), map);
				
				bombs.remove(b);
			}
			if(b.getY() > frame.getHeight()) bombs.remove(b);
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
		
		
		//g.drawImage(map, 0, 0, null);
		
		mainQuad.render(g);

		
		for(int i = 0; i < bombs.size(); i++) {
			Bomb b = bombs.get(i);
			b.render(g);
		}
		
		if(car != null)
			car.render(g);
		
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
				ticks++;
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle("Quadtree Test - "+frames+" FPS, "+ticks + " ticks");
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
		running = false;
	}

	private void loadFrame() {
		frame = new JFrame("Quadtree");
		frame.setSize(1024, 768);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		this.addMouseListener(new MouseListener() {	
			public void mouseReleased(MouseEvent e) { }
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == 1)
					bombs.add(new Bomb(e.getX(), e.getY()));
				if(e.getButton() == 3)
					car = new Car(e.getX(), e.getY());
			}
			public void mouseExited(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) { }
		});
		
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_A) {
					if(car != null) {
						car.setDx1(0);
						car.setDx2(0);
					}
				}else if(key == KeyEvent.VK_D) {
					if(car != null) {
						car.setDx1(0);
						car.setDx2(0);
					}
				}
			}
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_R) {
					drawRig = !drawRig;
				}
				if(key == KeyEvent.VK_Q) {
					int eing = 0;
					eing = Integer.parseInt(JOptionPane.showInputDialog("Qualität(1-10): "));
					while(eing < -1 || eing > 10) {
						JOptionPane.showMessageDialog(null, "Es muss zwischen 1 und 10 sein!");
						eing = Integer.parseInt(JOptionPane.showInputDialog("Qualität(1-10): "));
					}
					quality = 0;
					for(int i = 0; i < eing; i++) {
						quality += eing;
					}
					
					mainQuad = new Quad(0, 0, map.getWidth(), map);
				}
				if(key == KeyEvent.VK_C) {
					try {
						map = ImageIO.read(new File("res/map.png"));
						mainQuad = new Quad(0, 0, map.getWidth(), map);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(key == KeyEvent.VK_A) {
					if(car != null) {
						car.setDx1(-2);
						car.setDx2(-2);
					}
				}else if(key == KeyEvent.VK_D) {
					if(car != null) {
						car.setDx1(2);
						car.setDx2(2);
					}
				}
				if(key == KeyEvent.VK_SPACE) {
					if(car != null) {
						car.setDy1(-3);
						car.setDy2(-3);
					}
				}
				if(key == KeyEvent.VK_E) {
					normalBomb = !normalBomb;
				}
				if(key == KeyEvent.VK_S) {
				    File outputfile = new File("saved.png");
				    try {
						ImageIO.write(map, "png", outputfile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		frame.add(this);
		frame.setVisible(true);
	}
	
	public static double calculateDistance(double x1, double y1, double x2, double y2) {
		double diffX = x1 - x2;
		double diffY = y1 - y2;
		double distance = Math.sqrt(diffX*diffX + diffY*diffY);
		return distance;
	}
	
	public static void main(String[] args) { new Main(); }
}
