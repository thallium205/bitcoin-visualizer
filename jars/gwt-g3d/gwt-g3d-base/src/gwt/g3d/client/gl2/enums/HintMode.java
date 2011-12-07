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
 * HintMode flags.
 *
 * @author hao1300@gmail.com
 */
public enum HintMode {
  DONT_CARE(GLEnum.DONT_CARE),
  FASTEST(GLEnum.FASTEST),
  NICEST(GLEnum.NICEST);

  private static Map<Integer, HintMode> hintModeMap;
  private final int value;

  private HintMode(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding HintMode enum.
   *
   * @param hintMode
   */
  public static HintMode parseHintMode(int hintMode) {
    if (hintModeMap == null) {
      hintModeMap = new HashMap<Integer, HintMode>();
      for (HintMode v : values()) {
        hintModeMap.put(v.getValue(), v);
      }
    }
    return hintModeMap.get(hintMode);
  }
}