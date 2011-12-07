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
 * Determines the text baseline alignment settings.
 * 
 * @see <a href="http://dev.w3.org/html5/spec/Overview.html#dom-context-2d-textbaseline">
 * http://dev.w3.org/html5/spec/Overview.html#dom-context-2d-textbaseline</a>
 * 
 * @author hao1300@gmail.com
 */
public enum TextBaseline {
	/**
	 * Let the anchor point's vertical position be the top of the em box of the 
	 * first available font of the inline box.
	 */
	TOP("top"),
	/**
	 * Let the anchor point's vertical position be the hanging baseline of the 
	 * first available font of the inline box.
	 */
	HANGING("hanging"),
	/**
	 * Let the anchor point's vertical position be half way between the bottom 
	 * and the top of the em box of the first available font of the inline box.
	 */
	MIDDLE("middle"),
	/**
	 * Let the anchor point's vertical position be the alphabetic baseline of the
	 * first available font of the inline box.
	 * This is the default TextBaseline.
	 */
	ALPHABETIC("alphabetic"),
	/**
	 * Let the anchor point's vertical position be the ideographic baseline of 
	 * the first available font of the inline box.
	 */
	IDEOGRAPHIC("ideographic"),
	/**
	 * Let the anchor point's vertical position be the bottom of the em box of
	 * the first available font of the inline box.
	 */
	BOTTOM("bottom");
	
	private static Map<String, TextBaseline> textBaselineMap;
	private final String textBaselineName;
	
	private TextBaseline(String textBaselineName) {
		this.textBaselineName = textBaselineName;
	}
	
	@Override
	public String toString() {
		return textBaselineName;
	}
	
	/**
	 * Parses a string into a TextAlign.
	 * 
	 * @param textBaseline
	 */
	public static TextBaseline parseTextBaseline(String textBaseline) {
		if (textBaselineMap == null) {
			textBaselineMap = new HashMap<String, TextBaseline>();
			for (TextBaseline v : values()) {
				textBaselineMap.put(v.toString(), v);
			}
		}
		return textBaselineMap.get(textBaseline);
	}
}