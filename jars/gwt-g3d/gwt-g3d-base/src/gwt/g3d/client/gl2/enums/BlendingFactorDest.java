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
 * BlendingFactorDest flags.
 *
 * @author hao1300@gmail.com
 */
public enum BlendingFactorDest {
  ZERO(GLEnum.ZERO),
  ONE(GLEnum.ONE),
  SRC_COLOR(GLEnum.SRC_COLOR),
  ONE_MINUS_SRC_COLOR(GLEnum.ONE_MINUS_SRC_COLOR),
  SRC_ALPHA(GLEnum.SRC_ALPHA),
  ONE_MINUS_SRC_ALPHA(GLEnum.ONE_MINUS_SRC_ALPHA),
  DST_ALPHA(GLEnum.DST_ALPHA),
  ONE_MINUS_DST_ALPHA(GLEnum.ONE_MINUS_DST_ALPHA);

  private static Map<Integer, BlendingFactorDest> blendingFactorDestMap;
  private final int value;

  private BlendingFactorDest(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding BlendingFactorDest enum.
   *
   * @param blendingFactorDest
   */
  public static BlendingFactorDest parseBlendingFactorDest(int blendingFactorDest) {
    if (blendingFactorDestMap == null) {
      blendingFactorDestMap = new HashMap<Integer, BlendingFactorDest>();
      for (BlendingFactorDest v : values()) {
        blendingFactorDestMap.put(v.getValue(), v);
      }
    }
    return blendingFactorDestMap.get(blendingFactorDest);
  }
}
