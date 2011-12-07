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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * A {@link WebGLUnsignedShortArray} represents an array of 16-bit unsigned 
 * integer values.
 * A {@link WebGLUnsignedShortArray} has an element size of 2 bytes.
 * 
 * @author hao1300@gmail.com
 */
@Deprecated
public class WebGLUnsignedShortArray extends WebGLArray {
	public static final int BYTES_PER_ELEMENT = 2;
	
	protected WebGLUnsignedShortArray() {

	}
	
	/**
	 * Create a new {@link WebGLUnsignedShortArray} object of the given length 
	 * with a new underlying {@link WebGLArrayBuffer} large enough to hold length 
	 * elements of the specific type. Data in the buffer is initialized to 0.
	 * 
	 * @param length
	 */
	public static native WebGLUnsignedShortArray createArray(int length) /*-{
		return new $wnd.WebGLUnsignedShortArray(length);
	}-*/;
	
	/**
	 * Create a new {@link WebGLUnsignedShortArray} object with a new underlying 
	 * {@link WebGLArrayBuffer} large enough to hold the given data, then copy 
	 * the passed data into the buffer.
	 * 
	 * @param array
	 */
	public static native WebGLUnsignedShortArray createArray(
			WebGLUnsignedShortArray array) /*-{
		return new $wnd.WebGLUnsignedShortArray(array);
	}-*/;
	
	/**
	 * Create a new {@link WebGLUnsignedShortArray} object with a new underlying 
	 * {@link WebGLArrayBuffer} large enough to hold the given data, then copy 
	 * the passed data into the buffer.
	 * 
	 * @param array
	 */
	public static WebGLUnsignedShortArray createArray(int... array) {
		if (GWT.isScript()) {
			return createArrayCompiled(array);
		}
		return createArray(JsArrayUtils.toJsArrayInteger(array));
	}
	
	private static native WebGLUnsignedShortArray createArrayCompiled(int[] array) /*-{
		return new $wnd.WebGLUnsignedShortArray(array);
	}-*/;
	
	/**
	 * Create a new {@link WebGLUnsignedShortArray} object with a new underlying 
	 * {@link WebGLArrayBuffer} large enough to hold the given data, then copy 
	 * the passed data into the buffer.
	 * 
	 * @param array a JavaScript array
	 */
	public static native WebGLUnsignedShortArray createArray(JavaScriptObject array) /*-{
		return new $wnd.WebGLUnsignedShortArray(array);
	}-*/;
	
	/**
	 * Create a new {@link WebGLUnsignedShortArray} object using the passed 
	 * {@link WebGLArrayBuffer} for its storage. If a given byteOffset and 
	 * length references an area beyond the end of the {@link WebGLArrayBuffer} 
	 * an exception is raised.
	 * 
	 * @param buffer
	 * @param byteOffset indicates the offset in bytes from the start of 
	 * 				the {@link WebGLArrayBuffer}. must be a multiple of the element 
	 * 				size of the specific type, otherwise an exception is raised.
	 * @param length the count of elements from the offset that this 
	 * 				{@link WebGLUnsignedShortArray} will reference
	 */
	public static native WebGLUnsignedShortArray createArray(
			WebGLArrayBuffer buffer, int byteOffset, int length) /*-{
		return new $wnd.WebGLUnsignedShortArray(buffer, byteOffset, length);
	}-*/;
	
	/**
	 * Return the element at the given index. If the index is out of range, 
	 * an exception is raised. This is an index getter function, and may be 
	 * invoked via array index syntax where applicable.
	 * 
	 * @param index
	 */
	public native final int get(int index) /*-{
		return this[index];
	}-*/;
	
	/**
	 * Sets the element at the given index to the given value. If the index is 
	 * out of range, an exception is raised. This is an index setter function, 
	 * and may be invoked via array index syntax where applicable.
	 * 
	 * If the given value is out of range for the type of the array, a C-style 
	 * cast operation is performed to coerce the value to the valid range. No 
	 * clamping or rounding is performed.
	 * 
	 * @param index
	 * @param value
	 */
	public native final void set(int index, int value) /*-{
		this[index] = value;
	}-*/;
}
