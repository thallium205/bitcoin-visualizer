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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;

/**
 * Default implementation of the 2D context.
 * 
 * This is inspired by Oliver Zoran's gwt-canvas.
 * 
 * @author hao1300@gmail.com
 */
public final class ContextImpl extends JavaScriptObject implements Context {

	public static ContextImpl as(JavaScriptObject jsContext) {
		return jsContext.cast();
	}

	protected ContextImpl() {
	}

	@Override
	public native void arc(double x, double y, double radius, double startAngle,
			double endAngle, boolean antiClockwise) /*-{
		this.arc(x, y, radius, startAngle, endAngle, antiClockwise);
	}-*/;

	@Override
	public native void arcTo(double x1, double y1, double x2, double y2,
			double radius) /*-{
		this.arcTo(x1, y1, x2, y2, radius);
	}-*/;

	@Override
	public native void beginPath() /*-{
		this.beginPath();
	}-*/;

	@Override
	public native void bezierCurveTo(double cp1x, double cp1y, double cp2x,
			double cp2y, double x, double y) /*-{
		this.bezierCurveTo(cp1x, cp1y, cp2x, cp2y, x, y);
	}-*/;

	@Override
	public native void clear() /*-{
		this.clearRect(-1e4, -1e4, 2e4, 2e4);
	}-*/;

	@Override
	public native void clearRect(double x, double y, double w, double h) /*-{
		this.clearRect(x, y, w, h);
	}-*/;

	@Override
	public native void clip() /*-{
		this.clip();
	}-*/;

	@Override
	public native void closePath() /*-{
		this.closePath();
	}-*/;

	@Override
	public ImageData createImageData(ImageData imageData) {
		return this.createImageData(imageData);
	}

	@Override
	public native ImageData createImageData(int width, int height) /*-{
		return this.createImageData(width, height);
	}-*/;

	@Override
	public native CanvasGradient createLinearGradient(double x0, double y0,
			double x1, double y1) /*-{
		return this.createLinearGradient(x0, y0, x1, y1);
	}-*/;

	@Override
	public native CanvasPattern createPattern(CanvasElement image,
			String repetition) /*-{
		return this.createPattern(image, repetition);
	}-*/;

	@Override
	public native CanvasPattern createPattern(ImageElement image,
			String repetition) /*-{
		return this.createPattern(image, repetition);
	}-*/;

	@Override
	public native CanvasPattern createPattern(VideoElement image,
			String repetition) /*-{
		return this.createPattern(image, repetition);
	}-*/;

	@Override
	public native CanvasGradient createRadialGradient(double x0, double y0,
			double r0, double x1, double y1, double r1) /*-{
		return this.createRadialGradient(x0, y0, r0, x1, y1, r1);
	}-*/;

	@Override
	public native boolean drawFocusRing(Element element, double x, double y) /*-{
		return drawFocusRing(element, x, y);
	}-*/;

	@Override
	public native boolean drawFocusRing(Element element, double x, double y,
			boolean canDrawCustom) /*-{
		return drawFocusRing(element, x, y, canDrawCustom);
	}-*/;

	@Override
	public void drawImage(CanvasElement image, double x, double y) {
		drawImageImpl(image, x, y);
	}

	@Override
	public void drawImage(CanvasElement image, double x, double y, double width,
			double height) {
		drawImageImpl(image, x, y, width, height);
	}

	@Override
	public void drawImage(CanvasElement image, double sx, double sy,
			double sWidth, double sHeight, double dx, double dy, double dWidth,
			double dHeight) {
		drawImageImpl(image, sx, sy, sWidth, sHeight, dx, dy, dWidth, dHeight);
	}

	public void drawImage(ImageElement image, double x, double y) {
		drawImageImpl(image, x, y);
	}

	public void drawImage(ImageElement image, double x, double y, double width,
			double height) {
		drawImageImpl(image, x, y, width, height);
	}

	@Override
	public void drawImage(ImageElement image, double sx, double sy,
			double sWidth, double sHeight, double dx, double dy, double dWidth,
			double dHeight) {
		drawImageImpl(image, sx, sy, sWidth, sHeight, dx, dy, dWidth, dHeight);
	}

	public void drawImage(VideoElement image, double x, double y) {
		drawImageImpl(image, x, y);
	}

	public void drawImage(VideoElement image, double x, double y, double width,
			double height) {
		drawImageImpl(image, x, y, width, height);
	}

	@Override
	public void drawImage(VideoElement image, double sx, double sy,
			double sWidth, double sHeight, double dx, double dy, double dWidth,
			double dHeight) {
		drawImageImpl(image, sx, sy, sWidth, sHeight, dx, dy, dWidth, dHeight);
	}

	@Override
	public native void fill() /*-{
		this.fill();
	}-*/;

	@Override
	public native void fillRect(double x, double y, double w, double h) /*-{
		this.fillRect(x, y, w, h);
	}-*/;

	@Override
	public native void fillText(String text, double x, double y) /*-{
		this.fillText(text, x, y);
	}-*/;

	@Override
	public native void fillText(String text, double x, double y, double maxWidth) /*-{
		this.fillText(text, x, y, maxWidth);
	}-*/;

	@Override
	public native String getFont() /*-{
		return this.font;
	}-*/;

	@Override
	public native double getGlobalAlpha() /*-{
		return this.globalAlpha;
	}-*/;

	@Override
	public native String getGlobalCompositeOperation() /*-{
		return this.globalCompositeOperation;
	}-*/;

	@Override
	public native ImageData getImageData(double x, double y, double width,
			double height) /*-{
		return this.getImageData(x, y, width, height);
	}-*/;

	@Override
	public native String getLineCap() /*-{
		return this.lineCap;
	}-*/;

	@Override
	public native String getLineJoin() /*-{
		return this.lineJoin;
	}-*/;

	@Override
	public native double getLineWidth() /*-{
		return this.lineWidth;
	}-*/;

	@Override
	public native double getMiterLimit() /*-{
		return this.miterLimit;
	}-*/;

	@Override
	public native double getShadowBlur() /*-{
		return this.shadowBlur;
	}-*/;

	@Override
	public native String getShadowColor() /*-{
		return this.shadowColor;
	}-*/;

	@Override
	public native double getShadowOffsetX() /*-{
		return this.shadowOffsetX;
	}-*/;

	@Override
	public native double getShadowOffsetY() /*-{
		return this.shadowOffsetY;
	}-*/;

	@Override
	public native String getTextAlign() /*-{
		return this.textAlign;
	}-*/;

	@Override
	public native String getTextBaseline() /*-{
		return this.textBaseline;
	}-*/;

	@Override
	public native boolean isPointInPath(double x, double y) /*-{
		return this.isPointInPath(x, y);
	}-*/;

	@Override
	public native void lineTo(double x, double y) /*-{
		this.lineTo(x, y);
	}-*/;

	@Override
	public native double measureText(String text) /*-{
		return this.measureText(text).width;
	}-*/;

	@Override
	public native void moveTo(double x, double y) /*-{
		this.moveTo(x, y);
	}-*/;

	@Override
	public native void putImageData(ImageData imageData, double x, double y,
			double dirtyX, double dirtyY, double dirtyWidth, double dirtyHeight) /*-{
		this.putImageData(imageData, x, y);
		// dirtyX, dirtyY, dirtyWidth, dirtyHeight are not used because no browser
		// supports them yet.
	}-*/;

	@Override
	public native void quadraticCurveTo(double cpx, double cpy, double x, double y) /*-{
		this.quadraticCurveTo(cpx, cpy, x, y);
	}-*/;

	@Override
	public native void rect(double x, double y, double w, double h) /*-{
		this.rect(x, y, w, h);
	}-*/;

	@Override
	public native void restore() /*-{
		this.restore();
	}-*/;

	@Override
	public native void rotate(double angle) /*-{
		this.rotate(angle);
	}-*/;

	@Override
	public native void save() /*-{
		this.save();
	}-*/;

	@Override
	public native void scale(double x, double y) /*-{
		this.scale(x, y);
	}-*/;

	@Override
	public native void setFillStyle(CanvasGradient fillStyle) /*-{
		this.fillStyle = fillStyle;
	}-*/;

	@Override
	public native void setFillStyle(CanvasPattern fillStyle) /*-{
		this.fillStyle = fillStyle;
	}-*/;

	@Override
	public native void setFillStyle(String fillStyle) /*-{
		this.fillStyle = fillStyle;
	}-*/;

	@Override
	public native void setFont(String font) /*-{
		this.font = font;
	}-*/;

	@Override
	public native void setGlobalAlpha(double globalAlpha) /*-{
		this.globalAlpha = globalAlpha;
	}-*/;

	@Override
	public native void setGlobalCompositeOperation(String globalCompositeOperation) /*-{
		this.globalCompositeOperation = globalCompositeOperation;
	}-*/;

	@Override
	public native void setLineCap(String lineCap) /*-{
		this.lineCap = lineCap;
	}-*/;

	@Override
	public native void setLineJoin(String lineJoin) /*-{
		this.lineJoin = lineJoin;
	}-*/;

	@Override
	public native void setLineWidth(double lineWidth) /*-{
		this.lineWidth = lineWidth;
	}-*/;

	@Override
	public native void setMiterLimit(double miterLimit) /*-{
		this.miterLimit = miterLimit;
	}-*/;

	@Override
	public native void setShadowBlur(double shadowBlur) /*-{
		this.shadowBlur = shadowBlur;
	}-*/;

	@Override
	public native void setShadowColor(String shadowColor) /*-{
		this.shadowColor = shadowColor;
	}-*/;

	@Override
	public native void setShadowOffsetX(double shadowOffsetX) /*-{
		this.shadowOffsetX = shadowOffsetX;
	}-*/;

	@Override
	public native void setShadowOffsetY(double shadowOffsetY) /*-{
		this.shadowOffsetY = shadowOffsetY;
	}-*/;

	@Override
	public native void setStrokeStyle(CanvasGradient strokeStyle) /*-{
		this.strokeStyle = strokeStyle;
	}-*/;

	@Override
	public native void setStrokeStyle(CanvasPattern strokeStyle) /*-{
		this.strokeStyle = strokeStyle;
	}-*/;

	@Override
	public native void setStrokeStyle(String strokeStyle) /*-{
		this.strokeStyle = strokeStyle;
	}-*/;

	@Override
	public native void setTextAlign(String textAlign) /*-{
		this.textAlign = textAlign;
	}-*/;

	@Override
	public native void setTextBaseline(String textBaseline) /*-{
		this.textBaseline = textBaseline;
	}-*/;

	@Override
	public native void setTransform(double m11, double m12, double m21,
			double m22, double dx, double dy) /*-{
		this.setTransform(m11, m12, m21, 
				m22, dx, dy);
	}-*/;

	@Override
	public native void stroke() /*-{
		this.stroke();
	}-*/;

	@Override
	public native void strokeRect(double x, double y, double w, double h) /*-{
		this.strokeRect(x, y, w, h);
	}-*/;

	@Override
	public native void strokeText(String text, double x, double y) /*-{
		this.strokeText(text, x, y);
	}-*/;

	@Override
	public native void strokeText(String text, double x, double y, double maxWidth) /*-{
		this.strokeText(text, x, y, maxWidth);
	}-*/;

	@Override
	public native void transform(double m11, double m12, double m21, double m22,
			double dx, double dy) /*-{
		this.transform(m11, m12, m21, m22, dx, dy);
	}-*/;

	@Override
	public native void translate(double x, double y) /*-{
		this.translate(x, y);
	}-*/;

	private native void drawImageImpl(Element image, double dx, double dy) /*-{
		this.drawImage(image, dx, dy);
	}-*/;

	private native void drawImageImpl(Element image, double dx, double dy,
			double dWidth, double dHeight) /*-{
		this.drawImage(image, dx, dy, dWidth, dHeight);
	}-*/;

	private native void drawImageImpl(Element image, double sx, double sy,
			double sWidth, double sHeight, double dx, double dy, double dWidth,
			double dHeight) /*-{
		this.drawImage(image, sx, sy, sWidth, sHeight, dx, dy, dWidth, dHeight);
	}-*/;
}
