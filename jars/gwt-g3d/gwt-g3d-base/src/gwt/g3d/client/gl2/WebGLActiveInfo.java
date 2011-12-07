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

import gwt.g3d.client.gl2.enums.DataType;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The WebGLActiveInfo interface represents the information returned from the 
 * getActiveAttrib and getActiveUniform calls.
 * 
 * @author hao1300@gmail.com
 */
public final class WebGLActiveInfo extends JavaScriptObject {
	protected WebGLActiveInfo() {
		
	}
	
	/**
	 * Gets the size of the requested variable.
	 */
	public native int getSize() /*-{
		return this.size;
	}-*/;
	
	/**
	 * Gets the data type of the requested variable.
	 */
	public DataType getType() {
		return DataType.parseDataType(getTypeImpl());
	}
	
	/**
	 * Gets the name of the requested variable.
	 */
	public native String getName() /*-{
		return this.name;
	}-*/;
	
	private native int getTypeImpl() /*-{
		return this.type;
	}-*/;
}
