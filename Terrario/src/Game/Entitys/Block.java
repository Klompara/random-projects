package Game.Entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Game.GameStates.States.State_Play;
import Game.Main.MainLoop;
import Game.gfx.Assets;

public class Block {
	private int x, y;
	private int width, height;
	private int type;
	private BufferedImage texture;
	private State_Play handler;
	
	private Block BlockLeft, BlockRight, BlockTop, BlockBottom;
	private boolean seeme = false;
	private boolean oldseeme = false;
	
	public Block(int x, int y, int type, State_Play handler) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.handler = handler;
		width = 16;
		height = 16;
	}
	
	public void tick() {
		if(!oldseeme && seeme) {
			oldseeme = seeme;
			blockupdate();
		}else if(oldseeme && !seeme) {
			oldseeme = seeme;
		}
	}
	
	public void blockupdate() {
		// Texture ausrichtung
		boolean TouchB = false, TouchL = false, TouchR = false, TouchT = false;
		for(Block b : handler.blocks) {
			if(b != this) {
				if(x-width == b.getX() && y == b.getY()){ TouchL = true; BlockLeft = b; }
				if(x+width == b.getX() && y == b.getY()){ TouchR = true; BlockRight = b; }
				if(y-height == b.getY()&& x == b.getX()){ TouchT = true; BlockTop = b; }
				if(y+height == b.getY()&& x == b.getX()){ TouchB = true; BlockBottom = b; }

				if(type == 1) {
					int texindex = 0;
					
					if(TouchB && TouchT && TouchL && TouchR) {int[] num = {14, 15, 16}; texindex = rcf(num);}
					if(!TouchB && TouchT && TouchL && TouchR) {int[] num = {27, 28, 29}; texindex = rcf(num);}
					if(TouchB && !TouchT && TouchL && TouchR) {int[] num = {1, 2, 3}; texindex = rcf(num);}
					if(TouchB && TouchT && !TouchL && TouchR) {int[] num = {0, 13, 26}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && !TouchR) {int[] num = {4, 17, 30}; texindex = rcf(num);}
					if(TouchB && !TouchT && !TouchL && TouchR) {int[] num = {39, 41, 43}; texindex = rcf(num);}
					if(TouchB && !TouchT && TouchL && !TouchR) {int[] num = {40, 42, 44}; texindex = rcf(num);}
					if(!TouchB && TouchT && TouchL && !TouchR) {int[] num = {53, 55, 57}; texindex = rcf(num);}
					if(!TouchB && TouchT && !TouchL && TouchR) {int[] num = {52, 54, 56}; texindex = rcf(num);}
					if(TouchB && !TouchT && !TouchL && !TouchR) {int[] num = {6, 7, 8}; texindex = rcf(num);}
					if(!TouchB && TouchT && !TouchL && !TouchR) {int[] num = {45, 46, 47}; texindex = rcf(num);}
					if(!TouchB && !TouchT && TouchL && !TouchR) {int[] num = {12, 25, 38}; texindex = rcf(num);}
					if(!TouchB && !TouchT && !TouchL && TouchR) {int[] num = {9, 22, 35}; texindex = rcf(num);}
					if(TouchB && TouchT && !TouchL && !TouchR) {int[] num = {5, 18, 31}; texindex = rcf(num);}
					if(!TouchB && !TouchT && TouchL && TouchR) {int[] num = {58, 59, 60}; texindex = rcf(num);}
					if(!TouchB && !TouchT && !TouchL && !TouchR) {int[] num = {48, 49, 50}; texindex = rcf(num);}

					texture = Assets.texture_1[texindex];
				}else{
					int texindex = 79;
					
					if(TouchB && TouchT && TouchL && TouchR &&		checkt(5)) 								{int[] num = {17, 18, 19}; texindex = rcf(num);}
					if(!TouchB && TouchT && TouchL && TouchR &&		checkt(2))	{int[] num = {33, 34, 35}; texindex = rcf(num);}
					if(TouchB && !TouchT && TouchL && TouchR &&		checkt(1))	{int[] num = {1, 2, 3}; texindex = rcf(num);}
					if(TouchB && TouchT && !TouchL && TouchR &&		checkt(4))	{int[] num = {0, 16, 32}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && !TouchR &&		checkt(3))	{int[] num = {4, 20, 36}; texindex = rcf(num);}
					if(TouchB && !TouchT && !TouchL && TouchR)					{int[] num = {48, 50, 52}; texindex = rcf(num);}
					if(TouchB && !TouchT && TouchL && !TouchR)					{int[] num = {49, 51, 53}; texindex = rcf(num);}
					if(!TouchB && TouchT && TouchL && !TouchR)					{int[] num = {65, 67, 69}; texindex = rcf(num);}
					if(!TouchB && TouchT && !TouchL && TouchR)					{int[] num = {64, 66, 68}; texindex = rcf(num);}
					if(TouchB && !TouchT && !TouchL && !TouchR &&	checkt(1))								{int[] num = {6, 7, 8}; texindex = rcf(num);}
					if(!TouchB && TouchT && !TouchL && !TouchR &&	checkt(2))								{int[] num = {54, 55, 56}; texindex = rcf(num);}
					if(!TouchB && !TouchT && TouchL && !TouchR &&	checkt(3))								{int[] num = {12, 28, 44}; texindex = rcf(num);}
					if(!TouchB && !TouchT && !TouchL && TouchR &&	checkt(4))								{int[] num = {9, 25, 41}; texindex = rcf(num);}
					if(TouchB && TouchT && !TouchL && !TouchR &&	checkt(1) && checkt(2))					{int[] num = {5, 21, 37}; texindex = rcf(num);}
					if(!TouchB && !TouchT && TouchL && TouchR &&	checkt(3) && checkt(4))					{int[] num = {70, 71, 72}; texindex = rcf(num);}
					if(!TouchB && !TouchT && !TouchL && !TouchR)											{int[] num = {57, 58, 59}; texindex = rcf(num);}
					
					if(TouchB && TouchT && TouchL && TouchR &&	 	!checkt(5))											{int[] num = {184, 183, 182}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	!checkt(1) && checkt(2)  && checkt(3) && checkt(4))	{int[] num = {88, 89, 90}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	checkt(1) && !checkt(2)  && checkt(3) && checkt(4))	{int[] num = {104, 105, 106}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	checkt(1) && checkt(2)  && !checkt(3) && checkt(4))	{int[] num = {121, 137, 153}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	checkt(1) && checkt(2)  && checkt(3) && !checkt(4))	{int[] num = {120, 136, 152}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	!checkt(1) && !checkt(2)  && checkt(3) && checkt(4)){int[] num = {168, 169, 170}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	checkt(1) && checkt(2)  && !checkt(3) && !checkt(4)){int[] num = {122, 138, 154}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	checkt(1) && !checkt(2)  && !checkt(3) && checkt(4)){int[] num = {82, 114, 146}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	checkt(1) && !checkt(2)  && checkt(3) && !checkt(4)){int[] num = {83, 115, 147}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	!checkt(1) && checkt(2)  && !checkt(3) && checkt(4)){int[] num = {98, 130, 162}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	!checkt(1) && checkt(2)  && checkt(3) && !checkt(4)){int[] num = {99, 131, 163}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	checkt(1)  && !checkt(2) && !checkt(3) && !checkt(4)){int[] num = {91, 107, 123}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	!checkt(1) && checkt(2)  && !checkt(3) && !checkt(4)){int[] num = {139, 155, 171}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	!checkt(1) && !checkt(2) &&  checkt(3) && !checkt(4)){int[] num = {140, 156, 172}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && TouchR &&	 	!checkt(1) && !checkt(2) && !checkt(3) &&  checkt(4)){int[] num = {92, 108, 124}; texindex = rcf(num);}
					if(!TouchB && TouchT && TouchL && TouchR &&	 	!checkt(2) && !checkt(3) && !checkt(4))				{int[] num = {204, 220, 236}; texindex = rcf(num);}
					if(TouchB && !TouchT && TouchL && TouchR &&		!checkt(1) && !checkt(3) && !checkt(4))				{int[] num = {205, 221, 237}; texindex = rcf(num);}
					if(TouchB && TouchT && !TouchL && TouchR &&		!checkt(2) && !checkt(1) && !checkt(4))				{int[] num = {207, 223, 239}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && !TouchR &&		!checkt(2) && !checkt(3) && !checkt(1)) 			{int[] num = {206, 222, 238}; texindex = rcf(num);}
					if(TouchB && !TouchT && !TouchL && !TouchR && 	!checkt(1))											{int[] num = {86, 102, 118}; texindex = rcf(num);}
					if(!TouchB && TouchT && !TouchL && !TouchR && 	!checkt(2))											{int[] num = {134,150, 166}; texindex = rcf(num);}
					if(!TouchB && !TouchT && TouchL && !TouchR && 	!checkt(3))											{int[] num = {208,209, 210}; texindex = rcf(num);}
					if(!TouchB && !TouchT && !TouchL && TouchR && 	!checkt(4))											{int[] num = {211,212, 213}; texindex = rcf(num);}
					if(TouchB && TouchT && !TouchL && !TouchR && 	!checkt(1) && !checkt(2))							{int[] num = {198,214, 230}; texindex = rcf(num);}
					if(!TouchB && !TouchT && TouchL && TouchR && 	!checkt(3) && !checkt(4))							{int[] num = {185,186, 187}; texindex = rcf(num);}
					
					if(TouchB && TouchT && !TouchL && !TouchR && 	checkt(1) && !checkt(2))							{int[] num = {135,151, 167}; texindex = rcf(num);}
					if(TouchB && TouchT && !TouchL && !TouchR && 	!checkt(1)&&  checkt(2))							{int[] num = {87 ,103, 119}; texindex = rcf(num);}
					if(!TouchB && !TouchT && TouchL && TouchR && 	checkt(3) && !checkt(4))							{int[] num = {227,228, 229}; texindex = rcf(num);}
					if(!TouchB && !TouchT && TouchL && TouchR && 	!checkt(3)&&  checkt(4))							{int[] num = {224,225, 226}; texindex = rcf(num);}
					
					if(!TouchB && TouchT && TouchL && TouchR &&		!checkt(2) && (checkt(3) || checkt(4)))	{int[] num = {29, 30, 31}; texindex = rcf(num);}
					if(TouchB && !TouchT && TouchL && TouchR &&		!checkt(1) && (checkt(3) || checkt(4)))	{int[] num = {13, 14, 15}; texindex = rcf(num);}
					if(TouchB && TouchT && !TouchL && TouchR &&		!checkt(4) && (checkt(1) || checkt(2)))	{int[] num = {45, 46, 47}; texindex = rcf(num);}
					if(TouchB && TouchT && TouchL && !TouchR &&		!checkt(3) && (checkt(1) || checkt(2)))	{int[] num = {61, 62, 63}; texindex = rcf(num);}
					
					if(type == 2)texture = Assets.texture_2[texindex];
				}
			}
		}
	}
	
	private int rcf(int[] numbers) { // random chose from
		int rand = MainLoop.random(numbers.length-1, 0);
		return numbers[rand];
	}
	
	private boolean checkt(int key) {
		if(key == 1){ if(BlockBottom.getType() == type) return true; }
		if(key == 2){ if(BlockTop.getType() == type) return true; }
		if(key == 3){ if(BlockLeft.getType() == type) return true; }
		if(key == 4){ if(BlockRight.getType() == type) return true; }
		if(key == 5){
			if(BlockBottom.getType() == type && BlockTop.getType() == type && BlockLeft.getType() == type && BlockRight.getType() == type) {return true;
		}}
		return false;
	}
	
	public void render(Graphics2D g) {
		float diffx = x+width/2 - handler.player.getX()+16;
		float diffy = y+height/2 - handler.player.getY()+(48/2);
		float distance = (float) (Math.sqrt((diffx*diffx) + (diffy*diffy)));
		if(distance < MainLoop.WIDHT/4){
			g.drawImage(texture, x, y, null);
			int alpha = (int) (distance);
			if(alpha > 255) alpha = 255;
			g.setColor(new Color(0,0,0,alpha));
			g.fillRect(x, y, width, height);
			seeme = true;
		}else{
			seeme = false;
		}
	}
	
	public void remove() {
		handler.blocks.remove(this);
		for(Block b : handler.blocks) {
			if(x-width == b.getX() && y == b.getY()){ b.blockupdate(); }
			if(x+width == b.getX() && y == b.getY()){ b.blockupdate(); }
			if(y-height == b.getY()&& x == b.getX()){ b.blockupdate(); }
			if(y+height == b.getY()&& x == b.getX()){ b.blockupdate(); }
		}
	}
	
	public Rectangle2D BTop() { return new Rectangle(x+3, y, width-6, 3); }
	public Rectangle2D BBottom() { return new Rectangle(x+3, y+height-3, width-6, 3); }
	public Rectangle2D BRight() { return new Rectangle(x+width-3, y+3, 3, height-6); }
	public Rectangle2D BLeft() { return new Rectangle(x, y+3, 3, height-6); }

	public int getX() { return x; } 
	public void setX(int x) { this.x = x; } 

	public int getY() { return y; } 
	public void setY(int y) { this.y = y; } 

	public int getType() { return type; } 
	public void setType(int type) { this.type = type; } 
}
