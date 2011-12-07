package com.bitcoinvisualizer.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.bitcoinvisualizer.client.place.ClientPlaceManager;
import com.bitcoinvisualizer.client.place.DefaultPlace;
import com.bitcoinvisualizer.client.place.NameTokens;
import com.bitcoinvisualizer.client.view.AdminView;
import com.bitcoinvisualizer.client.view.HomeView;
import com.bitcoinvisualizer.client.view.MainPageView;
import com.bitcoinvisualizer.client.presenter.MainPagePresenter;
import com.bitcoinvisualizer.client.presenter.HomePresenter;
import com.bitcoinvisualizer.client.presenter.AdminPresenter;


public class ClientModule extends AbstractPresenterModule
{

	@Override
	protected void configure()
	{
		install(new DefaultModule(ClientPlaceManager.class));
		
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.home);

		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class, MainPageView.class, MainPagePresenter.MyProxy.class);

		bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);		

		bindPresenter(AdminPresenter.class, AdminPresenter.MyView.class, AdminView.class, AdminPresenter.MyProxy.class);
	}
}
