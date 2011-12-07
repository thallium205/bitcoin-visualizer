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
 * ShaderParameter flags.
 *
 * @author hao1300@gmail.com
 */
public enum ShaderParameter {
	/** Returns int */
	SHADER_TYPE(GLEnum.SHADER_TYPE),
	/** Returns boolean */
	DELETE_STATUS(GLEnum.DELETE_STATUS),
	/** Returns boolean */
	COMPILE_STATUS(GLEnum.COMPILE_STATUS),
	/** Returns int */
	INFO_LOG_LENGTH(GLEnum.INFO_LOG_LENGTH),
	/** Returns int */
	SHADER_SOURCE_LENGTH(GLEnum.SHADER_SOURCE_LENGTH);

  private static Map<Integer, ShaderParameter> shaderParameterMap;
  private final int value;

  private ShaderParameter(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding ShaderParameter enum.
   *
   * @param shaderParameter
   */
  public static ShaderParameter parseShaderParameter(int shaderParameter) {
    if (shaderParameterMap == null) {
      shaderParameterMap = new HashMap<Integer, ShaderParameter>();
      for (ShaderParameter v : values()) {
        shaderParameterMap.put(v.getValue(), v);
      }
    }
    return shaderParameterMap.get(shaderParameter);
  }
}
