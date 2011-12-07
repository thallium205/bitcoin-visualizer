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
import gwt.g3d.client.shader.AbstractShader;
import gwt.g3d.client.shader.ShaderException;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.GenerateMipmap;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.ShaderResource;
import gwt.g3d.resources.client.Texture2DResource;
import gwt.g3d.test.client.extras.Lesson13DemoExtra;

import javax.vecmath.Matrix4f;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-port for the lesson 13.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson13Demo extends AbstractLessonDemo {
	private static final int LESSON_NUMBER = 13;
	
	private final Lesson13DemoExtra extraWidget = new Lesson13DemoExtra();	
	private AbstractShader perFragmentShader, perVertexShader;
	private Texture2D moonTexture, crateTexture;
	private StaticMesh cubeMesh, moonMesh;
	private final Matrix4f nMatrix = new Matrix4f();
	
	private double lastTime;
	private float moonAngle, cubeAngle;
	
	protected Lesson13Demo() {
		super(LESSON_NUMBER);
		nMatrix.setIdentity();
	}
	
	@Override
	public void dispose() {
		moonMesh.dispose();
		cubeMesh.dispose();
		moonTexture.dispose();
		crateTexture.dispose();
		perFragmentShader.dispose();
		perVertexShader.dispose();
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
 
		if (extraWidget.isPerFragment()) {
			shader = perFragmentShader;
		} else {
			shader = perVertexShader;
		}
		shader.bind();
		
		PROJECTION.pushIdentity();
		PROJECTION.perspective(45, 1, .1f, 100);
		gl.uniformMatrix(shader.getUniformLocation("uPMatrix"), PROJECTION.get());
		PROJECTION.pop();
	
	  boolean lighting = extraWidget.isLighting();
    gl.uniform1i(shader.getUniformLocation("uUseLighting"), lighting ? 1 : 0);
    if (lighting) {
      gl.uniform(shader.getUniformLocation("uAmbientColor"), 
      		extraWidget.getAmbient()); 
      gl.uniform(shader.getUniformLocation("uPointLightingLocation"), 
      		extraWidget.getPosition());
      gl.uniform(shader.getUniformLocation("uPointLightingColor"),
      		extraWidget.getColor());
    }
    gl.uniform1i(shader.getUniformLocation("uUseTextures"), 
    		extraWidget.isTextures() ? 1 : 0);
	
    gl.activeTexture(TextureUnit.TEXTURE0);
    gl.uniform1i(shader.getUniformLocation("uSampler"), 0);
	  if (moonTexture != null) {
    	moonTexture.bind();
    }
		
		MODELVIEW.push();
    MODELVIEW.translate(0, 0, -5);
    MODELVIEW.rotateX((float) Math.toRadians(30));
    
    // Draws the moon.
    moonMesh.setPositionIndex(shader.getAttributeLocation("aVertexPosition"));
		moonMesh.setNormalIndex(shader.getAttributeLocation("aVertexNormal"));
		moonMesh.setTexCoordIndex(shader.getAttributeLocation("aTextureCoord"));
		
    MODELVIEW.push();
    MODELVIEW.rotateY((float) Math.toRadians(moonAngle));
    MODELVIEW.translate(2, 0, 0);
    setMatrixUniforms();
    moonMesh.draw();
    MODELVIEW.pop();
    
    // Draws the crate.
    if (crateTexture != null) {
    	crateTexture.bind();
    }
    cubeMesh.setPositionIndex(shader.getAttributeLocation("aVertexPosition"));
		cubeMesh.setNormalIndex(shader.getAttributeLocation("aVertexNormal"));
		cubeMesh.setTexCoordIndex(shader.getAttributeLocation("aTextureCoord"));
		
    MODELVIEW.push();
    MODELVIEW.rotateY((float) Math.toRadians(cubeAngle));
    MODELVIEW.translate(1.25f, 0, 0);
    setMatrixUniforms();
    cubeMesh.draw();
    MODELVIEW.pop();
    
    MODELVIEW.pop();
	  
	  double currTime = System.currentTimeMillis();
	  float elapsed = (float) (currTime - lastTime);
	  moonAngle += 0.05f * elapsed;
	  cubeAngle += 0.05f * elapsed;
	  lastTime = currTime;
	}

	
	@Override
	protected void initImpl() {
		try {
			perVertexShader = Resources.INSTANCE.perVertexShader().createShader(gl);
			perVertexShader.bind();
		} catch (ShaderException e) {
			Window.alert(e.getMessage());
			return;
		} 
		super.initImpl();
		perFragmentShader = shader;
		
		moonAngle = 180;
		cubeAngle = 0;
		
		moonMesh = new StaticMesh(gl, PrimitiveFactory.makeSphere(30, 30));
		cubeMesh = new StaticMesh(gl, PrimitiveFactory.makeBox());
		
		Resources.INSTANCE.moon().getTexture(new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				moonTexture = resource.createTexture(gl);
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Fail to load moon image.");
			}
		});
		
		Resources.INSTANCE.crate().getTexture(new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				crateTexture = resource.createTexture(gl);
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Fail to load crate image.");
			}
		});
		
		lastTime = System.currentTimeMillis();
	}
	
	private void setMatrixUniforms() {
    gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
    MODELVIEW.getInvertTranspose(nMatrix);
    gl.uniformMatrix(shader.getUniformLocation("uNMatrix"), nMatrix);
  }
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source({"shaders/lesson13.vp", "shaders/lesson13.fp"})
		ShaderResource shader();
		
		@Source({"shaders/lesson13vertex.vp", "shaders/lesson13vertex.fp"})
		ShaderResource perVertexShader();
		
		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();
		
		@Source("images/moon.gif")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		@GenerateMipmap
		ExternalTexture2DResource moon();
		
		@Source("images/crate.gif")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		ExternalTexture2DResource crate();
	}
}
