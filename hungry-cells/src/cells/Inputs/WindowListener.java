package cells.Inputs;

import java.awt.event.WindowEvent;

import cells.Main.Main;

public class WindowListener implements java.awt.event.WindowListener{
	
	public WindowListener() {
	}

	public void windowActivated(WindowEvent e) { }
	public void windowClosed(WindowEvent e) { }

	public void windowClosing(WindowEvent e) {
		if(!Main.isHost) {
			Main.client.sendData("02".getBytes());
		}
	}

	public void windowDeactivated(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) { }
	public void windowIconified(WindowEvent e) { }
	public void windowOpened(WindowEvent e) { }
}
