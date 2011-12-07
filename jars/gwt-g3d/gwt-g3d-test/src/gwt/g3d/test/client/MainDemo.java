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

import gwt.g2d.client.util.FpsTimer;
import gwt.g3d.client.Surface3D;
import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.WebGLContextAttributes;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.DepthFunction;
import gwt.g3d.client.gl2.enums.EnableCap;
import gwt.g3d.resources.client.ShaderResource;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

/** 
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainDemo implements EntryPoint {
	private static final AbstractDemo[] DEMOS = new AbstractDemo[] {
			new Lesson1Demo(),
			new Lesson2Demo(),
			new Lesson3Demo(),
			new Lesson4Demo(),
			new Lesson5Demo(),
			new Lesson6Demo(),
			new Lesson7Demo(),
			new Lesson8Demo(),
			new Lesson9Demo(),
			new Lesson10Demo(),
			new Lesson11Demo(),
			new Lesson12Demo(),
			new Lesson13Demo(),
			new Lesson14Demo(),
			new Lesson15Demo(),
			new HelloWorldDemo(),
			new ModelDemo(),
			new RotatingBoxDemo(),
			new TextureDemo(),
			new TextDemo(),
			new StencilBufferDemo(),
	};
	private Surface3D surface = new Surface3D(500, 500, new WebGLContextAttributes() {{
		setStencilEnable(true);
	}});
	private final ListBox demoListBox = new ListBox();
	private final Label fpsLabel = new Label();
	private final TextArea sourceTextArea = new TextArea();
	private final Anchor referenceLink = new Anchor();
	private final FlowPanel extraPanel = new FlowPanel();
	private AbstractDemo currentDemo;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		WebGLContextAttributes contextAttribs = new WebGLContextAttributes();
		contextAttribs.setStencilEnable(true);
		surface = new Surface3D(500, 500, contextAttribs);
		 
		HorizontalSplitPanel mainPanel = new HorizontalSplitPanel();
		mainPanel.setSplitPosition((20 + surface.getWidth()) + "px");
		RootPanel.get().add(mainPanel);
		mainPanel.setLeftWidget(createLeftWidget());
		mainPanel.setRightWidget(createRightWidget());
		
		final GL2 gl = surface.getGL();
		if (gl == null) {
			Window.alert("No WebGL context found. Exiting.");
			return;
		}
		
		gl.clearColor(0.0f, 0f, 0f, 1f);
		gl.clearDepth(1);
		gl.viewport(0, 0, surface.getWidth(), surface.getHeight());
		
		gl.enable(EnableCap.DEPTH_TEST);
		gl.depthFunc(DepthFunction.LEQUAL);
    gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);

		runDemo(gl);
	}
	
	/**
	 * Creates the widget to be put on the left side of the demo.
	 */
	private Widget createLeftWidget() {
		Panel leftPanel = new FlowPanel();
		leftPanel.add(new HTML("This demo was created using <a href=\""
				+ "http://code.google.com/p/gwt-g3d/\">gwt-g3d</a>"));
		leftPanel.add(fpsLabel);
		
		Panel surfacePanel = new FlowPanel();
		surfacePanel.add(surface);
		leftPanel.add(demoListBox);
		leftPanel.add(surfacePanel);
		Panel sourceButtonsPanel = new FlowPanel();
		sourceButtonsPanel.getElement().getStyle().setPadding(2, Unit.PX);
		leftPanel.add(sourceButtonsPanel);
		sourceButtonsPanel.add(new Button("Java Source", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ExternalTextResource textResource = 
						(ExternalTextResource) currentDemo.getClientBundle().getResource("source");
				loadExternalTextResource(textResource);
			}
		}));
		sourceButtonsPanel.add(new Button("Vertex Shader", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ResourcePrototype resource = currentDemo.getClientBundle().getResource("shader");
				if (resource != null) {
					sourceTextArea.setText(((ShaderResource) resource).getVertexShaderSource());
				} else {
					sourceTextArea.setText(
							"Unable to load vertex shader source or it is built-in.");
				}
			}
		}));
		sourceButtonsPanel.add(new Button("Fragment Shader", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ResourcePrototype resource = currentDemo.getClientBundle().getResource("shader");
				if (resource != null) {
					sourceTextArea.setText(((ShaderResource) resource).getFragmentShaderSource());
				} else {
					sourceTextArea.setText(
							"Unable to load fragment shader source or it is built-in.");
				}
			}
		}));
		leftPanel.add(new InlineLabel("Reference: "));
		referenceLink.setTarget("_blank");
		leftPanel.add(referenceLink);
		leftPanel.add(extraPanel);
		return leftPanel;
	}
	
	/**
	 * Creates the widget to be put on the right side of the demo.
	 */
	private Widget createRightWidget() {
		sourceTextArea.setSize("100%", "100%");
		sourceTextArea.setReadOnly(true);
		sourceTextArea.removeStyleName("gwt-TextArea-readonly");
		return sourceTextArea;
	}
	
	/**
	 * Loads the external text resource into the source text area.
	 */
	private void loadExternalTextResource(ExternalTextResource textResource) {
		try {
			textResource.getText(new ResourceCallback<TextResource>() {
				@Override
				public void onSuccess(TextResource resource) {
					sourceTextArea.setText(resource.getText().replaceAll("\t", "  "));
				}
				
				@Override
				public void onError(ResourceException e) {
					sourceTextArea.setText("Fails to load source file.");
				}
			});
		} catch (ResourceException e) {
			sourceTextArea.setText("Fails to load source file.");
		}
	}
	
	/**
	 * Initializes and runs the demo.
	 */
	private void runDemo(final GL2 gl) {
		for (AbstractDemo demo : DEMOS) {
			demo.setSurface(surface);
			demoListBox.addItem(demo.getName());
		}
		setCurrentDemo(DEMOS[0]);
		currentDemo.init(gl);		
		
		demoListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				currentDemo.dispose();
				
				gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);
				setCurrentDemo(DEMOS[demoListBox.getSelectedIndex()]);
				currentDemo.init(gl);
			}
		});
		
		FpsTimer timer = new FpsTimer(60) {
			@Override
			public void update() {
				fpsLabel.setText("FPS: " + getFps());
				currentDemo.update();
			}
		};
		timer.start();
	}
	
	/**
	 * Sets the currently selected demo.
	 */
	private void setCurrentDemo(AbstractDemo demo) {
		currentDemo = demo;
		if (currentDemo.getReference() != null) {
			referenceLink.setHref(currentDemo.getReference());
			referenceLink.setText(currentDemo.getReference());
		} else {
			referenceLink.setHref("");
			referenceLink.setText("");
		}
		extraPanel.clear();
		if (currentDemo.getExtraWidget() != null) {
			extraPanel.add(currentDemo.getExtraWidget());
		}
	}
}
