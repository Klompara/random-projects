package Programm;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static boolean running = false;
	public static final int WIDTH = 230, HEIGHT = WIDTH/12*9;
	
	private Thread thread;
	
	private LinkedList<Integer> Eingaben = new LinkedList<Integer>();
	private LinkedList<Integer> Zufall = new LinkedList<Integer>();
	
	private int Punkte = 0;
	
	public Main(){
		start();
		loadJFrame("Lotto");
		run();
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
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		// RENDER
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.cyan);
		g.setFont(new Font("sanserif",1,20));
		g.drawString("Punkte: "+Punkte, 20, 20);
		for(int i = 0; i < Zufall.size(); i++)
			g.drawString(""+Zufall.get(i), 20+i*30, 40);
		
		g.dispose();
		bs.show();
	}

	private void tick() {

	}

	public synchronized void stop(){
		try { thread.join();
		} catch (InterruptedException e) { e.printStackTrace(); }
		running = false;
	}
	public synchronized void start(){
		thread = new Thread();
		thread.start();
		running = true;
		
		for(int i = 1; i < 7; i++){
			int eingabe = Integer.parseInt(JOptionPane.showInputDialog("Zahl: "+i, "Zahl"));
			if(!Eingaben.contains(eingabe))
				if(eingabe < 1 || eingabe > 45){ JOptionPane.showMessageDialog(null, "Die Zahl muss zwischen 1 und 45 liegen!", "Error", JOptionPane.ERROR_MESSAGE); i--; }
				else{
					Eingaben.add(eingabe);
				}
			else{
				i--;
				JOptionPane.showMessageDialog(null, "Die eingegebene Zahl wurde schon eingegeben!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		for(int i = 0; i < 6; i++){
			int zahl = (rand(1, 45));
			if(!Zufall.contains(zahl))Zufall.add(zahl);
		}
		
		for(int i = 0; i < Eingaben.size(); i++){
			int inte = Eingaben.get(i);
			if(Zufall.contains(inte)){
				Punkte++;
			}
		}
		System.out.println("Punkte: "+Punkte);
	}
	
	public void loadJFrame(String titel){
		JFrame frame = new JFrame(titel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(this);
	}
	
	public int rand(int min, int max) {
		int range = (max - min) +1;
		return (int) ((Math.random() * range) + min);
	}
	
	public static void main(String args[]){ new Main(); }
}