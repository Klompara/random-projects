package Game;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glVertex3f;
import Game.GameStates.States.Play;

public class Block {
	public int x, y, z;
	
	
	public Block(int x, int y, int z, int type, Play handler) {
		this.x = x;
		this.y = y;
		this.z = z;
		
			for(int i = 0; i < handler.blocks.size(); i++) {
				Block b = handler.blocks.get(i);
				if(x == b.x && y == b.y && z == b.z && b != this) {
					handler.blocks.remove(this);
				}
			}
		
	}
	
	public void draw() {
		glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f+x, 1.0f+y, 0.0f+z);          // Top Right Of The Quad (Top)
        glVertex3f( 0.0f+x, 1.0f+y, 0.0f+z);          // Top Left Of The Quad (Top)
        glVertex3f( 0.0f+x, 1.0f+y, 1.0f+z);          // Bottom Left Of The Quad (Top)
        glVertex3f( 1.0f+x, 1.0f+y, 1.0f+z);          // Bottom Right Of The Quad (Top)
        glColor3f(1.0f,0.5f,0.0f);          // Set The Color To Orange
        glVertex3f( 1.0f+x, 0.0f+y, 1.0f+z);          // Top Right Of The Quad (Bottom)
        glVertex3f( 0.0f+x, 0.0f+y, 1.0f+z);          // Top Left Of The Quad (Bottom)
        glVertex3f( 0.0f+x, 0.0f+y, 0.0f+z);          // Bottom Left Of The Quad (Bottom)
        glVertex3f( 1.0f+x, 0.0f+y, 0.0f+z);          // Bottom Right Of The Quad (Bottom)
        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f( 1.0f+x, 1.0f+y, 1.0f+z);          // Top Right Of The Quad (Front)
        glVertex3f( 0.0f+x, 1.0f+y, 1.0f+z);          // Top Left Of The Quad (Front)
        glVertex3f( 0.0f+x, 0.0f+y, 1.0f+z);          // Bottom Left Of The Quad (Front)
        glVertex3f( 1.0f+x, 0.0f+y, 1.0f+z);          // Bottom Right Of The Quad (Front)
        glColor3f(1.0f,1.0f,0.0f);          // Set The Color To Yellow
        glVertex3f( 1.0f+x, 0.0f+y, 0.0f+z);          // Bottom Left Of The Quad (Back)
        glVertex3f( 0.0f+x, 0.0f+y, 0.0f+z);          // Bottom Right Of The Quad (Back)
        glVertex3f( 0.0f+x, 1.0f+y, 0.0f+z);          // Top Right Of The Quad (Back)
        glVertex3f( 1.0f+x, 1.0f+y, 0.0f+z);          // Top Left Of The Quad (Back)
        glColor3f(0.0f,0.0f,1.0f);          // Set The Color To Blue
        glVertex3f( 0.0f+x, 1.0f+y, 1.0f+z);          // Top Right Of The Quad (Left)
        glVertex3f( 0.0f+x, 1.0f+y, 0.0f+z);          // Top Left Of The Quad (Left)
        glVertex3f( 0.0f+x, 0.0f+y, 0.0f+z);          // Bottom Left Of The Quad (Left)
        glVertex3f( 0.0f+x, 0.0f+y, 1.0f+z);          // Bottom Right Of The Quad (Left)
        glColor3f(1.0f,0.0f,1.0f);          // Set The Color To Violet
        glVertex3f( 1.0f+x, 1.0f+y, 0.0f+z);          // Top Right Of The Quad (Right)
        glVertex3f( 1.0f+x, 1.0f+y, 1.0f+z);          // Top Left Of The Quad (Right)
        glVertex3f( 1.0f+x, 0.0f+y, 1.0f+z);          // Bottom Left Of The Quad (Right)
        glVertex3f( 1.0f+x, 0.0f+y, 0.0f+z);          // Bottom Right Of The Quad (Right)
	}
}
