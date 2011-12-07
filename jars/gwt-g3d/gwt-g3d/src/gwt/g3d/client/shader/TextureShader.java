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

import gwt.g3d.client.gl2.enums.TextureUnit;

import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple4f;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Represents a simple shader that renders a texture.
 * 
 * This shader contains the following variables:
 * uniform mat4 uModelViewMatrix, uProjectionMatrix;
 * uniform sampler2D uTexture;
 * uniform vec4 uTintColor;
 * attribute vec4 aPosition;
 * attribute vec2 aTexCoord;
 * 
 * @author hao1300@gmail.com
 */
public class TextureShader extends AbstractShader {
	
	@Override
	protected void initImpl() throws ShaderException {
		initProgram(Resources.INSTANCE.vertexShader().getText(), 
				Resources.INSTANCE.fragmentShader().getText());
	}
	
	/**
	 * Gets the attribute location of the aPosition variable.
	 */
	public int getAttributePosition() {
		return getAttributeLocation("aPosition");
	}
	
	/**
	 * Gets the attribute location of the aTexCoord variable.
	 */
	public int getAttributeTexCoord() {
		return getAttributeLocation("aTexCoord");
	}
	
	/**
	 * Sets the uModelViewMatrix. Note that this will also sets the 
	 * uNormalMatrix.
	 * 
	 * @param value
	 */
	public void setModelViewMatrix(Matrix4f value) {
		gl.uniformMatrix(getUniformLocation("uModelViewMatrix"), value);
	}
	
	/**
	 * Sets the uProjectionMatrix.
	 * 
	 * @param value
	 */
	public void setProjectionMatrix(Matrix4f value) {
		gl.uniformMatrix(getUniformLocation("uProjectionMatrix"), value);
	}
	
	/**
	 * Sets the uTintColor.
	 * 
	 * @param r
	 * @param g
	 * @param b 
	 * @param a
	 */
	public void setTintColor(float r, float g, float b, float a) {
		gl.uniform4f(getUniformLocation("uTintColor"), r, g, b, a);
	}
	
	/**
	 * Sets the uTintColor.
	 * 
	 * @param value
	 */
	public void setTintColor(Tuple4f value) {
		gl.uniform(getUniformLocation("uTintColor"), value);
	}
	
	/**
	 * Sets the uTexture to be the texture binded to the given texture unit.
	 * 
	 * @param textureUnit
	 */
	public void setTexture(TextureUnit textureUnit) {
		gl.uniform1i(getUniformLocation("uTexture"), 
				textureUnit.getValue() - TextureUnit.TEXTURE0.getValue());
	}	
	
	/** Resource files. */
	interface Resources extends ClientBundle {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source("source/texture.vp")
		TextResource vertexShader();

		@Source("source/texture.fp")
		TextResource fragmentShader();
	}
}
