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
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.primitive.PrimitiveFactory;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.ShaderResource;
import gwt.g3d.resources.client.Texture2DResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;

/**
 * GWT-port for the lesson 5.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson5Demo extends AbstractLessonDemo {
	private static final int LESSON_NUMBER = 5;
	private Texture2D texture;
	private StaticMesh mesh;
	private double lastTime;
	private float xRot, yRot, zRot;
	
	protected Lesson5Demo() {
		super(LESSON_NUMBER);
	}

	@Override
	public void dispose() {
		mesh.dispose();
		texture.dispose();
		shader.dispose();
	}
	
	@Override
	public ClientBundleWithLookup getClientBundle() {
		return Resources.INSTANCE;
	}
	
	@Override
	public void update() {
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);
		
    drawCube();

    double currTime = System.currentTimeMillis();
    double elapsed = currTime - lastTime;
    xRot += (90 * elapsed) / 1000.0;
    yRot += (90 * elapsed) / 1000.0;
    zRot += (90 * elapsed) / 1000.0;
    lastTime = currTime;
	}
	
	private void drawCube() {   
		MODELVIEW.push();
		MODELVIEW.translate(0, 0, -5);
		MODELVIEW.rotateX((float) Math.toRadians(xRot));
		MODELVIEW.rotateY((float) Math.toRadians(yRot));
		MODELVIEW.rotateZ((float) Math.toRadians(zRot));
		gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
		MODELVIEW.pop();
    
    mesh.draw();
	}
	
	@Override
	protected void initImpl() {
		super.initImpl();
		
		xRot = 0;
		yRot = 0;
		zRot = 0;
		
		PROJECTION.pushIdentity();
    PROJECTION.perspective(45, 1, .1f, 100);
    gl.uniformMatrix(shader.getUniformLocation("uPMatrix"), PROJECTION.get());
    PROJECTION.pop();
		
		mesh = new StaticMesh(gl, PrimitiveFactory.makeBox());
		mesh.setPositionIndex(shader.getAttributeLocation("aVertexPosition"));
		mesh.setTexCoordIndex(shader.getAttributeLocation("aTextureCoord"));
		mesh.setNormalIndex(-1);
		
		gl.activeTexture(TextureUnit.TEXTURE0);
    gl.uniform1i(shader.getUniformLocation("uSampler"), 0);
		
		Resources.INSTANCE.nehe().getTexture(new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				texture = resource.createTexture(gl);
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Fail to load image.");
			}
		});
		
		lastTime = System.currentTimeMillis();
	}
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source({"shaders/lesson5.vp", "shaders/lesson5.fp"})
		ShaderResource shader();
		
		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();
		
		@Source("images/nehe.gif")
		@MagFilter(TextureMagFilter.NEAREST)
		@MinFilter(TextureMinFilter.NEAREST)
		ExternalTexture2DResource nehe();
	}
}
