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
 * BufferParameterName flags.
 *
 * @author hao1300@gmail.com
 */
public enum BufferParameterName {
	/** Returns int */
  BUFFER_SIZE(GLEnum.BUFFER_SIZE),
  /** Returns int */
  BUFFER_USAGE(GLEnum.BUFFER_USAGE);

  private static Map<Integer, BufferParameterName> bufferParameterNameMap;
  private final int value;

  private BufferParameterName(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding BufferParameterName enum.
   *
   * @param bufferParameterName
   */
  public static BufferParameterName parseBufferParameterName(int bufferParameterName) {
    if (bufferParameterNameMap == null) {
      bufferParameterNameMap = new HashMap<Integer, BufferParameterName>();
      for (BufferParameterName v : values()) {
        bufferParameterNameMap.put(v.getValue(), v);
      }
    }
    return bufferParameterNameMap.get(bufferParameterName);
  }
}
