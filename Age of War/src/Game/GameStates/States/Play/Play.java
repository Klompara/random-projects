package Game.GameStates.States.Play;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import Game.Game;
import Game.Loader;
import Game.Entitys.Camera;
import Game.Entitys.Teacher;
import Game.GameStates.GameState;
import Game.GameStates.GameStates;
import Game.Input.MouseInput;

public class Play extends GameState {
	
	public List<Teacher> teacherHTL = new ArrayList<Teacher>();
	
	private Camera cam;
	
	public Play(GameStates stateID, Camera cam) {
		super(stateID);
		this.cam = cam;

	}

	public void tick() {
		cam.tick();
		for(Teacher t : teacherHTL) {
			t.tick();
		}
		if(MouseInput.isButtonDown) {
			MouseInput.isButtonDown = false;
			teacherHTL.add(new Teacher(MouseInput.mouseX, MouseInput.mouseY, 1, this) {});
		}
	}

	public void render(Graphics2D g) {
		g.translate(cam.getX(), cam.getY());

		for(int i = 0; i < 3; i++) {
			g.drawImage(Loader.getImage(0), (int) (0+i*Game.WIDTH-(cam.getX()/2)), 0, Game.WIDTH, Game.HEIGHT, null);
			g.drawImage(Loader.getImage(1), 0+i*Game.WIDTH, 0, Game.WIDTH, Game.HEIGHT, null);
		}
		
		for(Teacher t : teacherHTL) {
			t.render(g);
			System.out.println("test");
		}
		
		g.translate(-cam.getX(), -cam.getY());
	}
}
