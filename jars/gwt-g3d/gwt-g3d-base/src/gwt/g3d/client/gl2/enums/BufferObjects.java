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
 * BufferObjects flags.
 *
 * @author hao1300@gmail.com
 */
public enum BufferObjects {
  ARRAY_BUFFER(GLEnum.ARRAY_BUFFER),
  ELEMENT_ARRAY_BUFFER(GLEnum.ELEMENT_ARRAY_BUFFER),
  ARRAY_BUFFER_BINDING(GLEnum.ARRAY_BUFFER_BINDING),
  ELEMENT_ARRAY_BUFFER_BINDING(GLEnum.ELEMENT_ARRAY_BUFFER_BINDING),
  STREAM_DRAW(GLEnum.STREAM_DRAW),
  STATIC_DRAW(GLEnum.STATIC_DRAW),
  DYNAMIC_DRAW(GLEnum.DYNAMIC_DRAW),
  BUFFER_SIZE(GLEnum.BUFFER_SIZE),
  BUFFER_USAGE(GLEnum.BUFFER_USAGE),
  CURRENT_VERTEX_ATTRIB(GLEnum.CURRENT_VERTEX_ATTRIB);

  private static Map<Integer, BufferObjects> bufferObjectsMap;
  private final int value;

  private BufferObjects(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding BufferObjects enum.
   *
   * @param bufferObjects
   */
  public static BufferObjects parseBufferObjects(int bufferObjects) {
    if (bufferObjectsMap == null) {
      bufferObjectsMap = new HashMap<Integer, BufferObjects>();
      for (BufferObjects v : values()) {
        bufferObjectsMap.put(v.getValue(), v);
      }
    }
    return bufferObjectsMap.get(bufferObjects);
  }
}
