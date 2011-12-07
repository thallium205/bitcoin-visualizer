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
package gwt.g2d.client.graphics;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * Defines the type of corners that UAs will place where two lines meet.
 * <p>
 * A join exists at any point in a subpath shared by two consecutive lines. 
 * When a subpath is closed, then a join also exists at its first point 
 * (equivalent to its last point) connecting the first and last lines in the 
 * subpath.
 * 
 * @see <a href="http://dev.w3.org/html5/spec/Overview.html#dom-context-2d-linejoin">
 * http://dev.w3.org/html5/spec/Overview.html#dom-context-2d-linejoin</a>
 * 
 * @author hao1300@gmail.com
 */
public enum LineJoin {
	/**
	 * This value means that this is all that is rendered at joins.
	 */
	BEVEL("bevel"),
	/**
	 * This value means that a second filled triangle must (if it can given the 
	 * miter length) be rendered at the join, with one line being the line 
	 * between the two aforementioned corners, abutting the first triangle, and 
	 * the other two being continuations of the outside edges of the two joining 
	 * lines, as long as required to intersect without going over the miter 
	 * length.
	 * (This is the default {@link LineJoin} value).
	 */
	MITER("miter"),
	/**
	 * This value means that a filled arc connecting the two aforementioned 
	 * corners of the join, abutting (and not overlapping) the aforementioned 
	 * triangle, with the diameter equal to the line width and the origin at the 
	 * point of the join, must be rendered at joins.
	 */
	ROUND("round");
	
	private static Map<String, LineJoin> lineJoinMap;
	private final String lineJoinName;
	
	private LineJoin(String lineJoinName) {
		this.lineJoinName = lineJoinName;
	}
	
	@Override
	public String toString() {
		return lineJoinName;
	}
	
	/**
	 * Parses a string into a LineJoin.
	 * 
	 * @param lineJoin
	 */
	public static LineJoin parseLineJoin(String lineJoin) {
		if (lineJoinMap == null) {
			lineJoinMap = new HashMap<String, LineJoin>();
			for (LineJoin v : values()) {
				lineJoinMap.put(v.toString(), v);
			}
		}
		return lineJoinMap.get(lineJoin);
	}
}