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
package gwt.g2d.client.media;

import java.util.HashMap;
import java.util.Map;

/**
 * Enums that describes how confident the user agent is that it can play media 
 * resources of the given type.
 * 
 * @author hao1300@gmail.com
 */
public enum Playability {
	/**
	 * The user agent knows it cannot render the given media.
	 */
	CANNOT_PLAY(""),
	/**
	 * The type can be confidently established as being supported or not.
	 */
	MAYBE("maybe"),
	/**
	 * The user agent is confident that the type represents a media resource that
	 * it can render if used in with this audio or video element.
	 */
	PROBABLY("probably");
	
	private static Map<String, Playability> playabilityMap;
  private final String value;

  private Playability(String value) {
    this.value = value;
  }
  /**
   * Gets the enum's numerical value.
   */
  public String getValue() {
    return value;
  }
  
  /**
   * Parses a string to its corresponding Playability enum.
   * 
   * @param code
   */
  public static Playability parsePlayability(String code) {
  	if (playabilityMap == null) {
  		playabilityMap = new HashMap<String, Playability>();
  		for (Playability v : values()) {
  			playabilityMap.put(v.getValue(), v);
  		}
  	}
  	return playabilityMap.get(code);
  }		
}
