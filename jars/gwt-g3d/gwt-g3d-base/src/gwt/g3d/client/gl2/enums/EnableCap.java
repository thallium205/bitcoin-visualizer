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
 * EnableCap flags.
 *
 * @author hao1300@gmail.com
 */
public enum EnableCap {
  CULL_FACE(GLEnum.CULL_FACE),
  BLEND(GLEnum.BLEND),
  DITHER(GLEnum.DITHER),
  STENCIL_TEST(GLEnum.STENCIL_TEST),
  DEPTH_TEST(GLEnum.DEPTH_TEST),
  SCISSOR_TEST(GLEnum.SCISSOR_TEST),
  POLYGON_OFFSET_FILL(GLEnum.POLYGON_OFFSET_FILL),
  SAMPLE_ALPHA_TO_COVERAGE(GLEnum.SAMPLE_ALPHA_TO_COVERAGE),
  SAMPLE_COVERAGE(GLEnum.SAMPLE_COVERAGE);

  private static Map<Integer, EnableCap> enableCapMap;
  private final int value;

  private EnableCap(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding EnableCap enum.
   *
   * @param enableCap
   */
  public static EnableCap parseEnableCap(int enableCap) {
    if (enableCapMap == null) {
      enableCapMap = new HashMap<Integer, EnableCap>();
      for (EnableCap v : values()) {
        enableCapMap.put(v.getValue(), v);
      }
    }
    return enableCapMap.get(enableCap);
  }
}
