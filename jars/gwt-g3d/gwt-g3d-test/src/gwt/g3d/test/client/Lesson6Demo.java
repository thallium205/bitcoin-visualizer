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
import gwt.g2d.client.input.KeyboardManager;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureUnit;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.primitive.PrimitiveFactory;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.GenerateMipmap;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.ShaderResource;
import gwt.g3d.resources.client.Texture2DResource;
import gwt.g3d.test.client.extras.Lesson6DemoExtra;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-port for the lesson 6.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson6Demo extends AbstractLessonDemo implements KeyPressHandler {
	private static final int LESSON_NUMBER = 6;
	private final Texture2D[] crateTextures = new Texture2D[3];
	private final Lesson6DemoExtra extraWidget = new Lesson6DemoExtra();

	private StaticMesh mesh;
	private double lastTime;
	private float xRot, yRot;

	/** User defined variables. */
	private int filter;
	private float xSpeed, ySpeed, translateZ;

	/** Keyboard Handlers */
	private HandlerRegistration keyPressHandler;
	private final KeyboardManager keyboardManager = new KeyboardManager();
	
	protected Lesson6Demo() {
		super(LESSON_NUMBER);
	}

	@Override
	public void dispose() {
		mesh.dispose();
		for (Texture2D texture : crateTextures) {
			texture.dispose();
		}
		keyboardManager.unmanage();
		keyPressHandler.removeHandler();
		shader.dispose();
	}

	@Override
	public ClientBundleWithLookup getClientBundle() {
		return Resources.INSTANCE;
	}

	@Override
	public Widget getExtraWidget() {
		return extraWidget;
	}

	@Override
	public void onKeyPress(KeyPressEvent event) {
		if (Character.toUpperCase(event.getCharCode()) == 'F') {
			filter++;
			if (filter == crateTextures.length) {
				filter = 0;
			}
		}
	}

	@Override
	public void update() {
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT,
						ClearBufferMask.DEPTH_BUFFER_BIT);

		handleKeys();
		drawCube();

		double currTime = System.currentTimeMillis();
		double elapsed = currTime - lastTime;
		xRot += (xSpeed * elapsed) / 1000.0;
		yRot += (ySpeed * elapsed) / 1000.0;
		lastTime = currTime;
	}

	@Override
	protected void initImpl() {
		super.initImpl();

		filter = 0;
		xSpeed = 0;
		ySpeed = 0;
		xRot = 0;
		yRot = 0;
		translateZ = -5;

		keyboardManager.manage(getSurface());
		keyboardManager.setPreventDefault(true);
		keyPressHandler = getSurface().addKeyPressHandler(this);
		
		PROJECTION.pushIdentity();
		PROJECTION.perspective(45, 1, .1f, 100);
		gl.uniformMatrix(shader.getUniformLocation("uPMatrix"), PROJECTION.get());
		PROJECTION.pop();

		mesh = new StaticMesh(gl, PrimitiveFactory.makeBox());
		mesh.setPositionIndex(shader.getAttributeLocation("aVertexPosition"));
		mesh.setNormalIndex(-1);
		mesh.setTexCoordIndex(shader.getAttributeLocation("aTextureCoord"));

		gl.activeTexture(TextureUnit.TEXTURE0);
		gl.uniform1i(shader.getUniformLocation("uSampler"), 0);

		initializeTextures();
		lastTime = System.currentTimeMillis();
	}

	/**
	 * Gets the selected crate texture.
	 */
	private Texture2D getCrateTexture() {
		return crateTextures[filter];
	}

	private void drawCube() {
		MODELVIEW.push();
		MODELVIEW.translate(0, 0, translateZ);
		MODELVIEW.rotateX((float) Math.toRadians(xRot));
		MODELVIEW.rotateY((float) Math.toRadians(yRot));
		gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
		MODELVIEW.pop();

		if (getCrateTexture() != null) {
			getCrateTexture().bind();
		}

		mesh.draw();
	}

	private void initializeTextures() {
		ExternalTexture2DResource[] externalTextures = {
				Resources.INSTANCE.crate0(), Resources.INSTANCE.crate1(),
				Resources.INSTANCE.crate2() };
		int i = 0;
		for (ExternalTexture2DResource externalTexture : externalTextures) {
			final int index = i;
			externalTexture.getTexture(new ResourceCallback<Texture2DResource>() {
				@Override
				public void onSuccess(Texture2DResource resource) {
					crateTextures[index] = resource.createTexture(gl);
				}

				@Override
				public void onError(ResourceException e) {
					Window.alert("Fail to load image.");
				}
			});
			i++;
		}
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

		@Source( { "shaders/lesson5.vp", "shaders/lesson5.fp" })
		ShaderResource shader();

		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();

		@Source("images/crate.gif")
		@MagFilter(TextureMagFilter.NEAREST)
		@MinFilter(TextureMinFilter.NEAREST)
		ExternalTexture2DResource crate0();

		@Source("images/crate.gif")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		ExternalTexture2DResource crate1();

		@Source("images/crate.gif")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR_MIPMAP_NEAREST)
		@GenerateMipmap
		ExternalTexture2DResource crate2();
	}
}
