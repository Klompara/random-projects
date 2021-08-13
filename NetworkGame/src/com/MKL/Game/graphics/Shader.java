package com.MKL.Game.graphics;

import com.MKL.Game.util.ShaderUtil;

public class Shader {

	private final int ID;
	
	public Shader(String vert, String frag) {
		ID = ShaderUtil.load(vert, frag);
	}
	
	
	
}
