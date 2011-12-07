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
public enum FramebufferSlot {
  COLOR_ATTACHMENT0(GLEnum.COLOR_ATTACHMENT0),
  DEPTH_ATTACHMENT(GLEnum.DEPTH_ATTACHMENT),
  STENCIL_ATTACHMENT(GLEnum.STENCIL_ATTACHMENT);

  private static Map<Integer, FramebufferSlot> framebufferSlotMap;
  private final int value;

  private FramebufferSlot(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding FramebufferSlot enum.
   *
   * @param framebufferSlot
   */
  public static FramebufferSlot parseFramebufferSlot(int framebufferSlot) {
    if (framebufferSlotMap == null) {
      framebufferSlotMap = new HashMap<Integer, FramebufferSlot>();
      for (FramebufferSlot v : values()) {
        framebufferSlotMap.put(v.getValue(), v);
      }
    }
    return framebufferSlotMap.get(framebufferSlot);
  }
}
