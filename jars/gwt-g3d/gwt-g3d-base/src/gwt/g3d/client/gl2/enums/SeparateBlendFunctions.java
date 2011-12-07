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
 * Separate Blend Functions flags.
 *
 * @author hao1300@gmail.com
 */
public enum SeparateBlendFunctions {
  BLEND_DST_RGB(GLEnum.BLEND_DST_RGB),
  BLEND_SRC_RGB(GLEnum.BLEND_SRC_RGB),
  BLEND_DST_ALPHA(GLEnum.BLEND_DST_ALPHA),
  BLEND_SRC_ALPHA(GLEnum.BLEND_SRC_ALPHA),
  CONSTANT_COLOR(GLEnum.CONSTANT_COLOR),
  ONE_MINUS_CONSTANT_COLOR(GLEnum.ONE_MINUS_CONSTANT_COLOR),
  CONSTANT_ALPHA(GLEnum.CONSTANT_ALPHA),
  ONE_MINUS_CONSTANT_ALPHA(GLEnum.ONE_MINUS_CONSTANT_ALPHA),
  BLEND_COLOR(GLEnum.BLEND_COLOR);

  private static Map<Integer, SeparateBlendFunctions> separateBlendFunctionsMap;
  private final int value;

  private SeparateBlendFunctions(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding SeparateBlendFunctions enum.
   *
   * @param separateBlendFunctions
   */
  public static SeparateBlendFunctions parseSeparateBlendFunctions(int separateBlendFunctions) {
    if (separateBlendFunctionsMap == null) {
      separateBlendFunctionsMap = new HashMap<Integer, SeparateBlendFunctions>();
      for (SeparateBlendFunctions v : values()) {
        separateBlendFunctionsMap.put(v.getValue(), v);
      }
    }
    return separateBlendFunctionsMap.get(separateBlendFunctions);
  }
}
