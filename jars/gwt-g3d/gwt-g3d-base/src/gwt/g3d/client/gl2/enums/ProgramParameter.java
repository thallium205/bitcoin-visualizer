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
 * ProgramParameter flags.
 *
 * @author hao1300@gmail.com
 */
public enum ProgramParameter {
	/** Returns boolean */
	DELETE_STATUS(GLEnum.DELETE_STATUS),
	/** Returns boolean */
	LINK_STATUS(GLEnum.LINK_STATUS),
	/** Returns boolean */
	VALIDATE_STATUS(GLEnum.VALIDATE_STATUS),
	/** Returns int */
	INFO_LOG_LENGTH(GLEnum.INFO_LOG_LENGTH),
	/** Returns int */
	ATTACHED_SHADERS(GLEnum.ATTACHED_SHADERS),
	/** Returns int */
	ACTIVE_UNIFORMS(GLEnum.ACTIVE_UNIFORMS),
	/** Returns int */
	ACTIVE_UNIFORM_MAX_LENGTH(GLEnum.ACTIVE_UNIFORM_MAX_LENGTH),
	/** Returns int */
	ACTIVE_ATTRIBUTES(GLEnum.ACTIVE_ATTRIBUTES),
	/** Returns int */
	ACTIVE_ATTRIBUTE_MAX_LENGTH(GLEnum.ACTIVE_ATTRIBUTE_MAX_LENGTH);

  private static Map<Integer, ProgramParameter> programParameterMap;
  private final int value;

  private ProgramParameter(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding ProgramParameter enum.
   *
   * @param programParameter
   */
  public static ProgramParameter parseProgramParameter(int programParameter) {
    if (programParameterMap == null) {
      programParameterMap = new HashMap<Integer, ProgramParameter>();
      for (ProgramParameter v : values()) {
        programParameterMap.put(v.getValue(), v);
      }
    }
    return programParameterMap.get(programParameter);
  }
}
