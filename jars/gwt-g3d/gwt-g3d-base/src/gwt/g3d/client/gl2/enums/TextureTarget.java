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
 * TextureTarget flags.
 *
 * @author hao1300@gmail.com
 */
public enum TextureTarget {
  TEXTURE_2D(GLEnum.TEXTURE_2D),
  TEXTURE(GLEnum.TEXTURE),
  TEXTURE_CUBE_MAP(GLEnum.TEXTURE_CUBE_MAP),
  TEXTURE_BINDING_CUBE_MAP(GLEnum.TEXTURE_BINDING_CUBE_MAP),
  TEXTURE_CUBE_MAP_POSITIVE_X(GLEnum.TEXTURE_CUBE_MAP_POSITIVE_X),
  TEXTURE_CUBE_MAP_NEGATIVE_X(GLEnum.TEXTURE_CUBE_MAP_NEGATIVE_X),
  TEXTURE_CUBE_MAP_POSITIVE_Y(GLEnum.TEXTURE_CUBE_MAP_POSITIVE_Y),
  TEXTURE_CUBE_MAP_NEGATIVE_Y(GLEnum.TEXTURE_CUBE_MAP_NEGATIVE_Y),
  TEXTURE_CUBE_MAP_POSITIVE_Z(GLEnum.TEXTURE_CUBE_MAP_POSITIVE_Z),
  TEXTURE_CUBE_MAP_NEGATIVE_Z(GLEnum.TEXTURE_CUBE_MAP_NEGATIVE_Z),
  MAX_CUBE_MAP_TEXTURE_SIZE(GLEnum.MAX_CUBE_MAP_TEXTURE_SIZE);

  private static Map<Integer, TextureTarget> textureTargetMap;
  private final int value;

  private TextureTarget(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding TextureTarget enum.
   *
   * @param textureTarget
   */
  public static TextureTarget parseTextureTarget(int textureTarget) {
    if (textureTargetMap == null) {
      textureTargetMap = new HashMap<Integer, TextureTarget>();
      for (TextureTarget v : values()) {
        textureTargetMap.put(v.getValue(), v);
      }
    }
    return textureTargetMap.get(textureTarget);
  }
}
