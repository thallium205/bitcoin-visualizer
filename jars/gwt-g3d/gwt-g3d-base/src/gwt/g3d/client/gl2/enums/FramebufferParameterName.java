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
 * FramebufferParameterName flags.
 *
 * @author hao1300@gmail.com
 */
public enum FramebufferParameterName {
	/** Returns int */
	FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE(GLEnum.FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE),
	/** Returns {@link gwt.g3d.client.gl2.WebGLRenderbuffer} or 
	 * {@link gwt.g3d.client.gl2.WebGLTexture} */
	FRAMEBUFFER_ATTACHMENT_OBJECT_NAME(GLEnum.FRAMEBUFFER_ATTACHMENT_OBJECT_NAME),
	/** Returns int */
	FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL(GLEnum.FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL),
	/** Returns int */
	FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE(
			GLEnum.FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE);

  private static Map<Integer, FramebufferParameterName> framebufferParameterNameMap;
  private final int value;

  private FramebufferParameterName(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding FramebufferParameterName enum.
   *
   * @param framebufferParameterName
   */
  public static FramebufferParameterName parseFramebufferParameterName(int framebufferParameterName) {
    if (framebufferParameterNameMap == null) {
      framebufferParameterNameMap = new HashMap<Integer, FramebufferParameterName>();
      for (FramebufferParameterName v : values()) {
        framebufferParameterNameMap.put(v.getValue(), v);
      }
    }
    return framebufferParameterNameMap.get(framebufferParameterName);
  }
}
