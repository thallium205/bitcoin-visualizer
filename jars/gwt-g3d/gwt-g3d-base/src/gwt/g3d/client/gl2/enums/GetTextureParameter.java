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
 * GetTextureParameter flags.
 *
 * @author hao1300@gmail.com
 */
public enum GetTextureParameter {
  TEXTURE_MAG_FILTER(GLEnum.TEXTURE_MAG_FILTER),
  TEXTURE_MIN_FILTER(GLEnum.TEXTURE_MIN_FILTER),
  TEXTURE_WRAP_S(GLEnum.TEXTURE_WRAP_S),
  TEXTURE_WRAP_T(GLEnum.TEXTURE_WRAP_T),
  NUM_COMPRESSED_TEXTURE_FORMATS(GLEnum.NUM_COMPRESSED_TEXTURE_FORMATS),
  COMPRESSED_TEXTURE_FORMATS(GLEnum.COMPRESSED_TEXTURE_FORMATS);

  private static Map<Integer, GetTextureParameter> getTextureParameterMap;
  private final int value;

  private GetTextureParameter(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding GetTextureParameter enum.
   *
   * @param getTextureParameter
   */
  public static GetTextureParameter parseGetTextureParameter(int getTextureParameter) {
    if (getTextureParameterMap == null) {
      getTextureParameterMap = new HashMap<Integer, GetTextureParameter>();
      for (GetTextureParameter v : values()) {
        getTextureParameterMap.put(v.getValue(), v);
      }
    }
    return getTextureParameterMap.get(getTextureParameter);
  }
}
