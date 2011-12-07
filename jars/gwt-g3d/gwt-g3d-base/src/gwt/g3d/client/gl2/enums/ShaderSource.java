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
 * ShaderSource flags.
 *
 * @author hao1300@gmail.com
 */
public enum ShaderSource {
  COMPILE_STATUS(GLEnum.COMPILE_STATUS),
  INFO_LOG_LENGTH(GLEnum.INFO_LOG_LENGTH),
  SHADER_SOURCE_LENGTH(GLEnum.SHADER_SOURCE_LENGTH),
  SHADER_COMPILER(GLEnum.SHADER_COMPILER);

  private static Map<Integer, ShaderSource> shaderSourceMap;
  private final int value;

  private ShaderSource(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding ShaderSource enum.
   *
   * @param shaderSource
   */
  public static ShaderSource parseShaderSource(int shaderSource) {
    if (shaderSourceMap == null) {
      shaderSourceMap = new HashMap<Integer, ShaderSource>();
      for (ShaderSource v : values()) {
        shaderSourceMap.put(v.getValue(), v);
      }
    }
    return shaderSourceMap.get(shaderSource);
  }
}
