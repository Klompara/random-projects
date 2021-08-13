package Game.States.states;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Game.MainClass;
import Game.Entitys.Bloon;
import Game.Entitys.Tower;
import Game.Entitys.WayPoint;
import Game.States.State;
import Game.States.StateHandler;
import Game.States.StateTypes;

public class Play extends State {
	
	public List<Tower> towers = new ArrayList<Tower>();
	public List<WayPoint> wayPoints = new ArrayList<WayPoint>();
	public List<Bloon> bloons = new ArrayList<Bloon>();

	public Play(StateTypes state, StateHandler handler) {
		super(state, handler);
		bloons.add(new Bloon(handler, 0, 0));
		wayPoints.add(new WayPoint(handler, 100, 100, 0));
		Random r = new Random();
		for(int i = 0; i < 5; i++) {
			wayPoints.add(new WayPoint(handler, r.nextInt(MainClass.frameWidth), r.nextInt(MainClass.frameHeight), i+1));
		}
		towers.add(new Tower(handler, 200, 200, 1));
	}

	public void tick() {
		
		for(Bloon b : bloons) {
			b.tick();
		}
		
		for(Tower t : towers) {
			t.tick();
		}
	}

	public void render(Graphics2D g) {
		for(WayPoint p : wayPoints) {
			p.render(g);
		}
		
		for(Bloon b : bloons) {
			b.render(g);
		}
		
		for(Tower t : towers) {
			t.render(g);
		}
		
	

	}
}
