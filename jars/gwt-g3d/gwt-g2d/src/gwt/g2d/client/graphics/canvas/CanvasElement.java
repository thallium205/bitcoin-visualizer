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
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

/**
 * Interface for the canvas element.
 * 
 * @author hao1300@gmail.com
 */
@TagName(CanvasElement.TAG)
public class CanvasElement extends Element {
	
	static final String TAG = "canvas";
	
	 /**
   * Assert that the given {@link Element} is compatible with this class and
   * automatically typecast it.
   */
  public static CanvasElement as(Element elem) {
    assert elem.getTagName().equalsIgnoreCase(TAG);
    return (CanvasElement) elem;
  } 
	
	protected CanvasElement() {
		
	}
	
	/**
	 * Gets the width of the canvas.
	 */
	public native final int getWidth() /*-{
		return this.width;
	}-*/;
	
	/**
	 * Gets the height of the canvas.
	 */
	public native final int getHeight() /*-{
		return this.height;
	}-*/;
	
	/**
	 * Gets the context for rendering onto the canvas.
	 */
	public native final Context getContext2D() /*-{
		return this.getContext("2d");
	}-*/;
	
	/**
	 * Gets the context for rendering onto the canvas.
	 */
	public native final <T extends JavaScriptObject> T getContext(String contextId) /*-{
		return this.getContext(contextId);
	}-*/;
	
	/**
	 * Gets the context for rendering onto the canvas.
	 * 
	 * @param extra defines some extra property for initializing the context.
	 */
	public native final <T extends JavaScriptObject> T getContext(String contextId,
			Object extra) /*-{
		return this.getContext(contextId, extra);
	}-*/;
	
	/**
	 * Return a data: URL containing a representation of the image as a PNG file.
	 */
	public native final String toDataURL() /*-{
		return this.toDataURL();
	}-*/;
	
	/**
	 * Return a data: URL containing a representation of the image as a the given
	 * type.
	 */
	public native final String toDataURL(String type) /*-{
		return this.toDataURL(type);
	}-*/;
	
	/**
	 * Sets the width of the canvas.
	 */
	public native final void setWidth(int width) /*-{
		this.width = width;
	}-*/;
	
	/**
	 * Sets the height of the canvas.
	 */
	public native final void setHeight(int height) /*-{
		this.height = height;
	}-*/;
}
