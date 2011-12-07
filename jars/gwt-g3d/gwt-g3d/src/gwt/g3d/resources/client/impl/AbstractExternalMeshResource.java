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

import gwt.g3d.resources.client.ExternalMeshResource;
import gwt.g3d.resources.client.MeshResource;
import gwt.g3d.resources.client.Texture2DResource;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;

/**
 * Abstract implementation of {@link Texture2DResource}
 * 
 * @author hao1300@gmail.com
 */
public abstract class AbstractExternalMeshResource 
		implements ExternalMeshResource {
	
	/**
	 * Helper method for loading a mesh.
	 * 
	 * @param externalImageResource
	 * @param textureResource
	 */
	protected void getMesh(String url, final MeshDataInfo meshDataInfo,
			final ResourceCallback<MeshResource> callback) {
		RequestBuilder request = new RequestBuilder(RequestBuilder.POST, url);
		request.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				callback.onSuccess(new AbstractMeshResource(meshDataInfo,
						JSONParser.parse(response.getText())) {
					@Override
					public String getName() {
						return AbstractExternalMeshResource.this.getName();
					}
				});
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				callback.onError(new ResourceException(AbstractExternalMeshResource.this, 
						exception.getMessage()));
			}
		});
		try {
			request.send();
		} catch (RequestException e) {
			callback.onError(new ResourceException(AbstractExternalMeshResource.this, 
					e.getMessage()));
		}
	}	
}
