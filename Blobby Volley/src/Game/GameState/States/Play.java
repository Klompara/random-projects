package Game.GameState.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Game.MainClass;
import Game.TextureLoader;
import Game.Entitys.Player;
import Game.Entitys.PlayerMP;
import Game.GameState.GameState;
import Game.GameState.GameStates;
import Game.GameState.Handler;

public class Play extends GameState {

	private Player player;
	private PlayerMP playerMP;
	
	public Play(GameStates stateID, Handler handler) {
		super(stateID, handler);
	}

	public void tick() {
		if(player != null && playerMP != null) {
			player.tick();
			playerMP.tick();
		}
	}

	public void render(Graphics2D g) {
		g.drawImage(TextureLoader.getImage("Background.png"), 0, 0, null);
		if(player != null && playerMP != null) {
			player.render(g);
			playerMP.render(g);	
		}else{
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
			String s = "Nobody has connected :(";
			g.setColor(Color.black);
			g.drawString(s, (int) (MainClass.WIDTH/2-g.getFontMetrics().getStringBounds(s, g).getWidth()/2), 302);
			g.setColor(Color.white);
			g.drawString(s, (int) (MainClass.WIDTH/2-g.getFontMetrics().getStringBounds(s, g).getWidth()/2), 300);
		}
	}
}
