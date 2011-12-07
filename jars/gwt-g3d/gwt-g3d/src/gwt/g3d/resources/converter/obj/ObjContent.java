package gwt.g3d.resources.converter.obj;

import gwt.g3d.resources.client.impl.AbstractMeshResource;
import gwt.g3d.resources.client.impl.MeshDataInfo;
import gwt.g3d.resources.converter.ParsedContent;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.vecmath.Point3i;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4d;

/**
 * Stores the parsed obj file contents.
 * 
 * @author hao1300@gmail.com
 */
public class ObjContent implements ParsedContent {
	private static final int DEFAULT_CAPACITY = 500;
	private final List<Vector4d> vertices = new ArrayList<Vector4d>(DEFAULT_CAPACITY);
	private final List<Vector3d> normals = new ArrayList<Vector3d>(DEFAULT_CAPACITY);
	private final List<Vector3d> textureCoords = new ArrayList<Vector3d>(DEFAULT_CAPACITY);
	
	/** Hash a face index to a corresponding index. */
	private final Map<Point3i, Integer> indexMap = new HashMap<Point3i, Integer>(DEFAULT_CAPACITY);
	
	/** List of faces as declared by the obj file. */
	private final List<Point3i> faces = new ArrayList<Point3i>(DEFAULT_CAPACITY);
	
	/**
	 * Adds a vertex.
	 * 
	 * @param vertex
	 */
	public void addVertex(Vector4d vertex) {
		vertices.add(vertex);
	}
	
	/**
	 * Adds a vertex normal.
	 * 
	 * @param normal
	 */
	public void addNormal(Vector3d normal) {
		normals.add(normal);
	}
	
	/**
	 * Adds a vertex texture coordinate.
	 * 
	 * @param textureCoord
	 */
	public void addTextureCoord(Vector3d textureCoord) {
		textureCoords.add(textureCoord);
	}
	
	/**
	 * Adds a face.
	 * 
	 * @param face
	 */
	public void addFace(Point3i face) {
		faces.add(face);
	}
	
	@Override
	public void export(MeshDataInfo meshDataInfo, Writer writer) throws IOException {
		indexMap.clear();
		hashIndices();
		
		writer.write("{\n");
		
		writer.write("\"" + AbstractMeshResource.POSITION_FIELD + "\" : [");
		writeVertices(meshDataInfo, writer);
		writer.write("],\n");
		
		writer.write("\"" + AbstractMeshResource.NORMAL_FIELD + "\" : [");
		writeNormals(meshDataInfo, writer);
		writer.write("],\n");
		
		writer.write("\"" + AbstractMeshResource.TEX_COORD_FIELD + "\" : [");
		writeTextureCoords(meshDataInfo, writer);
		writer.write("],\n");
		
		writer.write("\"" + AbstractMeshResource.INDICES_FIELD + "\" : [");
		writeIndices(writer);
		writer.write("]\n");
		
		writer.write("}");
	}

	/**
	 * Go through the list of face index and hash them.
	 */
	private void hashIndices() {
		for (Point3i face : faces) {
			if (!indexMap.containsKey(face)) {
				indexMap.put(face, indexMap.size());
			}
		}
	}
	
	/**
	 * Writes the indices of the faces.
	 *
	 * @throws IOException
	 */
	private void writeIndices(Writer writer) throws IOException {
		boolean isFirst = true;
		for (Point3i face : faces) {
			if (isFirst) {
				isFirst = false;
			} else {
				writer.write(",");
			}
			writer.write(indexMap.get(face).toString());
		}
	}

	/**
	 * Writes the list of vertices from the starting index to the end index of 
	 * the faces.
	 * 
	 * @param writer
	 * @throws IOException 
	 */
	private void writeVertices(MeshDataInfo meshDataInfo, Writer writer) 
			throws IOException {
		final int dataSize = meshDataInfo.getPositionDataSize();
		int k = 0;
		for (Point3i face : faces) {
			int vertexIndex = indexMap.get(face);
			if (vertexIndex == k) {
				if (k != 0) {
					writer.write(",");
				}
				writeVector(writer, vertices.get(face.x - 1), dataSize);
				k++;
			}
		}
	}
	
	/**
	 * Writes the list of normals from the starting index to the end index of 
	 * the faces.
	 * 
	 * @param writer
	 * @throws IOException 
	 */
	private void writeNormals(MeshDataInfo meshDataInfo, Writer writer) 
			throws IOException {
		final int dataSize = meshDataInfo.getNormalDataSize();
		int k = 0;
		for (Point3i face : faces) {
			int vertexIndex = indexMap.get(face);
			if (vertexIndex == k && face.z != -1) {
				if (k != 0) {
					writer.write(",");
				}
				writeVector(writer, normals.get(face.z - 1), dataSize);
				k++;
			}
		}
	}
	
	/**
	 * Writes the list of texture coordinates from the starting index to the end 
	 * index of the faces.
	 * 
	 * @param writer
	 * @throws IOException 
	 */
	private void writeTextureCoords(MeshDataInfo meshDataInfo, Writer writer) 
			throws IOException {
		final int dataSize = meshDataInfo.getTexCoordDataSize();
		int k = 0;
		for (Point3i face : faces) {
			int vertexIndex = indexMap.get(face);
			if (vertexIndex == k && face.y != -1) {
				if (k != 0) {
					writer.write(",");
				}
				writeVector(writer, textureCoords.get(face.y - 1), dataSize);
				k++;
			}
		}
	}
	
	/**
	 * Writes a vector4 to the writer.
	 * 
	 * @param writer
	 * @param vector4
	 * @throws IOException 
	 */
	private void writeVector(Writer writer, Vector4d vector4, int dataSize) 
			throws IOException {
		writer.write(Double.toString(vector4.x));
		if (dataSize > 1) {
			writer.write("," + vector4.y);
		}
		if (dataSize > 2) {
			writer.write("," + vector4.z);
		}
		if (dataSize > 3) {
			writer.write("," + vector4.w);
		}
	}
	
	/**
	 * Writes a vector3 to the writer.
	 * 
	 * @param writer
	 * @param vector3
	 * @throws IOException 
	 */
	private void writeVector(Writer writer, Vector3d vector3, int dataSize) 
			throws IOException {
		writer.write(Double.toString(vector3.x));
		if (dataSize > 1) {
			writer.write("," + vector3.y);
		}
		if (dataSize > 2) {
			writer.write("," + vector3.z);
		}
	}
}
