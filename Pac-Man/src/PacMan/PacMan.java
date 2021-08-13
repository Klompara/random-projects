package PacMan;

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

import PacMan.GFX.VCam;
import PacMan.GFX.GameStates.GUI;
import PacMan.GFX.GameStates.Lobby;
import PacMan.Input.KeyInput;
import PacMan.net.NetClient;
import PacMan.net.NetServer;

public class PacMan extends Canvas implements Runnable{
	public static boolean graphicboost = false;
	
	public static int HEIGHT;
	public static boolean running = false;
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH;
	
	public static GameStates GAMESTATE = GameStates.Menu;
	
	private JFrame frame;
	
	private Handler handler;
	
	private KeyInput keyinput;
	
	public VCam cam;
	
	private GUI gui;
	public static Lobby lobby;
	
	public Thread thread;
	
	public static NetClient netclient;
	public static NetServer netserver;
	
	public PacMan() {
		WIDTH = 672;
		HEIGHT = (int) (WIDTH * 1.2);
		
		loadFrame("gameee");
		start();
		running = true;
	}
	
	private void tick() {
		if(GAMESTATE == GameStates.Game){
			handler.tick();
			cam.tick();
			gui.tick();
		}
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
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(GAMESTATE != GameStates.Game){
			Rectangle r=new Rectangle(0,0,WIDTH,HEIGHT);
			RadialGradientPaint rgp=new RadialGradientPaint(new Point2D.Double(WIDTH/2,HEIGHT/2),HEIGHT-200,new float[]{0.0f,1.0f},new Color[]{Color.darkGray.brighter(),Color.black});
			g.setPaint(rgp);
	  		g.fill(r);
		}

		if(GAMESTATE == GameStates.Game){
			g.translate(-cam.x, -cam.y);
			handler.render(g);
			g.translate(cam.x, cam.y);
			gui.render(g);
		}else if(GAMESTATE == GameStates.Menu){
	  		
			g.setColor(Color.white);
			g.setFont(new Font("sansserif", 1, 72));
			g.drawString("Pac-HideN´Seek",(int) (WIDTH/2 - g.getFontMetrics().getStringBounds("Pac-HideN´Seek", g).getWidth()/2), 265);
			g.setFont(new Font("sansserif", 1, 32));
			g.drawString("drücke Start", (int) (WIDTH/2 - g.getFontMetrics().getStringBounds("press Start", g).getWidth()/2), 468);
		}else if(GAMESTATE == GameStates.Lobby) {
			lobby.render(g);
		}
		
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
				frame.setTitle("gameee - "+frames+" FPS, "+ticks + " ticks");
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	private synchronized void start() {
		handler = new Handler(this);
		keyinput = new KeyInput(handler);
		gui = new GUI(handler);
			
		this.addKeyListener(keyinput);
		
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		System.out.println("Stop");
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		running = false;
	}
	
	private void loadFrame(String titel) {
		frame = new JFrame(titel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(this);
	}
	
	public static int random(int max, int min) {
		int range = (max - min) + 1;
		return (int) ((Math.random()*range) + min);
	}
	public static void main(String[] args) { new PacMan(); }
}
