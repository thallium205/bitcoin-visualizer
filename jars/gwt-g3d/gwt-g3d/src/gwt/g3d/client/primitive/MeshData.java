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
package gwt.g3d.client.primitive;

/** 
 * Struct for storing the mesh data 
 *
 * @author hao1300@gmail.com
 */
public class MeshData {
	private float[] vertices, normals, textCoords;
	private int[] triangles;
	
	public MeshData() {
	}
	
	public MeshData(float[] vertices, int[] triangles, float[] normals, 
			float[] textCoords) {
		this.vertices = vertices;
		this.normals = normals;
		this.textCoords = textCoords;
		this.triangles = triangles;
	}
	
	/**
	 * Sets the vertices of the mesh.
	 * 
	 * @param vertices
	 */
	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}
	
	/**
	 * Gets the vertices of the mesh.
	 */
	public float[] getVertices() {
		return vertices;
	}

	/**
	 * Sets the normals of the mesh.
	 * 
	 * @param normals
	 */
	public void setNormals(float[] normals) {
		this.normals = normals;
	}

	/**
	 * Gets the normals of the mesh.
	 */
	public float[] getNormals() {
		return normals;
	}

	/**
	 * Sets the texture coordinates for the mesh.
	 * 
	 * @param textCoords
	 */
	public void setTextCoords(float[] textCoords) {
		this.textCoords = textCoords;
	}

	/**
	 * Gets the texture coordinates for the mesh.
	 */
	public float[] getTextCoords() {
		return textCoords;
	}

	/**
	 * Sets the triangle indices for the mesh.
	 * 
	 * @param triangles
	 */
	public void setTriangles(int[] triangles) {
		this.triangles = triangles;
	}

	/**
	 * Gets the triangle indices for the mesh.
	 */
	public int[] getTriangles() {
		return triangles;
	}
}