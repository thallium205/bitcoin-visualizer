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
 * Determines the text alignment settings.
 * 
 * @see <a href="http://dev.w3.org/html5/spec/Overview.html#dom-context-2d-textalign">
 * http://dev.w3.org/html5/spec/Overview.html#dom-context-2d-textalign</a>
 * 
 * @author hao1300@gmail.com
 */
public enum TextAlign {
	/**
	 * Let the anchor point's horizontal position be the left edge of the inline
	 * box if the direction is 'ltr', else let the anchor point's horizontal 
	 * position be the right edge of the inline box if the direction is 'rtl'.
	 * (This is the default {@link TextAlign} value).
	 */
	START("start"),
	/**
	 * Let the anchor point's horizontal position be the left edge of the inline
	 * box if the direction is 'rtl', else let the anchor point's horizontal 
	 * position be the right edge of the inline box if the direction is 'ltr'.
	 */
	END("end"),
	/**
	 * Let the anchor point's horizontal position be the left edge of the 
	 * inline box.
	 */
	LEFT("left"),
	/**
	 * Let the anchor point's horizontal position be the right edge of the inline
	 * box.
	 */
	RIGHT("right"),
	/**
	 * Let the anchor point's horizontal position be half way between the left 
	 * and right edges of the inline box.
	 */
	CENTER("center");
	
	private static Map<String, TextAlign> textAlignMap;
	private final String textAlignName;
	
	private TextAlign(String textAlignName) {
		this.textAlignName = textAlignName;
	}
	
	@Override
	public String toString() {
		return textAlignName;
	}
	
	/**
	 * Parses a string into a TextAlign.
	 * 
	 * @param textAlign
	 */
	public static TextAlign parseTextAlign(String textAlign) {
		if (textAlignMap == null) {
			textAlignMap = new HashMap<String, TextAlign>();
			for (TextAlign v : values()) {
				textAlignMap.put(v.toString(), v);
			}
		}
		return textAlignMap.get(textAlign);
	}
}