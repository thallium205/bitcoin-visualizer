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
 * TextureMinFilter flags.
 *
 * @author hao1300@gmail.com
 */
public enum TextureMinFilter {
  NEAREST(GLEnum.NEAREST),
  LINEAR(GLEnum.LINEAR),
  NEAREST_MIPMAP_NEAREST(GLEnum.NEAREST_MIPMAP_NEAREST),
  LINEAR_MIPMAP_NEAREST(GLEnum.LINEAR_MIPMAP_NEAREST),
  NEAREST_MIPMAP_LINEAR(GLEnum.NEAREST_MIPMAP_LINEAR),
  LINEAR_MIPMAP_LINEAR(GLEnum.LINEAR_MIPMAP_LINEAR);

  private static Map<Integer, TextureMinFilter> textureMinFilterMap;
  private final int value;

  private TextureMinFilter(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding TextureMinFilter enum.
   *
   * @param textureMinFilter
   */
  public static TextureMinFilter parseTextureMinFilter(int textureMinFilter) {
    if (textureMinFilterMap == null) {
      textureMinFilterMap = new HashMap<Integer, TextureMinFilter>();
      for (TextureMinFilter v : values()) {
        textureMinFilterMap.put(v.getValue(), v);
      }
    }
    return textureMinFilterMap.get(textureMinFilter);
  }
}
