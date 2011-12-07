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
package gwt.g3d.test.client;

import static gwt.g3d.client.math.MatrixStack.MODELVIEW;
import static gwt.g3d.client.math.MatrixStack.PROJECTION;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureUnit;
import gwt.g3d.client.gl2.enums.TextureWrapMode;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.primitive.PrimitiveFactory;
import gwt.g3d.client.shader.ShaderException;
import gwt.g3d.client.shader.TextureShader;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.Texture2DResource;
import gwt.g3d.resources.client.WrapMode;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;

/**
 * Demo the use of texture.
 * 
 * @author hao1300@gmail.com
 */
public class TextureDemo extends AbstractDemo {
	private final TextureShader shader = new TextureShader();
	private Texture2D texture;
	private StaticMesh mesh;
	private float angle;

	protected TextureDemo() {
		super("Texture Demo", "");
	}

	@Override
	protected void initImpl() {
		try {
			shader.init(gl);
		} catch (ShaderException e) {
			Window.alert(e.getMessage());
			return;
		}
		shader.bind();

		angle = 0;

		mesh = new StaticMesh(gl, PrimitiveFactory.makeBox());
		mesh.setPositionIndex(shader.getAttributePosition());
		mesh.setNormalIndex(-1);
		mesh.setTexCoordIndex(shader.getAttributeTexCoord());

		shader.setTintColor(1, 1, 1, 1f);

		PROJECTION.pushIdentity();
    PROJECTION.perspective(45, 1, .1f, 100);
    shader.setProjectionMatrix(PROJECTION.get());
    PROJECTION.pop();		

		shader.setTexture(TextureUnit.TEXTURE0);
		gl.activeTexture(TextureUnit.TEXTURE0);

		Resources.INSTANCE.earth().getTexture(
				new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				texture = resource.createTexture(gl);
				mesh.draw();
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Fail to load image.");
			}
		});
	}

	@Override
	public void update() {
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, 
				ClearBufferMask.DEPTH_BUFFER_BIT);

		MODELVIEW.push();
		MODELVIEW.translate(0, 0, -5);
		MODELVIEW.rotateY(angle += .01f);
		shader.setModelViewMatrix(MODELVIEW.get());
		MODELVIEW.pop();

		mesh.draw();
	}

	@Override
	public void dispose() {
		shader.dispose();
		mesh.dispose();
		if (texture != null) {
			texture.dispose();
			texture = null;
		}
	}

	@Override
	public ClientBundleWithLookup getClientBundle() {
		return Resources.INSTANCE;
	}
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source("TextureDemo.java")
		ExternalTextResource source();
		
		@Source("images/earth.jpg")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		@WrapMode(TextureWrapMode.CLAMP_TO_EDGE)
		ExternalTexture2DResource earth();
	}
}
