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
 * PixelFormat flags.
 *
 * @author hao1300@gmail.com
 */
public enum PixelFormat {
  DEPTH_COMPONENT(GLEnum.DEPTH_COMPONENT),
  ALPHA(GLEnum.ALPHA),
  RGB(GLEnum.RGB),
  RGBA(GLEnum.RGBA),
  LUMINANCE(GLEnum.LUMINANCE),
  LUMINANCE_ALPHA(GLEnum.LUMINANCE_ALPHA);

  private static Map<Integer, PixelFormat> pixelFormatMap;
  private final int value;

  private PixelFormat(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding PixelFormat enum.
   *
   * @param pixelFormat
   */
  public static PixelFormat parsePixelFormat(int pixelFormat) {
    if (pixelFormatMap == null) {
      pixelFormatMap = new HashMap<Integer, PixelFormat>();
      for (PixelFormat v : values()) {
        pixelFormatMap.put(v.getValue(), v);
      }
    }
    return pixelFormatMap.get(pixelFormat);
  }
}
