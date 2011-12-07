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
 * Determines how shapes and images are drawn onto the existing bitmap.
 * The source image, A, is the shape or image being rendered. 
 * The destination image, B, is the current state of the bitmap.
 * 
 * @see <a href="http://dev.w3.org/html5/spec/Overview.html#dom-context-2d-globalcompositeoperation">
 * http://dev.w3.org/html5/spec/Overview.html#dom-context-2d-globalcompositeoperation</a>
 * 
 * @author hao1300@gmail.com
 */
public enum Composition {
	/**
	 * A atop B. Display the source image wherever both images are opaque. 
	 * Display the destination image wherever the destination image is opaque but 
	 * the source image is transparent. Display transparency elsewhere.
	 */
	SOURCE_ATOP("source-atop"),
	/**
	 * A in B. Display the source image wherever both the source image and 
	 * destination image are opaque. Display transparency elsewhere.
	 */
	SOURCE_IN("source-in"),
	/**
	 * A out B. Display the source image wherever the source image is opaque and 
	 * the destination image is transparent. Display transparency elsewhere.
	 */
	SOURCE_OUT("source-out"),
	/**
	 * A over B. Display the source image wherever the source image is opaque. 
	 * Display the destination image elsewhere.
	 * (This is the default {@link Composition} value).
	 */
	SOURCE_OVER("source-over"),
	/**
	 * B atop A. Same as source-atop but using the destination image instead of 
	 * the source image and vice versa.
	 */
	DESTINATION_ATOP("destination-atop"),
	/**
	 * B in A. Same as source-in but using the destination image instead of the 
	 * source image and vice versa.
	 */
	DESTINATION_IN("destination-in"),
	/**
	 * B out A. Same as source-out but using the destination image instead of the 
	 * source image and vice versa.
	 */
	DESTINATION_OUT("destination-out"),
	/**
	 * B over A. Same as source-over but using the destination image instead of 
	 * the source image and vice versa.
	 */
	DESTINATION_OVER("destination-over"),
	/**
	 * A plus B. Display the sum of the source image and destination image, with 
	 * color values approaching 1 as a limit.
	 */
	LIGHTER("lighter"),
	/**
	 * A (B is ignored). Display the source image instead of the destination 
	 * image.
	 */
	COPY("copy"),
	/**
	 * A xor B. Exclusive OR of the source image and destination image.
	 */
	XOR("xor");
	
	private static Map<String, Composition> compositionMap;
	private final String compositionName;
	
	private Composition(String compositionName) {
		this.compositionName = compositionName;
	}
	
	@Override
	public String toString() {
		return compositionName;
	}
	
	/**
	 * Parses a string into a Composition.
	 * 
	 * @param composition
	 */
	public static Composition parseComposition(String composition) {
		if (compositionMap == null) {
			compositionMap = new HashMap<String, Composition>();
			for (Composition v : values()) {
				compositionMap.put(v.toString(), v);
			}
		}
		return compositionMap.get(composition);
	}
}