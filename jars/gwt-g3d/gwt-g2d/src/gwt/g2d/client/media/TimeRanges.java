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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Represent a list of ranges (periods) of time.
 * 
 * @author hao1300@gmail.com
 */
public class TimeRanges extends JavaScriptObject {

	protected TimeRanges() {

	}

	/**
	 * Gets the number of ranges in the object.
	 */
	public native final int getLength() /*-{
		return this.length;
	}-*/;
	
	
	/**
	 * Gets the time for the start of the range with the given index, in seconds 
	 * measured from the start of the timeline that the object covers.
	 * 
	 * @param index
	 */
  public native final float getStart(int index) /*-{
  	return this.start(index);
  }-*/;
  
  /**
   * Gets the time for the end of the range with the given index,  in seconds 
   * measured from the start of the timeline that the object covers.
   * 
   * @param index
   */
  public native final float getEnd(int index) /*-{
  	return this.end(index);
  }-*/;
}
