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
package gwt.g3d.client;

import gwt.g2d.client.graphics.canvas.CanvasElement;
import gwt.g2d.client.math.Rectangle;
import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.GL2Impl;
import gwt.g3d.client.gl2.WebGLContextAttributes;

import javax.vecmath.Point2i;
import javax.vecmath.Tuple2i;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.FocusWidget;

/**
 * A surface with WebGL support.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul class='css'>
 * <li>.g3d-Surface3D { }</li>
 * </ul>
 * 
 * @author hao1300@gmail.com
 */
public class Surface3D extends FocusWidget {
	private GL2 context;	
	private final CanvasElement canvas;
	private int width, height;
	
	/**
	 * Initialize a surface with a default size of 100 by 100
	 */
	public Surface3D() {
		this(100, 100);
	}
	
	/**
	 * Initialize a surface of the given size.
	 * 
	 * @param width width of the surface.
	 * @param height height of the surface.
	 */
	public Surface3D(int width, int height) {
		this(width, height, null);
	}
	
	/**
	 * Initialize a surface of the given size.
	 * 
	 * @param size the size of the surface to initialize.
	 */
	public Surface3D(Tuple2i size) {
		this(size.x, size.y, null);
	}
	
	/**
	 * Initialize a surface of the given size.
	 * 
	 * @param width width of the surface.
	 * @param height height of the surface.
	 */
	public Surface3D(int width, int height, WebGLContextAttributes attribs) {
		canvas = Document.get().createElement("canvas").cast();
		setElement(Document.get().createDivElement());
		getElement().appendChild(canvas);
		canvas.setWidth(width);
		canvas.setHeight(height);
		setStylePrimaryName("g2d-Surface3D");
		this.width = width;
		this.height = height;
		if (attribs == null) {
			context = canvas.getContext("experimental-webgl").<GL2Impl>cast();
		} else {
			context = canvas.getContext("experimental-webgl", 
					attribs.getGLContextAttributesImpl()).<GL2Impl>cast();
		}
	}
	
	/**
	 * Initialize a surface of the given size.
	 * 
	 * @param size the size of the surface to initialize.
	 * @param attribs attributes of the rendering context.
	 */
	public Surface3D(Tuple2i size, WebGLContextAttributes attribs) {
		this(size.x, size.y, attribs);
	}
	
	/**
	 * Gets the size of the surface.
	 */
	public Tuple2i getSize() {
		return new Point2i(getWidth(), getHeight());
	}

	/**
	 * Gets the width of the surface.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the surface.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Resizes the surface.
	 * 
	 * @param width the new width of the surface.
	 * @param height the new height of the surface.
	 */
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Sets the width of the surface.
	 */
	public void setWidth(int width) {
		this.width = width;
		canvas.setWidth(width);
	}
	
	/**
	 * Sets the height of the surface.
	 */
	public void setHeight(int height) {
		this.height = height;
		canvas.setHeight(height);
	}
	
	/**
	 * Gets the rectangle that encloses this surface.
	 */
	public Rectangle getViewRectangle() {
		return new Rectangle(0, 0, getWidth(), getHeight());
	}

	/**
	 * Gets the canvas element.
	 * 
	 * @return the underlying canvas element.
	 */
	public CanvasElement getCanvas() {
		return canvas;
	}
	
	/**
	 * Gets the WebGL context.
	 * 
	 * @return the underlying context implementation for drawing onto the canvas,
	 * 				 or null if no context implementation is available.
	 */
	public GL2 getGL() {
		return context;
	}
}
