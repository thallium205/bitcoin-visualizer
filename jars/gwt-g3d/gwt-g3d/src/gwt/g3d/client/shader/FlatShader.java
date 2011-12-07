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

import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple4f;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Represents a single color shader. A FlatShader uses one color to shade the
 * entire mesh. Useful for debugging.
 * 
 * @author hao1300@gmail.com
 */
public class FlatShader extends AbstractShader {

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
	 * Sets the uModelViewMatrix.
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
	 * Sets the uColor.
	 * 
	 * @param r
	 * @param g
	 * @param b 
	 * @param a
	 */
	public void setColor(float r, float g, float b, float a) {
		gl.uniform4f(getUniformLocation("uColor"), r, g, b, a);
	}
	
	/**
	 * Sets the uColor.
	 * 
	 * @param value
	 */
	public void setColor(Tuple4f value) {
		gl.uniform(getUniformLocation("uColor"), value);
	}

	/** Resource files. */
	interface Resources extends ClientBundle {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source("source/flat.vp")
		TextResource vertexShader();

		@Source("source/flat.fp")
		TextResource fragmentShader();
	}
}
