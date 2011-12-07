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
package gwt.g3d.test.client;

import static gwt.g3d.client.math.MatrixStack.MODELVIEW;
import static gwt.g3d.client.math.MatrixStack.PROJECTION;
import gwt.g2d.client.input.KeyboardManager;
import gwt.g3d.client.gl2.enums.BeginMode;
import gwt.g3d.client.gl2.enums.BlendingFactorDest;
import gwt.g3d.client.gl2.enums.BlendingFactorSrc;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.EnableCap;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureUnit;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.ShaderResource;
import gwt.g3d.resources.client.Texture2DResource;
import gwt.g3d.test.client.extras.Lesson9DemoExtra;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Color3f;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-port for the lesson 9.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson9Demo extends AbstractLessonDemo {
	private static final int LESSON_NUMBER = 9;
	private static final int NUM_STARS = 50;
	private final Lesson9DemoExtra extraWidget = new Lesson9DemoExtra();
	private Texture2D starTexture;
	
	private StaticMesh mesh;
	private double lastTime;
	private final List<Star> stars = new ArrayList<Star>();
	
	/** User defined variables. */
	private float zoom, tilt, spin;
	
	/** Keyboard handlers */
	private final KeyboardManager keyboardManager = new KeyboardManager();
	
	protected Lesson9Demo() {
		super(LESSON_NUMBER);
	}	
	
	@Override
	public void dispose() {
		mesh.dispose();
		starTexture.dispose();
		keyboardManager.unmanage();
		shader.dispose();
		stars.clear();
		gl.disable(EnableCap.BLEND);
		gl.enable(EnableCap.DEPTH_TEST);
	}
	
	@Override
	public ClientBundleWithLookup getClientBundle() {
		return Resources.INSTANCE;
	}
	
	@Override
	public Widget getExtraWidget() {
		return extraWidget;
	}
	
	@Override
	public void update() {
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);
 
		handleKeys();
		
    MODELVIEW.pushIdentity();
    MODELVIEW.translate(0, 0, zoom);
    MODELVIEW.rotate((float) Math.toRadians(tilt), 1, 0, 0);
 
    boolean twinkle = extraWidget.isTwinkle();
    for (Star star : stars) {
      star.draw(twinkle);
      spin += 0.1f;
    }
    
    MODELVIEW.pop();
    
    double currTime = System.currentTimeMillis();
    float elapsed = (float) (currTime - lastTime);
    for (Star star : stars) {
      star.animate(elapsed);
    }
    lastTime = currTime;
	}
	
	@Override
	protected void initImpl() {
		super.initImpl();

		zoom = -15;
		tilt = 90;
		spin = 0;
		
		keyboardManager.manage(getSurface());
		keyboardManager.setPreventDefault(true);
    
    gl.blendFunc(BlendingFactorSrc.SRC_ALPHA, BlendingFactorDest.ONE);
    gl.enable(EnableCap.BLEND);
    gl.disable(EnableCap.DEPTH_TEST);
    
		gl.activeTexture(TextureUnit.TEXTURE0);
    gl.uniform1i(shader.getUniformLocation("uSampler"), 0);
    
		Resources.INSTANCE.star().getTexture(new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				starTexture = resource.createTexture(gl);
			}
			
			@Override
			public void onError(ResourceException e) {
				Window.alert("Fail to load image.");
			}
		});
    
		PROJECTION.pushIdentity();
		PROJECTION.perspective(45, 1, .1f, 100);
		gl.uniformMatrix(shader.getUniformLocation("uPMatrix"), PROJECTION.get());
		PROJECTION.pop();
		
		initMesh();
    initWorldObject();
    
    lastTime = System.currentTimeMillis();
	}
	
	private void drawStar() {
    setMatrixUniforms();
    mesh.draw();
  }
	
	/**
	 * Initializes the star mesh.
	 * 
	 * @param gl
	 */
	private void initMesh() {
		float[] vertices = { -1, -1, 0, 1, -1, 0, -1, 1, 0, 1, 1, 0 };
		float[] texCoords = { 0, 0, 1, 0, 0, 1, 1, 1 };
		int[] indices = { 0, 1, 2, 3 };
		mesh = new StaticMesh(gl, vertices, null, texCoords, indices);
		mesh.setPositionIndex(shader.getAttributeLocation("aVertexPosition"));
		mesh.setNormalIndex(-1);
		mesh.setTexCoordIndex(shader.getAttributeLocation("aTextureCoord"));
		mesh.setBeginMode(BeginMode.TRIANGLE_STRIP);
	}
	
	/** 
	 * Initializes the stars.
	 */
	private void initWorldObject() {
		for (int i = 0; i < NUM_STARS; i++) {
      stars.add(new Star((i / (float) NUM_STARS) * 5, i / (float) NUM_STARS));
    }
	}
	
	private void setMatrixUniforms() {
		gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
	}
	
	private void handleKeys() {
		if (keyboardManager.isButtonDown(KeyCodes.KEY_PAGEUP)) {
			zoom += 0.1f;
		}
		if (keyboardManager.isButtonDown(KeyCodes.KEY_PAGEDOWN)) {
			zoom -= 0.1f;
		}
		if (keyboardManager.isButtonDown(KeyCodes.KEY_UP)) {
			tilt += 2;
		}
		if (keyboardManager.isButtonDown(KeyCodes.KEY_DOWN)) {
			tilt -= 2;
		}
	}
	
	/** A star object. */
	private class Star {
		private float angle = 0, dist, rotationSpeed;
		private Color3f color = new Color3f(), twinkleColor = new Color3f();
		
		public Star(float startingDistance, float rotationSpeed) {
	    this.dist = startingDistance;
	    this.rotationSpeed = rotationSpeed;
	    // Set the colors to a starting value.
	    randomiseColors();
		}    
 
    public void animate(float elapsedTime) {
      float effectiveFPMS = 60f / 1000f;
      angle += rotationSpeed * effectiveFPMS * elapsedTime;
 
      // Decrease the distance, resetting the star to the outside of the spiral 
      // if it's at the center.
      dist -= 0.01f * effectiveFPMS * elapsedTime;
      if (dist < 0) {
        dist += 5;
        randomiseColors();
      } 
    } 
 
    public void draw(boolean twinkle) {
    	MODELVIEW.push();
    	
    	// Move to the star's position
    	MODELVIEW.rotateY((float) Math.toRadians(angle));
    	MODELVIEW.translate(dist, 0, 0);
      
      // Rotate back so that the star is facing the viewer
    	MODELVIEW.rotateY((float) Math.toRadians(-angle));
    	MODELVIEW.rotateX((float) Math.toRadians(-tilt));
      
      if (twinkle) {
        // Draw a non-rotating star in the alternate "twinkling" color
        gl.uniform(shader.getUniformLocation("uColor"), twinkleColor);
        drawStar();
      }
      
      // All stars spin around the Z axis at the same rate
      MODELVIEW.rotateZ((float) Math.toRadians(spin));
      
      // Draw the star in its main color
      gl.uniform(shader.getUniformLocation("uColor"), color);
      drawStar();
 
      MODELVIEW.pop();
    }
    
    /**
     * Assigns a random color to this star.
     */
    private void randomiseColors() {
    	// Give the star a random color for normal circumstances...
    	color.set((float) Random.nextDouble(),
    			(float) Random.nextDouble(), (float) Random.nextDouble());
 
      // When the star is twinkling, we draw it twice, once in the color below 
      // (not spinning) and then once in the main color defined above.
      twinkleColor.set((float) Random.nextDouble(),
      		(float) Random.nextDouble(), (float) Random.nextDouble());
    }
  }
	
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source({"shaders/lesson9.vp", "shaders/lesson9.fp"})
		ShaderResource shader();
		
		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();
		
		@Source("images/star.gif")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		ExternalTexture2DResource star();
	}
}
