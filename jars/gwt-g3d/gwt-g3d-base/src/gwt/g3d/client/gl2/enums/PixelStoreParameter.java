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
 * PixelStoreParameter flags.
 *
 * @author hao1300@gmail.com
 */
public enum PixelStoreParameter {
	PACK_ALIGNMENT(GLEnum.PACK_ALIGNMENT),
	UNPACK_ALIGNMENT(GLEnum.UNPACK_ALIGNMENT),
	UNPACK_FLIP_Y_WEBGL(GLEnum.UNPACK_FLIP_Y_WEBGL),
	UNPACK_PREMULTIPLY_ALPHA_WEBGL(GLEnum.UNPACK_PREMULTIPLY_ALPHA_WEBGL);

  private static Map<Integer, PixelStoreParameter> pixelStoreParameterMap;
  private final int value;

  private PixelStoreParameter(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding PixelStoreParameter enum.
   *
   * @param pixelStoreParameter
   */
  public static PixelStoreParameter parsePixelStoreParameter(int pixelStoreParameter) {
    if (pixelStoreParameterMap == null) {
      pixelStoreParameterMap = new HashMap<Integer, PixelStoreParameter>();
      for (PixelStoreParameter v : values()) {
        pixelStoreParameterMap.put(v.getValue(), v);
      }
    }
    return pixelStoreParameterMap.get(pixelStoreParameter);
  }
}
