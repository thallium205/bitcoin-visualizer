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
 * StencilFunction flags.
 *
 * @author hao1300@gmail.com
 */
public enum StencilFunction {
  NEVER(GLEnum.NEVER),
  LESS(GLEnum.LESS),
  EQUAL(GLEnum.EQUAL),
  LEQUAL(GLEnum.LEQUAL),
  GREATER(GLEnum.GREATER),
  NOTEQUAL(GLEnum.NOTEQUAL),
  GEQUAL(GLEnum.GEQUAL),
  ALWAYS(GLEnum.ALWAYS);

  private static Map<Integer, StencilFunction> stencilFunctionMap;
  private final int value;

  private StencilFunction(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding StencilFunction enum.
   *
   * @param stencilFunction
   */
  public static StencilFunction parseStencilFunction(int stencilFunction) {
    if (stencilFunctionMap == null) {
      stencilFunctionMap = new HashMap<Integer, StencilFunction>();
      for (StencilFunction v : values()) {
        stencilFunctionMap.put(v.getValue(), v);
      }
    }
    return stencilFunctionMap.get(stencilFunction);
  }
}
