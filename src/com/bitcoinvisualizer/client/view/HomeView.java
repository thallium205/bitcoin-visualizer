package com.bitcoinvisualizer.client.view;

import gwt.g3d.client.Surface3D;
import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.enums.ClearBufferMask;
import gwt.g3d.client.gl2.enums.DepthFunction;
import gwt.g3d.client.gl2.enums.EnableCap;
import gwt.g3d.client.math.MatrixStack;
import gwt.g3d.client.mesh.StaticMesh;
import gwt.g3d.client.primitive.PrimitiveFactory;
import gwt.g3d.client.shader.LambertianShader;
import gwt.g3d.client.shader.ShaderException;

import com.bitcoinvisualizer.client.presenter.HomePresenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class HomeView extends ViewImpl implements HomePresenter.MyView
{

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, HomeView>
	{
	}

	@Inject
	public HomeView(final Binder binder)
	{
		widget = binder.createAndBindUi(this);
		bindWebGL();
	}

	@Override
	public Widget asWidget()
	{
		return widget;
	}

	private void bindWebGL()
	{
		// Adds the Surface3D to the document.
		Surface3D surface = new Surface3D(500, 500);
		RootPanel.get().add(surface);
		final GL2 gl = surface.getGL();
		if (gl == null)
		{
			Window.alert("No WebGL context found. Exiting.");
			return;
		}

		// Sets up the GL context.
		gl.clearColor(0.0f, 0f, 0f, 1f);
		gl.clearDepth(1);
		gl.viewport(0, 0, surface.getWidth(), surface.getHeight());

		gl.enable(EnableCap.DEPTH_TEST);
		gl.depthFunc(DepthFunction.LEQUAL);
		gl.clear(ClearBufferMask.COLOR_BUFFER_BIT, ClearBufferMask.DEPTH_BUFFER_BIT);

		// Creates a lambertian shader.
		LambertianShader shader = new LambertianShader();
		try
		{
			shader.init(gl);
		} catch (ShaderException e)
		{
			Window.alert("Error loading the shader.");
			return;
		}

		// Binds the shader.
		shader.bind();

		// Creates a sphere.
		StaticMesh mesh = new StaticMesh(gl, PrimitiveFactory.makeSphere(30, 30));
		mesh.setPositionIndex(shader.getAttributePosition());
		mesh.setNormalIndex(shader.getAttributeNormal());

		shader.setLightPosition(0, 5, 5);
		shader.setDiffuseColor(1, 0, 0, 1);

		// Sets up the model view matrix.
		MatrixStack.MODELVIEW.push();
		MatrixStack.MODELVIEW.translate(0, 0, -5);
		shader.setModelViewMatrix(MatrixStack.MODELVIEW.get());
		MatrixStack.MODELVIEW.pop();

		// Sets up a basic camera for projection.
		MatrixStack.PROJECTION.pushIdentity();
		MatrixStack.PROJECTION.perspective(45, 1, .1f, 100);
		shader.setProjectionMatrix(MatrixStack.PROJECTION.get());
		MatrixStack.PROJECTION.pop();

		// Draws the mesh.
		mesh.draw();

		mesh.dispose();
		shader.dispose();

	}

}
