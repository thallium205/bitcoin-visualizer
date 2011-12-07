/*
 * Copyright 2010 Hao Nguyen
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

import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;

/**
 * Helper class for converting from Java arrays to JavaScript arrays 
 * ({@link JsArrayNumber} and {@link JsArrayInteger}).
 * 
 * @author hao1300@gmail.com
 */
public class JsArrayUtils {
	
	/**
	 * Converts from a Java array to a JavaScript array.
	 * 
	 * @param array the Java array to convert from.
	 * @return the equivalent JavaScript array.
	 */
	public static JsArrayNumber toJsArrayNumber(float... array) {
		JsArrayNumber jsArray = JsArrayNumber.createArray().cast();
		for (float v : array) {
			jsArray.push(v);
		}
		return jsArray;
	}
	
	/**
	 * Converts from a Java array to a JavaScript array.
	 * 
	 * @param array the Java array to convert from.
	 * @return the equivalent JavaScript array.
	 */
	public static JsArrayNumber toJsArrayNumberFromDouble(double... array) {
		JsArrayNumber jsArray = JsArrayNumber.createArray().cast();
		for (double v : array) {
			jsArray.push(v);
		}
		return jsArray;
	}
	
	/**
	 * Converts from a Java array to a JavaScript array.
	 * 
	 * @param array the Java array to convert from.
	 * @return the equivalent JavaScript array.
	 */
	public static JsArrayInteger toJsArrayInteger(int... array) {
		JsArrayInteger jsArray = JsArrayInteger.createArray().cast();
		for (int v : array) {
			jsArray.push(v);
		}
		return jsArray;
	}
}
