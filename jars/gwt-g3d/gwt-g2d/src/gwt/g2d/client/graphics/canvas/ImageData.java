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
 * Wrapper class for ImageData, which stores the width, height, and the 
 * pixel data of a dynamic image.
 * 
 * @author hao1300@gmail.com
 */
public final class ImageData extends JavaScriptObject {
	
	protected ImageData() {
		
	}
	
	/**
	 * Gets the width of the image data.
	 */
	public native int getWidth() /*-{
		return this.width;
	}-*/;
	
	/**
	 * Gets the height of the image data.
	 */
	public native int getHeight() /*-{
		return this.height;
	}-*/;
	
	/**
	 * Gets the CanvasPixelArray JavaScriptObject from this ImageData.
	 */
	public native CanvasPixelArray getPixelArray() /*-{
		return this.data;
	}-*/;
}
