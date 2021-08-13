package Game.GameStates.States;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import Game.Main;

public class Intro {
	
	private Texture introTexture;
	
	
	public Intro() {
		introTexture = Main.loadTexture("images/title/MK-logo");
	}
		
	public void update() {
		glBindTexture(GL_TEXTURE_2D, introTexture.getTextureID());
		introTexture.bind();
		glBegin(GL_TRIANGLES);
		 
		glTexCoord2f(1, 0);
		glVertex2i(450, 10);
		glTexCoord2f(0, 0);
		glVertex2i(10, 10);
		glTexCoord2f(0, 1);
		glVertex2i(10, 450);
		 
		glTexCoord2f(0, 1);
		glVertex2i(10, 450);
		glTexCoord2f(1, 1);
		glVertex2i(450, 450);
		glTexCoord2f(1, 0);
		glVertex2i(450, 10);
		 
		glEnd();
		
		introTexture.release();
	}
}
