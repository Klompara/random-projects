package Game.gfx;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import Game.Entitys.Block;
import Game.GameStates.States.State_Play;
import Game.Main.MainLoop;

public class WorldGenerator {
	
	private State_Play handler;
	public WorldGenerator(State_Play handler){
		this.handler = handler;
	}
	
	public void generateworld() {
		for(int i = 0; i < 255; i++) {
			for(int j = 0; j < 25; j++) {
				handler.blocks.add(new Block(i*16, j*16, MainLoop.random(2, 1), handler));
			}
		}
		
		for(int i = 0; i < 25; i++) {
			int x = MainLoop.random(0, 255)*16;
			int y = MainLoop.random(-50,-50)*16;
			int width = MainLoop.random(30, 5)*16;
			int height = MainLoop.random(30, 5)*16;
			Ellipse2D e = new Ellipse2D.Double(x, y, width, height);
			
			ArrayList<Block> b = new ArrayList<Block>();
			for(int xx = 0; xx < width/16; xx+=16) {
				for(int yy = 0; yy < height/16; yy+=16) {
					if(!e.contains(new Rectangle(xx*16, yy*16, 16, 16))) {
						b.add(new Block(xx*16, yy*16, MainLoop.random(2, 1), handler));
						handler.blocks.add(new Block(xx*16, yy*16, MainLoop.random(2, 1), handler));
						System.out.println("x: "+xx*16+ ", y: "+ yy*1);
					}
				}
			}
			for(Block block1 : b) {
				boolean intersect = false;
				for(int j = 0; j < handler.blocks.size(); j++) {
					Block block2 = handler.blocks.get(j);
					if(block1.getX() == block2.getX() && block1.getY() == block2.getY()) {
						intersect = true;
					}
				}
				System.out.println(intersect);
				if(!intersect){
					handler.blocks.add(block1);
					System.out.println("x: "+block1.getX() + ", y: "+block1.getY());
				}else{
				}
			}
			b.clear();
		}		
	}
}
