package com.bitcoinvisualizer.client.presenter;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

public class MainPagePresenter extends Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy>
{
	  /**
	   * Child presenters can fire a RevealContentEvent with TYPE_SetMainContent to set themselves
	   * as children of this presenter.
	   */
	  @ContentSlot
	  public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	public interface MyView extends View
	{
		// TODO Put your view methods here
	}

	@ProxyStandard
	public interface MyProxy extends Proxy<MainPagePresenter>
	{
	}

	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy)
	{
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent()
	{
		RevealRootContentEvent.fire(this, this);
	}
}
