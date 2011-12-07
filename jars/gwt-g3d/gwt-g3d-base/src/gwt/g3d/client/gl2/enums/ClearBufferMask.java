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
 * ClearBufferMask flags.
 *
 * @author hao1300@gmail.com
 */
public enum ClearBufferMask {
  DEPTH_BUFFER_BIT(GLEnum.DEPTH_BUFFER_BIT),
  STENCIL_BUFFER_BIT(GLEnum.STENCIL_BUFFER_BIT),
  COLOR_BUFFER_BIT(GLEnum.COLOR_BUFFER_BIT);

  private static Map<Integer, ClearBufferMask> clearBufferMaskMap;
  private final int value;

  private ClearBufferMask(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding ClearBufferMask enum.
   *
   * @param clearBufferMask
   */
  public static ClearBufferMask parseClearBufferMask(int clearBufferMask) {
    if (clearBufferMaskMap == null) {
      clearBufferMaskMap = new HashMap<Integer, ClearBufferMask>();
      for (ClearBufferMask v : values()) {
        clearBufferMaskMap.put(v.getValue(), v);
      }
    }
    return clearBufferMaskMap.get(clearBufferMask);
  }
}
