/*
 * Copyright 2009 Hao Nguyen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package gwt.g3d.client.shader;

import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.WebGLProgram;
import gwt.g3d.client.gl2.WebGLShader;
import gwt.g3d.client.gl2.WebGLUniformLocation;
import gwt.g3d.client.gl2.enums.ProgramParameter;
import gwt.g3d.client.gl2.enums.ShaderParameter;
import gwt.g3d.client.gl2.enums.ShaderType;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a WebGL shader.
 * AbstractShader caches the uniform locations and attribute locations, so it
 * is only slow the first time of those values are retrieved.
 * 
 * @author hao1300@gmail.com
 */
public abstract class AbstractShader implements Shader {
	private final Map<String, WebGLUniformLocation> uniformVariables = 
			new HashMap<String, WebGLUniformLocation>();
	private final Map<String, Integer> attribVariables = 
			new HashMap<String, Integer>();
	private WebGLProgram program;
	private WebGLShader vertexShader, fragmentShader;
	protected GL2 gl;
	
	@Override
	public void dispose() {
		gl.deleteShader(vertexShader);
		gl.deleteShader(fragmentShader);
		gl.deleteProgram(program);
		attribVariables.clear();
		uniformVariables.clear();
	}
	
	@Override
	public final void bind() {
		gl.useProgram(program);
		bindImpl();
	}
	
	@Override
	public final void init(GL2 gl) throws ShaderException {
		this.gl = gl;
		initImpl();
	}
	
	/**
	 * Override this method to initialize the shader program.
	 */
	protected abstract void initImpl() throws ShaderException;
	
	/**
	 * Gets the fragment shader.
	 */
	public WebGLShader getFragmentShader() {
		return fragmentShader;
	}
	
	/**
	 * Gets the shader program.
	 */
	public WebGLProgram getProgram() {
		return program;
	}
	
	/**
	 * Gets the vertex shader.
	 */
	public WebGLShader getVertexShader() {
		return vertexShader;
	}
	
	/**
	 * Initializes this shader with the given vertex shader source and fragment
	 * shader source.
	 * This should be called within {@link #init(GL2)} to ensure that the shader
	 * is correctly initialized.
	 * 
	 * @param vertexSource vertex shader source code
	 * @param fragmentSource fragment shader source code
	 * @throws ShaderException when there is a shader compilation error.
	 */
	protected final void initProgram(String vertexSource, String fragmentSource) 
			throws ShaderException {
		program = gl.createProgram();
		
		vertexShader = loadShader(vertexSource, ShaderType.VERTEX_SHADER);
		fragmentShader = loadShader(fragmentSource, ShaderType.FRAGMENT_SHADER);		
		
		gl.attachShader(program, vertexShader);
		gl.attachShader(program, fragmentShader);
		
		gl.linkProgram(program);
		if (!gl.getProgramParameterb(program, ProgramParameter.LINK_STATUS)) {
			String errorMsg = gl.getProgramInfoLog(program);
      throw new ShaderException("Could not initialise shaders.\n"
      		+ errorMsg);
    }		
	}
	
	/**
	 * Loads a shader from its source.
	 * 
	 * @param shaderSource
	 * @param shaderType
	 * @throws ShaderException when there is a shader compilation error.
	 */
	protected final WebGLShader loadShader(String shaderSource, 
			ShaderType shaderType) throws ShaderException {
		WebGLShader shader = gl.createShader(shaderType);
		gl.shaderSource(shader, shaderSource);
		gl.compileShader(shader);
		
		if (!gl.getShaderParameterb(shader, ShaderParameter.COMPILE_STATUS)) {
			String errorMsg = gl.getShaderInfoLog(shader);
			throw new ShaderException("Shader compiling error " + shaderType.toString() 
					+ "\n" + errorMsg);
		}
		return shader;
	}

	/**
	 * Override this method to performs shader-specific binding.
	 * This will be called after bind() is about to return.
	 */
	protected void bindImpl() {
		
	}
	
	/**
	 * Gets the uniform location of the given uniform variable name.
	 * 
	 * @param uniformName
	 */
	public WebGLUniformLocation getUniformLocation(String uniformName) {
		WebGLUniformLocation uniform = uniformVariables.get(uniformName);
		if (uniform == null) {
			uniform = gl.getUniformLocation(program, uniformName);
			uniformVariables.put(uniformName, uniform);
		}
		return uniform;
	}
	
	/**
	 * Gets the attribute location of the given attribute variable name.
	 * 
	 * @param attribName
	 */
	public int getAttributeLocation(String attribName) {
		Integer attribute = attribVariables.get(attribName);
		if (attribute == null) {
			attribute = gl.getAttribLocation(program, attribName);
			attribVariables.put(attribName, attribute);
		}
		return attribute;
	}
}
