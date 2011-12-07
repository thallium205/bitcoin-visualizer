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
 * GetPName flags.
 *
 * @author hao1300@gmail.com
 */
public enum GetPName {		
	/** Returns int */
	ACTIVE_TEXTURE(GLEnum.ACTIVE_TEXTURE),
	/** Returns {@link gwt.g3d.client.gl2.array.WebGLFloatArray} (with 2 elements) */
  ALIASED_LINE_WIDTH_RANGE(GLEnum.ALIASED_LINE_WIDTH_RANGE),
  /** Returns {@link gwt.g3d.client.gl2.array.WebGLFloatArray} (with 2 elements) */
  ALIASED_POINT_SIZE_RANGE(GLEnum.ALIASED_POINT_SIZE_RANGE),
  /** Returns int */
  ALPHA_BITS(GLEnum.ALPHA_BITS),
  /** Returns {@link gwt.g3d.client.gl2.WebGLBuffer} */
  ARRAY_BUFFER_BINDING(GLEnum.ARRAY_BUFFER_BINDING),
	/** Returns boolean */
	BLEND(GLEnum.BLEND),
	/** Returns {@link gwt.g3d.client.gl2.array.WebGLFloatArray} (with 4 values) */
	BLEND_COLOR(GLEnum.BLEND_COLOR),
	/** Returns int */
	BLEND_DST_ALPHA(GLEnum.BLEND_DST_ALPHA),
	/** Returns int */
	BLEND_DST_RGB(GLEnum.BLEND_DST_RGB),
	/** Returns int */
	BLEND_EQUATION_ALPHA(GLEnum.BLEND_EQUATION_ALPHA),
	/** Returns int */
	BLEND_EQUATION_RGB(GLEnum.BLEND_EQUATION_RGB),
	/** Returns int */
	BLEND_SRC_ALPHA(GLEnum.BLEND_SRC_ALPHA),
	/** Returns int */
	BLEND_SRC_RGB(GLEnum.BLEND_SRC_RGB),
	/** Returns int */
  BLUE_BITS(GLEnum.BLUE_BITS),
  /** Returns {@link gwt.g3d.client.gl2.array.WebGLFloatArray} (with 4 values) */
  COLOR_CLEAR_VALUE(GLEnum.COLOR_CLEAR_VALUE),
  /** Returns {@link gwt.g3d.client.gl2.array.WebGLUnsignedByteArray} (with 4 values) */
  COLOR_WRITEMASK(GLEnum.COLOR_WRITEMASK),	
	/** Returns null */
	COMPRESSED_TEXTURE_FORMATS(GLEnum.COMPRESSED_TEXTURE_FORMATS),
	/** Returns boolean */
	CULL_FACE(GLEnum.CULL_FACE),
	/** Returns int */
  CULL_FACE_MODE(GLEnum.CULL_FACE_MODE),
  /** Returns {@link gwt.g3d.client.gl2.WebGLProgram} */
  CURRENT_PROGRAM(GLEnum.CURRENT_PROGRAM),
  /** Returns int */
  DEPTH_BITS(GLEnum.DEPTH_BITS),
  /** Returns float */
  DEPTH_CLEAR_VALUE(GLEnum.DEPTH_CLEAR_VALUE),
  /** Returns int */
  DEPTH_FUNC(GLEnum.DEPTH_FUNC),
  /** Returns {@link gwt.g3d.client.gl2.array.WebGLFloatArray} (with 2 elements) */
  DEPTH_RANGE(GLEnum.DEPTH_RANGE),
	/** Returns boolean */
	DEPTH_TEST(GLEnum.DEPTH_TEST),
	/** Returns boolean */
  DEPTH_WRITEMASK(GLEnum.DEPTH_WRITEMASK),
  /** Returns boolean */
  DITHER(GLEnum.DITHER),
  /** Returns {@link gwt.g3d.client.gl2.WebGLBuffer} */
  ELEMENT_ARRAY_BUFFER_BINDING(GLEnum.ELEMENT_ARRAY_BUFFER_BINDING),
  /** Returns {@link gwt.g3d.client.gl2.WebGLFramebuffer} */
  FRAMEBUFFER_BINDING(GLEnum.FRAMEBUFFER_BINDING),
  /** Returns int */
  FRONT_FACE(GLEnum.FRONT_FACE),
  /** Returns int */
  GENERATE_MIPMAP_HINT(GLEnum.GENERATE_MIPMAP_HINT),
  /** Returns int */
  GREEN_BITS(GLEnum.GREEN_BITS),
  /** Returns float */
  LINE_WIDTH(GLEnum.LINE_WIDTH),
	/** Returns int */
	MAX_COMBINED_TEXTURE_IMAGE_UNITS(GLEnum.MAX_COMBINED_TEXTURE_IMAGE_UNITS),
	/** Returns int */
	MAX_CUBE_MAP_TEXTURE_SIZE(GLEnum.MAX_CUBE_MAP_TEXTURE_SIZE),
	/** Returns int */
	MAX_FRAGMENT_UNIFORM_VECTORS(GLEnum.MAX_FRAGMENT_UNIFORM_VECTORS),
	/** Returns int */
	MAX_RENDERBUFFER_SIZE(GLEnum.MAX_RENDERBUFFER_SIZE),
	/** Returns int */
	MAX_TEXTURE_IMAGE_UNITS(GLEnum.MAX_TEXTURE_IMAGE_UNITS),
	/** Returns int */
  MAX_TEXTURE_SIZE(GLEnum.MAX_TEXTURE_SIZE),
  /** Returns int */
  MAX_VARYING_VECTORS(GLEnum.MAX_VARYING_VECTORS),
  /** Returns int */
  MAX_VERTEX_ATTRIBS(GLEnum.MAX_VERTEX_ATTRIBS),
  /** Returns int */
  MAX_VERTEX_TEXTURE_IMAGE_UNITS(GLEnum.MAX_VERTEX_TEXTURE_IMAGE_UNITS),
  /** Returns int */
  MAX_VERTEX_UNIFORM_VECTORS(GLEnum.MAX_VERTEX_UNIFORM_VECTORS),
  /** Returns {@link gwt.g3d.client.gl2.array.WebGLIntArray} (with 2 elements) */
  MAX_VIEWPORT_DIMS(GLEnum.MAX_VIEWPORT_DIMS),
	/** Returns int */
	NUM_COMPRESSED_TEXTURE_FORMATS(GLEnum.PACK_ALIGNMENT),
	/** Returns int */
	PACK_ALIGNMENT(GLEnum.PACK_ALIGNMENT),
	/** Returns float */
  POLYGON_OFFSET_FACTOR(GLEnum.POLYGON_OFFSET_FACTOR),
  /** Returns boolean */
  POLYGON_OFFSET_FILL(GLEnum.POLYGON_OFFSET_FILL),
  /** Returns float */
  POLYGON_OFFSET_UNITS(GLEnum.POLYGON_OFFSET_UNITS),
  /** Returns int */
  RED_BITS(GLEnum.RED_BITS),
  /** Returns {@link gwt.g3d.client.gl2.WebGLRenderbuffer} */
  RENDERBUFFER_BINDING(GLEnum.RENDERBUFFER_BINDING),
  /** Returns int */
  SAMPLE_BUFFERS(GLEnum.SAMPLE_BUFFERS),
  /** Returns boolean */
  SAMPLE_COVERAGE_INVERT(GLEnum.SAMPLE_COVERAGE_INVERT),
  /** Returns float */
  SAMPLE_COVERAGE_VALUE(GLEnum.SAMPLE_COVERAGE_VALUE),
	/** Returns int */
  SAMPLES(GLEnum.SAMPLES),
  /** Returns {@link gwt.g3d.client.gl2.array.WebGLIntArray} (with 4 elements) */
  SCISSOR_BOX(GLEnum.SCISSOR_BOX),
  /** Returns boolean */
  SCISSOR_TEST(GLEnum.SCISSOR_TEST),
  /** Returns int */
  STENCIL_BACK_FAIL(GLEnum.STENCIL_BACK_FAIL),
  /** Returns int */
  STENCIL_BACK_FUNC(GLEnum.STENCIL_BACK_FUNC),
  /** Returns int */
  STENCIL_BACK_PASS_DEPTH_FAIL(GLEnum.STENCIL_BACK_PASS_DEPTH_FAIL),
  /** Returns int */
  STENCIL_BACK_PASS_DEPTH_PASS(GLEnum.STENCIL_BACK_PASS_DEPTH_PASS),
  /** Returns int */
  STENCIL_BACK_REF(GLEnum.STENCIL_BACK_REF),
  /** Returns int */
  STENCIL_BACK_VALUE_MASK(GLEnum.STENCIL_BACK_VALUE_MASK),
  /** Returns int */
  STENCIL_BACK_WRITEMASK(GLEnum.STENCIL_BACK_WRITEMASK),
  /** Returns int */
  STENCIL_BITS(GLEnum.STENCIL_BITS),	
	/** Returns int */
  STENCIL_CLEAR_VALUE(GLEnum.STENCIL_CLEAR_VALUE),
  /** Returns int */
  STENCIL_FAIL(GLEnum.STENCIL_FAIL),
  /** Returns int */
  STENCIL_FUNC(GLEnum.STENCIL_FUNC),
  /** Returns int */
  STENCIL_PASS_DEPTH_FAIL(GLEnum.STENCIL_PASS_DEPTH_FAIL),
  /** Returns int */
  STENCIL_PASS_DEPTH_PASS(GLEnum.STENCIL_PASS_DEPTH_PASS),
  /** Returns int */
  STENCIL_REF(GLEnum.STENCIL_REF),
  /** Returns int */
  STENCIL_VALUE_MASK(GLEnum.STENCIL_VALUE_MASK),
  /** Returns int */
  STENCIL_WRITEMASK(GLEnum.STENCIL_WRITEMASK),
  /** Returns int */
  SUBPIXEL_BITS(GLEnum.SUBPIXEL_BITS),
	/** Returns {@link gwt.g3d.client.gl2.WebGLTexture} */
  TEXTURE_BINDING_2D(GLEnum.TEXTURE_BINDING_2D),
  /** Returns {@link gwt.g3d.client.gl2.WebGLTexture} */
  TEXTURE_BINDING_CUBE_MAP(GLEnum.TEXTURE_BINDING_CUBE_MAP),
  /** Returns int */
  UNPACK_ALIGNMENT(GLEnum.UNPACK_ALIGNMENT),
  /** Returns {@link gwt.g3d.client.gl2.array.WebGLIntArray} (with 4 elements) */
  VIEWPORT(GLEnum.VIEWPORT);

  private static Map<Integer, GetPName> getPNameMap;
  private final int value;

  private GetPName(GLEnum glEnum) {
    this.value = glEnum.getValue();
  }
  /**
   * Gets the enum's numerical value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Parses an integer value to its corresponding GetPName enum.
   *
   * @param getPName
   */
  public static GetPName parseGetPName(int getPName) {
    if (getPNameMap == null) {
      getPNameMap = new HashMap<Integer, GetPName>();
      for (GetPName v : values()) {
        getPNameMap.put(v.getValue(), v);
      }
    }
    return getPNameMap.get(getPName);
  }
}
