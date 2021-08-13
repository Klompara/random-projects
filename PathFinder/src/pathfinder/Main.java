package pathfinder;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1200, HEIGHT = 1200;
	private JFrame frame;
	public static boolean running = false;
	public Thread thread;
	
	private Quadrat[][] maze;
	
	private boolean finished = false;
	private static boolean shownumbers = false;
	private int quadratsize = 2;
	
	public Main() {
		frame = new JFrame("Path finder");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		frame.add(this);
		frame.setVisible(true);
		
		maze = loadMaze(maze, "/maze2.png");
		
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	private void tick() {
		if(!finished)
			calculate();
	}
	
	private void calculate() {
		for(int y = 0; y < maze.length; y++) {
			for(int x = 0; x < maze[y].length; x++) {
				if(maze[y][x].status == 1) {
					
					maze[y][x].status = 3; // dass er nicht mehr gechecked wird
					
					if(y-1 >= 0 && y-1 < maze.length && x >= 0 && x < maze[y-1].length) {
						if(maze[y-1][x].blocktyp == type.WEG && maze[y-1][x].status == 0) {
							maze[y-1][x].status = 2;
							maze[y-1][x].parent = maze[y][x];
						}else if(maze[y-1][x].blocktyp == type.ENDE) {
							//maze[y-1][x].status = 4;
							finished = true;
							maze[y-1][x].parent = maze[y][x];
						}
					}
					
					if(y+1 >= 0 && y+1 < maze.length && x >= 0 && x < maze[y+1].length) {
						if(maze[y+1][x].blocktyp == type.WEG && maze[y+1][x].status == 0) {
							maze[y+1][x].status = 2;
							maze[y+1][x].parent = maze[y][x];
						}else if(maze[y+1][x].blocktyp == type.ENDE) {
							//maze[y+1][x].status = 4;
							finished = true;
							maze[y+1][x].parent = maze[y][x];
						}
					}
					
					
					
					if(y >= 0 && y < maze.length && x-1 >= 0 && x-1 < maze[y].length) {
						if(maze[y][x-1].blocktyp == type.WEG && maze[y][x-1].status == 0) {
							maze[y][x-1].status = 2;
							maze[y][x-1].parent = maze[y][x];
						}else if(maze[y][x-1].blocktyp == type.ENDE) {
							//maze[y][x-1].status = 4;
							finished = true;
							maze[y][x-1].parent = maze[y][x];
						}
					}
					
					
					
					if(y >= 0 && y < maze.length && x+1 >= 0 && x+1 < maze[y].length) {
						if(maze[y][x+1].blocktyp == type.WEG && maze[y][x+1].status == 0) {
							maze[y][x+1].status = 2;
							maze[y][x+1].parent = maze[y][x];
						}else if(maze[y][x+1].blocktyp == type.ENDE) {
							//maze[y][x+1].status = 4;
							finished = true;
							maze[y][x+1].parent = maze[y][x];
						}
					}
					
					
					if(y+1 >= 0 && y+1 < maze.length && x+1 >= 0 && x+1 < maze[y+1].length) {
						if(maze[y+1][x+1].blocktyp == type.WEG && maze[y+1][x+1].status == 0) {
							maze[y+1][x+1].status = 2;
							maze[y+1][x+1].parent = maze[y][x];
						}else if(maze[y+1][x+1].blocktyp == type.ENDE) {
							//maze[y-1][x].status = 4;
							finished = true;
							maze[y-1][x].parent = maze[y][x];
						}
					}
					
					if(y+1 >= 0 && y+1 < maze.length && x-1 >= 0 && x-1 < maze[y+1].length) {
						if(maze[y+1][x-1].blocktyp == type.WEG && maze[y+1][x-1].status == 0) {
							maze[y+1][x-1].status = 2;
							maze[y+1][x-1].parent = maze[y][x];
						}else if(maze[y+1][x-1].blocktyp == type.ENDE) {
							//maze[y-1][x].status = 4;
							finished = true;
							maze[y-1][x].parent = maze[y][x];
						}
					}
					
					if(y-1 >= 0 && y-1 < maze.length && x+1 >= 0 && x+1 < maze[y-1].length) {
						if(maze[y-1][x+1].blocktyp == type.WEG && maze[y-1][x+1].status == 0) {
							maze[y-1][x+1].status = 2;
							maze[y-1][x+1].parent = maze[y][x];
						}else if(maze[y-1][x+1].blocktyp == type.ENDE) {
							//maze[y-1][x].status = 4;
							finished = true;
							maze[y-1][x].parent = maze[y][x];
						}
					}
					
					if(y-1 >= 0 && y-1 < maze.length && x-1 >= 0 && x-1 < maze[y-1].length) {
						if(maze[y-1][x-1].blocktyp == type.WEG && maze[y-1][x-1].status == 0) {
							maze[y-1][x-1].status = 2;
							maze[y-1][x-1].parent = maze[y][x];
						}else if(maze[y-1][x-1].blocktyp == type.ENDE) {
							//maze[y-1][x].status = 4;
							finished = true;
							maze[y-1][x].parent = maze[y][x];
						}
					}
					
				}
			}
		}
		
		for(int y = 0; y < maze.length; y++) {
			for(int x = 0; x < maze[y].length; x++) {
				if(maze[y][x].status == 2) maze[y][x].status = 1;
			}
		}
	}
	
	private Quadrat[][] loadMaze(Quadrat[][] array, String path) {
		BufferedImage level = null;
		try { level = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) { e.printStackTrace(); }
		
		int w = level.getWidth();
		int h = level.getHeight();
		
		array = new Quadrat[h][w];
		
		for(int xx = 0; xx < w; xx++){
			for(int yy = 0; yy < h; yy++){
				int Pixel = level.getRGB(xx, yy);
				int red = (Pixel >> 16) & 0xff;
				int green = (Pixel >> 8) & 0xff;
				int blue = (Pixel) & 0xff;
				
				if(red == 255 && green == 0   && blue == 0  ) array[yy][xx] = new Quadrat(xx, yy, type.ENDE);
				if(red == 0   && green == 255 && blue == 0  ) array[yy][xx] = new Quadrat(xx, yy, type.ANFANG);
				if(red == 255 && green == 255 && blue == 255) array[yy][xx] = new Quadrat(xx, yy, type.WEG);
				if(red == 0   && green == 0   && blue == 0  ) array[yy][xx] = new Quadrat(xx, yy, type.MAUER);
			}
		}
		return array;
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
		
		
		for(int y = 0; y < maze.length; y++) {
			for(int x = 0; x < maze[y].length; x++) {
				
				
				
				if(maze[y][x].blocktyp == type.ANFANG) {
					g.setColor(Color.green);
					g.fillRect(x*quadratsize, y*quadratsize, quadratsize, quadratsize);
				}
				if(maze[y][x].blocktyp == type.ENDE) {
					g.setColor(Color.red);
					g.fillRect(x*quadratsize, y*quadratsize, quadratsize, quadratsize);
					
					if(finished) {
						Quadrat parent = maze[y][x];
						while(parent.parent != null) {
							parent.blocktyp = type.ENDE;
							parent = parent.parent;
						}
					}
				}
				if(maze[y][x].blocktyp == type.MAUER) {
					g.setColor(Color.black);
					g.fillRect(x*quadratsize, y*quadratsize, quadratsize, quadratsize);
				}
				if(maze[y][x].blocktyp == type.WEG) {
					g.setColor(Color.white);
					if(maze[y][x].status == 1 || maze[y][x].status == 3) {
						g.setColor(Color.gray);
					}
					g.fillRect(x*quadratsize, y*quadratsize, quadratsize, quadratsize);
					if(shownumbers) {
						g.setColor(Color.blue);
						g.drawString("" + maze[y][x].status, x*10, y*10+10);
					}					
				}
			}
		}
		
		g.dispose();
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 120.0;
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
				System.out.println("fps: "+frames+", ticks: "+ticks);
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
	}
	
	
	private enum type {
		ANFANG,
		ENDE,
		MAUER,
		WEG
	}
	
	public class Quadrat {
		public int x, y;
		public Quadrat parent;
		public type blocktyp;
		public int status = 0;

		public Quadrat(int x, int y, type blocktyp) {
			this.x = x;
			this.y = y;
			this.blocktyp = blocktyp;
			if(blocktyp == type.ANFANG) status = 1;
		}
	}
	
	public static void main(String[] args) { new Main(); }
}
