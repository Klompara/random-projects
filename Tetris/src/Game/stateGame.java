package Game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.FilteredImageSource;
import java.util.Random;

public class stateGame {

	private Color[] colors;
	
	private long lastTime;
	private long lastTimeInput;
	
	private Image imgBackgBlock;
	private Image[] imgBlocks = new Image[8];
	
	private int[][] blockMapLanded = new int[16][10];
	private int blockSize = (Main.WIDTH-100)/10;
	
	private int spacingX = 40;
	private int spacingY = 70;
	
	private Tetromino fallingBlock;
	private Tetromino ghostBlock;
	
	private int speed = 500;
	
	public stateGame(Color[] colors) {
		this.colors = colors;
		for(int y = 0; y < blockMapLanded.length; y++) {
			for(int x = 0; x < blockMapLanded[y].length; x++) {
				blockMapLanded[y][x] = 0;
			}
		}
		
		MixerFilter myFilter = new MixerFilter(new Color(100,100,100));
		imgBackgBlock = TextureLoader.getImage("BackgBlock.png");
		imgBackgBlock = Toolkit.getDefaultToolkit().createImage(new
				FilteredImageSource(imgBackgBlock.getSource(), myFilter));
		

		int randomNr = new Random().nextInt(7);
		fallingBlock = new Tetromino(randomNr, colors);
		ghostBlock = new Tetromino(randomNr, colors);
		
		for(int i = 0; i < imgBlocks.length; i++) {
			Color blockColor = colors[i];
			myFilter = new MixerFilter(new Color(blockColor.getRed(),blockColor.getGreen(),blockColor.getBlue(), 50));
			imgBlocks[i] = TextureLoader.getImage("Block.png");
			imgBlocks[i] = Toolkit.getDefaultToolkit().createImage(new
					FilteredImageSource(imgBlocks[i].getSource(), myFilter));
		}
	}
	
	public void tick() {
		if(lastTime == 0)
			lastTime = System.currentTimeMillis();
		if(lastTimeInput == 0)
			lastTimeInput = System.currentTimeMillis();
		
		if(lastTimeInput < System.currentTimeMillis()-speed/4) {
			lastTimeInput = System.currentTimeMillis();
			checkUserInput();
		}
		
		if(lastTime < System.currentTimeMillis()-speed) {
			
			boolean collide = false;
			
			for (int row = 0; row < fallingBlock.getShape().length; row++) {
			    for (int col = 0; col < fallingBlock.getShape()[row].length; col++) {
			        if (fallingBlock.getShape()[row][col] != 0) {
			        	//System.out.println(fallingBlock.getPotentialTopLeft()[0]+fallingBlock.getShape().length);
			        	if(fallingBlock.getPotentialTopLeft()[0]+fallingBlock.getShapeHeight(fallingBlock.getPotentialShape()) <= blockMapLanded.length) { // auf den boden
			        	//if(fallingBlock.getPotentialTopLeft()[0] < blockMapLanded.length) {
			        		if ((blockMapLanded[row + fallingBlock.getPotentialTopLeft()[0]][col + fallingBlock.getTopLeft()[1]]) != 0) {
				                collide = true;
				            }
			        	}else{
			        		collide = true;
			        	}
			        }
			    }
			}
			
			if(!collide) {
				fallingBlock.setTopLeft(new int[] { fallingBlock.getPotentialTopLeft()[0], fallingBlock.getTopLeft()[1] });
				fallingBlock.setPotentialTopLeft(new int[] {fallingBlock.getTopLeft()[0]+1, fallingBlock.getTopLeft()[1]});
			}else {
				colliding();
			}
			lastTime = System.currentTimeMillis();
			setGhostBlockPos();
		}
	}
	
	private void checkUserInput() {
		//System.out.println(KeyInput.currentKeyDown);
		if(KeyInput.currentKeyDown == 68 && KeyInput.isKeyDown && fallingBlock.getTopLeft()[1]+fallingBlock.getShapeWidth(fallingBlock.getPotentialShape()) < blockMapLanded[0].length) {
			boolean sidewayCollide = false;
			for(int y = 0; y < fallingBlock.getShape().length; y++) {
				for(int x = 0; x < fallingBlock.getShape()[y].length; x++) {
					if(fallingBlock.getShape()[y][x] != 0) {
						if(blockMapLanded[y+fallingBlock.getTopLeft()[0]][x+fallingBlock.getTopLeft()[1]+1] != 0) {
							sidewayCollide = true;
							//System.out.println("Colliding");
						}
					}
				}
			}
			if(!sidewayCollide && fallingBlock.getTopLeft()[1]+fallingBlock.getShapeWidth(fallingBlock.getPotentialShape()) <= blockMapLanded[0].length) {
				fallingBlock.setTopLeft(new int[] { fallingBlock.getTopLeft()[0], fallingBlock.getTopLeft()[1]+1 });
			}
			
		
		}else if(KeyInput.currentKeyDown == 65 && KeyInput.isKeyDown && fallingBlock.getTopLeft()[1]+fallingBlock.getShapeWidthFromRight(fallingBlock.getPotentialShape()) >= 0) {
			boolean sidewayCollide = false;
			for(int y = 0; y < fallingBlock.getShape().length; y++) {
				for(int x = 0; x < fallingBlock.getShape()[y].length; x++) {
					if(fallingBlock.getShape()[y][x] != 0) {
						if(blockMapLanded[y+fallingBlock.getTopLeft()[0]][x+fallingBlock.getTopLeft()[1]+fallingBlock.getShapeWidthFromRight(fallingBlock.getPotentialShape())-1] != 0) {
							sidewayCollide = true;
							//System.out.println("Colliding");
						}
					}
				}
			}
			if(!sidewayCollide && fallingBlock.getTopLeft()[1]+fallingBlock.getShapeWidthFromRight(fallingBlock.getPotentialShape()) >= 0) {
				fallingBlock.setTopLeft(new int[] { fallingBlock.getTopLeft()[0], fallingBlock.getTopLeft()[1]-1 });
			}
		}
		
		if(KeyInput.currentKeyDown == 87 && KeyInput.isKeyDown) {
			fallingBlock.switchShape(1);			
			
			boolean collideTurn = false;
			for(int y = 0; y < fallingBlock.getPotentialShape().length; y++) {
				for(int x = 0; x < fallingBlock.getPotentialShape()[y].length; x++) {
					if(fallingBlock.getPotentialShape()[y][x] != 0) {
						//System.out.println(x+fallingBlock.getShapeWidth(fallingBlock.getPotentialShape()));
						if(fallingBlock.getPotentialTopLeft()[0]+fallingBlock.getShapeHeight(fallingBlock.getPotentialShape()) >= blockMapLanded.length || fallingBlock.getTopLeft()[1]+fallingBlock.getShapeWidth(fallingBlock.getPotentialShape()) > blockMapLanded[1].length || fallingBlock.getTopLeft()[1]+fallingBlock.getShapeWidthFromRight(fallingBlock.getPotentialShape()) < 0) {
							collideTurn = true; // auserhalb des arrays
							//System.out.println("test");
						}
						else if(blockMapLanded[y+fallingBlock.getTopLeft()[0]][x+fallingBlock.getTopLeft()[1]] != 0) {
							collideTurn = true; // anderer Block im weg
						}
					}
						
				}
			}
			if(!collideTurn) {
				fallingBlock.setShape(fallingBlock.getPotentialShape());
			}else{
				fallingBlock.switchShape(-1);
			}
			
		}
		
		if(KeyInput.currentKeyDown == 83 && KeyInput.isKeyDown) {
			lastTime = System.currentTimeMillis()-speed*2;
		}
	}
	
	private void colliding() {
		for(int i = 0; i < fallingBlock.getShape().length; i++) {
			for(int j = 0; j < fallingBlock.getShape()[i].length; j++) {
				if(fallingBlock.getShape()[i][j] != 0)
					blockMapLanded[fallingBlock.getTopLeft()[0]+i][fallingBlock.getTopLeft()[1]+j] = fallingBlock.getShape()[i][j];
			}
		}
		speed-=5;
		int randomNr = new Random().nextInt(7);
		fallingBlock = new Tetromino(randomNr, colors);
		ghostBlock = new Tetromino(randomNr, colors);
		
		// check ob zeile(n) voll ist/sind
		for(int y = 0; y < blockMapLanded.length; y++) {
			boolean zeileVoll = true;
			for(int x = 0; x < blockMapLanded[y].length; x++) {
				if(blockMapLanded[y][x] != 0)
					zeileVoll = false;
			}
			
			if(zeileVoll) { // zeile i ist voll
				
			}
		}
	}
	
	private void setGhostBlockPos() {
		boolean collide = false;
		ghostBlock.setShape(fallingBlock.getShape());
		ghostBlock.setTopLeft(fallingBlock.getTopLeft());
		while(!collide) {
			collide = false;
			for (int row = 0; row < ghostBlock.getShape().length; row++) {
			    for (int col = 0; col < ghostBlock.getShape()[row].length; col++) {
			        if (ghostBlock.getShape()[row][col] != 0) {
			        	//System.out.println(fallingBlock.getPotentialTopLeft()[0]+fallingBlock.getShape().length);
			        	if(ghostBlock.getPotentialTopLeft()[0]+ghostBlock.getShapeHeight(ghostBlock.getPotentialShape()) <= blockMapLanded.length) { // auf den boden
			        	//if(fallingBlock.getPotentialTopLeft()[0] < blockMapLanded.length) {
			        		if ((blockMapLanded[row + ghostBlock.getPotentialTopLeft()[0]][col + ghostBlock.getTopLeft()[1]]) != 0) {
				                collide = true;
				            }
			        	}else{
			        		collide = true;
			        	}
			        }
			    }
			}
			
			if(!collide) {
				ghostBlock.setTopLeft(new int[] { ghostBlock.getPotentialTopLeft()[0], ghostBlock.getTopLeft()[1] });
				ghostBlock.setPotentialTopLeft(new int[] {ghostBlock.getTopLeft()[0]+1, ghostBlock.getTopLeft()[1]});
			}
		}
	}
	
	public void render(Graphics2D g) {
		//Hintergrund
		GradientPaint redtowhite = new GradientPaint(Main.WIDTH/3, 0, Color.gray, Main.WIDTH/10*6, Main.HEIGHT/10*8, Color.black);
		g.setPaint(redtowhite);
		g.fill(new Rectangle2D.Double(0, 0, Main.WIDTH, Main.HEIGHT));
		
		for(int y = 0; y < blockMapLanded.length; y++) {
			for(int x = 0; x < blockMapLanded[y].length; x++) {
				if(blockMapLanded[y][x] == 0) {
					g.drawImage(imgBackgBlock, spacingX+x*blockSize, spacingY+y*blockSize, blockSize, blockSize, null);
				}
				else {
					g.drawImage(imgBlocks[blockMapLanded[y][x]], spacingX+x*blockSize, spacingY+y*blockSize, blockSize, blockSize, null);
				}
				
				// BlockID anzeigen:
				/*
				g.setColor(Color.white);
				g.setFont(Main.font(20));
				g.drawString(Integer.toString(blockMapLanded[y][x]), x*blockSize+spacingX, y*blockSize+spacingY+30);
				*/
			}
		}
		
		for (int row = 0; row < fallingBlock.getShape().length; row++) {
		    for (int col = 0; col < fallingBlock.getShape()[row].length; col++) {
		        if (fallingBlock.getShape()[row][col] != 0) {
		        	g.drawImage(fallingBlock.getBlockImage(), spacingX+(fallingBlock.getTopLeft()[1]+col)*blockSize, spacingY+(fallingBlock.getTopLeft()[0]+row)*blockSize, blockSize, blockSize, null);
		        }
		     }
		}
		
		float opacity = 0.25f;
    	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		for (int row = 0; row < ghostBlock.getShape().length; row++) {
		    for (int col = 0; col < ghostBlock.getShape()[row].length; col++) {
		        if (ghostBlock.getShape()[row][col] != 0) {
		        	
		        	g.drawImage(ghostBlock.getBlockImage(), spacingX+(ghostBlock.getTopLeft()[1]+col)*blockSize, spacingY+(ghostBlock.getTopLeft()[0]+row)*blockSize, blockSize, blockSize, null);
		        }
		     }
		}
		opacity = 1f;
    	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
	}
	
}
