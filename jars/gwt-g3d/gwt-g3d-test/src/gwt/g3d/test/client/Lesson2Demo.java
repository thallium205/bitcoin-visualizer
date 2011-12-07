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
 * GWT-port for the lesson 2.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson2Demo extends AbstractLessonDemo {
	private static final int LESSON_NUMBER = 2;
	private int vertexPositionAttribute, vertexColorAttribute;
	
	protected Lesson2Demo() {
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
		vertexColorAttribute = shader.getAttributeLocation("aVertexColor");
		gl.enableVertexAttribArray(vertexColorAttribute);
    
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
    float[] vertices = {0f,  1f,  0f, -1f, -1f,  0f, 1f, -1f,  0f };
    gl.bufferData(BufferTarget.ARRAY_BUFFER, 
    		Float32Array.create(vertices), BufferUsage.STATIC_DRAW);
    gl.vertexAttribPointer(vertexPositionAttribute, 3, DataType.FLOAT, false, 0, 0);
    
    WebGLBuffer triangleVertexColorBuffer = gl.createBuffer();
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, triangleVertexColorBuffer);
    float[] colors = { 1f, 0f, 0f, 1f, 0f, 1f, 0f, 1f, 0f, 0f, 1f, 1f };
    gl.bufferData(BufferTarget.ARRAY_BUFFER,
    		Float32Array.create(colors), BufferUsage.STATIC_DRAW);
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, triangleVertexColorBuffer);
    gl.vertexAttribPointer(vertexColorAttribute, 4, DataType.FLOAT, false, 0, 0);
    
    gl.drawArrays(BeginMode.TRIANGLES, 0, 3);
    
    gl.deleteBuffer(triangleVertexPositionBuffer);
    gl.deleteBuffer(triangleVertexColorBuffer);
	}

	private void drawSquare() {
		MODELVIEW.push();
    MODELVIEW.translate(3, 0, 0);
    setMatrixUniforms();
    MODELVIEW.pop();
    
    WebGLBuffer squareVertexPositionBuffer = gl.createBuffer();
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, squareVertexPositionBuffer);
    float[] vertices = { 1,  1,  0, -1,  1,  0, 1, -1,  0, -1, -1,  0 };
    gl.bufferData(BufferTarget.ARRAY_BUFFER, 
    		Float32Array.create(vertices), BufferUsage.STATIC_DRAW);
    gl.vertexAttribPointer(vertexPositionAttribute, 3, DataType.FLOAT, false, 0, 0);
    
    WebGLBuffer squareVertexColorBuffer = gl.createBuffer();
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, squareVertexColorBuffer);
    float[] colors = new float[16];
    for (int i = 0; i < 16;) {
      colors[i++] = 0.5f;
      colors[i++] = 0.5f;
      colors[i++] = 1f;
      colors[i++] = 1f;
    }
    gl.bufferData(BufferTarget.ARRAY_BUFFER, 
    		Float32Array.create(colors), BufferUsage.STATIC_DRAW);
    gl.vertexAttribPointer(vertexColorAttribute, 4, DataType.FLOAT, false, 0, 0);
 
    gl.drawArrays(BeginMode.TRIANGLE_STRIP, 0, 4);
    
    gl.deleteBuffer(squareVertexPositionBuffer);
    gl.deleteBuffer(squareVertexColorBuffer);
	}
	
	private void setMatrixUniforms() {
    gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
  }
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source({"shaders/lesson2.vp", "shaders/lesson2.fp"})
		ShaderResource shader();
		
		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();
	}
}
