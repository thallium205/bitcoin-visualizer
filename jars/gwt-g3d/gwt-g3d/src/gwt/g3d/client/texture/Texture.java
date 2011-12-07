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
package gwt.g3d.client.texture;

import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.GLDisposable;

/**
 * Interfaces for a WebGLTexture
 * 
 * @author hao1300@gmail.com
 */
public interface Texture extends GLDisposable {
	
	/**
	 * Binds this texture to GL.
	 */
	void bind();
	
	/**
	 * Initializes this texture.
	 * This method must be called before accessing any method in this texture. 
	 * If a texture is disposed, it can be used again when init is called.
	 * 
	 * @param gl
	 */
	void init(GL2 gl);
}
