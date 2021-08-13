package pathfinder;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 4433339372853233415L;

	public static final int WIDTH = 1900, HEIGHT = WIDTH / 12 * 9;
	public Thread thread;
	public boolean running = false;

	private Field[][] fields = new Field[25][25];
	private List<Field> path = new LinkedList<Field>();

	public Main() {
		for (int y = 0; y < fields.length; y++) {
			for (int x = 0; x < fields[0].length; x++) {
				fields[y][x] = new Field(false, false, x, y);
				if(y == 5 && x < 20)
					fields[y][x].setWall(true);
			}
		}
		fields[0][0].setStart(true);
		fields[24][10].setEnd(true);
		loadFrame();
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 5;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void solve() {
		Field currentField = fields[0][0];
		Field endField = fields[24][10];
		Field fieldWithLowestF = null;
		int lowestF;
		List<Field> nearFields = new LinkedList<Field>();
		int gCounter = 0;
		
		for(int repeat = 30; repeat > 0; repeat--) {
			lowestF = 9999;
			nearFields.clear();
			gCounter++;
			if(currentField.getX()-1 > 0) {
				if(!fields[currentField.getX()-1][currentField.getY()].isWall())
					nearFields.add(fields[currentField.getX()-1][currentField.getY()]);
			}
			if(currentField.getX()+1 <= 24) {
				if(!fields[currentField.getX()+1][currentField.getY()].isWall())
					nearFields.add(fields[currentField.getX()+1][currentField.getY()]);
			}
			if(currentField.getY()-1 > 0) {
				if(!fields[currentField.getX()][currentField.getY()-1].isWall())
					nearFields.add(fields[currentField.getX()][currentField.getY()-1]);
			}
			if(currentField.getY()+1 <= 24) {
				if(!fields[currentField.getX()][currentField.getY()+1].isWall())
					nearFields.add(fields[currentField.getX()][currentField.getY()+1]);
			}
			for(Field field : nearFields) {
				if(!field.isOpened()) {
					field.setOpened(true);
					if(!field.isWall()) {
						int f; // addiert g und h
						int g; // wie vielter zug
						int hx, hy, h; // geschaetzte weite von anfang nach ende
						g = gCounter;
						hx = currentField.getX() - endField.getX();
						if(hx < 0) hx *= -1;
						hy = currentField.getY() - endField.getY();
						if(hy < 0) hy *= -1;
						h = hx+hy;
						System.out.println(h);
						f = g + h;
						field.setF(f);
						field.setG(g);
						field.setH(h);
						if(f <= lowestF) {
							fieldWithLowestF = field;
						}
					}
				}
			}
			currentField = fieldWithLowestF;
			path.add(currentField);
		}
	}

	boolean solved = false;
	private void tick() {
		if(!solved) {
			solve();
			solved = true;
		}
	}

	private void render() {
		// if(fieldCurrent != null)
		// System.out.println(fieldCurrent.getX() + " " + fieldCurrent.getY());
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(Color.darkGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (int y = 0; y < fields.length; y++) {
			for (int x = 0; x < fields[0].length; x++) {
				g.setColor(Color.white);
				if (fields[y][x].isStart())
					g.setColor(Color.green);
				if (fields[y][x].isEnd())
					g.setColor(Color.red);
				if (fields[y][x].isOpened())
					g.setColor(Color.lightGray);
				if(path.contains(fields[y][x])) 
					g.setColor(Color.blue);
				if(fields[y][x].isWall())
					g.setColor(Color.black);
				
				
				g.fillRect(x * 30, y * 30, 30, 30);
				g.setColor(Color.black);
				g.drawRect(x * 30, y * 30, 30, 30);
				if (fields[y][x].isOpened()) {
					g.setFont(new Font("", Font.PLAIN, 10));
					g.drawString(fields[y][x].getF() + "", x * 30, y * 30 + 10);
					g.drawString(fields[y][x].getG() + "", x * 30, y * 30 + 25);
					g.drawString(fields[y][x].getH() + "", x * 30 + 20, y * 30 + 25);
				}
			}
		}

		g.dispose();
		bs.show();
	}

	private void loadFrame() {
		JFrame frame = new JFrame("A* Pathfinder");
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
		this.start();
	}

	public static void main(String args[]) {
		new Main();
	}
}