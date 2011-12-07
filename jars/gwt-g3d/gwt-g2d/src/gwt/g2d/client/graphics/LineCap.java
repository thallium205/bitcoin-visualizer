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
 * Defines the type of endings that UAs will place on the end of lines.
 * 
 * @see <a href="http://dev.w3.org/html5/spec/Overview.html#dom-context-2d-linecap">
 * http://dev.w3.org/html5/spec/Overview.html#dom-context-2d-linecap</a>
 * 
 * @author hao1300@gmail.com
 */
public enum LineCap {
	/**
	 * This value means that the end of each line has a flat edge perpendicular 
	 * to the direction of the line (and that no additional line cap is added).
	 * (This is the default {@link LineCap} value)
	 */
	BUTT("butt"),
	/**
	 * The round value means that a semi-circle with the diameter equal to the 
	 * width of the line must then be added on to the end of the line.
	 */
	ROUND("round"),
	/**
	 * The square value means that a rectangle with the length of the line width 
	 * and the width of half the line width, placed flat against the edge 
	 * perpendicular to the direction of the line, must be added at the end of 
	 * each line.
	 */
	SQUARE("square");
	
	private static Map<String, LineCap> lineCapMap;
	private final String lineCapName;
	
	private LineCap(String lineCapName) {
		this.lineCapName = lineCapName;
	}
	
	@Override
	public String toString() {
		return lineCapName;
	}
	
	/**
	 * Parses a string into a LineCap.
	 * 
	 * @param lineCap
	 */
	public static LineCap parseLineCap(String lineCap) {
		if (lineCapMap == null) {
			lineCapMap = new HashMap<String, LineCap>();
			for (LineCap v : values()) {
				lineCapMap.put(v.toString(), v);
			}
		}
		return lineCapMap.get(lineCap);
	}
}
