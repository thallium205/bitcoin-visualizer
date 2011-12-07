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

import gwt.g3d.resources.ExternalMeshResourceGenerator;

import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.resources.ext.DefaultExtensions;
import com.google.gwt.resources.ext.ResourceGeneratorType;

/**
 * Represents a resource that stores information about a mesh.
 * 
 * @author hao1300@gmail.com
 */
@DefaultExtensions(value = {".obj", ".json"})
@ResourceGeneratorType(ExternalMeshResourceGenerator.class)
public interface ExternalMeshResource extends ResourcePrototype {


	/**
	 * Asynchronously loads the mesh resource.
	 * 
	 * @param meshResource
	 */
	void getMesh(ResourceCallback<MeshResource> meshResource);
}
