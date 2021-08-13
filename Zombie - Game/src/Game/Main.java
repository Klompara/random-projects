package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Game.Input.KeyInput;
import Game.Input.MouseInput;
import Game.Input.WindowListener;
import Game.gfx.VCam;
import Game.net.Client;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public static int WIDHT = 768;
	public static int HEIGHT = WIDHT / 12 * 9;
	
	public static boolean running = false;
	
	public Thread thread;
	
	private JFrame frame;
	
	private Client client;
	
	public static Handler handler;
	
	private VCam cam;
	
	private KeyInput keyinput;
	private MouseInput mouseinput;
	private WindowListener windowlistener;

	public Main() {
		loadFrame();
	}
	
	
	private void tick() {
		handler.tick();
		cam.tick(handler);
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

		
		g.setColor(Color.DARK_GRAY.darker());
		g.fillRect(0, 0, WIDHT, HEIGHT);
		
		
		g.translate(cam.getX(), cam.getY());
		handler.render(g);
		g.translate(-cam.getX(), -cam.getY());
			
			
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
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle("gamee - ticks: " + ticks + ", frames: "+frames);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	private synchronized void start() {
		String nickname = "";
		while(nickname.length() == 0 || nickname.length() > 12 || nickname.contains(";") || nickname.contains(" ")){
			nickname = JOptionPane.showInputDialog("Nickname: ");
		}
		InetAddress ipAddress = null;
		try {
			ipAddress = InetAddress.getByName(JOptionPane.showInputDialog(null, "Server IP-Addrese: ", "localhost", JOptionPane.QUESTION_MESSAGE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cam = new VCam();
		client = new Client(ipAddress);
		handler = new Handler(client, nickname, frame);
		client.sendData(("00;"+nickname+";").getBytes());
		keyinput = new KeyInput(handler);
		mouseinput = new MouseInput(handler, cam);
		windowlistener = new WindowListener(client, handler);

		
		frame.addWindowListener(windowlistener);
		frame.addComponentListener(new ComponentListener() {  
			public void componentMoved(ComponentEvent evt) { handler.stopWalk(); }
		    public void componentShown(ComponentEvent evt) {}
		    public void componentResized(ComponentEvent evt) {} 
		    public void componentHidden(ComponentEvent evt) {}
		});
		this.addMouseListener(mouseinput);
		this.addMouseMotionListener(mouseinput);
		this.addKeyListener(keyinput);
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private synchronized void stop() {
		System.out.println("stop");
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void loadFrame() {
		frame = new JFrame();
		frame.setSize(WIDHT, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.requestFocus();
		frame.add(this);
		frame.setVisible(true);
		start();
	}
	
	public static int random(int max, int min) {
		int range = (max - min) + 1;
		return (int) ((Math.random() * range) + min);
	}
	
	public static void main(String[] args) { new Main(); }
}
