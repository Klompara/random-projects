package Game.GameStates.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Game.Mainloop;
import Game.GameStates.StateHandler;
import Game.GameStates.StateHandler.GAMESTATES;

public class State_MainMenu {
	
	BufferedImage backg;
	private ArrayList<String> buttons;
	private int selected = 0;
	private int drehung, speed;
	private StateHandler handler;
	public State_MainMenu(StateHandler handler) {
		this.handler = handler;
		try {
			backg = ImageIO.read(getClass().getResourceAsStream("/images/MainBackg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttons = new ArrayList<String>();
		buttons.add("Play");
		buttons.add("Options");
		buttons.add("Exit");
	}
	
	public void tick() {
		drehung+=speed;
		if(selected > buttons.size()-1) selected = 0;
		if(selected < 0) selected = buttons.size()-1;
	}
	
	public void render(Graphics2D g) {
		double rotationRequired = Math.toRadians(drehung);
		double locationX = backg.getWidth() / 2;
		double locationY = backg.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		g.drawImage(op.filter(backg, null), Mainloop.WIDTH/2 - backg.getWidth()/2, Mainloop.HEIGHT/2 - backg.getHeight()/2, null);
		
		for(int i = 0; i < buttons.size(); i++) {
			String s = buttons.get(i);
			if(i == selected){
				g.setColor(Color.black);
			}else{
				g.setColor(new Color(0,0,0,255/4));
			}
			g.setFont(new Font("sansserif", 0, 60));
			g.drawString(s, 10, Mainloop.HEIGHT-(60*(i*-1 + 3)+60));
		}
	}
	
	public void next(int value) {
		if(value == 0) {
			if(selected == 0) handler.currentState = GAMESTATES.play;
			if(selected == 1) handler.currentState = GAMESTATES.options;
			if(selected == 2) System.exit(0);
		}else{
			selected+=value;
			speed+=value;
		}
	}
}
