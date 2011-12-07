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
 * Represents a widget for manipulating an {@link AudioElement}
 * 
 * @author hao1300@gmail.com
 */
public class Audio extends Media {

	/**
	 * Creates a new audio element.
	 */
	public Audio() {
		super(Document.get().createElement("audio").<AudioElement>cast());
	}
	
	/**
	 * Creates a new audio element with the given src.
	 * 
	 * @param source
	 */
	public Audio(String source) {
		this();
		getAudioElement().setSrc(source);
	}

	/**
	 * Gets the audio element.
	 */
	public AudioElement getAudioElement() {
		return getElement().cast();
	}
}
