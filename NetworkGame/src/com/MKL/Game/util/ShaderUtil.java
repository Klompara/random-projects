package com.MKL.Game.util;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class ShaderUtil {

	public ShaderUtil() {}
	
	public static int load(String vertPath, String fragPath) {
		String vert = FileUtils.readFromString(vertPath);
		String frag = FileUtils.readFromString(fragPath);
		return create(vert, frag);
	}
	
	public static int create(String vert, String frag) {
		int programID = glCreateProgram();
		int vertID = glCreateShader(GL_VERTEX_SHADER);
		int fragID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(vertID, vert);
		glShaderSource(fragID, frag);
		
		glCompileShader(vertID);
		if(glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Failed to compile shader: vertex");
			System.err.println(glGetShaderInfoLog(vertID, 2048));
		}
		
		glCompileShader(fragID);
		if(glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Failed to compile shader: fragment");
			System.err.println(glGetShaderInfoLog(fragID, 2048));
		}
		
		glAttachShader(programID, vertID);
		glAttachShader(programID, fragID);
		glLinkProgram(programID);
		glValidateProgram(programID);
		
		return programID;
	}
	
}
