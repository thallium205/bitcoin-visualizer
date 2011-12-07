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
import gwt.g3d.client.gl2.array.Float32Array;
import gwt.g3d.client.gl2.array.TypeArray;
import gwt.g3d.client.gl2.enums.BufferUsage;
import gwt.g3d.client.gl2.enums.DataType;
import gwt.g3d.client.primitive.MeshData;

/**
 * Represents a mesh whose vertices' information will stay the same once
 * created.
 * 
 * A SimpleStaticMesh is very similar to {@link StaticMesh} excepts that it 
 * uses {@link GL2#drawArrays(gwt.g3d.client.gl2.enums.BeginMode, int, int)}
 * for rendering instead of indices. 
 * 
 * @author hao1300@gmail.com
 */
public class SimpleStaticMesh extends AbstractMesh {
	private static final int VBO_POSITION_INDEX = 0;
	private static final int VBO_NORMAL_INDEX = 1;
	private static final int VBO_TEXCOORD_INDEX = 2;
	private int first, count;

	public SimpleStaticMesh() {
		super(3);
	}

	/**
	 * Initializes the mesh with the given data.
	 * This will also {@link #init(GL2)}, so there is no need to call 
	 * {@link #init(GL2)} again.
	 * 
	 * @param gl
	 * @param positions
	 * @param normals
	 * @param texCoords
	 * @param count
	 */
	public SimpleStaticMesh(GL2 gl, TypeArray positions, TypeArray normals,
			TypeArray texCoords, int count) {
		super(3);
		init(gl);
		if (positions != null) {
			setPositionData(positions);
		}
		if (normals != null) {
			setNormalData(normals);
		}
		if (texCoords != null) {
			setTexCoordData(texCoords);
		}
		setCount(count);
	}

	/**
	 * @see #SimpleStaticMesh(GL2, TypeArray, TypeArray, TypeArray, int)
	 */
	public SimpleStaticMesh(GL2 gl, float[] positions, float[] normals,
			float[] texCoords, int count) {
		this(gl, (positions == null) ? null : Float32Array.create(positions), 
				(normals == null) ? null : Float32Array.create(normals), 
				(texCoords == null) ? null : Float32Array.create(texCoords),
				count);
	}

	/**
	 * @see #SimpleStaticMesh(GL2, TypeArray, TypeArray, TypeArray, int)
	 */
	public SimpleStaticMesh(GL2 gl, MeshData meshData, int count) {
		this(gl, meshData.getVertices(), meshData.getNormals(), 
				meshData.getTextCoords(), count);
	}

	/**
	 * Sets the index of the vertex position attribute. If the attribute index is
	 * negative, the buffer will not be binded to gl.
	 * 
	 * @param positionIndex
	 */
	public void setPositionIndex(int positionIndex) {
		getVbo(VBO_POSITION_INDEX).setVertexIndex(positionIndex);
	}
	
	/**
	 * Sets the position data.
	 * This is equivalent to calling 
	 * setPositionData(positions, DataType.FLOAT, 3).
	 * 
	 * @param positions
	 */
	public void setPositionData(TypeArray positions) {
		setPositionData(positions, DataType.FLOAT, 3);
	}
	
	/**
	 * Sets the position data.
	 * 
	 * @param positions
	 * @param dataType type of the data passed.
	 * @param size number of elements per vertex.
	 */
	public void setPositionData(TypeArray positions, DataType dataType,
			int size) {
		VboConfig vbo = getVbo(VBO_POSITION_INDEX);
		vbo.setDataType(dataType);
		vbo.setDataSize(size);
		createAndBufferArrayData(vbo, positions, BufferUsage.STATIC_DRAW);
	}

	/**
	 * Sets the index of the vertex normal attribute. If the attribute index is
	 * negative, the buffer will not be binded to gl.
	 * 
	 * @param normalIndex
	 */
	public void setNormalIndex(int normalIndex) {
		getVbo(VBO_NORMAL_INDEX).setVertexIndex(normalIndex);
	}
	
	/**
	 * Sets the normals data.
	 * This is equivalent to calling setNormalData(normals, DataType.FLOAT, 3).
	 * 
	 * @param normals
	 */
	public void setNormalData(TypeArray normals) {
		setNormalData(normals, DataType.FLOAT, 3);
	}
	
	/**
	 * Sets the normals data.
	 * 
	 * @param normals
	 * @param dataType type of the data passed.
	 * @param size number of elements per vertex.
	 */
	public void setNormalData(TypeArray normals, DataType dataType,
			int size) {
		VboConfig vbo = getVbo(VBO_NORMAL_INDEX);
		vbo.setDataType(dataType);
		vbo.setDataSize(size);
		createAndBufferArrayData(vbo, normals, BufferUsage.STATIC_DRAW);
	}

	/**
	 * Sets the index of the vertex texture coordinate attribute. If the 
	 * attribute index is negative, the buffer will not be binded to gl.
	 * 
	 * @param texCoordIndex
	 */
	public void setTexCoordIndex(int texCoordIndex) {
		getVbo(VBO_TEXCOORD_INDEX).setVertexIndex(texCoordIndex);
	}
	
	/**
	 * Sets the texture coordinates data.
	 * This is equivalent to calling 
	 * setTexCoordData(texCoords, DataType.FLOAT, 2).
	 * 
	 * @param texCoords
	 */
	public void setTexCoordData(TypeArray texCoords) {
		setTexCoordData(texCoords, DataType.FLOAT, 2);
	}

	/**
	 * Sets the texture coordinates data.
	 * 
	 * @param texCoords
	 * @param dataType type of the data passed.
	 * @param size number of elements per vertex.
	 */
	public void setTexCoordData(TypeArray texCoords, DataType dataType,
			int size) {
		VboConfig vbo = getVbo(VBO_TEXCOORD_INDEX);
		vbo.setDataType(dataType);
		vbo.setDataSize(size);
		createAndBufferArrayData(vbo, texCoords, BufferUsage.STATIC_DRAW);
	}

	/**
	 * Sets the first index in 
	 * {@link GL2#drawArrays(gwt.g3d.client.gl2.enums.BeginMode, int, int)}
	 * 
	 * @param first
	 */
	public void setFirst(int first) {
		this.first = first;
	}

	/**
	 * Gets the first index in 
	 * {@link GL2#drawArrays(gwt.g3d.client.gl2.enums.BeginMode, int, int)}
	 */
	public int getFirst() {
		return first;
	}

	/**
	 * Sets the count in 
	 * {@link GL2#drawArrays(gwt.g3d.client.gl2.enums.BeginMode, int, int)}
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Gets the count in 
	 * {@link GL2#drawArrays(gwt.g3d.client.gl2.enums.BeginMode, int, int)}
	 */
	public int getCount() {
		return count;
	}
	
	@Override
	protected void drawImpl() {
		gl.drawArrays(getBeginMode(), first, count);
	}
}
