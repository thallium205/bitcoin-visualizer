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
 * BufferUsage flags.
 *
 * @author hao1300@gmail.com
 */
public enum BufferUsage {
  STREAM_DRAW(GLEnum.STREAM_DRAW),
  STATIC_DRAW(GLEnum.STATIC_DRAW),
  DYNAMIC_DRAW(GLEnum.DYNAMIC_DRAW);

  private static Map<Integer, BufferUsage> bufferUsageMap;
  private final int value;

  private BufferUsage(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding BufferUsage enum.
   *
   * @param bufferUsage
   */
  public static BufferUsage parseBufferUsage(int bufferUsage) {
    if (bufferUsageMap == null) {
      bufferUsageMap = new HashMap<Integer, BufferUsage>();
      for (BufferUsage v : values()) {
        bufferUsageMap.put(v.getValue(), v);
      }
    }
    return bufferUsageMap.get(bufferUsage);
  }
}
