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
package gwt.g3d.client.mesh;

import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.array.TypeArray;
import gwt.g3d.client.gl2.enums.BeginMode;
import gwt.g3d.client.gl2.enums.BufferTarget;
import gwt.g3d.client.gl2.enums.BufferUsage;

/**
 * A abstract mesh class for representing a mesh.
 * 
 * @author hao1300@gmail.com
 */
public abstract class AbstractMesh implements Mesh {
	protected GL2 gl;
	private VboConfig[] vbos; 
	private BeginMode beginMode = BeginMode.TRIANGLES;
	
	protected AbstractMesh(int numVbos) {
		vbos = new VboConfig[numVbos];
		for (int i = 0; i < numVbos; i++) {
			vbos[i] = new VboConfig();
		}
	}
	
	protected AbstractMesh(VboConfig... vbos) {
		this.vbos = vbos;
	}
	
	@Override
	public final void init(GL2 gl) {
		this.gl = gl;
	}
	
	@Override
	public final void dispose() {
		for (VboConfig vbo : vbos) {
			if (vbo.getVertexBuffer() != null) {
				gl.deleteBuffer(vbo.getVertexBuffer());
			}
			vbo.reset();
		}
		disposeImpl();
	};
	
	@Override
	public final void draw() {
		bind();
		drawImpl();
		unbind();
	}
	
	/**
	 * Gets the mode to draw the mesh with.
	 */
	public BeginMode getBeginMode() {
		return beginMode;
	}

	/**
	 * Sets the mode to draw the mesh with. The default mode is TRIANGLES.
	 * 
	 * @param beginMode
	 */
	public void setBeginMode(BeginMode beginMode) {
		this.beginMode = beginMode;
	}
	
	/**
	 * Gets the VBO config at the given index.
	 */
	protected final VboConfig getVbo(int index) {
		return vbos[index];
	}
	
	/**
	 * Overrides this method to draw the mesh. 
	 */
	protected abstract void drawImpl();
	
	/**
	 * Overrides this method to dispose unmanaged resources in the subclass.
	 */
	protected void disposeImpl() {
		
	}
	
	/**
	 * Binds the VBOs.
	 */
	protected final void bind() {
		for (VboConfig vbo : vbos) {
			bind(vbo);
		}
		bindImpl();
	}
	
	/**
	 * Overrides this method to perform extra binding.
	 */
	protected void bindImpl() {
		
	}
	
	/**
	 * Unbinds the VBOs.
	 */
	protected final void unbind() {
		gl.bindBuffer(BufferTarget.ARRAY_BUFFER, null);
		for (VboConfig vbo : vbos) {
			unbind(vbo);
		}
		unbindImpl();
	}
	
	/**
	 * Overrides this method to perform extra un binding.
	 */
	protected void unbindImpl() {
		
	}
	
	/**
	 * Creates a new buffer in the VBO and passes the data array to the VBO'
	 * s buffer as ARRAY_BUFFER target using the given usage.
	 * 
	 * @param vbo
	 * @param dataArray
	 * @param usage
	 */
	protected void createAndBufferArrayData(VboConfig vbo, TypeArray dataArray, 
			BufferUsage usage) {
		if (vbo.getVertexBuffer() != null) {
			gl.deleteBuffer(vbo.getVertexBuffer());
		}
		vbo.setVertexBuffer(gl.createBuffer());
		bufferArrayData(vbo, dataArray, usage);
	}
	
	/**
	 * Passes the data array to the VBO's buffer as ARRAY_BUFFER target using 
	 * the given usage.
	 * 
	 * @param vbo
	 * @param dataArray
	 * @param usage
	 */
	protected void bufferArrayData(VboConfig vbo, TypeArray dataArray, 
			BufferUsage usage) {
		gl.bindBuffer(BufferTarget.ARRAY_BUFFER, vbo.getVertexBuffer());
		gl.bufferData(BufferTarget.ARRAY_BUFFER, dataArray, usage);
	}
	
	/**
	 * Enables and binds the VBO.
	 * If vertexIndex is negative, this method will do nothing.
	 * 
	 * @see GL2.#vertexAttribPointer(int, int, DataType, boolean, int, int).
	 */
	protected void bind(VboConfig vbo) {
		int vertexIndex = vbo.getVertexIndex();
		if (vertexIndex >= 0) {
			gl.enableVertexAttribArray(vertexIndex);
			gl.bindBuffer(BufferTarget.ARRAY_BUFFER, vbo.getVertexBuffer());
			gl.vertexAttribPointer(vertexIndex, vbo.getDataSize(), 
					vbo.getDataType(), vbo.isNormalize(), 0, 0);
		}
	}
	
	/**
	 * Unbinds a VBO.
	 * If vertexIndex is negative, this method will do nothing.
	 * 
	 * @param vertexIndex
	 */
	protected void unbind(VboConfig vbo) {
		if (vbo.getVertexIndex() >= 0) {
			gl.disableVertexAttribArray(vbo.getVertexIndex());
		}
	}
}
