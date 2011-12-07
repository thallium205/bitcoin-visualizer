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
package gwt.g3d.client.gl2.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * BeginMode flags.
 *
 * @author hao1300@gmail.com
 */
public enum BeginMode {
  POINTS(GLEnum.POINTS),
  LINES(GLEnum.LINES),
  LINE_LOOP(GLEnum.LINE_LOOP),
  LINE_STRIP(GLEnum.LINE_STRIP),
  TRIANGLES(GLEnum.TRIANGLES),
  TRIANGLE_STRIP(GLEnum.TRIANGLE_STRIP),
  TRIANGLE_FAN(GLEnum.TRIANGLE_FAN);

  private static Map<Integer, BeginMode> beginModeMap;
  private final int value;

  private BeginMode(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding BeginMode enum.
   *
   * @param beginMode
   */
  public static BeginMode parseBeginMode(int beginMode) {
    if (beginModeMap == null) {
      beginModeMap = new HashMap<Integer, BeginMode>();
      for (BeginMode v : values()) {
        beginModeMap.put(v.getValue(), v);
      }
    }
    return beginModeMap.get(beginMode);
  }
}
