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
import gwt.g3d.client.math.MatrixStack;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.primitive.PrimitiveFactory;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.ShaderResource;
import gwt.g3d.resources.client.Texture2DResource;
import gwt.g3d.test.client.extras.Lesson11DemoExtra;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-port for the lesson 11.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson11Demo extends AbstractLessonDemo implements 
		MouseDownHandler, MouseUpHandler, MouseMoveHandler {
	private static final int LESSON_NUMBER = 11;
	private final Lesson11DemoExtra extraWidget = new Lesson11DemoExtra();
	private final Matrix3f nMatrix = new Matrix3f();
	private final Vector2f lastMouse = new Vector2f();
	private final Matrix4f moonRotationMatrix = new Matrix4f();
	private final MatrixStack rotationMatrixStack = new MatrixStack();
	private Texture2D moonTexture;
	private boolean mouseDown;
	
	private StaticMesh mesh;
	
	/** Mouse handler registration */
	private HandlerRegistration mouseDownRegistration, mouseUpRegistration,
			mouseMoveRegistration;
	
	protected Lesson11Demo() {
		super(LESSON_NUMBER);
	}
	
	@Override
	public void dispose() {
		mesh.dispose();
		moonTexture.dispose();
		shader.dispose();
		mouseDownRegistration.removeHandler();
		mouseUpRegistration.removeHandler();
		mouseMoveRegistration.removeHandler();
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
	public void onMouseDown(MouseDownEvent event) {
		lastMouse.set(event.getClientX(), event.getClientY());
		mouseDown = true;
	}
	
	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (!mouseDown) {
      return;
    }
    float newX = event.getClientX();
    float newY = event.getClientY();
 
    rotationMatrixStack.loadIdentity();
    
    float deltaX = newX - lastMouse.x;
    rotationMatrixStack.rotateY((float) Math.toRadians(deltaX / 10f));
 
    float deltaY = newY - lastMouse.y;
    rotationMatrixStack.rotateX((float) Math.toRadians(deltaY / 10f));
 
    moonRotationMatrix.mul(rotationMatrixStack.get(), moonRotationMatrix);
 
    lastMouse.set(newX, newY);
	}
	
	@Override
	public void onMouseUp(MouseUpEvent event) {
		mouseDown = false;
	}
	
	@Override
	public void update() {
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);
 
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
    
		MODELVIEW.push();
    MODELVIEW.translate(0, 0, -6);
    MODELVIEW.mul(moonRotationMatrix);
    MODELVIEW.getInvertTranspose(nMatrix);
		gl.uniformMatrix(shader.getUniformLocation("uNMatrix"), nMatrix);
		
		MODELVIEW.scale(2, 2, 2);
		gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
    mesh.draw();
    MODELVIEW.pop();
	}
		
	@Override
	protected void initImpl() {
		super.initImpl();
		
		mouseDownRegistration = getSurface().addMouseDownHandler(this);
		mouseUpRegistration = getSurface().addMouseUpHandler(this);
		mouseMoveRegistration = getSurface().addMouseMoveHandler(this);
    
		gl.activeTexture(TextureUnit.TEXTURE0);
    gl.uniform1i(shader.getUniformLocation("uSampler"), 0);
    
		Resources.INSTANCE.moon().getTexture(new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				moonTexture = resource.createTexture(gl);
				moonTexture.bind();
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Fail to load image.");
			}
		});
    
		PROJECTION.pushIdentity();
		PROJECTION.perspective(45, 1, .1f, 100);
		gl.uniformMatrix(shader.getUniformLocation("uPMatrix"), PROJECTION.get());
		PROJECTION.pop();
		
		mesh = new StaticMesh(gl, PrimitiveFactory.makeSphere(30, 30));
		mesh.setPositionIndex(shader.getAttributeLocation("aVertexPosition"));
		mesh.setNormalIndex(shader.getAttributeLocation("aVertexNormal"));
		mesh.setTexCoordIndex(shader.getAttributeLocation("aTextureCoord"));
		
		moonRotationMatrix.setIdentity();
	}
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source({"shaders/lesson7.vp", "shaders/lesson7.fp"})
		ShaderResource shader();
		
		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();
		
		@Source("images/moon.gif")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		ExternalTexture2DResource moon();
	}
}
