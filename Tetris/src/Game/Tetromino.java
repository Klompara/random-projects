package Game;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;

public class Tetromino {

	private int shapes[][][];
	private int shape[][];
	private int potentialShape[][];
	private int[] topLeft;
	private int[] potentialTopLeft;
	private int tetrominoID;
	private Color blockColor;
	private Image blockImage;
	private int currentShapeNumber = 0;	
	
	public Tetromino(int tetrominoID, Color[] colors) {
		this.tetrominoID = tetrominoID;
		loadShapes();
		potentialShape = shape;
		
		blockColor = colors[tetrominoID+1];
		topLeft = new int[] {0, 4}; // column 0 row 5
		potentialTopLeft = topLeft;
		
		
		MixerFilter myFilter = new MixerFilter(new Color(blockColor.getRed(),blockColor.getGreen(),blockColor.getBlue(), 50));
		blockImage = TextureLoader.getImage("Block.png");
		blockImage = Toolkit.getDefaultToolkit().createImage(new
				FilteredImageSource(blockImage.getSource(), myFilter));
	}
	
	private void loadShapes() {
		if(tetrominoID == 0) {
			shapes = new int[][][] {
				{
					{1,1},
					{1,1}
				}
			};
			shape = shapes[0];
		}else if(tetrominoID == 1) {
			shapes = new int[][][] {
				{
					{0,2,2},
					{0,2,0},
					{0,2,0}
				},
				{
					{0,0,0},
					{2,2,2},
					{0,0,2}
				},
				{
					{0,2,0},
					{0,2,0},
					{2,2,0}
				},
				{
					{2,0,0},
					{2,2,2},
					{0,0,0}
				}
			};
			
			shape = shapes[0];
		}else if(tetrominoID == 2) {
			shapes = new int[][][] {
				{
					{3,3,0},
					{0,3,0},
					{0,3,0}
				},
				{
					{0,0,3},
					{3,3,3},
					{0,0,0}
				},
				{
					{0,3,0},
					{0,3,0},
					{0,3,3}
				},
				{
					{0,0,0},
					{3,3,3},
					{3,0,0}
				}
			};
			shape = shapes[0];
		}else if(tetrominoID == 3) {
			shapes = new int[][][] {
				{
					{4,0,0},
					{4,4,0},
					{0,4,0}
				},
				{
					{0,4,4},
					{4,4,0},
					{0,0,0}
				},
				{
					{0,4,0},
					{0,4,4},
					{0,0,4}
				},
				{
					{0,0,0},
					{0,4,4},
					{4,4,0}
				}
			};
			
			shape = shapes[0];
		}else if(tetrominoID == 4) {
			shapes = new int[][][] {
				{
					{0,5,0},
					{5,5,0},
					{5,0,0}
				},
				{
					{5,5,0},
					{0,5,5},
					{0,0,0}
				},
				{
					{0,0,5},
					{0,5,5},
					{0,5,0}
				},
				{
					{0,0,0},
					{5,5,0},
					{0,5,5}
				}
			};
			
			shape = shapes[0];
		}else if(tetrominoID == 5) {
			shapes = new int[][][] {
				{
					{0,6,0},
					{0,6,6},
					{0,6,0}
				},
				{
					{0,0,0},
					{6,6,6},
					{0,6,0}
				},
				{
					{0,6,0},
					{6,6,0},
					{0,6,0}
				},
				{
					{0,6,0},
					{6,6,6},
					{0,0,0}
				}
			};
			shape = shapes[0];
		}else if(tetrominoID == 6) {
			shapes = new int[][][]{
				{
					{0,7,0,0},
					{0,7,0,0},
					{0,7,0,0},
					{0,7,0,0}
				},
				{
					{0,0,0,0},
					{7,7,7,7},
					{0,0,0,0},
					{0,0,0,0}
				},
				{
					{0,0,7,0},
					{0,0,7,0},
					{0,0,7,0},
					{0,0,7,0}
				},
				{
					{0,0,0,0},
					{0,0,0,0},
					{7,7,7,7},
					{0,0,0,0}
				}
			};
			
			shape = shapes[0];
		}
		
	}
	
	public int getShapeWidth(int[][] array) {
		int maxWidth = 0;
		for(int i = 0; i < array.length;i++) {
			boolean foundBlock = false;
			int tempWidth = 0;
			for(int j = 0; j < array[i].length; j++) {
				if(!foundBlock)
				{
					tempWidth++;
				}
				else if(array[i][j] != 0) {
					tempWidth++;
				}
				if(array[i][j] != 0) {
					foundBlock = true;
					
				}
			}
			if(tempWidth > maxWidth)
				maxWidth = tempWidth;
		}
		//System.out.println("maxWidth: " + maxWidth);
		return maxWidth;
	}
	
	public int getShapeWidthFromRight(int[][] array) {
		int deletableColumns = 0;
		boolean foundBlock = false;
		for(int i = 0; i < array[0].length && !foundBlock; i++) {
			boolean isClearColumn = true;
			for(int j = 0; j < array.length; j++) {
				if(array[j][i] != 0) {
					isClearColumn = false;
					foundBlock = true;
				}
			}
			if(isClearColumn && !foundBlock)
				deletableColumns++;
		}
		System.out.println(deletableColumns);
		return deletableColumns;
	}
	
	
	public int getShapeHeight(int[][] array) {
		int maxHeight = array[0].length;
		for(int i = array.length-1; i > 0; i--) {
			boolean rowSpace = true;
			for(int j = 0; j < array[i].length; j++) {
				if(array[i][j] != 0)
					rowSpace = false;
			}
			if(rowSpace)
				maxHeight--;
			else
				i = 0;
		}
		return maxHeight;
	}
	
	
	public void switchShape(int i) {
		currentShapeNumber+=i;
		if(currentShapeNumber >= shapes.length)
			currentShapeNumber = 0;
		if(currentShapeNumber < 0)
			currentShapeNumber = shapes.length-1;
		potentialShape = shapes[currentShapeNumber];
	}
	
	public int[][] getPotentialShape() {
		return potentialShape;
	}

	public void setPotentialShape(int[][] potentialShape) {
		this.potentialShape = potentialShape;
	}

	public Image getBlockImage() {
		return blockImage;
	}

	public void setBlockImage(Image blockImage) {
		this.blockImage = blockImage;
	}

	public int[][] getShape() {
		return shape;
	}

	public void setShape(int[][] shape) {
		this.shape = shape;
	}

	public int[] getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(int[] topLeft) {
		this.topLeft = topLeft;
	}

	public int[] getPotentialTopLeft() {
		return potentialTopLeft;
	}

	public void setPotentialTopLeft(int[] potentialTopLeft) {
		this.potentialTopLeft = potentialTopLeft;
	}

}
