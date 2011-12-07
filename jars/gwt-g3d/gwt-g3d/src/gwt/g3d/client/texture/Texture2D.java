package gwt.g3d.client.texture;

import gwt.g2d.client.graphics.canvas.CanvasElement;
import gwt.g2d.client.media.VideoElement;
import gwt.g3d.client.gl2.enums.PixelFormat;
import gwt.g3d.client.gl2.enums.PixelInternalFormat;
import gwt.g3d.client.gl2.enums.PixelType;
import gwt.g3d.client.gl2.enums.TextureParameterName;
import gwt.g3d.client.gl2.enums.TextureTarget;
import gwt.g3d.client.gl2.enums.TextureWrapMode;

import com.google.gwt.dom.client.ImageElement;

/**
 * Represents a 2D texture.
 * 
 * @author hao1300@gmail.com
 */
public class Texture2D extends AbstractTexture {
	
	public Texture2D() {
		super(TextureTarget.TEXTURE_2D);
	}
	
	@Override
	public void setWrapMode(TextureWrapMode wrapMode) {
		setWrapMode(TextureParameterName.TEXTURE_WRAP_S, wrapMode);
		setWrapMode(TextureParameterName.TEXTURE_WRAP_T, wrapMode);
	}

	/**
	 * Specifies a 2D image
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param image
	 */
	public void texImage(CanvasElement image) {
		gl.texImage2D(getTarget(), 0, PixelInternalFormat.RGBA, PixelFormat.RGBA,
			 PixelType.UNSIGNED_BYTE,	image);
	}
	
	/**
	 * Specifies a 2D image
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param image
	 */
	public void texImage(ImageElement image) {
		gl.texImage2D(getTarget(), 0, PixelInternalFormat.RGBA, PixelFormat.RGBA,
				 PixelType.UNSIGNED_BYTE,	image);
	}
	
	/**
	 * Specifies a 2D image
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param image
	 */
	public void texImage(VideoElement image) {
		gl.texImage2D(getTarget(), 0, PixelInternalFormat.RGBA, PixelFormat.RGBA,
				 PixelType.UNSIGNED_BYTE,	image);
	}
	
	/**
	 * Specifies a 2D image
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param image
	 */
	public void texSubImage(int xOffset, int yOffset, CanvasElement image) {
		gl.texSubImage2D(getTarget(), 0, xOffset, yOffset, PixelFormat.RGBA,
				 PixelType.UNSIGNED_BYTE, image);
	}
	
	/**
	 * Specifies a 2D image
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param image
	 */
	public void texSubImage(int xOffset, int yOffset, ImageElement image) {
		gl.texSubImage2D(getTarget(), 0, xOffset, yOffset, PixelFormat.RGBA,
				 PixelType.UNSIGNED_BYTE, image);
	}
	
	/**
	 * Specifies a 2D image
	 * Requires: {@link #bind()} must be called before using this method.
	 * 
	 * @param image
	 */
	public void texSubImage(int xOffset, int yOffset, VideoElement image) {
		gl.texSubImage2D(getTarget(), 0, xOffset, yOffset, PixelFormat.RGBA,
				 PixelType.UNSIGNED_BYTE, image);
	}
}
