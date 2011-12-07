package gwt.g2d.client.graphics.shapes;

import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.canvas.Context;
import gwt.g2d.client.math.Circle;
import gwt.g2d.client.math.MathHelper;
import gwt.g2d.client.math.Vector2;

/**
 * Represents a circular shape.
 * 
 * @author hao1300@gmail.com
 */
public class CircleShape extends Shape {
	private double centerX, centerY, radius;
	
	public CircleShape(double centerX, double centerY, double radius) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}
	
	public CircleShape(Vector2 center, double radius) {
		this(center.getX(), center.getY(), radius);
	}
	
	public CircleShape(Circle circle) {
		this(circle.getCenter(), circle.getRadius());
	}

	@Override
	public void draw(Surface surface) {
		Context context = surface.getContext();
		context.beginPath();
		context.arc(centerX, centerY, radius, 0, MathHelper.TWO_PI, true);
		context.closePath();
	}
}
