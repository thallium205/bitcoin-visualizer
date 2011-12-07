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

import gwt.g2d.client.math.MathHelper;

import javax.vecmath.Vector3f;

/**
 * Factory for creating primitive meshes.
 * 
 * This class was originally written for CS467/468 (Computer Graphics II and
 * Practicum) by Kavita Bala Copyright 2009 Computer Science Department, Cornell
 * University
 * 
 * @author Adam Arbree -- arbree@cs.cornell.edu
 * @author Wenzel Jakob
 * @author Shuang Zhao
 * @author Joann Luu
 * @author hao1300@gmail.com
 */
public class PrimitiveFactory {

	/**
	 * Creates a plane lying on the xz-plane 2 units in width and length. The
	 * plane's normal is the positive y-axis.
	 * 
	 * @return the mesh data that describes the plane.
	 */
	public static MeshData makePlane() {
		float[] planeVerts = { -1, 0, -1, -1, 0, 1, 1, 0, 1, 1, 0, -1 };
		float[] planeNormals = { 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, };
		float[] planeTexCoords = { 0, 1, 0, 0, 1, 0, 1, 1 };
		int[] planeTriangles = { 0, 1, 2, 0, 2, 3 };

		return new MeshData(planeVerts, planeTriangles, planeNormals,
				planeTexCoords);
	}

	/**
	 * Creates the box [-1,-1] to [1,1]
	 */
	public static MeshData makeBox() {
		float[] boxVerts = {
				-1, -1,  1,  1, -1,  1,  1,  1,  1, -1,  1,  1,  // Front face
	      -1, -1, -1, -1,  1, -1,  1,  1, -1,  1, -1, -1,  // Back face
	      -1,  1, -1, -1,  1,  1,  1,  1,  1,  1,  1, -1,  // Top face
	      -1, -1, -1,  1, -1, -1,  1, -1,  1, -1, -1,  1,  // Bottom face
	       1, -1, -1,  1,  1, -1,  1,  1,  1,  1, -1,  1,  // Right face	 
	      -1, -1, -1, -1, -1,  1, -1,  1,  1, -1,  1, -1,  // Left face
	  };

		float[] boxNormals = { 
			0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,				// Front face
			0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1,		// Back face
			0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0,				// Top face
			0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0,		// Bottom face
			1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,				// Right face
			-1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0,		// Left face 
		};

		float[] boxTexCoords = { 
			0, 0, 1, 0, 1, 1, 0, 1,  // Front face
	    1, 0, 1, 1, 0, 1, 0, 0,  // Back face
	    0, 1, 0, 0, 1, 0, 1, 1,  // Top face
	    1, 1, 0, 1, 0, 0, 1, 0,  // Bottom face
	    1, 0, 1, 1, 0, 1, 0, 0,  // Right face
	    0, 0, 1, 0, 1, 1, 0, 1,  // Left face
		};

		int[] boxTriangles = { 
			0, 1, 2,      0, 2, 3,    // Front face
      4, 5, 6,      4, 6, 7,    // Back face
      8, 9, 10,     8, 10, 11,  // Top face
      12, 13, 14,   12, 14, 15, // Bottom face
      16, 17, 18,   16, 18, 19, // Right face
      20, 21, 22,   20, 22, 23  // Left face 
	  };

		return new MeshData(boxVerts, boxTriangles, boxNormals, boxTexCoords);
	}

	/**
	 * Creates a cone. The cone is exactly, 2 units high and 1 unit in radius at
	 * the base. The cone is oriented along the y-axis. The number of slices is
	 * the number of subdivisions around the circumference of the cone (at the
	 * base) and stacks is the number of subdivisions along the height.
	 * 
	 * @param stacks
	 * @param slices
	 */
	public static MeshData makeCone(int stacks, int slices) {
		// Create all the vertex data
		int size = (slices + 1) + 1 + (slices + 1) * (stacks + 1);
		float[] vertices = new float[3 * size];
		float[] normals = new float[3 * size];
		float[] texCoords = new float[2 * size];
		int[] triangles = new int[3 * (slices + (2 * slices) * (stacks))];
		
		// Create the base pole vertex
		int pos = 0;
		int index = 0;
		vertices[0] = 0;
		vertices[1] = -1;
		vertices[2] = 0;
		normals[0] = 0;
		normals[1] = -1;
		normals[2] = 0;
		texCoords[0] = 0.5f;
		texCoords[1] = 0.5f;
		pos++;

		// Create the base cap vertices must duplicate the edges to get sharp corner
		for (int ctr = 0; ctr <= slices; ctr++) {
			double angle = ctr * MathHelper.TWO_PI / slices;
			float x = cos(angle);
			float z = sin(angle);
			
			index = pos * 3;
			vertices[index] = x;
			vertices[index + 1] = -1;
			vertices[index + 2] = z;
			normals[index] = 0;
			normals[index + 1] = -1;
			normals[index + 2] = 0;
			
			index = pos * 2;
			texCoords[index] = (x + 1) / 2;
			texCoords[index + 1] = (z + 1) / 2;
			pos++;
		}

		// Side vertices
		float sinAlpha = sin(Math.PI / 2f - Math.acos(1f / Math.sqrt(5)));
		Vector3f normal = new Vector3f();

		for (int s = 0; s <= stacks; s++) {
			float r = (stacks - s) / (float) stacks;
			float y = (2f * s) / stacks - 1f;
			float texCoordV = 1f - s / (float) stacks; 
			for (int ctr2 = 0; ctr2 <= slices; ctr2++) {
				double angle = ctr2 * MathHelper.TWO_PI / slices;
				float x = r * cos(angle);
				float z = r * sin(angle);
				
				index = pos * 3;
				vertices[index] = x;
				vertices[index + 1] = y;
				vertices[index + 2] = z;
				
				normal.set(x, y + sinAlpha, z);
				normal.normalize();
				normals[index] = normal.x;
				normals[index + 1] = normal.y;
				normals[index + 2] = normal.z;

				index = pos * 2;
				texCoords[index] = 1f - ctr2 / (float) slices;
				texCoords[index + 1] = texCoordV;
				pos++;
			}
		}

		// Create the triangles

		// Bottom
		index = 0;
		for (int i = 0; i < slices; i++) {
			triangles[index++] = 0;
			triangles[index++] = i + 1;
			triangles[index++] = i + 2;
		}

		// Side triangles
		for (int j = 0; j < stacks; j++) {
			for (int i = 0; i < slices; i++) {
				int topL = (j + 2) * (slices + 1) + i + 1;
				int topR = topL + 1;
				int botL = (j + 1) * (slices + 1) + i + 1;
				int botR = botL + 1;

				triangles[index++] = topL;
				triangles[index++] = topR;
				triangles[index++] = botL;
				triangles[index++] = botL;
				triangles[index++] = topR;
				triangles[index++] = botR;
			}
		}

		return new MeshData(vertices, triangles, normals, texCoords);
	}

	/**
	 * Creates a cylinder. The cylinder is exactly, 2 units high and 1 unit in
	 * radius. The cylinder is oriented along the y-axis. The number of slices is
	 * the number of subdivisions around the circumference of the cylinder and
	 * stacks is the number of subdivisions along the height.
	 * 
	 * @param stacks
	 * @param slices
	 */
	public static MeshData makeCylinder(int stacks, int slices) {

		// Create all the vertex data
		int size = 2 + (stacks + 3) * (slices + 1);
		float[] vertices = new float[3 * size];
		float[] normals = new float[3 * size];
		float[] texCoords = new float[2 * size];
		int[] triangles = new int[6 * ((stacks + 1) * slices)];
		
		// Create the pole vertices
		int pos = 0;
		vertices[0] = 0;
		vertices[1] = 1;
		vertices[2] = 0;
		normals[0] = 0;
		normals[1] = 1;
		normals[2] = 0;
		texCoords[0] = 0.5f;
		texCoords[1] = 0.5f;
		pos++;
		
		int index = pos * 3;
		vertices[index] = 0;
		vertices[index + 1] = -1;
		vertices[index + 2] = 0;
		normals[index] = 0;
		normals[index + 1] = -1;
		normals[index + 2] = 0;
		index = pos * 2;
		texCoords[index] = 0.5f;
		texCoords[index + 1] = 0.5f;
		pos++;

		// Create the cap vertices must duplicate the edges to get sharp corner
		for (int ctr = 0; ctr <= slices; ctr++) {
			double angle = ctr * MathHelper.TWO_PI / slices;
			float x = cos(angle);
			float z = sin(angle);
			
			index = pos * 3;
			vertices[index] = x;
			vertices[index + 1] = 1;
			vertices[index + 2] = z;
			normals[index] = 0;
			normals[index + 1] = 1;
			normals[index + 2] = 0;
			
			index = pos * 2;
			texCoords[index] = (x + 1) / 2f;
			texCoords[index + 1] = (z + 1) / 2f;
			pos++;
		}

		for (int ctr = 0; ctr <= slices; ctr++) {
			double angle = ctr * MathHelper.TWO_PI / slices;
			float x = cos(angle);
			float z = sin(angle);
			
			index = pos * 3;
			vertices[index] = x;
			vertices[index + 1] = -1;
			vertices[index + 2] = z;
			normals[index] = 0;
			normals[index + 1] = -1;
			normals[index + 2] = 0;
			
			index = pos * 2;
			texCoords[index] = (x + 1) / 2f;
			texCoords[index + 1] = (z + 1) / 2f;
			pos++;
		}

		// Create the intermediate vertices
		for (int ctr1 = 0; ctr1 <= stacks; ctr1++) {
			// The height of the current row
			float h = 1 - 2.0f * ctr1 / stacks;
			float texCoordV = ctr1 / (float) stacks;
			for (int ctr2 = 0; ctr2 <= slices; ctr2++) {
				// The cosine and sine of the zenith
				double angle = ctr2 * MathHelper.TWO_PI / slices;
				float x = cos(angle);
				float z = sin(angle);
				
				// Create the vertices
				index = pos * 3;
				vertices[index] = x;
				vertices[index + 1] = h;
				vertices[index + 2] = z;
				normals[index] = x;
				normals[index + 1] = 0;
				normals[index + 2] = z;
				
				index = pos * 2;
				texCoords[index] = 1f - ctr2 / (float) slices;
				texCoords[index + 1] = texCoordV;
				pos++;

			}
		}

		// Create the triangles
		// Top
		index = 0;
		for (int i = 0; i < slices; i++) {
			triangles[index++] = 0;
			triangles[index++] = i + 2;
			triangles[index++] = i + 3;
		}

		// Bottom
		int bRowOffset = 2 + slices + 1;
		for (int i = 0; i < slices; i++) {
			triangles[index++] = 1;
			triangles[index++] = i + bRowOffset;
			triangles[index++] = i + bRowOffset + 1;
		}

		// Middle
		for (int j = 0; j < stacks; j++) {
			for (int i = 0; i < slices; i++) {
				int topL = (j + 2) * (slices + 1) + i + 2;
				int topR = topL + 1;
				int botL = (j + 3) * (slices + 1) + i + 2;
				int botR = botL + 1;

				triangles[index++] = topL;
				triangles[index++] = topR;
				triangles[index++] = botL;
				
				triangles[index++] = botL;
				triangles[index++] = topR;
				triangles[index++] = botR;
			}
		}

		return new MeshData(vertices, triangles, normals, texCoords);
	}

	/**
	 * Makes a 1 unit radius sphere centered at the origin. The number of slices
	 * is the number of divisions along the equator and stacks is the number of
	 * divisions along any meridian.
	 * 
	 * @param stacks
	 * @param slices
	 */
	public static MeshData makeSphere(int stacks, int slices) {
		// Create all the vertex data
		int size = (stacks + 1) * (slices + 1);

		// Slices have 20 vertices each
		float[] vertices = new float[3 * size];
		float[] normals = new float[3 * size];
		float[] texCoords = new float[2 * size];
		int[] triangles = new int[6 * slices * stacks];
		
		// Create the intermediate vertices
		for (int ctr1 = 0, pos = 0; ctr1 <= stacks; ctr1++) {
			// The cosine and sine of the azimuth
			double azimuth = ctr1 * Math.PI / stacks;
			float cHeight1 = cos(azimuth);
			float sHeight1 = sin(azimuth);

			// Must duplicate the vertices on the wrapped edge to get the
			// textures to come out right
			for (int ctr2 = 0; ctr2 <= slices; ctr2++) {

				// The cosine and sine of the zenith
				double zenith = ctr2 * MathHelper.TWO_PI / slices;
				float cAcross1 = cos(zenith);
				float sAcross1 = sin(zenith);

				int index = pos * 3;
				
				float x = cAcross1 * sHeight1;
				float y = cHeight1;
				float z = sAcross1 * sHeight1;
				vertices[index] = x;
				vertices[index + 1] = y;
				vertices[index + 2] = z;

				float l = 1.0f / (float) Math.sqrt(x * x + y * y + z * z);

				normals[index] = x * l;
				normals[index + 1] = y * l;
				normals[index + 2] = z * l;
				
				index = pos * 2;
				texCoords[index] = 1.0f - (ctr2 / (float) slices);
				texCoords[index + 1] = ctr1 / (float) stacks;

				pos++;
			}
		}
		
		// Sets up the indices.
		for (int j = 0, index = 0; j < stacks; j++) {
			for (int i = 0; i < slices; i++) {
				int topL = j * (slices + 1) + i;
				int topR = topL + 1;
				int botL = ((j + 1) * (slices + 1) + i);
				int botR = botL + 1;

				triangles[index++] = topL;
				triangles[index++] = topR;
				triangles[index++] = botL;
				triangles[index++] = botL;
				triangles[index++] = topR;
				triangles[index++] = botR;
			}
		}
		return new MeshData(vertices, triangles, normals, texCoords);
	}

	/**
	 * Quick casting version of cosine
	 * 
	 * @param d
	 * @return
	 */
	private static float cos(double d) {
		return (float) Math.cos(d);
	}

	/**
	 * Quick casting version of sine
	 * 
	 * @param d
	 * @return
	 */
	private static float sin(double d) {
		return (float) Math.sin(d);
	}
}
