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

import static gwt.g3d.client.gl2.array.JsArrayUtils.toJsArrayInteger;
import static gwt.g3d.client.gl2.array.JsArrayUtils.toJsArrayNumber;
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
import gwt.g3d.client.math.MatrixUtils;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple2f;
import javax.vecmath.Tuple2i;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple3i;
import javax.vecmath.Tuple4f;
import javax.vecmath.Tuple4i;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.ImageElement;

/**
 * Default implementation of WebGL context.
 * 
 * @author hao1300@gmail.com
 */
@SuppressWarnings("deprecation")
public final class GL2Impl extends JavaScriptObject implements GL2 {

	public static GL2Impl as(JavaScriptObject jsContext) {
		return jsContext.cast();
	}

	protected GL2Impl() {
	}
	
	@Override
	public void activeTexture(TextureUnit texture) {
		activeTextureImpl(texture.getValue());
	}
	
	@Override
	public native void attachShader(WebGLProgram program, WebGLShader shader) /*-{
		this.attachShader(program, shader);		
	}-*/;
	
	@Override
	public native void bindAttribLocation(WebGLProgram program, int index, 
			String name) /*-{
		this.bindAttribLocation(program, index, name);
	}-*/;

	@Override
	public void bindBuffer(BufferTarget target, WebGLBuffer buffer) {
		bindBufferImpl(target.getValue(), buffer);
	}

	@Override
	public void bindFramebuffer(FramebufferTarget target, WebGLFramebuffer buffer) {
		bindFramebufferImpl(target.getValue(), buffer);
	}

	@Override
	public void bindRenderbuffer(RenderbufferTarget target,
			WebGLRenderbuffer buffer) {
		bindRenderbufferImpl(target.getValue(), buffer);
	}

	@Override
	public void bindTexture(TextureTarget target, WebGLTexture texture) {
		bindTextureImpl(target.getValue(), texture);		
	}

	@Override
	public native void blendColor(float red, float green, float blue,
			float alpha) /*-{
		this.blendColor(red, green, blue, alpha);
	}-*/;

	@Override
	public void blendEquation(BlendEquationMode mode) {
		blendEquationImpl(mode.getValue());
	}

	@Override
	public void blendEquationSeparate(BlendEquationMode modeRGB,
			BlendEquationMode modeAlpha) {
		blendEquationSeparateImpl(modeRGB.getValue(), modeAlpha.getValue());
	}

	@Override
	public void blendFunc(BlendingFactorSrc sfactor, BlendingFactorDest dfactor) {
		blendFuncImpl(sfactor.getValue(), dfactor.getValue());
	}

	@Override
	public void blendFuncSeparate(BlendingFactorSrc srcRGB,
			BlendingFactorDest dstRGB, BlendingFactorSrc srcAlpha,
			BlendingFactorDest dstAlpha) {
		blendFuncSeparateImpl(srcRGB.getValue(), dstRGB.getValue(), srcAlpha
				.getValue(), dstAlpha.getValue());
	}

	@Override
	public void bufferData(BufferTarget target, ArrayBuffer data, BufferUsage usage) {
		bufferDataImpl(target.getValue(), data, usage.getValue());
	}

	@Override
	public void bufferData(BufferTarget target, ArrayBufferView data,
			BufferUsage usage) {
		bufferDataImpl(target.getValue(), data, usage.getValue());
	}

	@Override
	public void bufferData(BufferTarget target, int size, BufferUsage usage) {
		bufferDataImpl(target.getValue(), size, usage.getValue());
	}
	
	@Deprecated
	@Override
	public void bufferData(BufferTarget target, WebGLArray data, BufferUsage usage) {
		bufferDataImpl(target.getValue(), data, usage.getValue());
	}

	@Deprecated
	@Override
	public void bufferData(BufferTarget target, WebGLArrayBuffer data,
			BufferUsage usage) {
		bufferDataImpl(target.getValue(), data, usage.getValue());
	}

	@Override
	public void bufferSubData(BufferTarget target, int offset, ArrayBuffer data) {
		this.bufferSubDataImpl(target.getValue(), offset, data);
	}

	@Override
	public void bufferSubData(BufferTarget target, int offset,
			ArrayBufferView data) {
		this.bufferSubDataImpl(target.getValue(), offset, data);
	}

	@Deprecated
	@Override
	public void bufferSubData(BufferTarget target, int offset, WebGLArray data) {
		bufferSubDataImpl(target.getValue(), offset, data);
	}

	@Deprecated
	@Override
	public void bufferSubData(BufferTarget target, int offset, WebGLArrayBuffer data) {
		bufferSubDataImpl(target.getValue(), offset, data);
	}

	@Override
	public FramebufferErrorCode checkFramebufferStatus(FramebufferTarget target) {
		return FramebufferErrorCode.parseErrorCode(checkFramebufferStatusImpl(
				target.getValue()));
	}

	@Override
	public void clear(ClearBufferMask mask) {
		clearImpl(mask.getValue());
	}

	@Override
	public void clear(ClearBufferMask mask1, ClearBufferMask mask2) {
		clearImpl(mask1.getValue() | mask2.getValue());
	}

	@Override
	public void clear(ClearBufferMask mask1, ClearBufferMask mask2,
			ClearBufferMask mask3) {
		clearImpl(mask1.getValue() | mask2.getValue() | mask3.getValue());
	}

	@Override
	public native void clearColor(float red, float green, float blue,
			float alpha) /*-{
		this.clearColor(red, green, blue, alpha);
	}-*/;

	@Override
	public native void clearDepth(float depth) /*-{
		this.clearDepth(depth);
	}-*/;

	@Override
	public native void clearStencil(int s) /*-{
		this.clearStencil(s);
	}-*/;

	@Override
	public native void colorMask(boolean red, boolean green, boolean blue,
			boolean alpha) /*-{
		this.colorMask(red, green, blue, alpha);
	}-*/;

	@Override
	public native void compileShader(WebGLShader shader) /*-{
		this.compileShader(shader);		
	}-*/;

	@Override
	public void copyTexImage2D(TextureTarget target, int level,
			PixelInternalFormat internalformat, int x, int y, int width, int height,
			int border) {
		copyTexImage2DImpl(target.getValue(), level, internalformat.getValue(), 
				x, y, width, height, border);
	}

	@Override
	public void copyTexSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, int x, int y, int width, int height) {
		copyTexSubImage2DImpl(target.getValue(), level, xoffset, yoffset, x, y, 
				width, height);
	}

	@Override
	public native WebGLBuffer createBuffer() /*-{
		return this.createBuffer();
	}-*/;

	@Override
	public native WebGLFramebuffer createFramebuffer() /*-{
		return this.createFramebuffer();
	}-*/;

	@Override
	public native WebGLProgram createProgram() /*-{
		return this.createProgram();
	}-*/;

	@Override
	public native WebGLRenderbuffer createRenderbuffer() /*-{
		return this.createRenderbuffer();
	}-*/;

	@Override
	public WebGLShader createShader(ShaderType type) {
		return createShaderImpl(type.getValue());
	}

	@Override
	public native WebGLTexture createTexture() /*-{
		return this.createTexture();
	}-*/;

	@Override
	public void cullFace(CullFaceMode mode) {
		cullFaceImpl(mode.getValue());
	}

	@Override
	public native void deleteBuffer(WebGLBuffer buffer)/*-{
		this.deleteBuffer(buffer);
	}-*/;

	@Override
	public native void deleteFramebuffer(WebGLFramebuffer buffer) /*-{
		this.deleteFramebuffer(buffer);
	}-*/;

	@Override
	public native void deleteProgram(WebGLProgram program) /*-{
		this.deleteProgram(program);		
	}-*/;

	@Override
	public native void deleteRenderbuffer(WebGLRenderbuffer buffer)/*-{
		this.deleteRenderbuffer(buffer);
	}-*/;

	@Override
	public native void deleteShader(WebGLShader shader) /*-{
		this.deleteShader(shader);		
	}-*/;

	@Override
	public native void deleteTexture(WebGLTexture texture) /*-{
		this.deleteTexture(texture);
	}-*/;

	@Override
	public void depthFunc(DepthFunction func) {
		depthFuncImpl(func.getValue());
	}

	@Override
	public native void depthMask(boolean flag) /*-{
		this.depthMask(flag);
	}-*/;

	@Override
	public native void depthRange(float zNear, float zFar) /*-{
		this.depthRange(zNear, zFar);
	}-*/;

	@Override
	public native void detachShader(WebGLProgram program, WebGLShader shader) /*-{
		this.detachShader(program, shader);		
	}-*/;

	@Override
	public void disable(EnableCap cap) {
		disableImpl(cap.getValue());
	}

	@Override
	public native void disableVertexAttribArray(int index) /*-{
		this.disableVertexAttribArray(index);
	}-*/;

	@Override
	public void drawArrays(BeginMode mode, int first, int count) {
		drawArraysImpl(mode.getValue(), first, count);
	}

	@Override
	public void drawElements(BeginMode mode, int count, DrawElementsType type,
			int offset) {
		drawElementsImpl(mode.getValue(), count, type.getValue(), offset);
	}

	@Override
	public void enable(EnableCap cap) {
		enableImpl(cap.getValue());
	}

	@Override
	public native void enableVertexAttribArray(int index) /*-{
		this.enableVertexAttribArray(index);
	}-*/;

	@Override
	public native void finish() /*-{
		this.finish();
	}-*/;

	@Override
	public native void flush() /*-{
		this.flush();
	}-*/;

	@Override
	public void framebufferRenderbuffer(FramebufferTarget target,
			FramebufferSlot attachment, RenderbufferTarget renderbuffertarget,
			WebGLRenderbuffer renderbuffer) {
		framebufferRenderbufferImpl(target.getValue(), attachment.getValue(),
				renderbuffertarget.getValue(), renderbuffer);
	}

	@Override
	public void framebufferTexture2D(FramebufferTarget target,
			FramebufferSlot attachment, TextureTarget textarget,
			WebGLTexture texture, int level) {
		framebufferTexture2DImpl(target.getValue(), attachment.getValue(),
				textarget.getValue(), texture, level);
	}

	@Override
	public void frontFace(FrontFaceDirection mode) {
		frontFaceImpl(mode.getValue());
	}

	@Override
	public void generateMipmap(TextureTarget target) {
		generateMipmapImpl(target.getValue());
	}

	@Override
	public native final WebGLActiveInfo getActiveAttrib(WebGLProgram program, 
			int index) /*-{
		return this.getActiveAttrib(program, index);
	}-*/;
	
	@Override
	public native final WebGLActiveInfo getActiveUniform(WebGLProgram program, 
			int index) /*-{
		return this.getActiveUniform(program, index);
	}-*/;

	@Override
	public final WebGLShader[] getAttachedShaders(
			WebGLProgram program) {
		JsArray<WebGLShader> shaders = this.getAttachedShadersImpl(program);
		WebGLShader[] outShaders = new WebGLShader[shaders.length()];
		for (int i = 0; i < outShaders.length; i++) {
			outShaders[i] = shaders.get(i);
		}
		return outShaders;
	}	
	
	@Override
	public native int getAttribLocation(WebGLProgram program, String name) /*-{
		return this.getAttribLocation(program, name);
	}-*/;

	@Override
	public int getBufferParameteri(BufferTarget target, BufferParameterName pname) {
		return getBufferParameteriImpl(target.getValue(), pname.getValue());
	}
	
	@Override
	public native CanvasElement getCanvas() /*-{
		return this.canvas;
	}-*/;

	@Override
	public WebGLContextAttributes getContextAttributes() {
		return new WebGLContextAttributes(getContextAttributesImpl());
	}
	
	@Override
	public ErrorCode getError() {
		return ErrorCode.parseErrorCode(getErrorImpl());
	}

	@Override
	public native JavaScriptObject getExtension(String name) /*-{
		return this.getExtension(name);
	}-*/;

	@Override
	public <T extends WebGLObject> T getFramebufferAttachmentParameter(
			FramebufferTarget target, FramebufferSlot attachment,
			FramebufferParameterName pname) {
		return this.<T>getFramebufferAttachmentParameterImpl(target.getValue(),
				attachment.getValue(), pname.getValue());
	}

	@Override
	public int getFramebufferAttachmentParameteri(FramebufferTarget target,
			FramebufferSlot attachment, FramebufferParameterName pname) {
		return getFramebufferAttachmentParameteriImpl(target.getValue(),
				attachment.getValue(), pname.getValue());
	}

	@Override
	public <T extends JavaScriptObject> T getParameter(GetPName pname) {
		return this.<T>getParameterImpl(pname.getValue());
	}
	
	@Override
	public boolean getParameterb(GetPName pname) {
		return getParameterbImpl(pname.getValue());
	}
	
	@Override
	public float getParameterf(GetPName pname) {
		return getParameterfImpl(pname.getValue());
	}

	@Override
	public int getParameteri(GetPName pname) {
		return getParameteriImpl(pname.getValue());
	}

	@Override
	public native final String getProgramInfoLog(WebGLProgram program) /*-{
		return this.getProgramInfoLog(program);
	}-*/;

	@Override
	public boolean getProgramParameterb(WebGLProgram program,
			ProgramParameter pname) {
		return getProgramParameterbImpl(program, pname.getValue());
	}
	
	@Override
	public int getProgramParameteri(WebGLProgram program, ProgramParameter pname) {
		return getProgramParameteriImpl(program, pname.getValue());
	}

	@Override
	public int getRenderbufferParameteri(RenderbufferTarget target,
			RenderbufferParameterName pname) {
		return getRenderbufferParameteriImpl(target.getValue(), pname.getValue());
	}

	@Override
	public native final String getShaderInfoLog(WebGLShader shader)/*-{
		return this.getShaderInfoLog(shader);
	}-*/;

	@Override
	public boolean getShaderParameterb(WebGLShader shader, ShaderParameter pname) {
		return getShaderParameterbImpl(shader, pname.getValue());
	}
	
	@Override
	public int getShaderParameteri(WebGLShader shader, ShaderParameter pname) {
		return getShaderParameteriImpl(shader, pname.getValue());
	}

	@Override
	public native final String getShaderSource(WebGLShader shader) /*-{
		return this.getShaderSource(shader);
	}-*/;

	@Override
	public String getString(StringName name) {
		return getStringImpl(name.getValue());
	}
	
	@Override
	public String[] getSupportedExtensions() {
		JsArrayString supportedExts = getSupportedExtensionsImpl();
		String[] outSupportedExts = new String[supportedExts.length()];
		for (int i = 0; i < outSupportedExts.length; i++) {
			outSupportedExts[i] = supportedExts.get(i);
		}
		return outSupportedExts;
	}
	
	@Override
	public native <T extends TypeArray> T getUniform(WebGLProgram program,
			WebGLUniformLocation location) /*-{
		return this.getUniform(program, location);
	}-*/;
	
	@Override
	public native boolean getUniformb(WebGLProgram program, 
			WebGLUniformLocation location) /*-{
		return this.getUniform(program, location);
	}-*/;
	
	@Override
	public native float getUniformf(WebGLProgram program, 
			WebGLUniformLocation location) /*-{
		return this.getUniform(program, location);
	}-*/;

	@Override
	public native int getUniformi(WebGLProgram program, 
			WebGLUniformLocation location) /*-{
		return this.getUniform(program, location);
	}-*/;

	@Override
	public native WebGLUniformLocation getUniformLocation(WebGLProgram program,
			String name) /*-{
		return this.getUniformLocation(program, name);
	}-*/;

	@Override
	public <T extends JavaScriptObject> T getVertexAttrib(int index,
			VertexAttribParameter pname) {
		return this.<T>getVertexAttribImpl(index, pname.getValue());
	}
	
	@Override
	public boolean getVertexAttribb(int index, VertexAttribParameter pname) {
		return this.getVertexAttribbImpl(index, pname.getValue());
	}

	@Override
	public int getVertexAttribi(int index, VertexAttribParameter pname) {
		return this.getVertexAttribiImpl(index, pname.getValue());
	}

	@Override
	public void hint(HintTarget target, HintMode mode) {
		hintImpl(target.getValue(), mode.getValue());
	}

	@Override
	public native boolean isBuffer(WebGLObject buffer) /*-{
		return this.isBuffer(buffer);
	}-*/;
	
	@Override
	public native boolean isContextLost() /*-{
		return this.isContextLost();
	}-*/;
	
	@Override
	public boolean isEnabled(EnableCap cap) {
		return isEnabledImpl(cap.getValue());
	}

	@Override
	public native boolean isFramebuffer(WebGLObject buffer) /*-{
		return this.isFramebuffer(buffer);
	}-*/;
	
	@Override
	public native boolean isProgram(WebGLObject program) /*-{
		return this.isProgram(program);
	}-*/;

	@Override
	public native boolean isRenderbuffer(WebGLObject buffer) /*-{
		return this.isRenderbuffer(buffer);
	}-*/;
	
	@Override
	public native boolean isShader(WebGLObject shader) /*-{
		return this.isShader(shader);
	}-*/;
	
	@Override
	public native boolean isTexture(WebGLObject buffer) /*-{
		return this.isTexture(buffer);
	}-*/;
	
	@Override
	public native void lineWidth(float width) /*-{
		this.lineWidth(width);
	}-*/;

	@Override
	public native void linkProgram(WebGLProgram program) /*-{
		this.linkProgram(program);		
	}-*/;
	
	@Override
	public void pixelStorei(PixelStoreParameter pname, int param) {
		pixelStoreiImpl(pname.getValue(), param);
	}

	@Override
	public native void polygonOffset(float factor, float units) /*-{
		this.polygonOffset(factor, units);
	}-*/;
	
	@Deprecated
	@Override
	public WebGLArray readPixels(int x, int y, int width, int height,
			PixelFormat format, PixelType type) {
		return readPixelsImpl(x, y, width, height, format.getValue(), type.getValue());
	}

	@Override
	public void readPixels(int x, int y, int width, int height,
			PixelFormat format, PixelType type, ArrayBufferView pixels) {
		this.readPixelsImpl(x, y, width, height, format.getValue(), type.getValue(),
				pixels);
	}

	@Override
	public void renderbufferStorage(RenderbufferTarget target,
			RenderbufferInternalFormat internalformat, int width, int height) {
		renderbufferStorageImpl(target.getValue(), internalformat.getValue(), 
				width, height);
	}
	
	@Override
	public native void sampleCoverage(float value, boolean invert) /*-{
		this.sampleCoverage(value, invert);
	}-*/;

	@Override
	public native void scissor(float x, float y, float width, float height) /*-{
		this.scissor(x, y, width, height);
	}-*/;

	@Override
	public native void shaderSource(WebGLShader shader, String source) /*-{
		this.shaderSource(shader, source);		
	}-*/;

	@Override
	public void stencilFunc(StencilFunction func, int ref, int mask) {
		stencilFuncImpl(func.getValue(), ref, mask);
	}

	@Override
	public void stencilFuncSeparate(CullFaceMode face, StencilFunction func,
			int ref, int mask) {
		stencilFuncSeparateImpl(face.getValue(), func.getValue(), ref, mask);
	}

	@Override
	public native void stencilMask(int mask) /*-{
		this.stencilMask(mask);
	}-*/;

	@Override
	public void stencilMaskSeparate(CullFaceMode face, int mask) {
		stencilMaskSeparateImpl(face.getValue(), mask);
	}

	@Override
	public void stencilOp(StencilOp fail, StencilOp zfail, StencilOp zpass) {
		stencilOpImpl(fail.getValue(), zfail.getValue(), zpass.getValue());
	}

	@Override
	public void stencilOpSeparate(CullFaceMode face, StencilOp fail,
			StencilOp zfail, StencilOp zpass) {
		stencilOpSeparateImpl(face.getValue(), fail.getValue(), zfail.getValue(),
				zpass.getValue());
	}

	@Override
	public void texImage2D(TextureTarget target, int level, CanvasElement image) {
		texImage2DImpl(target.getValue(), level, image);
	}

	@Override
	public void texImage2D(TextureTarget target, int level, CanvasElement image,
			boolean flipY) {
		texImage2DImpl(target.getValue(), level, image, flipY);
	}
	
	@Override
	public void texImage2D(TextureTarget target, int level,
			CanvasElement image, boolean flipY, boolean asPremultipliedAlpha) {
		texImage2DImpl(target.getValue(), level, image, flipY, asPremultipliedAlpha);
	}

	@Override
	public void texImage2D(TextureTarget target, int level, ImageData image) {
		texImage2DImpl(target.getValue(), level, image);
	}

	@Override
	public void texImage2D(TextureTarget target, int level, ImageData image,
			boolean flipY) {
		texImage2DImpl(target.getValue(), level, image, flipY);
	}
	
	@Override
	public void texImage2D(TextureTarget target, int level, ImageData image,
			boolean flipY, boolean asPremultipliedAlpha) {
		texImage2DImpl(target.getValue(), level, image, flipY, asPremultipliedAlpha);		
	}

	@Override
	public void texImage2D(TextureTarget target, int level, ImageElement image) {
		texImage2DImpl(target.getValue(), level, image);
	}

	@Override
	public void texImage2D(TextureTarget target, int level, ImageElement image,
			boolean flipY) {
		texImage2DImpl(target.getValue(), level, image, flipY);
	}
	
	@Override
	public void texImage2D(TextureTarget target, int level,
			ImageElement image, boolean flipY, boolean asPremultipliedAlpha) {
		texImage2DImpl(target.getValue(), level, image, flipY, asPremultipliedAlpha);
	}
	
	@Override
	public void texImage2D(TextureTarget target, int level,
			PixelInternalFormat internalformat, int width, int height, int border,
			PixelFormat format, PixelType type, ArrayBufferView pixels) {
		this.texImage2DImpl(target.getValue(), level, internalformat.getValue(), 
				width, height, border, format.getValue(), type.getValue(), pixels);
	}
	
	@Deprecated
	@Override
	public void texImage2D(TextureTarget target, int level,
			PixelInternalFormat internalformat, int width, int height, int border,
			PixelFormat format, PixelType type, WebGLArray pixels) {
		texImage2DImpl(target.getValue(), level, internalformat.getValue(), 
				width, height, border, format.getValue(), type.getValue(), pixels);		
	}
	
	@Override
	public void texImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, PixelFormat format, 
			PixelType type, CanvasElement pixels) {
		texImage2DImpl(target.getValue(), level, internalformat.getValue(), 
				format.getValue(), type.getValue(), pixels);
	}

	@Override
	public void texImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, PixelFormat format, 
			PixelType type, ImageData pixels) {
		texImage2DImpl(target.getValue(), level, internalformat.getValue(), 
				format.getValue(), type.getValue(), pixels);
	}
	
	@Override
	public void texImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, PixelFormat format, 
			PixelType type, ImageElement pixels) {
		texImage2DImpl(target.getValue(), level, internalformat.getValue(), 
				format.getValue(), type.getValue(), pixels);
	}

	@Override
	public void texImage2D(TextureTarget target, int level, 
			PixelInternalFormat internalformat, PixelFormat format, 
			PixelType type, VideoElement pixels) {
		texImage2DImpl(target.getValue(), level, internalformat.getValue(), 
				format.getValue(), type.getValue(), pixels);
	}

	@Override
	public void texImage2D(TextureTarget target, int level, VideoElement image) {
		texImage2DImpl(target.getValue(), level, image);
	}
	
	@Override
	public void texImage2D(TextureTarget target, int level, VideoElement image,
			boolean flipY) {
		texImage2DImpl(target.getValue(), level, image, flipY);
	}

	@Override
	public void texImage2D(TextureTarget target, int level,
			VideoElement image, boolean flipY, boolean asPremultipliedAlpha) {
		texImage2DImpl(target.getValue(), level, image, flipY, asPremultipliedAlpha);
	}

	@Override
	public void texParameter(TextureTarget target, TextureParameterName pname,
			TextureMagFilter param) {
		texParameteriImpl(target.getValue(), pname.getValue(), param.getValue());
	}
	
	@Override
	public void texParameter(TextureTarget target, TextureParameterName pname,
			TextureMinFilter param) {
		texParameteriImpl(target.getValue(), pname.getValue(), param.getValue());
	}
	
	@Override
	public void texParameter(TextureTarget target, TextureParameterName pname,
			TextureWrapMode param) {
		texParameteriImpl(target.getValue(), pname.getValue(), param.getValue());		
	}
	
	@Override
	public void texParameterf(TextureTarget target, TextureParameterName pname,
			float param) {
		texParameterfImpl(target.getValue(), pname.getValue(), param);
	}
	
	@Override
	public void texParameteri(TextureTarget target, TextureParameterName pname,
			int param) {
		texParameteriImpl(target.getValue(), pname.getValue(), param);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, CanvasElement image) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, CanvasElement image, boolean flipY) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image, flipY);
	}
	
	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, CanvasElement image, boolean flipY,
			boolean asPremultipliedAlpha) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image, flipY,
				asPremultipliedAlpha);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, ImageData image) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, ImageData image, boolean flipY) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image, flipY);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, ImageData image, boolean flipY, boolean asPremultipliedAlpha) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image, flipY,
				asPremultipliedAlpha);		
	}
	
	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, ImageElement image) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, ImageElement image, boolean flipY) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image, flipY);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, ImageElement image, boolean flipY,
			boolean asPremultipliedAlpha) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image, flipY,
				asPremultipliedAlpha);
	}
	
	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, int width, int height, PixelFormat format, PixelType type,
			ArrayBufferView pixels) {
		this.texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, 
				width, height, format.getValue(), type.getValue(), pixels);
	}

	@Deprecated
	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, int width, int height, PixelFormat format, PixelType type,
			WebGLArray pixels) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, width, height, 
				format.getValue(), type.getValue(), pixels);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, PixelFormat format, PixelType type, CanvasElement canvas) {
		this.texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, 
				format.getValue(), type.getValue(), canvas);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, PixelFormat format, PixelType type, ImageData pixels) {
		this.texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, 
				format.getValue(), type.getValue(), pixels);
	}
	
	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, PixelFormat format, PixelType type, ImageElement image) {
		this.texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, 
				format.getValue(), type.getValue(), image);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, PixelFormat format, PixelType type, VideoElement video) {
		this.texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, 
				format.getValue(), type.getValue(), video);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, VideoElement image) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, VideoElement image, boolean flipY) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image, flipY);
	}

	@Override
	public void texSubImage2D(TextureTarget target, int level, int xoffset,
			int yoffset, VideoElement image, boolean flipY,
			boolean asPremultipliedAlpha) {
		texSubImage2DImpl(target.getValue(), level, xoffset, yoffset, image, flipY,
				asPremultipliedAlpha);
	}

	@Override
	public void uniform(WebGLUniformLocation location, Tuple2f v) {
		uniform2f(location, v.x, v.y);		
	}
	
	@Override
	public void uniform(WebGLUniformLocation location, Tuple2i v) {
		uniform2f(location, v.x, v.y);		
	}

	@Override
	public void uniform(WebGLUniformLocation location, Tuple3f v) {
		uniform3f(location, v.x, v.y, v.z);		
	}

	@Override
	public void uniform(WebGLUniformLocation location, Tuple3i v) {
		uniform3i(location, v.x, v.y, v.z);		
	}

	@Override
	public void uniform(WebGLUniformLocation location, Tuple4f v) {
		uniform4f(location, v.x, v.y, v.z, v.w);		
	}

	@Override
	public void uniform(WebGLUniformLocation location, Tuple4i v) {
		uniform4i(location, v.x, v.y, v.z, v.w);		
	}
	
	@Override
	public native void uniform1f(WebGLUniformLocation location, float x) /*-{
		this.uniform1f(location, x);		
	}-*/;

	@Override
	public void uniform1fv(WebGLUniformLocation location, float[] v) {
		uniform1fvImpl(location, toJsArrayNumber(v));
	}

	@Override
	public void uniform1fv(WebGLUniformLocation location, Float32Array v) {
		uniform1fvImpl(location, v);
	}

	@Override
	public void uniform1fv(WebGLUniformLocation location, JsArrayNumber v) {
		uniform1fvImpl(location, v);		
	}
	
	@Deprecated
	@Override
	public void uniform1fv(WebGLUniformLocation location, WebGLFloatArray v) {
		uniform1fvImpl(location, v);		
	}
	
	@Override
	public native void uniform1i(WebGLUniformLocation location, int x) /*-{
		this.uniform1i(location, x);		
	}-*/;

	@Override
	public void uniform1iv(WebGLUniformLocation location, int[] v) {
		uniform1ivImpl(location, toJsArrayInteger(v));
	}

	@Override
	public void uniform1iv(WebGLUniformLocation location, Int32Array v) {
		uniform1ivImpl(location, v);
	}

	@Override
	public void uniform1iv(WebGLUniformLocation location, JsArrayInteger v) {
		uniform1ivImpl(location, v);		
	}

	@Deprecated
	@Override
	public void uniform1iv(WebGLUniformLocation location, WebGLIntArray v) {
		uniform1ivImpl(location, v);		
	}
	
	@Override
	public native void uniform2f(WebGLUniformLocation location, float x, float y) /*-{
		this.uniform2f(location, x, y);		
	}-*/;
	
	@Override
	public void uniform2fv(WebGLUniformLocation location, float[] v) {
		uniform2fvImpl(location, toJsArrayNumber(v));
	}

	@Override
	public void uniform2fv(WebGLUniformLocation location, Float32Array v) {
		uniform2fvImpl(location, v);
	}

	@Override
	public void uniform2fv(WebGLUniformLocation location, JsArrayNumber v) {
		uniform2fvImpl(location, v);		
	}
	
	@Deprecated
	@Override
	public void uniform2fv(WebGLUniformLocation location, WebGLFloatArray v) {
		uniform2fvImpl(location, v);		
	}

	@Override
	public native void uniform2i(WebGLUniformLocation location, int x, int y) /*-{
		this.uniform2i(location, x, y);		
	}-*/;

	@Override
	public void uniform2iv(WebGLUniformLocation location, int[] v) {
		uniform2ivImpl(location, toJsArrayInteger(v));
	}

	@Override
	public void uniform2iv(WebGLUniformLocation location, Int32Array v) {
		uniform2ivImpl(location, v);
	}
	
	@Override
	public void uniform2iv(WebGLUniformLocation location, JsArrayInteger v) {
		uniform2ivImpl(location, v);		
	}

	@Deprecated
	@Override
	public void uniform2iv(WebGLUniformLocation location, WebGLIntArray v) {
		uniform2ivImpl(location, v);		
	}

	@Override
	public native void uniform3f(WebGLUniformLocation location, float x, float y, 
			float z) /*-{
		this.uniform3f(location, x, y, z);		
	}-*/;

	@Override
	public void uniform3fv(WebGLUniformLocation location, float[] v) {
		uniform3fvImpl(location, toJsArrayNumber(v));
	}
	
	@Override
	public void uniform3fv(WebGLUniformLocation location, Float32Array v) {
		uniform3fvImpl(location, v);
	}

	@Override
	public void uniform3fv(WebGLUniformLocation location, JsArrayNumber v) {
		uniform3fvImpl(location, v);		
	}

	@Deprecated
	@Override
	public void uniform3fv(WebGLUniformLocation location, WebGLFloatArray v) {
		uniform3fvImpl(location, v);		
	}

	@Override
	public native void uniform3i(WebGLUniformLocation location, int x, int y, int z) /*-{
		this.uniform3i(location, x, y, z);		
	}-*/;
	
	@Override
	public void uniform3iv(WebGLUniformLocation location, Float32Array v) {
		uniform3ivImpl(location, v);
	}

	@Override
	public void uniform3iv(WebGLUniformLocation location, int[] v) {
		uniform3ivImpl(location, toJsArrayInteger(v));
	}

	@Override
	public void uniform3iv(WebGLUniformLocation location, JsArrayInteger v) {
		uniform3ivImpl(location, v);		
	}

	@Deprecated
	@Override
	public void uniform3iv(WebGLUniformLocation location, WebGLIntArray v) {
		uniform3ivImpl(location, v);		
	}

	@Override
	public native void uniform4f(WebGLUniformLocation location, float x, float y,
			float z, float w) /*-{
		this.uniform4f(location, x, y, z, w);		
	}-*/;
	
	@Override
	public void uniform4fv(WebGLUniformLocation location, float[] v) {
		uniform4fvImpl(location, toJsArrayNumber(v));
	}
	
	@Override
	public void uniform4fv(WebGLUniformLocation location, Float32Array v) {
		uniform4fvImpl(location, v);
	}
	
	@Override
	public void uniform4fv(WebGLUniformLocation location, JsArrayNumber v) {
		uniform4fvImpl(location, v);
	}	

	@Deprecated
	@Override
	public void uniform4fv(WebGLUniformLocation location, WebGLFloatArray v) {
		uniform4fvImpl(location, v);
	}

	@Override
	public native void uniform4i(WebGLUniformLocation location, int x, int y, 
			int z, int w) /*-{
		this.uniform4i(location, x, y, z, w);		
	}-*/;
	
	@Override
	public void uniform4iv(WebGLUniformLocation location, int[] v) {
		uniform4ivImpl(location, toJsArrayInteger(v));
	}

	@Override
	public void uniform4iv(WebGLUniformLocation location, Int32Array v) {
		uniform4ivImpl(location, v);
	}
	
	@Override
	public void uniform4iv(WebGLUniformLocation location, JsArrayInteger v) {
		uniform4ivImpl(location, v);		
	}

	@Deprecated
	@Override
	public void uniform4iv(WebGLUniformLocation location, WebGLIntArray v) {
		uniform4ivImpl(location, v);		
	}
	
	@Override
	public void uniformMatrix(WebGLUniformLocation location, boolean transpose,
			Matrix3f value) {
		uniformMatrix3fvImpl(location, transpose, MatrixUtils.toJsFloatArray(value));
	}

	@Override
	public void uniformMatrix(WebGLUniformLocation location, boolean transpose,
			Matrix4f value) {
		uniformMatrix4fvImpl(location, transpose, MatrixUtils.toJsFloatArray(value));
	}
		
	@Override
	public void uniformMatrix(WebGLUniformLocation location, Matrix3f value) {
		uniformMatrix(location, true, value);
	}
	
	@Override
	public void uniformMatrix(WebGLUniformLocation location, Matrix4f value) {
		uniformMatrix(location, true, value);
	}
	
	@Override
	public void uniformMatrix2fv(WebGLUniformLocation location,
			boolean transpose, float[] value) {
		uniformMatrix2fvImpl(location, transpose, toJsArrayNumber(value));		
	}

	@Override
	public void uniformMatrix2fv(WebGLUniformLocation location,
			boolean transpose, Float32Array value) {
		uniformMatrix2fvImpl(location, transpose, value);	
	}

	@Override
	public void uniformMatrix2fv(WebGLUniformLocation location,
			boolean transpose, JsArrayNumber value) {
		uniformMatrix2fvImpl(location, transpose, value);		
	}

	@Deprecated
	@Override
	public void uniformMatrix2fv(WebGLUniformLocation location,
			boolean transpose, WebGLFloatArray value) {
		uniformMatrix2fvImpl(location, transpose, value);		
	}
	
	private native void uniformMatrix2fvImpl(WebGLUniformLocation location,
			boolean transpose, JavaScriptObject value) /*-{
		this.uniformMatrix2fv(location, false, 
				transpose ? [
						value[0], value[2],
						value[1], value[3] ]
				: value);
	}-*/;
	
	@Override
	public void uniformMatrix3fv(WebGLUniformLocation location,
			boolean transpose, float[] value) {
		uniformMatrix3fvImpl(location, transpose, toJsArrayNumber(value));
	}
	
	@Override
	public void uniformMatrix3fv(WebGLUniformLocation location,
			boolean transpose, Float32Array value) {
		uniformMatrix3fvImpl(location, transpose, value);	
	}

	@Override
	public void uniformMatrix3fv(WebGLUniformLocation location,
			boolean transpose, JsArrayNumber value) {
		uniformMatrix3fvImpl(location, transpose, value);		
	}

	@Deprecated
	@Override
	public void uniformMatrix3fv(WebGLUniformLocation location,
			boolean transpose, WebGLFloatArray value) {
		uniformMatrix3fvImpl(location, transpose, value);		
	}
	
	private native void uniformMatrix3fvImpl(WebGLUniformLocation location,
			boolean transpose, JavaScriptObject value) /*-{
		this.uniformMatrix3fv(location, false, 
				transpose ? [
						value[0], value[3], value[6],
						value[1], value[4], value[7],
						value[2], value[5], value[8] ]
				: value);
	}-*/;
	
	@Override
	public void uniformMatrix4fv(WebGLUniformLocation location, 
			boolean transpose, float[] value) {
		uniformMatrix4fvImpl(location, transpose, toJsArrayNumber(value));
	}
	
	@Override
	public void uniformMatrix4fv(WebGLUniformLocation location,
			boolean transpose, Float32Array value) {
		uniformMatrix4fvImpl(location, transpose, value);
	}

	@Override
	public void uniformMatrix4fv(WebGLUniformLocation location,
			boolean transpose, JsArrayNumber value) {
		uniformMatrix4fvImpl(location, transpose, value);
	}
	
	@Deprecated
	@Override
	public void uniformMatrix4fv(WebGLUniformLocation location,
			boolean transpose, WebGLFloatArray value) {
		uniformMatrix4fvImpl(location, transpose, value);
	}
	
	private native void uniformMatrix4fvImpl(WebGLUniformLocation location,
			boolean transpose, JavaScriptObject value) /*-{
		this.uniformMatrix4fv(location, false, 
				transpose ? [
						value[0], value[4], value[8], value[12],
						value[1], value[5], value[9], value[13],
						value[2], value[6], value[10], value[14],
						value[3], value[7], value[11], value[15] ]
				: value);
	}-*/;

	@Override
	public native void useProgram(WebGLProgram program) /*-{
		this.useProgram(program);		
	}-*/;
	
	@Override
	public native void validateProgram(WebGLProgram program) /*-{
		this.validateProgram(program);		
	}-*/;

	@Override
	public void vertexAttrib(int indx, Tuple2f values) {
		vertexAttrib2f(indx, values.x, values.y);
	}
	
	@Override
	public void vertexAttrib(int indx, Tuple3f values) {
		vertexAttrib3f(indx, values.x, values.y, values.z);
	}

	@Override
	public void vertexAttrib(int indx, Tuple4f values) {
		vertexAttrib4f(indx, values.x, values.y, values.z, values.w);
	}
	
	@Override
	public native void vertexAttrib1f(int indx, float x) /*-{
		this.vertexAttrib1f(indx, x);
	}-*/;
		
	@Override
	public void vertexAttrib1fv(int indx, float[] values) {
		vertexAttrib1fvImpl(indx, toJsArrayNumber(values));
	}
	
	@Override
	public void vertexAttrib1fv(int indx, Float32Array values) {
		vertexAttrib1fvImpl(indx, values);
	}

	@Override
	public void vertexAttrib1fv(int indx, JsArrayNumber values) {
		vertexAttrib1fvImpl(indx, values);		
	}
	
	@Deprecated
	@Override
	public void vertexAttrib1fv(int indx, WebGLFloatArray values) {
		vertexAttrib1fvImpl(indx, values);		
	}

	
	@Override
	public native void vertexAttrib2f(int indx, float x, float y) /*-{
		this.vertexAttrib2f(indx, x, y);
	}-*/;

	@Override
	public void vertexAttrib2fv(int indx, float[] values) {
		vertexAttrib2fvImpl(indx, toJsArrayNumber(values));
	}

	@Override
	public void vertexAttrib2fv(int indx, Float32Array values) {
		vertexAttrib2fvImpl(indx, values);
	}

	@Override
	public void vertexAttrib2fv(int indx, JsArrayNumber values) {
		vertexAttrib2fvImpl(indx, values);		
	}

	@Deprecated
	@Override
	public void vertexAttrib2fv(int indx, WebGLFloatArray values) {
		vertexAttrib2fvImpl(indx, values);		
	}
	
	@Override
	public native void vertexAttrib3f(int indx, float x, float y, float z) /*-{
		this.vertexAttrib3f(indx, x, y, z);		
	}-*/;

	@Override
	public void vertexAttrib3fv(int indx, float[] values) {
		vertexAttrib3fvImpl(indx, toJsArrayNumber(values));
	}
	
	@Override
	public void vertexAttrib3fv(int indx, Float32Array values) {
		vertexAttrib3fvImpl(indx, values);
	}
	
	@Override
	public void vertexAttrib3fv(int indx, JsArrayNumber values) {
		vertexAttrib3fvImpl(indx, values);		
	}

	@Deprecated
	@Override
	public void vertexAttrib3fv(int indx, WebGLFloatArray values) {
		vertexAttrib3fvImpl(indx, values);		
	}

	@Override
	public native void vertexAttrib4f(int indx, float x, float y, float z, float w) /*-{
		this.vertexAttrib4f(indx, x, y, z, w);			
	}-*/;
	
	@Override
	public void vertexAttrib4fv(int indx, float[] values) {
		vertexAttrib4fvImpl(indx, toJsArrayNumber(values));
	}

	@Override
	public void vertexAttrib4fv(int indx, Float32Array values) {
		vertexAttrib4fvImpl(indx, values);
	}
	
	@Override
	public void vertexAttrib4fv(int indx, JsArrayNumber values) {
		vertexAttrib4fvImpl(indx, values);		
	}

	@Deprecated
	@Override
	public void vertexAttrib4fv(int indx, WebGLFloatArray values) {
		vertexAttrib4fvImpl(indx, values);		
	}
	
	@Override
	public void vertexAttribPointer(int indx, int size, DataType type,
			boolean normalized, int stride, int offset) {
		vertexAttribPointerImpl(indx, size, type.getValue(), normalized,
				stride, offset);
	}

	@Override
	public native void viewport(float x, float y, float width, float height) /*-{
		this.viewport(x, y, width, height);
	}-*/;
	
	private native void activeTextureImpl(int texture) /*-{
		this.activeTexture(texture);
	}-*/;

	private native void bindBufferImpl(int target, WebGLBuffer buffer)/*-{
		this.bindBuffer(target, buffer);
	}-*/;

	private native void bindFramebufferImpl(int target, WebGLFramebuffer buffer)/*-{
		this.bindFramebuffer(target, buffer);
	}-*/;	
	
	private native void bindRenderbufferImpl(int target, WebGLRenderbuffer buffer)/*-{
		this.bindRenderbuffer(target, buffer);
	}-*/;
	
	private native void bindTextureImpl(int target, WebGLTexture texture) /*-{
		this.bindTexture(target, texture);		
	}-*/;

	private native void blendEquationImpl(int mode) /*-{
		this.blendEquation(mode);
	}-*/;

	private native void blendEquationSeparateImpl(int modeRGB, int modeAlpha) /*-{
		this.blendEquationSeparate(modeRGB, modeAlpha);
	}-*/;
	
	private native void blendFuncImpl(int sfactor, int dfactor) /*-{
		this.blendFunc(sfactor, dfactor);
	}-*/;
	
	private native void blendFuncSeparateImpl(int srcRGB, int dstRGB,
			int srcAlpha, int dstAlpha) /*-{
		this.blendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
	}-*/;

	private native void bufferDataImpl(int target, int size, int usage)/*-{
		this.bufferData(target, size, usage);
	}-*/;
	
	private native void bufferDataImpl(int target, JavaScriptObject data, int usage) /*-{
		this.bufferData(target, data, usage);
	}-*/;

	private native void bufferSubDataImpl(int target, int offset, JavaScriptObject data) /*-{
		this.bufferSubData(target, offset, data);
	}-*/;
	
	private native int checkFramebufferStatusImpl(int target) /*-{
		return this.checkFramebufferStatus(target);
	}-*/;

	private native void clearImpl(int mask) /*-{
		this.clear(mask);
	}-*/;
	
	private native void copyTexImage2DImpl(int target, int level,
			int internalformat, int x, int y, int width, int height, int border) /*-{
		this.copyTexImage2D(target, level, internalformat, x, y, width, height, border);		
	}-*/;

	private native void copyTexSubImage2DImpl(int target, int level,
			int xoffset, int yoffset, int x, int y, int width, int height) /*-{
		this.copyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);		
	}-*/;
	
	private native WebGLShader createShaderImpl(int type) /*-{
		return this.createShader(type);
	}-*/;

	private native void cullFaceImpl(int mode) /*-{
		this.cullFace(mode);
	}-*/;

	private native void depthFuncImpl(int func) /*-{
		this.depthFunc(func);
	}-*/;

	private native void disableImpl(int cap) /*-{
		this.disable(cap);
	}-*/;
	
	private native void drawArraysImpl(int mode, int first, int count) /*-{
		this.drawArrays(mode, first, count);
	}-*/;
	
	private native void drawElementsImpl(int mode, int count, int type, int offset) /*-{
		this.drawElements(mode, count, type, offset);
	}-*/;

	private native void enableImpl(int cap) /*-{
		this.enable(cap);
	}-*/;
	
	private native void framebufferRenderbufferImpl(int target, int attachment, 
			int renderbuffertarget, WebGLRenderbuffer renderbuffer) /*-{
		this.framebufferRenderbuffer(target, attachment, renderbuffertarget,
				renderbuffer);
	}-*/;
	
	private native void framebufferTexture2DImpl(int target, int attachment, 
			int textarget, WebGLTexture texture, int level) /*-{
		this.framebufferTexture2D(target, attachment, textarget, texture, level);
	}-*/;

	private native void frontFaceImpl(int mode) /*-{
		this.frontFace(mode);
	}-*/;

	private native void generateMipmapImpl(int target) /*-{
		this.generateMipmap(target);
	}-*/;
	
	private native final JsArray<WebGLShader> getAttachedShadersImpl(
			WebGLProgram program) /*-{
		return this.getAttachedShaders(program);
	}-*/;

	private native int getBufferParameteriImpl(int target, int pname) /*-{
	return this.getBufferParameter(target, pname);		
}-*/;

	private native final JavaScriptObject getContextAttributesImpl() /*-{
		return this.getContextAttributes();
	}-*/;

	private native int getErrorImpl() /*-{
		return this.getError();
	}-*/;

	private native int getFramebufferAttachmentParameteriImpl(
			int target, int attachment, int pname) /*-{
		return this.getFramebufferAttachmentParameter(target, attachment, pname);
	}-*/;

	private native <T extends WebGLObject> T getFramebufferAttachmentParameterImpl(
			int target, int attachment, int pname) /*-{
		return this.getFramebufferAttachmentParameter(target, attachment, pname);
	}-*/;

	private native boolean getParameterbImpl(int pname) /*-{
		return this.getParameter(pname);
	}-*/;
	
	private native float getParameterfImpl(int pname) /*-{
		return this.getParameter(pname);
	}-*/;

	private native int getParameteriImpl(int pname) /*-{
		return this.getParameter(pname);
	}-*/;
	
	private native <T extends JavaScriptObject> T getParameterImpl(int pname) /*-{
		return this.getParameter(pname);
	}-*/;
	
	private native boolean getProgramParameterbImpl(WebGLProgram program, int pname) /*-{
		return this.getProgramParameter(program, pname);
	}-*/;
	
	private native int getProgramParameteriImpl(WebGLProgram program, int pname) /*-{
		return this.getProgramParameter(program, pname);
	}-*/;
	
	private native int getRenderbufferParameteriImpl(int target, int pname) /*-{
		return this.getRenderbufferParameter(target, pname);
	}-*/;
	
	private native boolean getShaderParameterbImpl(WebGLShader shader, int pname) /*-{
		return this.getShaderParameter(shader, pname);
	}-*/;

	private native int getShaderParameteriImpl(WebGLShader shader, int pname) /*-{
		return this.getShaderParameter(shader, pname);
	}-*/;

	private native String getStringImpl(int name) /*-{
		return this.getString(name);
	}-*/;
	
	private native JsArrayString getSupportedExtensionsImpl() /*-{
		return this.getSupportedExtensions();
	}-*/;

	private native boolean getVertexAttribbImpl(int index, int pname) /*-{
		return this.getVertexAttrib(index, pname);		
	}-*/;
	
	private native int getVertexAttribiImpl(int index, int pname) /*-{
		return this.getVertexAttrib(index, pname);		
	}-*/;

	private native <T extends JavaScriptObject> T getVertexAttribImpl(int index, 
			int pname) /*-{
		return this.getVertexAttrib(index, pname);		
	}-*/;

	private native void hintImpl(int target, int mode) /*-{
		this.hint(target, mode);
	}-*/;

	private native boolean isEnabledImpl(int cap) /*-{
		return this.isEnabled(cap);
	}-*/;

	private native void pixelStoreiImpl(int pname, int param) /*-{
		this.pixelStorei(pname, param);
	}-*/;

	@Deprecated
	private native WebGLArray readPixelsImpl(int x, int y, int width, int height,
			int format, int type) /*-{
		return this.readPixels(x, y, width, height, format, type);		
	}-*/;

	private native void readPixelsImpl(int x, int y, int width, int height,
			int format, int type, JavaScriptObject pixels) /*-{
		return this.readPixels(x, y, width, height, format, type, pixels);		
	}-*/;

	private native void renderbufferStorageImpl(int target, int internalformat, 
			int width, int height) /*-{
		this.renderbufferStorage(target, internalformat, width, height);
	}-*/;

	private native void stencilFuncImpl(int func, int ref, int mask) /*-{
		this.stencilFunc(func, ref, mask);
	}-*/;

	private native void stencilFuncSeparateImpl(int face, int func, int ref,
			int mask) /*-{
		this.stencilFuncSeparate(face, func, ref, mask);
	}-*/;

	private native void stencilMaskSeparateImpl(int face, int mask) /*-{
		this.stencilMaskSeparate(face, mask);
	}-*/;

	private native void stencilOpImpl(int fail, int zfail, int zpass) /*-{
		this.stencilOp(fail, zfail, zpass);
	}-*/;
	
	private native void stencilOpSeparateImpl(int face, int fail, int zfail,
			int zpass) /*-{
		this.stencilOpSeparate(face, fail, zfail, zpass);
	}-*/;

	private native void texImage2DImpl(int target, int level, int internalformat, 
			int width, int height, int border, int format, int type, 
			JavaScriptObject pixels) /*-{
		this.texImage2D(target, level, internalformat, width, height, border, 
				format, type, pixels);
	}-*/;
	
	private native void texImage2DImpl(int target, int level, int internalformat, 
			int format, int type, JavaScriptObject pixels) /*-{
		this.texImage2D(target, level, internalformat, format, type, pixels);
	}-*/;

	private native void texImage2DImpl(int target, int level,
			JavaScriptObject image) /*-{
		this.texImage2D(target, level, image);
	}-*/;
	
	private native void texImage2DImpl(int target, int level,
			JavaScriptObject image, boolean flipY) /*-{
		this.texImage2D(target, level, image, flipY);
	}-*/;

	private native void texImage2DImpl(int target, int level,
			JavaScriptObject image, boolean flipY, boolean asPremultipliedAlpha) /*-{
		this.texImage2D(target, level, image, flipY, asPremultipliedAlpha);
	}-*/;

	private native void texParameterfImpl(int target, int pname, float param) /*-{
		this.texParameterf(target, pname, param);
	}-*/;
	
	private native void texParameteriImpl(int target, int pname, int param) /*-{
		this.texParameteri(target, pname, param);
	}-*/;
	
	private native void texSubImage2DImpl(int target, int level, 
			int xoffset, int yoffset, int width, int height, int format, int type,
			JavaScriptObject pixels) /*-{
		this.texSubImage2D(target, level, xoffset, yoffset, width, height, format,
				type, pixels);
	}-*/;

	private native void texSubImage2DImpl(int target, int level, 
			int xoffset, int yoffset, int format, int type,
			JavaScriptObject pixels) /*-{
		this.texSubImage2D(target, level, xoffset, yoffset, format,
				type, pixels);
	}-*/;
	
	private native void texSubImage2DImpl(int target, int level, 
			int xoffset, int yoffset, JavaScriptObject image) /*-{
		this.texSubImage2D(target, level, xoffset, yoffset, image);
	}-*/;

	private native void texSubImage2DImpl(int target, int level, 
			int xoffset, int yoffset, JavaScriptObject image, boolean flipY) /*-{
		this.texSubImage2D(target, level, xoffset, yoffset, image, flipY);
	}-*/;
	
	private native void texSubImage2DImpl(int target, int level, 
			int xoffset, int yoffset, JavaScriptObject image, boolean flipY,
			boolean asPremultipliedAlpha) /*-{
		this.texSubImage2D(target, level, xoffset, yoffset, image, flipY, 
				asPremultipliedAlpha);
	}-*/;
	
	private native void uniform1fvImpl(WebGLUniformLocation location, 
			JavaScriptObject v) /*-{
		this.uniform1fv(location, v);
	}-*/;
	
	private native void uniform1ivImpl(WebGLUniformLocation location, 
			JavaScriptObject v) /*-{
		this.uniform1iv(location, v);
	}-*/;

	private native void uniform2fvImpl(WebGLUniformLocation location, 
			JavaScriptObject v) /*-{
		this.uniform2fv(location, v);
	}-*/;

	private native void uniform2ivImpl(WebGLUniformLocation location, 
			JavaScriptObject v) /*-{
		this.uniform2iv(location, v);
	}-*/;

	private native void uniform3fvImpl(WebGLUniformLocation location, 
			JavaScriptObject v) /*-{
		this.uniform3fv(location, v);
	}-*/;

	private native void uniform3ivImpl(WebGLUniformLocation location, 
			JavaScriptObject v) /*-{
		this.uniform3iv(location, v);
	}-*/;

	private native void uniform4fvImpl(WebGLUniformLocation location, 
			JavaScriptObject v) /*-{
		this.uniform4fv(location, v);
	}-*/;
	
	private native void uniform4ivImpl(WebGLUniformLocation location, 
			JavaScriptObject v) /*-{
		this.uniform4iv(location, v);
	}-*/;

	private native void vertexAttrib1fvImpl(int indx, JavaScriptObject values) /*-{
		this.vertexAttrib1fv(indx, values);
	}-*/;
	
	private native void vertexAttrib2fvImpl(int indx, JavaScriptObject values) /*-{
		this.vertexAttrib2fv(indx, values);
	}-*/;

	private native void vertexAttrib3fvImpl(int indx, JavaScriptObject values) /*-{
		this.vertexAttrib3fv(indx, values);
	}-*/;
	
	private native void vertexAttrib4fvImpl(int indx, JavaScriptObject values) /*-{
		this.vertexAttrib4fv(indx, values);
	}-*/;

	private native void vertexAttribPointerImpl(int indx, int size, int type,
			boolean normalized, int stride, int offset) /*-{
		this.vertexAttribPointer(indx, size, type, normalized, stride, offset);		
	}-*/;
}
