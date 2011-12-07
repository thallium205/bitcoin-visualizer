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
import static java.lang.Float.parseFloat;
import gwt.g2d.client.input.KeyboardManager;
import gwt.g3d.client.gl2.array.Float32Array;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureUnit;
import gwt.g3d.client.mesh.SimpleStaticMesh;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.ExternalTexture2DResource;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.ShaderResource;
import gwt.g3d.resources.client.Texture2DResource;
import gwt.g3d.test.client.extras.Lesson10DemoExtra;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-port for the lesson 10.
 * 
 * @author hao1300@gmail.com
 */
public class Lesson10Demo extends AbstractLessonDemo {
	private static final int LESSON_NUMBER = 10;
	private Texture2D mudTexture;
	private final Lesson10DemoExtra extraWidget = new Lesson10DemoExtra();
	
	private SimpleStaticMesh mesh = new SimpleStaticMesh();
	private double lastTime;
	private boolean isWorldLoaded;
	
	/** User defined variables. */
	private float pitch, pitchRate;
  private float yaw, yawRate;
  private float xPos, yPos, zPos;
  private float speed;
  private float joggingAngle;
	
	/** Keyboard handlers */
	private final KeyboardManager keyboardManager = new KeyboardManager();
	
	protected Lesson10Demo() {
		super(LESSON_NUMBER);
	}
	
	@Override
	public void dispose() {
		mesh.dispose();
		mudTexture.dispose();
		keyboardManager.unmanage();
		shader.dispose();
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
		if (!isWorldLoaded)  {
			return;
		}
		handleKeys();
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);
 
		MODELVIEW.push();
		MODELVIEW.rotateX((float) Math.toRadians(-pitch));
		MODELVIEW.rotateY((float) Math.toRadians(-yaw));
    MODELVIEW.translate(-xPos, -yPos, -zPos);
    gl.uniformMatrix(shader.getUniformLocation("uMVMatrix"), MODELVIEW.get());
    mesh.draw();
    MODELVIEW.pop();
    
    double currTime = System.currentTimeMillis();
    float elapsed = (float) (currTime - lastTime);
    if (speed != 0) {
      xPos -= (float) (Math.sin(Math.toRadians(yaw)) * speed * elapsed);
      zPos -= (float) (Math.cos(Math.toRadians(yaw)) * speed * elapsed);

      // 0.6 "fiddle factor" - makes it feel more realistic :-)
      joggingAngle += elapsed * 0.6f;
      yPos = (float) Math.sin(Math.toRadians(joggingAngle)) / 20 + 0.4f;
    }

    yaw += yawRate * elapsed;
    pitch += pitchRate * elapsed;
    lastTime = currTime;
	}	
	
	@Override
	protected void initImpl() {
		super.initImpl();
		
		resetVariables();
	  
		keyboardManager.manage(getSurface());
		keyboardManager.setPreventDefault(true);
    
		gl.activeTexture(TextureUnit.TEXTURE0);
    gl.uniform1i(shader.getUniformLocation("uSampler"), 0);
 		
		Resources.INSTANCE.mud().getTexture(
				new ResourceCallback<Texture2DResource>() {
			@Override
			public void onSuccess(Texture2DResource resource) {
				mudTexture = resource.createTexture(gl);
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
		
		mesh.init(gl);
		mesh.setPositionIndex(shader.getAttributeLocation("aVertexPosition"));
		mesh.setTexCoordIndex(shader.getAttributeLocation("aTextureCoord"));
		
		loadWorld();
		
    lastTime = System.currentTimeMillis();
	}
	
	private void handleKeys() {
		if (keyboardManager.isButtonDown(KeyCodes.KEY_PAGEUP)) {
      pitchRate = 0.1f;
    } else if (keyboardManager.isButtonDown(KeyCodes.KEY_PAGEDOWN)) {
      pitchRate = -0.1f;
    } else {
      pitchRate = 0;
    }
 
    if (keyboardManager.isButtonDown(KeyCodes.KEY_LEFT) 
    		|| keyboardManager.isButtonDown((int) 'A')) {
      yawRate = 0.1f;
    } else if (keyboardManager.isButtonDown(KeyCodes.KEY_RIGHT) 
    		|| keyboardManager.isButtonDown((int) 'D')) {
      yawRate = -0.1f;
    } else {
      yawRate = 0;
    }
 
    if (keyboardManager.isButtonDown(KeyCodes.KEY_UP) 
    		|| keyboardManager.isButtonDown((int) 'W')) {
      speed = 0.003f;
    } else if (keyboardManager.isButtonDown(KeyCodes.KEY_DOWN) 
    		|| keyboardManager.isButtonDown((int) 'S')) {
      speed = -0.003f;
    } else {
      speed = 0;
    }
	}
	
	private void loadWorld() {
		try {
			Resources.INSTANCE.world().getText(new ResourceCallback<TextResource>() {
				@Override
				public void onSuccess(TextResource resource) {
					String data = resource.getText();
					String[] lines = data.split("\n");
			    final List<Float> vertexPositions = new ArrayList<Float>();
			    final List<Float> vertexTextureCoords = new ArrayList<Float>();
			    int vertexCount = 0;
			    for (String line : lines) {
			      String[] vals = line.replaceAll("^\\s+", "").split("\\s+");
			      if (vals.length == 5 && !vals[0].equals("//")) {
			        // It is a line describing a vertex; get X, Y and Z first
			        vertexPositions.add(parseFloat(vals[0]));
			        vertexPositions.add(parseFloat(vals[1]));
			        vertexPositions.add(parseFloat(vals[2]));
			 
			        // And then the texture coords
			        vertexTextureCoords.add(parseFloat(vals[3]));
			        vertexTextureCoords.add(parseFloat(vals[4]));
			 
			        vertexCount++;
			      }
			    }
			    mesh.setPositionData(toFloatArray(vertexPositions));
			    mesh.setTexCoordData(toFloatArray(vertexTextureCoords));
			    mesh.setCount(vertexCount);
			    isWorldLoaded = true;
				}
				
				@Override
				public void onError(ResourceException e) {
					Window.alert("Fail to load world due to: " + e.getMessage());
				}
				
				private Float32Array toFloatArray(List<Float> floatList) {
					Float32Array floatArray = Float32Array.create(floatList.size());
			    int i = 0;
			    for (float v : floatList) {
			    	floatArray.set(i++, v);
			    }
			    return floatArray;
				}
			});
		} catch (ResourceException e) {
			Window.alert("Fail to load world due to: " + e.getMessage());
		}
	}
	
	private void resetVariables() {
		pitch = 0;
		pitchRate = 0;
	  yaw = 0;
	  yawRate = 0;
	  xPos = 0;
	  yPos = .4f;
	  zPos = 0;
	  speed = 0;
	  joggingAngle = 0;
	  isWorldLoaded = false;
	}
		
	/** Resource files. */
	interface Resources extends ClientBundleWithLookup {
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source({"shaders/lesson10.vp", "shaders/lesson10.fp"})
		ShaderResource shader();
		
		@Source("Lesson" + LESSON_NUMBER + "Demo.java")
		ExternalTextResource source();
		
		@Source("extras/world.txt")
		ExternalTextResource world();
		
		@Source("images/mud.gif")
		@MagFilter(TextureMagFilter.LINEAR)
		@MinFilter(TextureMinFilter.LINEAR)
		ExternalTexture2DResource mud();
	}
}
