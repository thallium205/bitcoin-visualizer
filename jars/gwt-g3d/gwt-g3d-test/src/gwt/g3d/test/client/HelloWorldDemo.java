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

import gwt.g3d.client.camera.AbstractCamera;
import gwt.g3d.client.camera.BasicCamera;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.primitive.PrimitiveFactory;
import gwt.g3d.client.shader.FlatShader;
import gwt.g3d.client.shader.ShaderException;

import javax.vecmath.Matrix4f;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.user.client.Window;

/**
 * A very simple FlatShader shader demo.
 * 
 * @author hao1300@gmail.com
 */
public class HelloWorldDemo extends AbstractDemo {
	private final FlatShader shader = new FlatShader();
	private StaticMesh mesh;
	
	public HelloWorldDemo() {
		super("Hello World Demo", null);
	}
	
	@Override
	protected void initImpl() {
		try {
			shader.init(gl);
		} catch (ShaderException e) {
			Window.alert(e.getMessage());
			return;
		}
    
    mesh = new StaticMesh(gl, PrimitiveFactory.makeSphere(30, 30));
		shader.bind();
		
		mesh.setPositionIndex(shader.getAttributePosition());
		shader.setColor(1, 1, 1, 1);

		Matrix4f mvMatrix = new Matrix4f();
		mvMatrix.setIdentity();
		shader.setModelViewMatrix(mvMatrix);

		AbstractCamera camera = new BasicCamera(30, 1, 1, 10000);
		camera.setEye(0, 0, 7);
		camera.setTarget(0, 0, 0);
		camera.setUp(0, 1, 0);
		shader.setProjectionMatrix(camera.getViewProjectionMatrix());
		
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

		@Source("HelloWorldDemo.java")
		ExternalTextResource source();
	}
}
