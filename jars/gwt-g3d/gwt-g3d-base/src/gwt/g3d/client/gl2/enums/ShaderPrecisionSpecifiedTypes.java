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
 * ShaderPrecision-SpecifiedTypes flags.
 *
 * @author hao1300@gmail.com
 */
public enum ShaderPrecisionSpecifiedTypes {
  LOW_FLOAT(GLEnum.LOW_FLOAT),
  MEDIUM_FLOAT(GLEnum.MEDIUM_FLOAT),
  HIGH_FLOAT(GLEnum.HIGH_FLOAT),
  LOW_INT(GLEnum.LOW_INT),
  MEDIUM_INT(GLEnum.MEDIUM_INT),
  HIGH_INT(GLEnum.HIGH_INT);

  private static Map<Integer, ShaderPrecisionSpecifiedTypes> shaderPrecisionSpecifiedTypesMap;
  private final int value;

  private ShaderPrecisionSpecifiedTypes(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding ShaderPrecisionSpecifiedTypes enum.
   *
   * @param shaderPrecisionSpecifiedTypes
   */
  public static ShaderPrecisionSpecifiedTypes parseShaderPrecisionSpecifiedTypes(int shaderPrecisionSpecifiedTypes) {
    if (shaderPrecisionSpecifiedTypesMap == null) {
      shaderPrecisionSpecifiedTypesMap = new HashMap<Integer, ShaderPrecisionSpecifiedTypes>();
      for (ShaderPrecisionSpecifiedTypes v : values()) {
        shaderPrecisionSpecifiedTypesMap.put(v.getValue(), v);
      }
    }
    return shaderPrecisionSpecifiedTypesMap.get(shaderPrecisionSpecifiedTypes);
  }
}
