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
import gwt.g3d.client.gl2.array.Uint16Array;
import gwt.g3d.client.gl2.enums.BeginMode;
import gwt.g3d.client.gl2.enums.BufferTarget;
import gwt.g3d.client.gl2.enums.BufferUsage;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.DataType;
import gwt.g3d.client.gl2.enums.DrawElementsType;
import gwt.g3d.resources.client.ShaderResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;

/**
 * GWT-port for the lesson 4.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson4Demo extends AbstractLessonDemo {
	private static final int LESSON_NUMBER = 4;
	private WebGLBuffer pyramidVertexPositionBuffer, pyramidVertexColorBuffer,
			cubeVertexPositionBuffer, cubeVertexColorBuffer, cubeVertexIndexBuffer;
	private int vertexPositionAttribute, vertexColorAttribute;
	private double lastTime;
	private float rPyramid, rCube;
	
	protected Lesson4Demo() {
		super(LESSON_NUMBER);
	}

	@Override
	public void dispose() {
		gl.deleteBuffer(pyramidVertexPositionBuffer);
		gl.deleteBuffer(pyramidVertexColorBuffer);
		gl.deleteBuffer(cubeVertexPositionBuffer);
		gl.deleteBuffer(cubeVertexColorBuffer);
		gl.deleteBuffer(cubeVertexIndexBuffer);
		shader.dispose();
	}
	
	@Override
	public ClientBundleWithLookup getClientBundle() {
		return Resources.INSTANCE;
	}
	
	@Override
	public void update() {
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);
		
		MODELVIEW.push();
		MODELVIEW.translate(-1.5f, 0, -8);
		drawPyramid();
    drawCube();
    MODELVIEW.pop();

    double currTime = System.currentTimeMillis();
    double elapsed = currTime - lastTime;
    rPyramid += (90 * elapsed) / 1000f;
    rCube -= (75 * elapsed) / 1000f;
    lastTime = currTime;
	}
	
	@Override
	protected void initImpl() {
		super.initImpl();

		rPyramid = 0;
		rCube = 0;
		
		vertexPositionAttribute = shader.getAttributeLocation("aVertexPosition");
		gl.enableVertexAttribArray(vertexPositionAttribute);
		vertexColorAttribute = shader.getAttributeLocation("aVertexColor");
		gl.enableVertexAttribArray(vertexColorAttribute);
    
		PROJECTION.pushIdentity();
    PROJECTION.perspective(45, 1, .1f, 100);
    gl.uniformMatrix(shader.getUniformLocation("uPMatrix"), PROJECTION.get());
    PROJECTION.pop();
		
		initBuffers();
		lastTime = System.currentTimeMillis();
	}
	
	private void initBuffers() {
		pyramidVertexPositionBuffer = gl.createBuffer();
		gl.bindBuffer(BufferTarget.ARRAY_BUFFER, pyramidVertexPositionBuffer);
    float[] vertices = {
				0, 1, 0, -1, -1, 1, 1, -1, 1, 	// Front face
				0, 1, 0, 1, -1, 1, 1, -1, -1,		// Right face
				0, 1, 0, 1, -1, -1, -1, -1, -1,	// Back face
				0, 1, 0, -1, -1, -1, -1, -1, 1	// Left face
    };
    gl.bufferData(BufferTarget.ARRAY_BUFFER, 
    		Float32Array.create(vertices), BufferUsage.STATIC_DRAW);
 
    pyramidVertexColorBuffer = gl.createBuffer();
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, pyramidVertexColorBuffer);
    float[] colors = { 
			1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1,	// Front face
			1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1,	// Right face
			1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1,	// Back face
			1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1	// Left face
    };
    gl.bufferData(BufferTarget.ARRAY_BUFFER,
    		Float32Array.create(colors), BufferUsage.STATIC_DRAW);
    
    cubeVertexPositionBuffer = gl.createBuffer();
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, cubeVertexPositionBuffer);
    float[] cubeVertices = new float[] { 
				-1, -1, 1, 1, -1, 1, 1, 1, 1, -1, 1, 1,			// Front face
				-1, -1, -1, -1, 1, -1, 1, 1, -1, 1, -1, -1,	// Back face
				-1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, -1,			// Top face
				-1, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, 1,	// Bottom face
				1, -1, -1, 1, 1, -1, 1, 1, 1, 1, -1, 1,			// Right face
				-1, -1, -1, -1, -1, 1, -1, 1, 1, -1, 1, -1	// Left face
    };
    gl.bufferData(BufferTarget.ARRAY_BUFFER, 
    		Float32Array.create(cubeVertices), BufferUsage.STATIC_DRAW);
 
    cubeVertexColorBuffer = gl.createBuffer();
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, cubeVertexColorBuffer);
    float[][] faceColors = {
    		{1, 0, 0, 1},     	// Front face
        {1, 1, 0, 1},     	// Back face
        {0, 1, 0, 1},     	// Top face
        {1, 0.5f, 0.5f, 1}, // Bottom face
        {1, 0, 1, 1},     	// Right face
        {0, 0, 1, 1},     	// Left face
    };
    
    // 4 vertices per face * 4 color components * number of faces.
    float[] cubeColors = new float[4 * 4 * faceColors.length]; 
    int index = 0;
    for (float[] faceColor : faceColors) {
      for (int i = 0; i < 4; i++) {
      	for (float v : faceColor) {
      		cubeColors[index++] = v;
      	}
      }
    }
    gl.bufferData(BufferTarget.ARRAY_BUFFER, 
    		Float32Array.create(cubeColors), BufferUsage.STATIC_DRAW);
    
    cubeVertexIndexBuffer = gl.createBuffer();
    gl.bindBuffer(BufferTarget.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer);
    int[] cubeVertexIndices = {
	      0, 1, 2,      0, 2, 3,    // Front face
	      4, 5, 6,      4, 6, 7,    // Back face
	      8, 9, 10,     8, 10, 11,  // Top face
	      12, 13, 14,   12, 14, 15, // Bottom face
	      16, 17, 18,   16, 18, 19, // Right face
	      20, 21, 22,   20, 22, 23  // Left face
    };
    gl.bufferData(BufferTarget.ELEMENT_ARRAY_BUFFER, 
    		Uint16Array.create(cubeVertexIndices), 
    		BufferUsage.STATIC_DRAW);
	}
	
	private void drawPyramid() {
		MODELVIEW.push();
		MODELVIEW.rotateY((float) Math.toRadians(rPyramid));
		setMatrixUniforms();
		MODELVIEW.pop();
    
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, pyramidVertexPositionBuffer);
    gl.vertexAttribPointer(vertexPositionAttribute, 3, DataType.FLOAT, false, 0, 0);
 
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, pyramidVertexColorBuffer);
    gl.vertexAttribPointer(vertexColorAttribute, 4, DataType.FLOAT, false, 0, 0);
    
    gl.drawArrays(BeginMode.TRIANGLES, 0, 12);
	}
	
	private void drawCube() {      
		MODELVIEW.push();
		MODELVIEW.translate(3, 0, 0);
		MODELVIEW.rotate((float) Math.toRadians(rCube), 1, 1, 1);
		setMatrixUniforms();
		MODELVIEW.pop();
		
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, cubeVertexPositionBuffer);
    gl.vertexAttribPointer(vertexPositionAttribute, 3, DataType.FLOAT, false, 0, 0);
 
    gl.bindBuffer(BufferTarget.ARRAY_BUFFER, cubeVertexColorBuffer);
    gl.vertexAttribPointer(vertexColorAttribute, 4, DataType.FLOAT, false, 0, 0);
 
    gl.bindBuffer(BufferTarget.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer);
    gl.drawElements(BeginMode.TRIANGLES, 36, DrawElementsType.UNSIGNED_SHORT, 0);
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
