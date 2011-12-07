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

import gwt.g2d.client.graphics.Color;
import gwt.g2d.client.math.Vector2;

/**
 * Adapter for accessing the image data object.
 * 
 * @author hao1300@gmail.com
 */
public final class ImageDataAdapter {
	private final int width, height;
	private final ImageData imageData;
	private final CanvasPixelArray pixelData;
	
	/**
	 * Casts the JavaScriptObject into an ImageData.
	 * Requires: the given JavaScriptObject must be an instance of ImageData.
	 * 
	 * @param imageData
	 */
	public ImageDataAdapter(ImageData imageData) {
		this.imageData = imageData;
		this.width = imageData.getWidth();
		this.height = imageData.getHeight();
		this.pixelData = imageData.getPixelArray();
	}
	
	/**
	 * Gets the color at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return color
	 */
	public Color getColor(int x, int y) {
		int index = getIndex(x, y);
		return new Color(pixelData.getData(index), 
				pixelData.getData(index + 1), 
				pixelData.getData(index + 2),
				pixelData.getData(index + 3) / 255.0);
	}
	
	/**
	 * Gets the color at the given position.
	 * 
	 * @param position
	 * @return color
	 */
	public Color getColor(Vector2 position) {
		return getColor(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the red value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return red value
	 */
	public int getRed(int x, int y) {
		return pixelData.getData(getIndex(x, y));
	}
	
	/**
	 * Gets the red value at the given position.
	 * 
	 * @param position
	 * @return red value
	 */
	public int getRed(Vector2 position) {
		return getRed(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the green value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return green value
	 */
	public int getGreen(int x, int y) {
		return pixelData.getData(getIndex(x, y) + 1);
	}
	
	/**
	 * Gets the green value at the given position.
	 * 
	 * @param position
	 * @return green value
	 */
	public int getGreen(Vector2 position) {
		return getGreen(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the blue value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return blue value
	 */
	public int getBlue(int x, int y) {
		return pixelData.getData(getIndex(x, y) + 2);
	}
	
	/**
	 * Gets the blue value at the given position.
	 * 
	 * @param position
	 * @return blue value
	 */
	public int getBlue(Vector2 position) {
		return getBlue(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the alpha value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return alpha value
	 */
	public int getAlpha(int x, int y) {
		return pixelData.getData(getIndex(x, y) + 3);
	}
	
	/**
	 * Gets the alpha value at the given position.
	 * 
	 * @param position
	 * @return alpha value
	 */
	public int getAlpha(Vector2 position) {
		return getAlpha(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Sets the color at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	public void setColor(int x, int y, Color color) {
		int index = getIndex(x, y);
		pixelData.setData(index, color.getR());
		pixelData.setData(index + 1, color.getG());
		pixelData.setData(index + 2, color.getB());
		pixelData.setData(index + 3, (int) (color.getAlpha() * 255));
	}
	
	/**
	 * Sets the color at the given position.
	 * 
	 * @param position
	 * @param color
	 */
	public void setColor(Vector2 position, Color color) {
		setColor(position.getIntX(), position.getIntY(), color);
	}
	
	/**
	 * Sets the red value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setRed(int x, int y, int value) {
		pixelData.setData(getIndex(x, y), value);
	}
	
	/**
	 * Sets the red value at the given position.
	 * 
	 * @param position
	 * @param value
	 */
	public void setRed(Vector2 position, int value) {
		setRed(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Sets the green value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setGreen(int x, int y, int value) {
		pixelData.setData(getIndex(x, y) + 1, value);
	}
	
	/**
	 * Sets the green value at the given position.
	 * 
	 * @param position
	 * @param value
	 */
	public void setGreen(Vector2 position, int value) {
		setGreen(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Sets the blue value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setBlue(int x, int y, int value) {
		pixelData.setData(getIndex(x, y) + 2, value);
	}
	
	/**
	 * Sets the blue value at the given position.
	 * 
	 * @param position
	 * @param value
	 */
	public void setBlue(Vector2 position, int value) {
		setBlue(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Sets the alpha value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setAlpha(int x, int y, int value) {
		pixelData.setData(getIndex(x, y) + 3, value);
	}
	
	/**
	 * Sets the alpha value at the given position.
	 * 
	 * @param position
	 * @param value
	 */
	public void setAlpha(Vector2 position, int value) {
		setAlpha(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Gets the image data as a JavaScriptObject.
	 */
	public ImageData getImageData() {
		return imageData;
	}
	
	/**
	 * Gets the image data's pixel array representation.
	 * 
	 * @return pixel array
	 */
	public CanvasPixelArray getPixelData() {
		return pixelData;
	}
	
	/**
	 * Gets the index based on the given (x, y) position.
	 * 
	 * @param x
	 * @param y
	 */
	private int getIndex(int x, int y) {
		return 4 * (y * width + x);
	}
	
	/**
	 * Gets the image data representation.
	 */
	public CanvasPixelArray getData() {
		return pixelData;
	}
	
	/**
	 * Gets the width of the image data.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the image data.
	 */
	public int getHeight() {
		return height;
	}
}
