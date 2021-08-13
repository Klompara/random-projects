package Game.Input;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Game.Handler;
import Game.net.Client;

public class WindowListener extends WindowAdapter implements java.awt.event.WindowListener{
	
	private Client client;
	private Handler handler;
	public WindowListener(Client client, Handler handler) {
		this.client = client;
		this.handler = handler;
	}
	
	public void windowClosing(WindowEvent e) {
		// sendet disconnect packet
		client.sendData(("02;"+handler.getPlayer().getUsername()+";").getBytes());
		System.exit(0);
	}
}
