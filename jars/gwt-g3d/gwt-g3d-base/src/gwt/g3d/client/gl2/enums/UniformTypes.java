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
 * UniformTypes flags.
 *
 * @author hao1300@gmail.com
 */
public enum UniformTypes {
  FLOAT_VEC2(GLEnum.FLOAT_VEC2),
  FLOAT_VEC3(GLEnum.FLOAT_VEC3),
  FLOAT_VEC4(GLEnum.FLOAT_VEC4),
  INT_VEC2(GLEnum.INT_VEC2),
  INT_VEC3(GLEnum.INT_VEC3),
  INT_VEC4(GLEnum.INT_VEC4),
  BOOL(GLEnum.BOOL),
  BOOL_VEC2(GLEnum.BOOL_VEC2),
  BOOL_VEC3(GLEnum.BOOL_VEC3),
  BOOL_VEC4(GLEnum.BOOL_VEC4),
  FLOAT_MAT2(GLEnum.FLOAT_MAT2),
  FLOAT_MAT3(GLEnum.FLOAT_MAT3),
  FLOAT_MAT4(GLEnum.FLOAT_MAT4),
  SAMPLER_2D(GLEnum.SAMPLER_2D),
  SAMPLER_CUBE(GLEnum.SAMPLER_CUBE);

  private static Map<Integer, UniformTypes> uniformTypesMap;
  private final int value;

  private UniformTypes(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding UniformTypes enum.
   *
   * @param uniformTypes
   */
  public static UniformTypes parseUniformTypes(int uniformTypes) {
    if (uniformTypesMap == null) {
      uniformTypesMap = new HashMap<Integer, UniformTypes>();
      for (UniformTypes v : values()) {
        uniformTypesMap.put(v.getValue(), v);
      }
    }
    return uniformTypesMap.get(uniformTypes);
  }
}
