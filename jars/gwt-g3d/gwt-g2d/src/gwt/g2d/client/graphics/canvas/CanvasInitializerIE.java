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
package gwt.g2d.client.graphics.canvas;

import com.google.gwt.dom.client.Style.Unit;

/**
 * Helper class for initializing a canvas element for IE.
 * In case of IE8 and below, use excanvas wrapper.
 * 
 * @author hao1300@gmail.com
 */
public class CanvasInitializerIE extends CanvasInitializer {
	
	@Override
	public void init(CanvasElement element, int width, int height) {
		if (isUsingExcanvas()) {
			initExcanvas(element);
		}
		setWidth(element, width);
		setHeight(element, height);
	}
	
	@Override
	public void setWidth(CanvasElement element, int width) {
		if (isUsingExcanvas()) {
			element.getStyle().setWidth(width, Unit.PX);
		} else {
			super.setWidth(element, width);
		}
	}
	
	@Override
	public void setHeight(CanvasElement element, int height) {
		if (isUsingExcanvas()) {
			element.getStyle().setHeight(height, Unit.PX);
		} else {
			super.setHeight(element, height);
		}
	}
	
	/**
	 * Checks whether we should be using excanvas (false for IE9+).
	 * 
	 * @return
	 */
	private native boolean isUsingExcanvas() /*-{
		return $wnd.G_vmlCanvasManager !== null;
	}-*/;
	
	/**
	 * Initializing excanvas support for the canvas element.
	 * 
	 * @param element
	 */
	private native void initExcanvas(CanvasElement element) /*-{
		$wnd.G_vmlCanvasManager.initElement(element);
	}-*/;
}
