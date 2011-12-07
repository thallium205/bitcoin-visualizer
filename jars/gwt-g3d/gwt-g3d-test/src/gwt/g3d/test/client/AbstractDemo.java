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

import gwt.g3d.client.Surface3D;
import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.GLDisposable;

import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents an abstract demo for g3d. 
 * 
 * @author hao1300@gmail.com
 */
public abstract class AbstractDemo implements GLDisposable {
	protected GL2 gl;
	private Surface3D surface;
	private final String name;
	private final String reference;

	protected AbstractDemo(String name, String reference) {
		this.name = name;
		this.reference = reference;
	}
	
	/**
	 * Sets the GL surface.
	 * 
	 * @param surface
	 */
	public void setSurface(Surface3D surface) {
		this.surface = surface;
	}

	/**
	 * Gets the GL surface.
	 */
	public Surface3D getSurface() {
		return surface;
	}
	
	/**
	 * Initializes the demo.
	 * 
	 * @param gl
	 */
	public final void init(GL2 gl) {
		this.gl = gl;
		initImpl();
	}
	
	/**
	 * Override this method to perform initialization logics.
	 */
	protected abstract void initImpl();
	
	/**
	 * Updates the frame.
	 * Override this method to perform update per frame.
	 */
	public void update() {
	}

	/**
	 * Gets the name of the demo.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the URL to the reference, or null if no reference is used.
	 */
	public String getReference() {
		return reference;
	}
	
	/**
	 * Gets an extra-demo dependent widget to be put beneath the reference link.
	 * Override this method to return a demo-dependent widget, or null if none
	 * exists.
	 */
	public Widget getExtraWidget() {
		return null;
	}
	
	/**
	 * Gets the client bundle for loading resources.
	 */
	public abstract ClientBundleWithLookup getClientBundle();
}
