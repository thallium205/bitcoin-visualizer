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
package gwt.g2d.client.graphics;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.UIObject;

/**
 * Helper class for measure the size of a text in pixels.
 * 
 * @author hao1300@gmail.com
 * @author Bulatju (contributor)
 */
public abstract class TextMeasurer {
	/** Measures the text's advance size using the font's property. */
	public static final TextMeasurer DEFAULT_TEXT_MEASURER = 
			new FontBoundedTextMeasurer();
	
	protected TextMeasurer() {
	}
	
	/**
	 * Returns the advance width with the metrics of the text.
	 * 
	 * @param surface the surface where the text are to be rendered.
	 * @param text the text to be rendered.
	 */
	public abstract double measureTextWidth(Surface surface, String text); 
	
	/**
	 * Returns the advance height of the text in pixel when it is rendered
	 * in the given surface.
	 * 
	 * @param surface the surface where the text are to be rendered.
	 * @param text the text to be rendered.
	 */
	public abstract double measureTextHeight(Surface surface, String text);
	
	/**
	 * Measures the text's advance size using the property of its font.
	 */
	private static class FontBoundedTextMeasurer extends TextMeasurer {
		// Hidden html tag used for measuring the of the text.
		private Map<Surface, Element> hiddenHtml;
		
		@Override
		public double measureTextWidth(Surface surface, String text) {
			return surface.measureText(text);
		}
		
		@Override
		public double measureTextHeight(Surface surface, String text) {
			if (hiddenHtml == null) {
				hiddenHtml = new HashMap<Surface, Element>();
			}
			// Caches the element for measuring height so we don't need to 
			// reattach it every time.
			Element elem = hiddenHtml.get(surface);
			if (elem == null) {
				elem = DOM.createDiv();
				elem.setInnerText("o");
				Style style = elem.getStyle();
				style.setProperty("margin", "0px");
				style.setProperty("border", "0px");
				style.setProperty("padding", "0px");
				surface.getElement().appendChild(elem);
				hiddenHtml.put(surface, elem);
			}
			UIObject.setVisible(elem, true);
			// All characters in the same font have the same height property, so we 
			// only need to use an arbitrary character to figure out the height, 
			// avoiding performance hit caused by long text.
			elem.getStyle().setProperty("font", surface.getFont());
			double height = elem.getOffsetHeight();
			UIObject.setVisible(elem, false);
			return height;
		}
	}
}
