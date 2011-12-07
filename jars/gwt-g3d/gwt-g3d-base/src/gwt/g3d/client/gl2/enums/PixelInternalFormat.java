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
 * PixelInternalFormat flags.
 *
 * @author hao1300@gmail.com
 */
public enum PixelInternalFormat {
  ALPHA(GLEnum.ALPHA),
  RGB(GLEnum.RGB),
  RGBA(GLEnum.RGBA),
  LUMINANCE(GLEnum.LUMINANCE),
  LUMINANCE_ALPHA(GLEnum.LUMINANCE_ALPHA);

  private static Map<Integer, PixelInternalFormat> pixelInternalFormatMap;
  private final int value;

  private PixelInternalFormat(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding PixelInternalFormat enum.
   *
   * @param pixelInternalFormat
   */
  public static PixelInternalFormat parsePixelInternalFormat(int pixelInternalFormat) {
    if (pixelInternalFormatMap == null) {
      pixelInternalFormatMap = new HashMap<Integer, PixelInternalFormat>();
      for (PixelInternalFormat v : values()) {
        pixelInternalFormatMap.put(v.getValue(), v);
      }
    }
    return pixelInternalFormatMap.get(pixelInternalFormat);
  }
}
