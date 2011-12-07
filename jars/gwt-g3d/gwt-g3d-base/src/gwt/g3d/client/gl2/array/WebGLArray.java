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
 * The WebGLArray interface is the abstract base interface for all the typed 
 * array interfaces. Every WebGLArray subclass presents a typed view of a 
 * WebGLArrayBuffer region. Multiple WebGLArrays can reference the same or 
 * overlapping regions of a WebGLArrayBuffer.
 * 
 * @author hao1300@gmail.com
 */
@Deprecated
public abstract class WebGLArray extends JavaScriptObject {
	
	protected WebGLArray() {
		
	}
	
	/**
	 * Gets the WebGLArrayBuffer holding the data for this array.
	 */
	public native final WebGLArrayBuffer getBuffer() /*-{
		return this.buffer;
	}-*/;
	
	/**
	 * Gets he offset of this data, in bytes, from the start of this WebGLArray's 
	 * WebGLArrayBuffer.
	 */
	public native final int getByteOffset() /*-{
		return this.byteOffset;
	}-*/;
	
	/**
	 * Gets the length of this data in bytes.
	 */
	public native final int getByteLength() /*-{
		return this.byteLength;
	}-*/;
	
	/**
	 * Gets the length of this data in elements.
	 */
	public native final int getLength() /*-{
		return this.length;
	}-*/;
	
	/**
	 * Returns a new WebGLArray view of the WebGLArrayBuffer store for this 
	 * WebGLArray, referencing the element at offset in the current view, and 
	 * containing length elements.
	 * 
	 * The returned WebGLArray will be of the same underlying type as the 
	 * original WebGLArray.
	 * 
	 * @param offset
	 * @param length
	 */
	public native final WebGLArray slice(int offset, int length) /*-{
		return this.slice(offset, length);
	}-*/;
	
	/**
	 * Set multiple values, reading input values from the array. 
	 */
	public native final void set(WebGLArray array) /*-{
		this.set(array);
	}-*/;
	
	/**
	 * Set multiple values, reading input values from the array. 
	 * The offset value indicates the index in the current array where values are 
	 * written.
	 */
	public native final void set(WebGLArray array, int offset) /*-{
		this.set(array, offset);
	}-*/;
}
