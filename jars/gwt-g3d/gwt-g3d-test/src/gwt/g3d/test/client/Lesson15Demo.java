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
import gwt.g3d.test.client.extras.Lesson15DemoExtra;

import javax.vecmath.Matrix4f;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-port for the lesson 15.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson15Demo extends AbstractLessonDemo {
	private static final int LESSON_NUMBER = 15;
	
	private final Lesson15DemoExtra extraWidget = new Lesson15DemoExtra();
	private StaticMesh mesh;
	private Texture2D earthTexture, earthSpecularTexture;
	private final Matrix4f nMatrix = new Matrix4f();
	
	private double lastTime;
	private float angle;
	
	protected Lesson15Demo() {
		super(LESSON_NUMBER);
		nMatrix.setIdentity();
	}
	
	@Override
	public void dispose() {
		shader.dispose();
		mesh.dispose();
		if (earthTexture != null) {
			earthTexture.dispose();
			earthTexture = null;
		}
		if (earthSpecularTexture != null) {
			earthSpecularTexture.dispose();
			earthSpecularTexture = null;
		}
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
		
		gl.uniform1i(shader.getUniformLocation("uUseColorMap"), 
				extraWidget.isUseColorMap() ? 1 : 0);
		gl.uniform1i(shader.getUniformLocation("uUseSpecularMap"), 
				extraWidget.isUseSpecularMap() ? 1 : 0);
		
		boolean lighting = extraWidget.isLighting();
    gl.uniform1i(shader.getUniformLocation("uUseLighting"), lighting ? 1 : 0);
    if (lighting) {
      gl.uniform(shader.getUniformLocation("uAmbientColor"), 
      		extraWidget.getAmbient()); 
      gl.uniform(shader.getUniformLocation("uPointLightingLocation"), 
      		extraWidget.getPosition());
      gl.uniform(shader.getUniformLocation("uPointLightingSpecularColor"),
      		extraWidget.getSpecularColor());
      gl.uniform(shader.getUniformLocation("uPointLightingDiffuseColor"),
      		extraWidget.getDiffuseColor());
    }

    gl.activeTexture(TextureUnit.TEXTURE0);
    if (earthTexture != null) {
    	earthTexture.bind();
    }
    gl.uniform1i(shader.getUniformLocation("uColorMapSampler"), 0);
 
    gl.activeTexture(TextureUnit.TEXTURE1);
    if (earthSpecularTexture != null) {
    	earthSpecularTexture.bind();
    }
    gl.uniform1i(shader.getUniformLocation("uSpecularMapSampler"), 1);
    
    MODELVIEW.pushIdentity();
    MODELVIEW.translate(0, 0, -40);
    MODELVIEW.rotate((float) Math.toRadians(23.4), 1, 0, -1);
    MODELVIEW.rotateY((float) Math.toRadians(angle));
    MODELVIEW.getInvertTranspose(nMatrix);
    gl.uniformMatrix(shader.getUniformLocation("uNMatrix"), nMatrix);
    
    MODELVIEW.scale(13, 13, 13);
    gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
    mesh.draw();
    MODELVIEW.pop();
 
		double currTime = System.currentTimeMillis();
	  float elapsed = (float) (currTime - lastTime);
	  angle += 0.05f * elapsed;
	  lastTime = currTime;
	}

	
	@Override
	protected void initImpl() {
		super.initImpl();
		
		angle = 180;
		
		PROJECTION.pushIdentity();
		PROJECTION.perspective(45, 1, .1f, 100);
		gl.uniformMatrix(shader.getUniformLocation("uPMatrix"), PROJECTION.get());
		PROJECTION.pop();
		
		mesh = new StaticMesh(gl, PrimitiveFactory.makeSphere(30, 30));
		mesh.setPositionIndex(shader.getAttributeLocation("aVertexPosition"));
		mesh.setNormalIndex(shader.getAttributeLocation("aVertexNormal"));
		mesh.setTexCoordIndex(shader.getAttributeLocation("aTextureCoord"));
		
		Resources.INSTANCE.earthSpecular().getTexture(new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				earthSpecularTexture = resource.createTexture(gl);
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Fail to load galvanized image.");
			}
		});
		
		Resources.INSTANCE.earth().getTexture(new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				earthTexture = resource.createTexture(gl);
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Fail to load earth image.");
			}
		});
		
		lastTime = System.currentTimeMillis();
	}
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source({"shaders/lesson13.vp", "shaders/lesson15.fp"})
		ShaderResource shader();
		
		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();
		
		@Source("images/earth.jpg")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		ExternalTexture2DResource earth();
		
		@Source("images/earth-specular.gif")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		ExternalTexture2DResource earthSpecular();
	}
}
