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
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalMeshResource;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MeshResource;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.NormalArrayInfo;
import gwt.g3d.resources.client.PositionArrayInfo;
import gwt.g3d.resources.client.ShaderResource;
import gwt.g3d.resources.client.TexCoordArrayInfo;
import gwt.g3d.resources.client.Texture2DResource;
import gwt.g3d.test.client.extras.Lesson14DemoExtra;
import gwt.g3d.test.client.extras.Lesson14DemoExtra.TextureType;

import javax.vecmath.Matrix4f;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-port for the lesson 14.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson14Demo extends AbstractLessonDemo {
	private static final int LESSON_NUMBER = 14;
	
	private final Lesson14DemoExtra extraWidget = new Lesson14DemoExtra();
	private StaticMesh mesh;
	private Texture2D earthTexture, galvanizedTexture;
	private final Matrix4f nMatrix = new Matrix4f();
	
	private double lastTime;
	private float teapotAngle;
	
	protected Lesson14Demo() {
		super(LESSON_NUMBER);
		nMatrix.setIdentity();
	}
	
	@Override
	public void dispose() {
		shader.dispose();
		if (mesh != null) {
			mesh.dispose();
			mesh = null;
		}
		if (earthTexture != null) {
			earthTexture.dispose();
			earthTexture = null;
		}
		if (galvanizedTexture != null) {
			galvanizedTexture.dispose();
			galvanizedTexture = null;
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
	
		if (mesh == null) {
			return;
		}
		
		gl.uniform1i(shader.getUniformLocation("uShowSpecularHighlights"), 
				extraWidget.isUseSpecular() ? 1 : 0);
		
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

    if (extraWidget.getTextureType() == TextureType.NONE) {
    	gl.uniform1i(shader.getUniformLocation("uUseTextures"), 0);
    } else {
    	gl.uniform1i(shader.getUniformLocation("uUseTextures"), 1);
    	gl.activeTexture(TextureUnit.TEXTURE0);
    	switch (extraWidget.getTextureType()) {
    		case GALVANIZED:
    			if (galvanizedTexture != null) {
    				galvanizedTexture.bind();
    			}
    			break;
    		case EARTH:
    			if (earthTexture != null) {
    				earthTexture.bind();
    			}
    			break;
    	}
    	gl.uniform1i(shader.getUniformLocation("uSampler"), 0);
    }
    gl.uniform1f(shader.getUniformLocation("uMaterialShininess"), extraWidget.getShininess());
    
 
    MODELVIEW.pushIdentity();
    MODELVIEW.translate(0, 0, -40);
    MODELVIEW.rotate((float) Math.toRadians(23.4), 1, 0, -1);
    MODELVIEW.rotateY((float) Math.toRadians(teapotAngle));
    setMatrixUniforms();
    mesh.draw();
    MODELVIEW.pop();
 
		double currTime = System.currentTimeMillis();
	  float elapsed = (float) (currTime - lastTime);
	  teapotAngle += 0.05f * elapsed;
	  lastTime = currTime;
	}

	
	@Override
	protected void initImpl() {
		super.initImpl();
		
		teapotAngle = 0;
		
		PROJECTION.pushIdentity();
		PROJECTION.perspective(45, 1, .1f, 100);
		gl.uniformMatrix(shader.getUniformLocation("uPMatrix"), PROJECTION.get());
		PROJECTION.pop();
		
		Resources.INSTANCE.teapot().getMesh(new ResourceCallback<MeshResource>() {
			@Override
			public void onSuccess(MeshResource resource) {
				mesh = resource.createMesh(gl);
				mesh.setPositionIndex(shader.getAttributeLocation("aVertexPosition"));
				mesh.setNormalIndex(shader.getAttributeLocation("aVertexNormal"));
				mesh.setTexCoordIndex(shader.getAttributeLocation("aTextureCoord"));
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert(e.getMessage());
			}
		});
		
		Resources.INSTANCE.galvanized().getTexture(new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				galvanizedTexture = resource.createTexture(gl);
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
	
	private void setMatrixUniforms() {
    gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
    MODELVIEW.getInvertTranspose(nMatrix);
    gl.uniformMatrix(shader.getUniformLocation("uNMatrix"), nMatrix);
  }
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source({"shaders/lesson13.vp", "shaders/lesson14.fp"})
		ShaderResource shader();
		
		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();
		
		@Source("models/teapon.json")
		@PositionArrayInfo(dataSize = 3)
		@NormalArrayInfo(dataSize = 3)
		@TexCoordArrayInfo(dataSize = 2)
		ExternalMeshResource teapot();
		
		@Source("images/earth.jpg")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		ExternalTexture2DResource earth();
		
		@Source("images/galvanized.jpg")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		ExternalTexture2DResource galvanized();
	}
}
