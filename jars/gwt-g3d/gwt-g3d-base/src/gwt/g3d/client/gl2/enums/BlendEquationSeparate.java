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
 * BlendEquationSeparate flags.
 *
 * @author hao1300@gmail.com
 */
public enum BlendEquationSeparate {
  FUNC_ADD(GLEnum.FUNC_ADD),
  BLEND_EQUATION(GLEnum.BLEND_EQUATION),
  BLEND_EQUATION_RGB(GLEnum.BLEND_EQUATION_RGB),
  BLEND_EQUATION_ALPHA(GLEnum.BLEND_EQUATION_ALPHA);

  private static Map<Integer, BlendEquationSeparate> blendEquationSeparateMap;
  private final int value;

  private BlendEquationSeparate(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding BlendEquationSeparate enum.
   *
   * @param blendEquationSeparate
   */
  public static BlendEquationSeparate parseBlendEquationSeparate(int blendEquationSeparate) {
    if (blendEquationSeparateMap == null) {
      blendEquationSeparateMap = new HashMap<Integer, BlendEquationSeparate>();
      for (BlendEquationSeparate v : values()) {
        blendEquationSeparateMap.put(v.getValue(), v);
      }
    }
    return blendEquationSeparateMap.get(blendEquationSeparate);
  }
}
