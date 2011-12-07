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

import java.util.Arrays;

/**
 * A color stop in a gradient.
 * 
 * @author hao1300@gmail.com
 */
public final class ColorStop {
	private final double offset;
	private final Color color;
	
	public ColorStop(double offset, Color color) {
		this.offset = offset;
		this.color = color;
	}
	
	public ColorStop(ColorStop rhs) {
		this(rhs.getOffset(), rhs.getColor());
	}
	
	/**
	 * Gets the offset of the color stop.
	 */
	public final double getOffset() {
		return offset;
	}
	
	/**
	 * Gets the color at the color stop.
	 */
	public final Color getColor() {
		return color;
	}
	
	@Override
	public final boolean equals(Object obj) {
		return (obj instanceof ColorStop) ? equals((ColorStop) obj) : false;
	}
	
	public final boolean equals(ColorStop colorStop) {
		return getOffset() == colorStop.getOffset() 
				&& colorStop.getColor().equals(colorStop.getColor());
	}
	
	@Override
	public final int hashCode() {
		return Arrays.hashCode(new double[]{getOffset(), getColor().hashCode()});
	}
}
