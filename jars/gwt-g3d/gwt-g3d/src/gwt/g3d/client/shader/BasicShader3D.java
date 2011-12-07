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

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;

/**
 * Represents a basic shader in 3D space.
 * 
 * BasicShader3D should contain at least the following variables:
 * uniform mat4 uModelViewMatrix, uProjectionMatrix;
 * uniform mat3 uNormalMatrix;
 * attribute vec4 aPosition
 * attribute vec3 aNormal;
 * 
 * @author hao1300@gmail.com
 */
public abstract class BasicShader3D extends AbstractShader {
	private final Matrix3f normalMatrix = new Matrix3f();
	
	/**
	 * Gets the attribute location of the aPosition variable.
	 */
	public int getAttributePosition() {
		return getAttributeLocation("aPosition");
	}

	/**
	 * Gets the attribute location of the aNormal variable.
	 */
	public int getAttributeNormal() {
		return getAttributeLocation("aNormal");
	}
	
	/**
	 * Sets the uModelViewMatrix. Note that this will also sets the 
	 * uNormalMatrix.
	 * 
	 * @param value
	 */
	public void setModelViewMatrix(Matrix4f value) {
		gl.uniformMatrix(getUniformLocation("uModelViewMatrix"), value);
		value.get(normalMatrix);
		normalMatrix.invert();
		normalMatrix.transpose();
		gl.uniformMatrix(getUniformLocation("uNormalMatrix"), normalMatrix);
	}
	
	/**
	 * Sets the uProjectionMatrix.
	 * 
	 * @param value
	 */
	public void setProjectionMatrix(Matrix4f value) {
		gl.uniformMatrix(getUniformLocation("uProjectionMatrix"), value);
	}
}
