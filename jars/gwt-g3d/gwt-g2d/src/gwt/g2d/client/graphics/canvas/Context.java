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
package gwt.g2d.client.graphics.canvas;

import gwt.g2d.client.media.VideoElement;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;

/**
 * Represents a 2D context used for drawing vector graphics onto canvas.
 * 
 * This is inspired by Oliver Zoran's gwt-canvas.
 * 
 * @see <a href="http://www.whatwg.org/specs/web-apps/current-work/#the-canvas">
 *      http://www.whatwg.org/specs/web-apps/current-work/#the-canvas</a>
 * @see <a href="http://www.w3.org/html/wg/html5/#the-canvas">
 *      http://www.w3.org/html/wg/html5/#the-canvas</a>
 * @see <a href="http://canvex.lazyilluminati.com/tests/tests/results.html">
 *      http://canvex.lazyilluminati.com/tests/tests/results.html</a>
 *      
 * @author hao1300@gmail.com
 */
public interface Context {
	
	/**
	 * Adds points to the subpath such that the arc described by the 
	 * circumference of the circle described by the arguments, starting at the 
	 * given start angle and ending at the given end angle, going in the given 
	 * direction, is added to the path, connected to the previous point by a 
	 * straight line.
	 */
	void arc(double x, double y, double radius, double startAngle, 
			double endAngle, boolean antiClockwise);
  
	/**
	 * Let the point (x0, y0) be the last point in the subpath.
	 * <p>
	 * If the point (x0, y0) is equal to the point (x1, y1), or if the point 
	 * (x1, y1) is equal to the point (x2, y2), or if the radius radius is zero, 
	 * then the method must add the point (x1, y1) to the subpath, and connect 
	 * that point to the previous point (x0, y0) by a straight line.
	 * <p>
	 * Otherwise, if the points (x0, y0), (x1, y1), and (x2, y2) all lie on a 
	 * single straight line, then the method must add the point (x1, y1) to the 
	 * subpath, and connect that point to the previous point (x0, y0) by a 
	 * straight line.
	 * <p>
	 * Otherwise, let The Arc be the shortest arc given by circumference of the 
	 * circle that has radius radius, and that has one point tangent to the 
	 * half-infinite line that crosses the point (x0, y0) and ends at the point 
	 * (x1, y1), and that has a different point tangent to the half-infinite line 
	 * that ends at the point (x1, y1) and crosses the point (x2, y2). The points 
	 * at which this circle touches these two lines are called the start and end 
	 * tangent points respectively. The method must connect the point (x0, y0) to 
	 * the start tangent point by a straight line, adding the start tangent point 
	 * to the subpath, and then must connect the start tangent point to the end 
	 * tangent point by The Arc, adding the end tangent point to the subpath.
	 */
	void arcTo(double x1, double y1, double x2, double y2, double radius);
	
	/**
	 * Resets the current path.
	 */
	void beginPath();
	
	/**
	 * Adds the given point to the current path, connected to the previous one by 
	 * a cubic Bézier curve with the given control points.
	 */
	void bezierCurveTo(double cp1x, double cp1y, double cp2x, double cp2y, 
			double x, double y);
	
	/**
	 * Clears all pixels on the canvas to transparent black.
	 */
	void clear();
	
	/**
	 * Clears all pixels on the canvas in the given rectangle to transparent 
	 * black.
	 */
	void clearRect(double x, double y, double width, double height);
	
	/**
	 * Further constrains the clipping region to the given path.
	 */
	void clip();
	
	/**
	 * Marks the current subpath as closed, and starts a new subpath with a point 
	 * the same as the start and end of the newly closed subpath.
	 */
	void closePath();
	
	/**
	 * Instantiate new blank ImageData objects whose dimension is equal to
	 * the given imageData.
	 * 
	 * @param imageData
	 * @return a new ImageData object.
	 */
	ImageData createImageData(ImageData imageData);
	
	/**
	 * Instantiate new blank ImageData objects whose dimension is equal to
	 * width x height.
	 * 
	 * @param width
	 * @param height
	 * @return a new ImageData object.
	 */
	ImageData createImageData(int width, int height);
	
	/**
	 * Gets the adapter for a linear gradient between two points.
	 */
	CanvasGradient createLinearGradient(double x0, double y0, 
			double x1, double y1);
	
	/**
	 * Creates a CanvasPattern object that uses the given image and repeats in 
	 * the direction(s) given by the repetition argument.
	 * 
	 * @param image
	 * @param repetition
	 * @return a new CanvasPattern object.
	 */
	CanvasPattern createPattern(CanvasElement image, String repetition);
	
	/**
	 * Creates a CanvasPattern object that uses the given image and repeats in 
	 * the direction(s) given by the repetition argument.
	 * 
	 * @param image
	 * @param repetition
	 * @return a new CanvasPattern object.
	 */
	CanvasPattern createPattern(ImageElement image, String repetition);
	
	/**
	 * Creates a CanvasPattern object that uses the given image and repeats in 
	 * the direction(s) given by the repetition argument.
	 * 
	 * @param image
	 * @param repetition
	 * @return a new CanvasPattern object.
	 */
	CanvasPattern createPattern(VideoElement image, String repetition);
	
	/**
	 * Gets the adapter for a radial gradient.
	 */
	CanvasGradient createRadialGradient(double x0, double y0, 
			double radius0, double x1, double y1, double radius1);
	
	/**
	 * This is equivalent to calling drawFocusRing(element, x, y, false).
	 * @see #drawFocusRing(Element, double, double, boolean)
	 */
	boolean drawFocusRing(Element element, double x, double y);
	
	/**
	 * If the given element is focused, draws a focus ring around the current 
	 * path, following the platform conventions for focus rings. The given 
	 * coordinate is used if the user's attention needs to be brought to a 
	 * particular position (e.g. if a magnifier is following the editing caret 
	 * in a text field).
	 * <p>
	 * If the canDrawCustom argument is true, then the focus ring is only drawn 
	 * if the user has configured his system to draw focus rings in a particular 
	 * manner. (For example, high contrast focus rings.)
	 * <p>
	 * Returns true if the given element is focused, the canDrawCustom argument 
	 * is true, and the user has not configured his system to draw focus rings 
	 * in a particular manner. Otherwise, returns false.
	 * <p>
	 * When the method returns true, the author is expected to manually draw a 
	 * focus ring.
	 */
	boolean drawFocusRing(Element element, double x, double y, boolean canDrawCustom);
	
	/**
	 * Draws the given image onto the canvas.
	 */
	void drawImage(CanvasElement image, double x, double y);

	/**
	 * Draws the given image onto the canvas.
	 */
	void drawImage(CanvasElement image, double x, double y, 
			double width, double height);

	/**
	 * Draws the given image onto the canvas.
	 */
	void drawImage(CanvasElement image, 
			double sourceX, double sourceY, 
			double sourceWidth, double sourceHeight, 
			double destinationX, double destinationY, 
			double destinationWidth, double destinationHeight);
	
	/**
	 * Draws the given image onto the canvas.
	 */
	void drawImage(ImageElement image, double x, double y);

	/**
	 * Draws the given image onto the canvas.
	 */
	void drawImage(ImageElement image, double x, double y, 
			double width, double height);

	/**
	 * Draws the given image onto the canvas.
	 */
	void drawImage(ImageElement image, 
			double sourceX, double sourceY, 
			double sourceWidth, double sourceHeight, 
			double destinationX, double destinationY, 
			double destinationWidth, double destinationHeight);
	
	/**
	 * Draws the given image onto the canvas.
	 */
	void drawImage(VideoElement image, double x, double y);

	/**
	 * Draws the given image onto the canvas.
	 */
	void drawImage(VideoElement image, double x, double y, 
			double width, double height);

	/**
	 * Draws the given image onto the canvas.
	 */
	void drawImage(VideoElement image, 
			double sourceX, double sourceY, 
			double sourceWidth, double sourceHeight, 
			double destinationX, double destinationY, 
			double destinationWidth, double destinationHeight);
	
	/**
	 * Fills the subpaths with the current fill style.
	 */
	void fill();
	
	/**
	 * Paints the given rectangle onto the canvas, using the current fill style.
	 */
	void fillRect(double x, double y, double width, double height);
	
	/**
	 * Renders the given text at the given (x, y).
	 */
	void fillText(String text, double x, double y);
	
	/**
	 * Renders the given text at the given (x, y), ensuring that the text is
	 * not wider than maxWidth.
	 */
	void fillText(String text, double x, double y, double maxWidth);
	
	/**
	 * Gets the font settings.
	 */
	String getFont();
	
	/**
	 * Gets the current alpha value applied to rendering operations.
	 */
	double getGlobalAlpha();
	
	/**
	 * Gets the current composition operation.
	 */
	String getGlobalCompositeOperation();
	
	/**
	 * Returns an ImageData object representing the underlying pixel data for the 
	 * area of the canvas denoted by the rectangle whose corners are the four 
	 * points (x, y), (x + width, y), (x + width, y + height), (x, y + height), 
	 * in canvas coordinate space units. Pixels outside the canvas must be 
	 * returned as transparent black. Pixels must be returned as 
	 * non-premultiplied alpha values.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	ImageData getImageData(double x, double y, double width, double height);	
	
	/**
	 * Gets the current line cap style.
	 */
	String getLineCap();
	
	/**
	 * Gets the current line join style.
	 */
	String getLineJoin();
	
	/**
	 * Gets the current line width.
	 */
	double getLineWidth();
	
	/**
	 * Gets the current miter limit ratio.
	 */
	double getMiterLimit();
	
	/**
	 * Gets the size of the blurring effect.
	 */
	double getShadowBlur();
	
	/**
	 * Gets the color of the shadow.
	 */
	String getShadowColor();
	
	/**
	 * Gets the distance that the shadow will be offset in the positive
	 * horizontal direction.
	 */
	double getShadowOffsetX();
	
	/**
	 * Gets the distance that the shadow will be offset in the positive
	 * vertical direction.
	 */
	double getShadowOffsetY();
	
	/**
	 * Gets the text alignment settings.
	 */
	String getTextAlign();
	
	/**
	 * Gets the text baseline alignment settings.
	 */
	String getTextBaseline();
	
	/**
	 * Returns true if the given point is in the current path.
	 */
	boolean isPointInPath(double x, double y);
	
	/**
	 * Adds the given point to the current subpath, connected to the previous one 
	 * by a straight line.
	 */
	void lineTo(double x, double y);

	/**
	 * Returns the advance width with the metrics of the given text in the 
	 * current font.
	 */
	double measureText(String text);

	/**
	 * Creates a new subpath with the given point.
	 */
	void moveTo(double x, double y);
	
	/**
	 * <p>Paints the data from the given ImageData object onto the canvas. If a 
	 * dirty rectangle is provided, only the pixels from that rectangle are 
	 * painted.</p>
	 * <p>The globalAlpha and globalCompositeOperation attributes, as well as the 
	 * shadow attributes, are ignored for the purposes of this method call; 
	 * pixels in the canvas are replaced wholesale, with no composition, alpha 
	 * blending, no shadows, etc.</p>
	 * 
	 * @param imageData
	 * @param x
	 * @param y
	 * @param dirtyX
	 * @param dirtyY
	 * @param dirtyWidth
	 * @param dirtyHeight
	 */
	void putImageData(ImageData imageData, double x, double y, 
			double dirtyX, double dirtyY, double dirtyWidth, double dirtyHeight);
	
	/**
	 * Adds the given point to the current path, connected to the previous one by 
	 * a quadratic Bézier curve with the given control point.
	 */
	void quadraticCurveTo(double cpx, double cpy, double x, double y);

	/**
	 * Adds a new closed subpath to the path, representing the given rectangle.
	 */
	void rect(double x, double y, double w, double h);
	
	/**
	 * Pushes the current state onto the stack.
	 */
	void restore();
	
	/**
	 * Changes the transformation matrix to apply a rotation transformation with 
	 * the given characteristics.
	 */
	void rotate(double angle);
	
	/**
	 * Pushes the current state onto the stack.
	 */
	void save();
	
	/**
	 * Changes the transformation matrix to apply a scaling transformation with 
	 * the given characteristics.
	 */
	void scale(double x, double y);
	
	/**
	 * Sets the current style used for filling shapes.
	 */
	void setFillStyle(CanvasGradient gradient);
	
	/**
	 * Sets the current style used for filling shapes.
	 */
	void setFillStyle(CanvasPattern pattern);
	
	/**
	 * Sets the current style used for filling shapes
	 */
	void setFillStyle(String color);
	
	/**
	 * Sets the font settings. The syntax is the same as for the CSS 'font' 
	 * property; values that cannot be parsed as CSS font values are ignored.
	 */
	void setFont(String font);
	
	/**
	 * Sets the current alpha value applied to rendering operations.
	 */
	void setGlobalAlpha(double globalAlpha);
	
	/**
	 * Sets the current composite operation.
	 */
	void setGlobalCompositeOperation(String globalCompositeOperation);
	
	/**
	 * Sets the current line cap style.
	 */
	void setLineCap(String lineCap);
	
	/**
	 * Sets the current line join style.
	 */
	void setLineJoin(String lineJoin);
	
	/**
	 * Sets the current line width.
	 */
	void setLineWidth(double lineWidth);
	
	/**
	 * Sets the current miter limit ratio.
	 */
	void setMiterLimit(double miterLimit);
	
	/**
	 * Gets the size of the blurring effect.
	 * 
	 * @param shadowBlur
	 */
	void setShadowBlur(double shadowBlur);
	
	/**
	 * Sets the color of the shadow.
	 * 
	 * @param shadowColor
	 */
	void setShadowColor(String shadowColor);
	
	/**
	 * Sets the distance that the shadow will be offset in the positive 
	 * horizontal direction.
	 * 
	 * @param shadowOffsetX
	 */
	void setShadowOffsetX(double shadowOffsetX);
	
	/**
	 * Sets the distance that the shadow will be offset in the positive 
	 * vertical direction.
	 * 
	 * @param shadowOffsetY
	 */
	void setShadowOffsetY(double shadowOffsetY);
	
	/**
	 * Sets the current style used for stroking shapes.
	 */
	void setStrokeStyle(CanvasGradient gradient);
	
	/**
	 * Sets the current style used for stroking shapes.
	 */
	void setStrokeStyle(CanvasPattern pattern);
	
	/**
	 * Sets the current style used for stroking shapes.
	 */
	void setStrokeStyle(String color);
	
	/**
	 * Sets the text alignment settings.
	 */
	void setTextAlign(String textAlign);
	
	/**
	 * Sets the text baseline alignment settings.
	 */
	void setTextBaseline(String textBaseline);
	
	/**
	 * Resets the current transform to the identity matrix, and then invoke the 
	 *; method 
	 * with the same arguments.
	 */
	void setTransform(double m11, double m12, double m21, double m22, 
			double dx, double dy);
	
	/**
	 * Strokes the subpaths with the current stroke style.
	 */
	void stroke();
	
	/**
	 * Paints the box that outlines the given rectangle onto the canvas, using 
	 * the current stroke style.
	 */
	void strokeRect(double x, double y, double width, double height);
	
	/**
	 * Renders the given text at the given (x, y).
	 */
	void strokeText(String text, double x, double y);
	
	/**
	 * Renders the given text at the given (x, y), ensuring that the text is
	 * not wider than maxWidth.
	 */
	void strokeText(String text, double x, double y, double maxWidth);
	
	/**
	 * Changes the transformation matrix to apply the matrix given by the 
	 * arguments.
	 */
	void transform(double m11, double m12, double m21, double m22, 
			double dx, double dy);
	
	/**
	 * Changes the transformation matrix to apply a translation transformation 
	 * with the given characteristics.
	 */
	void translate(double x, double y);
}
