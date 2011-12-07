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
 * Represents an element that is used for playing videos or movies.
 * 
 * @author hao1300@gmail.com
 */
@TagName(VideoElement.TAG)
public class VideoElement extends MediaElement {

	static final String TAG = "video";

	/**
	 * Assert that the given {@link Element} is compatible with this class and
	 * automatically typecast it.
	 */
	public static VideoElement as(Element elem) {
		assert elem.getTagName().equalsIgnoreCase(TAG);
		return elem.cast();
	}

	protected VideoElement() {
	}

	/**
	 * Gets the height of the video element in pixels.
	 */
	public native final int getHeight() /*-{
		return this.height;
	}-*/;

	/**
	 * Gets the address of an image file that the user agent can show while no
	 * video data is available.
	 */
	public native final String getPoster() /*-{
		return this.poster;
	}-*/;

	/**
	 * Gets the intrinsic width of the video in CSS pixels.
	 */
	public native final int getVideoHeight() /*-{
		return this.videoHeight;
	}-*/;

	/**
	 * Gets the intrinsic width of the video in CSS pixels.
	 */
	public native final int getVideoWidth() /*-{
		return this.videoWidth;
	}-*/;

	/**
	 * Gets the width of the video element in pixels.
	 */
	public native final int getWidth() /*-{
		return this.width;
	}-*/;

	/**
	 * Sets the height of the video element in pixels.
	 * 
	 * @param height
	 */
	public native final void setHeight(int height) /*-{
		this.height = height;
	}-*/;

	/**
	 * Sets the address of an image file that the user agent can show while no
	 * video data is available.
	 * 
	 * @param poster
	 */
	public native final void setPoster(String poster) /*-{
		this.poster = poster;
	}-*/;

	/**
	 * Sets the width of the video element in pixels.
	 * 
	 * @param width
	 */
	public native final void setWidth(int width) /*-{
		this.width = width;
	}-*/;
}
