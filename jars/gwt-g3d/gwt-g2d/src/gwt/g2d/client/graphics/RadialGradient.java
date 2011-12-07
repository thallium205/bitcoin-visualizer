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

import gwt.g2d.client.graphics.canvas.Context;
import gwt.g2d.client.graphics.canvas.CanvasGradient;
import gwt.g2d.client.math.Circle;
import gwt.g2d.client.math.Vector2;

/**
 * Represents a radial gradient that paints along the cone given by the circles.
 * 
 * @author hao1300@gmail.com
 */
public class RadialGradient extends Gradient {
	private double x0, y0, radius0, x1, y1, radius1;
	
	/**
	 * Creates a radial gradient from the circle (x0, y0, radius0) to the circle
	 * (x1, y1, radius1).
	 */
	public RadialGradient(double x0, double y0, double radius0, 
			double x1, double y1, double radius1) {
		this.x0 = x0;
		this.y0 = y0;
		this.radius0 = radius0;
		this.x1 = x1;
		this.y1 = y1;
		this.radius1 = radius1;
	}
	
	/**
	 * Creates a radial gradient from position0 with radius radius0 to position1 
	 * with radius radius1.
	 */
	public RadialGradient(Vector2 position0, double radius0, 
			Vector2 position1, double radius1) {
		this(position0.getX(), position0.getY(), radius0, 
				position1.getX(), position1.getY(), radius1);
	}
	
	/**
	 * Creates a radial gradient from circle0 to circle1.
	 */
	public RadialGradient(Circle circle0, Circle circle1) {
		this(circle0.getCenter(), circle0.getRadius(), 
				circle1.getCenter(), circle1.getRadius());
	}
	
	@Override
	public final CanvasGradient createGradientAdapter(Context context) {
		return CanvasGradient.as(context.createRadialGradient(x0, y0, radius0, 
				x1, y1, radius1));
	}
}
