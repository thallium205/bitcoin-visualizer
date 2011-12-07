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
package gwt.g2d.client.graphics.canvas;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Adapter for accessing the canvas pixel array object.
 * 
 * @author hao1300@gmail.com
 */
public final class CanvasPixelArray extends JavaScriptObject {
	
	/**
	 * Casts the JavaScriptObject into a CanvasPixelArray.
	 * Requires: the given JavaScriptObject must be an instance of 
	 * CanvasPixelArray.
	 * 
	 * @param pixelArray
	 */
	public static CanvasPixelArray as(JavaScriptObject pixelArray) {
		return pixelArray.cast();
	}
	
	protected CanvasPixelArray() {

	}
	
	/**
	 * Gets the length the pixel array, which is height * width * 4.
	 */
	public native int getLength() /*-{
		return this.length;
	}-*/;
	
	/**
	 * Gets the pixel color value at the given index. 
	 * 
	 * @param index 0 based index. index 0 to 3 represents the R, G, B, A values 
	 * of the top left pixel, and index 4 to 7 represents the color values of the
	 * next pixel to the right of the top left pixel.
	 * @return the color value at the given index.
	 */
	public native int getData(int index) /*-{
		return this[index];
	}-*/;
	
	/**
	 * Sets the pixel color value at the given index. 
	 * 
	 * @param index 0 based index. index 0 to 3 represents the R, G, B, A values 
	 * of the top left pixel, and index 4 to 7 represents the color values of the
	 * next pixel to the right of the top left pixel.
	 * @param data the color value at the given index.
	 */
	public native void setData(int index, int data) /*-{
		this[index] = data;
	}-*/;
}
