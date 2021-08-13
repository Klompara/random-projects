package Game.GameState.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Game.MainClass;
import Game.TextureLoader;
import Game.GameState.GameState;
import Game.GameState.GameStates;
import Game.GameState.Handler;
import Game.Input.MouseInput;
import Game.Toolkit.Functions;
import Game.net.Client;

public class MainMenu extends GameState {

	private List<String> buttonTexts = new ArrayList<String>();
	private List<Rectangle> buttonBounds = new ArrayList<Rectangle>();
	private int hoveringButton = -1;
	
	public MainMenu(GameStates stateID, Handler handler) {
		super(stateID, handler);
		buttonTexts.add("Play");
		buttonTexts.add("Exit");
	}

	public void tick() {
		boolean isHovering = false;
		for(int i = 0; i < buttonBounds.size(); i++) {
			Rectangle r = buttonBounds.get(i);
			if(r.contains(new Point(MouseInput.mouseX, MouseInput.mouseY))) {
				hoveringButton = i;
				isHovering = true;
				if(MouseInput.isMousePressed) {
					MouseInput.isMousePressed = false;
					if(i == 0) { 		// Play
						boolean isHost = (JOptionPane.showConfirmDialog(null, "Do you want to create a lobby?", "Host", JOptionPane.YES_NO_OPTION) == 0);
						
						int port = -1;
						String s = null;
						while(port < 0 || port > 65535) {
							s = JOptionPane.showInputDialog("Port: ", "");
							if(!Functions.isInteger(s)){
								port = -1;
							}else{
								port = Integer.parseInt(s);
							}
						}
						
						if(isHost) {
							new Client(port);
						}else{
							new Client(JOptionPane.showInputDialog("IP-Adress: "), port);
						}
						handler.setCurrentState(GameStates.Play);
					}else if(i == 1) { 	// Exit
						MainClass.running = false;
					}
				}
			}
		}
		if(!isHovering) hoveringButton = -1;
	}

	public void render(Graphics2D g) {
		g.drawImage(TextureLoader.getImage("Background.png"), 0, 0, MainClass.WIDTH, MainClass.HEIGHT, null);
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, MainClass.WIDTH, MainClass.HEIGHT);
		g.drawImage(TextureLoader.getImage("BlobbyVolleyLogo.png"), 75, 75, null);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 45));
		for(int i = 0; i < buttonTexts.size(); i++) {
			if(i != hoveringButton) {
				g.setColor(Color.blue.brighter());
			}else{
				g.setColor(Color.blue.darker());
			}
			String s = buttonTexts.get(i);
			g.drawString(s, (int) (MainClass.WIDTH*0.6), 350 + (i*50));
			
			if(buttonBounds.size() != buttonTexts.size()) {
				buttonBounds.add(new Rectangle((int) (MainClass.WIDTH*0.6), 315 + (i*50),
						Functions.getFontWidth(g, s), Functions.getFontHeight(g, s)-10));
			}
		}
	}
}
