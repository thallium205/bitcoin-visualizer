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

import gwt.g2d.resources.client.ExternalImageResource;
import gwt.g2d.resources.client.ImageElementResource;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.Texture2DResource;

import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;

/**
 * Abstract implementation of {@link Texture2DResource}
 * 
 * @author hao1300@gmail.com
 */
public abstract class AbstractExternalTexture2DResource 
		implements ExternalTexture2DResource {
	
	/**
	 * Helper method for loading a texture.
	 * 
	 * @param externalImageResource
	 * @param textureResource
	 */
	protected void getTexture(ExternalImageResource externalImageResource,
			final ResourceCallback<Texture2DResource> callback) {
		externalImageResource.getImage(new ResourceCallback<ImageElementResource>() {
			@Override
			public void onSuccess(ImageElementResource resource) {
				onImageLoaded(resource, callback);
			}
			
			@Override
			public void onError(ResourceException e) {
				callback.onError(new ResourceException(e.getResource(), 
						e.getMessage()));
			}
		});
	}
	
	/**
	 * Overrides this method to handle the event when the requested image
	 * is successfully loaded.
	 * 
	 * @param imageResource
	 * @param textureResource
	 */
	protected abstract void onImageLoaded(ImageElementResource imageResource,
			ResourceCallback<Texture2DResource> textureResource);
}
