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
package gwt.g3d.client.gl2;

/**
 * The WebGLShader interface represents an OpenGL Shader Object. The underlying 
 * object is created as if by calling glCreateShader , attached to a Program as 
 * if by calling glAttachShader and destroyed as if by calling glDeleteShader.
 * 
 * @author hao1300@gmail.com
 */
public class WebGLShader extends WebGLObject {

	protected WebGLShader() {
		
	}
}