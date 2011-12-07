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
 * TextureUnit flags.
 *
 * @author hao1300@gmail.com
 */
public enum TextureUnit {
  TEXTURE0(GLEnum.TEXTURE0),
  TEXTURE1(GLEnum.TEXTURE1),
  TEXTURE2(GLEnum.TEXTURE2),
  TEXTURE3(GLEnum.TEXTURE3),
  TEXTURE4(GLEnum.TEXTURE4),
  TEXTURE5(GLEnum.TEXTURE5),
  TEXTURE6(GLEnum.TEXTURE6),
  TEXTURE7(GLEnum.TEXTURE7),
  TEXTURE8(GLEnum.TEXTURE8),
  TEXTURE9(GLEnum.TEXTURE9),
  TEXTURE10(GLEnum.TEXTURE10),
  TEXTURE11(GLEnum.TEXTURE11),
  TEXTURE12(GLEnum.TEXTURE12),
  TEXTURE13(GLEnum.TEXTURE13),
  TEXTURE14(GLEnum.TEXTURE14),
  TEXTURE15(GLEnum.TEXTURE15),
  TEXTURE16(GLEnum.TEXTURE16),
  TEXTURE17(GLEnum.TEXTURE17),
  TEXTURE18(GLEnum.TEXTURE18),
  TEXTURE19(GLEnum.TEXTURE19),
  TEXTURE20(GLEnum.TEXTURE20),
  TEXTURE21(GLEnum.TEXTURE21),
  TEXTURE22(GLEnum.TEXTURE22),
  TEXTURE23(GLEnum.TEXTURE23),
  TEXTURE24(GLEnum.TEXTURE24),
  TEXTURE25(GLEnum.TEXTURE25),
  TEXTURE26(GLEnum.TEXTURE26),
  TEXTURE27(GLEnum.TEXTURE27),
  TEXTURE28(GLEnum.TEXTURE28),
  TEXTURE29(GLEnum.TEXTURE29),
  TEXTURE30(GLEnum.TEXTURE30),
  TEXTURE31(GLEnum.TEXTURE31),
  ACTIVE_TEXTURE(GLEnum.ACTIVE_TEXTURE);

  private static Map<Integer, TextureUnit> textureUnitMap;
  private final int value;

  private TextureUnit(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding TextureUnit enum.
   *
   * @param textureUnit
   */
  public static TextureUnit parseTextureUnit(int textureUnit) {
    if (textureUnitMap == null) {
      textureUnitMap = new HashMap<Integer, TextureUnit>();
      for (TextureUnit v : values()) {
        textureUnitMap.put(v.getValue(), v);
      }
    }
    return textureUnitMap.get(textureUnit);
  }
}
