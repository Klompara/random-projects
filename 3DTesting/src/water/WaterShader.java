package water;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import entities.Light;
import shaders.ShaderProgram;
import toolbox.Maths;

public class WaterShader extends ShaderProgram {

	private final static String VERTEX_FILE = "src/water/waterVertex.vert";
	private final static String FRAGMENT_FILE = "src/water/waterFragment.frag";

	private int location_modelMatrix;
	private int location_viewMatrix;
	private int location_projectionMatrix;
	private int location_refractionTexture;
	private int location_reflectionTexture;
	private int location_moveFactor;
	private int location_cameraPosition;
	private int location_dudvMap;
	private int location_normalMap;
	private int location_depthMap;
	private int location_lightPosition;
	private int location_lightColor;
	
	public WaterShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	protected void getAllUniformLocations() {
		location_projectionMatrix = getUniformLocation("projectionMatrix");
		location_viewMatrix = getUniformLocation("viewMatrix");
		location_modelMatrix = getUniformLocation("modelMatrix");
		location_reflectionTexture = getUniformLocation("reflectionTexture");
		location_refractionTexture = getUniformLocation("refractionTexture");
		location_moveFactor = getUniformLocation("moveFactor");
		location_cameraPosition = getUniformLocation("cameraPosition");
		location_dudvMap = getUniformLocation("dudvMap");
		location_normalMap = getUniformLocation("normalMap");
		location_depthMap = getUniformLocation("depthMap");
		location_lightColor = getUniformLocation("lightColor");
		location_lightPosition = getUniformLocation("lightPosition");
	}
	
	public void connectTextureUnits() {
		super.loadInt(location_reflectionTexture, 0);
		super.loadInt(location_refractionTexture, 1);
		super.loadInt(location_dudvMap, 2);
		super.loadInt(location_normalMap, 3);
		super.loadInt(location_depthMap, 4);
	}
	
	public void loadLight(Light sun) {
		super.loadVector3f(location_lightColor, sun.getColour());
		super.loadVector3f(location_lightPosition, sun.getPosition());
	}
	
	public void loadMoveFactor(float factor) {
		super.loadFloat(location_moveFactor, factor);
	}

	public void loadProjectionMatrix(Matrix4f projection) {
		super.loadMatrix(location_projectionMatrix, projection);
	}
	
	public void loadViewMatrix(Camera camera){
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
		super.loadVector3f(location_cameraPosition, camera.getPosition());
	}

	public void loadModelMatrix(Matrix4f modelMatrix){
		super.loadMatrix(location_modelMatrix, modelMatrix);
	}
}
