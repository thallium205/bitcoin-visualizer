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
 * FramebufferErrorCode flags.
 *
 * @author hao1300@gmail.com
 */
public enum FramebufferErrorCode {
  FRAMEBUFFER_COMPLETE(GLEnum.FRAMEBUFFER_COMPLETE),
  FRAMEBUFFER_INCOMPLETE_ATTACHMENT(GLEnum.FRAMEBUFFER_INCOMPLETE_ATTACHMENT),
  FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT(GLEnum.FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT),
  FRAMEBUFFER_INCOMPLETE_DIMENSIONS(GLEnum.FRAMEBUFFER_INCOMPLETE_DIMENSIONS),
  FRAMEBUFFER_UNSUPPORTED(GLEnum.FRAMEBUFFER_UNSUPPORTED);

  private static Map<Integer, FramebufferErrorCode> errorCodeMap;
  private final int value;

  private FramebufferErrorCode(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }
  
  /**
   * Parses an integer framebuffer error code to its corresponding 
   * FramebufferErrorCode enum.
   * 
   * @param errorCode
   */
  public static FramebufferErrorCode parseErrorCode(int errorCode) {
  	if (errorCodeMap == null) {
  		errorCodeMap = new HashMap<Integer, FramebufferErrorCode>();
  		for (FramebufferErrorCode v : values()) {
  			errorCodeMap.put(v.getValue(), v);
  		}
  	}
  	return errorCodeMap.get(errorCode);
  }
}
