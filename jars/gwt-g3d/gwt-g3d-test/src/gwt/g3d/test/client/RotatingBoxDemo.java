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
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.primitive.PrimitiveFactory;
import gwt.g3d.client.shader.LambertianShader;
import gwt.g3d.client.shader.ShaderException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.user.client.Window;

/**
 * A rotating box demo.
 * 
 * @author hao1300@gmail.com
 */
public class RotatingBoxDemo extends AbstractDemo {
	private final LambertianShader shader = new LambertianShader();
	private StaticMesh mesh;
	private float angle;
	
	public RotatingBoxDemo() {
		super("Rotating Box Demo", "");
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
		mesh.setNormalIndex(shader.getAttributeNormal());
		mesh.setTexCoordIndex(-1);
		
		shader.setDiffuseColor(.2f, .2f, .8f, 1f);
		shader.setLightPosition(0, 0, 5);
		
		PROJECTION.pushIdentity();
    PROJECTION.perspective(30, 1, 1, 10000);
    PROJECTION.lookAt(0, 0, 7, 0, 0, 0, 0, 1, 0);
    shader.setProjectionMatrix(PROJECTION.get());
    PROJECTION.pop();		
		
		mesh.draw();
	}
	
	@Override
	public void update() {
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);
		
		MODELVIEW.push();
		MODELVIEW.rotateY(angle -= .03f);
		MODELVIEW.rotateZ(.2f);
		shader.setModelViewMatrix(MODELVIEW.get());
		MODELVIEW.pop();
		
		mesh.draw();
	}
	
	@Override
	public void dispose() {
		shader.dispose();
		mesh.dispose();
	}
	
	@Override
	public ClientBundleWithLookup getClientBundle() {
		return Resources.INSTANCE;
	}
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source("RotatingBoxDemo.java")
		ExternalTextResource source();
	}
}
