package com.Game.net;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;


public class Game extends Applet implements Runnable{
	private static final long serialVersionUID = 3842083814021014550L;
	public final static int WIDTH = 1024, HEIGHT = WIDTH / 12 * 9;
	private Handler handler;
	private Menu menu;
	public static int sleeptime = 1;
	public static boolean entwickler = false;
	public static boolean burningballs = true;
	public static boolean Faden = true;
	private boolean running = true;
	//Für double buffering
	private Image i;
	private Graphics2D doubleG;
	
	//Menue
	public enum STATE {
		Menu,
		Game,
		GameOver,
		Win,
		Help
	};
	public static STATE gameState = STATE.Menu;
	
	public void init(){
		setSize(WIDTH, HEIGHT);
	}
	
	public void start() {
		/*
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image cursor = toolkit.getImage("riptosfilecorner.bplaced.net/Programms/cursor.png");
		Point cursorHotSpot = new Point(0, 0);
		Cursor customCursor = toolkit.createCustomCursor(cursor, cursorHotSpot, "Custom Cursor");
		setCursor(customCursor);
		*/
		
		Thread thread = new Thread(this);
		thread.start();
		handler = new Handler();
		this.addMouseListener(new Menu(handler));
		menu = new Menu(handler);
		this.addKeyListener(new KeyInput(handler));
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2-64/2, HEIGHT-32, ID.Player, handler));
			handler.addObject(new Ball(WIDTH/2-32/2, HEIGHT-128, ID.Ball, handler));
			
			for(int i = 0; i < 13; i++){
				handler.addObject(new Block(64*i+i*8+34, 60, ID.Block, new Color(0, 72, 153)));
			}
			for(int i = 0; i < 12; i++){
				handler.addObject(new Block(64*i+i*8+66, 120, ID.Block, new Color(255, 237, 0)));
			}
			for(int i = 0; i < 13; i++){
				handler.addObject(new Block(64*i+i*8+34, 180, ID.Block, new Color(227, 6, 19)));
			}
			for(int i = 0; i < 12; i++){
				handler.addObject(new Block(64*i+i*8+66, 240, ID.Block, new Color(0, 150, 64)));
			}
		}
	}
	public void run() {
		while(running){
			if(gameState == STATE.GameOver){
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					handler.object.remove(tempObject);
				}
			}
			if(gameState == STATE.Game){
				handler.tick();
				int Zaehler = 0;
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					if(tempObject.getID() == ID.Block){Zaehler++;}
				}
				if(Zaehler == 0){
					for(int i = 0; i < handler.object.size(); i++){
						GameObject tempObject = handler.object.get(i);
						handler.object.remove(tempObject);
					}
					gameState = STATE.Win;
				}
			}
			repaint();
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		running = false;
	}
	public void destroy(){
		running = false;
	}
	
	public void update(Graphics g) {
		i = createImage(this.getSize().width, this.getSize().height);
		doubleG = (Graphics2D) i.getGraphics();
		doubleG.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		doubleG.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);
		g.drawImage(i, 0, 0, this);
	}
	
	public void paint(Graphics2D g){
		if(gameState == STATE.Game)
			handler.render(g);
		else if(gameState == STATE.Menu || gameState == STATE.Help){
			menu.render(g);
		}
		else if(gameState == STATE.GameOver){
			g.setColor(Color.RED);
			g.setFont(new Font("sansserif", 1, 72));
			g.drawString("Game Over !", Game.WIDTH/2-200, 128);
			g.setColor(Color.blue);
			g.setFont(new Font("sansserif", 1, 20));
			g.drawString("Press Enter", Game.WIDTH/2-100, 300);
		}
		else if(gameState == STATE.Win){
			g.setColor(Color.green);
			g.setFont(new Font("sansserif", 1, 72));
			g.drawString("Congratulation!", Game.WIDTH/2-300, 150);
			g.setColor(Color.blue);
			g.setFont(new Font("sansserif", 1, 20));
			g.drawString("Press Enter", Game.WIDTH/2-100, 300);
		}
		if(entwickler){
			g.setFont(new Font("sansserif", 1, 10));
			g.setColor(Color.black);
			g.drawString("Entwickleroptionen: ON", WIDTH-130, HEIGHT-6);
		}
		g.setColor(Color.gray);
		g.drawRect(0, 0, Game.WIDTH-1, Game.HEIGHT-1);
		g.dispose();
	}
	
	public static int clamp(int var, int min, int max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	/* 
	 * Wenn du das liest,
	 * dann bist du ein Nerd.
	 * Und wenn du ein Nerd bist,
	 * dann gefällt dir das ^-^
	*/
}
