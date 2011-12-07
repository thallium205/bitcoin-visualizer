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
 * ReadFormat flags.
 *
 * @author hao1300@gmail.com
 */
public enum ReadFormat {
  IMPLEMENTATION_COLOR_READ_TYPE(GLEnum.IMPLEMENTATION_COLOR_READ_TYPE),
  IMPLEMENTATION_COLOR_READ_FORMAT(GLEnum.IMPLEMENTATION_COLOR_READ_FORMAT);

  private static Map<Integer, ReadFormat> readFormatMap;
  private final int value;

  private ReadFormat(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding ReadFormat enum.
   *
   * @param readFormat
   */
  public static ReadFormat parseReadFormat(int readFormat) {
    if (readFormatMap == null) {
      readFormatMap = new HashMap<Integer, ReadFormat>();
      for (ReadFormat v : values()) {
        readFormatMap.put(v.getValue(), v);
      }
    }
    return readFormatMap.get(readFormat);
  }
}
