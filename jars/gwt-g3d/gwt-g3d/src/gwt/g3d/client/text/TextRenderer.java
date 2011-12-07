package gwt.g3d.client.text;

import gwt.g2d.client.graphics.Color;
import gwt.g2d.client.graphics.KnownColor;
import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.TextAlign;
import gwt.g2d.client.graphics.TextBaseline;
import gwt.g2d.client.graphics.TextMeasurer;
import gwt.g3d.client.Surface3D;
import gwt.g3d.client.texture.Texture2D;

import javax.vecmath.Vector2d;

/**
 * Helper class for rendering texts onto a texture.
 * 
 * @author hao1300@gmail.com
 */
public class TextRenderer {
	/** Use an underlying 2D surface to draw text. */
	private final Surface surface;
	private final boolean autoResize;
	private Color bgColor, textColor;
	private String font;
	
	/**
	 * Constructor.
	 * The size of the texture will be resized to fit the text.
	 * 
	 * @param canvasWidget
	 */
	public TextRenderer(Surface3D canvasWidget) {
		this.autoResize = true;
		surface = new Surface(100, 100);
		canvasWidget.getElement().appendChild(surface.getElement());
		surface.setVisible(false);
		initTextRenderer();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param canvasWidget
	 * @param width width of the texture
	 * @param height height of the texture
	 */
	public TextRenderer(Surface3D canvasWidget, int width, int height) {
		this.autoResize = false;
		surface = new Surface(width, height);
		canvasWidget.getElement().appendChild(surface.getElement());
		surface.setVisible(false);
		initTextRenderer();
	}
	
	/**
	 * Sets the background color.
	 * 
	 * @param bgColor
	 */
	public void setBackgroundColor(Color bgColor) {
		this.bgColor = bgColor;
	}
	
	/**
	 * Gets the background color.
	 */
	public Color getBackgroundColor() {
		return bgColor;
	}
	
	/**
	 * Sets the color of the text.
	 * 
	 * @param textColor
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	
	/**
	 * Gets the color of the text.
	 */
	public Color getTextColor() {
		return textColor;
	}

	/**
	 * Sets the CSS font of the text.
	 * 
	 * @param font
	 */
	public void setFont(String font) {
		this.font = font;
		surface.setFont(font);
	}

	/**
	 * Gets the CSS font of the text.
	 */
	public String getFont() {
		return font;
	}
	
	/**
	 * Fills the text at the top left corner.
	 * 
	 * @param text
	 */
	public void fillText(String text) {
		fillText(text, 0, 0);
	}
	
	/**
	 * Fills the text where the top left corner is at the given position.
	 * 
	 * @param text
	 * @param position
	 */
	public void fillText(String text, Vector2d position) {
		fillText(text, position.getX(), position.getY());
	}
	
	/**
	 * Fills the text where the top left corner is at the given position.
	 * 
	 * @param text
	 * @param x
	 * @param y
	 */
	public void fillText(String text, double x, double y) {
		surface.setVisible(true);
		prepareDraw(text);
		surface.setFillStyle(textColor)
				.fillText(text, x, y);
		surface.setVisible(false);
	}
	
	/**
	 * Strokes the text at the top left corner.
	 * 
	 * @param text
	 */
	public void strokText(String text) {
		strokText(text, 0, 0);
	}
	
	/**
	 * Strokes the text where the top left corner is at the given position.
	 * 
	 * @param text
	 * @param position
	 */
	public void strokText(String text, Vector2d position) {
		strokText(text, position.getX(), position.getY());
	}
	
	/**
	 * Strokes the text where the top left corner is at the given position.
	 * 
	 * @param text
	 * @param x
	 * @param y
	 */
	public void strokText(String text, double x, double y) {
		surface.setVisible(true);
		prepareDraw(text);
		surface.setStrokeStyle(textColor)
				.strokeText(text, x, y);
		surface.setVisible(false);
	}
	
	/**
	 * Exports the text to a texture.
	 * 
	 * @param texture
	 */
	public void toTexture(Texture2D texture) {
		surface.setVisible(true);
		texture.bind();
		texture.texImage(surface.getCanvas());
		surface.setVisible(false);
	}
	
	/**
	 * Helper class for initializing this TextRenderer.
	 */
	private void initTextRenderer() {
		setFont("10px sans-serif");
		setBackgroundColor(KnownColor.WHITE);
		setTextColor(KnownColor.BLACK);
	}
	
	/**
	 * Preparing the surface to draw the given text.
	 * 
	 * @param text
	 */
	private void prepareDraw(String text) {
		if (autoResize) {
			surface.setSize((int) Math.ceil(measureTextWidth(text)), 
			    	(int) Math.ceil(measureTextHeight(text)));
			surface.setFont(font)
				.setTextAlign(TextAlign.LEFT)
				.setTextBaseline(TextBaseline.TOP);
		}
		surface.fillBackground(bgColor);
	}
	
	/**
	 * Returns the advanced width of the text in the current font in pixels.
	 * 
	 * @param text
	 */
	private double measureTextWidth(String text) {
		return surface.measureText(text);
	}
	
	/**
	 * Measures the height of the text in the current font in pixels.
	 * 
	 * @param text
	 */
	private double measureTextHeight(String text) {
		return TextMeasurer.DEFAULT_TEXT_MEASURER.measureTextHeight(surface, "0");
	}
}
