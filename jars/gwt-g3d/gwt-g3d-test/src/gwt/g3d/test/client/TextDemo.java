package gwt.g3d.test.client;

import static gwt.g3d.client.math.MatrixStack.MODELVIEW;
import static gwt.g3d.client.math.MatrixStack.PROJECTION;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureUnit;
import gwt.g3d.client.gl2.enums.TextureWrapMode;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.primitive.PrimitiveFactory;
import gwt.g3d.client.shader.ShaderException;
import gwt.g3d.client.shader.TextureShader;
import gwt.g3d.client.text.TextRenderer;
import gwt.g3d.client.texture.Texture2D;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.user.client.Window;

public class TextDemo extends AbstractDemo {
	private final TextureShader shader = new TextureShader();
	private StaticMesh mesh;
	private float angle;

	protected TextDemo() {
		super("Text Demo", "");
	}

	@Override
	protected void initImpl() {
		try {
			shader.init(gl);
		} catch (ShaderException e) {
			Window.alert(e.getMessage());
			return;
		}
		shader.bind();

		angle = 0;

		mesh = new StaticMesh(gl, PrimitiveFactory.makeBox());
		mesh.setPositionIndex(shader.getAttributePosition());
		mesh.setNormalIndex(-1);
		mesh.setTexCoordIndex(shader.getAttributeTexCoord());

		shader.setTintColor(1, 1, 1, 1f);

		MODELVIEW.pushIdentity();
		shader.setModelViewMatrix(MODELVIEW.get());
		MODELVIEW.pop();
		
		PROJECTION.pushIdentity();
		PROJECTION.perspective(45, 1, .1f, 100);
		shader.setProjectionMatrix(PROJECTION.get());
		PROJECTION.pop();		

		shader.setTexture(TextureUnit.TEXTURE0);

		gl.activeTexture(TextureUnit.TEXTURE0);

		Texture2D texture = new Texture2D();
		texture.init(gl);
		texture.bind();
		texture.setMagFilter(TextureMagFilter.LINEAR);
		texture.setMinFilter(TextureMinFilter.LINEAR);
		texture.setWrapMode(TextureWrapMode.CLAMP_TO_EDGE);
		TextRenderer textRenderer = new TextRenderer(getSurface());
		textRenderer.setFont("80px sans-serif");
		textRenderer.fillText("Hello World");
		textRenderer.toTexture(texture);
		mesh.draw();
	}

	@Override
	public void update() {
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, 
				ClearBufferMask.DEPTH_BUFFER_BIT);

		MODELVIEW.push();
		MODELVIEW.translate(0, 0, -5);
		MODELVIEW.rotateY(angle += .01f);
		shader.setModelViewMatrix(MODELVIEW.get());
		MODELVIEW.pop();

		mesh.draw();
	}

	@Override
	public void dispose() {
		shader.dispose();
		mesh.dispose();
	}

	@Override
	public ClientBundleWithLookup getClientBundle() {
		return Resources.INSTANCE;
	}
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);

		@Source("TextDemo.java")
		ExternalTextResource source();
	}
}
