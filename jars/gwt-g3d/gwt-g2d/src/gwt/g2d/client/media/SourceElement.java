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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

/**
 * Represents a source element.
 * 
 * @author hao1300@gmail.com
 */
@TagName(AudioElement.TAG)
public class SourceElement extends Element {
	static final String TAG = "source";

	/**
	 * Assert that the given {@link Element} is compatible with this class and
	 * automatically typecast it.
	 */
	public static SourceElement as(Element elem) {
		assert elem.getTagName().equalsIgnoreCase(TAG);
		return elem.cast();
	}

	protected SourceElement() {

	}

	/**
	 * Gets the address of the media resource.
	 */
	public native final String getSrc() /*-{
		return this.src;
	}-*/;

	/**
	 * Gets the type of the media resource, to help the user agent determine if it
	 * can play this media resource before fetching it.
	 */
	public native final String getType() /*-{
		return this.type;
	}-*/;

	/**
	 * Gets the intended media type of the media resource, to help the user agent 
	 * determine if this media resource is useful to the user before fetching it. 
	 * Its value must be a valid media query.
	 */
	public native final String getMedia() /*-{
		return this.media;
	}-*/;

	/**
	 * Sets the address of the media resource.
	 */
	public native final void setSrc(String src) /*-{
		this.src = src;
	}-*/;

	/**
	 * Sets the type of the media resource, to help the user agent determine if it
	 * can play this media resource before fetching it. If specified, its value
	 * must be a valid MIME type. The codecs parameter may be specified and might
	 * be necessary to specify exactly how the resource is encoded.
	 * 
	 * @param type
	 */
	public native final void setType(String type) /*-{
		this.type = type;
	}-*/;

	/**
	 * Sets the intended media type of the media resource, to help the user agent 
	 * determine if this media resource is useful to the user before fetching it. 
	 * Its value must be a valid media query.
	 * 
	 * The default, if the media attribute is omitted, is "all", meaning that by 
	 * default styles apply to all media.
	 */
	public native final void setMedia(String media) /*-{
		this.media = media;
	}-*/;
}
