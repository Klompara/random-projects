package net.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import net.Snake.Main.State;

public class KeyInput extends KeyAdapter{
	private boolean N = false;
	private boolean I1 = false;
	private boolean K = false;
	private boolean I2 = false;
	public static boolean gedrückt = false;
	
	public KeyInput(){
		
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(!gedrückt){
			if (Main.Richtung != "Rechts")
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){Main.Richtung = "Links";}
			if (Main.Richtung != "Links")
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){Main.Richtung = "Rechts";}
			if (Main.Richtung != "Hoch")
				if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){Main.Richtung = "Runter";}
			if (Main.Richtung != "Runter")
				if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP){Main.Richtung = "Hoch";}
			gedrückt = true;
		}
		if (key == KeyEvent.VK_F11){
			//Main.frame.setDefaultLookAndFeelDecorated(true);
		}
		if (key == KeyEvent.VK_ESCAPE){
			if(Main.gameState == State.Game)
				Main.gameState = State.Pause;
			if(Main.gameState == State.Menu || Main.gameState == State.GameOver)
				System.exit(0);
		}
		if (key == KeyEvent.VK_N){N = true;}
		if (key == KeyEvent.VK_I && N){I1 = true;}
		if (key == KeyEvent.VK_K && N && I1){K = true;}
		if (key == KeyEvent.VK_I && N && I1 && K){I2 = true;}
		if (I2){
			Main.SpecialEdition = true;
		}else{
			Main.SpecialEdition = false;
		}

		
		if (key == KeyEvent.VK_END){
			N = false;
			I1 = false;
			K = false;
			I2 = false;
			Main.SpecialEdition = false;
		}
	}
	
	public void keyReleased(KeyEvent e){
		
	}

}
