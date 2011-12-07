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
 * StencilOp flags.
 *
 * @author hao1300@gmail.com
 */
public enum StencilOp {
  ZERO(GLEnum.ZERO),
  KEEP(GLEnum.KEEP),
  REPLACE(GLEnum.REPLACE),
  INCR(GLEnum.INCR),
  DECR(GLEnum.DECR),
  INVERT(GLEnum.INVERT),
  INCR_WRAP(GLEnum.INCR_WRAP),
  DECR_WRAP(GLEnum.DECR_WRAP);

  private static Map<Integer, StencilOp> stencilOpMap;
  private final int value;

  private StencilOp(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding StencilOp enum.
   *
   * @param stencilOp
   */
  public static StencilOp parseStencilOp(int stencilOp) {
    if (stencilOpMap == null) {
      stencilOpMap = new HashMap<Integer, StencilOp>();
      for (StencilOp v : values()) {
        stencilOpMap.put(v.getValue(), v);
      }
    }
    return stencilOpMap.get(stencilOp);
  }
}
