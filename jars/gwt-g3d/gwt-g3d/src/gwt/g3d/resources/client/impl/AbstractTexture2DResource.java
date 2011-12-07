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
package gwt.g3d.resources.client.impl;

import gwt.g2d.resources.client.ImageElementResource;
import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.Texture2DResource;

import com.google.gwt.dom.client.ImageElement;

/**
 * Abstract implementation of {@link Texture2DResource}
 * 
 * @author hao1300@gmail.com
 */
public abstract class AbstractTexture2DResource 
		implements Texture2DResource {
	private final ImageElement imageElement;
	
	public AbstractTexture2DResource(ImageElementResource imageResource) {
		this.imageElement = imageResource.getImage();
	}
	
	@Override
	public Texture2D createTexture(GL2 gl) {
		Texture2D texture2d = new Texture2D();
		texture2d.init(gl);
		texture2d.bind();
		texture2d.texImage(imageElement);
		setTextureParameters(texture2d);
		return texture2d;
	}
	
	@Override
	public ImageElement getImage() {
		return imageElement;
	}
	
	/**
	 * Sets up the texture parameters.
	 * 
	 * @param texture2d
	 */
	protected abstract void setTextureParameters(Texture2D texture2d);
}
