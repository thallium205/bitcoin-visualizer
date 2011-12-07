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
package gwt.g3d.resources.client;

import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.shader.AbstractShader;
import gwt.g3d.client.shader.ShaderException;
import gwt.g3d.resources.ShaderResourceGenerator;

import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.resources.ext.DefaultExtensions;
import com.google.gwt.resources.ext.ResourceGeneratorType;

/**
 * Represents a resource that creates a {@link AbstractShader} from a vertex 
 * shader and a fragment shader.
 * The source for a ShaderResource has to be a vertex shader followed by a
 * fragment shader.
 * 
 * @author hao1300@gmail.com
 */
@DefaultExtensions(value = {".txt"})
@ResourceGeneratorType(ShaderResourceGenerator.class)
public interface ShaderResource extends ResourcePrototype {

	/**
	 * Creates a shader using the declared vertex shader and fragment shader
	 * as source.
	 * 
	 * @param gl
	 */
	AbstractShader createShader(GL2 gl) throws ShaderException;
	
	/**
	 * Gets the source code for the vertex shader.
	 */
	String getVertexShaderSource();
	
	/**
	 * Gets the source code for the fragment shader.
	 */
	String getFragmentShaderSource();
}
