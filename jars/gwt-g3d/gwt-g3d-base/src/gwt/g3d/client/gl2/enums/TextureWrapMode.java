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
 * TextureWrapMode flags.
 *
 * @author hao1300@gmail.com
 */
public enum TextureWrapMode {
  REPEAT(GLEnum.REPEAT),
  CLAMP_TO_EDGE(GLEnum.CLAMP_TO_EDGE),
  MIRRORED_REPEAT(GLEnum.MIRRORED_REPEAT);

  private static Map<Integer, TextureWrapMode> textureWrapModeMap;
  private final int value;

  private TextureWrapMode(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding TextureWrapMode enum.
   *
   * @param textureWrapMode
   */
  public static TextureWrapMode parseTextureWrapMode(int textureWrapMode) {
    if (textureWrapModeMap == null) {
      textureWrapModeMap = new HashMap<Integer, TextureWrapMode>();
      for (TextureWrapMode v : values()) {
        textureWrapModeMap.put(v.getValue(), v);
      }
    }
    return textureWrapModeMap.get(textureWrapMode);
  }
}
