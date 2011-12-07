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
package gwt.g3d.client.gl2.array;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The WebGLArrayBuffer interface describes the buffer used to store data for 
 * the WebGLArray interface and its subclasses.
 * 
 * @author hao1300@gmail.com
 */
@Deprecated
public class WebGLArrayBuffer extends JavaScriptObject {

	protected WebGLArrayBuffer() {
		
	}
	
	/**
	 * Create a new WebGLArrayBuffer of the passed length in bytes. Data in the 
	 * buffer is initialized to 0.
	 * 
	 * @param length
	 */
	public static native WebGLArrayBuffer createBuffer(int length) /*-{
		return new $wnd.WebGLArrayBuffer(length);
	}-*/;
	
	/**
	 * Gets the length of the buffer in bytes. The length is fixed when the 
	 * WebGLArrayBuffer is created.
	 */
	public native final int getByteLength() /*-{
		return this.byteLength;
	}-*/;
}
