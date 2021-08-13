package net.Snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = -9182806748627671651L;
	public static final int WIDTH = 1024, HEIGHT = WIDTH / 12 * 9;
	public Thread thread;
	public boolean running = false;
	public boolean isHost;
	private int frames;
	private int fps;
	public double amountOfTicks = 20.0;
	public JFrame frame;
	public int SnakeWidth = 18;
	public int Abstand = 0;
	public int warteschlange = 0;
	LinkedList<SnakeTile>   Schlange     = new LinkedList<SnakeTile>();
	LinkedList<SnakeTileMP> SchlangeMP = new LinkedList<SnakeTileMP>();
	LinkedList<Apfel>       Apples           = new LinkedList<Apfel>();
	public static String Richtung = "Rechts";
	private Random r;
	private Menu menu;
	public static int minuten = 0;
	public static int sekunden = 0;
	private BufferedImage icon;
	private BufferedImage NikiHead;
	private BufferedImage Fäkalien;
	public static boolean SpecialEdition = false;
	public static String GameType = "Singleplayer";
	private int drehung = 0;
	
	public enum State {
		Menu,
		Game,
		GameOver,
		Pause;
	}
	public static State gameState;
	
	public Main() {
		try{
			//Lade Resourcen
			icon = ImageIO.read(getClass().getResourceAsStream("/snake-icon.png"));
			NikiHead = ImageIO.read(getClass().getResourceAsStream("/Niki.png"));
			Fäkalien = ImageIO.read(getClass().getResourceAsStream("/scheisse.png"));
		} catch(IOException e){
			e.printStackTrace();
		}


		
		JFrame frame = new JFrame("Snake!");
		frame.setIconImage(icon);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
		this.start();
		this.addKeyListener(new KeyInput());
		this.addMouseListener(new Menu(this));		

		gameState = State.Menu;
	}

	public synchronized void start(){
		menu = new Menu(this);
		
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
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		frames = 0;
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
				//System.out.println("FPS: " + amountOfTicks + " Ticks: "+frames);
				//Frames per seconds && updates per seconds
				fps = frames;
				frames = 0;
				if(gameState == State.Game){
					sekunden++;
					if(sekunden == 60){
						sekunden = 0;
						minuten++;
					}
				}
			}
		}
		stop();
	}
	
	private void tick(){
		
		if(gameState == State.Game){
			
			if(warteschlange != 0){
				Schlange.addFirst(new SnakeTile(0, 0));
				warteschlange--;
			}
				
			while(Schlange.size() <= 1){
				for(int i = 0; i < 3; i++){
					Schlange.add(new SnakeTile(64+((SnakeWidth+Abstand)*i), 32));
				}
			}
			
			
			if(SpecialEdition)drehung+=20;
			for(int i = 0; i < Schlange.size(); i++){
				SnakeTile schlange = Schlange.get(i);
				if(Schlange.getLast() == schlange){
					for(int i2 = 0; i2 < Apples.size(); i2++){
						Apfel apfel = Apples.get(i2);
						if(schlange.getBounds().intersects(apfel.getBounds())){
							Apples.remove(apfel);
							warteschlange++;
						}
					}
				}
			}
			
			
			SnakeTile tempObjectFirst = Schlange.getFirst();
			SnakeTile tempObjectLast = Schlange.getLast();
			Schlange.remove(tempObjectFirst);
			if(Richtung == "Rechts"){
				Schlange.add(new SnakeTile(tempObjectLast.x+(SnakeWidth+Abstand), tempObjectLast.y));
			}
			if(Richtung == "Links"){
				Schlange.add(new SnakeTile(tempObjectLast.x-(SnakeWidth+Abstand), tempObjectLast.y));
			}
			if(Richtung == "Hoch"){
				Schlange.add(new SnakeTile(tempObjectLast.x, tempObjectLast.y-(SnakeWidth+Abstand)));
			}
			if(Richtung == "Runter"){
				Schlange.add(new SnakeTile(tempObjectLast.x, tempObjectLast.y+(SnakeWidth+Abstand)));
			}
			KeyInput.gedrückt = false;
			
			r = new Random();
			if(Apples.size() == 0){
				for(int i2 = 0; i2 < 1; i2++)
					Apples.add(new Apfel(r.nextInt(WIDTH/(SnakeWidth*2))*(SnakeWidth*2), r.nextInt(HEIGHT/(SnakeWidth*2))*(SnakeWidth*2)));
				amountOfTicks++;
			}
			
			for(int i = 0; i < Apples.size(); i++){
				Apfel apfel = Apples.get(i);
				if(apfel.getBounds().intersects(new Rectangle(0, 0, 150, 90))){
					apfel.x = r.nextInt(WIDTH/(SnakeWidth*2))*(SnakeWidth*2);
					apfel.y = r.nextInt(HEIGHT/(SnakeWidth*2))*(SnakeWidth*2);
				}
			}
			for(SnakeTile tile : Schlange){
				if (tile.x < 0) {
					gameState = State.GameOver;
				}
				if (tile.x > WIDTH) {
					gameState = State.GameOver;
				}
				if (tile.y < 0){
					gameState = State.GameOver;
				}
				if (tile.y > HEIGHT) {
					gameState = State.GameOver;
				}
			}
		}
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
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		if(gameState == State.Game || gameState == State.Pause || gameState == State.GameOver){
			g.setColor(Color.cyan);
			for(int i = 0; i < 4; i++)
				g.draw3DRect(0+i, 0+i, 175-i*2, 100-i*2, true);
			g.setColor(Color.darkGray);
			
			for(SnakeTile tile : Schlange){
				tile.render(g);
				if(SpecialEdition){
					Graphics2D g2 = (Graphics2D) g;
					
					RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					g2.setRenderingHints(rh);
					AffineTransform at = new AffineTransform();
					at.setToTranslation(tile.x-SnakeWidth/2, tile.y-SnakeWidth/2); 				// position x,y
					at.rotate(Math.toRadians(drehung), NikiHead.getWidth()/2, NikiHead.getHeight()/2);		// rotation, set center rotation (30x30px picture)/2 = 15,15 "center"
					g2.drawImage(NikiHead, at, null); 
					Toolkit.getDefaultToolkit().sync();
				}
			}
			
			
			for(Apfel apfel : Apples){
				apfel.render(g);
			}
			
			// Schatten
			g.setColor(Color.gray);
			g.setFont(new Font("sansserif", 1, 20));
			g.drawString("Snake length: "+Schlange.size(), 7, 27);
			g.drawString("FPS: "+fps, 7, 52);
			if(sekunden < 10)
				g.drawString("Time: " + minuten + ":0" + sekunden, 7, 77);
			else
				g.drawString("Time: " + minuten + ":" + sekunden, 7, 77);
			
			g.setFont(new Font("sansserif", 1, 20));
			g.setColor(Color.white);
			g.drawString("Snake length: "+Schlange.size(), 5, 25);
			g.drawString("FPS: "+fps, 5, 50);
			if(sekunden < 10)
				g.drawString("Time: " + minuten + ":0" + sekunden, 5, 75);
			else
				g.drawString("Time: " + minuten + ":" + sekunden, 5, 75);

		}
		if(gameState == State.Menu || gameState == State.Pause || gameState == State.GameOver){
			menu.render(g);
		}

		g.dispose();
		bs.show();
	}
	
	public int rand(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
	
	//---------- SCHLANGEN ABTEILE ----------\\
	public class SnakeTile {
		public int x, y;
		private int red, green, blue;
		public SnakeTile(int x, int y) {
			this.x = x;
			this.y = y;
			green = 255;
			
			r = new Random();
			if(Schlange.size() >= 100){
				green = r.nextInt((255 - 230) + 230) + 1;
				red = r.nextInt((255 - 230) + 230) + 1;
				blue = r.nextInt((255 - 230) + 230) + 1;
			}
		}
		
		void render(Graphics g) {
			g.setColor(new Color(red, green, blue));
			if(Schlange.getLast() == this)
				g.fillRect(x, y, SnakeWidth+1, SnakeWidth+1);
			else
				g.drawRect(x, y, SnakeWidth, SnakeWidth);
			
			for(int i = 0; i < Schlange.size(); i++){
				SnakeTile tempObject = Schlange.get(i);
				if(tempObject != this){
					if(tempObject.getBounds().intersects(getBounds())){
						gameState = State.GameOver;
					}
				}
			}
		}
		
		Rectangle getBounds(){
			return new Rectangle(x, y, SnakeWidth, SnakeWidth);
		}
	}
	
	//---------- SCHLANGEN ABTEILE ----------\\
	// MP
	public class SnakeTileMP {
		public int x, y;
		private int red, green, blue;
		public SnakeTileMP(int x, int y) {
			this.x = x;
			this.y = y;
			blue = 255;
			
			r = new Random();
			if(Schlange.size() >= 100){
				green = r.nextInt((255 - 230) + 230) + 1;
				red = r.nextInt((255 - 230) + 230) + 1;
				blue = r.nextInt((255 - 230) + 230) + 1;
			}
		}
		
		void render(Graphics g) {
			g.setColor(new Color(red, green, blue));
			if(SchlangeMP.getLast() == this)
				g.fillRect(x, y, SnakeWidth+1, SnakeWidth+1);
			else
				g.drawRect(x, y, SnakeWidth, SnakeWidth);
			
			for(int i = 0; i < Schlange.size(); i++){
				SnakeTileMP tempObject = SchlangeMP.get(i);
				if(tempObject != this){
					if(tempObject.getBounds().intersects(getBounds())){
						gameState = State.GameOver;
					}
				}
			}

		}
		
		Rectangle getBounds(){
			return new Rectangle(x, y, SnakeWidth, SnakeWidth);
		}
	}
	
	//----------       Apfel       ----------\\
	public class Apfel {
		public int x, y;
		public Apfel(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		void render(Graphics g) {
			g.setColor(Color.red);
			if(SpecialEdition)
				g.drawImage(Fäkalien, x, y, SnakeWidth*2, SnakeWidth*2, null);
			else
				for(int i = 0; i < SnakeWidth/4; i++)
					g.draw3DRect(x+i, y+i, SnakeWidth*2-i*2, SnakeWidth*2-i*2, true);
		}
		
		Rectangle getBounds(){
			return new Rectangle(x, y, SnakeWidth*2, SnakeWidth*2);
		}
	}
	
	
	public static void main (String args[]){
		new Main();
	}
}
