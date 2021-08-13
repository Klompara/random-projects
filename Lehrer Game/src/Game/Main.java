package Game;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Game.Entity.Handler;
import Game.Entity.Entitys.Player;
import Game.Entity.Entitys.Lehrer.ID;
import Game.Entity.Entitys.Upgrades.BiggerBullets;
import Game.Entity.Entitys.Upgrades.FastShoot;
import Game.Inputs.KeyInput;
import Game.Inputs.MouseInput;
import Game.gfx.HUD;
import Game.gfx.Spawner;
import Game.gfx.GameStates.Help;
import Game.gfx.GameStates.Intro;
import Game.gfx.GameStates.MainMenu;
import Game.gfx.GameStates.Options;

public class Main extends Canvas implements Runnable{
	public static boolean running = false;
	private Thread thread;
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 1024, HEIGHT = WIDTH / 14 *9, shootSpeed = 1;
	
	private Handler handler;
	private KeyInput keyinput;
	private MouseInput mouseinput;
	private Spawner spawner;
	
	private Intro intro;
	private MainMenu mainmenu;
	private Help help;
	private Options options;
	private HUD hud;
	
	public enum STATE{
		Intro(),
		MainMenu(),
		Help(),
		Options(),
		Game(),
	};
	public static STATE gameState = STATE.Intro;
	
	public Main(){
		start();
		loadFrame("Teacher Game :D");
		run();
	}
	
	private void tick(){
		switch(gameState){
			case Intro:intro.tick();
				break;
			case MainMenu: mainmenu.tick();
				break;
			case Help:
				break;
			case Options:
				break;
			case Game:	handler.tick();
						spawner.tick();
						mouseinput.tick();
				break;
			default: System.err.println("Kann das Game-State nicht laden! @tick");
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
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
		g.setColor(new Color(30, 144, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		switch(gameState){
			case Intro: intro.render(g);
				break;
			case MainMenu: mainmenu.render(g);
				break;
			case Help: help.render(g);
				break;
			case Options: options.render(g);
				break;
			case Game:	handler.render(g);
						spawner.render(g);
						hud.render(g);
				break;
			default: System.err.println("Kann das Game-State nicht laden! @render");
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
	
	public synchronized void start(){
		thread = new Thread();
		thread.start();
		running = true;
		
		handler = new Handler();
		intro = new Intro();
		mainmenu = new MainMenu();
		help = new Help();
		options = new Options();
		hud = new HUD(handler);
		mouseinput = new MouseInput(handler);
		keyinput = new KeyInput(handler, mouseinput, mainmenu, help, options);
		spawner = new Spawner(handler);
		
		this.addMouseListener(mouseinput);
		this.addMouseMotionListener(mouseinput);
		this.addKeyListener(keyinput);
		
		handler.entitys.add(new Player(WIDTH/2-16, HEIGHT/2-16, ID.Player, handler));
		handler.entitys.add(new FastShoot(100, 100, ID.FastShoot, handler));
		handler.entitys.add(new BiggerBullets(200, 100, ID.BiggerBullet, handler));
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFrame(String titel){
		JFrame frame = new JFrame(titel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public static int random(int max, int min){
		int Range = (max-min)+1;
		return (int) ((Math.random()*Range)+min);
	}
	public static AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	public static int clamp(int var, int min, int max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String[] args) { new Main(); }
}
