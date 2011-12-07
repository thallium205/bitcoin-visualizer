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
package gwt.g2d.client.media;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents a widget that wraps a {@link SourceElement}.
 * 
 * @author hao1300@gmail.com
 */
public class Source extends Widget {

	public Source() {
		setElement(DOM.createElement("source"));
	}
	
	public Source(String src) {
		this();
		getSourceElement().setSrc(src);
	}
	
	/**
	 * Gets the source element.
	 */
	public SourceElement getSourceElement() {
		return getElement().cast();
	}
	
	/**
	 * Gets the address of the media resource.
	 */
	public String getSrc() {
		return getSourceElement().getSrc();
	}

	/**
	 * Gets the type of the media resource, to help the user agent determine if it
	 * can play this media resource before fetching it.
	 */
	public String getType() {
		return getSourceElement().getType();
	}

	/**
	 * Gets the intended media type of the media resource, to help the user agent 
	 * determine if this media resource is useful to the user before fetching it. 
	 * Its value must be a valid media query.
	 */
	public String getMedia() {
		return getSourceElement().getMedia();
	}

	/**
	 * Sets the address of the media resource.
	 */
	public void setSrc(String src) {
		getSourceElement().setSrc(src);
	}

	/**
	 * Sets the type of the media resource, to help the user agent determine if it
	 * can play this media resource before fetching it. If specified, its value
	 * must be a valid MIME type. The codecs parameter may be specified and might
	 * be necessary to specify exactly how the resource is encoded.
	 * 
	 * @param type
	 */
	public void setType(String type) {
		getSourceElement().setType(type);
	}

	/**
	 * Sets the intended media type of the media resource, to help the user agent 
	 * determine if this media resource is useful to the user before fetching it. 
	 * Its value must be a valid media query.
	 * 
	 * The default, if the media attribute is omitted, is "all", meaning that by 
	 * default styles apply to all media.
	 */
	public void setMedia(String media) {
		getSourceElement().setMedia(media);
	}
}
