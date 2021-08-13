package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Cell {
	private int x, y;
	private int gridX, gridY;
	private int ID;
	
	private boolean live = false;
	private boolean finallive = false;
	
	public Cell(int gridX, int gridY, int ID) {
		this.ID = ID;
		this.gridX = gridX;
		this.gridY = gridY;
		x = gridX * Main.CELL_SIZE;
		y = gridY * Main.CELL_SIZE;
	}
	
	public void tick(ArrayList<Cell> cells) {
		int liveSurround = 8;
		
		int[] neighbours = new int[8];
		
		neighbours[0] = ID+Main.COLLS-1;
		neighbours[1] = ID+Main.COLLS;
		neighbours[2] = ID+Main.COLLS+1;
		neighbours[3] = ID-1;
		neighbours[4] = ID+1;
		neighbours[5] = ID-Main.COLLS-1;
		neighbours[6] = ID-Main.COLLS;
		neighbours[7] = ID-Main.COLLS+1;
		
		for(int i = 0; i < neighbours.length; i++) {
			if(!Main.clamp(neighbours[i], 0, cells.size()-1)){ 
				liveSurround--;
			} else {
				if(!cells.get(neighbours[i]).isLive()) {
					liveSurround--;
				}
			}
		}

		if(liveSurround != 2 && liveSurround != 3) {
			finallive = false;
		} else if(liveSurround == 2) {
			finallive = live;
		} else if(liveSurround == 3) {
			finallive = true;
		}
	}
	
	public void setFinalLive() {
		live = finallive;
	}
	
	public void render(Graphics2D g) {
		if(live) {
			g.setColor(Color.yellow);
			g.fillRect(x, y, Main.CELL_SIZE, Main.CELL_SIZE);
		}else{
			g.setColor(Color.gray);
			g.fillRect(x, y, Main.CELL_SIZE, Main.CELL_SIZE);
		}
		g.setColor(Color.DARK_GRAY);
		g.drawRect(x, y, Main.CELL_SIZE, Main.CELL_SIZE);
	}
	
	public Rectangle bounds() {
		return new Rectangle(x, y, Main.CELL_SIZE, Main.CELL_SIZE);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getGridX() {
		return gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
}
