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
 * FramebufferObject flags.
 *
 * @author hao1300@gmail.com
 */
public enum FramebufferObject {
  FRAMEBUFFER(GLEnum.FRAMEBUFFER),
  RENDERBUFFER(GLEnum.RENDERBUFFER),
  RGBA4(GLEnum.RGBA4),
  RGB5_A1(GLEnum.RGB5_A1),
  RGB565(GLEnum.RGB565),
  DEPTH_COMPONENT16(GLEnum.DEPTH_COMPONENT16),
  STENCIL_INDEX(GLEnum.STENCIL_INDEX),
  STENCIL_INDEX8(GLEnum.STENCIL_INDEX8),
  RENDERBUFFER_WIDTH(GLEnum.RENDERBUFFER_WIDTH),
  RENDERBUFFER_HEIGHT(GLEnum.RENDERBUFFER_HEIGHT),
  RENDERBUFFER_INTERNAL_FORMAT(GLEnum.RENDERBUFFER_INTERNAL_FORMAT),
  RENDERBUFFER_RED_SIZE(GLEnum.RENDERBUFFER_RED_SIZE),
  RENDERBUFFER_GREEN_SIZE(GLEnum.RENDERBUFFER_GREEN_SIZE),
  RENDERBUFFER_BLUE_SIZE(GLEnum.RENDERBUFFER_BLUE_SIZE),
  RENDERBUFFER_ALPHA_SIZE(GLEnum.RENDERBUFFER_ALPHA_SIZE),
  RENDERBUFFER_DEPTH_SIZE(GLEnum.RENDERBUFFER_DEPTH_SIZE),
  RENDERBUFFER_STENCIL_SIZE(GLEnum.RENDERBUFFER_STENCIL_SIZE),
  FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE(GLEnum.FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE),
  FRAMEBUFFER_ATTACHMENT_OBJECT_NAME(GLEnum.FRAMEBUFFER_ATTACHMENT_OBJECT_NAME),
  FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL(GLEnum.FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL),
  FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE(GLEnum.FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE),
  COLOR_ATTACHMENT0(GLEnum.COLOR_ATTACHMENT0),
  DEPTH_ATTACHMENT(GLEnum.DEPTH_ATTACHMENT),
  STENCIL_ATTACHMENT(GLEnum.STENCIL_ATTACHMENT),
  NONE(GLEnum.NONE),
  FRAMEBUFFER_COMPLETE(GLEnum.FRAMEBUFFER_COMPLETE),
  FRAMEBUFFER_INCOMPLETE_ATTACHMENT(GLEnum.FRAMEBUFFER_INCOMPLETE_ATTACHMENT),
  FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT(GLEnum.FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT),
  FRAMEBUFFER_INCOMPLETE_DIMENSIONS(GLEnum.FRAMEBUFFER_INCOMPLETE_DIMENSIONS),
  FRAMEBUFFER_UNSUPPORTED(GLEnum.FRAMEBUFFER_UNSUPPORTED),
  FRAMEBUFFER_BINDING(GLEnum.FRAMEBUFFER_BINDING),
  RENDERBUFFER_BINDING(GLEnum.RENDERBUFFER_BINDING),
  MAX_RENDERBUFFER_SIZE(GLEnum.MAX_RENDERBUFFER_SIZE),
  INVALID_FRAMEBUFFER_OPERATION(GLEnum.INVALID_FRAMEBUFFER_OPERATION);

  private static Map<Integer, FramebufferObject> framebufferObjectMap;
  private final int value;

  private FramebufferObject(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding FramebufferObject enum.
   *
   * @param framebufferObject
   */
  public static FramebufferObject parseFramebufferObject(int framebufferObject) {
    if (framebufferObjectMap == null) {
      framebufferObjectMap = new HashMap<Integer, FramebufferObject>();
      for (FramebufferObject v : values()) {
        framebufferObjectMap.put(v.getValue(), v);
      }
    }
    return framebufferObjectMap.get(framebufferObject);
  }
}
