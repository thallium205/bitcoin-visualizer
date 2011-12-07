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
 * RenderbufferInternalFormat flags.
 *
 * @author hao1300@gmail.com
 */
public enum RenderbufferInternalFormat {
	RGBA4(GLEnum.RGBA4),
	RGB5_A1(GLEnum.RGB5_A1),
	RGB565(GLEnum.RGB565),
  DEPTH_COMPONENT16(GLEnum.DEPTH_COMPONENT16),
  STENCIL_INDEX8(GLEnum.STENCIL_INDEX8);

  private static Map<Integer, RenderbufferInternalFormat> renderbufferInternalFormatMap;
  private final int value;

  private RenderbufferInternalFormat(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding RenderbufferInternalFormat enum.
   *
   * @param renderbufferInternalFormat
   */
  public static RenderbufferInternalFormat parseRenderbufferInternalFormat(int renderbufferInternalFormat) {
    if (renderbufferInternalFormatMap == null) {
      renderbufferInternalFormatMap = new HashMap<Integer, RenderbufferInternalFormat>();
      for (RenderbufferInternalFormat v : values()) {
        renderbufferInternalFormatMap.put(v.getValue(), v);
      }
    }
    return renderbufferInternalFormatMap.get(renderbufferInternalFormat);
  }
}
