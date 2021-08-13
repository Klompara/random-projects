package cells.Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cells.Inputs.MouseInput;
import cells.Inputs.WindowListener;
import cells.gfx.VCam;
import cells.net.Client;
import cells.net.Server;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 768;
	public static int HEIGHT = WIDTH/12*9;
	
	public static boolean isHost;
	public static boolean graphicboost = false;
	public static boolean running = false;
	public Thread thread;
	
	private Handler handler;
	public VCam cam;
	
	private MouseInput mouseinput;
	private WindowListener windowlistener;
	
	public static Client client;
	public static Server server;
	
	public JFrame frame;

	public Main() {
		loadFrame("Cells");
		running = true;
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
		if(!graphicboost){
			g.setRenderingHint(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(
					RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		
		g.setColor(new Color(255, 250, 250));
		g.fillRect(0, 0, WIDTH, HEIGHT);
	
		handler.render(g);
		
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
				
				frame.setTitle("Hungry cells - ticks: "+ticks+" frames: "+frames);
				
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private synchronized void start() {
		windowlistener = new WindowListener();
		cam = new VCam();
		mouseinput = new MouseInput(cam);
		handler = new Handler(mouseinput, cam);
		
		this.addMouseListener(mouseinput);
		this.addMouseMotionListener(mouseinput);
		frame.addWindowListener(windowlistener);
		
		if(new Integer(JOptionPane.showConfirmDialog(null, "Do you want to run the server?", "Hosting", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION) {
			isHost = true;
			server = new Server(handler);
			server.start();
		}else{
			isHost = false;
			client = new Client(JOptionPane.showInputDialog("Ip Address: ", "localhost"), handler);
			client.start();
			client.sendData(("00;"+handler.player.getUsername()+";").getBytes());
		}
	
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		System.out.println("stoping application");
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void loadFrame(String titel) {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.requestFocus();
		frame.setVisible(true);
		frame.add(this);
		start();
	}
	
	public static int random(int max, int min) {
		int range = (max - min)+1;
		return (int) ((Math.random()*range)+min);
	}
		
	public static void main(String[] e) { new Main(); }
}
