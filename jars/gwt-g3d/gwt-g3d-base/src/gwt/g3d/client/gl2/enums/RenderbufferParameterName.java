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
 * RenderbufferParameterName flags.
 * 
 * @author hao1300@gmail.com
 */
public enum RenderbufferParameterName {
	/** Returns int */
	RENDERBUFFER_WIDTH(GLEnum.RENDERBUFFER_WIDTH),
	/** Returns int */
	RENDERBUFFER_HEIGHT(GLEnum.RENDERBUFFER_HEIGHT),
	/** Returns int */
	RENDERBUFFER_INTERNAL_FORMAT(GLEnum.RENDERBUFFER_INTERNAL_FORMAT),
	/** Returns int */
	RENDERBUFFER_RED_SIZE(GLEnum.RENDERBUFFER_RED_SIZE),
	/** Returns int */
	RENDERBUFFER_GREEN_SIZE(GLEnum.RENDERBUFFER_GREEN_SIZE),
	/** Returns int */
	RENDERBUFFER_BLUE_SIZE(GLEnum.RENDERBUFFER_BLUE_SIZE),
	/** Returns int */
	RENDERBUFFER_ALPHA_SIZE(GLEnum.RENDERBUFFER_ALPHA_SIZE),
	/** Returns int */
	RENDERBUFFER_DEPTH_SIZE(GLEnum.RENDERBUFFER_DEPTH_SIZE),
	/** Returns int */
	RENDERBUFFER_STENCIL_SIZE(GLEnum.RENDERBUFFER_STENCIL_SIZE);

	private static Map<Integer, RenderbufferParameterName> renderbufferParameterNameMap;
  private final int value;

  private RenderbufferParameterName(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding RenderbufferParameterName enum.
   *
   * @param renderbufferParameterName
   */
  public static RenderbufferParameterName parseRenderbufferParameterName(int renderbufferParameterName) {
    if (renderbufferParameterNameMap == null) {
      renderbufferParameterNameMap = new HashMap<Integer, RenderbufferParameterName>();
      for (RenderbufferParameterName v : values()) {
        renderbufferParameterNameMap.put(v.getValue(), v);
      }
    }
    return renderbufferParameterNameMap.get(renderbufferParameterName);
  }
}
