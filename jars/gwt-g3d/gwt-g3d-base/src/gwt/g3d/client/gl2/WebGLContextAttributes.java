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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * WebGLContextAttributes interface contains drawing surface attributes and is 
 * passed as the second parameter to getContext.
 * 
 * @author hao1300@gmail.com
 */
public class WebGLContextAttributes {
	private final WebGLContextAttributesImpl impl;
	
	public WebGLContextAttributes() {
		impl = JavaScriptObject.createObject().cast();
	}
	
	/**
	 * Converts a JavaScriptObject to this WebGLContextAttributes.
	 *  
	 * @param contextAttribs a JavaScriptObject whose type is 
	 * 				WebGLContextAttributes.
	 */
	WebGLContextAttributes(JavaScriptObject contextAttribs) {
		impl = contextAttribs.cast();
	}
	
	/**
	 * Gets the  JavaScriptObject for the 
	 * @return
	 */
	public JavaScriptObject getGLContextAttributesImpl() {
		return impl;		
	}
	
	/**
	 * Default: true. If the value is true, the drawing buffer has an alpha 
	 * channel for the purposes of performing OpenGL destination alpha operations 
	 * and compositing with the page. If the value is false, no alpha buffer is 
	 * available.
	 * 
	 * @param enable
	 */
	public void setAlphaEnable(boolean enable) {
		impl.setAlphaEnable(enable);
	}
	
	/**
	 * Default: true. If the value is true, the drawing buffer has a depth buffer 
	 * of at least 16 bits. If the value is false, no depth buffer is available.
	 * 
	 * @param enable
	 */
	public void setDepthEnable(boolean enable) {
		impl.setDepthEnable(enable);
	}
	
	/**
	 * Default: false. If the value is true, the drawing buffer has a stencil 
	 * buffer of at least 8 bits. If the value is false, no stencil buffer is 
	 * available.
	 * 
	 * @param enable
	 */
	public void setStencilEnable(boolean enable) {
		impl.setStencilEnable(enable);
	}
	
	/**
	 * Default: true. If the value is true and the implementation supports 
	 * antialiasing the drawing buffer will perform antialiasing using its 
	 * choice of technique (multisample/supersample) and quality. If the value 
	 * is false or the implementation does not support antialiasing, no 
	 * antialiasing is performed.
	 * 
	 * @param enable
	 */
	public void setAntialiasEnable(boolean enable) {
		impl.setAntialiasEnable(enable);
	}
	
	/**
	 * Default: true. If the value is true the page compositor will assume the 
	 * drawing buffer contains colors with premultiplied alpha. If the value is 
	 * false the page compositor will assume that colors in the drawing buffer 
	 * are not premultiplied. This flag is ignored if the alpha flag is false.
	 * 
	 * @param enable
	 */
	public void setPremultipliedAlphaEnable(boolean enable) {
		impl.setPremultipliedAlphaEnable(enable);
	}

	/**
	 * @see #setAlphaEnable(boolean)
	 */
	public boolean isAlphaEnable() {
		return impl.isAlphaEnable();
	}
	
	/**
	 * @see #setDepthEnable(boolean)
	 */
	public boolean isDepthEnable() {
		return impl.isDepthEnable();
	}
	
	/**
	 * @see #setStencilEnable(boolean)
	 */
	public boolean isStencilEnable() {
		return impl.isStencilEnable();
	}
	
	/**
	 * @see #setAntialiasEnable(boolean)
	 */
	public boolean isAntialiasEnable() {
		return impl.isAntialiasEnable();
	}
	
	/**
	 * @see #setPremultipliedAlphaEnable(boolean)
	 */
	public boolean isPremultipliedAlphaEnable() {
		return impl.isPremultipliedAlphaEnable();
	}
	
	/**
	 * JavaScriptObject that wraps the functionalities of WebGLContextAttributes.
	 * 
	 * @author hao1300@gmail.com
	 */
	private static class WebGLContextAttributesImpl extends JavaScriptObject {
		protected WebGLContextAttributesImpl() {
		}
		
		public native final void setAlphaEnable(boolean enable) /*-{
			this.alpha = enable;
		}-*/;
		
		public native final void setDepthEnable(boolean enable) /*-{
			this.depth = enable;
		}-*/;
		
		public native final void setStencilEnable(boolean enable) /*-{
			this.stencil = enable;
		}-*/;
		
		public native final void setAntialiasEnable(boolean enable) /*-{
			this.antialias = enable;
		}-*/;
		
		public native final void setPremultipliedAlphaEnable(boolean enable) /*-{
			this.premultipliedAlpha = enable;
		}-*/;

		public native final boolean isAlphaEnable() /*-{
			return this.alpha;
		}-*/;
		
		public native final boolean isDepthEnable() /*-{
			return this.depth;
		}-*/;
		
		public native final boolean isStencilEnable() /*-{
			return this.stencil;
		}-*/;
		
		public native final boolean isAntialiasEnable() /*-{
			return this.antialias;
		}-*/;
		
		public native final boolean isPremultipliedAlphaEnable() /*-{
			return this.premultipliedAlpha;
		}-*/;
	}
}
