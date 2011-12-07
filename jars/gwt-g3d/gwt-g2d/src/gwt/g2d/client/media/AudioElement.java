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
 * Represents a sound or audio stream.
 * 
 * @author hao1300@gmail.com
 */
@TagName(AudioElement.TAG)
public class AudioElement extends MediaElement {

	static final String TAG = "audio";
	
	/**
   * Assert that the given {@link Element} is compatible with this class and
   * automatically typecast it.
   */
  public static AudioElement as(Element elem) {
    assert elem.getTagName().equalsIgnoreCase(TAG);
    return elem.cast();
  }
  
	protected AudioElement() {
		
	}
}
