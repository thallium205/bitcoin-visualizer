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
import gwt.g3d.client.gl2.enums.BlendingFactorDest;
import gwt.g3d.client.gl2.enums.BlendingFactorSrc;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.EnableCap;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureUnit;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.primitive.PrimitiveFactory;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.ShaderResource;
import gwt.g3d.resources.client.Texture2DResource;
import gwt.g3d.test.client.extras.Lesson8DemoExtra;

import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-port for the lesson 8.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson8Demo extends AbstractLessonDemo {
	private static final int LESSON_NUMBER = 8;
	private final Matrix3f nMatrix = new Matrix3f(); 
	private final Lesson8DemoExtra extraWidget = new Lesson8DemoExtra();
	private Texture2D glassTexture;
	
	private StaticMesh mesh;
	private double lastTime;
	private float xRot, yRot;
	
	/** User defined variables. */
	private float xSpeed, ySpeed, translateZ = -5;
	
	/** Keyboard handlers */
	private final KeyboardManager keyboardManager = new KeyboardManager();
	
	protected Lesson8Demo() {
		super(LESSON_NUMBER);
	}

	@Override
	public void dispose() {
		mesh.dispose();
		glassTexture.dispose();
		keyboardManager.unmanage();
		shader.dispose();
		
		gl.disable(EnableCap.BLEND);
    gl.enable(EnableCap.DEPTH_TEST);
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
	public void update() {
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);
	
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

		xSpeed = 3;
		ySpeed = -3;
    xRot = 0;
		yRot = 0;
		translateZ = -5;
		
    keyboardManager.manage(getSurface());
    keyboardManager.setPreventDefault(true);
    
    PROJECTION.pushIdentity();
    PROJECTION.perspective(45, 1, .1f, 100);
    gl.uniformMatrix(shader.getUniformLocation("uPMatrix"), PROJECTION.get());
    PROJECTION.pop();
		
		mesh = new StaticMesh(gl, PrimitiveFactory.makeBox());
		mesh.setPositionIndex(shader.getAttributeLocation("aVertexPosition"));
		mesh.setNormalIndex(shader.getAttributeLocation("aVertexNormal"));
		mesh.setTexCoordIndex(shader.getAttributeLocation("aTextureCoord"));
		
		gl.activeTexture(TextureUnit.TEXTURE0);
    gl.uniform1i(shader.getUniformLocation("uSampler"), 0);
		
		Resources.INSTANCE.glass().getTexture(new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				glassTexture = resource.createTexture(gl);
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Fail to load image.");
			}
		});
		
		lastTime = System.currentTimeMillis();
	}
	
	private void drawCube() {   
		MODELVIEW.push();
		MODELVIEW.translate(0, 0, translateZ);
		MODELVIEW.rotateX((float) Math.toRadians(xRot));
		MODELVIEW.rotateY((float) Math.toRadians(yRot));
		setMatrixUniforms();
		MODELVIEW.pop();
    
    if (extraWidget.isBlending()) {
      gl.blendFunc(BlendingFactorSrc.SRC_ALPHA, BlendingFactorDest.ONE);
      gl.enable(EnableCap.BLEND);
      gl.disable(EnableCap.DEPTH_TEST);
      gl.uniform1f(shader.getUniformLocation("uAlpha"), extraWidget.getAlpha());
    } else {
      gl.disable(EnableCap.BLEND);
      gl.enable(EnableCap.DEPTH_TEST);
    }
    
		boolean lighting = extraWidget.isLighting();
    gl.uniform1i(shader.getUniformLocation("uUseLighting"), lighting ? 1 : 0);
    if (lighting) {
      gl.uniform(shader.getUniformLocation("uAmbientColor"), 
      		extraWidget.getAmbient()); 
      Vector3f lightingDirection = extraWidget.getDirection();
      lightingDirection.normalize();
      lightingDirection.scale(-1);
      gl.uniform(shader.getUniformLocation("uLightingDirection"), 
      		lightingDirection);
 
      gl.uniform(shader.getUniformLocation("uDirectionalColor"),
      		extraWidget.getColor());
    }
		
    mesh.draw();
	}
	
	private void setMatrixUniforms() {
    gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
    MODELVIEW.getInvertTranspose(nMatrix);
    gl.uniformMatrix(shader.getUniformLocation("uNMatrix"), nMatrix);
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
		
		@Source({"shaders/lesson7.vp", "shaders/lesson8.fp"})
		ShaderResource shader();
		
		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();
		
		@Source("images/glass.gif")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		ExternalTexture2DResource glass();
	}
}
