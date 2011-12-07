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



import com.google.gwt.dom.client.Document;

/**
 * Represents a widget for manipulating an {@link VideoElement}.
 * 
 * @author hao1300@gmail.com
 */
public class Video extends Media {

	/**
	 * Creates a new audio element.
	 */
	public Video() {
		super(Document.get().createElement("video").<VideoElement>cast());
	}
	
	/**
	 * Creates a new audio element with the given src.
	 * 
	 * @param source
	 */
	public Video(String source) {
		this();
		getVideoElement().setSrc(source);
	}
	
	/**
	 * Gets the video element.
	 */
	public VideoElement getVideoElement() {
		return getElement().cast();
	}
	
	/**
	 * Gets the height of the video element in pixels.
	 */
	public int getHeight() {
		return getVideoElement().getHeight();
	}

	/**
	 * Gets the address of an image file that the user agent can show while no
	 * video data is available.
	 */
	public String getPoster() {
		return getVideoElement().getPoster();
	}

	/**
	 * Gets the intrinsic width of the video in CSS pixels.
	 */
	public int getVideoHeight() {
		return getVideoElement().getVideoHeight();
	}

	/**
	 * Gets the intrinsic width of the video in CSS pixels.
	 */
	public int getVideoWidth() {
		return getVideoElement().getVideoWidth();
	}

	/**
	 * Gets the width of the video element in pixels.
	 */
	public int getWidth() {
		return getVideoElement().getWidth();
	}

	/**
	 * Sets the height of the video element in pixels.
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		getVideoElement().setHeight(height);
	}

	/**
	 * Sets the address of an image file that the user agent can show while no
	 * video data is available.
	 * 
	 * @param poster
	 */
	public void setPoster(String poster) {
		getVideoElement().setPoster(poster);
	}

	/**
	 * Sets the width of the video element in pixels.
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		getVideoElement().setWidth(width);
	}
}
