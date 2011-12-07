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
package gwt.g3d.resources.client.impl;

import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.array.Float32Array;
import gwt.g3d.client.gl2.array.TypeArray;
import gwt.g3d.client.gl2.array.Uint16Array;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.resources.client.MeshResource;

import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

/**
 * Abstract implementation of {@link MeshResource}
 * 
 * @author hao1300@gmail.com
 */
public abstract class AbstractMeshResource implements MeshResource {
	public static final String POSITION_FIELD = "vertexPositions";
	public static final String NORMAL_FIELD = "vertexNormals";
	public static final String TEX_COORD_FIELD = "vertexTextureCoords";
	public static final String INDICES_FIELD = "indices";
	
	private final TypeArray positions, normals, texCoords, indices;
	private final MeshDataInfo meshDataInfo;
	
	public AbstractMeshResource(MeshDataInfo meshDataInfo, JSONValue meshValue) {
		this.meshDataInfo = meshDataInfo;
		JSONObject jsonObj = meshValue.isObject();
		positions = Float32Array.create(jsonObj.get(POSITION_FIELD)
				.isArray().getJavaScriptObject().<JsArrayNumber>cast());
		normals = Float32Array.create(jsonObj.get(NORMAL_FIELD)
				.isArray().getJavaScriptObject().<JsArrayNumber>cast());
		texCoords = Float32Array.create(jsonObj.get(TEX_COORD_FIELD)
				.isArray().getJavaScriptObject().<JsArrayNumber>cast());
		indices = Uint16Array.create(jsonObj.get(INDICES_FIELD)
				.isArray().getJavaScriptObject().<JsArrayInteger>cast());
	}
	
	@Override
	public StaticMesh createMesh(GL2 gl) {
		StaticMesh mesh = new StaticMesh(gl, null, null, null, indices);
		mesh.setPositionData(positions, meshDataInfo.getPositionDataType(), 
				meshDataInfo.getPositionDataSize());
		mesh.setNormalData(normals, meshDataInfo.getNormalDataType(), 
				meshDataInfo.getNormalDataSize(), meshDataInfo.isNormalized());
		mesh.setTexCoordData(texCoords, meshDataInfo.getTexCoordDataType(), 
				meshDataInfo.getTexCoordDataSize());
		return mesh;
	}
}
