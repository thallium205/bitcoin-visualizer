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

import gwt.g3d.client.gl2.WebGLObject;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Represents an array of WebGLObject objects.
 * 
 * @author hao1300@gmail.com
 */
public class WebGLObjectArray<T extends WebGLObject> extends JavaScriptObject {

	protected WebGLObjectArray() {
		
	}
	
	/**
	 * Gets the number of objects in the array.
	 */
	public native final int getLength() /*-{
		return this.length;
	}-*/;
	
	/**
	 * Gets the WebGLObject at the given index.
	 * 
	 * @param index
	 */
  public native final T get(int index) /*-{
  	return this[index];
  }-*/;
}
