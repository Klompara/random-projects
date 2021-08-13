package PacMan.Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import PacMan.GameStates;
import PacMan.Handler;
import PacMan.PacMan;
import PacMan.GFX.GameStates.Lobby;
import PacMan.net.NetClient;
import PacMan.net.NetServer;
import PacMan.net.Packets.Packet00Login;

public class KeyInput extends KeyAdapter implements KeyListener{
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(PacMan.GAMESTATE == GameStates.Game) {
			if(key == KeyEvent.VK_A){ handler.player.keydown[0] = true; }
			if(key == KeyEvent.VK_W){ handler.player.keydown[1] = true; }
			if(key == KeyEvent.VK_D){ handler.player.keydown[2] = true; }
			if(key == KeyEvent.VK_S){ handler.player.keydown[3] = true; }
			if(key == KeyEvent.VK_F9){ if(!handler.hitboxes){ handler.hitboxes = true; }else{ handler.hitboxes = false; }}
		}else if(PacMan.GAMESTATE == GameStates.Menu){
			if(key == KeyEvent.VK_ENTER){
				//Custom button text
				Object[] options = {"Create Lobby",
				                    "Join Lobby"};
				int n = JOptionPane.showOptionDialog(null,
				    "Multiplayer",
				    "A Silly Question",
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[0]);
				
				String ipAdress = "localhost";
				if(n == 0) {
					PacMan.netserver = new NetServer();
					PacMan.netserver.start();
				}else if(n == 1) {
					ipAdress = JOptionPane.showInputDialog("IP Adresse");
				}
				PacMan.netclient = new NetClient(ipAdress);
				PacMan.netclient.start();
				
				Packet00Login loginPacket = new Packet00Login(JOptionPane.showInputDialog("Benutzername"));
				loginPacket.writeData(PacMan.netclient);
				
				PacMan.lobby = new Lobby(PacMan.netserver);
				
				PacMan.GAMESTATE = GameStates.Lobby;
			}
		}

		if(key == KeyEvent.VK_F8){ if(!PacMan.graphicboost){ PacMan.graphicboost = true; }else{PacMan.graphicboost = false;}}
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A){ handler.player.keydown[0] = false; }
		if(key == KeyEvent.VK_W){ handler.player.keydown[1] = false; }
		if(key == KeyEvent.VK_D){ handler.player.keydown[2] = false; }
		if(key == KeyEvent.VK_S){ handler.player.keydown[3] = false; }
	}
}
