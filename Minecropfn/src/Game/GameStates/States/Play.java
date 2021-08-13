package Game.GameStates.States;

import static org.lwjgl.opengl.GL11.*;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import Game.Block;
import Game.Camera;
import Game.Main;

public class Play {

	private Camera cam;

	private float movespeed;
	private int mouseSpeed = 1;

	public ArrayList<Block> blocks;

	public Play() {
		blocks = new ArrayList<Block>();
		blocks.add(new Block(0, 0, 0, 0, this));

		movespeed = 0.1f;

		cam = new Camera(110, Display.getWidth() / Display.getHeight(), 0.1f, 100);
	}

	public void update() {
		checkKeys();
		cam.useView();

		if (blocks.size() < 30 * 30 * 30 * 30) {
			blocks.add(new Block(Main.random(30, 0), Main.random(30, 0), Main.random(30, 0), 1, this));
		}

		glPushMatrix();
		{
			glTranslated(0, 0, 0);
			glBegin(GL_QUADS);
			{
				for (Block b : blocks) {
					b.draw();
				}
			}
			glEnd();

			glLineWidth(3);

			glBegin(GL_LINES);
			{
				glColor3f(1f, 1f, 1f);

				glVertex3f(0, 0, 0);
				glVertex3f(0, 0, 100);

				glVertex3f(0, 0, 0);
				glVertex3f(0, 100, 0);

				glVertex3f(0, 100, 0);
				glVertex3f(0, 100, 100);

				glVertex3f(0, 100, 0);
				glVertex3f(100, 100, 0);

				glVertex3f(0, 0, 0);
				glVertex3f(100, 0, 0);

				glVertex3f(100, 0, 0);
				glVertex3f(100, 0, 100);

				glVertex3f(0, 0, 100);
				glVertex3f(100, 0, 100);

				glVertex3f(100, 100, 0);
				glVertex3f(100, 100, 100);

				glVertex3f(0, 100, 100);
				glVertex3f(100, 100, 100);

				glVertex3f(100, 0, 100);
				glVertex3f(100, 100, 100);

				glVertex3f(100, 0, 0);
				glVertex3f(100, 100, 0);

				glVertex3f(0, 0, 100);
				glVertex3f(0, 100, 100);
			}
			glEnd();
		}
		glPopMatrix();
	}

	private void checkKeys() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					if (Mouse.isGrabbed())
						Mouse.setGrabbed(false);
					else {
						Display.destroy();
						System.exit(0);
					}
				}

				if (Keyboard.getEventKey() == Keyboard.KEY_F11) {
					try {
						if (!Display.isFullscreen()) {
							Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());

						} else {
							Display.setFullscreen(!Display.isFullscreen());
							Display.setDisplayMode(new DisplayMode(800, 600));
						}

						cam.initProjection();
						glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
						glLoadIdentity();
						glViewport(0, 0, Display.getWidth(), Display.getHeight());
					} catch (LWJGLException ex) {
						ex.printStackTrace();
					}
				}

				if (Keyboard.getEventKey() == Keyboard.KEY_LCONTROL) {
					movespeed = 0.5f;
				}
			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_LCONTROL) {
					movespeed = 0.1f;
				}
			}
		}

		while (Mouse.next()) {
			if (Mouse.isButtonDown(0) && !Mouse.isGrabbed()) {
				Mouse.setGrabbed(true);
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			cam.setY(cam.getY() - 0.03f - movespeed);
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			cam.setY(cam.getY() + 0.03f + movespeed);
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			cam.move(movespeed, 1);
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
			cam.move(-movespeed, 1);
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			cam.move(movespeed, 0);
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			cam.move(-movespeed, 0);
		if (Mouse.isGrabbed()) {
			float mouseDX = Mouse.getDX() * mouseSpeed * 0.16f;
			float mouseDY = Mouse.getDY() * mouseSpeed * 0.16f;
			cam.rotatey(mouseDX);
			cam.rotatex(-mouseDY);
		}
	}
}
