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
 * VertexArrays flags.
 *
 * @author hao1300@gmail.com
 */
public enum VertexArrays {
  VERTEX_ATTRIB_ARRAY_ENABLED(GLEnum.VERTEX_ATTRIB_ARRAY_ENABLED),
  VERTEX_ATTRIB_ARRAY_SIZE(GLEnum.VERTEX_ATTRIB_ARRAY_SIZE),
  VERTEX_ATTRIB_ARRAY_STRIDE(GLEnum.VERTEX_ATTRIB_ARRAY_STRIDE),
  VERTEX_ATTRIB_ARRAY_TYPE(GLEnum.VERTEX_ATTRIB_ARRAY_TYPE),
  VERTEX_ATTRIB_ARRAY_NORMALIZED(GLEnum.VERTEX_ATTRIB_ARRAY_NORMALIZED),
  VERTEX_ATTRIB_ARRAY_POINTER(GLEnum.VERTEX_ATTRIB_ARRAY_POINTER),
  VERTEX_ATTRIB_ARRAY_BUFFER_BINDING(GLEnum.VERTEX_ATTRIB_ARRAY_BUFFER_BINDING);

  private static Map<Integer, VertexArrays> vertexArraysMap;
  private final int value;

  private VertexArrays(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding VertexArrays enum.
   *
   * @param vertexArrays
   */
  public static VertexArrays parseVertexArrays(int vertexArrays) {
    if (vertexArraysMap == null) {
      vertexArraysMap = new HashMap<Integer, VertexArrays>();
      for (VertexArrays v : values()) {
        vertexArraysMap.put(v.getValue(), v);
      }
    }
    return vertexArraysMap.get(vertexArrays);
  }
}
