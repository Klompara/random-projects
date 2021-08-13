package terrain;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import models.RawModel;
import renderEngine.Loader;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.Maths;

public class Terrain {

	private static final float SIZE = 2048;
	
	private float[][] heights;
	
	private float x, z;
	private RawModel model;
	private TerrainTexturePack texturePack;
	private TerrainTexture blendMap;
	
	public Terrain(int gridX, int gridZ, Loader loader, TerrainTexturePack texturePack, TerrainTexture blendMap ) {
		this.x = SIZE * gridX;
		this.z = SIZE * gridZ;
		this.texturePack = texturePack;
		this.blendMap = blendMap;
		this.model = generateTerrain(loader);
	}

	private RawModel generateTerrain(Loader loader){
		HeightGenerator heightGenerator = new HeightGenerator();
		
		int VERTEX_COUNT = (int) (SIZE/8);
		
		heights = new float[VERTEX_COUNT][VERTEX_COUNT];
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT-1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
				float height = getHeight(j, i, heightGenerator);
				heights[j][i] = height;
				vertices[vertexPointer*3+1] = height;
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
				Vector3f normal = calculateNormals(j, i, heightGenerator);
				normals[vertexPointer*3] = normal.x;
				normals[vertexPointer*3+1] = normal.y;
				normals[vertexPointer*3+2] = normal.z;
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
				textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz=0;gz<VERTEX_COUNT-1;gz++){
			for(int gx=0;gx<VERTEX_COUNT-1;gx++){
				int topLeft = (gz*VERTEX_COUNT)+gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}
	
	private Vector3f calculateNormals(int x, int y, HeightGenerator generator) {
		float heightL = getHeight(x-1, y, generator);
		float heightR = getHeight(x+1, y, generator);
		float heightD = getHeight(x, y-1, generator);
		float heightU = getHeight(x, y+1, generator);
		Vector3f normal = new Vector3f(heightL - heightR, 2f, heightD - heightU);
		normal.normalise();
		return normal;
	}
	
	private float getHeight(int x, int y, HeightGenerator generator) {
		return generator.generateHeight(x, y);
	}
	
	public float getHeightsOfTerrain(float WorldX, float WorldZ) {
		float terrainX = WorldX - x;
		float terrainZ = WorldZ - z;
		float gridSqareSize = SIZE / ((float)heights.length - 1);
		int gridX = (int) Math.floor(terrainX / gridSqareSize);
		int gridZ = (int) Math.floor(terrainZ / gridSqareSize);
		if(gridX >= heights.length-1 || gridZ >= heights.length-1 || gridX < 0 || gridZ < 0) {
			return -Float.MAX_VALUE;
		}
		float xCoord = (terrainX % gridSqareSize)/gridSqareSize;
		float zCoord = (terrainZ % gridSqareSize)/gridSqareSize;
		float answer;
		if(xCoord <= (1-zCoord)) {
			answer = Maths
					.barryCentric(new Vector3f(0, heights[gridX][gridZ], 0), new Vector3f(1,
							heights[gridX + 1][gridZ], 0), new Vector3f(0,
							heights[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		} else {
			answer = Maths
					.barryCentric(new Vector3f(1, heights[gridX + 1][gridZ], 0), new Vector3f(1,
							heights[gridX + 1][gridZ + 1], 1), new Vector3f(0,
							heights[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		}
		return answer;
	}
	
	public float getSize() { return SIZE; }
	public float getX() { return x; } 
	public float getZ() { return z; } 
	public RawModel getModel() { return model; } 
	public TerrainTexturePack getTexturePack() { return texturePack; } 
	public TerrainTexture getBlendMap() { return blendMap; } 
}
