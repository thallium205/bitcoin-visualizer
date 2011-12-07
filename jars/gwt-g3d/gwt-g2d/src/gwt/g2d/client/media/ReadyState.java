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
 * Enum that describes the current ready state.
 * 
 * @author hao1300@gmail.com
 */
public enum ReadyState {
	/**
	 * No information regarding the media resource is available. No data for the 
	 * current playback position is available. Media elements whose networkState 
	 * attribute is NETWORK_EMPTY are always in the HAVE_NOTHING state.
	 */
	HAVE_NOTHING(0),
	/**
	 * Enough of the resource has been obtained that the duration of the resource 
	 * is available. In the case of a video element, the dimensions of the video 
	 * are also available. The API will no longer raise an exception when 
	 * seeking. No media data is available for the immediate current playback 
	 * position.
	 */
  HAVE_METADATA(1),
  /**
   * Data for the immediate current playback position is available, but either 
   * not enough data is available that the user agent could successfully 
   * advance the current playback position in the direction of playback at all 
   * without immediately reverting to the HAVE_METADATA state, or there is no 
   * more data to obtain in the direction of playback. For example, in video 
   * this corresponds to the user agent having data from the current frame, 
   * but not the next frame; and to when playback has ended.
   */
  HAVE_CURRENT_DATA(2),
  /**
   * Data for the immediate current playback position is available, as well as 
   * enough data for the user agent to advance the current playback position 
   * in the direction of playback at least a little without immediately 
   * reverting to the HAVE_METADATA state. For example, in video this 
   * corresponds to the user agent having data for at least the current frame 
   * and the next frame. The user agent cannot be in this state if playback 
   * has ended, as the current playback position can never advance in this case.
   */
  HAVE_FUTURE_DATA(3),
  /**
   * All the conditions described for the HAVE_FUTURE_DATA state are met, and, 
   * in addition, the user agent estimates that data is being fetched at a rate 
   * where the current playback position, if it were to advance at the rate 
   * given by the defaultPlaybackRate attribute, would not overtake the 
   * available data before playback reaches the end of the media resource.
   */
  HAVE_ENOUGH_DATA(4);
	
	private static Map<Integer, ReadyState> readyStateMap;
  private final int value;

  private ReadyState(int value) {
    this.value = value;
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }
  
  /**
   * Parses an integer code to its corresponding ReadyState enum.
   * 
   * @param code
   */
  public static ReadyState parseReadyState(int code) {
  	if (readyStateMap == null) {
  		readyStateMap = new HashMap<Integer, ReadyState>();
  		for (ReadyState v : values()) {
  			readyStateMap.put(v.getValue(), v);
  		}
  	}
  	return readyStateMap.get(code);
  }		
}
