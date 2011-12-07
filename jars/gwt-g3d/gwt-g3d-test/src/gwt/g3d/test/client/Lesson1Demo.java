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
package gwt.g3d.test.client;

import static gwt.g3d.client.math.MatrixStack.MODELVIEW;
import static gwt.g3d.client.math.MatrixStack.PROJECTION;
import gwt.g3d.client.gl2.WebGLBuffer;
import gwt.g3d.client.gl2.array.Float32Array;
import gwt.g3d.client.gl2.enums.BeginMode;
import gwt.g3d.client.gl2.enums.BufferTarget;
import gwt.g3d.client.gl2.enums.BufferUsage;
import gwt.g3d.client.gl2.enums.DataType;
import gwt.g3d.resources.client.ShaderResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;

/**
 * GWT-port for the lesson 1.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson1Demo extends AbstractLessonDemo {
	private static final int LESSON_NUMBER = 1;
	private int vertexPositionAttribute;

	protected Lesson1Demo() {
		super(LESSON_NUMBER);
	}

	@Override
	public void dispose() {
		shader.dispose();
	}
	
	@Override
	public ClientBundleWithLookup getClientBundle() {
		return Resources.INSTANCE;
	}
	
	@Override
	protected void initImpl() {
		super.initImpl();
		vertexPositionAttribute = shader.getAttributeLocation("aVertexPosition");
		gl.enableVertexAttribArray(vertexPositionAttribute);

		PROJECTION.pushIdentity();
    PROJECTION.perspective(45, 1, .1f, 100);
    gl.uniformMatrix(shader.getUniformLocation("uPMatrix"), PROJECTION.get());
    PROJECTION.pop();
		
    MODELVIEW.push();
    MODELVIEW.translate(-1.5f, 0, -7);
		drawTriangle();
		drawSquare();
		MODELVIEW.pop();
	}
	
	private void drawTriangle() {
		setMatrixUniforms();
		
		WebGLBuffer triangleVertexPositionBuffer = gl.createBuffer();
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, triangleVertexPositionBuffer);
    float[] vertices = { 0, 1, 0, -1, -1, 0, 1, -1, 0 };
    gl.bufferData(BufferTarget.ARRAY_BUFFER, 
    		Float32Array.create(vertices), BufferUsage.STATIC_DRAW);
    gl.vertexAttribPointer(vertexPositionAttribute, 3, DataType.FLOAT, false, 0, 0);
    
    gl.drawArrays(BeginMode.TRIANGLES, 0, 3);
		gl.deleteBuffer(triangleVertexPositionBuffer);
	}
	
	private void drawSquare() {
		MODELVIEW.translate(3, 0, 0);
		setMatrixUniforms();
		
    WebGLBuffer squareVertexPositionBuffer = gl.createBuffer();
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, squareVertexPositionBuffer);
    float[] vertices = { 1, 1, 0, -1, 1, 0, 1, -1, 0, -1, -1, 0 };
    gl.bufferData(BufferTarget.ARRAY_BUFFER, 
    		Float32Array.create(vertices), BufferUsage.STATIC_DRAW);
    gl.vertexAttribPointer(vertexPositionAttribute, 3, DataType.FLOAT, false, 0, 0);
    gl.drawArrays(BeginMode.TRIANGLE_STRIP, 0, 4);
    gl.deleteBuffer(squareVertexPositionBuffer);
  }
	
	private void setMatrixUniforms() {
		gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
	}

	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source({"shaders/lesson1.vp", "shaders/lesson1.fp"})
		ShaderResource shader();

		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();
	}
}
