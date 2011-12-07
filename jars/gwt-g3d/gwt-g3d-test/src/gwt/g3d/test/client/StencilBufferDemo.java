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
import gwt.g3d.client.gl2.enums.EnableCap;
import gwt.g3d.client.gl2.enums.StencilFunction;
import gwt.g3d.client.gl2.enums.StencilOp;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.primitive.MeshData;
import gwt.g3d.client.primitive.PrimitiveFactory;
import gwt.g3d.client.shader.LambertianShader;
import gwt.g3d.client.shader.ShaderException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.user.client.Window;

/**
 * A simple stencil buffer demo.
 * 
 * @author hao1300@gmail.com
 */
public class StencilBufferDemo extends AbstractDemo {
	private final LambertianShader shader = new LambertianShader();
	
	public StencilBufferDemo() {
		super("Stencil Buffer Demo", null);
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
		
		PROJECTION.pushIdentity();
		PROJECTION.perspective(45, 1, .1f, 100);
		shader.setProjectionMatrix(PROJECTION.get());
		PROJECTION.pop();
		
		shader.setDiffuseColor(1, 0.4f, 0.4f, 1);
		shader.setLightPosition(0, 2, 0);
		
		gl.clear(ClearBufferMask.STENCIL_BUFFER_BIT);

		// Uses stencil test to draw a plane to clip the upper half of the sphere.
		gl.colorMask(false, false, false, false);
		gl.enable(EnableCap.STENCIL_TEST);
		gl.stencilFunc(StencilFunction.ALWAYS, 1, 1);
		gl.stencilOp(StencilOp.KEEP, StencilOp.KEEP, StencilOp.REPLACE);
		gl.disable(EnableCap.DEPTH_TEST);
		MODELVIEW.push();
			MODELVIEW.translate(0, 1f, -3);
			drawPlane();
		MODELVIEW.pop();

		gl.enable(EnableCap.DEPTH_TEST);
		gl.colorMask(true, true, true, true);
		gl.stencilFunc(StencilFunction.EQUAL, 1, 1);
		gl.stencilOp(StencilOp.KEEP, StencilOp.KEEP, StencilOp.KEEP);

		MODELVIEW.push();
			MODELVIEW.translate(0, 0, -3);
			drawObject();
		MODELVIEW.pop();

		gl.disable(EnableCap.STENCIL_TEST);
	}

	@Override
	public void dispose() {
		shader.dispose();
	}
	
	private void drawObject() {
		shader.setModelViewMatrix(MODELVIEW.get());
		
		StaticMesh sphereMesh = new StaticMesh(gl, PrimitiveFactory.makeSphere(32, 16));
		sphereMesh.setPositionIndex(shader.getAttributePosition());
		sphereMesh.setNormalIndex(shader.getAttributeNormal());
		sphereMesh.draw();
		sphereMesh.dispose();
	}
	
	private void drawPlane() {
		shader.setModelViewMatrix(MODELVIEW.get());
		
		MeshData meshData = new MeshData(
				new float[] { -1, 1, 0, -1, -1, 0, 1, -1, 0, 1, 1, 0 },
				new int[] { 0, 1, 2, 0, 2, 3 },
				new float[] { 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1 },
				new float[] { 0, 1, 0, 0, 1, 0, 1, 1 }
				);
		StaticMesh floorMesh = new StaticMesh(gl, meshData);
		floorMesh.setPositionIndex(shader.getAttributePosition());
		floorMesh.setNormalIndex(shader.getAttributeNormal());
		floorMesh.draw();
		floorMesh.dispose();
	}
	
	@Override
	public ClientBundleWithLookup getClientBundle() {
		return Resources.INSTANCE;
	}
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source("StencilBufferDemo.java")
		ExternalTextResource source();
	}
}
