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
import gwt.g3d.client.mesh.StaticMesh;

import com.google.gwt.resources.client.ResourcePrototype;

/**
 * Represents a resource that stores information about a mesh.
 * This should only be used as part of {@link ExternalMeshResource}.
 * 
 * @author hao1300@gmail.com
 */
public interface MeshResource extends ResourcePrototype {

	/**
	 * Creates a static mesh out of the mesh data in this resource.
	 * 
	 * @param gl
	 */
	StaticMesh createMesh(GL2 gl);
}
