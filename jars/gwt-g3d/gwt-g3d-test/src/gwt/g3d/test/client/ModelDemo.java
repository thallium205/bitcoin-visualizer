/*
 * Copyright 2010 Hao Nguyen
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
import gwt.g2d.client.input.KeyboardManager;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureUnit;
import gwt.g3d.client.gl2.enums.TextureWrapMode;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.shader.ShaderException;
import gwt.g3d.client.shader.TextureShader;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalMeshResource;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MeshResource;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.Texture2DResource;
import gwt.g3d.resources.client.WrapMode;
import gwt.g3d.test.client.extras.ModelDemoExtra;
import gwt.g3d.test.client.extras.ModelDemoExtra.ModelType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * Demo the use of texture.
 * 
 * @author hao1300@gmail.com
 */
public class ModelDemo extends AbstractDemo {
	private final ModelDemoExtra extraWidget = new ModelDemoExtra();
	private final TextureShader shader = new TextureShader();
	private Texture2D texture;
	private StaticMesh mesh;
	private double lastTime;
	private float xRot, yRot;
	private float scale;
	private float xSpeed, ySpeed, translateZ;
	
	private final KeyboardManager keyboardManager = new KeyboardManager();
	
	protected ModelDemo() {
		super("Model Demo", "");
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
		shader.setTintColor(1, 1, 1, 1f);

		keyboardManager.manage(getSurface());
		keyboardManager.setPreventDefault(true);
		
		extraWidget.addModelSelectedHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (mesh != null) {
					mesh.dispose();
					mesh = null;
				}
				if (texture != null) {
					texture.dispose();
					texture = null;
				}
				loadModel(extraWidget.getSelectedModel());
			}
		});
		
		xSpeed = 0;
		ySpeed = 0;
		xRot = 0;
		yRot = 0;
		translateZ = -1;
		
		PROJECTION.pushIdentity();
    PROJECTION.perspective(45, 1, .1f, 100);
    shader.setProjectionMatrix(PROJECTION.get());
    PROJECTION.pop();		

		shader.setTexture(TextureUnit.TEXTURE0);
		gl.activeTexture(TextureUnit.TEXTURE0);

		loadModel(extraWidget.getSelectedModel());
		
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void update() {
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, 
				ClearBufferMask.DEPTH_BUFFER_BIT);

		handleKeys();
		
		double currTime = System.currentTimeMillis();
		double elapsed = currTime - lastTime;
		xRot += (xSpeed * elapsed) / 1000.0;
		yRot += (ySpeed * elapsed) / 1000.0;
		lastTime = currTime;
		
		MODELVIEW.push();
		MODELVIEW.translate(0, 0, translateZ);
		MODELVIEW.rotateX((float) Math.toRadians(xRot));
		MODELVIEW.rotateY((float) Math.toRadians(yRot));
		MODELVIEW.scale(scale, scale, scale);
		shader.setModelViewMatrix(MODELVIEW.get());
		MODELVIEW.pop();

		if (mesh != null && texture != null) {
			mesh.draw();
		}
	}

	@Override
	public void dispose() {
		shader.dispose();
		keyboardManager.unmanage();
		if (mesh != null) {
			mesh.dispose();
			mesh = null;
		}
		if (texture != null) {
			texture.dispose();
			texture = null;
		}
	}
	
	@Override
	public Widget getExtraWidget() {
		return extraWidget;
	}

	@Override
	public ClientBundleWithLookup getClientBundle() {
		return Resources.INSTANCE;
	}
	
	private void loadModel(ModelType modelType) {
		ExternalTexture2DResource textureResource;
		ExternalMeshResource meshResource;
		Resources resource = Resources.INSTANCE;
		switch (modelType) {
			case FISH:
				textureResource = resource.fishTexture();
				meshResource = resource.fish();
				scale = 1;
				break;
			case PLANT:
				textureResource = resource.plantTexture();
				meshResource = resource.plant();
				scale = 0.04f;
				break;
			case PTERANADON:
				textureResource = resource.pteranadonTexture();
				meshResource = resource.pteranadon();
				scale = 0.007f;
				break;
			default:
				throw new RuntimeException("Unsupported model type!");
		}
		
		textureResource.getTexture(
				new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				texture = resource.createTexture(gl);
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Fail to load image.");
			}
		});
    
		meshResource.getMesh(new ResourceCallback<MeshResource>() {
			@Override
			public void onSuccess(MeshResource resource) {
				mesh = resource.createMesh(gl);
				mesh.setPositionIndex(shader.getAttributePosition());
				mesh.setNormalIndex(-1);
				mesh.setTexCoordIndex(shader.getAttributeTexCoord());
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert(e.getMessage());
			}
		});
	}
	
	private void handleKeys() {
		if (keyboardManager.isButtonDown(KeyCodes.KEY_PAGEUP)) {
			translateZ += 0.05;
		}
		if (keyboardManager.isButtonDown(KeyCodes.KEY_PAGEDOWN)) {
			translateZ -= 0.05;
		}
		if (keyboardManager.isButtonDown(KeyCodes.KEY_LEFT)) {
			ySpeed--;
		}
		if (keyboardManager.isButtonDown(KeyCodes.KEY_RIGHT)) {
			ySpeed++;
		}
		if (keyboardManager.isButtonDown(KeyCodes.KEY_UP)) {
			xSpeed--;
		}
		if (keyboardManager.isButtonDown(KeyCodes.KEY_DOWN)) {
			xSpeed++;
		}
	}
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source("ModelDemo.java")
		ExternalTextResource source();
		
		@Source("models/fish.obj")
		ExternalMeshResource fish();
		
		@Source("models/plant.obj")
		ExternalMeshResource plant();
		
		@Source("models/pteranadon.obj")
		ExternalMeshResource pteranadon();
		
		@Source("images/fish.png")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		@WrapMode(TextureWrapMode.CLAMP_TO_EDGE)
		ExternalTexture2DResource fishTexture();
		
		@Source("images/plant.jpg")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		@WrapMode(TextureWrapMode.CLAMP_TO_EDGE)
		ExternalTexture2DResource plantTexture();
		
		@Source("images/pteranadon.jpg")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		@WrapMode(TextureWrapMode.CLAMP_TO_EDGE)
		ExternalTexture2DResource pteranadonTexture();
	}
}
