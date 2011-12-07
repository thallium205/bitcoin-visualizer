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
package gwt.g3d.client.gl2;

import gwt.g2d.client.graphics.canvas.CanvasElement;
import gwt.g2d.client.graphics.canvas.ImageData;
import gwt.g2d.client.media.VideoElement;
import gwt.g3d.client.gl2.array.ArrayBuffer;
import gwt.g3d.client.gl2.array.ArrayBufferView;
import gwt.g3d.client.gl2.array.Float32Array;
import gwt.g3d.client.gl2.array.Int32Array;
import gwt.g3d.client.gl2.array.TypeArray;
import gwt.g3d.client.gl2.array.WebGLArray;
import gwt.g3d.client.gl2.array.WebGLArrayBuffer;
import gwt.g3d.client.gl2.array.WebGLFloatArray;
import gwt.g3d.client.gl2.array.WebGLIntArray;
import gwt.g3d.client.gl2.enums.BeginMode;
import gwt.g3d.client.gl2.enums.BlendEquationMode;
import gwt.g3d.client.gl2.enums.BlendingFactorDest;
import gwt.g3d.client.gl2.enums.BlendingFactorSrc;
import gwt.g3d.client.gl2.enums.BufferParameterName;
import gwt.g3d.client.gl2.enums.BufferTarget;
import gwt.g3d.client.gl2.enums.BufferUsage;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.CullFaceMode;
import gwt.g3d.client.gl2.enums.DataType;
import gwt.g3d.client.gl2.enums.DepthFunction;
import gwt.g3d.client.gl2.enums.DrawElementsType;
import gwt.g3d.client.gl2.enums.EnableCap;
import gwt.g3d.client.gl2.enums.ErrorCode;
import gwt.g3d.client.gl2.enums.FramebufferErrorCode;
import gwt.g3d.client.gl2.enums.FramebufferParameterName;
import gwt.g3d.client.gl2.enums.FramebufferSlot;
import gwt.g3d.client.gl2.enums.FramebufferTarget;
import gwt.g3d.client.gl2.enums.FrontFaceDirection;
import gwt.g3d.client.gl2.enums.GetPName;
import gwt.g3d.client.gl2.enums.HintMode;
import gwt.g3d.client.gl2.enums.HintTarget;
import gwt.g3d.client.gl2.enums.PixelFormat;
import gwt.g3d.client.gl2.enums.PixelInternalFormat;
import gwt.g3d.client.gl2.enums.PixelStoreParameter;
import gwt.g3d.client.gl2.enums.PixelType;
import gwt.g3d.client.gl2.enums.ProgramParameter;
import gwt.g3d.client.gl2.enums.RenderbufferInternalFormat;
import gwt.g3d.client.gl2.enums.RenderbufferParameterName;
import gwt.g3d.client.gl2.enums.RenderbufferTarget;
import gwt.g3d.client.gl2.enums.ShaderParameter;
import gwt.g3d.client.gl2.enums.ShaderType;
import gwt.g3d.client.gl2.enums.StencilFunction;
import gwt.g3d.client.gl2.enums.StencilOp;
import gwt.g3d.client.gl2.enums.StringName;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureParameterName;
import gwt.g3d.client.gl2.enums.TextureTarget;
import gwt.g3d.client.gl2.enums.TextureUnit;
import gwt.g3d.client.gl2.enums.TextureWrapMode;
import gwt.g3d.client.gl2.enums.VertexAttribParameter;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple2f;
import javax.vecmath.Tuple2i;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple3i;
import javax.vecmath.Tuple4f;
import javax.vecmath.Tuple4i;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.dom.client.ImageElement;

/**
 * Interface for defining a WebGL rendering context.
 * 
 * @author hao1300@gmail.com
 */
@SuppressWarnings("deprecation")
public interface GL2 {
	
	/**
	 * Select active texture unit.
	 * 
	 * @param texture
	 */
	void activeTexture(TextureUnit texture);

	/**
	 * Attach a shader object to a program object.
	 * 
	 * @param program Specifies the program object to which a shader object will 
	 * 				be attached.
	 * @param shader Specifies the shader object that is to be attached.
	 */
	void attachShader(WebGLProgram program, WebGLShader shader);
	
	/**
	 * Associate a generic vertex attribute index with a named attribute variable.
	 * 
	 * @param program Specifies the handle of the program object in which the 
	 * 				association is to be made.
	 * @param index Specifies the index of the generic vertex attribute to be 
	 * 				bound.
	 * @param name Specifies a null terminated string containing the name of the 
	 * 				vertex shader attribute variable to which index is to be bound.
	 */
	void bindAttribLocation(WebGLProgram program, int index, String name);
	
	/**
	 * Bind a named buffer object.
	 * 
	 * @param target Specifies the target to which the buffer object is bound.
	 * @param buffer Specifies the name of a buffer object.
	 */
	void bindBuffer(BufferTarget target, WebGLBuffer buffer);
	
	/**
	 * Bind a named framebuffer object.
	 * 
	 * @param target Specifies the target to which the framebuffer object is 
	 * 				bound.
	 * @param buffer Specifies the name of a framebuffer object.
	 */
	void bindFramebuffer(FramebufferTarget target, WebGLFramebuffer buffer);
	
	/**
	 * Bind a named renderbuffer object.
	 * 
	 * @param target Specifies the target to which the renderbuffer object is 
	 * 				bound.
	 * @param buffer Specifies the name of a renderbuffer object.
	 */
	void bindRenderbuffer(RenderbufferTarget target, WebGLRenderbuffer buffer);
	
	/**
	 * Bind a named texture to a texturing target.
	 * 
	 * @param target Specifies the target to which the texture is bound.
	 * @param texture Specifies the name of a texture.
	 */
	void bindTexture(TextureTarget target, WebGLTexture texture);
	
	/**
	 * Set the blend color.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void blendColor(float red, float green, float blue, float alpha);
	
	/**
	 * Specify the equation used for both the RGB blend equation and the Alpha 
	 * blend equation.
	 * 
	 * @param mode
	 */
	void blendEquation(BlendEquationMode mode); 
	
	/**
	 * Set the RGB blend equation and the alpha blend equation separately.
	 */
	void blendEquationSeparate(BlendEquationMode modeRGB, BlendEquationMode modeAlpha);
	
	/**
	 * Specify pixel arithmetic.
	 */
	void blendFunc(BlendingFactorSrc sfactor, BlendingFactorDest dfactor);
	
	/**
	 * Specify pixel arithmetic for RGB and alpha components separately.
	 * 
	 * @param srcRGB
	 * @param dstRGB
	 * @param srcAlpha
	 * @param dstAlpha
	 */
	void blendFuncSeparate(BlendingFactorSrc srcRGB, BlendingFactorDest dstRGB, 
			BlendingFactorSrc srcAlpha, BlendingFactorDest dstAlpha);
	
	/**
	 * Set the size of the currently bound WebGLBuffer object for the passed 
	 * target to the size of the passed data, then write the contents of data to 
	 * the buffer object.
	 * 
	 * @param target Specifies the target buffer object.
	 * @param data Specifies a pointer to data that will be copied into the data 
	 * 				store for initialization
	 * @param usage Specifies the expected usage pattern of the data store.
	 */
	void bufferData(BufferTarget target, ArrayBuffer data, BufferUsage usage);
	
	/**
	 * Set the size of the currently bound WebGLBuffer object for the passed 
	 * target to the size of the passed data, then write the contents of data to 
	 * the buffer object.
	 * 
	 * @param target Specifies the target buffer object.
	 * @param data Specifies a pointer to data that will be copied into the data 
	 * 				store for initialization
	 * @param usage Specifies the expected usage pattern of the data store.
	 */
	void bufferData(BufferTarget target, ArrayBufferView data, BufferUsage usage);
	
	/**
	 * Set the size of the currently bound WebGLBuffer object for the passed 
	 * target. The buffer is initialized to 0.
	 * 
	 * @param target Specifies the target buffer object.
	 * @param size Specifies the size in bytes of the buffer object's new data 
	 * 				store.
	 * @param usage Specifies the expected usage pattern of the data store.
	 */
	void bufferData(BufferTarget target, int size, BufferUsage usage);
	
	/**
	 * Set the size of the currently bound WebGLBuffer object for the passed 
	 * target to the size of the passed data, then write the contents of data to 
	 * the buffer object.
	 * 
	 * @param target Specifies the target buffer object.
	 * @param data Specifies a pointer to data that will be copied into the data 
	 * 				store for initialization
	 * @param usage Specifies the expected usage pattern of the data store.
	 */
	@Deprecated
	void bufferData(BufferTarget target, WebGLArray data, BufferUsage usage);
	
	/**
	 * Set the size of the currently bound WebGLBuffer object for the passed 
	 * target to the size of the passed data, then write the contents of data to 
	 * the buffer object.
	 * 
	 * @param target Specifies the target buffer object.
	 * @param data Specifies a pointer to data that will be copied into the data 
	 * 				store for initialization
	 * @param usage Specifies the expected usage pattern of the data store.
	 */
	@Deprecated
	void bufferData(BufferTarget target, WebGLArrayBuffer data, BufferUsage usage);
	
	/**
	 * For the WebGLBuffer object bound to the passed target write the passed 
	 * data starting at the passed offset. If the data would be written past the 
	 * end of the buffer object an INVALID_VALUE error is raised.
	 * 
	 * @param target Specifies the target buffer object.
	 * @param offset Specifies the offset into the buffer object's data store 
	 * 				where data replacement will begin, measured in bytes.
	 * @param data Specifies a pointer to the new data that will be copied into 
	 * 				the data store.
	 */
	void bufferSubData(BufferTarget target, int offset, ArrayBuffer data);
	
	/**
	 * For the WebGLBuffer object bound to the passed target write the passed 
	 * data starting at the passed offset. If the data would be written past the 
	 * end of the buffer object an INVALID_VALUE error is raised.
	 * 
	 * @param target Specifies the target buffer object.
	 * @param offset Specifies the offset into the buffer object's data store 
	 * 				where data replacement will begin, measured in bytes.
	 * @param data Specifies a pointer to the new data that will be copied into 
	 * 				the data store.
	 */
	void bufferSubData(BufferTarget target, int offset, ArrayBufferView data);
	
	/**
	 * For the WebGLBuffer object bound to the passed target write the passed 
	 * data starting at the passed offset. If the data would be written past the 
	 * end of the buffer object an INVALID_VALUE error is raised.
	 * 
	 * @param target Specifies the target buffer object.
	 * @param offset Specifies the offset into the buffer object's data store 
	 * 				where data replacement will begin, measured in bytes.
	 * @param data Specifies a pointer to the new data that will be copied into 
	 * 				the data store.
	 */
	@Deprecated
	void bufferSubData(BufferTarget target, int offset, WebGLArray data);
	
	/**
	 * For the WebGLBuffer object bound to the passed target write the passed 
	 * data starting at the passed offset. If the data would be written past the 
	 * end of the buffer object an INVALID_VALUE error is raised.
	 * 
	 * @param target Specifies the target buffer object.
	 * @param offset Specifies the offset into the buffer object's data store 
	 * 				where data replacement will begin, measured in bytes.
	 * @param data Specifies a pointer to the new data that will be copied into 
	 * 				the data store.
	 */
	@Deprecated
	void bufferSubData(BufferTarget target, int offset, WebGLArrayBuffer data); 
	
	/**
	 * Return the framebuffer completeness status of a framebuffer object.
	 * 
	 * @param target Specifies the target to which the framebuffer object is 
	 * 				bound.
	 * @return identifies whether or not the currently bound framebuffer is 
	 * 				framebuffer complete, and if not, which of the rules of framebuffer 
	 * 				completeness is violated.
	 */
	FramebufferErrorCode checkFramebufferStatus(FramebufferTarget target);
	
	/**
	 * Clear buffers to preset values.
	 * 
	 * @param mask
	 */
	void clear(ClearBufferMask mask);
	
	/**
	 * Clear buffers to preset values.
	 * 
	 * @param mask1
	 * @param mask2
	 */
	void clear(ClearBufferMask mask1, ClearBufferMask mask2);
	
	/**
	 * Clear buffers to preset values.
	 * 
	 * @param mask1
	 * @param mask2
	 * @param mask3
	 */
	void clear(ClearBufferMask mask1, ClearBufferMask mask2, ClearBufferMask mask3);
	
	/**
	 * Specify the red, green, blue, and alpha values used when the color buffers 
	 * are cleared. The initial values are all 0.
	 * 
	 * @param red 
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void clearColor(float red, float green, float blue, float alpha);
	
	/**
	 * Specifies the depth value used when the depth buffer is cleared. The 
	 * initial value is 1.
	 * 
	 * @param depth
	 */
	void clearDepth(float depth);
	
	/**
	 * Specifies the index used when the stencil buffer is cleared. The initial 
	 * value is 0.
	 * 
	 * @param s
	 */
	void clearStencil(int s);
	
	/**
	 * Specify whether red, green, blue, and alpha can or cannot be written into 
	 * the frame buffer. The initial values are all true, indicating that the 
	 * color components can be written.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void colorMask(boolean red, boolean green, boolean blue, boolean alpha);
	
	/**
	 * Compile a shader object.
	 * 
	 * @param shader Specifies the shader object to be compiled.
	 */
	void compileShader(WebGLShader shader);
	
	/**
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param internalformat Specifies the internal format of the texture.
	 * @param x Specify the window coordinates of the x-coordinate of the 
	 * 				rectangular region of pixels to be copied.
	 * @param y Specify the window coordinates of the y-coordinate of the 
	 * 				rectangular region of pixels to be copied.
	 * @param width Specifies the width of the texture image. All implementations 
	 * 				support 2D texture images that are at least 64 texels wide and 
	 * 				cube-mapped texture images that are at least 16 texels wide.
	 * @param height Specifies the height of the texture image. All 
	 * 				implementations support 2D texture images that are at least 64 
	 * 				texels high and cube-mapped texture images that are at least 16 
	 * 				texels high.
	 * @param border Specifies the width of the border. Must be 0.
	 */
	void copyTexImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, int x, int y, int width, int height, 
			int border);
	
	/**
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param x Specify the window coordinates of the lower left corner of the 
	 * 				rectangular region of pixels to be copied.
	 * @param y Specify the window coordinates of the lower left corner of the 
	 * 				rectangular region of pixels to be copied.
	 * @param width Specifies the width of the texture subimage.
	 * @param height Specifies the height of the texture subimage.
	 */
	void copyTexSubImage2D(TextureTarget target, int level, 
			int xoffset, int yoffset, int x, int y, int width, int height);
	
	/**
	 * Create a WebGLBuffer object and initialize it with a buffer object name as 
	 * if by calling glGenBuffers.
	 */
	WebGLBuffer createBuffer();
	
	/**
	 * Create a WebGLFramebuffer object and initialize it with a framebuffer 
	 * object name as if by calling glGenFramebuffers.
	 */
	WebGLFramebuffer createFramebuffer();
	
	/**
	 * Create a WebGLProgram object and initialize it with a program object name 
	 * as if by calling glCreateProgram.
	 */
	WebGLProgram createProgram();
	
	/**
	 * Create a WebGLRenderbuffer object and initialize it with a renderbuffer 
	 * object name as if by calling glGenRenderbuffers.
	 */
	WebGLRenderbuffer createRenderbuffer();
	
	/**
	 * Create a WebGLShader object and initialize it with a shader object name 
	 * as if by calling glCreateShader.
	 */
	WebGLShader createShader(ShaderType type);
	
	/**
	 * Create a WebGLTexture object and initialize it with a texture object name 
	 * as if by calling glGenTextures.
	 */
	WebGLTexture createTexture();
	
	/**
	 * Specify whether front- or back-facing facets can be culled.
	 * 
	 * @param mode
	 */
	void cullFace(CullFaceMode mode);
	
	/**
	 * Delete the buffer object contained in the passed WebGLBuffer as if by 
	 * calling glDeleteBuffers. If the buffer has already been deleted the call 
	 * has no effect. Note that the buffer object will be deleted when the 
	 * WebGLBuffer object is destroyed. This method merely gives the author 
	 * greater control over when the buffer object is destroyed.
	 * 
	 * @param buffer
	 */
	void deleteBuffer(WebGLBuffer buffer);
	
	/**
	 * Delete the framebuffer object contained in the passed WebGLFramebuffer as 
	 * if by calling glDeleteFramebuffers. If the framebuffer has already been 
	 * deleted the call has no effect. Note that the framebuffer object will be 
	 * deleted when the WebGLFramebuffer object is destroyed. This method merely 
	 * gives the author greater control over when the framebuffer object is 
	 * destroyed.
	 * 
	 * @param buffer
	 */
	void deleteFramebuffer(WebGLFramebuffer buffer);
	
	/**
	 * Delete the program object contained in the passed WebGLProgram as if by 
	 * calling glDeleteProgram. If the program has already been deleted the call 
	 * has no effect. Note that the program object will be deleted when the 
	 * WebGLProgram object is destroyed. This method merely gives the author 
	 * greater control over when the program object is destroyed.
	 * 
	 * @param program
	 */
	void deleteProgram(WebGLProgram program);
	
	/**
	 * Delete the renderbuffer object contained in the passed WebGLRenderbuffer 
	 * as if by calling glDeleteRenderbuffers. If the renderbuffer has already 
	 * been deleted the call has no effect. Note that the renderbuffer object 
	 * will be deleted when the WebGLRenderbuffer object is destroyed. This 
	 * method merely gives the author greater control over when the renderbuffer 
	 * object is destroyed.
	 * 
	 * @param buffer
	 */
	void deleteRenderbuffer(WebGLRenderbuffer buffer);
	
	/**
	 * Delete the shader object contained in the passed WebGLShader as if by 
	 * calling glDeleteShader. If the shader has already been deleted the call
	 * has no effect. Note that the shader object will be deleted when the 
	 * WebGLShader object is destroyed. This method merely gives the author 
	 * greater control over when the shader object is destroyed.
	 * 
	 * @param shader
	 */
	void deleteShader(WebGLShader shader);
	
	/**
	 * Delete the texture object contained in the passed WebGLTexture as if by 
	 * calling glDeleteTextures. If the texture has already been deleted the 
	 * call has no effect. Note that the texture object will be deleted when the 
	 * WebGLTexture object is destroyed. This method merely gives the author 
	 * greater control over when the texture object is destroyed.
	 * 
	 * @param texture
	 */
	void deleteTexture(WebGLTexture texture);
	
	/**
	 * Specify the value used for depth buffer comparisons.
	 * 
	 * @param func
	 */
	void depthFunc(DepthFunction func);
	
	/**
	 * Enable or disable writing into the depth buffer.
	 * 
	 * @param flag
	 */
	void depthMask(boolean flag);
	
	/**
	 * Specify mapping of depth values from normalized device coordinates to 
	 * window coordinates.
	 * 
	 * @param zNear Specifies the mapping of the near clipping plane to window 
	 * 				coordinates. The initial value is 0.
	 * @param zFar Specifies the mapping of the far clipping plane to window
	 * 				coordinates. The initial value is 1.
	 */
	void depthRange(float zNear, float zFar);

	/**
	 * Detach a shader object from a program object.
	 * 
	 * @param program Specifies the program object from which to detach the 
	 * 				shader object.
	 * @param shader Specifies the shader object to be detached.
	 */
	void detachShader(WebGLProgram program, WebGLShader shader);
	
	/**
	 * Disable server-side GL capabilities.
	 * 
	 * @param cap
	 */
	void disable(EnableCap cap);
	
	/**
   * Disable a generic vertex attribute array.
   * 
   * @param index Specifies the index of the generic vertex attribute to be 
   * 				disabled.
   */
  void disableVertexAttribArray(int index);
	
	/**
	 * Draw using the currently bound index array.
	 * 
	 * @param mode Specifies what kind of primitives to render.
	 * @param first Specifies the starting index in the enabled arrays.
	 * @param count Specifies the number of indices to be rendered.
	 */
	void drawArrays(BeginMode mode, int first, int count);
	
	/**
	 * Draw using the currently bound index array. The given offset is in bytes, 
	 * and must be a valid multiple of the size of the given type or an 
	 * INVALID_VALUE error will be raised.
	 * 
	 * @param mode Specifies what kind of primitives to render.
	 * @param count Specifies the number of elements to be rendered.
	 * @param type Specifies the type of the values in indices.
	 * @param offset Specifies a pointer to the location where the indices are 
	 * 				stored.
	 */
	void drawElements(BeginMode mode, int count, DrawElementsType type, int offset);
	
	/**
	 * Enable server-side GL capabilities.
	 * 
	 * @param cap
	 */
	void enable(EnableCap cap);
	
	/**
   * Enable a generic vertex attribute array.
   * 
   * @param index Specifies the index of the generic vertex attribute to be 
   * 				disabled.
   */
  void enableVertexAttribArray(int index);
	
	/**
	 * Block until all GL execution is complete.
	 */
	void finish();
	
	/**
	 * Force execution of GL commands in finite time
	 */
	void flush();
	
	/**
	 * Attach a renderbuffer object to a framebuffer object.
	 * 
	 * @param target Specifies the framebuffer target.
	 * @param attachment Specifies the attachment point to which renderbuffer 
	 * 				should be attached.
	 * @param renderbuffertarget Specifies the renderbuffer target.
	 * @param renderbuffer Specifies the renderbuffer object that is to be 
	 * 				attached.
	 */
	void framebufferRenderbuffer(FramebufferTarget target, 
			FramebufferSlot attachment, RenderbufferTarget renderbuffertarget, 
      WebGLRenderbuffer renderbuffer);
	
	/**
	 * Attach a texture image to a framebuffer object.
	 * 
	 * @param target Specifies the framebuffer target.
	 * @param attachment Specifies the attachment point to which an image from 
	 * 				texture should be attached.
	 * @param textarget Specifies the texture target.
	 * @param texture Specifies the texture object whose image is to be attached.
	 * @param level Specifies the mipmap level of the texture image to be 
	 * 				attached, which must be 0.
	 */
	void framebufferTexture2D(FramebufferTarget target, 
			FramebufferSlot attachment, TextureTarget textarget, 
			WebGLTexture texture, int level);
	
	/**
	 * Define front- and back-facing polygons.
	 * 
	 * @param mode
	 */
	void frontFace(FrontFaceDirection mode);
	
	/**
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target
	 */
	void generateMipmap(TextureTarget target);
	
	/**
	 * Returns information about the size, type and name of the vertex attribute 
	 * at the passed index of the passed program object.
	 * 
	 * @param program
	 * @param index
	 */
	WebGLActiveInfo getActiveAttrib(WebGLProgram program, int index);
	
	/**
	 * Returns information about the size, type and name of the uniform at the 
	 * passed index of the passed program object.
	 * 
	 * @param program
	 * @param index
	 */
  WebGLActiveInfo getActiveUniform(WebGLProgram program, int index);
	
	/**
   * Returns the list of shaders attached to the passed program.
   * 
   * @param program
   */
  WebGLShader[] getAttachedShaders(WebGLProgram  program);
	
	/**
	 * Returns the location of an attribute variable.
	 * 
	 * @param program Specifies the program object to be queried.
	 * @param name Points to string containing the name of the attribute variable 
	 * 				whose location is to be queried.
	 */
	int getAttribLocation(WebGLProgram program, String name);
	
	/**
	 * Return parameters of a buffer object
	 * 
	 * @param target Specifies the target buffer object.
	 * @param pname Specifies the symbolic name of a buffer object parameter.
	 * @return the value for the passed pname. The type returned is the natural 
	 * 				type for the requested pname.
	 */
	int getBufferParameteri(BufferTarget target, BufferParameterName pname);
	
	/**
	 * Gets the reference to the canvas element which created this context.
	 */
	CanvasElement getCanvas();
	
	/**
	 * Gets the WebGLContextAttributes desribing the current drawing buffer.
	 */
	WebGLContextAttributes getContextAttributes();
  
  /**
	 * Return error information.
	 */
	ErrorCode getError();
  
	/**
	 * Returns an object if the passed extension is supported, or null if not. 
	 * The object returned from getExtension contains any constants or 
	 * functions used by the extension, if any. A returned object may have no 
	 * constants or functions if the extension does not define any, but a unique 
	 * object must still be returned. That object is used to indicate that the 
	 * extension has been enabled.
	 */
  JavaScriptObject getExtension(String name);
	
	/**
	 * Return the value for the passed pname given the passed target and 
	 * attachment.
	 * 
	 * @param <T> return type is dependent on pname.
	 * @param target Specifies the target framebuffer object.
	 * @param attachment Specifies the symbolic name of a framebuffer object 
	 * 				attachment point.
	 * @param pname Specifies the symbolic name of a framebuffer object 
	 * 				attachment parameter.
	 */
	<T extends WebGLObject> T getFramebufferAttachmentParameter(
			FramebufferTarget target, FramebufferSlot attachment, 
			FramebufferParameterName pname);
	
	/**
	 * Return the value for the passed pname given the passed target and 
	 * attachment.
	 * 
	 * @param target Specifies the target framebuffer object.
	 * @param attachment Specifies the symbolic name of a framebuffer object 
	 * 				attachment point.
	 * @param pname Specifies the symbolic name of a framebuffer object 
	 * 				attachment parameter.
	 */
	int getFramebufferAttachmentParameteri(FramebufferTarget target, 
			FramebufferSlot attachment, FramebufferParameterName pname);
	
	/**
	 * Return the value or values of a selected parameter.
	 * 
	 * @param <T> return type is dependent on pname.
	 * @param pname
	 */
	<T extends JavaScriptObject> T getParameter(GetPName pname);
	
	/**
	 * Return the value or values of a selected parameter.
	 * 
	 * @param pname
	 */
	boolean getParameterb(GetPName pname);
	
	/**
	 * Return the value or values of a selected parameter.
	 * 
	 * @param pname
	 */
	float getParameterf(GetPName pname);
	
	/**
	 * Return the value or values of a selected parameter.
	 * 
	 * @param pname
	 */
	int getParameteri(GetPName pname);
	
	/**
	 * Returns the information log for a program object.
	 * 
	 * @param program Specifies the program object whose information log is to 
	 * 				be queried.
	 */
	String getProgramInfoLog(WebGLProgram program);
	
	/**
	 * Return the value for the passed pname given the passed program.
	 * 
	 * @param program Specifies the program object to be queried.
	 * @param pname Specifies the object parameter.
	 */
	boolean getProgramParameterb(WebGLProgram program, ProgramParameter pname);
	
	/**
	 * Return the value for the passed pname given the passed program.
	 * 
	 * @param program Specifies the program object to be queried.
	 * @param pname Specifies the object parameter.
	 */
	int getProgramParameteri(WebGLProgram program, ProgramParameter pname);
  
  /**
	 * Return the value for the passed pname given the passed target.
	 * 
	 * @param target Specifies the target renderbuffer object.
	 * @param pname Specifies the symbolic name of a renderbuffer object 
	 * 				parameter.
	 */
	int getRenderbufferParameteri(RenderbufferTarget target, 
			RenderbufferParameterName pname);
  
	/**
	 * Returns the information log for a shader object.
	 * 
	 * @param shader Specifies the shader object whose information log is to be 
	 * 				queried.
	 */
  String getShaderInfoLog(WebGLShader shader);
	
	/**
	 * Return the value for the passed pname given the passed shader.
	 * 
	 * @param shader Specifies the shader object to be queried.
	 * @param pname Specifies the object parameter.
	 */
	boolean getShaderParameterb(WebGLShader shader, ShaderParameter pname);
	
	/**
	 * Return the value for the passed pname given the passed shader.
	 * 
	 * @param shader Specifies the shader object to be queried.
	 * @param pname Specifies the object parameter.
	 */
	int getShaderParameteri(WebGLShader shader, ShaderParameter pname);
	
	/**
   * Returns the source code string from a shader object.
   * 
   * @param shader Specifies the shader object to be queried.
   */
  String getShaderSource(WebGLShader shader);
	
	/**
	 * Return a string describing the current GL connection.
	 * 
	 * @param name
	 */
	String getString(StringName name);
	
	/**
	 * Returns an array of all the supported extension strings. Any string in 
	 * this list, when passed to getExtension must return a valid object. Any 
	 * other string passed to getExtension must return null.
	 */
	String[] getSupportedExtensions();
	
	/**
	 * Return the uniform value at the passed location in the passed program. 
	 * 
	 * @param <T> return type is dependent on the type of the uniform variable.
	 * @param program Specifies the program object to be queried.
	 * @param location Specifies the location of the uniform variable to be 
	 * 				queried.
	 * @return The type returned is dependent on the uniform type.
	 */
	<T extends TypeArray> T getUniform(WebGLProgram program, 
			WebGLUniformLocation location);
	
	/**
	 * Return the uniform value at the passed location in the passed program. 
	 * 
	 * @param program Specifies the program object to be queried.
	 * @param location Specifies the location of the uniform variable to be 
	 * 				queried.
	 */
	boolean getUniformb(WebGLProgram program, WebGLUniformLocation location);
	
	/**
	 * Return the uniform value at the passed location in the passed program. 
	 * 
	 * @param program Specifies the program object to be queried.
	 * @param location Specifies the location of the uniform variable to be 
	 * 				queried.
	 * @return The type returned is dependent on the uniform type.
	 */
	float getUniformf(WebGLProgram program, WebGLUniformLocation location);
	
	/**
	 * Return the uniform value at the passed location in the passed program. 
	 * 
	 * @param program Specifies the program object to be queried.
	 * @param location Specifies the location of the uniform variable to be 
	 * 				queried.
	 */
	int getUniformi(WebGLProgram program, WebGLUniformLocation location);
	
	/**
	 * Return the location of a uniform variable.
	 * 
	 * @param program Specifies the program object to be queried.
	 * @param name Points to a string containing the name of the uniform variable 
	 * 				whose location is to be queried.
	 */
	WebGLUniformLocation getUniformLocation(WebGLProgram program, String name);
	
	/**
	 * Return the information requested in pname about the vertex attribute at 
	 * the passed index. 
	 * 
	 * @param <T> return type is dependent on pname.
	 * @param index Specifies the generic vertex attribute parameter to be 
	 * 				queried.
	 * @param pname Specifies the symbolic name of the vertex attribute parameter
	 * 				to be queried.
	 * @return The type returned is dependent on the information requested.
	 */
	<T extends JavaScriptObject> T getVertexAttrib(int index, VertexAttribParameter pname);
	
	/**
	 * Return the information requested in pname about the vertex attribute at 
	 * the passed index. 
	 * 
	 * @param index Specifies the generic vertex attribute parameter to be 
	 * 				queried.
	 * @param pname Specifies the symbolic name of the vertex attribute parameter
	 * 				to be queried.
	 * @return The type returned is dependent on the information requested.
	 */
	boolean getVertexAttribb(int index, VertexAttribParameter pname);

	/**
	 * Return the information requested in pname about the vertex attribute at 
	 * the passed index. 
	 * 
	 * @param index Specifies the generic vertex attribute parameter to be 
	 * 				queried.
	 * @param pname Specifies the symbolic name of the vertex attribute parameter
	 * 				to be queried.
	 * @return The type returned is dependent on the information requested.
	 */
	int getVertexAttribi(int index, VertexAttribParameter pname);
	 
	/**
	 * Specify implementation-specific hints.
	 * 
	 * @param target Specifies a symbolic constant indicating the behavior to be 
	 * 				controlled.
	 * @param mode Specifies a symbolic constant indicating the desired behavior.
	 */
	void hint(HintTarget target, HintMode mode);
	
	/**
	 * Determine if a name corresponds to a buffer object.
	 * 
	 * @param buffer
	 */
	boolean isBuffer(WebGLObject buffer);

	/**
	 * Returns true if the context is in the lost state.
	 */
	boolean isContextLost();
	
  /**
	 * Test whether a capability is enabled.
	 * 
	 * @param cap
	 */
	boolean isEnabled(EnableCap cap);
  /**
	 * Return true if the passed WebGLObject is a WebGLFramebuffer and false 
	 * otherwise.
	 * 
	 * @param buffer
	 */
	boolean isFramebuffer(WebGLObject buffer);
  /**
	 * Return true if the passed WebGLObject is a WebGLProgram and false 
	 * otherwise.
	 * 
	 * @param program
	 */
	boolean isProgram(WebGLObject program);
  /**
	 * Return true if the passed WebGLObject is a WebGLRenderbuffer and false 
	 * otherwise.
	 * 
	 * @param buffer
	 */
	boolean isRenderbuffer(WebGLObject buffer);
  /**
	 * Return true if the passed WebGLObject is a WebGLShader and false 
	 * otherwise.
	 * 
	 * @param shader
	 */
	boolean isShader(WebGLObject shader);
  /**
	 * Return true if the passed WebGLObject is a WebGLTexture and false 
	 * otherwise.
	 * 
	 * @param buffer
	 */
	boolean isTexture(WebGLObject buffer);
  /**
	 * Specifies the width of rasterized lines. The initial value is 1.
	 * 
	 * @param width
	 */
	void lineWidth(float width);
  /**
	 * Link a program object.
	 * 
	 * @param program Specifies the handle of the program object to be linked.
	 */
	void linkProgram(WebGLProgram program);
  /**
	 * Set pixel storage modes.
	 * 
	 * @param pname
	 * @param param
	 */
	void pixelStorei(PixelStoreParameter pname, int param);
  /**
	 * Set the scale and units used to calculate depth values.
	 * 
	 * @param factor Specifies a scale factor that is used to create a variable 
	 * 				depth offset for each polygon. The initial value is 0.
	 * @param units Is multiplied by an implementation-specific value to create a
	 * 				constant depth offset. The initial value is 0.
	 */
	void polygonOffset(float factor, float units);
  /**
	 * Read a block of pixels from the frame buffer.
	 * 
	 * @param x Specify the window coordinates of the first pixel that is 
	 * 				read from the frame buffer. This location is the lower left corner
	 * 				of a rectangular block of pixels.
	 * @param y Specify the window coordinates of the first pixel that is 
	 * 				read from the frame buffer. This location is the lower left corner
	 * 				of a rectangular block of pixels.
	 * @param width Specify the dimensions of the pixel rectangle. width 
	 * 				and height of one correspond to a single pixel.
	 * @param height Specify the dimensions of the pixel rectangle. width 
	 * 				and height of one correspond to a single pixel.
	 * @param format Specifies the format of the pixel data.
	 * @param type Specifies the data type of the pixel data.
	 * @return a WebGLArray with pixels within the passed rectangle. The specific 
	 * 				subclass of WebGLArray returned depends on the passed type. If it 
	 * 				is UNSIGNED_BYTE, a WebGLUnsignedByteArray is returned, otherwise 
	 * 				a WebGLUnsignedShortArray is returned.
	 */
	@Deprecated
	WebGLArray readPixels(int x, int y, int width, int height, 
      PixelFormat format, PixelType type);
	
	/**
	 * Fills pixels with the pixel data in the specified rectangle of the frame 
	 * buffer. The data returned from readPixels must be up-to-date as of the 
	 * most recently sent drawing command.
	 *  
	 * For any pixel lying outside the frame buffer, the value read contains 0 
	 * in all channels.
	 *  
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type The type of pixels must match the type of the data to be read.
	 * 				If it is UNSIGNED_BYTE, a Uint8Array must be supplied; if it is
	 * 				UNSIGNED_SHORT_5_6_5, UNSIGNED_SHORT_4_4_4_4, or 
	 * 				UNSIGNED_SHORT_5_5_5_1, a Uint16Array must be supplied. If the 
	 * 				types do not match, an INVALID_OPERATION error is generated.
	 * @param pixels If pixels is null, an INVALID_VALUE error is generated. 
	 * 				If pixels is non-null, but is not large enough to retrieve all of 
	 * 				the pixels in the specified rectangle taking into account pixel 
	 * 				store modes, an INVALID_OPERATION value is generated.
	 */
	void readPixels(int x, int y, int width, int height, 
      PixelFormat format, PixelType type, ArrayBufferView pixels);
  /**
	 * Create and initialize a renderbuffer object's data store.
	 * 
	 * @param target Specifies the renderbuffer target.
	 * @param internalformat Specifies the color-renderable, depth-renderable, 
	 * 				or stencil-renderable format of the renderbuffer.
	 * @param width Specifies the width of the renderbuffer in pixels.
	 * @param height Specifies the height of the renderbuffer in pixels.
	 */
	void renderbufferStorage(RenderbufferTarget target, 
			RenderbufferInternalFormat internalformat, int width, int height);
  
  /**
	 * Specify multisample coverage parameters.
	 * 
	 * @param value Specify a single floating-point sample coverage value. The 
	 * 				value is clamped to the range 0 1 . The initial value is 1.0.
	 * @param invert Specify a single boolean value representing if the coverage 
	 * 				masks should be inverted.
	 */
	void sampleCoverage(float value, boolean invert);
  
  /**
	 * Define the scissor box.
	 * 
	 * @param x Specify the lower left corner of	the scissor box. Initially 0.
	 * @param y Specify the lower left corner of	the scissor box. Initially 0.
	 * @param width Specify the width of the scissor box. When a GL context is 
	 * 				first attached to a window, width and height are set to the 
	 * 				dimensions	of that	window.
	 * @param height Specify the height of the scissor box. When a GL context is 
	 * 				first attached to a window, width and height are set to the 
	 * 				dimensions	of that	window.
	 */
	void scissor(float x, float y, float width, float height);
	
	/**
	 * Replace the source code in a shader object.
	 * 
	 * @param shader Specifies the handle of the shader object whose source code 
	 * 				is to be replaced.
	 * @param source Specifies a string containing the source code to be loaded 
	 * 				into the shader.
	 */
	void shaderSource(WebGLShader shader, String source);
	
	/**
	 * Set front and back function and reference value for stencil testing.
	 * 
	 * @param func Specifies the test function.
	 * @param ref Specifies the reference value for the stencil test. ref is 
	 * 				clamped to the range 0 to 2^n - 1 , where n is the number of 
	 * 				bitplanes	in the stencil buffer. The initial value is 0.
	 * @param mask Specifies a mask that is ANDed with both the reference value 
	 * 				and the stored stencil value when the test is done. The initial 
	 * 				value is all 1's.
	 */
	void stencilFunc(StencilFunction func, int ref, int mask);
	
	/**
	 * Set front and/or back function and reference value for stencil testing.
	 * 
	 * @param face Specifies whether front and/or back stencil state is updated.
	 * @param func Specifies the test function.
	 * @param ref Specifies the reference value for the stencil test. ref is 
	 * 				clamped to the range 0 to 2^n - 1 , where n is the number of 
	 * 				bitplanes	in the stencil buffer. The initial value is 0.
	 * @param mask Specifies a mask that is ANDed with both the reference value 
	 * 				and the stored stencil value when the test is done. The initial 
	 * 				value is all 1's.
	 */
	void stencilFuncSeparate(CullFaceMode face, StencilFunction func, int ref, 
			int mask);
	
	/**
	 * Specifies a bit mask to enable and disable writing of individual bits in 
	 * the stencil planes. Initially, the mask is all 1's.
	 * 
	 * @param mask Specifies a bit mask to enable and disable writing of 
	 * 				individual bits in the stencil planes. Initially, the mask is 
	 * 				all 1's.
	 */
	void stencilMask(int mask);
	
	/**
	 * Control the front and/or back writing of individual bits in the stencil 
	 * planes.
	 * 
	 * @param face Specifies whether the front and/or back stencil writemask is 
	 * 				updated.
	 * @param mask Specifies a bit mask to enable and disable writing of 
	 * 				individual bits in the stencil planes. Initially, the mask is 
	 * 				all 1's.
	 */
	void stencilMaskSeparate(CullFaceMode face, int mask);
	
	/**
	 * Sets front and back stencil test actions.
	 * 
	 * @param fail
	 * @param zfail
	 * @param zpass
	 */
	void stencilOp(StencilOp fail, StencilOp zfail, StencilOp zpass);
	
	/**
	 * Sets front and/or back stencil test actions.
	 * 
	 * @param face
	 * @param fail
	 * @param zfail
	 * @param zpass
	 */
	void stencilOpSeparate(CullFaceMode face, StencilOp fail, StencilOp zfail, 
			StencilOp zpass);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, CanvasElement image);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image.
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, CanvasElement image, 
			boolean flipY);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image.
	 * @param asPremultipliedAlpha
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, CanvasElement image, 
			boolean flipY, boolean asPremultipliedAlpha);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, ImageData image);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image.
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, ImageData image, 
			boolean flipY);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image.
	 * @param asPremultipliedAlpha
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, ImageData image, 
			boolean flipY, boolean asPremultipliedAlpha);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, ImageElement image);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image.
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, ImageElement image, 
			boolean flipY);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image.
	 * @param asPremultipliedAlpha
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, ImageElement image, 
			boolean flipY, boolean asPremultipliedAlpha);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If the passed pixels value is null a buffer of sufficient size initialized 
	 * to 0 is passed. If an attempt is made to call this function with no 
	 * WebGLTexture bound, an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param internalformat Specifies the internal format of the texture.
	 * @param width Specifies the width of the texture subimage.
	 * @param height Specifies the height of the texture subimage.
	 * @param border Specifies the width of the border. Must be 0.
	 * @param format Specifies the format of the texel data. Must match 
	 * 				internalformat.
	 * @param type Specifies the data type of the texel data.
	 * @param pixels Specifies a pointer to the image data in memory.
	 */
	void texImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, int width, int height, int border, 
			PixelFormat format, PixelType type, ArrayBufferView pixels);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If the passed pixels value is null a buffer of sufficient size initialized 
	 * to 0 is passed. If an attempt is made to call this function with no 
	 * WebGLTexture bound, an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param internalformat Specifies the internal format of the texture.
	 * @param width Specifies the width of the texture subimage.
	 * @param height Specifies the height of the texture subimage.
	 * @param border Specifies the width of the border. Must be 0.
	 * @param format Specifies the format of the texel data. Must match 
	 * 				internalformat.
	 * @param type Specifies the data type of the texel data.
	 * @param pixels Specifies a pointer to the image data in memory.
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, int width, int height, int border, 
			PixelFormat format, PixelType type, WebGLArray pixels);
	
	/**
	 * Uploads the given element or image data to the currently bound WebGLTexture.
	 * 
	 * The source image data is conceptually first converted to the data type and format 
	 * specified by the format and type arguments, and then transferred to the OpenGL 
	 * implementation. If a packed pixel format is specified which would imply loss of 
	 * bits of precision from the image data, this loss of precision must occur. 
	 * 
	 * If the source image is an RGB or RGBA lossless image with 8 bits per channel, the 
	 * browser guarantees that the full precision of all channels is preserved. 
	 * 
	 * If the original image semantically contains an alpha channel and the 
	 * UNPACK_PREMULTIPLY_ALPHA_WEBGL pixel storage parameter is false, then the alpha 
	 * channel is guaranteed to never have been premultiplied by the RGB values, whether 
	 * those values are derived directly from the original file format or converted from 
	 * some other color format. 
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound (see above), 
	 * an INVALID_OPERATION error is generated. 
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param internalformat Specifies the internal format of the texture.
	 * @param format Specifies the format of the texel data. Must match 
	 * 				internalformat.
	 * @param type Specifies the data type of the texel data.
	 * @param pixels
	 */
	void texImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, 
			PixelFormat format, PixelType type, CanvasElement pixels);
	
	/**
	 * Uploads the given element or image data to the currently bound WebGLTexture.
	 * 
	 * The source image data is conceptually first converted to the data type and format 
	 * specified by the format and type arguments, and then transferred to the OpenGL 
	 * implementation. If a packed pixel format is specified which would imply loss of 
	 * bits of precision from the image data, this loss of precision must occur. 
	 * 
	 * If the source image is an RGB or RGBA lossless image with 8 bits per channel, the 
	 * browser guarantees that the full precision of all channels is preserved. 
	 * 
	 * If the original image semantically contains an alpha channel and the 
	 * UNPACK_PREMULTIPLY_ALPHA_WEBGL pixel storage parameter is false, then the alpha 
	 * channel is guaranteed to never have been premultiplied by the RGB values, whether 
	 * those values are derived directly from the original file format or converted from 
	 * some other color format. 
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound (see above), 
	 * an INVALID_OPERATION error is generated. 
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param internalformat Specifies the internal format of the texture.
	 * @param format Specifies the format of the texel data. Must match 
	 * 				internalformat.
	 * @param type Specifies the data type of the texel data.
	 * @param pixels
	 */
	void texImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, 
			PixelFormat format, PixelType type, ImageData pixels);
	
	/**
	 * Uploads the given element or image data to the currently bound WebGLTexture.
	 * 
	 * The source image data is conceptually first converted to the data type and format 
	 * specified by the format and type arguments, and then transferred to the OpenGL 
	 * implementation. If a packed pixel format is specified which would imply loss of 
	 * bits of precision from the image data, this loss of precision must occur. 
	 * 
	 * If the source image is an RGB or RGBA lossless image with 8 bits per channel, the 
	 * browser guarantees that the full precision of all channels is preserved. 
	 * 
	 * If the original image semantically contains an alpha channel and the 
	 * UNPACK_PREMULTIPLY_ALPHA_WEBGL pixel storage parameter is false, then the alpha 
	 * channel is guaranteed to never have been premultiplied by the RGB values, whether 
	 * those values are derived directly from the original file format or converted from 
	 * some other color format. 
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound (see above), 
	 * an INVALID_OPERATION error is generated. 
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param internalformat Specifies the internal format of the texture.
	 * @param format Specifies the format of the texel data. Must match 
	 * 				internalformat.
	 * @param type Specifies the data type of the texel data.
	 * @param pixels
	 */
	void texImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, 
			PixelFormat format, PixelType type, ImageElement pixels);
	
	/**
	 * Uploads the given element or image data to the currently bound WebGLTexture.
	 * 
	 * The source image data is conceptually first converted to the data type and format 
	 * specified by the format and type arguments, and then transferred to the OpenGL 
	 * implementation. If a packed pixel format is specified which would imply loss of 
	 * bits of precision from the image data, this loss of precision must occur. 
	 * 
	 * If the source image is an RGB or RGBA lossless image with 8 bits per channel, the 
	 * browser guarantees that the full precision of all channels is preserved. 
	 * 
	 * If the original image semantically contains an alpha channel and the 
	 * UNPACK_PREMULTIPLY_ALPHA_WEBGL pixel storage parameter is false, then the alpha 
	 * channel is guaranteed to never have been premultiplied by the RGB values, whether 
	 * those values are derived directly from the original file format or converted from 
	 * some other color format. 
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound (see above), 
	 * an INVALID_OPERATION error is generated. 
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param internalformat Specifies the internal format of the texture.
	 * @param format Specifies the format of the texel data. Must match 
	 * 				internalformat.
	 * @param type Specifies the data type of the texel data.
	 * @param pixels
	 */
	void texImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, 
			PixelFormat format, PixelType type, VideoElement pixels);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, VideoElement image);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image.
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, VideoElement image, 
			boolean flipY);
	
	/**
	 * Specify a two-dimensional texture image.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image.
	 * @param asPremultipliedAlpha
	 */
	@Deprecated
	void texImage2D(TextureTarget target, int level, VideoElement image, 
			boolean flipY, boolean asPremultipliedAlpha);
	
	/**
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 */
	void texParameter(TextureTarget target, TextureParameterName pname, 
			TextureMagFilter param);
	
	/**
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 */
	void texParameter(TextureTarget target, TextureParameterName pname, 
			TextureMinFilter param);
	
	/**
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 */
	void texParameter(TextureTarget target, TextureParameterName pname,
			TextureWrapMode param);
	
	/**
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 */
	void texParameterf(TextureTarget target, TextureParameterName pname, float param);
	
	/**
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 */
	void texParameteri(TextureTarget target, TextureParameterName pname, int param);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image 
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			CanvasElement image);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image. 
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			CanvasElement image, boolean flipY);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image. 
	 * @param asPremultipliedAlpha
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			CanvasElement image, boolean flipY, boolean asPremultipliedAlpha);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image 
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			ImageData image);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image. 
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			ImageData image, boolean flipY);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image. 
	 * @param asPremultipliedAlpha
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			ImageData image, boolean flipY, boolean asPremultipliedAlpha);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image 
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			ImageElement image);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image. 
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			ImageElement image, boolean flipY);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image. 
	 * @param asPremultipliedAlpha
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			ImageElement image, boolean flipY, boolean asPremultipliedAlpha);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 *  
	 * See texImage2D for the interpretation of the format and type arguments.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound 
	 * (see above), an INVALID_OPERATION error is generated. 
	 * 
	 * See Pixel Storage Parameters for WebGL-specific pixel storage parameters 
	 * that affect the behavior of this function.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
		  int width, int height, PixelFormat format, PixelType type, 
		  ArrayBufferView pixels);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If the passed pixels value is null a buffer of sufficient size initialized 
	 * to 0 is passed. If an attempt is made to call this function with no 
	 * WebGLTexture bound, an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param width Specifies the width of the texture subimage.
	 * @param height Specifies the height of the texture subimage.
	 * @param format Specifies the format of the texel data. 
	 * @param type Specifies the data type of the texel data.
	 * @param pixels Specifies a pointer to the image data in memory.
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			int width, int height, PixelFormat format, PixelType type, 
			WebGLArray pixels);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * Updates a sub-rectangle of the currently bound WebGLTexture with the 
	 * contents of the given element or image data.
	 * 
	 * See texImage2D for the interpretation of the format and type arguments.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound 
	 * (see above), an INVALID_OPERATION error is generated. 
	 * 
	 * See Pixel Storage Parameters for WebGL-specific pixel storage parameters 
	 * that affect the behavior of this function.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param format
	 * @param type
	 * @param canvas
	 */
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
		  PixelFormat format, PixelType type, CanvasElement canvas);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * Updates a sub-rectangle of the currently bound WebGLTexture with the 
	 * contents of the given element or image data.
	 * 
	 * See texImage2D for the interpretation of the format and type arguments.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound 
	 * (see above), an INVALID_OPERATION error is generated. 
	 * 
	 * See Pixel Storage Parameters for WebGL-specific pixel storage parameters 
	 * that affect the behavior of this function.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
		  PixelFormat format, PixelType type, ImageData pixels);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * Updates a sub-rectangle of the currently bound WebGLTexture with the 
	 * contents of the given element or image data.
	 * 
	 * See texImage2D for the interpretation of the format and type arguments.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound 
	 * (see above), an INVALID_OPERATION error is generated. 
	 * 
	 * See Pixel Storage Parameters for WebGL-specific pixel storage parameters 
	 * that affect the behavior of this function.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param format
	 * @param type
	 * @param image
	 */
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
		  PixelFormat format, PixelType type, ImageElement image);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * Updates a sub-rectangle of the currently bound WebGLTexture with the 
	 * contents of the given element or image data.
	 * 
	 * See texImage2D for the interpretation of the format and type arguments.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound 
	 * (see above), an INVALID_OPERATION error is generated. 
	 * 
	 * See Pixel Storage Parameters for WebGL-specific pixel storage parameters 
	 * that affect the behavior of this function.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param format
	 * @param type
	 * @param video
	 */
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
		  PixelFormat format, PixelType type, VideoElement video);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image 
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			VideoElement image);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image. 
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			VideoElement image, boolean flipY);
	
	/**
	 * Specify a two-dimensional texture subimage.
	 * 
	 * If an attempt is made to call this function with no WebGLTexture bound, 
	 * an INVALID_OPERATION error is raised.
	 * 
	 * @param target Specifies the target texture.
	 * @param level Specifies the level-of-detail number. Level 0 is the base 
	 * 				image level. Level n is the nth mipmap reduction image.
	 * @param xoffset Specifies a texel offset in the x direction within the 
	 * 				texture array.
	 * @param yoffset Specifies a texel offset in the y direction within the 
	 * 				texture array.
	 * @param image
	 * @param flipY If false, the upper left pixel of the passed image is sent 
	 * 				first and then transfer proceeds across and then down the image.
	 * 				If true, the lower left pixel of the passed image is sent first and 
	 * 				then transfer proceeds across and then up the image. 
	 * @param asPremultipliedAlpha
	 */
	@Deprecated
	void texSubImage2D(TextureTarget target, int level, int xoffset, int yoffset, 
			VideoElement image, boolean flipY, boolean asPremultipliedAlpha);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform(WebGLUniformLocation location, Tuple2f v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform(WebGLUniformLocation location, Tuple2i v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform(WebGLUniformLocation location, Tuple3f v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform(WebGLUniformLocation location, Tuple3i v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform(WebGLUniformLocation location, Tuple4f v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform(WebGLUniformLocation location, Tuple4i v);

	/**
	 * Sets the specified uniform to the values provided. 
	 * 
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param x
	 */
	void uniform1f(WebGLUniformLocation location, float x);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform1fv(WebGLUniformLocation location, float[] v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform1fv(WebGLUniformLocation location, Float32Array v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform1fv(WebGLUniformLocation location, JsArrayNumber v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	@Deprecated
	void uniform1fv(WebGLUniformLocation location, WebGLFloatArray v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param x
	 */
	void uniform1i(WebGLUniformLocation location, int x);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform1iv(WebGLUniformLocation location, int[] v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform1iv(WebGLUniformLocation location, Int32Array v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform1iv(WebGLUniformLocation location, JsArrayInteger v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	@Deprecated
	void uniform1iv(WebGLUniformLocation location, WebGLIntArray v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param x
	 * @param y
	 */
	void uniform2f(WebGLUniformLocation location, float x, float y);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform2fv(WebGLUniformLocation location, float[] v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform2fv(WebGLUniformLocation location, Float32Array v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform2fv(WebGLUniformLocation location, JsArrayNumber v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	@Deprecated
	void uniform2fv(WebGLUniformLocation location, WebGLFloatArray v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param x
	 * @param y
	 */
	void uniform2i(WebGLUniformLocation location, int x, int y);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform2iv(WebGLUniformLocation location, int[] v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform2iv(WebGLUniformLocation location, Int32Array v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform2iv(WebGLUniformLocation location, JsArrayInteger v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	@Deprecated
	void uniform2iv(WebGLUniformLocation location, WebGLIntArray v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param x
	 * @param y
	 * @param z
	 */
	void uniform3f(WebGLUniformLocation location, float x, float y, float z);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform3fv(WebGLUniformLocation location, float[] v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform3fv(WebGLUniformLocation location, Float32Array v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform3fv(WebGLUniformLocation location, JsArrayNumber v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	@Deprecated
	void uniform3fv(WebGLUniformLocation location, WebGLFloatArray v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param x
	 * @param y
	 * @param z
	 */
	void uniform3i(WebGLUniformLocation location, int x, int y, int z);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform3iv(WebGLUniformLocation location, Float32Array v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform3iv(WebGLUniformLocation location, int[] v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform3iv(WebGLUniformLocation location, JsArrayInteger v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	@Deprecated
	void uniform3iv(WebGLUniformLocation location, WebGLIntArray v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void uniform4f(WebGLUniformLocation location, float x, float y, float z,
			float w);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform4fv(WebGLUniformLocation location, float[] v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform4fv(WebGLUniformLocation location, Float32Array v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform4fv(WebGLUniformLocation location, JsArrayNumber v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	@Deprecated
	void uniform4fv(WebGLUniformLocation location, WebGLFloatArray v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void uniform4i(WebGLUniformLocation location, int x, int y, int z, int w);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform4iv(WebGLUniformLocation location, int[] v);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform4iv(WebGLUniformLocation location, Int32Array v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	void uniform4iv(WebGLUniformLocation location, JsArrayInteger v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param v
	 */
	@Deprecated
	void uniform4iv(WebGLUniformLocation location, WebGLIntArray v);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose false to set the matrix as it is (row major order). 
	 * 				true to set the matrix as row major order (OpenGL	standard)
	 * @param value
	 */
	void uniformMatrix(WebGLUniformLocation location, boolean transpose,
			Matrix3f value);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose false to set the matrix as it is (row major order).
	 * 				true to set the matrix as column major order (OpenGL standard)
	 * @param value
	 */
	void uniformMatrix(WebGLUniformLocation location, boolean transpose,
			Matrix4f value);
	
	/**
	 * Sets the specified uniform to the values provided.
	 * {@link Matrix3f} is stored in row-major order, but this method will 
	 * set it as column major order.
	 * This is equivalent to calling uniformMatrix3f(location, true, value);
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param value
	 */
	void uniformMatrix(WebGLUniformLocation location, Matrix3f value);
	
	/**
	 * Sets the specified uniform to the values provided.
	 * {@link Matrix4f} is stored in row-major order, but this method will 
	 * set it as column major order.
	 * This is equivalent to calling uniformMatrix4f(location, true, value);
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param value
	 */
	void uniformMatrix(WebGLUniformLocation location, Matrix4f value);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	void uniformMatrix2fv(WebGLUniformLocation location, boolean transpose,
			float[] value);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	void uniformMatrix2fv(WebGLUniformLocation location, boolean transpose,
			Float32Array value);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	void uniformMatrix2fv(WebGLUniformLocation location, boolean transpose,
			JsArrayNumber value);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	@Deprecated
	void uniformMatrix2fv(WebGLUniformLocation location, boolean transpose,
			WebGLFloatArray value);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	void uniformMatrix3fv(WebGLUniformLocation location, boolean transpose,
			float[] value);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	void uniformMatrix3fv(WebGLUniformLocation location, boolean transpose,
			Float32Array value);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	void uniformMatrix3fv(WebGLUniformLocation location, boolean transpose,
			JsArrayNumber value);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	@Deprecated
	void uniformMatrix3fv(WebGLUniformLocation location, boolean transpose,
			WebGLFloatArray value);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	void uniformMatrix4fv(WebGLUniformLocation location, boolean transpose,
			float[] value);

	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	void uniformMatrix4fv(WebGLUniformLocation location, boolean transpose,
			Float32Array value);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
		void uniformMatrix4fv(WebGLUniformLocation location, boolean transpose,
				JsArrayNumber value);
	
	/**
	 * Sets the specified uniform to the values provided.
	 *  
	 * @param location must have been obtained from the currently used program 
	 * 				via an earlier call to getUniformLocation, or an INVALID_VALUE 
	 * 				error will be raised.
	 * @param transpose
	 * @param value
	 */
	@Deprecated
	void uniformMatrix4fv(WebGLUniformLocation location, boolean transpose,
			WebGLFloatArray value);

	/**
	 * Install a program object as part of current rendering state.
	 * 
	 * @param program Specifies the handle of the program object whose 
	 * 				executables are to be used as part of current rendering state.
	 */
	void useProgram(WebGLProgram program);
	
	/**
	 * Validate a program object.
	 * 
	 * @param program Specifies the handle of the program object to be validated.
	 */
	void validateProgram(WebGLProgram program);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib(int indx, Tuple2f values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib(int indx, Tuple3f values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib(int indx, Tuple4f values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param x Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib1f(int indx, float x);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib1fv(int indx, float[] values);

	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib1fv(int indx, Float32Array values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib1fv(int indx, JsArrayNumber values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	@Deprecated
	void vertexAttrib1fv(int indx, WebGLFloatArray values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param x Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 * @param y Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib2f(int indx, float x, float y);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib2fv(int indx, float[] values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib2fv(int indx, Float32Array values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib2fv(int indx, JsArrayNumber values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	@Deprecated
	void vertexAttrib2fv(int indx, WebGLFloatArray values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param x Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 * @param y Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 * @param z Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib3f(int indx, float x, float y, float z);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib3fv(int indx, float[] values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib3fv(int indx, Float32Array values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib3fv(int indx, JsArrayNumber values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	@Deprecated
	void vertexAttrib3fv(int indx, WebGLFloatArray values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param x Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 * @param y Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 * @param z Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 * @param w Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib4f(int indx, float x, float y, float z, float w);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib4fv(int indx, float[] values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib4fv(int indx, Float32Array values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	void vertexAttrib4fv(int indx, JsArrayNumber values);
	
	/**
	 * Specifies the value of a generic vertex attribute.
	 * 
	 * @param indx Specifies the index of the generic vertex attribute to 
	 * 				be modified.
	 * @param values Specifies the new values to be used for the specified vertex 
	 * 				attribute.
	 */
	@Deprecated
	void vertexAttrib4fv(int indx, WebGLFloatArray values);
	
	/**
   * Assign the currently bound WebGLBuffer object to the passed vertex attrib 
   * index. Size is number of components per attribute. Stride and offset are 
   * in units of bytes. Passed stride and offset must be appropriate for the 
   * passed type and size or an INVALID_VALUE error will be raised.
   * 
   * @param indx Specifies the index of the generic vertex attribute to be 
   * 				modified.
   * @param size Specifies the number of components per generic vertex 
   * 				attribute. Must be 1, 2, 3, or 4. The initial value is 4.
   * @param type Specifies the data type of each component in the array.
   * @param normalized Specifies whether fixed-point data values should be 
   * 				normalized true or converted directly as fixed-point values 
   * 				false when they are accessed.
   * @param stride Specifies the byte offset between consecutive generic vertex
   * 				attributes. If stride is 0, the generic vertex attributes are 
   * 				understood to be tightly packed in the array. The initial value 
   * 				is 0.
   * @param offset Specifies a pointer to the first component of the first 
   * 				generic vertex attribute in the array. The initial value is 0.
   */
  void vertexAttribPointer(int indx, int size, DataType type, 
  		boolean normalized, int stride, int offset);
	
	/**
	 * Set the viewport.
	 * 
	 * @param x Specify the lower left corner of the viewport rectangle, 
	 * 				in pixels. The initial value is 0.
	 * @param y Specify the lower left corner of the viewport rectangle, 
	 * 				in pixels. The initial value is 0.
	 * @param width Specify the width of the viewport. When a GL context is 
	 * 				first attached to a window, width and height are set to the 
	 * 				dimensions of that window.
	 * @param height Specify the height of the viewport. When a GL context is 
	 * 				first attached to a window, width and height are set to the 
	 * 				dimensions of that window.
	 */
	void viewport(float x, float y, float width, float height);
}
