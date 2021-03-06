package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import entities.Camera;
import entities.Entity;
import entities.Lamp;
import entities.Light;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import terrain.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import water.WaterFrameBuffers;
import water.WaterRenderer;
import water.WaterShader;
import water.WaterTile;

public class MainGameLoop {
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		MasterRenderer renderer = new MasterRenderer(loader);
		Random r = new Random();
		
		System.out.println("OpenGL: " + GL11.glGetString(GL11.GL_VERSION));
		
		//************-LISTS-************\\
		List<Entity> entitys = new ArrayList<Entity>();
		List<Light> lights = new ArrayList<Light>();
		List<Terrain> terrains = new ArrayList<Terrain>();
		List<GuiTexture> guiTextures = new ArrayList<GuiTexture>();
		//************-------************\\
		
		//************-LIGHTS-************\\
		lights.add(new Light(new Vector3f(0,3600,0), new Vector3f(1,1,1), new Vector3f(1f, 0f, 0f)));
		//************--------************\\

		//************-TERRAIN-************\\
		TerrainTexture groundTexture = new TerrainTexture(loader.loadTexture("grass"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("sand"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("road"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(groundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		
		for(int y = -1; y < 1; y++) {
			for(int x = -1; x < 1; x++) {
				terrains.add(new Terrain(-x, -y, loader, texturePack, blendMap));
			}
		}
		//terrains.add(new Terrain(0, 0, loader, texturePack, blendMap));
		//************---------************\\
		
		//************-PLAYER-************\\
//		float px = r.nextFloat() * -768;
//		float pz = r.nextFloat() * -768;
//		float py = terrains.get(0).getHeightsOfTerrain(px, pz);
		float px = 100;
		float pz = 100;
		float py = 0;
		ModelData data = OBJFileLoader.loadOBJ("spyro");
		RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
		ModelTexture texture = new ModelTexture(loader.loadTexture("spyro"));
		texture.setUseFakeLighting(false);
		texture.setReflectivity(0.1f);
		texture.setShineDamper(50000f);
		TexturedModel texturedModel = new TexturedModel(model,texture);
		Player player = new Player(texturedModel, new Vector3f(px,py,pz), 0, r.nextInt(360), 0, 1.2f);
		entitys.add(player);
		//************--------************\\
		
		Camera camera = new Camera(player);
		
		//************-ENTITYS-************\\
		ModelData treeData = OBJFileLoader.loadOBJ("tree");
		ModelData fernData = OBJFileLoader.loadOBJ("fern");
		ModelData lampData = OBJFileLoader.loadOBJ("lamp");
		
		RawModel treeModel = loader.loadToVAO(treeData.getVertices(), treeData.getTextureCoords()
				, treeData.getNormals(), treeData.getIndices());
		RawModel fernModel = loader.loadToVAO(fernData.getVertices(), fernData.getTextureCoords()
				, fernData.getNormals(), fernData.getIndices());
		RawModel lampModel = loader.loadToVAO(lampData.getVertices(), lampData.getTextureCoords()
				, lampData.getNormals(), lampData.getIndices());
		
		ModelTexture treeModelTexture = new ModelTexture(loader.loadTexture("tree"));
		ModelTexture fernModelTexture = new ModelTexture(loader.loadTexture("fern"));
		ModelTexture lampModelTexture = new ModelTexture(loader.loadTexture("lamp"));

		fernModelTexture.setNumberOfRows(2);
		fernModelTexture.setHasTransparency(true);
		fernModelTexture.setUseFakeLighting(true);
		lampModelTexture.setUseFakeLighting(true);
		
		TexturedModel treeTexturedModel = new TexturedModel(treeModel, treeModelTexture);
		TexturedModel fernTexturedModel = new TexturedModel(fernModel, fernModelTexture);
		TexturedModel lampTexturedModel = new TexturedModel(lampModel, lampModelTexture);
		
		entitys.add(new Entity(texturedModel, new Vector3f(px, py, pz), 0f, 0f, 0f, 100f));

		for(int i = 0; i < 400; i++) {
			float tx = r.nextFloat() * -100;
			float tz = r.nextFloat() * -100;
			float ty = terrains.get(0).getHeightsOfTerrain(tx, tz);
			Entity tree = new Entity(treeTexturedModel, new Vector3f(tx, ty, tz),
					0, (r.nextFloat() *360f), 0, (r.nextFloat() * 0.4f) + 0.25f);
			entitys.add(tree);
			
			float fx = r.nextFloat() * -100;
			float fz = r.nextFloat() * -100;
			float fy = terrains.get(0).getHeightsOfTerrain(fx, fz);
			Entity fern = new Entity(fernTexturedModel, new Vector3f(fx, fy, fz), r.nextInt(4),
					0, (r.nextFloat() *360f), 0, 0.4f);
			entitys.add(fern);
		}
		for(int i = 0; i < 3; i++) {
			float lx = r.nextFloat() * -100;
			float lz = r.nextFloat() * -100;
			float ly = terrains.get(0).getHeightsOfTerrain(lx, lz);
			Vector3f colour = null;
			if(i == 0) colour = new Vector3f(2, 0, 0);
			if(i == 1) colour = new Vector3f(0, 2, 2);
			if(i == 2) colour = new Vector3f(2, 2, 0);
			Lamp lamp = new Lamp(lampTexturedModel, new Vector3f(lx, ly, lz),
					0, (r.nextFloat() *360f), 0, 1f, lights, colour);
			entitys.add(lamp);
		}
		//************---------************\\

		//************-WATER-************\\
		WaterFrameBuffers fbos = new WaterFrameBuffers();
		WaterShader waterShader = new WaterShader();
		WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), fbos);
		List<WaterTile> waterTiles = new ArrayList<WaterTile>();
		WaterTile waterPlane = new WaterTile(0, 0, 0);
		waterTiles.add(waterPlane);

		//************-------************\\
		
		//************-GUIS-************\\
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		//guiTextures.add(new GuiTexture(fbos.getReflectionTexture(), new Vector2f(0.5f,0.5f), new Vector2f(0.25f,0.25f)));
		//guiTextures.add(new GuiTexture(fbos.getRefractionTexture(), new Vector2f(-0.5f,0.5f), new Vector2f(0.25f,0.25f)));
		//************------************\\
		
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {	
			player.move(terrains);
			camera.move();
			
			GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
			if (Keyboard.next()) {
			    if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
	                int polygonMode = GL11.glGetInteger(GL11.GL_POLYGON_MODE);
	                if (polygonMode == GL11.GL_LINE) {
	                	GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);
	                } else if (polygonMode == GL11.GL_FILL) {
	                	GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_POINT);
	                } else if (polygonMode == GL11.GL_POINT) {
	                	GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_LINE);
	                }
			    }
			}

			// Render Reflection
			fbos.bindReflectionFrameBuffer();
			float distance = 2 * (camera.getPosition().y - waterPlane.getHeight());
			camera.getPosition().y -= distance;
			camera.invertPitch();
			renderer.renderScene(entitys, terrains, lights, camera, new Vector4f(0, 1, 0, -waterPlane.getHeight()+1f));
			camera.getPosition().y += distance;
			camera.invertPitch();
			
			// Render Refracion
			fbos.bindRefractionFrameBuffer();
			renderer.renderScene(entitys, terrains, lights, camera, new Vector4f(0, -1, 0, waterPlane.getHeight()+0.5f));
			
			fbos.unbindCurrentFrameBuffer();
			GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
			renderer.renderScene(entitys, terrains, lights, camera, new Vector4f(0, 1, 0, Float.MAX_VALUE));
			waterRenderer.render(waterTiles, camera, lights.get(0));
			guiRenderer.render(guiTextures);
			
			DisplayManager.updateDisplay();
		}
		
		fbos.cleanUp();
		guiRenderer.cleanUp();
		waterShader.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}