package com.bitcoinvisualizer.client.presenter;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.bitcoinvisualizer.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Widget;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy>
{

	public interface MyView extends View
	{
		// TODO Put your view methods here
	}

	@ProxyStandard
	@NameToken(NameTokens.home)
	public interface MyProxy extends ProxyPlace<HomePresenter>
	{
	}

	@Inject
	public HomePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy)
	{
		super(eventBus, view, proxy);
		
		
	}

	// We added two new annotations, @ProxyCodeSplit, used by GWTP proxy
	// generator to create a proxy that uses code splitting, and
	// @NameToken(NameTokens.homePage) to bind this presenter to the "!homePage"
	// history token. When "#!homePage" is requested on the URL, this presenter
	// will be revealed.
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
}
