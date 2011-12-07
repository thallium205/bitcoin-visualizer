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
 * BlendingFactorSrc flags.
 *
 * @author hao1300@gmail.com
 */
public enum BlendingFactorSrc {
  ZERO(GLEnum.ZERO),
  ONE(GLEnum.ONE),
  DST_COLOR(GLEnum.DST_COLOR),
  ONE_MINUS_DST_COLOR(GLEnum.ONE_MINUS_DST_COLOR),
  SRC_ALPHA_SATURATE(GLEnum.SRC_ALPHA_SATURATE),
  SRC_ALPHA(GLEnum.SRC_ALPHA),
  ONE_MINUS_SRC_ALPHA(GLEnum.ONE_MINUS_SRC_ALPHA),
  DST_ALPHA(GLEnum.DST_ALPHA),
  ONE_MINUS_DST_ALPHA(GLEnum.ONE_MINUS_DST_ALPHA);

  private static Map<Integer, BlendingFactorSrc> blendingFactorSrcMap;
  private final int value;

  private BlendingFactorSrc(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding BlendingFactorSrc enum.
   *
   * @param blendingFactorSrc
   */
  public static BlendingFactorSrc parseBlendingFactorSrc(int blendingFactorSrc) {
    if (blendingFactorSrcMap == null) {
      blendingFactorSrcMap = new HashMap<Integer, BlendingFactorSrc>();
      for (BlendingFactorSrc v : values()) {
        blendingFactorSrcMap.put(v.getValue(), v);
      }
    }
    return blendingFactorSrcMap.get(blendingFactorSrc);
  }
}
