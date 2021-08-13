package Game.GameStates.States;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Game.Game;
import Game.Loader;
import Game.GameStates.GameState;
import Game.GameStates.GameStates;
import Game.GameStates.Handler;
import Game.Input.MouseInput;
import Game.toolbox.Maths;

public class MainMenu extends GameState {
	
	private List<String> buttonsText = new ArrayList<String>();
	private List<Rectangle2D> buttonsBounds = new ArrayList<Rectangle2D>();
	private int hoveredButton = -1;
	
	public MainMenu(GameStates stateID) {
		super(stateID);
		buttonsText.add("Play");
		buttonsText.add("Instructions");
		buttonsText.add("Extras");
		buttonsText.add("Play more Games");
		buttonsText.add("");
		buttonsText.add("A game by Michael K.");
	}

	public void tick() {
		boolean hovering = false;
		for(int i = 0; i < buttonsBounds.size(); i++) {
			Rectangle2D r = buttonsBounds.get(i);
			if(r.contains(new Point(MouseInput.mouseX, MouseInput.mouseY))) {
				hoveredButton = i;
				hovering = true;
				if(MouseInput.isButtonDown) {
					MouseInput.isButtonDown = false;
					if(i == 0) {
						Handler.currentState = GameStates.Play;
					}else if(i == 1) {
						Handler.currentState = GameStates.Instruction;
					}else if(i == 2) {
						JOptionPane.showMessageDialog(null, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam\n"
														  + "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat,\n"
														  + "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.\n"
														  + "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor\n"
														  + "sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,\n"
														  + "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna");
					}else if(i == 3) {
				        String url = "https://www.dropbox.com/sh/51f2klzcl52wnuo/AABqnZ0rtmITlC-359VRfI4xa?dl=0";
				        JOptionPane.showMessageDialog(null, "I can't guarantee that all of these programms are working\non your PC and that they are all no viruses! :)");
				        if (Desktop.isDesktopSupported()) {
				        	// Windows
				            try {
								Desktop.getDesktop().browse(new URI(url));
							} catch (IOException | URISyntaxException e) {
								e.printStackTrace();
							}
				        } else {
				            // Ubuntu
				            Runtime runtime = Runtime.getRuntime();
				            try {
								runtime.exec("/usr/bin/firefox -new-window " + url);
							} catch (IOException e) {
								e.printStackTrace();
							}
				        }
					}else if(i == 5) {
						String url = "http://steamcommunity.com/id/0101111001011110/";
						if (Desktop.isDesktopSupported()) {
							// Windows
							try {
								Desktop.getDesktop().browse(new URI(url));
							} catch (IOException | URISyntaxException e) {
								e.printStackTrace();
							}
				        } else {
				            // Ubuntu
				            Runtime runtime = Runtime.getRuntime();
				            try {
								runtime.exec("/usr/bin/firefox -new-window " + url);
							} catch (IOException e) {
								e.printStackTrace();
							}
				        }
					}
				}
			}
		}
		if(!hovering) {
			hoveredButton = -1;
		}
	}

	public void render(Graphics2D g) {
		g.drawImage(Loader.getImage(0), // Sky
				-0 - MouseInput.mouseX/(Game.WIDTH/50),
				-0 - MouseInput.mouseY/(Game.HEIGHT/50),
				Game.WIDTH+(Game.WIDTH/10), Game.HEIGHT+(Game.HEIGHT/10), null);
		
		g.drawImage(Loader.getImage(1), // Hills
				-0 - MouseInput.mouseX/(Game.WIDTH/75),
				-0 - MouseInput.mouseY/(Game.HEIGHT/75),
				Game.WIDTH+(Game.WIDTH/10), Game.HEIGHT+(Game.HEIGHT/10), null);
		
		g.setFont(new Font(Font.SERIF, Font.PLAIN, 100));
		g.setColor(new Color(220, 220, 220, 180));
		g.fillRoundRect(Game.WIDTH/2 - Maths.getFontWidth("Age of War", g)/2-20,
				115, Maths.getFontWidth("Age of War", g)+30,
				120, 100, 100);

		
		g.setColor(Color.yellow.darker());
		g.drawString("Age of War", Game.WIDTH/2 - Maths.getFontWidth("Age of War", g)/2, 203);
		g.setColor(Color.yellow);
		g.drawString("Age of War", Game.WIDTH/2 - Maths.getFontWidth("Age of War", g)/2, 200);
		
		for(int i = 0; i < buttonsText.size(); i++) {
			int fontsize = 45;
			if(i == hoveredButton && i != buttonsText.size()-1) {
				fontsize += 5;
			}else if(i == hoveredButton && i == buttonsText.size()-1) {
				fontsize = 40;
			}else if(i == buttonsText.size()-1) {
				fontsize -= 10;
			}else{
				fontsize = 45;
			}
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontsize));
			String s = buttonsText.get(i);
			g.setColor(Color.gray);
			g.drawString(s, Game.WIDTH/2 - Maths.getFontWidth(s, g)/2, 324 + i*50);
			g.setColor(Color.white);
			g.drawString(s, Game.WIDTH/2 - Maths.getFontWidth(s, g)/2, 320 + i*50);
		}
		if(buttonsBounds.size() == 0) {
			for(int i = 0; i < buttonsText.size(); i++) {
				String s = buttonsText.get(i);
				buttonsBounds.add(new Rectangle(Game.WIDTH/2 - Maths.getFontWidth(s, g)/2, 320 + i*50-30, 
						Maths.getFontWidth(s, g), 30));
			}
		}
		
		/* Draw button bounds
		 * 		for(Rectangle2D r : buttonsBounds) { g.draw(r); }
		 */
	}
}
