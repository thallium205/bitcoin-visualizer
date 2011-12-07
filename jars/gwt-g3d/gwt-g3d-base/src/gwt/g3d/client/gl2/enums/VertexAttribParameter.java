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
 * VertexAttribParameter flags.
 *
 * @author hao1300@gmail.com
 */
public enum VertexAttribParameter {
	/** Returns {@link gwt.g3d.client.gl2.WebGLBuffer} */
	VERTEX_ATTRIB_ARRAY_BUFFER_BINDING(GLEnum.VERTEX_ATTRIB_ARRAY_BUFFER_BINDING),
	/** Returns boolean */
  VERTEX_ATTRIB_ARRAY_ENABLED(GLEnum.VERTEX_ATTRIB_ARRAY_ENABLED),
  /** Returns int */
  VERTEX_ATTRIB_ARRAY_SIZE(GLEnum.VERTEX_ATTRIB_ARRAY_SIZE),
  /** Returns int */
  VERTEX_ATTRIB_ARRAY_STRIDE(GLEnum.VERTEX_ATTRIB_ARRAY_STRIDE),
  /** Returns int */
  VERTEX_ATTRIB_ARRAY_TYPE(GLEnum.VERTEX_ATTRIB_ARRAY_TYPE),
  /** Returns boolean */
  VERTEX_ATTRIB_ARRAY_NORMALIZED(GLEnum.VERTEX_ATTRIB_ARRAY_NORMALIZED),
  /** Returns {@link gwt.g3d.client.gl2.array.WebGLFloatArray} (with 4 elements) */
  CURRENT_VERTEX_ATTRIB(GLEnum.CURRENT_VERTEX_ATTRIB);

  private static Map<Integer, VertexAttribParameter> vertexAttribParameterMap;
  private final int value;

  private VertexAttribParameter(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding VertexAttribParameter enum.
   *
   * @param vertexAttribParameter
   */
  public static VertexAttribParameter parseVertexAttribParameter(int vertexAttribParameter) {
    if (vertexAttribParameterMap == null) {
      vertexAttribParameterMap = new HashMap<Integer, VertexAttribParameter>();
      for (VertexAttribParameter v : values()) {
        vertexAttribParameterMap.put(v.getValue(), v);
      }
    }
    return vertexAttribParameterMap.get(vertexAttribParameter);
  }
}
