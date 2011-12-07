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

import gwt.g3d.client.gl2.WebGLBuffer;
import gwt.g3d.client.gl2.enums.DataType;

/**
 * Stores the configuration for a vertex buffer objection.
 * 
 * @author hao1300@gmail.com
 */
public class VboConfig {
	private int vertexIndex = -1;
	private WebGLBuffer vertexBuffer;
	private DataType dataType = DataType.FLOAT;
	private int dataSize = 4;
	private boolean normalize;

	public VboConfig() {
		
	}
	
	public VboConfig(DataType dataType, int dataSize) {
		this.dataType = dataType;
		this.dataSize = dataSize;
	}
	
	/**
	 * Resets the VBO to its default values.
	 */
	public void reset() {
		vertexIndex = -1;
		vertexBuffer = null;
		dataType = DataType.FLOAT;
		dataSize = 4;
		normalize = false;
	}
	
	/**
	 * Sets the index of the vertex attribute.
	 * Default: -1;
	 */
	public void setVertexIndex(int vertexIndex) {
		this.vertexIndex = vertexIndex;
	}

	/**
	 * Gets the index of the vertex attribute.
	 */
	public int getVertexIndex() {
		return vertexIndex;
	}

	/**
	 * Sets the buffer that stores the vertex information.
	 * 
	 * @param vertexBuffer
	 */
	public void setVertexBuffer(WebGLBuffer vertexBuffer) {
		this.vertexBuffer = vertexBuffer;
	}

	/**
	 * Gets the buffer that stores the vertex information.
	 */
	public WebGLBuffer getVertexBuffer() {
		return vertexBuffer;
	}

	/**
	 * Sets the type of data that this VBO stores.
	 * Default: DataType.FLOAT
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	/**
	 * Gets the type of data that this VBO stores.
	 */
	public DataType getDataType() {
		return dataType;
	}

	/**
	 * Sets the size per vertex.
	 * Default: 4
	 */
	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}

	/**
	 * Gets the size per vertex.
	 */
	public int getDataSize() {
		return dataSize;
	}

	/**
	 * Sets whether to normalize the data.
	 * 
	 * @param normalize
	 */
	public void setNormalize(boolean normalize) {
		this.normalize = normalize;
	}

	/**
	 * Gets whehter to normalize the data.
	 */
	public boolean isNormalize() {
		return normalize;
	}
}