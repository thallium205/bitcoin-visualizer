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
 * Shaders flags.
 *
 * @author hao1300@gmail.com
 */
public enum Shaders {
  FRAGMENT_SHADER(GLEnum.FRAGMENT_SHADER),
  VERTEX_SHADER(GLEnum.VERTEX_SHADER),
  MAX_VERTEX_ATTRIBS(GLEnum.MAX_VERTEX_ATTRIBS),
  MAX_VERTEX_UNIFORM_VECTORS(GLEnum.MAX_VERTEX_UNIFORM_VECTORS),
  MAX_VARYING_VECTORS(GLEnum.MAX_VARYING_VECTORS),
  MAX_COMBINED_TEXTURE_IMAGE_UNITS(GLEnum.MAX_COMBINED_TEXTURE_IMAGE_UNITS),
  MAX_VERTEX_TEXTURE_IMAGE_UNITS(GLEnum.MAX_VERTEX_TEXTURE_IMAGE_UNITS),
  MAX_TEXTURE_IMAGE_UNITS(GLEnum.MAX_TEXTURE_IMAGE_UNITS),
  MAX_FRAGMENT_UNIFORM_VECTORS(GLEnum.MAX_FRAGMENT_UNIFORM_VECTORS),
  SHADER_TYPE(GLEnum.SHADER_TYPE),
  DELETE_STATUS(GLEnum.DELETE_STATUS),
  LINK_STATUS(GLEnum.LINK_STATUS),
  VALIDATE_STATUS(GLEnum.VALIDATE_STATUS),
  ATTACHED_SHADERS(GLEnum.ATTACHED_SHADERS),
  ACTIVE_UNIFORMS(GLEnum.ACTIVE_UNIFORMS),
  ACTIVE_UNIFORM_MAX_LENGTH(GLEnum.ACTIVE_UNIFORM_MAX_LENGTH),
  ACTIVE_ATTRIBUTES(GLEnum.ACTIVE_ATTRIBUTES),
  ACTIVE_ATTRIBUTE_MAX_LENGTH(GLEnum.ACTIVE_ATTRIBUTE_MAX_LENGTH),
  SHADING_LANGUAGE_VERSION(GLEnum.SHADING_LANGUAGE_VERSION),
  CURRENT_PROGRAM(GLEnum.CURRENT_PROGRAM);

  private static Map<Integer, Shaders> shadersMap;
  private final int value;

  private Shaders(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding Shaders enum.
   *
   * @param shaders
   */
  public static Shaders parseShaders(int shaders) {
    if (shadersMap == null) {
      shadersMap = new HashMap<Integer, Shaders>();
      for (Shaders v : values()) {
        shadersMap.put(v.getValue(), v);
      }
    }
    return shadersMap.get(shaders);
  }
}
