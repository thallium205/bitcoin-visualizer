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
 * Enum that describes the current network state.
 * 
 * @author hao1300@gmail.com
 */
public enum NetworkState {
	/**
	 * The element has not yet been initialized. All attributes are in their 
	 * initial states.
	 */
	NETWORK_EMPTY(0),
	/**
	 * The element's resource selection algorithm is active and has selected a 
	 * resource, but it is not actually using the network at this time.
	 */
  NETWORK_IDLE(1),
  /**
   * The user agent is actively trying to download data.
   */
  NETWORK_LOADING(2),
  /**
   * The element's resource selection algorithm is active, but it has failed 
   * to find a resource to use.
   */
  NETWORK_NO_SOURCE(3);
	
	private static Map<Integer, NetworkState> networkStateMap;
  private final int value;

  private NetworkState(int value) {
    this.value = value;
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }
  
  /**
   * Parses an integer code to its corresponding NetworkState enum.
   * 
   * @param code
   */
  public static NetworkState parseNetworkState(int code) {
  	if (networkStateMap == null) {
  		networkStateMap = new HashMap<Integer, NetworkState>();
  		for (NetworkState v : values()) {
  			networkStateMap.put(v.getValue(), v);
  		}
  	}
  	return networkStateMap.get(code);
  }		
}
