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
 * Enum that describes the media playback error.
 * 
 * @author hao1300@gmail.com
 */
public enum MediaError {
	/**
	 * No media error.
	 */
	NO_ERROR(0),
	/**
	 * The fetching process for the media resource was aborted by the user agent 
	 * at the user's request.
	 */
	MEDIA_ERR_ABORTED(1),
	/**
	 * A network error of some description caused the user agent to stop fetching 
	 * the media resource, after the resource was established to be usable.
	 */
  MEDIA_ERR_NETWORK(2),
  /**
   * An error of some description occurred while decoding the media resource, 
   * after the resource was established to be usable.
   */
  MEDIA_ERR_DECODE(3),
  /**
   * The media resource indicated by the src attribute was not suitable.
   */
  MEDIA_ERR_SRC_NOT_SUPPORTED(4);
	
	private static Map<Integer, MediaError> mediaErrorMap;
  private final int value;

  private MediaError(int value) {
    this.value = value;
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }
  
  /**
   * Parses an integer code to its corresponding MediaError enum.
   * 
   * @param code
   */
  public static MediaError parseMediaError(int code) {
  	if (mediaErrorMap == null) {
  		mediaErrorMap = new HashMap<Integer, MediaError>();
  		for (MediaError v : values()) {
  			mediaErrorMap.put(v.getValue(), v);
  		}
  	}
  	return mediaErrorMap.get(code);
  }		
}
