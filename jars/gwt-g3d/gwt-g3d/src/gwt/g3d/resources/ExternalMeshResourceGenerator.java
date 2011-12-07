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
package gwt.g3d.resources;

import gwt.g3d.client.gl2.enums.DataType;
import gwt.g3d.resources.client.ExternalMeshResource;
import gwt.g3d.resources.client.MeshResource;
import gwt.g3d.resources.client.NormalArrayInfo;
import gwt.g3d.resources.client.PositionArrayInfo;
import gwt.g3d.resources.client.TexCoordArrayInfo;
import gwt.g3d.resources.client.impl.AbstractExternalMeshResource;
import gwt.g3d.resources.client.impl.MeshDataInfo;
import gwt.g3d.resources.converter.ParserException;
import gwt.g3d.resources.converter.obj.ObjParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.dev.util.Util;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.ext.AbstractResourceGenerator;
import com.google.gwt.resources.ext.ResourceContext;
import com.google.gwt.resources.ext.ResourceGeneratorUtil;
import com.google.gwt.user.rebind.SourceWriter;
import com.google.gwt.user.rebind.StringSourceWriter;

/**
 * Generator for {@link ExternalMeshResource}.
 * 
 * @author hao1300@gmail.com
 */
public final class ExternalMeshResourceGenerator 
		extends AbstractResourceGenerator {
	
	@Override
	public String createAssignment(TreeLogger logger, ResourceContext context,
			JMethod method) throws UnableToCompleteException {
		URL[] resources = ResourceGeneratorUtil.findResources(logger, context,
        method);
		
		if (resources.length != 1) {
      logger.log(TreeLogger.ERROR, "Exactly one resource must be specified",
          null);
      throw new UnableToCompleteException();
    }
		
		MeshDataInfo meshDataInfo = getMeshDataInfo(method);
		
		String url = null;
		URL modelUrl = resources[0];
		if (modelUrl.getFile().endsWith(".obj")) {
			try {
				String content = Util.readURLAsString(modelUrl);
				File outFile = File.createTempFile("temp", ".json");
				BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
				new ObjParser().parse(content).export(meshDataInfo, writer);
				writer.close();
				url = context.deploy(outFile.toURI().toURL(), true);
			} catch (ParserException e) {
				e.printStackTrace();
				throw new UnableToCompleteException();
			} catch (IOException e) {
				e.printStackTrace();
				throw new UnableToCompleteException();
			} 
		} else if (modelUrl.getFile().endsWith(".json")) {
			url = context.deploy(modelUrl, true);		
		} else {
			// Unsupported format.
			throw new UnableToCompleteException();
		}
		
		SourceWriter sw = new StringSourceWriter();
		sw.println(String.format("new %s() {",
				AbstractExternalMeshResource.class.getName()));
		sw.indent();
		
		// Creates method: void getMesh(ResourceCallback<MeshResource>);
		sw.println(String.format("public void getMesh(%s<%s> callback) {",
				ResourceCallback.class.getName(), 
				MeshResource.class.getName()));
		sw.indent();
		
		// Creates the MeshDataInfo.
		sw.println(String.format("%s meshDataInfo = new %s();", 
				MeshDataInfo.class.getName(),
				MeshDataInfo.class.getName()));
		
		sw.println(String.format("meshDataInfo.setPositionDataType(%s.%s);",
				DataType.class.getName(), meshDataInfo.getPositionDataType()));
		sw.println(String.format("meshDataInfo.setPositionDataSize(%s);",
				meshDataInfo.getPositionDataSize()));

		sw.println(String.format("meshDataInfo.setNormalDataType(%s.%s);",
				DataType.class.getName(), meshDataInfo.getNormalDataType()));
		sw.println(String.format("meshDataInfo.setNormalDataSize(%s);",
				meshDataInfo.getNormalDataSize()));
		sw.println(String.format("meshDataInfo.setNormalized(%s);",
				meshDataInfo.isNormalized()));
		
		sw.println(String.format("meshDataInfo.setTexCoordDataType(%s.%s);",
			DataType.class.getName(), meshDataInfo.getTexCoordDataType()));
		sw.println(String.format("meshDataInfo.setTexCoordDataSize(%s);",
				meshDataInfo.getTexCoordDataSize()));
		
		sw.println(String.format("getMesh(%s, meshDataInfo, callback);", url));
		sw.outdent();
		sw.println("}");
		
		// Creates method: String getName();
		sw.println("public String getName() {");
		sw.indent();
		sw.println("return \"" + method.getName() + "\";");
		sw.outdent();
		sw.println("}");
		
		sw.outdent();
		sw.println("}");
		
		return sw.toString();
	}
	
	/**
	 * Gets the mesh data info from the annotation of the given method.
	 */
	private MeshDataInfo getMeshDataInfo(JMethod method) {
		MeshDataInfo meshDataInfo = new MeshDataInfo();
		PositionArrayInfo positionArrayInfo = method.getAnnotation(PositionArrayInfo.class);
		if (positionArrayInfo != null) {
			meshDataInfo.setPositionDataType(positionArrayInfo.dataType());
			meshDataInfo.setPositionDataSize(positionArrayInfo.dataSize());
		}
		
		NormalArrayInfo normalArrayInfo = method.getAnnotation(NormalArrayInfo.class);
		if (normalArrayInfo != null) {
			meshDataInfo.setNormalDataType(normalArrayInfo.dataType());
			meshDataInfo.setNormalDataSize(normalArrayInfo.dataSize());
			meshDataInfo.setNormalized(normalArrayInfo.isNormalized());
		}
	
		TexCoordArrayInfo texCoordArrayInfo = method.getAnnotation(TexCoordArrayInfo.class);
		if (texCoordArrayInfo != null) {
			meshDataInfo.setTexCoordDataType(texCoordArrayInfo.dataType());
			meshDataInfo.setTexCoordDataSize(texCoordArrayInfo.dataSize());
		}
		return meshDataInfo;
	}
}
