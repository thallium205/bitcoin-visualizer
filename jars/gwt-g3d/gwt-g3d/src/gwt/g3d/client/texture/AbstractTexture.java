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
package gwt.g3d.client.texture;

import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.WebGLTexture;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureParameterName;
import gwt.g3d.client.gl2.enums.TextureTarget;
import gwt.g3d.client.gl2.enums.TextureWrapMode;

/**
 * Represents an abstract Texture.
 * 
 * @author hao1300@gmail.com
 */
public abstract class AbstractTexture implements Texture {
	private final TextureTarget target;
	private WebGLTexture texture;
	protected GL2 gl;
	
	protected AbstractTexture(TextureTarget target) {
		this.target = target;
	}
	
	/**
	 * Gets the texture target.
	 */
	public TextureTarget getTarget() {
		return target;
	}
	
	/**
	 * Gets the WebGLTexture implementation.
	 */
	public WebGLTexture getTexture() {
		return texture;
	}
	
	@Override
	public void init(GL2 gl) {
		this.gl = gl;
		texture = gl.createTexture();
	}
	
	@Override
	public void dispose() {
		gl.deleteTexture(texture);
	}
	
	@Override
	public void bind() {
		gl.bindTexture(target, texture);
	}
	
	/**
	 * Generates mipmap for this texture.
	 * Requires: {@link #bind()} must be called before using this method.
	 */
	public void generateMipmap() {
		gl.generateMipmap(target);
	}
	
	/**
	 * Sets the texture mag filter.
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param magFilter
	 */
	public void setMagFilter(TextureMagFilter magFilter) {
		gl.texParameter(target, TextureParameterName.TEXTURE_MAG_FILTER, magFilter);
	}
	
	/**
	 * Sets the texture min filter.
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param minFilter
	 */
	public void setMinFilter(TextureMinFilter minFilter) {
		gl.texParameter(target, TextureParameterName.TEXTURE_MIN_FILTER, minFilter);
	}
	
	/**
	 * Sets the texture wrap mode.
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param pname
	 * @param wrapMode
	 */
	public void setWrapMode(TextureParameterName pname, TextureWrapMode wrapMode) {
		gl.texParameter(target, pname, wrapMode);
	}
	
	/**
	 * Sets the texture wrap mode in all directions.
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param wrapMode
	 */
	public abstract void setWrapMode(TextureWrapMode wrapMode);
	
	/**
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param pname
	 * @param param
	 * @see GL2#texParameteri(TextureTarget, TextureParameterName, int)
	 */
	public void setParameter(TextureParameterName pname, int param) {
		gl.texParameteri(target, pname, param);
	}
	
	/**
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param pname
	 * @param param
	 * @see GL2#texParameteri(TextureTarget, TextureParameterName, int)
	 */
	public void setParameter(TextureParameterName pname, float param) {
		gl.texParameterf(target, pname, param);
	}
}
